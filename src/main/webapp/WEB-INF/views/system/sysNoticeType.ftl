<#include "../common/common.ftl"/> <@layout>
<table id="dg" class="easyui-datagrid"
       url="${ctx!}/sysNoticeType/query"
       data-options="onDblClickRow:editModel"
       toolbar="#tb" rownumbers="true" border="false"
       fit="true"    fitColumns="true"
       nowrap="false"
       striped="true"
       pagination="true"
       ctrlSelect="true"
       pageSize="40" pageList="[20,40]">
    <thead>
    <tr>
        <th data-options="field:'ID',checkbox:true"></th>
        <th field="LOGO" align="center" width="60" formatter="logoFmt">图标</th>
        <th field="CATE" width="100">分类</th>
        <th field="NAME" width="100">名称</th>
        <th field="CODE" width="100">编码</th>
        <th field="UNTIL_EXPIRY_DAY" width="100">n天后 过期</th>
        <th field="UNTIL_DEAD_DAY" width="100">n天后 删除</th>
        <#--<th field="TEMPLATE" width="400">消息模板(ftl)</th>-->
        <th field="REMARK" width="400">备注</th>
        <th field="CREATER" width="100" formatter="usernameFmt">创建人</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a onclick="newModel('${ctx!}/sysNoticeType/newModel',  '750px', '500px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/sysNoticeType/newModel',  '750px', '500px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/sysNoticeType/deleteAction')" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-delete" plain="true">删除</a>
    <span  id="searchSpan" class="searchInputArea" >
         <input name="search_LIKE_CATE"   prompt="分类" class="easyui-textbox" style="width:120px; ">
         <input name="search_LIKE_NAME"   prompt="名称" class="easyui-textbox" style="width:120px; ">
         <input name="search_LIKE_CODE"   prompt="编码" class="easyui-textbox" style="width:120px; ">
         <input name="search_LIKE_REMARK" prompt="备注" class="easyui-textbox" style="width:120px; ">
         <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    function usernameFmt(val,row) {
        return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }
    function logoFmt(val) {
        if(isEmpty(val)){
            return '';
        }
        return '<a target="_blank" href="${ctx!}/'+ val + '"><img height="25px" src="${ctx!}/' + val + '" alt="logo"/></a>'
    }
</script>
</@layout>
