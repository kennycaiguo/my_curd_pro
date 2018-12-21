<#include "../common/common.ftl"/> <@layout>
<script type="text/javascript">
    function dgOnSelect(index,row){
        $("#sysNoticeTypeId").val(row.ID);
        $("#searchSpan2 .searchBtn").first().trigger('click')
    }
    var winIndex;
    /*打开添加角色弹窗*/
    function openUtilsRole() {
        var row = $("#dg").datagrid("getSelected");
        if(row==null){
            popup.msg('请选择一个通知类型');
            return;
        }
        winIndex = popup.openIframe('关联角色','${ctx!}/utils/role?yesBtnTxt=关联角色', '500px', '400px')
    }

    /**
     * 保存选中的角色，被其它窗口调用
     * @param roles
     */
    function addRolesAction(roles){
        var row = $("#dg").datagrid("getSelected");
        if(row==null){
            popup.msg('请选择一个通知类型');
            return;
        }
        var ids = [];
        roles.forEach(function(aryItem){
            ids.push(aryItem.ID);
        });
        $.post('${ctx!}/sysNTRole/addAction?roleIds=' + ids.join(',')+"&sysNoticeTypeId="+row.ID, function (data) {
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
</script>

<div id="nestLayout" class="easyui-layout" fit="true"   >
    <div data-options="region:'west' " split="true"   style="width:600px;border-top: none;">
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
        <div id="tb">
            <div  id="searchSpan" class="searchInputAreaDiv" style="text-align: center;">
                <input name="search_LIKE_CATE" prompt="分类" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_CODE" prompt="编码" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                   onclick="queryModel('dg','searchSpan')">搜索</a>
            </div>
        </div>
    </div>

    <div data-options="region:'center' " split="true"   collapsed="false"  style="border-top: none;">
        <table id="dg2" class="easyui-datagrid"
               url="${ctx!}/sysNTRole/query"
               toolbar="#tb2" rownumbers="true" border="false"
               fit="true"    fitColumns="true"
               striped="false"
               singleSelect="true"
               pagination="true"
               pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th field="CATE" width="100">类型分类</th>
                <th field="NAME" width="200">类型名</th>
                <th field="CODE" width="200">类型编码</th>
                <th field="ROLE_NAME" width="200">角色名</th>
                <th field="ROLE_CODE" width="200">角色编码</th>
                <th field="SYS_ROLE_ID" width="200" formatter="deleteFmt">操作</th>
            </tr>
            </thead>
        </table>
        <div id="tb2">
            <a onclick="openUtilsRole()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">关联角色</a>
            <span id="searchSpan2" class="searchInputArea">
                <input name="search_LIKE_b.NAME" prompt="角色名" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_b.CODE" prompt="角色编码" class="easyui-textbox" style="width:120px; ">
                <input id="sysNoticeTypeId" name="search_EQS_a.SYS_NOTICE_TYPE_ID" type="hidden" >
                <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                   onclick="queryModel('dg2','searchSpan2')">搜索</a>
            </span>
        </div>
    </div>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    /* 删除 单行 */
    function deleteNoticeTypeRole(sysNoticeTypeId,roleId) {
        $.post('${ctx!}/sysNTRole/deleteAction?sysNoticeTypeId='+sysNoticeTypeId+"&roleId="+roleId , function (data) {
            if(data.state=='ok'){
                popup.msg(data.msg, function () {
                    $('#dg2').datagrid('reload');
                });
            }else if(data.state=='error'){
                popup.errMsg('系统异常',data.msg);
            }else{
                popup.msg(data.msg);
            }
        },"json").error(function(){ popup.errMsg(); });
    }
    function deleteFmt(val,row){
        return '<a href="javascript:deleteNoticeTypeRole(\''+row.SYS_NOTICE_TYPE_ID+'\',\''+row.SYS_ROLE_ID+'\')">删除</a>'
    }
</script>
</@layout>
