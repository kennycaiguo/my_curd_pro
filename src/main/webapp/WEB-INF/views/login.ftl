<@compress single_line=true><!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>用户登录</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="x-ua-compatible" content="ie=edge,chrome=1">
    <meta content=always name=referrer>
    <link rel="shortcut icon" href="${ctx!}/static/image/favicon.ico"/>
    <link rel="stylesheet" href="${ctx!}/static/plugins/purecss/pure-min.css">
    <link rel="stylesheet" href="${ctx!}/static/plugins/magic-check/magic-check.css">
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctx!}/static/plugins/purecss/grids-responsive-old-ie-min.css">
    <![endif]-->
    <!--[if gt IE 8]><!-->
    <link rel="stylesheet" href="${ctx!}/static/plugins/purecss/grids-responsive-min.css">
    <!--<![endif]-->
    <!--[if lt IE 9]>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.js"></script>
    <![endif]-->
    <style>
        * {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
        }

        body, html {
            height: 100%;
            width: 100%;
            overflow: hidden;
            /*子元素垂直居中*/
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .bg1 {
            background: url("${ctx!}/static/image/login_bg.svg");
            background-repeat: no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-position: center 0;
        }

        .form-signin {
            opacity: .7;
            padding: 15px 35px 45px;
            background-color: #fff;
            border: 1px solid rgba(0, 0, 0, 0.1);
            transition: .3s;
            -webkit-transition: .3s;
            -moz-transition: .3s;
        }

        .form-signin:hover {
            opacity: 1;
            box-shadow: inset lightgrey 0px 0px 5px 0px;
        }

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            font-weight: normal;
        }

        .form-signin .pure-checkbox {
            margin-bottom: 30px;
            font-size: 14px;
        }

        .button-large {
            font-size: 115%;
            margin-top: .7rem;
        }

        .warning-msg {
            color: #ff0000;
            font-weight: bold;
            padding-left: 10px;
        }

        .errorm-msg {
            color: #a94442;
            font-size: 14px;
        }

        .hiddenCls {
            display: none;
        }
    </style>
</head>
<body id="body" class="bg1">
<div class="pure-g">
    <div class="pure-u-1 pure-u-md-4-24  pure-u-lg-9-24"></div>
    <div class="pure-u-1 pure-u-md-16-24 pure-u-lg-6-24">
        <div id="loginFormWraper" style="margin: -4rem 1.5rem 0;">
            <form class="pure-form form-signin " action="${ctx!}/login/action" method="post">
                <h2 class="form-signin-heading">
                    用户 登录
                </h2>
                <fieldset class="pure-group">
                    <input type="text" name="username" class="pure-input-1" placeholder="用户名" value="${username!}"
                           tabindex="1">
                    <input type="password" name="password" class="pure-input-1" placeholder="密码" tabindex="2">
                </fieldset>
                <div class="errorm-msg">${errMsg!}</div>
                <input id="remember" name="remember" class="magic-checkbox" type="checkbox">
                <label for="remember"
                       onmouseenter="document.getElementsByClassName('warning-msg')[0].classList.remove('hiddenCls');"
                       onmouseleave="document.getElementsByClassName('warning-msg')[0].classList.add('hiddenCls');">
                    记住我(24h)<span class="warning-msg hiddenCls">公共电脑慎用此功能!</span>
                </label>
                <button type="submit" class="pure-button pure-input-1 pure-button-primary button-large">登录</button>
            </form>
        </div>
    </div>
    <div class="pure-u-1 pure-u-md-4-24 pure-u-lg-9-24"></div>
</div>
<script>
    /*不被 iframe 嵌套, 如果被嵌套，父页面跳转到登录页面*/
    if (top.location != self.location) {
        top.location = "${ctx!}/login";
    }
</script>
</body>
</html>
</@compress>