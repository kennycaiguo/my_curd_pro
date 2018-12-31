<#--笔记   -->
<#include "../common/common.ftl"/>
<@layout>
<style>
    .card{
        margin: 5px 10px;
    }
    .card div{
      padding: 3px 0;
    }
    .card .title{
        font-size: 16px !important;
        font-weight: 400;
    }
    .card .time{
        font-size: 12px;
        color:grey;
    }
    .overHidden{
        overflow: hidden;
    }
    .datagrid-row{
        height: 70px;
    }
    .datagrid-btable{
        width: 100%;
    }
    .datagrid-header-row{
        display: none;
    }
    .datagrid-toolbar{
        border-bottom: none;
    }
</style>
<script>
    var contextMenuRow;
    var contextMenuNode;

    /*中间 datagrid load success*/
    function dgLoadSuccess(){
        $('#dg2').datagrid("selectRow",0);
    }

    /*中间datagrid 选中*/
    function selectNoteRow(index,row){
        $('#noteIframe').attr('src','${ctx!}/sysNote/editNoteModel?id='+row.ID);
    }

    /*中间datagrid 右键菜单*/
    function dgContextMenu(e,index,row){
        e.preventDefault();
        contextMenuRow = row;
        console.log(index);
        $('#mm2').menu('show',{
            left: e.pageX,
            top: e.pageY
        });
    }


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
    function newNote(){
        if(contextMenuNode==null){
            return;
        }
        $.getJSON('${ctx!}/sysNote/addNoteAction?cateId='+contextMenuNode.id,function (data) {
            if(data.state==='ok'){
                $('#dg2').datagrid("reload");
            }else if(data.state==='error'){
                popup.errMsg('系统异常',data.msg);
            }else{
                popup.msg(data.msg);
            }
        })
    }
    function delNote(){
        alert('delNote');
    }
</script>
<div class="easyui-layout" fit="true" border="false">
    <div data-options="region:'west',split:false" style="width:250px;border-top: none;" collapsible="false"    >
        <div style="margin: 10px;" >
            <ul id="tt"></ul>
            <div id="mm" class="easyui-menu"    >
                <div onclick="newNote()"  iconCls="iconfont icon-add" >新建笔记</div>
                <div onclick="newCate()"  iconCls="iconfont icon-add" >新建文件夹</div>
                <div onclick="editCate()" iconCls="iconfont icon-edit" >编辑文件夹</div>
                <div onclick="delCate()"  iconCls="iconfont icon-delete" >删除文件夹</div>
            </div>
        </div>
    </div>

    <div data-options="region:'center'" style="border-top: none;"  >
        <div class="easyui-layout" fit="true" border="false">
            <div data-options="region:'west',split:false" style="width:350px;border-top: none;border-left: none;" collapsible="true"    >
                <table id="dg2" class="easyui-datagrid"   fit="true"    fitColumns="true"
                       url="${ctx!}/sysNote/queryNote" toolbar="#tb2" border="false"
                       data-options="singleSelect:true,onLoadSuccess:dgLoadSuccess,onSelect:selectNoteRow,onRowContextMenu:dgContextMenu">
                    <thead>
                    <tr>
                        <th  field="TITLE" width="100" formatter="noteFmt">我的笔记</th>
                    </tr>
                    </thead>
                </table>
                <div id="tb2">
                    <div id="searchSpan2" class="searchInputAreaDiv" style="text-align: center"  >
                        <input id="cateId" type="hidden" name="extra_cateId">
                        <div class="pure-form" style="text-align: center;padding: 5px 0px;">
                            <i  onclick="queryModel('dg2','searchSpan2')" class="searchBtn"></i>
                            <input name="search_LIKE_TITLE" class="pure-input-rounded" placeholder="输入关键字 enter 搜索"  style="width: 90%;">
                        </div>
                    </div>
                </div>
                <div id="mm2" class="easyui-menu"    >
                    <div onclick="delNote()"  iconCls="iconfont icon-delete" >删除笔记</div>
                </div>
            </div>
            <div data-options="region:'center'" style="border-top: none;"  bodyCls="overHidden" >
                <iframe id="noteIframe"  style="width:100%;height:100%;" frameborder="0"></iframe>
            </div>
        </div>
    </div>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/plugins/easyui1.6.10/datagrid-scrollview.js"></script>
<script>
    function noteFmt(val,row){
        var html = '<div class="card"><div class="title">'
                +row.TITLE+'</div><div class="time">'+row.CREATE_TIME
                +'</div></div>';
        return html;
    }

    $(function () {
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
        /*屏蔽菜单的右键菜单*/
        preventDomContextMenu("mm");
        preventDomContextMenu("mm2");
    });
</script>
</@layout>