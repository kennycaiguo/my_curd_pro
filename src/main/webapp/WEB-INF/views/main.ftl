<#include "/WEB-INF/views/common/common.ftl"/>
<@layout>
s<#include "common/popup.ftl"/>
<link rel="stylesheet" href="${ctx!}/res/css/main-page.css">
<script>
    if(top.location!=self.location){
        top.location = "${ctx!}/login";
    }
</script>
</head>
<body>
<div id="mainLayout" class="easyui-layout" fit="true" border="false">
    <div class="head" data-options="region:'north'" border="false">
        <span class="head-text" onclick="fullScreenToggleNew(this)" title="点击全屏" >my_curd</span>
        <span class="head-menu">
             你好, (${(session.auth_user_roles)!} )
            <a id="opeMenu" class="pure-button pure-button-primary " >
                ${(session.auth_user.name)!}
            </a>
            <div id="opeMenuItem" style="width:100px;">
                <div name="viewNotification"> 系统通知<span id="unreadSpan" class="unreadCount"></span></div>
                <div name="editInfo">修改个人信息</div>
                <div name="changePwd">修改密码</div>
                <div name="logout">退出</div>
            </div>
            <script>
                ;(function(){
                    function openSysNotifiction(){
                        popup.openIframe('系统通知', '${ctx!}/systemNotification', '700px', '400px');

                        /* 移除未读消息数小红点，此处并不合理 */
                        $('#unreadSpan').addClass('hid');
                    }
                    function openEditInfoForm() {
                        popup.openIframe('修改用户信息', '${ctx!}/userInfo', '560px', '600px');
                    }
                    function openChangePwdForm(){
                        popup.openIframe('修改密码', '${ctx!}/userPassword', '560px', '400px');
                    }
                    function logout(){
                        var logoutUrl = '${ctx!}/logout';
                        window.location.href=logoutUrl;
                    }

                    var opeMenu = $('#opeMenu').menubutton({ menu: '#opeMenuItem' });
                    $(opeMenu.menubutton('options').menu).menu({
                        onClick: function (item) {
                            switch (item.name){
                                case 'viewNotification':  openSysNotifiction() ;break;
                                case 'editInfo':  openEditInfoForm() ;break;
                                case 'changePwd': openChangePwdForm() ;break;
                                case 'logout': logout() ;break;
                            }
                        }
                    })
                })();
            </script>
        </span>
    </div>
    <div data-options="region:'west',split:false" title="&nbsp;&nbsp;功能导航" headerCls="borderTopNone grayBg" bodyCls="grayBg" style=" width:200px;">
        <div class="input-control">
            <input type="text" id="filterInput" placeholder="输入关键字、Enter过滤">
        </div>
        <ul id="permissionTree" style="margin-left:9px">
        </ul>
    </div>
    <div data-options="region:'center'" border="false" >
        <!-- pill="true" narrow="true" plain="true" tab 可选样式-->
        <div id="tabGroup"   pill="false"  narrow="false" plain="true" ></div>
        <div id="tabsMenu" class="easyui-menu">
            <div data-options="name:0">刷新</div>
            <div class="menu-sep"></div>
            <div data-options="name:1">关闭</div>
            <div data-options="name:2">关闭其它</div>
            <div data-options="name:3">关闭所有</div>
            <div class="menu-sep"></div>
            <div data-options="name:4">打开新页面</div>
        </div>
    </div>
