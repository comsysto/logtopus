<!doctype html>
<html>
<body>
<div class="row-fluid" style="min-height: 700px">
    <aside class=" well span4">
        <div class="row-fluid">
            <h5>Current Distribution</h5>
            <div>
                <g:include controller="charts" action="levelPieChart"/>
            </div>
            <hr/>
        </div>

        <div class="row-fluid">
            <div>
                <h3>Try It</h3>
                <ul class="nav nav-list">
                    <li><g:link controller="logEvent">Event Filter</g:link></li>
                    <li><g:link controller="issueRanking" action="top10">Top 10 Issues</g:link></li>
                    <li><g:link controller="charts" action="distributionBarChart" params="[time: '1h']"> Distribution over the last Hour</g:link></li>
                    <li><g:link controller="charts" action="distributionBarChart" params="[time: '1d']"> Distribution over the last Day</g:link></li>
                    <li><g:link controller="charts" action="distributionBarChart" params="[time: '1w']"> Distribution over the last Week</g:link></li>
                    <li><g:link controller="charts" action="levelPieChart">Total Overview</g:link> </li>
                </ul>
            </div>

        </div>
    </aside>

    <section id="main" class="span8">

        <div class="hero-unit">
            <div class="row-fluid">
                <span class="span2"><g:img dir="images" file="logtopus_icon_small.png"></g:img>&nbsp;</span>
                <span class="span10">
                    <h1>Welcome to Logtopus</h1>
                    <p>Need a helping... tentacle?</p>
                </span>

            </div>
        </div>

        <g:include controller="issueRanking" action="top10"/>



    </section>
</div>

</body>
</html>
