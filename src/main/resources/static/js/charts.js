google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(drawTDEEChart);

function drawTDEEChart() {
    var dataArray = [['Date', 'TDEE']];
    for (var i = 0; i < dates.length; i++) {
        dataArray.push([dates[i], tdees[i]]);
    }
    var data = google.visualization.arrayToDataTable(dataArray);
    var options = {
            title: 'How has your TDEE changed over time?',
            titleTextStyle: {
                fontSize: 16,
                bold: true
            },
            width: 600,
            height: 350,
            chartArea: {
                left: 60,
                right: 20,
                top: 40,
                bottom: 60,
                width: '75%',
                height: '70%'
            },
            curveType: 'function',
            legend: { position: 'bottom' },
            colors: ['#1f77b4'],
            backgroundColor: '#f9f9f9',
            hAxis: {
                title: 'Date',
                titleTextStyle: {italic: false, bold: true}
            },
            vAxis: {
                title: 'TDEE (calories)',
                titleTextStyle: {italic: false, bold: true},
                viewWindow: {
                        max: 3500,
                        min: 1000
                }
            },
            lineWidth: 3,
            pointSize: 5
        };
    var chart = new google.visualization.LineChart(document.getElementById('tdee_chart'));
    chart.draw(data, options);
}