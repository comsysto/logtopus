
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
				<li><g:link class="list" action="filterList"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-logEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list logEvent">
			
				<g:if test="${logEventInstance?.applicationName}">
				<li class="fieldcontain">
					<span id="applicationName-label" class="property-label"><g:message code="logEvent.applicationName.label" default="Application Name" /></span>
					
						<span class="property-value" aria-labelledby="applicationName-label"><g:fieldValue bean="${logEventInstance}" field="applicationName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.applicationVersion}">
				<li class="fieldcontain">
					<span id="applicationVersion-label" class="property-label"><g:message code="logEvent.applicationVersion.label" default="Application Version" /></span>
					
						<span class="property-value" aria-labelledby="applicationVersion-label"><g:fieldValue bean="${logEventInstance}" field="applicationVersion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.hostIp}">
				<li class="fieldcontain">
					<span id="hostIp-label" class="property-label"><g:message code="logEvent.hostIp.label" default="Host Ip" /></span>
					
						<span class="property-value" aria-labelledby="hostIp-label"><g:fieldValue bean="${logEventInstance}" field="hostIp"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.hostName}">
				<li class="fieldcontain">
					<span id="hostName-label" class="property-label"><g:message code="logEvent.hostName.label" default="Host Name" /></span>
					
						<span class="property-value" aria-labelledby="hostName-label"><g:fieldValue bean="${logEventInstance}" field="hostName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.level}">
				<li class="fieldcontain">
					<span id="level-label" class="property-label"><g:message code="logEvent.level.label" default="Level" /></span>
					
						<span class="property-value" aria-labelledby="level-label"><g:fieldValue bean="${logEventInstance}" field="level"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="logEvent.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${logEventInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.logger}">
				<li class="fieldcontain">
					<span id="logger-label" class="property-label"><g:message code="logEvent.logger.label" default="Logger" /></span>
					
						<span class="property-value" aria-labelledby="logger-label"><g:fieldValue bean="${logEventInstance}" field="logger"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.message}">
				<li class="fieldcontain">
					<span id="message-label" class="property-label"><g:message code="logEvent.message.label" default="Message" /></span>
					
						<span class="property-value" aria-labelledby="message-label"><g:fieldValue bean="${logEventInstance}" field="message"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.sha1}">
				<li class="fieldcontain">
					<span id="sha1-label" class="property-label"><g:message code="logEvent.sha1.label" default="Sha1" /></span>
					
						<span class="property-value" aria-labelledby="sha1-label"><g:fieldValue bean="${logEventInstance}" field="sha1"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${logEventInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="logEvent.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${logEventInstance?.time}" /></span>
					
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
