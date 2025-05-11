google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(drawTDEEChart);
google.charts.setOnLoadCallback(drawWeightChart);

function drawTDEEChart() {
    var dataArray = [['Date', 'TDEE']];
    for (var i = 0; i < datesTdee.length; i++) {
        dataArray.push([datesTdee[i], tdees[i]]);
    }
    var data = google.visualization.arrayToDataTable(dataArray);
    var options = {
        title: 'How has your TDEE changed over time?',
        titleTextStyle: {
            fontSize: 18,
            bold: true
        },
        width: 800,
        height: 550,
        chartArea: {
            left: 70,
            right: 30,
            top: 60,
            bottom: 70,
            width: '80%',
            height: '70%'
        },
        curveType: 'none',
        legend: { position: 'bottom', textStyle: { fontSize: 12 } },
        colors: ['#FF7767'],
        backgroundColor: '#FFEBCD',
        hAxis: {
            title: 'Date',
            titleTextStyle: { italic: false, bold: true, fontSize: 14 },
            textStyle: { fontSize: 12 }
        },
        vAxis: {
            title: 'TDEE (calories)',
            titleTextStyle: { italic: false, bold: true, fontSize: 14 },
            textStyle: { fontSize: 12 },
            viewWindow: {
                max: Math.max(...tdees) + 200,
                min: Math.min(...tdees) - 200
            }
        },
        lineWidth: 3,
        pointSize: 5
    };
    var chart = new google.visualization.LineChart(document.getElementById('tdee_chart'));
    chart.draw(data, options);
}

function drawWeightChart() {
    var dataArray = [['Date', 'Weight']];
    for (var i = 0; i < datesWeight.length; i++) {
        dataArray.push([datesWeight[i], weights[i]]);
    }
    var data = google.visualization.arrayToDataTable(dataArray);
    var options = {
        title: 'How has your weight changed over time?',
        titleTextStyle: {
            fontSize: 18,
            bold: true
        },
        width: 800,
        height: 550,
        chartArea: {
            left: 70,
            right: 30,
            top: 60,
            bottom: 70,
            width: '80%',
            height: '70%'
        },
        curveType: 'none',
        legend: { position: 'bottom', textStyle: { fontSize: 12 } },
        colors: ['#FF7767'],
        backgroundColor: '#FFEBCD',
        hAxis: {
            title: 'Date',
            titleTextStyle: { italic: false, bold: true, fontSize: 14 },
            textStyle: { fontSize: 12 }
        },
        vAxis: {
            title: 'Weight [kg]',
            titleTextStyle: { italic: false, bold: true, fontSize: 14 },
            textStyle: { fontSize: 12 },
            viewWindow: {
                max: Math.max(...weights) + 5,
                min: Math.min(...weights) - 5
            }
        },
        lineWidth: 3,
        pointSize: 5
    };
    var chart = new google.visualization.LineChart(document.getElementById('weight_chart'));
    chart.draw(data, options);
}