const API_BASE = 'http://localhost:8080/api';

// ---------- Expenses ----------
export async function getExpenses() {
  const res = await fetch(`${API_BASE}/expenses`);
  if (!res.ok) throw new Error('Failed to fetch expenses');
  return res.json();
}

export async function addExpense(expenseData) {
  const res = await fetch(`${API_BASE}/expenses`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(expenseData)
  });
  if (!res.ok) throw new Error('Failed to add expense');
  return res.json();
}

export async function getSplitSummary() {
  const res = await fetch(`${API_BASE}/expenses/split`);
  if (!res.ok) throw new Error('Failed to fetch split summary');
  return res.json();
}

// ---------- Budget ----------
export async function getBudget() {
  const res = await fetch(`${API_BASE}/budget`);
  if (!res.ok) throw new Error('Failed to fetch budget');
  return res.json();
}

export async function updateBudget(budgetData) {
  const res = await fetch(`${API_BASE}/budget`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(budgetData)
  });
  if (!res.ok) throw new Error('Failed to update budget');
  return res.json();
}
