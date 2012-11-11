
<%@ page import="com.comsysto.logtopus.LogLevelUtil; com.comsysto.logtopus.EventAggregate" %>
<!doctype html>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'aggregate.label', default: 'Stack')}" />
</head>
<body>

<div class="row-fluid">

    <div class="span12">
        %{--<div class="page-header">--}%
            <h2>Top 10 Issues</h2>
        %{--</div>--}%

        <g:if test="${flash.message}">
            <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
        </g:if>

        <table class="table">
            <thead>
            <tr>
                <g:sortableColumn property="applicationName" title="${message(code: 'logEvent.applicationName.label', default: 'Application Name')}" />
                <g:sortableColumn property="level" title="${message(code: 'logEvent.level.label', default: 'Level')}" />
                <g:sortableColumn property="exceptionName" title="${message(code: 'logEvent.exceptionName.label', default: 'Exception Name')}" />
                <g:sortableColumn property="n" title="${message(code: 'logEvent.n.label', default: 'Count')}" />

                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${top10List}" var="item">
                <tr style="background-color: ${LogLevelUtil.getColorCode(((EventAggregate)item).level)};">
                    <td>${fieldValue(bean: item, field: "applicationName")}</td>
                    <td>${fieldValue(bean: item, field: "level")}</td>
                    <td>${fieldValue(bean: item, field: "exceptionName")}</td>
                    <td>${fieldValue(bean: item, field: "n")}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>
