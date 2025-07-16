// js/load-navbar.js
document.addEventListener("DOMContentLoaded", function () {
  fetch("components/navbar.html")
    .then(res => res.text())
    .then(data => {
      document.getElementById("navbar-container").innerHTML = data;
    });
});
