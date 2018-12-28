<#include "../common/simeditor.ftl"/>
<@layout>
<style>
    body,html{
        background-color: #ffffff;
        width: 100%;
        height: 100%;
    }
    .simditor{
        border: none !important;
    }
    #form{
        52px;
    }
    .pure-form .title{
        background-color: #ffffff;
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
</style>
<body>
<form id="form" class="pure-form">
    <div class="title">
        <input type="text" placeholder="输入笔记标题">
        <button type="submit" class="pure-button pure-button-primary button-small">保存</button>
    </div>
</form>
<textarea id="editor" placeholder="写笔记"  autofocus></textarea>
<script>
    var editor = new Simditor({
        textarea: $('#editor'),
        defaultImage: '${ctx!}/static/image/female.jpg',
        toolbar:['title','bold','italic','underline','strikethrough','fontScale','color','ol', 'ul','blockquote','code','table','link','image','hr','indent','outdent','alignment']
    });
</script>
</body>
</@layout>
