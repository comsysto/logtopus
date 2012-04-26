
<%@ page import="com.comsysto.logtopus.LogEvent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
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
					
						<g:sortableColumn property="applicationId" title="${message(code: 'logEvent.applicationId.label', default: 'Application Id')}" />
					
						<g:sortableColumn property="level" title="${message(code: 'logEvent.level.label', default: 'Level')}" />
					
						<g:sortableColumn property="message" title="${message(code: 'logEvent.message.label', default: 'Message')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'logEvent.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="timestamp" title="${message(code: 'logEvent.timestamp.label', default: 'Timestamp')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${logEventInstanceList}" status="i" var="logEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${logEventInstance.id}">${fieldValue(bean: logEventInstance, field: "applicationId")}</g:link></td>
					
						<td>${fieldValue(bean: logEventInstance, field: "level")}</td>
					
						<td>${fieldValue(bean: logEventInstance, field: "message")}</td>
					
						<td>${fieldValue(bean: logEventInstance, field: "name")}</td>
					
						<td><g:formatDate date="${logEventInstance.timestamp}" /></td>
					
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