<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
<table id="dg" class="easyui-datagrid"
    url="${ctx!}/sysUser/query"
    toolbar="#tb" rownumbers="true" border="false"
    fitColumns="true"
    fit="true" pagination="true"
    ctrlSelect="true"
    striped="true"
    pageSize="40" pageList="[20,40]">
 <thead>
 <tr>
     <th data-options="field:'ID',checkbox:true"></th>
     <th field="USERNAME" width="150" formatter="usernameFmt">用户名</th>
     <th field="ORG_NAME" width="200" formatter="orgNameFmt">部门</th>
 <#--<th field="EMAIL" width="100">邮箱</th>-->
 <#--<th field="PHONE" width="100">电话</th>-->
     <th field="JOB" width="150">职位</th>
     <th field="JOB_LEVEL_TEXT" width="150">职位级别</th>
     <th field="NAME" width="100">姓名</th>
 <#--<th field="AVATAR" width="100">头像</th>-->
     <th field="GENDER_TEXT" width="50">性别</th>
     <th field="USER_STATE_TEXT" width="100">是否禁用</th>
     <th field="LAST_LOGIN_TIME" width="200">上次登录时间</th>
 </tr>
 </thead>
</table>
<div id="tb">
    <a onclick="newModel('${ctx!}/sysUser/newModel', '800px', '500px')" href="#" class="easyui-linkbutton"  iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/sysUser/newModel', '800px', '500px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/sysUser/deleteAction')" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-delete" plain="true">删除</a>
    <a onclick="resetPwd()" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-resetPwd" plain="true">重置密码</a>
    <a onclick="newUserRole()" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-config" plain="true">配置角色</a>
    <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_a.USERNAME" prompt="用户名" class="easyui-textbox" style="width:120px; ">
            <input id="org" name="search_LIKE_a.ORG_ID" prompt="部门"   >
            <input name="search_LIKE_a.JOB" prompt="职位" class="easyui-textbox" style="width:120px; ">
            <input name="search_EQS_a.JOB_LEVEL" prompt="职位级别"   style="width:120px; "
                   class="easyui-combobox" data-options="valueField:'VALUE',textField:'LABEL',panelHeight:'auto',url:'${ctx!}/sysDict/combobox?groupCode=jobLevel'" >
            <input name="search_LIKE_a.NAME" prompt="姓名" class="easyui-textbox" style="width:120px; ">
            <input name="search_EQS_a.GENDER" prompt="性别"   style="width:120px; "
                   class="easyui-combobox" data-options="valueField:'VALUE',textField:'LABEL',panelHeight:'auto',url:'${ctx!}/sysDict/combobox?groupCode=gender'" >
            <input name="search_EQS_a.USER_STATE" prompt="是否禁用"  style="width:120px;"
                   class="easyui-combobox" data-options="valueField:'VALUE',textField:'LABEL',panelHeight:'auto',url:'${ctx!}/sysDict/combobox?groupCode=userState'" >
            <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"  onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/js/input2combotree.js"></script>
<script>
    /*机构查询条件*/
    initFormCombotree('#org','','','${ctx!}/sysOrg/orgComboTree?withRoot=false',false);

    function resetPwd(){
        var rows = $("#dg").datagrid("getSelections");
        if (rows.length!=0) {
            popup.openConfirm(null,3, '重置密码', '您确定要重置选中的'+rows.length+'位用户的密码吗?', function () {
                var ids = [];
                rows.forEach(function(row){
                    ids.push(row.ID);
                });
                $.post('${ctx!}/sysUser/resetPwd?ids=' + ids.join(','), function (data) {
                    popup.msg(data.msg);
                }, "json").error(function(){ popup.errMsg(); });
            });
        } else {
            popup.msg('请至少选择一条数据');
        }
    }

    function usernameFmt(val,row) {
        return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }
    function orgNameFmt(val,row) {
        return '<a href="javascript:orgInfo(\'${ctx!}\',\''+row.ORG_ID+'\')" title="点击查看机构信息" >'+val+'</a>';
    }


    /**
     * 用户配置角色
     */
    function newUserRole(){
        var rows= $("#dg").datagrid("getSelections");
        if (rows.length==1) {
            popup.openIframe('配置角色','${ctx!}/sysUser/newUserRole?id=' + rows[0].ID, '800px', '500px');
        } else {
            popup.msg('请选择一行数据配置角色');
        }
    }
</script>
</@layout>