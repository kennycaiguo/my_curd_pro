<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
    <#include "../common/popup.ftl"/>
    <table id="dg" class="easyui-datagrid"
          url="${ctx!}/sysVisitLog/query"
          toolbar="#tb" rownumbers="true" border="false"
          data-options=""
          fitColumns="false"
          fit="true" pagination="true"
          ctrlSelect="true"
          striped="true"
          pageSize="40" pageList="[20,40]">
       <thead>
       <tr>
           <th data-options="field:'ID',checkbox:true"></th>
           <th field="URL" width="300" formatter="urlFmt">请求地址</th>
           <th field="TYPE" width="100">请求类型</th>
           <th field="SYS_USER" width="100"  formatter="usernameFmt" >用户</th>
           <th field="SYS_USER_IP" width="150">IP地址</th>
           <th field="CREATE_TIME" width="200">创建时间</th>
       </tr>
       </thead>
    </table>
    <div id="tb">
        <#--<a onclick="viewModel('查看','dg','${ctx!}/sysVisitLog/view', '800px', '600px')" href="#"-->
           <#--class="easyui-linkbutton" iconCls="iconfont icon-eye" plain="true">查看</a>-->
        <a onclick="deleteModel('dg','${ctx!}/sysVisitLog/deleteAction')" href="#" class="easyui-linkbutton  "
           iconCls="iconfont icon-delete" plain="true">删除</a>
        <span id="searchSpan" class="searchInputArea">
            <input name="search_LIKE_SYS_USER" prompt="用户名" class="easyui-textbox" style="width:120px; ">
            <input name="search_LIKE_SYS_USER_IP" prompt="IP地址" class="easyui-textbox" style="width:120px; ">
            <input name="search_LIKE_TYPE" prompt="请求类型" class="easyui-combobox" data-options="valueField:'VALUE',textField:'LABEL'
               ,url:'${ctx!}/sysDict/combobox?groupCode=httpMethod'" style="width:120px; " >
            <input name="search_GTEDT_CREATE_TIME"  prompt="创建时间起" class="easyui-datetimebox" >
            <input name="search_LTEDT_CREATE_TIME" prompt="创建时间止" class="easyui-datetimebox" >

            <a href="#" class="easyui-linkbutton searchBtn"
               data-options="iconCls:'iconfont icon-search',plain:true"
               onclick="queryModel('dg','searchSpan')">搜索</a>
        </span>
    </div>
    <script src="${ctx!}/static/js/dg-curd.js"></script>
    <script>
        function urlFmt(val,row){
            return '<a title="点击查看请求信息" href="javascript:viewModelsByLink(\'查看\',\'${ctx!}/sysVisitLog/view?id='
                    +row.ID+'\',\'800px\',\'400px\')"  >'+val+'</a>';
        }
        function usernameFmt(val,row) {
            return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
        }
    </script>
</@layout>