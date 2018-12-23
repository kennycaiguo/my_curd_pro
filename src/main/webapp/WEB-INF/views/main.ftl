<#include "/WEB-INF/views/common/common.ftl"/>
<@layout>
<#include "common/popup.ftl"/>
<link rel="stylesheet" href="${ctx!}/static/css/main.css">
<div id="mainLayout" class="easyui-layout" fit="true" border="false">
    <div class="mainHeader" data-options="region:'north'" border="false">
        <ul class="headerMenu">
            <li><a href="javascript:fullScreenToggleNew()" title="点击全屏" style="width: 199px;" class="title">my_curd_pro</a></li>
            <li><a href="#" class="active">首页</a></li>
            <li><a href="#">云笔记</a></li>
            <span class="right">
                <li>
                    <a href="#"   title="点击查看通知">
                        <i class="iconfont icon-bell"></i> 13
                    </a>
                </li>
                <li>
                    <span id="opeMenu" class="pure-button pure-button-primary" >${(username)!}</span>
                    <div id="opeMenuItem" style="width:100px;">
                        <div name="editInfo">修改个人信息</div>
                        <div name="changePwd">修改密码</div>
                        <div name="logout">退出</div>
                    </div>
                </li>
            </span>
        </ul>
        <script>
            (function(){
                function openSysNotifiction(){
                    popup.openIframe('系统通知', '${ctx!}/systemNotification', '700px', '400px');
                    /* 移除未读消息数小红点，此处并不合理 */
                    $('#unreadSpan').addClass('hid');
                }
                function openEditInfoForm() {
                    popup.openIframeNoResize('修改用户信息', '${ctx!}/dashboard/userInfo', '360px', '560px');
                }
                function openChangePwdForm(){
                    popup.openIframeNoResize('修改密码', '${ctx!}/dashboard/userPass', '360px', '380px');
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
    </div>
    <div cls="sidebar" data-options="region:'west',split:false" title="&nbsp;&nbsp;功能导航" style=" width:200px;">
        <div class="pure-form" style="text-align: center;padding: 10px 0px;">
            <input id="filterInput"  type="text" placeholder="输入关键字、Enter过滤">
        </div>
        <ul id="permissionTree" style="margin-left:9px">
        </ul>
    </div>
    <div data-options="region:'center'" border="false" >
        <!-- pill="true" narrow="true" plain="true" tab 可选样式-->
        <div id="tabGroup"   pill="false"  narrow="false" plain="false" ></div>
        <div id="tabsMenu" class="easyui-menu">
            <div data-options="name:0">刷新</div>
            <div class="menu-sep"></div>
            <div data-options="name:1">关闭</div>
            <div data-options="name:2">关闭其它</div>
            <div data-options="name:3">关闭所有</div>
        </div>
    </div>
</div>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/js/tab-tools.js"></script>
<script src="${ctx!}/static/js/d-toast.min.js"></script>
<script>
    if(top.location!=self.location){
        top.location = "${ctx!}/login";
    }
</script>
<script>
    function menuTreeInit(){
        const easyTree = new EasyTree();
        $.getJSON('${ctx!}/dashboard/menuTree',function(data){
            var treeData = easyTree.treeDataBuild(data, "id", "pid", "text,iconCls,url");
            $('#permissionTree').tree({
                data: treeData,
                animate:true,
                onSelect: function (node) {
                    if ($("#tree").tree("isLeaf", node.target)) {
                        TabTools.addOrRefresh("${ctx!}"+node.url, "", node.text, node.iconCls);
                    }
                }
            });
        }).error(function(){ popup.errMsg(); });
    }

    function menuTreeSearchInit(){
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

    $(function(){
        menuTreeInit();
        menuTreeSearchInit();
        TabTools.contextMenuInit();
    });
</script>
</@layout>