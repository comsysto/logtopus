
<%@ page import="com.comsysto.logtopus.LogEvent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'logEvent.label', default: 'LogEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span9">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="applicationName" title="${message(code: 'logEvent.applicationName.label', default: 'Application Name')}" />
						
							<g:sortableColumn property="applicationVersion" title="${message(code: 'logEvent.applicationVersion.label', default: 'Application Version')}" />
						
							<g:sortableColumn property="exceptionName" title="${message(code: 'logEvent.exceptionName.label', default: 'Exception Name')}" />
						
							<g:sortableColumn property="hostIp" title="${message(code: 'logEvent.hostIp.label', default: 'Host Ip')}" />
						
							<g:sortableColumn property="hostName" title="${message(code: 'logEvent.hostName.label', default: 'Host Name')}" />
						
							<g:sortableColumn property="level" title="${message(code: 'logEvent.level.label', default: 'Level')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${logEventInstanceList}" var="logEventInstance">
						<tr>
						
							<td>${fieldValue(bean: logEventInstance, field: "applicationName")}</td>
						
							<td>${fieldValue(bean: logEventInstance, field: "applicationVersion")}</td>
						
							<td>${fieldValue(bean: logEventInstance, field: "exceptionName")}</td>
						
							<td>${fieldValue(bean: logEventInstance, field: "hostIp")}</td>
						
							<td>${fieldValue(bean: logEventInstance, field: "hostName")}</td>
						
							<td>${fieldValue(bean: logEventInstance, field: "level")}</td>
						
							<td class="link">
								<g:link action="show" id="${logEventInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${logEventInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
