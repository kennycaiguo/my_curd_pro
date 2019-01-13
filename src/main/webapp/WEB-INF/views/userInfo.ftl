<#include "common/pure-page.ftl"/>
<@layout>
<style>
    .avatarWrap{
        width: 80px;
        height: 80px;
        margin: 0 auto;
        overflow: hidden;
        -webkit-border-radius: 50%;
        -moz-border-radius: 50%;
        border-radius: 50%;
        transition: .3s;
        -webkit-transition: .3s;
        -moz-transition: .3s;
    }
    .avatarWrap:hover{
        background-color: #ffffff;
    }
    .avatarWrap img:hover{
        opacity: .4;
    }
</style>
<div style="padding: 5px;"></div>
<div id="avatar" class="avatarWrap" onclick="openSelectImg()">
    <a href="javascript:void(0)"  title="点击修改头像">
          <#if (sysUser.gender)=='M' >
              <img id="avatarImg" src="${ctx!}/${(sysUser.avatar)!'static/image/male.jpg'}" class="pure-img" alt="${(sysUser.username)!} 头像">
          </#if>
          <#if (sysUser.gender)=='F' >
              <img id="avatarImg" src="${ctx!}/${(sysUser.avatar)!'static/image/female.jpg'}" class="pure-img" alt="${(sysUser.username)!} 头像">
          </#if>
    </a>
</div>
<input type="file" id="fileElem" multiple accept="image/*" style="display:none" onchange="selectImg(this.files)" />
<script>
    /*打开选择文件*/
    function openSelectImg(){
        $("#fileElem").click();
    }
    /*选中文件后上传文件*/
    function selectImg(files){
        var file = files[0];
        if (!file.type.match('image.*')) {
            return false;
        }
        var form_data = new FormData();
        form_data.append("file", file);
        form_data.append("remark", "${(sysUser.username)!} 头像");
        $.ajax({
            type: "POST",
            url: "${ctx!}/utils/uploadFile",
            dataType : "json",
            crossDomain: true,   /* 如果用到跨域，需要后台开启CORS */
            processData: false,  /* 注意：不要 process data */
            contentType: false,  /* 注意：不设置 contentType */
            data: form_data,
            success:function(data){
                if(data.state === 'ok'){
                    $("#avatarImg").attr("src","${ctx!}/"+data.path);
                    $("#avatarInput").val(data.path);
                }else if(data.state === 'error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            },
            fail:function (x) {
                popup.errMsg();
            }
        });
    }
</script>
<div class="pure-g">
    <div class="pure-u-1" style="padding:10px 15px;">
        <form class="pure-form">
            <input type="hidden" name="userId" value="${(sysUser.id)!}">
            <input id="avatarInput" type="hidden" name="avatar" value="${(sysUser.avatar)!}">
            <fieldset class="pure-group">
                <input class="pure-input-1" id="username" type="text" placeholder="登录名" value="${(sysUser.username)!}"  readonly>
                <input class="pure-input-1" id="org" type="text" placeholder="部门" value="${(orgName)!}"  readonly>
            </fieldset>
            <fieldset class="pure-group">
                <input class="pure-input-1" id="name" name="name" type="text" placeholder="姓名" value="${(sysUser.name)!}"  >
                <input class="pure-input-1" id="job" name="name" type="text" placeholder="职位" value="${(sysUser.job)!}"  >
                <input class="pure-input-1" id="phone" name="phone" type="text" placeholder="手机号" value="${(sysUser.phone)!}">
                <input class="pure-input-1" id="email" name="email" type="text" placeholder="邮箱" value="${(sysUser.email)!}">
            </fieldset>
            <fieldset class="pure-group">
                <select id="gender" name="gender" class="pure-input-1" style="height: 2.45em;">
                    <option disabled selected>选择性别</option>
                    <option value="M">男</option>
                    <option value="F">女</option>
                </select>
                <script>
                    (function () {
                        $('#gender').val('${(sysUser.gender)!}');
                    })();
                </script>
            </fieldset>
            <button id="subBtn" type="button" class="pure-button button-small  pure-input-1 pure-button-primary">修改</button>
        </form>
    </div>
</div>
<script>
    $("#subBtn").click(function () {
        $.ajax({
            url: '${ctx!}/dashboard/changeUserInfo',
            data: $("form").serialize(),
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                if(data.state === 'ok'){
                    popup.msg(data.msg,function () {
                        window.location.reload();
                    });
                }else if(data.state === 'error'){
                    popup.errMsg('系统异常',data.msg);
                }else{
                    popup.msg(data.msg);
                }
            },
            error:function(x,h,r){
                popup.errMsg(x,h);
            }
        })
    });
</script>
</@layout>