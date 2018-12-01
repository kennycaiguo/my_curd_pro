<#--数据字典分组 表单-->
<#include "../common/common.ftl"/>
<@layout>
<style>
    .button-primary,
    .button-success,
    .button-error,
    .button-warning,
    .button-secondary {
        color: white;
        border-radius: 0px;
        text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
    }
    .button-primary{
        background: #1E9FFF;
    }
    .button-success {
        background: rgb(28, 184, 65);
    }
    .button-error {
        background: rgb(202, 60, 60);
    }
    .button-warning {
        background: rgb(223, 117, 20);
    }
    .button-secondary {
        background: rgb(66, 184, 221);
    }
    .button-xsmall {
        font-size: 70% !important;
    }
    .button-small {
        font-size: 85% !important;
    }
    .button-large {
        font-size: 110% !important;
    }
    .button-xlarge {
        font-size: 125% !important;
    }
    .button-xsmall>i.iconfont {
        font-size: 70%;
    }
    .button-small>i.iconfont {
        font-size: 85%;
    }
    .button-largel>i.iconfont {
        font-size: 110%;
    }
    .button-xlarge>i.iconfont {
        font-size: 125%;
    }
</style>
<form id="modelForm" method="POST" >
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="id" type="hidden" value="${(dataDict.id)!}">
        <tbody>
        <tr>
            <td>类型：</td>
            <td>
                <input name="dictType" value="${(dataDict.dictType)!}"  class="easyui-textbox" data-options="required:true">
            </td>
        </tr>
        <tr>
            <td>键：</td>
            <td>
                <input name="dictKey" value="${(dataDict.dictKey)!}"   class="easyui-textbox"  data-options="required:true">
            </td>
        </tr>
        <tr>
            <td>值：</td>
            <td>
                <input name="dictValue" value="${(dataDict.dictValue)!}"   class="easyui-textbox" data-options="required:true">
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="parent.layer.close(parent.layer.getFrameIndex(window.name));" >
        <i class="iconfont icon-cancel"></i>
        取消
    </button>
    <button  class=" button-small   pure-button pure-button-primary" onclick="saveAction()" >
        <i class="iconfont icon-save"></i>
        确定
    </button>
</div>
</@layout>
