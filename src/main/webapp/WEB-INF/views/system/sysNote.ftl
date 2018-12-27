<#--笔记 treegrid  -->
<#include "../common/common.ftl"/>
<@layout>
<link href="https://cdn.jsdelivr.net/npm/tailwindcss/dist/tailwind.min.css" rel="stylesheet">
<script>
    var contextMenuNode;
    function newCate() {
        var url = '${ctx!}/sysNote/newCateModel';
        if(contextMenuNode!=null){
            url +="?pid="+contextMenuNode.id;
        }
        popup.openIframe('新建分类', url, '360px', '360px');
    }
    function editCate(){
        if(contextMenuNode==null || contextMenuNode.id == '0'){
            return;
        }
        popup.openIframe('编辑', '${ctx!}/sysNote/newCateModel?id=' + contextMenuNode.id, '360px', '360px');
    }
    function delCate() {
        if(contextMenuNode==null){
            return;
        }
        popup.openConfirm(null,3, '删除', '您确定要删除选中的文件夹 "'+contextMenuNode.text+'" 吗?', function () {
            $.post('${ctx!}/sysNote/deleteCateAction?id=' + contextMenuNode.id, function (data) {
                if(data.state==='ok'){
                    popup.msg(data.msg, function () {
                        $('#tt').tree('reload');
                    });
                }else if(data.state==='error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            }, "json").error(function(){ popup.errMsg(); });
        });
    }
</script>
<div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'west',split:false" style="width:250px;border-top: none;" collapsible="false"    >
        <div style="margin: 10px;" >
            <ul id="tt"></ul>
            <div id="mm" class="easyui-menu" style="width:120px;" >
                <div name="newNote" onclick="expand()">新建笔记</div>
                <div class="menu-sep"></div>
                <div onclick="newCate()"  data-options="iconCls:'iconfont icon-add'">新建文件夹</div>
                <div onclick="editCate()" data-options="iconCls:'iconfont icon-edit'">编辑文件夹</div>
                <div onclick="delCate()" data-options="iconCls:'iconfont icon-delete'">删除文件夹</div>
            </div>
        </div>
    </div>

    <div data-options="region:'center'" style="border-top: none;"  >
        <div class="easyui-layout" fit="true" border="false">
            <div data-options="region:'west',split:false" style="width:350px;border-top: none;border-left: none;" collapsible="false"    >
                <table id="dg2" class="easyui-datagrid"   border="false"   fit="true"   fitColumns="true"
                       url="${ctx!}/sysNote/queryNote" toolbar="#tb2"
                       data-options="view:scrollview,singleSelect:true,pageSize:50">
                    <thead>
                    <tr>
                        <th  field="TITLE" width="100" formatter="noteFmt">我的笔记</th>
                    </tr>
                    </thead>
                </table>
                <div id="tb2">
                    <div id="searchSpan2" class="searchInputAreaDiv" style="text-align: center"  >
                        <input id="cateId" type="hidden" name="extra_cateId">
                        <input name="search_LIKE_TITLE" prompt="搜索" class="easyui-textbox" >
                        <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
                           onclick="queryModel('dg2','searchSpan2')">搜索</a>
                    </div>
                </div>
            </div>
            <div data-options="region:'center'" style="border-top: none;"  bodyCls="bg-grey-lighter"  >
                bianjiqi
            </div>
        </div>
    </div>
</div>
<script src="${ctx!}/static/js/tg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/plugins/easyui1.6.10/datagrid-scrollview.js"></script>
<script>
    function noteFmt(val,row){
       return val;
    }

    (function () {
        var easyTree = new EasyTree();
        $('#tt').tree({
            url: '${ctx!}/sysNote/queryCate',
            animate:true,
            lines:false,
            loadFilter: function(data){
                data = easyTree.treeDataBuild(data, 'id', 'pid', 'id,pid,text,remark,create_time,update_time,state,iconCls');
                return data;
            },
            onContextMenu: function(e,node){
                e.preventDefault();
                contextMenuNode = node;
                $('#mm').menu('show',{
                    left: e.pageX,
                    top: e.pageY
                });
            },
            onSelect: function (node) {
                $('#cateId').val(node.id);
                $(".searchBtn","#searchSpan2").first().trigger('click');
            }
        });
    })();

    $(function () {
        preventDomContextMenu("mm");
    });
</script>
</@layout>