<#include "../common/common.ftl"/> <@layout>
<table id="dg" class="easyui-datagrid"
       url="${ctx!}/sysFile/query"
       toolbar="#tb" rownumbers="false" border="false"
       fit="true" pagination="true"
       fitColumns="true"
       striped="true"
       ctrlSelect="true"
       multiSort="false"
       pageSize="40" pageList="[20,40]">
    <thead>
    <tr>
        <th data-options="field:'ID',checkbox:true"></th>
        <th align="center" field="img_view" width="60" formatter="fView"  >查看</th>
        <#if showUserSearch >
            <th align="center" field="USERNAME" width="100" formatter="usernameFmt">上传者</th>
        </#if>
        <th sortable="true" field="CREATE_TIME" width="160">上传时间</th>
        <th field="ORI_NAME" width="150" >文件原名</th>
        <th field="REMARK" width="200">文件备注</th>
        <#--<th align="center" field="TYPE" width="100" >文件类型</th>-->
        <th sortable="true" align="center" field="LENGTH" width="100" formatter="lengthFmt">文件大小(KB)</th>
        <th align="center" field="MIME" width="140">MIME</th>
        <th field="PATH" width="300"  >文件路径</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <a onclick="newModel('${ctx!}/sysFile/newModel',  '550px', '350px')" href="#" class="easyui-linkbutton"  iconCls="iconfont icon-add" plain="true">新增</a>
    <a onclick="editModel('dg','${ctx!}/sysFile/newModel', '550px', '320px')" href="#"  class="easyui-linkbutton" iconCls="iconfont icon-edit" plain="true">编辑</a>
    <a onclick="deleteModel('dg','${ctx!}/sysFile/deleteAction')" href="#" class="easyui-linkbutton  "  iconCls="iconfont icon-delete" plain="true">删除</a>

    <span id="searchSpan" class="searchInputArea">
          <input prompt="文件后缀" class="easyui-combobox" style="width:150px; "
                 data-options="valueField:'value', multiple:true,textField:'label',url:'${ctx!}/sysFile/fileTypeData',onChange:function(n){$('#hiddenTypeInput').val(n)}">

          <input id="hiddenTypeInput" type="hidden" name="search_INS_sf.type">
          <#if showUserSearch >
          <input name="search_LIKE_su.USERNAME" prompt="上传者" class="easyui-textbox" style="width:120px; ">
          </#if>
          <input name="search_LIKE_sf.ORI_NAME" prompt="文件原名" class="easyui-textbox" style="width:120px; ">
          <input name="search_LIKE_sf.REMARK" prompt="备注" class="easyui-textbox" style="width:120px; ">
          <input name="search_GTEDT_sf.CREATE_TIME" prompt="上传时间起" class="easyui-datetimebox"  >
          ~
          <input name="search_LTEDT_sf.CREATE_TIME" prompt="上传时间止" class="easyui-datetimebox"  >
          <a href="#" class="easyui-linkbutton searchBtn" data-options="iconCls:'iconfont icon-search',plain:true"
             onclick="queryModel('dg','searchSpan')">搜索</a>
    </span>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
<script>
    /* 图片格式化*/
    function fView(val, row) {
        var ctx = '${ctx!}/';
        if ('png,jpg,jpeg,gif,bmp'.indexOf(row.TYPE) >= 0) {
            return '<a target="_blank" title="点击查看大图" href="' + ctx + row.PATH + '"><img height="25px" src="' + ctx + row.PATH + '" alt="' + row.ORI_NAME + '"/></a>'
        }  else {
            return '<a target="_blank" title="点击查看" style="font-size:18px" href="' + ctx + row.PATH + '"><i class="iconfont icon-link"></i></a>'
        }
    }
    function pathFmt(val) {
        var curHref = window.document.location.href;
        var curPathname = window.document.location.pathname;
        var firstPos = curHref.indexOf(curPathname);
        curHref = curHref.substring(0, firstPos);
        return curHref + '/' + val;
    }
    function lengthFmt(val) {
        if(val!=null){
            /* 操作系统 的 舍去精度方法很智能，此处只是四舍五入*/
            val = (val/1024).toFixed(2); /* B -> KB*/
        }
        return val;
    }

    <#if showUserSearch >
    function usernameFmt(val,row) {
        return '<a title="点击查看人员信息" href="javascript:userInfo(\'${ctx!}\',\''+val+'\')" >'+val+'</a>';
    }
    </#if>
</script>
</@layout>
