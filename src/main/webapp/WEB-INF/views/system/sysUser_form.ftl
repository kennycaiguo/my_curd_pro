<#--用户表单-->
<#include "../common/common.ftl"/>
<@layout>
<form id="modelForm" method="POST"
      action="<#if sysUser?? >${ctx!}/sysUser/updateAction<#else>${ctx!}/sysUser/addAction</#if>">
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="id" type="hidden" value="${(sysUser.id)!}">
        <tbody>
        <tr>
            <td>部门：</td>
            <td>
                <input id="orgId" name="orgId" value="${(sysUser.orgId)!}">
            </td>
            <td>用户名：</td>
        <#if sysUser?? >
            <td title="不可修改">
                <input name="username" value="${(sysUser.username)!}" class="easyui-textbox"
                       data-options="required:true" readonly="true">
            </td>
        <#else >
            <td>
                <input name="username" value="${(sysUser.username)!}" class="easyui-textbox"
                       data-options="required:true">
            </td>
        </#if>
        </tr>
        <tr>
            <td>姓名：</td>
            <td>
                <input name="name" value="${(sysUser.name)!}" class="easyui-textbox" data-options="required:true">
            </td>
            <td>性别：</td>
            <td>
                <input name="gender" value="${(sysUser.gender)!}" class="easyui-combobox"
                       data-options="required:false,panelHeight:'auto',valueField:'VALUE',textField:'LABEL',url:'${ctx!}/sysDict/combobox?groupCode=gender'">
            </td>
        </tr>
        <tr>
            <td>职位：</td>
            <td>
                <input name="job" value="${(sysUser.job)!}" class="easyui-textbox" data-options="required:true">
            </td>
            <td>级别：</td>
            <td>
                <input name="jobLevel" value="${(sysUser.jobLevel)!}" class="easyui-combobox"
                       data-options="required:true,panelHeight:'auto',valueField:'VALUE',textField:'LABEL',url:'${ctx!}/sysDict/combobox?groupCode=jobLevel'">
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>
                <input name="email" value="${(sysUser.email)!}" class="easyui-textbox" data-options="required:true">
            </td>
            <td>电话：</td>
            <td>
                <input name="phone" value="${(sysUser.phone)!}" class="easyui-textbox" data-options="required:true">
            </td>
        </tr>
        <tr>
            <td>状态：</td>
            <td colspan="3">
                <input name="userState" value="${(sysUser.userState)!}" class="easyui-combobox"
                       data-options="required:true,panelHeight:'auto',valueField:'VALUE',textField:'LABEL',url:'${ctx!}/sysDict/combobox?groupCode=userState'">
            </td>
        </tr>
        <#--<tr>-->
        <#--<td>头像：</td>-->
        <#--<td colspan="3">-->
        <#--<input name="avatar" value="${(sysUser.avatar)!}"   class="easyui-textbox"  style="width: 100%;" >-->
        <#--</td>-->
        <#--</tr>-->

        </tbody>
    </table>
</form>
<div class="formBtnsDiv">
    <button class=" pure-button button-small" onclick="popup.close(window.name);">
        <i class="iconfont icon-cancel"></i> 取消
    </button>
    <button class=" button-small   pure-button pure-button-primary" onclick="saveAction('modelForm','reload','dg')">
        <i class="iconfont icon-save"></i> 确定
    </button>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/js/input2combotree.js"></script>
<script>
    initFormCombotree('#orgId', '${(sysUser.orgId)!}', '', '${ctx!}/sysOrg/orgComboTree?withRoot=false', false);
</script>
</@layout>
