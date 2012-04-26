
<%@ page import="com.comsysto.logtopus.LogEvent" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'logEvent.label', default: 'LogEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-logEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-logEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list logEvent">
			
				<g:if test="${logEventInstance?.applicationId}">
				<li class="fieldcontain">
					<span id="applicationId-label" class="property-label"><g:message code="logEvent.applicationId.label" default="Application Id" /></span>
					
						<span class="property-value" aria-labelledby="applicationId-label"><g:fieldValue bean="${logEventInstance}" field="applicationId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.level}">
				<li class="fieldcontain">
					<span id="level-label" class="property-label"><g:message code="logEvent.level.label" default="Level" /></span>
					
						<span class="property-value" aria-labelledby="level-label"><g:fieldValue bean="${logEventInstance}" field="level"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.message}">
				<li class="fieldcontain">
					<span id="message-label" class="property-label"><g:message code="logEvent.message.label" default="Message" /></span>
					
						<span class="property-value" aria-labelledby="message-label"><g:fieldValue bean="${logEventInstance}" field="message"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="logEvent.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${logEventInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.timestamp}">
				<li class="fieldcontain">
					<span id="timestamp-label" class="property-label"><g:message code="logEvent.timestamp.label" default="Timestamp" /></span>
					
						<span class="property-value" aria-labelledby="timestamp-label"><g:formatDate date="${logEventInstance?.timestamp}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${logEventInstance?.id}" />
					<g:link class="edit" action="edit" id="${logEventInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
