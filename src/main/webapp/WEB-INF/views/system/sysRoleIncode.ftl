<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
<table id="dg" class="easyui-datagrid"
     url="${ctx!}/sysRoleIncode/query"
     toolbar="#tb" rownumbers="true" border="false"
     fitColumns="false"
     fit="true" pagination="true"
     ctrlSelect="true"
     striped="false"
     pageSize="40" pageList="[20,40]">
  <thead>
  <tr>
      <th data-options="field:'ID',checkbox:true"></th>
      <th field="NAME" width="100">名称</th>
      <th field="CODE" width="100">编码</th>
      <th field="DESCRIPTION" width="200">说明</th>
      <th field="CREATER" width="100">创建人</th>
      <th field="CREATE_TIME" width="200">创建时间</th>
  </tr>
  </thead>
</table>
<div id="tb">
    <a onclick="newModel('${ctx!}/sysRoleIncode/newModel', '500px', '400px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/sysRoleIncode/newModel', '500px', '400px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/sysRoleIncode/deleteAction')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-delete" plain="true">删除</a>
    <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
            <input name="search_LIKE_CODE" prompt="编码" class="easyui-textbox" style="width:120px; ">
            <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>