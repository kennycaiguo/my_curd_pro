<#--角色表单-->
<#include "../common/common.ftl"/>
<@layout>
<form id="modelForm" method="POST" action="<#if sysUser?? >${ctx!}/sysUser/updateAction<#else>${ctx!}/sysUser/addAction</#if>">
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="id" type="hidden" value="${(sysUser.id)!}">
        <tbody>
        <tr>
            <td>用户名：</td>
            <td>
                <input name="username" value="${(sysUser.username)!}"   class="easyui-textbox"  data-options="required:true"    >
            </td>
            <td>密码：</td>
            <td>
                <input name="password" value="${(sysUser.password)!}"   class="easyui-textbox"  data-options="required:true"    >
            </td>
        </tr>
        <tr>
            <td>姓名：</td>
            <td>
                <input name="name" value="${(sysUser.name)!}"   class="easyui-textbox"  data-options="required:true"    >
            </td>
            <td>部门：</td>
            <td>
            <input id="orgId" name="orgId" value="${(sysUser.orgId)!}"     >
            </td>
        </tr>
        <tr>
            <td>状态：</td>
            <td>
                <input name="disable" value="${(sysUser.disable)!}"   class="easyui-textbox"  data-options="required:true"    >
            </td>
            <td>头像：</td>
            <td >
                <input name="avatar" value="${(sysUser.avatar)!}"   class="easyui-textbox" data-options="required:true"   >
            </td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td>
                <input name="email" value="${(sysUser.email)!}"   class="easyui-textbox"  data-options="required:true"    >
            </td>
            <td>电话：</td>
            <td>
                <input name="phone" value="${(sysUser.phone)!}"   class="easyui-textbox"  data-options="required:true"    >
            </td>
        </tr>
        <tr>
            <td>职位：</td>
            <td>
                <input name="job" value="${(sysUser.job)!}"   class="easyui-textbox"  data-options="required:true"    >
            </td>
            <td>级别：</td>
            <td>
                <input name="jobLevel" value="${(sysUser.jobLevel)!}"   class="easyui-textbox"  data-options="required:true"    >
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
<script src="${ctx!}/static/js/easyui-tree-tools.js"></script>
<script src="${ctx!}/static/js/input2combotree.js"></script>
<script>
    initFormCombotree('#orgId','${pid!}','${(sysOrg.id)!}','${ctx!}/sysOrg/orgComboTree');
</script>
</@layout>
