<#include "../common/pure-page.ftl"/>
<@layout>
<#include "../common/simditor.ftl"/>
<style>
    body,html{
        background-color: #ffffff;
        width: 100%;
        height: 100%;
    }
    .formWraper{
        height: 48px;
        border-bottom: 1px solid lightgray;
    }
    .pure-form .title input{
        width: 92%;
        font-size:21px;
        font-weight: 400;
        border:none;
        box-shadow: none;
    }
    .pure-form select{
        height: 2.45em;
    }
    .simditor{
       border: none !important;
    }
</style>
<script>
    function saveNoteAction() {
        $.ajax({
            url:'${ctx!}/sysNote/updateNoteAction',
            type:'post',
            dataType:'json',
            data:{
                id:'${(sysNote.id)!}',
                title:$('#titleInput').val(),
                userId:'${(sysNote.userId)!}',
                content:editor.getValue()
            },
            success:function(data){
                if(data.state==='ok'){
                    popup.msg(data.msg,function(){
                        parent.$('#dg2').datagrid('reload');
                    });
                }else if(data.state==='error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            },
            error:function (x,hr,r) {
                popup.errMsg();
            }
        });
    }
</script>
<div class="formWraper">
    <form id="form" class="pure-form">
        <div class="title">
            <input id="titleInput" type="text" placeholder="输入笔记标题" value="${(sysNote.title)!}">
            <button id="saveBtn" type="button" class="pure-button  button-small" onclick="saveNoteAction()">保存</button>
        </div>
    </form>
</div>
<textarea id="editor">
    ${(sysNote.content?replace("_NEWLINE_","\r\n"))!}
</textarea>
<script>
    /*code 按钮有bug，不使用*/
    var editor = new Simditor({
        textarea: $('#editor'),
        defaultImage: '${ctx!}/static/image/favicon.ico',
        toolbar:['title','bold','italic','underline','strikethrough','fontScale','color','ol'
            , 'ul','blockquote' /*,'code'*/,'table','link','image','hr','indent','outdent','alignment','|','mark', 'emoji','html'],
        emoji:{
            imagePath: '${ctx!}/static/plugins/simditor-2.3.23/emoji/image/'
        }
    });
</script>
</@layout>
