<!doctype html>
<html>
<body>
<div class="row-fluid">
    <aside id="application-status" class="span3">
        <div class="well sidebar-nav">
            <h5>Current Distribution</h5>
            <div>
                <g:include controller="charts" action="levelPieChart"/>
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
                    <li><g:link controller="charts" action="distributionBarChart" params="[time: '1h']"> Distribution over the last Hour</g:link></li>
                    <li><g:link controller="charts" action="distributionBarChart" params="[time: '1d']"> Distribution over the last Day</g:link></li>
                    <li><g:link controller="charts" action="distributionBarChart" params="[time: '1w']"> Distribution over the last Week</g:link></li>
                    <li><g:link controller="charts" action="levelPieChart">Total Overview</g:link> </li>
                </ul>
            </div>

        </div>

    </section>
</div>

</body>
</html>
