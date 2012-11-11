<%@ page import="com.comsysto.logtopus.LogLevelUtil; com.comsysto.logtopus.LogEvent" contentType="text/html;charset=UTF-8" %>
<html>
<head>
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
                title: 'Message Log Levels',

                colors: ['${LogLevelUtil.getColorCode('DEBUG')}',
                    '${LogLevelUtil.getColorCode('INFO')}',
                    '${LogLevelUtil.getColorCode('WARN')}',
                    '${LogLevelUtil.getColorCode('ERROR')}',
                    '${LogLevelUtil.getColorCode('FATAL')}'
                ],
                is3D: true,
                pieSliceTextStyle: {color: 'black'}
            };

            var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
<div id="chart_div" style="width: 100%; height: 100%;"></div>
</body>
</html>