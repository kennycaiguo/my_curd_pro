/*通用小工具*/

/*浏览器全屏*/
function fullScreen() {
    var el = document.documentElement,
        rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen,
        wscript;
    if (typeof rfs != "undefined" && rfs) {
        rfs.call(el);
        return;
    }
    if (typeof window.ActiveXObject != "undefined") {
        wscript = new ActiveXObject("WScript.Shell");
        if (wscript) {
            wscript.SendKeys("{F11}");
        }
    }
}
/*浏览器退出全屏*/
function exitFullScreen() {
    var el = document,
        cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen,
        wscript;
    if (typeof cfs != "undefined" && cfs) {
        cfs.call(el);
        return;
    }

    if (typeof window.ActiveXObject != "undefined") {
        wscript = new ActiveXObject("WScript.Shell");
        if (wscript != null) {
            wscript.SendKeys("{F11}");
        }
    }
}
function fullScreenToggleNew() {
    var btn = $('ul.headerMenu li a.title').first();
    if (btn.hasClass('full')) {
        exitFullScreen();
        btn.removeClass('full').attr('title', '点击全屏');
    } else {
        fullScreen();
        btn.addClass('full').attr('title', '点击退出全屏');
    }
}

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


/**
 * 阻止右键菜单
 * @param evt
 */
function preventContextMenu(evt){
    var event=evt||window.event;
    if(event&&event.returnValue){
        event.preventDefault();
    }else{
        event.returnValue=false;
    }
}
function preventDomContextMenu(id){
    document.getElementById(id).oncontextmenu=function (evt) {
        preventContextMenu(evt);
    };
}