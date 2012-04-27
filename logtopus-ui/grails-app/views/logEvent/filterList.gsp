
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

    <h1>LogEvent Filter</h1>
    <g:form action="filterList" method="post" >
        <div class="dialog">
            <table>
                <thead>
                <tr>
                    <td valign='top' class='name'>
                        <label for='level'>Level</label>
                    </td>
                    <td valign='top' class='name'>
                        <label for='hostName'>Host Name</label>
                    </td>
                    <td valign='top' class='name'>
                        <label for='applicationVersion'>Application Version</label>
                    </td>
                    <td valign='top' class='name'>
                        <label for='applicationName'>Application Name</label>
                    </td>
                    <td valign='top' class='name'>
                        <label for='logMessage'>Message:</label>
                    </td>
                </tr>
                </thead>
                <tbody>
                <tr class='prop'>
                    <td valign='top' class='value'>
                        <g:select name='level'
                                  noSelection="['':'']"
                                  value="${flash.level}"
                                  from='${LogEvent.allLogLevels()}'>
                        </g:select>
                    </td>
                    <td valign='top' class='value'>
                        <input type="text" id="hostName" name="hostName" value="${flash.hostName}"/>
                    </td>
                    <td valign='top' class='value'>
                        <input type="text" id="applicationVersion" name="applicationVersion" value="${flash.applicationVersion}"/>
                    </td>
                    <td valign='top' class='value'>
                        <input type="text" id="applicationName" name="applicationName" value="${flash.applicationName}"/>
                    </td>
                    <td valign='top' class='value'>
                        <input type="text" id="logMessage" name="logMessage" value="${flash.logMessage}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Search" /></span>
        </div>
    </g:form>

    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="level" title="${message(code: 'logEvent.level.label', default: 'Level')}" />

            <g:sortableColumn property="hostName" title="${message(code: 'logEvent.hostName.label', default: 'Host Name')}" />

            <g:sortableColumn property="applicationVersion" title="${message(code: 'logEvent.applicationVersion.label', default: 'Application Version')}" />

            <g:sortableColumn property="applicationName" title="${message(code: 'logEvent.applicationName.label', default: 'Application Name')}" />

            <g:sortableColumn property="message" title="${message(code: 'logEvent.message.label', default: 'Message')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${logEventInstanceList}" status="i" var="logEventInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${logEventInstance.id}">${fieldValue(bean: logEventInstance, field: "level")}</g:link></td>

                <td>${fieldValue(bean: logEventInstance, field: "hostName")}</td>

                <td>${fieldValue(bean: logEventInstance, field: "applicationVersion")}</td>

                <td>${fieldValue(bean: logEventInstance, field: "applicationName")}</td>

                <td>${fieldValue(bean: logEventInstance, field: "message")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${logEventInstanceList.getTotalCount()}" params="${flash}" />
    </div>
</div>
</body>
</html>