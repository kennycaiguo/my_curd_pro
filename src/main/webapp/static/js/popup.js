/**
 *  弹窗组件封装,依赖jquery 和 layer
 */

top.layer.config({
    shift: 0, /*0-6的动画形式，-1不开启，开启动画可能导致 dialog 异常情况*/
    shade: 0.1 /*遮罩透明度*/
});

var layerSkin = {
    default:'',
    lan: 'layui-layer-lan',
    molv: 'layui-layer-molv',
    green:'layui-layer-green'
};
layerSkin.default = layerSkin.lan;

var popup = {
    /* 打开 iframe 弹窗*/
    openIframe: function (title, url, width, height, skin) {
        var index = top.layer.open({
            skin: skin || layerSkin.default,
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true,
            area: [width || '80%', height || '90%'],
            content: [url],
            success:function (dom,i) {
                // 也可通过 iframe 传值
               sessionStorage.setItem("iframeId",window.name);
            }
        });
        return index;
    },
    /*iframe 弹窗，不能调整大小*/
    openIframeNoResize:function(title,url,width,height,autoHeight,skin){
        var index = top.layer.open({
            skin: skin || layerSkin.default,
            type: 2,
            title: title,
            maxmin: false,
            resize:false,
            shadeClose: true,
            area: [width || '80%', height || '90%'],
            content: [url],
            success:function(dom,i){
                // 也可通过 iframe 传值
                sessionStorage.setItem("iframeId",window.name);
                // 根据内容高度 自适应
                if(autoHeight){
                    top.layer.iframeAuto(i);
                }
            }
        });
        return index;
    },
    /*页面层*/
    openDOM: function (title, content, width, height, skin) {
        top.layer.open({
            type: 1,
            skin: skin || layerSkin.default,
            shadeClose: true,
            area: [width || '80%', height || '90%'],
            title: title,
            closeBtn: 1, /*显示关闭按钮*/
            content: content
        });
    },
    openConfirm:function(skin,icon, title, msg, yesFun, noFun){
        top.layer.confirm(msg, {
            skin: skin || layerSkin.default,
            icon: icon,
            title: title,
            resize:false,
            btn: ['确定', '取消']
        }, function () {
            if (typeof yesFun=="function") {
                yesFun();
            }
        }, function (index) {
            if (typeof noFun=="function") {
                noFun();
            }
            layer.close(index);
        });
    },
    /*普通提示*/
    msg: function (msg,cbk) {
        var that = this;
        var setting = {
            time: 1500,
            // offset:'t'
        };
        if(msg.indexOf('成功')>=0){
            setting.icon=1;
        }else if(msg.indexOf('失败')>=0){
            setting.icon=2;
        }else{
            setting.icon=0; // 感叹号，代表提示框
        }
        top.layer.msg(msg, setting, cbk);
    },
    /*错误提示*/
    errMsg: function (title, msg) {
        title = title || '悄悄告诉你';
        msg = msg || '系统发生严重错误了哥们！赶紧联系管理员。';
        top.layer.alert(msg, {
            icon: 5,
            title: [title, 'background:#e74b3b; color:white; line-height:30px;height:30px;padding: 0 10px'],
            btn: [],
            shadeClose: true,
            resize: true,
        });
    },
    /* 关闭弹窗 */
    close:function (windowName) {
        top.layer.close(top.layer.getFrameIndex(windowName));
    },
    /*关闭弹窗，父窗口关闭子窗口*/
    closeByIndex:function(windowIndex){
        top.layer.close(windowIndex);
    }
}