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



/**
 * 在页面中创建一个隐藏的 post form 并提交表单
 * 用例： 通过 post 方式打开新页面，参数中文等
 * @param url     表单提交地址
 * @param target  表单提交方式 _self 、_blank
 * @param params  post 参数, { name: "param1", value: "param1"} 数组
 */
function postForm(url,target, params) {
    var temp_form = document.createElement("form");
    temp_form.action = url;
    temp_form.target = target;
    temp_form.method = "post";
    temp_form.style.display = "none";

    for (var item in params) {
        var opt = document.createElement("textarea");
        opt.name = params[item].name;
        opt.value = params[item].value;
        temp_form.appendChild(opt);
    }

    document.body.appendChild(temp_form);
    temp_form.submit();
    document.body.removeChild(temp_form);
}


/*jQuery 表单序列化 成对象*/
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function isEmptyObject(e) {
    var t;
    for (t in e)
        return !1;
    return !0
}

$.fn.serializeJsonAry = function(){
    var serializeObject = this.serializeObject();
    var vCount = 0;
    for(var item in serializeObject){
        var tmp = $.isArray(serializeObject[item]) ? serializeObject[item].length : 1;
        vCount = (tmp > vCount) ? tmp : vCount;
    }
    var jsonAry = new Array();
    if(!isEmptyObject(serializeObject)){
        if(vCount > 1) {
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in serializeObject) {
                    jsonObj[item] = serializeObject[item][i];
                }
                jsonAry.push(jsonObj);
            }
        }else{
            jsonAry.push(serializeObject)
        }
    }
    return jsonAry;
};