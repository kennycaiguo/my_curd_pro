<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
<#include "../common/popup.ftl"/>
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
     <th field="USERNAME" width="100">用户名</th>
     <th field="NAME" width="100">姓名</th>
     <th field="AVATAR" width="100">头像</th>
     <th field="GENDER" width="100">性别</th>
     <th field="ORG_NAME" width="100">部门</th>
     <th field="EMAIL" width="100">邮箱</th>
     <th field="PHONE" width="100">电话</th>
     <th field="JOB" width="100">职位</th>
     <th field="JOB_LEVEL" width="100">职位级别</th>
     <th field="DISABLE" width="100">是否禁用</th>
     <th field="LAST_LOGIN_TIME" width="100">上次登录时间</th>
 </tr>
 </thead>
</table>
<div id="tb">
    <a onclick="newModel('${ctx!}/sysUser/newModel', '800px', '500px')" href="#" class="easyui-linkbutton"  iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/sysUser/newModel', '800px', '500px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/sysUser/deleteAction')" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-delete" plain="true">删除</a>
    <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
            <input name="search_LIKE_CODE" prompt="编码" class="easyui-textbox" style="width:120px; ">
            <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"  onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>