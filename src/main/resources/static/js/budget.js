document.getElementById('budgetForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const budget = {
    year: parseInt(document.getElementById('year').value),
    month: parseInt(document.getElementById('month').value),
    amount: parseFloat(document.getElementById('amount').value),
    user: document.getElementById('user').value
  };

  try {
    const response = await fetch('http://localhost:8080/api/budget', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(budget)
    });

    if (response.ok) {
      document.getElementById('budgetForm').reset();
      document.getElementById('budgetSuccess').classList.remove('hidden');
      fetchCurrentBudget();
    } else {
      alert('❌ Failed to save budget');
    }
  } catch (error) {
    console.error('Error:', error);
    alert('❌ Error connecting to server');
  }
});

async function fetchCurrentBudget() {
  try {
    const res = await fetch('http://localhost:8080/api/budget');
    const budget = await res.json();

    const budgetText = `₹${budget.amount} for ${budget.month}/${budget.year} (User: ${budget.user})`;
    document.getElementById('currentBudget').textContent = budgetText;
  } catch (error) {
    document.getElementById('currentBudget').textContent = 'No budget found.';
  }
}

// Load budget on page load
fetchCurrentBudget();
