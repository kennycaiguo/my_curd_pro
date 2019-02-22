<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
    <div class="easyui-layout" fit="true" border="false">
        <div data-options="region:'west',split:true" title="字典分组" style="width:40%;" collapsible="false" headerCls="borderTopNone">
            <table id="dg" class="easyui-datagrid"
                   url="${ctx!}/sysDict/queryGroup?search_EQS_del_flag=0"
                   toolbar="#tb" rownumbers="true" border="false"
                   data-options="onSelect:groupSelect"
                   fitColumns="true"
                   fit="true" pagination="true"
                   ctrlSelect="true"
                   striped="false"
                   pageSize="40" pageList="[20,40]">
                <thead>
                <tr>
                    <th data-options="field:'ID',checkbox:true"></th>
                    <th field="GROUP_NAME" width="150">名称</th>
                    <th field="GROUP_CODE" width="200">编码</th>
                    <th field="CREATE_TIME" width="200">创建时间</th>
                </tr>
                </thead>
            </table>
            <div id="tb">
                <a onclick="newModel('${ctx!}/sysDict/newGroupModel', '500px', '250px')" href="#" class="easyui-linkbutton"
                   iconCls="iconfont icon-add" plain="true">新增</a>
                <a onclick="editModel('dg','${ctx!}/sysDict/newGroupModel', '500px', '250px')" href="#"
                   class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
                <a onclick="deleteModel('dg','${ctx!}/sysDict/deleteGroupAction')" href="#" class="easyui-linkbutton  "
                   iconCls="iconfont icon-delete" plain="true">删除</a>
                <span id="searchSpan" class="searchInputArea">
                    <input name="search_LIKE_GROUP_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
                    <input name="search_LIKE_GROUP_CODE" prompt="编码" class="easyui-textbox" style="width:120px; ">
                    <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                       onclick="queryModel('dg','searchSpan')">搜索</a>
                </span>
            </div>
        </div>

        <div data-options="region:'center',title:'字典值'"  headerCls="borderTopNone">
            <table id="dg2" class="easyui-datagrid"
                   url="${ctx!}/sysDict/queryDict?search_EQS_del_flag=0"
                   toolbar="#tb2" rownumbers="true" border="false"
                   fit="true" pagination="true"
                   fitColumns="true"
                   ctrlSelect="true"
                   striped="false"
                   pageSize="40" pageList="[20,40]">
                <thead>
                <tr>
                    <th data-options="field:'ID',checkbox:true"></th>
                    <th field="DICT_LABEL" width="150">名称</th>
                    <th field="DICT_VALUE" width="200">编码</th>
                    <th field="DICT_SORT" width="100">排序号</th>
                    <th field="CREATE_TIME" width="200">创建时间</th>
                </tr>
                </thead>
            </table>
            <div id="tb2">
                <a onclick="newDictModel()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">新增</a>
                <a onclick="editModel('dg2','${ctx!}/sysDict/newDictModel', '500px', '320px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
                <a onclick="deleteModel('dg2','${ctx!}/sysDict/deleteDictAction')" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-delete" plain="true">删除</a>
                <span id="searchSpan2" class="searchInputArea">
                      <input id="groupCodeHid" name="search_EQS_GROUP_CODE" type="hidden"  >
                    <input name="search_LIKE_dict_label" prompt="名称" class="easyui-textbox" style="width:120px; ">
                    <input name="search_LIKE_dict_value" prompt="编码" class="easyui-textbox" style="width:120px; ">
                    <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                       onclick="queryModel('dg2','searchSpan2')">搜索</a>
                </span>
            </div>
        </div>
    </div>
    <script src="${ctx!}/static/js/dg-curd.js"></script>
    <script>
        /*新增字典弹窗*/
        function newDictModel(){
            var rows = $("#dg").datagrid("getSelections");
            if(rows.length==0 || rows.length>1){
               popup.msg('请选中一条字典分组');
               return;
            }
            popup.openIframe('新建','${ctx!}/sysDict/newDictModel?groupCode='+rows[0].GROUP_CODE, '500px', '320px')
        };

        /*左侧分组选中*/
        function groupSelect(index,row){
            $('#groupCodeHid').val(row.groupCode);
            $('#searchSpan2 a').first().trigger('click');
        };
    </script>
</@layout>