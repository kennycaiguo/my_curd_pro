<#--地区数据-->
<#include "../common/common.ftl"/>
<@layout>
<link rel="stylesheet" href="${ctx!}/static/css/tree-icon.css">
<table id="tg" class="easyui-treegrid"
       url="${ctx!}/sysDataRegion/query"
       data-options="onLoadSuccess:expandChina,lines:true,animate:true"
       toolbar="#tb"
       border="false"
       fit="true"
       fitColumns="false"
       rownumbers="true"
       idField="ID" treeField="NAME"
       >
    <thead>
    <tr>
        <th field="NAME" width="200">名字</th>
        <th field="ID" width="100">地区代码</th>
        <th field="PINYIN" width="100">英文名称</th>
        <th field="LNG"  width="100" >经度</th>
        <th field="LAT"  width="100" >纬度</th>
        <th field="CITY_CODE" width="100">区号</th>
        <th field="ZIP_CODE" width="100">邮编</th>
        <th field="IS_DISABLE" width="100" formatter="disableFmt">状态</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a onclick="newModel('tg','${ctx!}/sysDataRegion/newModel', '700px', '400px')" href="#" class="easyui-linkbutton"  iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('tg','${ctx!}/sysDataRegion/newModel', '700px', '400px')" href="#" class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('tg','${ctx!}/sysDataRegion/deleteAction')" href="#" class="easyui-linkbutton  "  iconCls="iconfont icon-delete" plain="true">删除</a>
</div>
<script src="${ctx!}/static/js/tg-curd.js"></script>
<script>
    function disableFmt(val,row){
        if(val=="1"){
            return '<font color="red">禁用</font>';
        }
        return '正常';
    }
    function expandChina(){
        $("#tg").treegrid("expand","100000");
    }
</script>
</@layout>
