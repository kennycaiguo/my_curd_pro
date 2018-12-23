<#include "../common/common.ftl"/> <@layout>
<script type="text/javascript">
    function dgOnSelect(index,row){
        $("#sysNoticeTypeId").val(row.ID);
        $("#searchSpan2 .searchBtn").first().trigger('click')
    }
    var winIndex;
    /*打开添加角色弹窗*/
    function openUtilsUser() {
        var row = $("#dg").datagrid("getSelected");
        if(row==null){
            popup.msg('请选择一个通知类型');
            return;
        }
        winIndex = popup.openIframe('关联用户','${ctx!}/utils/user?yesBtnTxt=关联用户', '800px', '500px')
    }

    /**
     * 保存选中的用户，被其它窗口调用
     * @param roles
     */
    function addUsersAction(roles){
        var row = $("#dg").datagrid("getSelected");
        if(row==null){
            popup.msg('请选择一个通知类型');
            return;
        }
        var ids = [];
        roles.forEach(function(aryItem){
            ids.push(aryItem.ID);
        });
        $.post('${ctx!}/sysNTUser/addAction?userIds=' + ids.join(',')+"&sysNoticeTypeId="+row.ID, function (data) {
            if(data.state=='ok'){
                popup.msg(data.msg, function () {
                    $('#dg2').datagrid('reload');
                    popup.closeByIndex(winIndex);
                });
            }else if(data.state=='error'){
                popup.errMsg('系统异常',data.msg);
            }else{
                popup.msg(data.msg);
            }
        }, "json").error(function(){ popup.errMsg(); });
    }


    /* 删除 多行 */
    function deleteNTUs() {
        var rows = $("#dg2").datagrid("getSelections");
        if(rows.length==0){
            popup.msg('请至少选择一行进行删除');
            return;
        }
        popup.openConfirm(null,3, '删除', '您确定要删除选中的'+rows.length+'条记录吗?', function () {
            var idPairs = [];
            rows.forEach(function(row){
                idPairs.push(row.SYS_NOTICE_TYPE_ID+","+row.SYS_USER_ID);
            });
            $.post('${ctx!}/sysNTUser/deleteAction?idPairs='+ idPairs.join(';'), function (data) {
                if(data.state==='ok'){
                    popup.msg(data.msg, function () {
                        $('#dg2').datagrid('reload');
                    });
                }else if(data.state==='error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            }, "json").error(function(){ popup.errMsg(); });
        });
    }
    /* 删除 单行 */
    function delSingleNTU(sysNoticeTypeId,userId) {
        var idPairs = [];
        idPairs.push(sysNoticeTypeId+","+userId);
        $.post('${ctx!}/sysNTUser/deleteAction?idPairs='+idPairs.join(";") , function (data) {
            if(data.state=='ok'){
                popup.msg(data.msg, function () {
                    $('#dg2').datagrid('reload');
                });
            }else if(data.state=='error'){
                popup.errMsg('系统异常',data.msg);
            }else{
                popup.msg(data.msg);
            }
        }, "json").error(function(){ popup.errMsg(); });
    }
</script>

<div id="nestLayout" class="easyui-layout" fit="true"   >
    <div data-options="region:'west' " split="true" title="通知类型"   style="width:600px;"  headerCls="borderTopNone">
        <table id="dg" class="easyui-datagrid"  url="${ctx!}/sysNoticeType/query"
               toolbar="#tb" rownumbers="true" border="false"
               data-options="onSelect:dgOnSelect"
               fit="true"    fitColumns="true"
               striped="false" singleSelect="true"
               pagination="true"  nowrap="false"
               pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th field="CATE" width="200">分类</th>
                <th field="NAME" width="200">名称</th>
                <th field="CODE" width="200">编码</th>
            <#--<th field="REMARK" width="400">备注</th>-->
            </tr>
            </thead>
        </table>
        <div id="tb" style="text-align: center">
            <div  id="searchSpan" class="searchInputAreaDiv" style="text-align: center;">
                <input name="search_LIKE_CATE" prompt="分类" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_CODE" prompt="编码" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                   onclick="queryModel('dg','searchSpan')">搜索</a>
            </div>
        </div>
    </div>

    <div data-options="region:'center' " split="true"   collapsed="false"   headerCls="borderTopNone">
        <table id="dg2" class="easyui-datagrid" title="已关联用户"
               url="${ctx!}/sysNTUser/query"
               toolbar="#tb2" rownumbers="true" border="false"
               fit="true"    fitColumns="true"
               striped="false" ctrlSelect="true"
               pagination="true"  pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th data-options="field:'SYS_NOTICE_TYPE_ID',checkbox:true"></th>
                <th field="CATE" width="100">类型分类</th>
                <th field="NAME" width="200">类型名</th>
                <th field="CODE" width="200">类型编码</th>
                <th field="USERNAME" width="200" formatter="usernameFmt">用户名</th>
                <th field="SYS_USER_ID" width="200" formatter="deleteFmt">操作</th>
            </tr>
            </thead>
        </table>
        <div id="tb2">
            <a onclick="openUtilsUser()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">添加</a>
            <a onclick="deleteNTUs()" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-delete" plain="true">删除</a>
            <span id="searchSpan2" class="searchInputArea">
                <input name="search_LIKE_c.USERNAME" prompt="用户名" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_c.NAME" prompt="用户姓名" class="easyui-textbox" style="width:120px; ">
                <input id="sysNoticeTypeId" name="search_EQS_a.SYS_NOTICE_TYPE_ID" type="hidden" >
                <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel('dg2','searchSpan2')">搜索</a>
            </span>
        </div>
    </div>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    function deleteFmt(val,row){
        return '<a href="javascript:delSingleNTU(\''+row.SYS_NOTICE_TYPE_ID+'\',\''+row.SYS_USER_ID+'\')">删除</a>'
    }
    function usernameFmt(val,row) {
        return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }
</script>
</@layout>
