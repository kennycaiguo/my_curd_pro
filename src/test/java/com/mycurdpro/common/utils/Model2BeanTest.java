package com.mycurdpro.common.utils;


import base.DbTestBase;
import com.mycurdpro.system.model.SysDict;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * model 转 bean 工具测试
 */
public class Model2BeanTest {

    Model2Bean<SysDict,DictBean> m2b = new Model2Bean<>();

    @Test
    public void modelsToBeans() {
        DbTestBase.dbInit();

        String sql  = "select a.*,b.GROUP_NAME from SYS_DICT a left join SYS_DICT_GROUP b on a.GROUP_CODE = b.GROUP_CODE";
        List<SysDict> sysDicts = SysDict.dao.find(sql);
        for(SysDict sysDict : sysDicts){
            System.out.println(sysDict.get("group_name").toString());
        }

        Map<String,String> mapping = new HashMap<String,String>(){{
            put("GROUP_CODE","groupCode");
            put("ID","id");
            put("DICT_LABEL","dictLabel");
            put("DICT_VALUE","dictValue");
            put("CREATE_TIME","createTime");
            put("DICT_SORT","dictSort");
        }};

        List<DictBean> dictBeans = m2b.toBeans(sysDicts,DictBean.class,true);
        for(DictBean bean : dictBeans){
            System.out.println(bean);
        }
    }
}