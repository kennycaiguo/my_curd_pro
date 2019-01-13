<#--  zhangchuang 2019-01-13 21:26:12 -->
<#include "../common/common.ftl"/>
<@layout>
<style>
    /*datagrid 行号大时调整*/
    .datagrid-header-rownumber{
        width:50px !important;
    }
    .datagrid-cell-rownumber{
        width:50px !important;
    }
</style>
<table id="dg" class="easyui-datagrid"
       url="${ctx!}/exSingleTable/query"
       toolbar="#tb" rownumbers="true" border="false"
       fit="true"    fitColumns="false"
       striped="false"  pagination="true"
       ctrlSelect="true" pageSize="40" pageList="[20,40]">
    <thead>
    <tr>
        <th data-options="field:'ID',checkbox:true"></th>
        <th field="NAME" width="150">姓名</th>
        <th field="AGE" width="150">年龄</th>
        <th field="GENDER" width="150">性别</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a onclick="newModel('${ctx!}/exSingleTable/newModel', '500px', '400px')" href="#" class="easyui-linkbutton"
       iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/exSingleTable/newModel', '500px', '400px')" href="#"
       class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/exSingleTable/deleteAction')" href="#" class="easyui-linkbutton  "
       iconCls="iconfont icon-delete" plain="true">删除</a>

   <a onclick="goUploadPage('${ctx!}/utils/goUploadFilePage','${ctx!}/exSingleTable/importExcel','上传excel','上传excel')"
      href="#" class="easyui-linkbutton"   iconCls="iconfont icon-import" plain="true">导入</a>
   <a onclick="exportExcel('${ctx!}/exSingleTable/exportExcel','searchSpan')" href="#" class="easyui-linkbutton"
     iconCls="iconfont icon-export" plain="true">导出</a>

    <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_test" prompt="测试" class="easyui-textbox" style="width:120px; ">
            <a href="#" class="easyui-linkbutton searchBtn"  data-options="iconCls:'iconfont icon-search',plain:true"
               onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>
