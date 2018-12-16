<#include "../common/common.ftl"/>
<@layout>
<table id="dg" class="easyui-datagrid"
       url="${ctx!}/sysRole/queryRoleUser?search_EQS_a.SYS_ROLE_ID=${roleId!}"
       toolbar="#tb" rownumbers="true" border="false"
       fit="true"    fitColumns="true"
       striped="true"   pageSize="40" pageList="[20,40]"
       pagination="true"
       ctrlSelect="true" >
    <thead>
    <tr>
        <th  width="100" field="USERNAME" formatter="usernameFmt">登录名</th>
        <th  width="100" field="NAME">姓名</th>
        <th  width="100" field="JOB">职位</th>
        <#--<th field="CREATER" width="100">操作人</th>-->
        <th field="SYS_ROLE_ID" width="50" formatter="deleteFmt">操作</th>
    </tr>
    </thead>
</table>
<div id="tb" style="text-align: center; padding: 5px">
    <div id="searchSpan" class="searchInputAreaDiv" style="text-align: center" >
      	 <input name="search_LIKE_b.USERNAME" prompt="登录名" class="easyui-textbox" style="width:120px">
         <input name="search_LIKE_b.NAME" prompt="姓名" class="easyui-textbox" style="width:120px">
          <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
             onclick="queryModel('dg','searchSpan')">搜索</a>
    </div>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    function usernameFmt(val,row) {
        return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }
    function deleteFmt(val,row){
        var userId = row.SYS_USER_ID;
        var roleId = row.SYS_ROLE_ID;
        return '<a href="javascript:deleteRoleUser(\''+userId+'\',\''+roleId+'\')"> 删除 </a>'
    }

    /* 删除 单行 */
    function deleteRoleUser(userId,roleId) {
        $.get('${ctx!}/sysRole/deleteUserRole?userId='+userId+"&roleId="+roleId , function (data) {
            if(data.state=='ok'){
                popup.msg(data.msg, function () {
                    $('#dg').datagrid('reload');
                });
            }else if(data.state=='error'){
                popup.errMsg('系统异常',data.msg);
            }else{
                popup.msg(data.msg);
            }
        }, "json").error(function(){ popup.errMsg(); });
    }
</script>
</@layout>
