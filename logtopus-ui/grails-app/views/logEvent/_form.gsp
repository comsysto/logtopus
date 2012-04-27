<%@ page import="com.comsysto.logtopus.LogEvent" %>



<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'applicationName', 'error')} ">
	<label for="applicationName">
		<g:message code="logEvent.applicationName.label" default="Application Name" />
		
	</label>
	<g:textField name="applicationName" value="${logEventInstance?.applicationName}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'applicationVersion', 'error')} ">
	<label for="applicationVersion">
		<g:message code="logEvent.applicationVersion.label" default="Application Version" />
		
	</label>
	<g:textField name="applicationVersion" value="${logEventInstance?.applicationVersion}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'exceptionName', 'error')} ">
	<label for="exceptionName">
		<g:message code="logEvent.exceptionName.label" default="Exception Name" />
		
	</label>
	<g:textField name="exceptionName" value="${logEventInstance?.exceptionName}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'hostIp', 'error')} ">
	<label for="hostIp">
		<g:message code="logEvent.hostIp.label" default="Host Ip" />
		
	</label>
	<g:textField name="hostIp" value="${logEventInstance?.hostIp}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'hostName', 'error')} ">
	<label for="hostName">
		<g:message code="logEvent.hostName.label" default="Host Name" />
		
	</label>
	<g:textField name="hostName" value="${logEventInstance?.hostName}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'level', 'error')} ">
	<label for="level">
		<g:message code="logEvent.level.label" default="Level" />
		
	</label>
	<g:textField name="level" value="${logEventInstance?.level}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="logEvent.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${logEventInstance?.location}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'logger', 'error')} ">
	<label for="logger">
		<g:message code="logEvent.logger.label" default="Logger" />
		
	</label>
	<g:textField name="logger" value="${logEventInstance?.logger}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="logEvent.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${logEventInstance?.message}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'sha1', 'error')} ">
	<label for="sha1">
		<g:message code="logEvent.sha1.label" default="Sha1" />
		
	</label>
	<g:textField name="sha1" value="${logEventInstance?.sha1}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'time', 'error')} ">
	<label for="time">
		<g:message code="logEvent.time.label" default="Time" />
		
	</label>
	<g:datePicker name="time" precision="day" value="${logEventInstance?.time}" />
</div>

