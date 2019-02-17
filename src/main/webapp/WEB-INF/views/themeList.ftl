<#include "common/pure-page.ftl"/>
<@layout>
<style>
    .colorArea{
        height: 60px;
        line-height: 60px;
        text-align: center;
        color: #ffffff;
        transition: .3s;
    }
    .colorArea:hover{
        -webkit-box-shadow: inset 0 0 5px #ffffff;
        -moz-box-shadow: inset 0 0 5px  #ffffff;
        box-shadow: inset 0 0 5px  #ffffff;
    }
    .colorArea.active:after{
        content: '当前';
    }
</style>
<div class="pure-g">
    <#list colorList as item>
        <div onclick="setTheme('${item.VALUE}','${item.COLOR}',this)" class="pure-u-1-2 colorArea  <#if theme == item.VALUE>active</#if>" title="${item.LABEL}"  style="background-color:${item.COLOR};">
        </div>
    </#list>
</div>
<script>
    function setTheme(colorName,color,o){
        console.log(colorName);
        console.log(color);
        $.ajax({
            url: '${ctx!}/dashboard/themeSet',
            data:{
                colorName:colorName,
                color:color
            },
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                if(data.state === 'ok'){
                    popup.msg(data.msg,function () {
                         $('.colorArea.active').removeClass('active');
                         $(o).addClass('active');
                         top.window.location.reload();
                    });
                }else if(data.state === 'error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            },
            error:function(x,h,r){
                console.log(x);
                popup.errMsg();
            }
        })
    }
</script>
</@layout>