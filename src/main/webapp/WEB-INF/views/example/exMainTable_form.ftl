<#-- zhangchuang  2019-01-17 19:04:42 -->
<#include "../common/common.ftl"/>
<@layout>
<style>
    .sonTablePanel{
        padding: 5px;
    }
    .sonTablePanel>.addRowBtn{
        margin-bottom: 5px;
    }
    .sonTablePanel>.tpl{
        display: none;
    }
    .sonTablePanel table{
        width: 100%;
    }
</style>
<script>
    /*重新计算行号*/
    function buildRowNumber(dom){
      var trAry = $("tr",dom);
      var replaceAttr = ['name','textboxname','numberboxname','comboname','spinnername','slidername','checkboxname','radiobuttonname'];
      for(var i=0;i<trAry.length;i++){
          $('td.number',trAry[i]).text(i+1);
          var html = $(trAry[i]).html().replace(/\[(.+?)\]/g,'['+i+']');
          var inputAry = $('input',trAry[i]);
          for(var j =0;j<inputAry.length;j++){
              for(var x=0;x<replaceAttr.length;x++){
                  if($(inputAry[j]).attr(replaceAttr[x])!=undefined){
                      $(inputAry[j]).attr(replaceAttr[x],   $(inputAry[j]).attr(replaceAttr[x]).replace(/\[(.+?)\]/g,'['+i+']'));
                  }
              }
          }
      }
    }

    /**
     * 增行
     * @param o 增行按钮
     */
    function addRow(o){
        var tbody = $(o).parent().find("table>tbody");
        var tpl = $(o).parent().find(".tpl").html();
        tpl = tpl.replace(/tpltr/g,'tr').replace(/tpltd/g,'td').replace(/myinput/g,"input").replace(/myui/g,'easyui');
        tbody.append(tpl);
        $.parser.parse(tbody);
        buildRowNumber(tbody);
    }

    /**
     * 删行
     * @param o
     * @param url
     */
    function delRow(o,url) {
        var tr = $(o).parents('tr');
        var tbody =  $(o).parents('tbody');
        var idVal = tr.find("input[name*='.id']").first().val();
        if(notEmpty(idVal)){
            $.getJSON(url+"?id="+idVal,function (data) {
                if(data.state==='ok'){
                    tr.remove();
                    buildRowNumber(tbody);
                    popup.msg(data.msg);
                }else if(data.state==='error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            }).error(function () {
                popup.errMsg();
            });
        }else{
            tr.remove();
            buildRowNumber(tbody);
        }
    }
</script>
<form id="modelForm" method="POST" action="<#if exMainTable?? >${ctx!}/exMainTable/updateAction<#else>${ctx!}/exMainTable/addAction</#if>">
    <table class=" pure-table pure-table-horizontal centerTable labelInputTable">
        <input id="id" name="exMainTable.id"  type="hidden" value="${(exMainTable.id)!}">
        <tbody>
            <tr>
                <td>
                    姓名:
                </td>
                <td>
                    <input name="exMainTable.name" value="${(exMainTable.name)!}" class="easyui-textbox"  data-options="required:true">
                </td>
                <td>
                    性别:
                </td>
                <td>
                    <input name="exMainTable.gender" value="${(exMainTable.gender)!}" class="easyui-textbox"  data-options="required:true">
                </td>
            </tr>
        </tbody>
    </table>
    <div class="easyui-tabs" style="width:800px; height: auto;margin: 20px auto;"   >
        <div  title="子表1"  class="sonTablePanel">
            <a onclick="addRow(this)"   class="easyui-linkbutton addRowBtn" title="增行"  iconCls="iconfont icon-add" plain="true">增行</a>
            <div class="tpl">
                <tpltr>
                    <myinput name="exSonTable1[number].id"  type="hidden" >
                    <myinput name="exSonTable1[number].mainId" type="hidden" >
                    <tpltd class="number">1</tpltd>
                    <tpltd><myinput style="width: 157px;" name="exSonTable1[number].name"  class="myui-textbox"  data-options="required:true"></tpltd>
                    <tpltd><myinput style="width: 157px;" name="exSonTable1[number].xlevel" class="myui-textbox"  data-options="required:true"></tpltd>
                    <tpltd><a  onclick="delRow(this,'${ctx!}/exMainTable/deleteExSonTable1Action')" title="删行"  class="myui-linkbutton"  iconCls="iconfont icon-delete" plain="true"></a></tpltd>
                </tpltr>
            </div>
            <table  class="pure-table  pure-table-bordered" >
                <thead>
                <tr>
                    <th>序号</th>
                    <th>名称</th>
                    <th>等级</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
             <#if exSonTable1s??>
                 <#list exSonTable1s as item>
                     <tr>
                         <input name="exSonTable1[${item_index}].id"  type="hidden" value="${(item.id)!}">
                         <input name="exSonTable1[${item_index}].mainId" type="hidden" value="${(item.mainId)!}" >
                         <td class="number">${item_index+1}</td>
                         <td><input style="width: 157px;" name="exSonTable1[${item_index}].name" value="${(item.name)!}" class="easyui-textbox"  data-options="required:true"></td>
                         <td><input style="width: 157px;" name="exSonTable1[${item_index}].xlevel" value="${(item.xlevel)!}" class="easyui-textbox"  data-options="required:true"></td>
                         <td> <a  onclick="delRow(this,'${ctx!}/exMainTable/deleteExSonTable1Action')"  title="删行"   class="easyui-linkbutton"  iconCls="iconfont icon-delete" plain="true"></a></td>
                     </tr>
                 </#list>
             </#if>
                </tbody>
            </table>
        </div>
        <div  title="子表2"  class="sonTablePanel">
            <a onclick="addRow(this)"   class="easyui-linkbutton addRowBtn"  iconCls="iconfont icon-add" plain="true">增行</a>
            <div class="tpl">
                <tpltr>
                    <myinput name="exSonTable2[number].id"  type="hidden" >
                    <myinput name="exSonTable2[number].mainId" type="hidden" >
                    <tpltd class="number">1</tpltd>
                    <tpltd><myinput style="width: 157px;" name="exSonTable2[number].name"  class="myui-textbox"  data-options="required:true"></tpltd>
                    <tpltd><myinput style="width: 157px;" name="exSonTable2[number].xlevel" class="myui-textbox"  data-options="required:true"></tpltd>
                    <tpltd><a  onclick="delRow(this,'${ctx!}/exMainTable/deleteExSonTable2Action')"   title="删行"  class="myui-linkbutton"  iconCls="iconfont icon-delete" plain="true"></a></tpltd>
                </tpltr>
            </div>
            <table  class="pure-table  pure-table-bordered" >
                <thead>
                <tr>
                    <th>序号</th>
                    <th>名称</th>
                    <th>等级</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
             <#if exSonTable2s??>
                 <#list exSonTable2s as item>
                     <tr>
                         <input name="exSonTable2[${item_index}].id"  type="hidden" value="${(item.id)!}">
                         <input name="exSonTable2[${item_index}].mainId" type="hidden" value="${(item.mainId)!}" >
                         <td class="number">${item_index+1}</td>
                         <td><input style="width: 157px;" name="exSonTable2[${item_index}].name" value="${(item.name)!}" class="easyui-textbox"  data-options="required:true"></td>
                         <td><input style="width: 157px;" name="exSonTable2[${item_index}].xlevel" value="${(item.xlevel)!}" class="easyui-textbox"  data-options="required:true"></td>
                         <td><a  onclick="delRow(this,'${ctx!}/exMainTable/deleteExSonTable2Action')"  title="删行"  class="easyui-linkbutton"  iconCls="iconfont icon-delete" plain="true">删行</a></td>
                     </tr>
                 </#list>
             </#if>
                </tbody>
            </table>
        </div>
    </div>
</form>

<div  class="formBtnsDiv">
    <button  class=" pure-button button-small" onclick="popup.close(window.name);" >  <i class="iconfont icon-cancel"></i> 取消</button>
    <button  class=" button-small   pure-button pure-button-primary" onclick="saveAction('modelForm','reload','dg')" ><i class="iconfont icon-save"></i> 确定</button>
</div>
<script src="${ctx!}/static/js/dg-curd.js"></script>
</@layout>
