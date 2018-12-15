<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
<table id="dg" class="easyui-datagrid"
     url="${ctx!}/sysRole/query"
     toolbar="#tb" rownumbers="true" border="false"
     fitColumns="false"
     fit="true" pagination="true"
     ctrlSelect="true"
     striped="true"
     pageSize="40" pageList="[20,40]">
  <thead>
  <tr>
      <th data-options="field:'ID',checkbox:true"></th>
      <th field="NAME" width="150">名称</th>
      <th field="CODE" width="150">编码</th>
      <th field="DESCRIPTION" width="300">说明</th>
      <th field="SORT" width="50">排序号</th>
  </tr>
  </thead>
</table>
<div id="tb">
    <a onclick="newModel('${ctx!}/sysRole/newModel', '500px', '400px')" href="#" class="easyui-linkbutton"
       iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/sysRole/newModel', '500px', '400px')" href="#"
       class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/sysRole/deleteAction')" href="#" class="easyui-linkbutton  "
       iconCls="iconfont icon-delete" plain="true">删除</a>
    <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
            <input name="search_LIKE_CODE" prompt="编码" class="easyui-textbox" style="width:120px; ">
            <a href="#" class="easyui-linkbutton searchBtn"
               data-options="iconCls:'iconfont icon-search',plain:true"
               onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>