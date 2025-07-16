document.getElementById('expenseForm').addEventListener('submit', async (e) => {
  e.preventDefault();

  const expense = {
    category: document.getElementById('category').value,
    amount: parseFloat(document.getElementById('amount').value),
    paidBy: document.getElementById('paidBy').value,
    date: document.getElementById('date').value,
  };

  try {
    const response = await fetch('http://localhost:8080/api/expenses', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(expense),
    });

    if (response.ok) {
      document.getElementById('expenseForm').reset();
      document.getElementById('successMessage').classList.remove('hidden');
    } else {
      alert('❌ Failed to add expense');
    }
  } catch (error) {
    console.error('Error:', error);
    alert('❌ Error connecting to server');
  }
});
