
<%@ page import="com.comsysto.logtopus.LogEvent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'logEvent.label', default: 'LogEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
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
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${logEventInstance?.applicationName}">
						<dt><g:message code="logEvent.applicationName.label" default="Application Name" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="applicationName"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.applicationVersion}">
						<dt><g:message code="logEvent.applicationVersion.label" default="Application Version" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="applicationVersion"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.exceptionName}">
						<dt><g:message code="logEvent.exceptionName.label" default="Exception Name" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="exceptionName"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.hostIp}">
						<dt><g:message code="logEvent.hostIp.label" default="Host Ip" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="hostIp"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.hostName}">
						<dt><g:message code="logEvent.hostName.label" default="Host Name" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="hostName"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.level}">
						<dt><g:message code="logEvent.level.label" default="Level" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="level"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.location}">
						<dt><g:message code="logEvent.location.label" default="Location" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="location"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.logger}">
						<dt><g:message code="logEvent.logger.label" default="Logger" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="logger"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.message}">
						<dt><g:message code="logEvent.message.label" default="Message" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="message"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.sha1}">
						<dt><g:message code="logEvent.sha1.label" default="Sha1" /></dt>
						
							<dd><g:fieldValue bean="${logEventInstance}" field="sha1"/></dd>
						
					</g:if>
				
					<g:if test="${logEventInstance?.time}">
						<dt><g:message code="logEvent.time.label" default="Time" /></dt>
						
							<dd><g:formatDate date="${logEventInstance?.time}" /></dd>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${logEventInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${logEventInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
