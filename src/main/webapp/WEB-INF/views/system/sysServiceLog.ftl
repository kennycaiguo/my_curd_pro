<#--数据字典 datagrid  -->
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
          url="${ctx!}/sysServiceLog/query"
          toolbar="#tb" rownumbers="true" border="false"
          fitColumns="false" nowrap="false"
          fit="true" pagination="true"
          ctrlSelect="true"  striped="false"
          pageSize="40" pageList="[10,20,40,80]">
       <thead>
       <tr>
           <th data-options="field:'ID',checkbox:true"></th>
           <th field="SYS_USER" width="150"  formatter="usernameFmt" >用户</th>
           <th field="CONTENT" width="400">内容</th>
           <th field="URL" width="300" >请求地址</th>
           <th field="SYS_USER_IP" width="200">IP地址</th>
           <th field="CREATE_TIME" width="200">创建时间</th>
       </tr>
       </thead>
    </table>
    <div id="tb">
        <a onclick="deleteModel('dg','${ctx!}/sysServiceLog/deleteAction')" href="#" class="easyui-linkbutton  "
           iconCls="iconfont icon-delete" plain="true">删除</a>
        <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_SYS_USER" prompt="用户名" class="easyui-textbox" style="width:120px; ">
            <input name="search_LIKE_SYS_USER_IP" prompt="IP地址" class="easyui-textbox" style="width:120px; ">
            <input name="search_GTEDT_CREATE_TIME"  prompt="创建时间起" class="easyui-datetimebox" >
            <input name="search_LTEDT_CREATE_TIME" prompt="创建时间止" class="easyui-datetimebox" >
            <a href="#" class="easyui-linkbutton searchBtn"
               data-options="iconCls:'iconfont icon-search',plain:true"
               onclick="queryModel('dg','searchSpan')">搜索</a>
        </span>
    </div>
    <script src="${ctx!}/static/js/dg-curd.js"></script>
    <script>
        function usernameFmt(val,row) {
            return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
        }
    </script>
</@layout>