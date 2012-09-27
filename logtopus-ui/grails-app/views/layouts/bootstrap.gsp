<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
<head>
    <r:require modules="scaffolding"/>
    <r:require modules="bootstrap"/>
    <title><g:layoutTitle default="Logtopus" /></title>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="initial-scale = 1.0">

    <!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <r:layoutResources/>

    %{--TODO: Insert Logtopus Icon--}%
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">

    %{--Actual Page header stuff--}%
    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="${createLink(uri: '/')}"><i class="icon-search icon-white"></i>Logtopus</a>
            <ul class="nav">
                <li<%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/')}">Dashboard</a></li>
                <li<%= request.forwardURI == "${createLink(uri: '/about')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/about')}">About</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    %{--Actual Page content comes here--}%
    <g:layoutBody/>
    <hr>
    <footer style="float: right;">
        <p>&copy; comSysto 2012 - Licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache License, Version 2.0</a></p>
    </footer>
</div>

<r:layoutResources/>

</body>
</html>