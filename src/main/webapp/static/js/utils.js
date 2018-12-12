/*通用小工具*/


function isEmpty(val){
     if(val==null || val == undefined || $.trim(val)==''){
         return true;
     }
     return false;
}

function notEmpty(val){
    return !isEmpty(val);
}


/**
 * 查看用户信息 （弹窗卡片)
 * @param username
 */
function userInfo(ctx,username){
    if(isEmpty(popup)){
        popup = parent.popup;
        if(isEmpty(popup)){
            console.log("找不到 popup 对象，请先引入相关工具库。");
            return;
        }
    }
    if(isEmpty(username)){
        console.log("username 参数不可为空。");
        return;
    }
    popup.openIframeNoResize(username+" 用户信息", ctx+"/utils/userInfo?username="+username, "300px","430px",true);
}

/**
 * 查看机构信息 （弹窗卡片)
 * @param ctx
 * @param orgId
 */
function orgInfo(ctx,orgId){
    if(isEmpty(popup)){
        popup = parent.popup;
        if(isEmpty(popup)){
            console.log("找不到 popup 对象，请先引入相关工具库。");
            return;
        }
    }
    if(isEmpty(orgId)){
        console.log("orgId 参数不可为空。");
        return;
    }
    popup.openIframeNoResize("机构信息", ctx+"/utils/orgInfo?id="+orgId, "300px","365px",true);
}