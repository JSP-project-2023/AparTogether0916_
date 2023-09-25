 const labels = ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월',];
  const data = {
  labels: labels,
  datasets: [{
    label: '월 매출 현황',
    data: [65, 59, 80, 81, 56, 55, 40],
    fill: false,
    borderColor: 'rgb(75, 192, 192)',
    tension: 0.1
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