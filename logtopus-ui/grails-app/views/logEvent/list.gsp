
<%@ page import="com.comsysto.logtopus.LogEvent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'logEvent.label', default: 'LogEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-logEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-logEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="applicationName" title="${message(code: 'logEvent.applicationName.label', default: 'Application Name')}" />
					
						<g:sortableColumn property="applicationVersion" title="${message(code: 'logEvent.applicationVersion.label', default: 'Application Version')}" />
					
						<g:sortableColumn property="hostIp" title="${message(code: 'logEvent.hostIp.label', default: 'Host Ip')}" />
					
						<g:sortableColumn property="hostName" title="${message(code: 'logEvent.hostName.label', default: 'Host Name')}" />
					
						<g:sortableColumn property="level" title="${message(code: 'logEvent.level.label', default: 'Level')}" />
					
						<g:sortableColumn property="location" title="${message(code: 'logEvent.location.label', default: 'Location')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${logEventInstanceList}" status="i" var="logEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${logEventInstance.id}">${fieldValue(bean: logEventInstance, field: "applicationName")}</g:link></td>
					
						<td>${fieldValue(bean: logEventInstance, field: "applicationVersion")}</td>
					
						<td>${fieldValue(bean: logEventInstance, field: "hostIp")}</td>
					
						<td>${fieldValue(bean: logEventInstance, field: "hostName")}</td>
					
						<td>${fieldValue(bean: logEventInstance, field: "level")}</td>
					
						<td>${fieldValue(bean: logEventInstance, field: "location")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${logEventInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
