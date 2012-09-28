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
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"/>
    <![endif]-->
    <r:layoutResources/>

    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" sizes="144x144" href="${resource(dir: 'images', file: 'logtopus_icon_small.png')}">
    %{--Actual Page header stuff--}%
    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="${createLink(uri: '/')}">Logtopus</a>
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
</div>

<div class="container-fluid">
    <hr>
    <footer>
        <p class="pull-right">&copy; comSysto 2012 - Licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache License, Version 2.0</a></p>
    </footer>
</div>

<r:layoutResources/>

</body>
</html>