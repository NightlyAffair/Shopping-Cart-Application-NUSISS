document.addEventListener("DOMContentLoaded", function () {

  // ========================
  // 1️⃣ FAVORITE BUTTON
  // ========================
  document.querySelectorAll(".wishlist-btn").forEach((btn) => {
    btn.addEventListener("click", () => {
      btn.classList.toggle("active");
      const icon = btn.querySelector("i");
      if (btn.classList.contains("active")) {
        icon.classList.remove("bi-heart");
        icon.classList.add("bi-heart-fill");
      } else {
        icon.classList.remove("bi-heart-fill");
        icon.classList.add("bi-heart");
      }
    });
  });

  // ========================
  // 2️⃣ SEARCH BAR
  // ========================
  const searchInput = document.querySelector(".filter-bar input[type='text']");
  const productCards = document.querySelectorAll(".product-card");

  if (searchInput) {
    searchInput.addEventListener("input", () => {
      const keyword = searchInput.value.toLowerCase();
      productCards.forEach((card) => {
        const title = card.querySelector(".card-title").textContent.toLowerCase();
        const description = card.querySelector(".card-text").textContent.toLowerCase();
        if (title.includes(keyword) || description.includes(keyword)) {
          card.parentElement.style.display = "block";
        } else {
          card.parentElement.style.display = "none";
        }
      });
    });
  }

  // ========================
  // 3️⃣ SORT & CATEGORY DROPDOWNS
  // ========================
  const categorySelect = document.querySelector(".filter-bar select:nth-of-type(1)");
  const sortSelect = document.querySelector(".filter-bar select:nth-of-type(2)");

  function applyFilters() {
    const selectedCategory = categorySelect.value.toLowerCase();
    const sortOption = sortSelect.value.toLowerCase();

    let products = Array.from(productCards);

    // Filter by category (simulated)
    products.forEach((card) => {
      const title = card.querySelector(".card-title").textContent.toLowerCase();
      if (selectedCategory === "all categories" || title.includes(selectedCategory)) {
        card.parentElement.style.display = "block";
      } else {
        card.parentElement.style.display = "none";
      }
    });

    // Sort by price (simulated)
    if (sortOption.includes("low")) {
      products.sort((a, b) =>
          parseFloat(a.querySelector(".price").textContent.replace("$", "")) -
          parseFloat(b.querySelector(".price").textContent.replace("$", ""))
      );
    } else if (sortOption.includes("high")) {
      products.sort((a, b) =>
          parseFloat(b.querySelector(".price").textContent.replace("$", "")) -
          parseFloat(a.querySelector(".price").textContent.replace("$", ""))
      );
    }

    // Rearrange DOM
    const container = document.querySelector(".row.g-4");
    products.forEach((p) => container.appendChild(p.parentElement));
  }

  categorySelect.addEventListener("change", applyFilters);
  sortSelect.addEventListener("change", applyFilters);

  // ========================
  // 4️⃣ PAGINATION
  // ========================
  const paginationLinks = document.querySelectorAll(".pagination .page-link");
  const itemsPerPage = 4;
  let currentPage = 1;

  function showPage(pageNum) {
    const products = Array.from(productCards);
    products.forEach((card, index) => {
      const start = (pageNum - 1) * itemsPerPage;
      const end = pageNum * itemsPerPage;
      card.parentElement.style.display = index >= start && index < end ? "block" : "none";
    });

    paginationLinks.forEach((link) => link.parentElement.classList.remove("active"));
    paginationLinks[pageNum].parentElement.classList.add("active");
    currentPage = pageNum;
  }

  paginationLinks.forEach((link, index) => {
    link.addEventListener("click", (e) => {
      e.preventDefault();
      if (!isNaN(link.textContent)) {
        showPage(parseInt(link.textContent));
      }
    });
  });

  // Initialize
  showPage(currentPage);
});
