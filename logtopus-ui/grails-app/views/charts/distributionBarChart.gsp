<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Level Distribution over Time</title>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load("visualization", "1", {packages:["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {

            var timeSlots = ${timeSlots};
            var inputData = ${countOverTime};

            var dataArray = new Array(timeSlots.length);
            dataArray[0] = ['${timeUnit}', 'DEBUG', 'INFO', 'WARN', 'ERROR', 'FATAL'];

            for (var i=0; i<timeSlots.length; i++){
                dataArray[i+1]= new Array(6);
                dataArray[i+1][0] = timeSlots[i];
                dataArray[i+1][1] = inputData[i*5] //debug
                dataArray[i+1][2] = inputData[i*5+1]//info
                dataArray[i+1][3] = inputData[i*5+2]//warn
                dataArray[i+1][4] = inputData[i*5+3]//error
                dataArray[i+1][5] = inputData[i*5+4]//fatal
            }

            var data = google.visualization.arrayToDataTable(dataArray);

            var options = {
                title: 'Level Distribution',
                width: '1400',
                height: '300',
                colors: ['blue', 'green', 'orange', 'red', 'violet'],
                hAxis: {title: 'Time', titleTextStyle: {color: 'blue'}},
                vAxis: {title: 'Count', titleTextStyle: {color: 'blue'}}
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>

</head>

<body>
<div id="chart_div"></div>
</body>
</html>