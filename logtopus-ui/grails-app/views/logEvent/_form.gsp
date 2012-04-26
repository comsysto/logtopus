<%@ page import="com.comsysto.logtopus.LogEvent" %>



<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'level', 'error')} ">
	<label for="level">
		<g:message code="logEvent.level.label" default="Level" />
		
	</label>
	<g:textField name="level" value="${logEventInstance?.level}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="logEvent.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${logEventInstance?.message}" />
</div>

<div class="fieldcontain ${hasErrors(bean: logEventInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="logEvent.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${logEventInstance?.name}" />
</div>

