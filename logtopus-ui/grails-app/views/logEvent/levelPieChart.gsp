<%@ page import="com.comsysto.logtopus.LogEvent" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Log Level Overview Chart</title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">

        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ["Level", "Count"],
                ['DEBUG', ${debugCount}],
                ['INFO',     ${infoCount}],
                ['WARN',      ${warnCount}],
                ['ERROR',  ${errorCount}],
                ['FATAL', ${fatalCount}]
            ]);

            var options = {
                title: 'Message Log Levels'
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div id="chart_div" style="width: 900px; height: 500px;"></div>
</body>
</html>