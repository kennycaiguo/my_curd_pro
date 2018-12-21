<#include "../common/common.ftl"/><@layout>
<form id="modelForm" method="POST" <#if !sysFile??>enctype="multipart/form-data"</#if>
      action="<#if sysFile?? >${ctx!}/sysFile/updateAction<#else>${ctx!}/sysFile/addAction</#if>" >
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="id" type="hidden" value="${(sysFile.id)!}">
        <tbody id="tbody">
        <#if !sysFile??>
            <tr>
                <td>文件：</td>
                <td>
                    <input style="width: 100%;" name="file" class="easyui-filebox"
                           data-options="required:true, buttonText: '选择文件', buttonAlign: 'right'">
                </td>
            </tr>
        </#if>
        <tr>
            <td>文件备注：</td>
            <td>
                <input style="width: 300px; height: 100px" name="remark" value="${(sysFile.remark)!}"
                       class="easyui-textbox" data-options="multiline:true ">
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="popup.close(window.name);" >
        <i class="iconfont icon-cancel"></i> 取消
    </button>
    <button  class=" button-small   pure-button pure-button-primary" onclick="saveAction('modelForm','reload','dg')" >
        <i class="iconfont icon-save"></i> 确定
    </button>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>
