<#--定时任务运行日志 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
<table id="dg" class="easyui-datagrid"
     url="${ctx!}/sysTask/taskLogQuery?search_EQS_CLASS_NAME=${className!}"
     toolbar="#tb" rownumbers="true" border="false"
     fitColumns="true"  fit="true" pagination="true"  ctrlSelect="true"
     striped="false"  pageSize="40" pageList="[20,40]">
  <thead>
  <tr>
      <th data-options="field:'ID',checkbox:true"></th>
      <th field="RESULT" width="100" formatter="resultFmt">运行结果</th>
  <#--<th field="CLASS_NAME" width="250">类名</th>-->
      <th field="START_TIME" width="150">开始时间</th>
      <th field="END_TIME" width="150">结束时间</th>
      <#--<th field="ERROR" width="200">错误信息</th>-->
  </tr>
  </thead>
</table>
<div id="tb">
    <a onclick="deleteModel('dg','${ctx!}/sysTask/deleteTaskLogAction')" href="#" class="easyui-linkbutton  "
           iconCls="iconfont icon-delete" plain="true">删除</a>
    <span id="searchSpan" class="searchInputArea">
            <input name="search_GTEDT_START_TIME" prompt="开始时间" class="easyui-datetimebox"  >
            <input name="search_LTEDT_END_TIME" prompt="结束时间" class="easyui-datetimebox"  >
            <a href="#" class="easyui-linkbutton searchBtn"
               data-options="iconCls:'iconfont icon-search',plain:true"
               onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    function resultFmt(val) {
        if(val=='success'){
            return '<font color="#40b370">'+val+'</font>'
        }
        return '<font color="red">'+val+'</font>'
    }
</script>
</@layout>