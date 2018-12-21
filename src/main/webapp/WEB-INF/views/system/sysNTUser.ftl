<#include "../common/common.ftl"/> <@layout>
<style>
    #tb2 .iconfont {
        color: #229FF2;
        font-size: 16px;
        font-weight: 500;
    }
</style>
<script>
    $(function () {
        /* 右侧 enter 搜索监听 */
        $("#enterSpan2").on("keydown", function (e) {
            if (e.keyCode == 13) {
                queryModel2();
            }
        });
    });

    /*左侧 datagrid 选中事件*/
    function dgOnSelect(index,row){
        console.log(row.id);
        $("#notificationTypeId").val(row.id);
        queryModel2();
    }
</script>
<script type="text/javascript">
    /*左侧 条件查询*/
    function queryModel() {
        var queryParams = {};
        queryParams['search_LIKE_category'] = $("#category").textbox("getValue");
        queryParams['search_LIKE_txt'] = $("#txt").textbox("getValue");
        queryParams['search_LIKE_code'] = $("#code").textbox("getValue");
        queryParams['search_LIKE_remark'] = $("#remark").textbox("getValue");
        $('#dg').datagrid('load', queryParams);
    }
    /*右侧侧 条件查询*/
    function queryModel2(){
        var queryParams = {};
        queryParams['search_LIKE_b.username'] = $("#username").textbox("getValue");
        queryParams['search_LIKE_b.name'] = $("#name").textbox("getValue");
        queryParams['search_LIKE_c.name'] = $("#orgName").textbox("getValue");
        queryParams['search_LIKE_b.job'] = $("#job").textbox("getValue");
        queryParams['search_EQ_a.notification_type_id'] = $("#notificationTypeId").val();
        $('#dg2').datagrid('load', queryParams);
    }


    var winIndex;
    /* 打开选择角色窗口*/
    function openSelectRoleModel(){
        var row = $("#dg").datagrid("getSelected");
        if(row==null){
            popup.msg('请选择一个系统通知类型');
            return;
        };
        winIndex = popup.openIframe('选择用户', '${ctx!}/utils/user?singleSelect=false&yesBtnTxt=关联用户', '800px', '500px');
    }
    /* 保存 选中的用户 和 系统通知类型,此处在 utils/role 中调用*/
    function saveAction(selectedAry){
        var row = $("#dg").datagrid("getSelected");
        if(row==null){
            popup.msg('请选择一个系统通知类型');
            return;
        };
        var ids = [];
        selectedAry.forEach(function(aryItem){
            ids.push(aryItem.id);
        });
        $.post('${ctx!}/sysNotificationTypeUser/addAction?userIds=' + ids.join(',')+"&sysNotificationTypeId="+row.id, function (data) {
            popup.msg(data, function () {
                $('#dg2').datagrid('reload');
                layer.close(winIndex);
            });
        }, "text").error(function(){ popup.errMsg(); });
    }
</script>

<div id="nestLayout" class="easyui-layout" fit="true"   >
    <div data-options="region:'west' " split="true"   style="width:600px;border-top: none;">
        <table id="dg" class="easyui-datagrid" title="系统通知类型"
               url="${ctx!}/sysNotificationType/query"
               toolbar="#tb" rownumbers="true" border="false"
               data-options="onSelect:dgOnSelect"
               fit="true"    fitColumns="true"
               striped="false"
               singleSelect="true"
               pagination="true"
               nowrap="false"
               pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th field="category" width="200">分类</th>
                <th field="txt" width="200">名称</th>
                <th field="code" width="200">编码</th>
                <th field="remark" width="400">备注</th>
            </tr>
            </thead>
        </table>
        <div id="tb" style="text-align: center">
            <span id="enterSpan" >
                <input id="category" prompt="分类" class="easyui-textbox" style="width:120px; ">
                <input id="txt" prompt="名称" class="easyui-textbox" style="width:120px; ">
                <input id="code" prompt="编码" class="easyui-textbox" style="width:120px; ">
                <input id="remark" prompt="备注" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel()">搜索</a>
            </span>
        </div>
    </div>

    <div data-options="region:'center' " split="true"   collapsed="false"  style="border-top: none;">
        <table id="dg2" class="easyui-datagrid" title="已关联用户"
               url="${ctx!}/sysNotificationTypeUser/query"
               toolbar="#tb2" rownumbers="true" border="false"
               fit="true"    fitColumns="true"
               striped="false"
               singleSelect="true"
               pagination="true"
               pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th field="category" width="100">通知分类</th>
                <th field="txt" width="100">通知类型</th>
                <th  field="username" width="100">登录名</th>
                <th  field="name" width="100">姓名</th>
                <th field="org_name" width="150">部门</th>
                <th field="job" width="100">职务</th>
                <th field="user_id" width="200" formatter="deleteFmt">操作</th>
            </tr>
            </thead>
        </table>
        <div id="tb2">
            <a onclick="openSelectRoleModel()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">关联用户</a>
            <span id="enterSpan2" class="searchInputArea">
                <input id="username" prompt="登录名" class="easyui-textbox" style="width:120px">
                <input id="name" prompt="姓名" class="easyui-textbox" style="width:120px">
                <input id="orgName" prompt="部门" class="easyui-textbox" style="width:120px">
                <input id="job" prompt="职务" class="easyui-textbox" style="width:120px">
                <input id="notificationTypeId" type="hidden" >
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel2()">搜索</a>
            </span>
        </div>
    </div>
</div>
<script>
    /* 删除 单行 */
    function deleteNotificationTypeUser(notification_type_id,userId) {
        $.get('${ctx!}/sysNotificationTypeUser/deleteAction?sysNotificationTypeId='+notification_type_id+"&userId="+userId , function (data) {
            popup.msg(data, function () {
                $('#dg2').datagrid('reload');
            });
        }, "text").error(function(){ popup.errMsg(); });
    };
    function deleteFmt(val,row){
        var notification_type_id = row.notification_type_id;
        var userId = row.user_id;
        return '<a href="javascript:deleteNotificationTypeUser(\''+notification_type_id+'\',\''+userId+'\')">删除</a>'
    };
</script>
</@layout>
