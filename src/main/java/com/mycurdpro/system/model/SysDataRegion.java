package com.mycurdpro.system.model;

import com.mycurdpro.system.model.base.BaseSysDataRegion;

import java.util.List;

/**
 * Generated model
 * DB: SYS_DATA_REGION  地区数据
 *
 * @author zhangchuang
 */
@SuppressWarnings("serial")
public class SysDataRegion extends BaseSysDataRegion<SysDataRegion> {
    public static final SysDataRegion dao = new SysDataRegion().dao();

    /**
     * 根据pid 查询
     *
     * @param pid
     * @return
     */
    public List<SysDataRegion> findByPid(String pid) {
        String sql = " select * from sys_data_region where parent_id = ?  ";
        return find(sql, pid);
    }

}
