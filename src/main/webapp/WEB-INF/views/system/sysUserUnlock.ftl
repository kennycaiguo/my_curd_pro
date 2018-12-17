<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
    <table id="dg" class="easyui-datagrid"
          url="${ctx!}/sysUserUnlock/query"
          toolbar="#tb" rownumbers="true" border="false"
          fitColumns="false"
          fit="true" pagination="true"
          ctrlSelect="true"
          striped="true"
          pageSize="40" pageList="[10,20,40,80]">
       <thead>
       <tr>
           <th data-options="field:'ID',checkbox:true"></th>
           <th field="USERNAME" width="150"  formatter="usernameFmt" >用户名</th>
           <th field="NAME" width="100">姓名</th>
           <th field="JOB" width="200" >职位</th>
       </tr>
       </thead>
    </table>
    <div id="tb">
        <a onclick="unlock()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-delete" plain="true">解锁</a>
    </div>
    <script src="${ctx!}/static/js/dg-curd.js"></script>
    <script>
        function usernameFmt(val,row) {
            return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
        }

        /**
         * 解锁
         */
        function unlock() {
            var rows = $("#dg").datagrid("getSelections");
            if (rows.length!=0) {
                popup.openConfirm(null,3, '解锁', '您确定要解锁选中的'+rows.length+'个账号吗?', function () {
                    var usernames = [];
                    rows.forEach(function(row){
                        usernames.push(row.USERNAME);
                    });
                    $.post('${ctx!}/sysUserUnlock/unlockAction?usernames=' + usernames.join(','), function (data) {
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
                });
            } else {
                popup.msg('请至少选择一行进行解锁');
            }
        }
    </script>
</@layout>