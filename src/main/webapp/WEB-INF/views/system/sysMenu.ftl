<#--菜单 treegrid  -->
<#include "../common/common.ftl"/>
<@layout>
    <script>
        /**
         *  角色相关用户
         */
        function openMenuRole(){
            var rows= $("#tg").treegrid("getSelections");
            if (rows.length==1) {
                popup.openIframe('相关角色','${ctx!}/sysMenu/openMenuRole?id=' + rows[0].ID, '600px', '500px');
            } else {
                popup.msg('请选择一行数据查看相关角色');
            }
        }

    </script>
    <table id="tg" border="false"  ></table>
    <div id="tb">
        <a onclick="newModel('tg','${ctx!}/sysMenu/newModel', '700px', '400px')" href="#" class="easyui-linkbutton"  iconCls="iconfont icon-add" plain="true">新增</a>
        <a onclick="editModel('tg','${ctx!}/sysMenu/newModel', '700px', '400px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
        <a onclick="deleteModel('tg','${ctx!}/sysMenu/deleteAction')" href="#" class="easyui-linkbutton  "  iconCls="iconfont icon-delete" plain="true">删除</a>
        <a onclick="openMenuRole()" href="javascript:void(0)" class="easyui-linkbutton " iconCls="iconfont icon-look" plain="true"> 查看角色</a>
        <span id="searchSpan" class="searchInputArea">
                <input name="search_LIKE_NAME" prompt="名称" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                   onclick="queryModel('tg','searchSpan')">搜索</a>
        </span>
    </div>
<script src="${ctx!}/static/js/tg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script>
    ;(function () {
        var easyTree = new EasyTree();
        $("#tg").treegrid({
            url: '${ctx!}/sysMenu/query',
            method: 'POST',
            idField: 'ID',
            treeField: 'NAME',
            fit: true,
            lines:true,
            animate:true,
            fitColumns:false,
            rownumbers: true,
            toolbar: '#tb',
            loadFilter: function (data) {
                data = easyTree.treeDataBuild(data, 'ID', 'PID', 'ID,PID,SORT,NAME,URL,ICON,IS_LEAF,state,iconCls');
                return data;
            },
            columns: [[
                {field: 'NAME', title: '机构名', width: 300},
                {field: 'URL', title: '地址', width: 300},
                {field: 'ICON', title: '图标', width: 300},
                {field: 'SORT', title: '排序', width: 80}
            ]]
        });

    })();
</script>
</@layout>