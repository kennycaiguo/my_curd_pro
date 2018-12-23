<!--选择用户页面-->
<#include "../common.ftl"/>
<@layout>
<script type="text/javascript">
    var iframeId = sessionStorage.getItem("iframeId");
    function saveSelected(){
        var ary = $("#dg").datagrid("getSelections");
        if(ary.length==0){
            popup.msg('请至少选择一个角色');
            return;
        }
        if(top.location==self.location){
           return;
        }
        /*通过 iframe id  获得 窗口 并调用其方法*/
       top.frames[iframeId].addUsersAction(ary);
    }
</script>
<table id="dg" class="easyui-datagrid"
       url="${ctx!}/utils/queryUser"
       fitColumns="true"
       singleSelect="${singleSelect}"
       toolbar="#tb"
       rownumbers="true"
       border="false"
       fit="true" pagination="true">
    <thead>
    <tr>
        <#if singleSelect=="false"><th data-options="field:'ID',checkbox:true"></th></#if>
        <th field="USERNAME" width="150" formatter="usernameFmt">用户名</th>
        <th field="NAME" width="100">姓名</th>
        <th field="ORG_NAME" width="200">部门</th>
        <th field="JOB" width="150">职位</th>
        <th field="USER_STATE_TEXT" width="100">是否禁用</th>
    </tr>
    </thead>
</table>
<div id="tb" >
    <a onclick="saveSelected()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-save" plain="true">${yesBtnTxt!}</a>
    <span id="searchSpan" class="searchInputArea" >
        <input name="search_LIKE_a.USERNAME" prompt="用户名" class="easyui-textbox" style="width:120px; ">
        <input name="search_LIKE_a.NAME" prompt="姓名" class="easyui-textbox" style="width:120px; ">
        <input name="search_LIKE_a.JOB" prompt="职位" class="easyui-textbox" style="width:120px; ">
        <input name="search_EQS_a.USER_STATE" prompt="是否禁用"  style="width:120px;"
               class="easyui-combobox" data-options="valueField:'VALUE',textField:'LABEL',panelHeight:'auto',url:'${ctx!}/sysDict/combobox?groupCode=userState'" >
        <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel('dg','searchSpan')">搜索
        </a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    function usernameFmt(val,row) {
        return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }
</script>
</@layout>
