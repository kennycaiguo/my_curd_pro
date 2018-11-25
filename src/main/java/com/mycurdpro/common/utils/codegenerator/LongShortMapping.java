package com.mycurdpro.common.utils.codegenerator;


import java.util.HashMap;
import java.util.Map;

/**
 * 长短名映射
 */
public class LongShortMapping {

    @SuppressWarnings("serial")
    private static final Map<String, String> map = new HashMap<String, String>(7) {{
        put("java.util.Date", "Date");
        put("java.lang.String", "String");
        put("java.math.BigDecimal", "BigDecimal");
        put("java.lang.Integer", "Integer");
        put("java.lang.Double", "Double");
        put("java.lang.Float", "Float");
        put("java.lang.Long", "Long");
    }};

    public static String getShortName(String longName) {
        return map.get(longName);
    }
}
