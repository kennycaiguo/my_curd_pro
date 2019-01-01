<#--笔记分类 表单-->
<#include "../common/common.ftl"/>
<@layout>
<script>
    function saveAction(){
        $('#modelForm').form('submit', {
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (data) {
                if(typeof data ==='string'){
                    data = JSON.parse(data);
                }
                if(data.state === 'ok'){
                    popup.msg(data.msg, function () {
                        window.parent.frames[sessionStorage.getItem("iframeId")].$("#tt").tree("reload");
                        popup.close(window.name);
                    });
                }else if(data.state === 'error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            }
        });
    }
</script>
<form id="modelForm" method="POST" action="<#if sysNoteCate?? >${ctx!}/sysNote/updateCateAction<#else>${ctx!}/sysNote/addCateAction</#if>">
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="id" type="hidden" value="${(sysNoteCate.id)!}">
        <input type="hidden" name="sysUserId" value="${(sysNoteCate.sysUserId)!}">
        <tbody>
        <tr>
            <td>父级：</td>
            <td>
                <input name="pid"  id="pid" data-options="panelHeight:'200px'">
            </td>
        </tr>
        <tr>
            <td>名称：</td>
            <td>
              <input name="cateTitle" value="${(sysNoteCate.cateTitle)!}"   class="easyui-textbox"  data-options="required:true" >
            </td>
        </tr>
        <tr>
            <td>备注：</td>
            <td >
                <input name="cateRemark" value="${(sysNoteCate.cateRemark)!}"   class="easyui-textbox" data-options="required:false,multiline:true" style="height: 70px; width: 100%;">
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="popup.close(window.name);" >
        <i class="iconfont icon-cancel"></i> 取消
    </button>
    <button  class=" button-small   pure-button pure-button-primary" onclick="saveAction()" >
        <i class="iconfont icon-save"></i> 确定
    </button>
</div>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/js/input2combotree.js"></script>
<script>
    initFormCombotree('#pid','${pid!}','${(sysNoteCate.id)!}','${ctx!}/sysNote/cateComboTree',true);
</script>
</@layout>
