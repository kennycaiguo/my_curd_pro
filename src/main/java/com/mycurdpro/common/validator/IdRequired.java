package com.mycurdpro.common.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.validate.Validator;
import com.mycurdpro.common.config.Constant;

/**
 * ids 参数不可为空, 使用 json 返回错误信息
 */
public class IdRequired extends Validator {
    @Override
    protected void validate(Controller c) {
        validateRequired("ids","ids", "ids"+Constant.PARAM_IS_EMPTY);
    }

    @Override
    protected void handleError(Controller c) {
        Ret ret = Ret.create().setFail().set("msg","ids"+Constant.PARAM_IS_EMPTY);
        c.renderJson(ret);
        return;
    }
}
