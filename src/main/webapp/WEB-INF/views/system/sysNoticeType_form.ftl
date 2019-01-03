<#include "../common/common.ftl"/><@layout>
<script>
    function saveAction() {
        var url;
        var idVal = $('#id').val();

        if (isEmpty(idVal)) {
            url = '${ctx!}/sysNotificationType/addAction';
        } else {
            url = '${ctx!}/sysNotificationType/updateAction';
        }

        $('#modelForm').form('submit', {
            url: url,
            onSubmit: function() {
                return $(this).form('validate');
            },
            success: function (data) {
                parent.popup.msg(data, function () {
                    parent.$("#dg").datagrid("reload");
                    parent.layer.close(parent.layer.getFrameIndex(window.name));
                });
            }
        });
    }
</script>
</head>
<body style="padding: 10px 30px; ">
<form id="modelForm" method="POST" action="<#if sysNoticeType?? >${ctx!}/sysNoticeType/updateAction<#else>${ctx!}/sysNoticeType/addAction</#if>" >
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable" >
        <input id="id" name="id"  type="hidden" value="${(sysNoticeType.id)!}">
        <tbody>
            <tr>
                <td>分类:</td>
                <td colspan="3">
                    <input name="cate" value="${(sysNoticeType.cate)!}" class="easyui-textbox"   data-options="required:true"  >
                </td>
            </tr>
            <tr>
                <td>
                    名称:
                </td>
                <td>
                    <input name="name" value="${(sysNoticeType.name)!}" class="easyui-textbox"  data-options="required:true">
                </td>
                <td>
                    编码:
                </td>
                <td>
                    <input name="code" value="${(sysNoticeType.code)!}" class="easyui-textbox"  data-options="required:true,validType:['length[5,20]']">
                </td>
            </tr>
            <tr>
                <td>图片Logo:</td>
                <td colspan="3">
                    <input name="logo" value="${(sysNoticeType.logo)!}" class="easyui-textbox"  data-options="required:true" style="width: 100%;"   >
                </td>
            </tr>
            <tr>
                <td>
                    过期天数:
                </td>
                <td>
                    <input name="untilExpiryDay" value="${(sysNoticeType.untilExpiryDay)!}" class="easyui-numberbox"  data-options="required:true,min:0,precision:0">
                </td>

                <td>
                    删除天数:
                </td>
                <td>
                    <input name="untilDeadDay" value="${(sysNoticeType.untilDeadDay)!}" class="easyui-numberbox"  data-options="required:true,min:0,precision:0">
                </td>
            </tr>
            <tr>
                <td>
                    备注:
                </td>
                <td colspan="3">
                    <input name="remark" value="${(sysNoticeType.remark)!}" class="easyui-textbox" style="width: 100%;height: 70px;"
                           data-options="required:true,multiline:true">
                </td>
            </tr>
            <tr>
                <td>
                    消息模板:
                </td>
                <td colspan="3">
                    <input name="template" value="${(sysNoticeType.template?js_string)!}" class="easyui-textbox"   style="width: 100%;height: 80px;"
                           data-options="required:true,multiline:true">
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