</div>
<script src="${ctx!}/res/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/res/js/tab-tools.js"></script>
<script src="${ctx!}/res/js/d-toast.min.js"></script>
<script>
    ;(function () {
        var MainPage = {
            init: function () {
                var that = this;
                /* 权限树 */
                that.permissTreeInit();
                /* 消息通知 */
                that.notificationInit();
                /* tab 右键菜单 */
                TabTools.contextMenuInit();
                /* 页面中事件绑定 */
                that.bind();
            },
            permissTreeInit: function () {
                /* 初始化左侧 菜单 并绑定事件*/
                var easyTree = new EasyTree();
                $.ajax({
                    url: "${ctx!}/main/permissionTree",
                    type: "GET",
                    dataType: "json",
                    success: function (data) {
                        var treeData = easyTree.treeDataBuild(data, "id", "pid", "text,iconCls,url");
                        $('#permissionTree').tree({
                            data: treeData,
                            onSelect: function (node) {
                                /* 只能打开 叶子node*/
                                if ($("#tree").tree("isLeaf", node.target)) {
                                    TabTools.addOrRefresh("${ctx!}"+node.url, "", node.text, node.iconCls);
                                }
                            }
                        });
                    },
                    error: function (x, m) {
                        console.log("权限数据请求错误: " + m);
                    }
                });
            },
            notificationInit:function(){
                var that  = this;
                that.refreshUnreadCount();

                /* 初始化 websocket */
                if ('WebSocket' in window) {
                    console.log('当前浏览器支持 websocket，初始化websocket 连接');
                    var ws = new WebSocket("ws://localhost/ws-server?userId=${(aesUserId)!}");
                    ws.onerror = function () {
                        console.log("WebSocket连接发生错误");
                    };
                    ws.onopen = function () {
                        console.log("WebSocket连接成功");
                    };
                    ws.onmessage = function (event) {
                        console.log(event.data);
                        that.refreshUnreadCount();
                        if(event.data){
                            var msg = JSON.parse(event.data);
                            /* 消息详情显示弹窗, 通知中带有 category 和 code， 为了扩展方便 */
                            if(msg.category=='SYSTEM'){
                                /* 右侧通知弹窗， 兼容性不好 且 和整体风格不搭配，随后会替换*/
                                ;new dToast({
                                    title: msg.title,
                                    body:msg.content,
                                    icon:'${ctx!}/'+msg.logo,
                                    inner:true,   /* 不用底层通知，默认为false */
                                    timeout:10000 /* 10 秒 消失 */
                                });
                            }
                        }
                    };
                    ws.onclose = function () {
                        console.log("WebSocket连接关闭");
                    };
                    function closeWebSocket() {
                        ws.close();
                    };
                    window.onbeforeunload = function () {
                        /* 浏览器关闭 页面刷新跳转 主动断开ws连接 */
                        closeWebSocket();
                    };
                } else {
                    console.log('当前浏览器 Not support websocket')
                }
            },
            refreshUnreadCount:function(){
                /* 获得 未读消息数量 */
                $.get('${ctx!}/systemNotification/getUnreadCount', function (data) {
                    if(data.unreadCount==0){
                        $('#unreadSpan').addClass('hid');
                    }else{
                        $('#unreadSpan').removeClass('hid');
                        $('#unreadSpan').html(data.unreadCount);
                        /*popup.msg("未读消息 "+data.unreadCount+" 条");*/
                    }
                }, "json").error(function(){ popup.errMsg(); });
            },
            bind: function () {
                /* 权限树过滤（根据输入框内容过滤）*/
                $("#filterInput").on("keydown", function () {
                    if (event.keyCode == "13") {
                        var inputVal = $(this).val();
                        var aryTemp =[];
                        $('#permissionTree').tree({
                            filter: function (q, node) {
                                var flag =  node.text.indexOf(inputVal) >= 0;
                                if(flag){
                                    /* text 直接符合条件*/
                                    var nodex =  $('#permissionTree').tree('find',node.id);
                                    aryTemp =   $('#permissionTree').tree("getChildren",nodex.target);
                                    return true;
                                }else{
                                    /* 符合条件节点的子孙节点 */
                                  for(var i=0;i<aryTemp.length;i++){
                                      if(aryTemp[i].id == node.id){
                                          return true;
                                      }
                                  }
                                  return false;
                                }
                                /*return  node.text.indexOf(inputVal) >= 0 ;*/
                            }
                        });
                        if ($.trim(inputVal) == "" || inputVal == null || inputVal == undefined ) {
                            $('#tt').tree('doFilter', '');
                        } else {
                            $('#permissionTree').tree('doFilter', inputVal);
                        }
                    }
                })
            }
        };
        MainPage.init();
    })();
</script>
</@layout>