package com.mycurdpro.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.mycurdpro.common.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 查询过滤器，将前端传递  固定格式字符串参数 解析为查询条件
 */
public class SearchSql implements Interceptor {

    private final static Logger LOG = LoggerFactory.getLogger(SearchSql.class);
    private SearchFilter n;

    public void intercept(Invocation ai) {
        Controller c = ai.getController();
        // 查询字段前缀
        String prefix = "search_";

        Map<String, Object> searchParams = getParametersStartingWith(c.getRequest(), prefix);
        Map<String, SearchFilter> filters = parse(searchParams);
        String whereSql = buildFilter(filters.values());
        c.setAttr(Constant.SEARCH_SQL, whereSql);
        // easyui grid 分页
        int pageNumber = c.getParaToInt("page", 1);
        int pageSize = c.getParaToInt("rows", 10);

//        //分页参数  bootstrap 分页 和 easyui grid 分页 兼容写法
//        int pageNumber;
//        int pageSize;
//        if (StrKit.notBlank(c.getPara("offset"))) {
//            // bootstraptable 分页
//            pageNumber = c.getParaToInt("offset", 0);
//            pageSize = c.getParaToInt("limit", 10);
//            if (pageNumber != 0) {// 获取页数
//                pageNumber = pageNumber / pageSize;
//            }
//            pageNumber += 1;
//        } else {
//            // easyui grid 分页
//            pageNumber = c.getParaToInt("page", 1);
//            pageSize = c.getParaToInt("rows", 10);
//        }

        c.setAttr("pageNumber", pageNumber);
        c.setAttr("pageSize", pageSize);
        ai.invoke();
    }

    /**
     * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
     * 返回的结果的Parameter名已去除前缀.
     */
    private Map<String, Object> getParametersStartingWith(
            HttpServletRequest request, String prefix) {
        Enumeration<String> paramNames = request.getParameterNames();
        Map<String, Object> params = new TreeMap<>();
        if (prefix == null) {
            prefix = "";
        }
        while (paramNames != null && paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if ("".equals(prefix) || paramName.startsWith(prefix)) {
                String unprefixed = paramName.substring(prefix.length());
                String[] values = request.getParameterValues(paramName);
                if (values == null || values.length == 0) {
                } else if (values.length > 1) {
                    params.put(unprefixed, values);
                } else {
                    params.put(unprefixed, values[0]);
                }
            }
        }
        return params;
    }

    /**
     * searchParams中key的格式为OPERATOR_FIELDNAME
     */
    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = new HashMap<>();
        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            // 过滤掉空值
            String key = entry.getKey();
            Object value = entry.getValue();
            if (StrKit.isBlank((String) value)) {
                continue;
            }
            // 拆分operator与field
            String[] names = key.split("_");
            if (names.length < 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            // field 中可能有查询条件
            String filedName;
            StringBuilder filedNameTemp = new StringBuilder();
            for (int i = 1; i < names.length; i++) {
                filedNameTemp.append(names[i]).append("_");
            }
            if (filedNameTemp.substring(filedNameTemp.length() - 1).equals("_")) {
                filedNameTemp = new StringBuilder(filedNameTemp.substring(0, filedNameTemp.length() - 1));
            }
            filedName = filedNameTemp.toString();
            // 查询条件
            SearchFilter.Operator operator = SearchFilter.Operator.valueOf(names[0]);
            // 创建searchFilter
            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }
        return filters;
    }

    /**
     * 按属性条件列表创建查询字句
     */
    private String buildFilter(final Collection<SearchFilter> filters) {
        StringBuilder sb = new StringBuilder();
        if (null != filters && filters.size() > 0) {
            for (SearchFilter filter : filters) {
                if (sb.length() > 0) {
                    sb.append(" and ");
                }
                sb.append(filter.fieldName);
                // 此处 可能要根据数据库类型 修改
                switch (filter.operator) {
                    case EQS:
                        // 字符串 相等
                        sb.append(" ='").append(filter.fieldValue).append("'");
                        break;
                    case EQN:
                        // 数字型相等
                        sb.append(" =").append(filter.fieldValue);
                        break;
                    case LIKE:
                        // 字符串模糊匹配
                        sb.append(" like ").append("'%").append(filter.fieldValue).append("%'");
                        break;
                    case GTN:
                        // 数字型大于
                        sb.append(" >").append(filter.fieldValue);
                        break;
                    case LTN:
                        // 数字型小于
                        sb.append(" <").append(filter.fieldValue);
                        break;
                    case GTEN:
                        // 数字型 大于等于
                        sb.append(" >=").append(filter.fieldValue);
                        break;
                    case LTEN:
                        // 数字型 小于等于
                        sb.append(" <=").append(filter.fieldValue);
                        break;
                    case GTD:
                        // 日期 大于
                        sb.append(" > to_date('").append(filter.fieldValue).append("','yyyy-MM-dd')");
                        break;
                    case LTD:
                        // 日期 小于
                        sb.append(" < to_date('").append(filter.fieldValue).append("','yyyy-MM-dd')");
                        break;
                    case GTED:
                        // 日期 大于等于
                        sb.append(" >= to_date('").append(filter.fieldValue).append("','yyyy-MM-dd')");
                        break;
                    case LTED:
                        // 日期 小于等于
                        sb.append(" <= to_date('").append(filter.fieldValue).append("','yyyy-MM-dd')");
                        break;
                    case NEQ:
                        sb.append(" !='").append(filter.fieldValue).append("'");
                        break;
                    case INS:
                        // in 字符串
                        sb.append(" in ('").append(filter.fieldValue.toString().replaceAll(",","','")).append("')");
                        break;
                    case INN:
                        // in 数字
                        sb.append(" in (").append(filter.fieldValue).append(")");
                        break;
                    case GTDT:
                        // 日期时间 大于
                        sb.append(" > to_date('").append(filter.fieldValue).append("','yyyy-mm-dd hh24:mi:ss')");
                        break;
                    case LTDT:
                        // 日期时间 小于
                        sb.append(" < to_date('").append(filter.fieldValue).append("','yyyy-mm-dd hh24:mi:ss')");
                        break;
                    case GTEDT:
                        // 日期时间 大于等于
                        sb.append(" >= to_date('").append(filter.fieldValue).append("','yyyy-mm-dd hh24:mi:ss')");
                        break;
                    case LTEDT:
                        // 日期时间 小于等于
                        sb.append(" <= to_date('").append(filter.fieldValue).append("','yyyy-mm-dd hh24:mi:ss')");
                        break;
                    case IS:
                        // is null
                        sb.append("  is null ");
                        break;
                    case ISNOT:
                        // not is null
                        sb.append("  is not null ");
                        break;
                    default:
                        LOG.warn("找不到预定义设置,{},请自己扩展。",filter.operator);
                }
            }
        }
        LOG.debug("build filters: {}", sb.toString());
        return sb.toString();
    }
}

class SearchFilter {
    // 查询字段名
    public final  String fieldName;
    // 查询字段值
    public final Object fieldValue;
    // 查询条件
    public final Operator operator;

    public SearchFilter(String fieldName, Operator operator, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.operator = operator;
    }

    public enum Operator {
        EQS,EQN, LIKE, GTN, LTN, GTEN, LTEN,GTD,LTD, GTED, LTED, NEQ, INS,INN,GTDT,LTDT,GTEDT,LTEDT,IS,ISNOT
    }
}