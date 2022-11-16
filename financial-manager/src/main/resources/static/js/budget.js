$(document).ready(function() {



var transaction_y = totalExpenses.map(x => x["amount"])
var transaction_x = totalExpenses.map(x => x["name"])





var barColors = [
  "#b91d47",
  "#e8c3b9",
  "#00aba9",
   "#2b5797",
   "#e8c3b9",
   "#1e7145"
];



new Chart("myChart", {
  type: "doughnut",
  data: {
    labels: transaction_x,
    datasets: [{
      backgroundColor: barColors,
      data: transaction_y

    }]
  },
  options: {
    title: {
      display: true,
      text: "Your expenses is"
    }
  }
});

});