<#--笔记 移动-->
<#include "../common/common.ftl"/>
<@layout>
<form id="modelForm" method="POST" action="${ctx!}/sysNote/moveNoteAction">
    <table class=" pure-table pure-table-horizontal  labelInputTable" style="width: 100%;">
        <input id="id" name="id" type="hidden" value="${(sysNote.id)!}">
        <tbody>
        <tr>
            <td>文件夹：</td>
            <td>
                <input name="cateId"  id="cateId" data-options="panelHeight:'200px'">
            </td>
        </tr>
        <tr>
            <td>名称：</td>
            <td style="font-size: 14px;padding-left: 22px;">
               ${(sysNote.title)!}
            </td>
        </tr>
        <tr>
            <td>更新时间：</td>
            <td style="font-size: 14px;padding-left: 22px;">
                ${(sysNote.createTime)!}
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="popup.close(window.name);" >
        <i class="iconfont icon-cancel"></i> 取消
    </button>
    <button  class=" button-small pure-button pure-button-primary" onclick="saveAction('modelForm','reload','dg2')"  >
        <i class="iconfont icon-save"></i> 确定
    </button>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/js/input2combotree.js"></script>
<script>
    initFormCombotree('#cateId','${(sysNote.cateId)!}','','${ctx!}/sysNote/cateComboTree',true);
</script>
</@layout>
