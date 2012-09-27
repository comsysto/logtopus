<!doctype html>
<html>
<body>
<div class="row-fluid">
    <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
            <h5>Current Distribution</h5>
            <div>
                <g:include controller="logEvent" action="levelPieChart"/>
            </div>
        </div>
    </aside>

    <section id="main" class="span9">

        <div class="hero-unit">
            <h1>Welcome to Logtopus</h1>
        </div>

        <div class="row-fluid">

            <div class="span4">
                <h2>Try It</h2>
                <ul class="nav nav-list">
                    <li><g:link controller="logEvent">Event Filter</g:link></li>
                    <li><a href="logEvent/distributionBarChart">Log Level Distribution</a></li>
                    <li><a href="logEvent/levelPieChart">Message Level Overview</a></li>
                </ul>
            </div>

        </div>

    </section>
</div>

</body>
</html>
