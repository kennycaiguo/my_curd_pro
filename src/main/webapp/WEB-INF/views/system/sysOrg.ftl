<#--组织机构 treegrid  -->
<#include "../common/common.ftl"/>
<@layout>
<#include "../common/popup.ftl"/>
<div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'west',split:true" style="width:30%;" collapsible="false" >
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
               url="${ctx!}/sysOrg/queryUser"
               toolbar="#tb2" rownumbers="true" border="false"
               fit="true" pagination="true"
               fitColumns="true"
               ctrlSelect="true"
               striped="true"
               pageSize="40" pageList="[20,40]">
            <thead>
            <tr>
                <th data-options="field:'ID',checkbox:true"></th>
                <th field="ORG_NAME" width="100">部门</th>
                <th field="NAME" width="100">姓名</th>
                <th field="JOB" width="150">职位</th>
                <th field="GENDER_TEXT" width="50">性别</th>
                <th field="USERNAME" width="100" formatter="usernameFmt">用户名</th>
            </tr>
            </thead>
        </table>
        <div id="tb2">
            <span style="display: inline-block;height: 30px;padding: 4px 10px;">
                  <input class="easyui-checkbox" id="cascadeSearch" checked="true" value="cascadeOrg" label="级联查询"  data-options="onChange:cascadeOrgChange" >
            </span>
            <span id="searchSpan2" class="searchInputArea"   >
                <#-- 前台传递 手动处理的 参数-->
                <input id="cascadeOrg" type="hidden" name="extra_cascadeOrg">
                <input id="orgId" type="hidden" name="extra_orgId">
                <#-- 拦截器 拼装sql-->
                <input name="search_LIKE_a.NAME" prompt="姓名" class="easyui-textbox" style="width:120px; ">
                <input name="search_LIKE_a.JOB" prompt="职位" class="easyui-textbox" style="width:120px; ">
                <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                   onclick="queryModel('dg2','searchSpan2')">搜索</a>
            </span>
        </div>
    </div>
</div>
<script src="${ctx!}/static/js/tg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script>
    function usernameFmt(val,row) {
        return '<a title="点击查看用户信息"  href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }
    function orgNameFmt(val,row) {
      return '<a href="javascript:orgInfo(\'${ctx!}\',\''+row.ID+'\')" title="点击查看机构信息" >'+val+'</a>';
    }

    <#--级联机构查询 onChange 事件-->
    function cascadeOrgChange(checked){
        $("#cascadeOrg").val(checked);

        if(notEmpty($("#orgId").val())){
            $(".searchBtn","#searchSpan2").first().trigger('click');
        }
    }

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
                {field: 'NAME', title: '机构名', width: 300,formatter:orgNameFmt},
                {field: 'SORT', title: '排序', width: 80}
            ]],
            onSelect: function (row) {
                $('#orgId').val(row.ID);
                $("#cascadeOrg").val($('#cascadeSearch').checkbox('options').checked);
                $(".searchBtn","#searchSpan2").first().trigger('click');
            }
        });

    })();
</script>
</@layout>