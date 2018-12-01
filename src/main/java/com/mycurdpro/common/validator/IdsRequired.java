package com.mycurdpro.common.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.validate.Validator;
import com.mycurdpro.common.config.Constant;

/**
 * ids 参数不可为空, 使用 json 返回错误信息
 */
public class IdsRequired extends Validator {
    @Override
    protected void validate(Controller c) {
        validateRequired("ids","ids", "ids 参数为空");
    }

    @Override
    protected void handleError(Controller c) {
        Ret ret = Ret.create().setFail().set("msg","ids 参数为空");
        c.renderJson(ret);
        return;
    }
}
