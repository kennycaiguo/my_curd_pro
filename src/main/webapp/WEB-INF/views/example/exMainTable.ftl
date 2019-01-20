<#--  zhangchuang 2019-01-17 19:04:42 -->
<#include "../common/common.ftl"/>
<@layout>
<table id="dg" class="easyui-datagrid"
       url="${ctx!}/exMainTable/query"
       toolbar="#tb" rownumbers="true" border="false"
       fit="true"    fitColumns="false"
       striped="false"  pagination="true"
       ctrlSelect="true" pageSize="40" pageList="[20,40]">
    <thead>
    <tr>
        <th data-options="field:'ID',checkbox:true"></th>
        <th field="NAME" width="150">姓名</th>
        <th field="GENDER" width="150">性别</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a onclick="newModel('${ctx!}/exMainTable/newModel', '800px', '500px')" href="#" class="easyui-linkbutton"
       iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/exMainTable/newModel', '800px', '500px')" href="#"
       class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/exMainTable/deleteAction')" href="#" class="easyui-linkbutton  "
       iconCls="iconfont icon-delete" plain="true">删除</a>
    <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_test" prompt="测试" class="easyui-textbox" style="width:120px; ">
            <a href="#" class="easyui-linkbutton searchBtn"  data-options="iconCls:'iconfont icon-search',plain:true"
               onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>
