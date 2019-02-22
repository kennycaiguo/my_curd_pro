package com.mycurdpro.system.service;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.mycurdpro.common.utils.ExecutorServiceUtils;
import com.mycurdpro.common.utils.Id.IdUtils;
import com.mycurdpro.common.utils.freemarker.FreemarkerUtils;
import com.mycurdpro.common.utils.ws.SendMsgUtils;
import com.mycurdpro.system.model.*;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 消息通知工具
 *
 * @author zhangchuang
 */
public class SysNoticeService {
    private final static Logger LOG = LoggerFactory.getLogger(SysNoticeService.class);

    /**
     * 发送系统通知
     *
     * @param noticeCode     系统通知类型编码
     * @param templateParams 系统通知模板参数 (模板基于freemarker)
     * @return
     */
    @Before(Tx.class)
    public boolean sendNotice(String noticeCode, Map<String, Object> templateParams) {
        // 查询通知模板
        SysNoticeType sysNoticeType = SysNoticeType.dao.findByCode(noticeCode);
        if (sysNoticeType == null) {
            LOG.debug("{}, 未找到消息通知类型", noticeCode);
            return false;
        }

        // 查询通知接收人
        Set<String> receivers = getNotificationReceivers(sysNoticeType.getId());
        if (receivers.size() == 0) {
            LOG.debug("{}, 未找到消息接收人", noticeCode);
            return false;
        }

        // 通知标题
        String msgTitle = sysNoticeType.getName();
        // 通知内容
        String msgContent = FreemarkerUtils.renderAsText(sysNoticeType.getTemplate().replaceAll("FTL", "\\$"), templateParams);

        // 保存通知数据
        boolean flag = saveNotificationData(sysNoticeType, msgTitle, msgContent, receivers);
        if (!flag) {
            LOG.info("{}, 系统通知 数据保存数据库失败", noticeCode);
            return false;
        }

        LOG.info("{}, 系统通知 执行成功", noticeCode);

        // 服务器消息推送
        Map<String, Object> msg = new HashMap<>();
        msg.put("cate", sysNoticeType.getCate());
        msg.put("code", sysNoticeType.getCode());
        msg.put("logo", sysNoticeType.getLogo());
        msg.put("title", msgTitle);
        msg.put("content", msgContent);

        // websocket 消息推送
        SendMsgUtils.sendToUsers(receivers, JSON.toJSONString(msg));
        return flag;
    }


    /**
     * 获得 系统通知  关联的所有用户id
     *
     * @param noticeTypeId 系统通知类型id
     * @return
     */
    private Set<String> getNotificationReceivers(String noticeTypeId) {
        Set<String> userIdSet = new HashSet<>();
        // 通知类型 关联的角色，角色关联的用户
        List<Record> userIds = SysNoticeTypeSysRole.dao.findUserIdsByNoticeType(noticeTypeId);
        userIds.forEach(record -> userIdSet.add(record.get("SYS_USER_ID")));
        // 通知类型关联的用户
        userIds = SysNoticeTypeSysUser.dao.findUserIdByNoticeType(noticeTypeId);
        userIds.forEach(record -> userIdSet.add(record.get("SYS_USER_ID")));

        System.err.println(userIdSet.size());
        return userIdSet;
    }


    /**
     * 保存 系统通知 数据
     *
     * @param sysNoticeType 从数据库中查询到系统通知类型对象
     * @param msgTitle
     * @param msgConent     系统通知模板内容
     * @param receivers     系统通知接收人集合，不可为null，且size 大于0
     * @return
     */
    private boolean saveNotificationData(SysNoticeType sysNoticeType, String msgTitle, String msgConent, Set<String> receivers) {
        SysNotice sysNotice = new SysNotice();
        sysNotice.setTypeCode(sysNoticeType.getCode())
                .setTitle(msgTitle)
                .setContent(msgConent)
                .setCreateTime(new Date())
                .setExpiryTime(new DateTime().plusDays(sysNoticeType.getUntilExpiryDay()).toDate())
                .setDeadTime(new DateTime().plusDays(sysNoticeType.getUntilDeadDay()).toDate())
                .setId(IdUtils.id());
        boolean flag = sysNotice.save();
        if (!flag) {
            return false;
        }
        for (String receiverId : receivers) {
            //  不能触发事务
            ExecutorServiceUtils.pool.submit(() -> {
                SysNoticeDetail detail = new SysNoticeDetail();
                detail.setId(IdUtils.id());
                detail.setSysNoticeId(sysNotice.getId());
                detail.setReceiver(receiverId);
                detail.setHasRead("N");  // 未阅读
                detail.save();
            });
        }
        return true;
    }
}
