<#--菜单 表单-->
<#include "../common/common.ftl"/>
<@layout>
<form id="modelForm" method="POST" action="<#if sysMenu?? >${ctx!}/sysMenu/updateAction<#else>${ctx!}/sysMenu/addAction</#if>">
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="id" type="hidden" value="${(sysMenu.id)!}">
        <tbody>
        <tr>
            <td>父机构：</td>
            <td>
                <input name="pid"  id="pid">
            </td>
        </tr>
        <tr>
            <td>名称：</td>
            <td>
                <input name="name" value="${(sysMenu.name)!}"   class="easyui-textbox"  data-options="required:true" >
            </td>
        </tr>
        <tr>
            <td>地址：</td>
            <td>
                <input name="url" value="${(sysMenu.url)!}"   class="easyui-textbox"  data-options="required:true" >
            </td>
        </tr>
        <tr>
            <td>图标：</td>
            <td>
                <input name="icon" value="${(sysMenu.icon)!}"   class="easyui-textbox"  data-options="required:true" >
            </td>
        </tr>
        <tr>
            <td>排序号：</td>
            <td>
                <input name="sort" value="${(sysMenu.sort)!}"   class="easyui-numberbox" data-options="precision:0,min:0">
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="popup.close(window.name);" >
        <i class="iconfont icon-cancel"></i> 取消
    </button>
    <button  class="button-small pure-button pure-button-primary" onclick="saveAction('modelForm','reload','tg')" >
        <i class="iconfont icon-save"></i> 确定
    </button>
</div>
<script src="${ctx!}/static/js/tg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/js/input2combotree.js"></script>
<script>
    initFormCombotree('#pid','${pid!}','${(sysMenu.id)!}','${ctx!}/sysMenu/menuComboTree');
</script>
</@layout>
