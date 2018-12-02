/**
 *  弹窗组件封装,依赖jquery 和 layer
 */

layer.config({
    shift: -1, /*0-6的动画形式，-1不开启，开启动画可能导致 dialog 异常情况*/
    shade: 0.1 /*遮罩透明度*/
});

var layerSkin = {
    default:'',
    lan: 'layui-layer-lan',
    molv: 'layui-layer-molv'
}

var popup = {
    /* 打开 iframe 弹窗*/
    openIframe: function (title, url, width, height, skin) {
        var index = layer.open({
            skin: skin || layerSkin.lan,
            type: 2,
            title: title,
            maxmin: true,
            shadeClose: true,
            area: [width || '80%', height || '90%'],
            offset: top.location==self.location || width=="100%"  ? 'auto':'30px',
            content: [url]
        });
        return index;
    },
    /*页面层*/
    openDOM: function (title, content, width, height, skin) {
        layer.open({
            type: 1,
            skin: skin || layerSkin.lan,
            shadeClose: true,
            area: [width || '80%', height || '90%'],
            offset: top.location==self.location || width=="100%" ? 'auto':'30px',
            title: title,
            closeBtn: 1, /*显示关闭按钮*/
            content: content
        });
    },
    openConfirm:function(skin,icon, title, msg, yesFun, noFun){
        layer.confirm(msg, {
            skin: skin || layerSkin.lan,
            icon: icon,
            offset:top.location==self.location ? 'auto':['30px','200px'],
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
            time: 1500
        };
        if(msg.indexOf('成功')>=0){
            setting.icon=1;
        }else if(msg.indexOf('失败')>=0){
            setting.icon=2;
        }
        layer.msg(msg, setting, cbk);
    },
    /*错误提示*/
    errMsg: function (title, msg) {
        title = title || '悄悄告诉你';
        msg = msg || '系统发生严重错误了哥们！赶紧联系管理员。';
        var mlayer;
        if (top.location != self.location) {
            mlayer = parent.layer;
        } else {
            mlayer = layer;
        }
        mlayer.alert(msg, {
            icon: 5,
            title: [title, 'background:#e74b3b; color:white; line-height:30px;height:30px;padding: 0 10px'],
            btn: [],
            shadeClose: true,
            resize: true,
        });
    }
}