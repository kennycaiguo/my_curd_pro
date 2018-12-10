<#--角色表单-->
<#include "../common/common.ftl"/>
<@layout>
<form id="modelForm" method="POST" action="<#if sysRoleIncode?? >${ctx!}/sysRoleIncode/updateAction<#else>${ctx!}/sysRoleIncode/addAction</#if>">
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="id" type="hidden" value="${(sysRoleIncode.id)!}">
        <tbody>
        <tr>
            <td>名称：</td>
            <td>
                <input name="name" value="${(sysRoleIncode.name)!}"   class="easyui-textbox"  data-options="required:true"  style="width: 200px" >
            </td>
        </tr>
        <tr>
            <td>编码：</td>
            <td>
                <input name="code" value="${(sysRoleIncode.code)!}"   class="easyui-textbox" data-options="required:true"  style="width: 200px">
            </td>
        </tr>
        <tr>
            <td>说明：</td>
            <td>
                <input name="description" value="${(sysRoleIncode.description)!}"   class="easyui-textbox" data-options="multiline:true" style="width: 200px;height: 100px;">
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="parent.layer.close(parent.layer.getFrameIndex(window.name));" >
        <i class="iconfont icon-cancel"></i> 取消
    </button>
    <button  class=" button-small   pure-button pure-button-primary" onclick="saveAction('modelForm','reload','dg')" >
        <i class="iconfont icon-save"></i> 确定
    </button>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>
