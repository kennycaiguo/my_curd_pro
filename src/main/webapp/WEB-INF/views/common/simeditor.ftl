<#--pure css simeditor-->
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
    <link rel="stylesheet" href="${ctx!}/static/plugins/purecss/pure-min.css">
    <link rel="stylesheet" href="${ctx!}/static/css/custom-ui.css">
    <link rel="stylesheet" href="${ctx!}/static/css/custom-theme-green.css">
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctx!}/static/plugins/purecss/grids-responsive-old-ie-min.css">
    <![endif]-->
    <!--[if gt IE 8]><!-->
    <link rel="stylesheet" href="${ctx!}/static/plugins/purecss/grids-responsive-min.css">
    <!--<![endif]-->
    <!--[if lt IE 9]>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7/html5shiv.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${ctx!}/static/plugins/simditor-2.3.23/simditor.css" />
    <script type="text/javascript" src="${ctx!}/static/plugins/simditor-2.3.23/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx!}/static/plugins/simditor-2.3.23/module.js"></script>
    <script type="text/javascript" src="${ctx!}/static/plugins/simditor-2.3.23/hotkeys.js"></script>
    <script type="text/javascript" src="${ctx!}/static/plugins/simditor-2.3.23/uploader.js"></script>
    <script type="text/javascript" src="${ctx!}/static/plugins/simditor-2.3.23/simditor.js"></script>
    <#include "popup.ftl"/>
</head>
    <#nested>
</html>
</@compress>
</#macro>