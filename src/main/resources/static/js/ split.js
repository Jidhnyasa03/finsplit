async function fetchSplitSummary() {
  const splitContainer = document.getElementById('splitResult');
  splitContainer.innerHTML = 'Loading...';

  try {
    const response = await fetch('http://localhost:8080/api/expenses/split');
    const data = await response.json();

    if (data.length === 0) {
      splitContainer.innerHTML = '<p class="text-gray-500">No expenses to split.</p>';
      return;
    }

    splitContainer.innerHTML = '';
    data.forEach(item => {
      const line = document.createElement('p');
      line.innerHTML = `<span class="font-medium text-indigo-600">${item.from}</span> owes
                        <span class="font-medium text-green-600">${item.to}</span>
                        ₹${item.amount.toFixed(2)}`;
      splitContainer.appendChild(line);
    });
  } catch (error) {
    splitContainer.innerHTML = '<p class="text-red-600">❌ Failed to load split summary</p>';
    console.error('Split API error:', error);
  }
}

fetchSplitSummary();
