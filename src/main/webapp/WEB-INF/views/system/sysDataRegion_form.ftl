<#--地区数据表单-->
<#include "../common/common.ftl"/>
<@layout>
<script>
    function saveAction() {
        $('#modelForm').form('submit', {
            onSubmit: function() {
                return $(this).form('validate');
            },
            success: function (data) {
                if(typeof data ==='string'){
                    data = JSON.parse(data);
                }
                if(data.state === 'ok'){
                    popup.msg(data.msg, function () {
                        /*只刷新部分*/
                        window.parent.frames[sessionStorage.getItem("iframeId")].$("#tg").treegrid("reload",$('#parentId').numberbox('getValue'));
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
<form id="modelForm" method="POST" action="${ctx!}/sysDataRegion/addOrUpdateAction">
    <table class="pure-table pure-table-horizontal centerTable labelInputTable">
        <tbody>
        <tr>
            <td>
                地区代码:
            </td>
            <td>
                <input id="id" name="id" value="${(sysDataRegion.id)!}" class="easyui-numberbox"
                       data-options="required:true,precision:0">
            </td>
            <td>
                上级地区代码:
            </td>
            <td>
                <input id="parentId" name="parentId" value="${(sysDataRegion.parentId)!}" class="easyui-numberbox"
                       data-options="required:true,precision:0">
            </td>
        </tr>
        <tr>
            <td>
                名字:
            </td>
            <td>
                <input name="name" value="${(sysDataRegion.name)!}" class="easyui-textbox" data-options="required:true">
            </td>
            <td>
                英文名称:
            </td>
            <td>
                <input name="pinyin" value="${(sysDataRegion.pinyin)!}" class="easyui-textbox"
                       data-options="required:true">
            </td>
        </tr>
        <tr>
            <td>
                短名:
            </td>
            <td>
                <input name="shortName" value="${(sysDataRegion.shortName)!}" class="easyui-textbox"
                       data-options="required:true">
            </td>
            <td>
                地区级别:
            </td>
            <td>
                <input name="levelType" class="easyui-combobox"
                       data-options="
                             data: [ {value:'0',text:'国'} ,{value:'1',text:'省'},{value:'2',text:'市'},{value:'3',text:'县/区'}],
                             editable: false,
                             required:true,
                             panelHeight:'auto',
                             value:'${(sysDataRegion.levelType)!}'">
            </td>
        </tr>
        <tr>
            <td>
                区号:
            </td>
            <td>
                <input name="cityCode" value="${(sysDataRegion.cityCode)!}" class="easyui-textbox">
            </td>
            <td>
                邮政编码:
            </td>
            <td>
                <input name="zipCode" value="${(sysDataRegion.zipCode)!}" class="easyui-textbox">
            </td>
        </tr>
        <tr>
            <td>
                纬度:
            </td>
            <td>
                <input name="lat" value="${(sysDataRegion.lat)!}" class="easyui-textbox">
            </td>
            <td>
                经度:
            </td>
            <td>
                <input name="lng" value="${(sysDataRegion.lng)!}" class="easyui-textbox">
            </td>
        </tr>
        <tr>
            <td>
                路径名称:
            </td>
            <td>
                <input name="mergerName" value="${(sysDataRegion.mergerName)!}" class="easyui-textbox"
                       data-options="required:true">
            </td>
            <td>
                是否禁用:
            </td>
            <td>
                <input name="hasDisable" class="easyui-combobox"
                       data-options=" data: [ {value:'0',text:'否'} ,{value:'1',text:'是'}],
                            editable: false,  required:true,panelHeight:'auto',
                            value:'${(sysDataRegion.hasDisable)!}'">
            </td>
        </tr>
        </tbody>
    </table>
</form>
<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="popup.close(window.name);" >
        <i class="iconfont icon-cancel"></i> 取消
    </button>
    <button  class="button-small pure-button pure-button-primary" onclick="saveAction()" >
        <i class="iconfont icon-save"></i> 确定
    </button>
</div>
</@layout>
