<#macro layout>
<@compress single_line=true>
<!DOCTYPE html>
<html lang="zh-CN" >
<head>
    <meta charset="utf-8">
    <#-- 浏览器兼容性 -->
    <meta name="renderer" content="webkit"/>
    <meta name="force-rendering" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta content=always name=referrer>
    <link rel="shortcut icon" href="${ctx!}/static/image/favicon.ico"/>
    <title>你好！ ${(username)!}</title>
    <#include "easyui.ftl"/>
    <#include "custom.ftl"/>
    <#include "popup.ftl"/>
</head>
<body>
    <#nested>
</body>
</html>
</@compress>
</#macro>