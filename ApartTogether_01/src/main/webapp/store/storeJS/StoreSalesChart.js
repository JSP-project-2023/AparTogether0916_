 var jan = $('input[name=01]').val();
 var feb = $('input[name=02]').val();
 var mar = $('input[name=03]').val();
 var par = $('input[name=04]').val();
 var may = $('input[name=05]').val();
 var jun = $('input[name=06]').val();
 var jul = $('input[name=07]').val();
 var aug = $('input[name=08]').val();
 var sep = $('input[name=09]').val();
 var oct = $('input[name=10]').val();
 var nov = $('input[name=11]').val();
 var dec = $('input[name=12]').val();
 
 const labels = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'];
  const data = {
  labels: labels,
  datasets: [{
    label: '월 매출 현황',
    data: [jan, feb, mar, par, may, jun, jul, aug, sep, oct, nov, dec],
    fill: false,
    borderColor: 'rgb(75, 192, 192)',
    tension: 0.3
  }]
};
  const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'line',
    data: data,
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });