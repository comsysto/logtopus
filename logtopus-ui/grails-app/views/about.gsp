<!doctype html>
<html>
<body>
<div class="row-fluid">
    <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
            <h5>Application Status</h5>
            <ul>
                <li>App version: <g:meta name="app.version"/></li>
                <li>Grails version: <g:meta name="app.grails.version"/></li>
                <li>Groovy version: ${GroovySystem.getVersion()}</li>
                <li>JVM version: ${System.getProperty('java.version')}</li>
                <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
            </ul>
            <h5>Installed Plugins</h5>
            <ul>
                <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                    <li>${plugin.name} - ${plugin.version}</li>
                </g:each>
            </ul>
        </div>
    </aside>

    <section id="main" class="span9">

        <div class="hero-unit">
            <h2>comSysto 2012</h2>
            <h4>Original Authors: Samer Al-Hunaty and Benjamin Steinert</h4>
        </div>

        <h5>Existing Controllers</h5>
        <ul>
            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                <li<%= c.logicalPropertyName == controllerName ? ' class="active"' : '' %>><g:link controller="${c.logicalPropertyName}">${c.naturalName}</g:link></li>
            </g:each>
        </ul>
    </section>
</div>

</body>
</html>
