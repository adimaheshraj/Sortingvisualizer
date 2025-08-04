
let array = [];

function generateArray() {
  array = Array.from({ length: 30 }, () => Math.floor(Math.random() * 100));
  document.getElementById('array-input').value = '';
  renderArray();
}

function renderArray() {
  const container = document.getElementById("array-container");
  container.innerHTML = "";
  array.forEach(value => {
    const bar = document.createElement("div");
    bar.className = "bar";
    bar.style.height = `${value * 3}px`;
    container.appendChild(bar);
  });
}

function getArrayToSort() {
  const input = document.getElementById('array-input');
  if (input && input.value.trim()) {
    return input.value.split(',').map(x => parseInt(x.trim(), 10)).filter(x => !isNaN(x));
  }
  return array;
}

async function sortArray(type, arr) {
  try {
    const response = await fetch(`http://localhost:8080/api/sort/${type}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(arr),
    });
    if (!response.ok) throw new Error('API error');
    const sorted = await response.json();
    array = sorted;
    animateArray();
  } catch (err) {
    alert('Sorting failed. Is the backend running?');
  }
}


function animateArray() {
  renderArray();
}

window.onload = function() {
  generateArray();
  document.getElementById('sort-btn').onclick = async function() {
    const algorithm = document.getElementById('algorithm-select').value;
    const arr = getArrayToSort();
    await sortArray(algorithm, arr);
  };
  document.getElementById('generate-btn').onclick = generateArray;
};
