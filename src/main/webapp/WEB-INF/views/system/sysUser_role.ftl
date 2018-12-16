<#--角色表单-->
<#include "../common/common.ftl"/>
<@layout>
      <table id="dg" class="easyui-datagrid"
             url="${ctx!}/sysUser/queryUserRole?search_EQS_a.SYS_USER_ID=${(userId)!}"
             toolbar="#tb" rownumbers="true" border="false"
             fitColumns="true"
             fit="true" pagination="true"
             ctrlSelect="true">
          <thead>
          <tr>
              <th data-options="field:'ID',checkbox:true"></th>
              <th field="NAME" width="100">名称</th>
              <th field="CODE" width="100">名称</th>
              <th field="DESCRIPTION" width="300">说明</th>
              <th field="CREATER" width="100" formatter="usernameFmt">添加人</th>
          </tr>
          </thead>
      </table>
    <div id="tb">
        <a onclick="openUtilsRole()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">添加</a>
        <a onclick="deleteRolesAction()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">删除</a>
        <span id="searchSpan" class="searchInputArea">
             <input name="search_LIKE_b.NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
             <input name="search_LIKE_b.NAME" prompt="编码" class="easyui-textbox" style="width:120px; ">
             <input name="search_LIKE_a.CREATER" prompt="添加人" class="easyui-textbox" style="width:120px; ">
             <a href="#" class="easyui-linkbutton searchBtn"
                data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel('dg','searchSpan')">搜索
             </a>
        </span>
    </div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    function usernameFmt(val,row) {
        return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }

    var winIndex;
    /*添加角色弹窗*/
    function openUtilsRole() {
        winIndex = popup.openIframe('添加角色','${ctx!}/utils/role', '500px', '400px')
    }

    /**
     * 保存选中的角色，被其它窗口调用
     * @param roles
     */
    function addRolesAction(roles){
        var ids = [];
        roles.forEach(function(aryItem){
            ids.push(aryItem.ID);
        });
        $.post('${ctx!}/sysUser/addUserRoleAction?roleIds=' + ids.join(',')+"&userId=${(userId)!}", function (data) {
            if(data.state=='ok'){
                popup.msg(data.msg, function () {
                    $('#dg').datagrid('reload');
                    popup.closeByIndex(winIndex);
                });
            }else if(data.state=='error'){
                popup.errMsg('系统异常',data.msg);
            }else{
                popup.msg(data.msg);
            }
        }, "json").error(function(){ popup.errMsg(); });
    }

    /**
     * 删除用户角色
     */
    function deleteRolesAction(){
        var rows = $("#dg").datagrid("getSelections");
        if (rows.length!=0) {
            popup.openConfirm(null,3, '删除', '您确定要删除选中的'+rows.length+'条记录吗?', function () {
                var roleIds = [];
                rows.forEach(function(row){
                    roleIds.push(row.SYS_ROLE_ID);
                });
                $.post('${ctx!}/sysUser/deleteUserRoleAction?userId=${(userId)!}&roleIds=' + roleIds.join(','), function (data) {
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
            popup.msg('请至少选择一行进行删除');
        }
    }
</script>
</@layout>
