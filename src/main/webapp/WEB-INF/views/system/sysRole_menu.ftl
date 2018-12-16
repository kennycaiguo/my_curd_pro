<#include "../common/common.ftl"/> <@layout>
<style>
    html, body {
        height: 96%;
        background-color: #f5f5f5
    }
</style>
<script type="text/javascript">
    /*数组去重*/
    Array.prototype.unique = function () {
        var res = [];
        var json = {};
        for (var i = 0; i < this.length; i++) {
            if (!json[this[i]]) {
                res.push(this[i]);
                json[this[i]] = 1;
            }
        }
        return res;
    };

    /*更新菜单*/
    function menuTreeUpdate() {
        var checkedNodes = $("#allMenuTree").tree("getChecked");
        var menuIds = [];
        for (var i = 0; i < checkedNodes.length; i++) {
            /* 只提交叶子节点 */
            if ($("#allMenuTree").tree("isLeaf", checkedNodes[i].target)) {
                menuIds.push(checkedNodes[i].id);
            }
        }
        $.post('${ctx!}/sysRole/menuTreeUpdate', {
            roleId: '${roleId!}',
            menuIds: menuIds.join(",")
        }, function (data) {
            if (data.state == 'ok') {
                popup.msg(data.msg, function () {
                    $('#dg').datagrid('reload');
                    popup.close(window.name);
                });
            } else if (data.state == 'error') {
                popup.errMsg('系统异常', data.msg);
            } else {
                popup.msg(data.msg);
            }
        }, 'json').error(function () {
            popup.errMsg();
        });
    }
</script>
<div id="toolbars" style=" text-align: right;padding: 2px; ">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'iconfont icon-save'" onclick="menuTreeUpdate()">更新菜单</a>
</div>
<div style="height: 96%; overflow: auto;background-color:#fff;border: 1px solid lightgray;">
    <ul id="allMenuTree" class="easyui-tree"></ul>
</div>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script>
    var easyTree = new EasyTree();
    function loadMenuTreeChecked() {
        var url = "${ctx!}/sysRole/menuTreeChecked?roleId=${roleId!}";
        $.getJSON(url, function (data) {
            data = easyTree.treeDataBuild(data, 'id', 'pid', 'text,state,checked,pid,iconCls');
            $("#allMenuTree").tree({
                data: data,
                checkbox: true,
                lines: true
            });
        });
    }
    $(function () {
        loadMenuTreeChecked();
    });
</script>
</@layout>
