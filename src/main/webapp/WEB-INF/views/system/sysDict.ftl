<#--数据字典 datagrid  -->
<#include "../common/common.ftl"/>
<@layout>
<#include "../common/popup.ftl"/>
</head>
<body>
<script type="text/javascript">
    function newModel() {
        popup.openIframe('新建', '${ctx!}/dataDict/newModel', '500px', '300px')
    }
    function editModel() {
        var rows= $("#dg").treegrid("getSelections");
        if (rows.length==1) {
            popup.openIframe('编辑', '${ctx!}/dataDict/newModel?id=' + rows[0].id, '500px', '300px');
        } else {
            popup.msg('请选择一行数据进行编辑');
        }
    }
    function deleteModel() {
        var rows = $("#dg").datagrid("getSelections");
        if (rows.length!=0) {
            popup.openConfirm(3, '删除', '您确定要删除选中的'+rows.length+'条记录吗?', function () {
                var ids = [];
                rows.forEach(function(row){
                    ids.push(row.id);
                });
                $.post('${ctx!}/dataDict/deleteAction?ids=' + ids.join(','), function (data) {
                    popup.msg(data, function () {
                        $('#dg').datagrid('reload');
                    });
                }, "text").error(function(){ popup.errMsg(); });
            });

        } else {
            popup.msg('请至少选择一行进行删除');
        }
    }
    function queryModel() {
        var queryParams = {};
        queryParams['search_LIKE_dict_type'] = $("#type").textbox("getValue");
        queryParams['search_LIKE_dict_key'] = $("#key").textbox("getValue");
        queryParams['search_LIKE_dict_value'] = $("#value").textbox("getValue");
        $('#dg').datagrid('load', queryParams);
    }
</script>
<div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'west',split:true" title="字典分组" style="width:50%;">
        <table id="dg" class="easyui-datagrid"
               url="${ctx!}/sysDict/queryGroup"
               data-options="onDblClickRow:editModel"
               toolbar="#tb" rownumbers="true" border="false"
               fitColumns="true"
               fit="true" pagination="true"
               ctrlSelect="true"
               striped="true"
               pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th field="groupName" width="150">名称</th>
                <th field="groupCode" width="200">编码</th>
            </tr>
            </thead>
        </table>
        <div id="tb">
            <a onclick="newModel()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">新增</a>
            <a onclick="editModel()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
            <a onclick="deleteModel()" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-delete" plain="true">删除</a>
            <span id="enterSpan" class="searchInputArea">
                <input id="groupName" prompt="名称" class="easyui-textbox" style="width:120px; ">
                <input id="groupCode" prompt="编码" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel()">搜索</a>
            </span>
        </div>
    </div>

    <div data-options="region:'center',title:'字典值'">
        <table id="dg2" class="easyui-datagrid"
               url="${ctx!}/dataDict/query"
               data-options="onDblClickRow:editModel"
               toolbar="#tb2" rownumbers="true" border="false"
               fit="true" pagination="true"
               ctrlSelect="true"
               striped="true"
               pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th field="dict_type" width="150">类型(dict_type)</th>
                <th field="dict_key" width="200">名称(dict_key)</th>
                <th field="dict_value" width="300">值(dict_value)</th>
                <th field="create_time" width="200">创建时间</th>
                <th field="last_edit_time" width="200">最后修改时间</th>
            </tr>
            </thead>
        </table>
        <div id="tb2">
            <a onclick="newModel()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-add" plain="true">新增</a>
            <a onclick="editModel()" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
            <a onclick="deleteModel()" href="#" class="easyui-linkbutton  " iconCls="iconfont icon-delete" plain="true">删除</a>
            <span id="enterSpan" class="searchInputArea">
            <input id="type" prompt="类型(dict_type)" class="easyui-textbox" style="width:120px; ">
                <input id="key" prompt="名称(dict_key)" class="easyui-textbox" style="width:120px; ">
                <input id="value" prompt="值(dict_value)" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'iconfont icon-search',plain:true" onclick="queryModel()">搜索</a>
            </span>
        </div>
    </div>
</div>
</@layout>