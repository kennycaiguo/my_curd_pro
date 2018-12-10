<#--组织机构 treegrid  -->
<#include "../common/common.ftl"/>
<@layout>
<#include "../common/popup.ftl"/>
<div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'west',split:true" style="width:30%;" collapsible="false">
        <table id="tg" border="false"  ></table>
        <div id="tb">
            <a onclick="newModel('tg','${ctx!}/sysOrg/newModel', '700px', '500px')" href="#" class="easyui-linkbutton"  iconCls="iconfont icon-add" plain="true">新增</a>
            <a onclick="editModel('tg','${ctx!}/sysOrg/newModel', '700px', '400px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
            <a onclick="deleteModel('tg','${ctx!}/sysOrg/deleteAction')" href="#" class="easyui-linkbutton  "  iconCls="iconfont icon-delete" plain="true">删除</a>
            <span id="searchSpan" class="searchInputArea">
                    <input name="search_LIKE_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
                    <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                       onclick="queryModel('tg','searchSpan')">搜索</a>
            </span>
        </div>
    </div>

    <div data-options="region:'center'">
        <table id="dg2" class="easyui-datagrid"
               url="${ctx!}/sysDict/queryDict?search_EQS_del_flag=0"
               toolbar="#tb2" rownumbers="true" border="false"
               fit="true" pagination="true"
               fitColumns="true"
               ctrlSelect="true"
               striped="true"
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
            <div id="searchSpan2" class="searchInputAreaDiv"   >
                <input name="search_LIKE_dict_label" prompt="名称" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_dict_value" prompt="编码" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                   onclick="queryModel('dg2','searchSpan2')">搜索</a>
            </div>
        </div>
    </div>
</div>
<script src="${ctx!}/static/js/tg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script>

    ;(function () {
        var easyTree = new EasyTree();
        $("#tg").treegrid({
            url: '${ctx!}/sysOrg/query',
            method: 'POST',
            idField: 'ID',
            treeField: 'NAME',
            fit: true,
            lines:true,
            fitColumns:true,
            rownumbers: true,
            toolbar: '#tb',
            loadFilter: function (data) {
                data = easyTree.treeDataBuild(data, 'ID', 'PID', 'ID,PID,NAME,ADDRESS,MARK,IS_LEAF,SORT,CODE,state,iconCls');
                return data;
            },
            columns: [[
                {field: 'NAME', title: '机构名', width: 300},
                {field: 'SORT', title: '排序', width: 80}
            ]],
            onSelect: function (row) {
              /*  if (row.address == undefined) {
                    $('#orgAddressTd').text("");
                } else {
                    $('#orgAddressTd').text(row.address);
                }

                if (row.mark == undefined) {
                    $('#orgRemarkTd').text("");
                } else {
                    $('#orgRemarkTd').text(row.mark);
                }

                var queryParams = {};
                if ($('#cascadeSearch').prop('checked')) {
                    /!* 后台处理 查询 orgid 子孙 的相关用户 *!/
                    queryParams.orgId = row.id;
                } else {
                    /!* 只查询用户orgid 的用户 *!/
                    queryParams.search_EQ_org_id = row.id;
                }
                $('#dg').datagrid('load', queryParams);*/
            }
        });

    })();
</script>
</@layout>