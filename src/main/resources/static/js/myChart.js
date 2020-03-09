var chartDataStr = decodeHtml(chartData);	
var chartJsonArray = JSON.parse(chartDataStr);

var arrayalength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for (var i = 0; i < arrayalength; i++) {
	numericData[i] = chartJsonArray[i].value;
	labelData[i] = chartJsonArray[i].label
}

// For a pie chart - https://www.chartjs.org/docs/latest/getting-started/
new Chart(document.getElementById('myPieChart'), {
    type: 'pie',
    // The data for our dataset
    data: {
        labels: labelData,
        datasets: [{
            label: 'My Dataset',
            backgroundColor: ["#B3342B", "#4F33FF", "#DDE91F"],
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title: {
    		display: true,
    		text: "Project Status"
    	}
    }
});

function decodeHtml(html) {
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}