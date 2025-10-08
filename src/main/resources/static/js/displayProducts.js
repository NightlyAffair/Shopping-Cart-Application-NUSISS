// Sample product data with reviews (replace with actual data from backend)
const products = [
  {
    id: 1,
    name: "Premium Notebook",
    description: "High-quality notebook for all your writing needs",
    price: 12.99,
    image: "https://via.placeholder.com/300x300",
    category: "Stationery",
    rating: 4.5,
    reviewCount: 24
  },
  {
    id: 2,
    name: "Wireless Mouse",
    description: "Ergonomic wireless mouse with long battery life",
    price: 24.99,
    image: "https://via.placeholder.com/300x300",
    category: "Electronics",
    rating: 4.2,
    reviewCount: 18
  },
  {
    id: 3,
    name: "Coffee Mug",
    description: "Ceramic coffee mug with heat retention",
    price: 8.99,
    image: "https://via.placeholder.com/300x300",
    category: "Beverages",
    rating: 4.8,
    reviewCount: 42
  }
  // Add more products as needed
];

// Pagination settings
const ITEMS_PER_PAGE = 20;
let currentPage = 1;
let filteredProducts = [...products];

// Initialize page
document.addEventListener('DOMContentLoaded', function() {
  renderProducts();
  setupEventListeners();
});

// Render products
function renderProducts() {
  const container = document.getElementById('productContainer');
  const start = (currentPage - 1) * ITEMS_PER_PAGE;
  const end = start + ITEMS_PER_PAGE;
  const pageProducts = filteredProducts.slice(start, end);
  
  container.innerHTML = '';
  
  if (pageProducts.length === 0) {
    container.innerHTML = `
      <div class="col-12 text-center py-5">
        <i class="bi bi-inbox display-1 text-muted"></i>
        <h4 class="mt-3">No products found</h4>
        <p class="text-muted">Try adjusting your filters</p>
      </div>
    `;
    return;
  }
  
  pageProducts.forEach(product => {
    const card = createProductCard(product);
    container.innerHTML += card;
  });
  
  // Update product count
  document.getElementById('productCount').textContent = 
    `Showing ${start + 1}-${Math.min(end, filteredProducts.length)} of ${filteredProducts.length} products`;
  
  // Render pagination
  renderPagination();
  
  // Setup wishlist buttons
  setupWishlistButtons();
}

// Create product card HTML
function createProductCard(product) {
  const stars = generateStarRating(product.rating);
  
  return `
    <div class="col">
      <div class="card product-card h-100">
        <button class="wishlist-btn" data-product-id="${product.id}">
          <i class="bi bi-heart"></i>
        </button>
        <img src="${product.image}" class="card-img-top" alt="${product.name}">
        <div class="card-body d-flex flex-column">
          <h6 class="card-title">${product.name}</h6>
          <p class="card-text">${product.description}</p>
          <div class="rating mb-2">
            ${stars}
            <span class="text-muted ms-1">(${product.reviewCount})</span>
          </div>
          <div class="price">$${product.price.toFixed(2)}</div>
          <div class="mt-auto">
            <a href="/products/${product.id}" class="btn btn-primary btn-sm w-100">
              <i class="bi bi-eye me-1"></i>View Details
            </a>
          </div>
        </div>
      </div>
    </div>
  `;
}

// Generate star rating HTML
function generateStarRating(rating) {
  let stars = '';
  const fullStars = Math.floor(rating);
  const hasHalfStar = rating % 1 >= 0.5;
  
  for (let i = 0; i < fullStars; i++) {
    stars += '<i class="bi bi-star-fill"></i>';
  }
  
  if (hasHalfStar) {
    stars += '<i class="bi bi-star-half"></i>';
  }
  
  const emptyStars = 5 - Math.ceil(rating);
  for (let i = 0; i < emptyStars; i++) {
    stars += '<i class="bi bi-star"></i>';
  }
  
  return stars;
}

// Setup event listeners
function setupEventListeners() {
  // Search
  document.getElementById('searchInput').addEventListener('input', filterProducts);
  
  // Category filter
  document.getElementById('categorySelect').addEventListener('change', filterProducts);
  
  // Sort
  document.getElementById('sortSelect').addEventListener('change', sortProducts);
  
  // Filter button
  document.getElementById('filterBtn').addEventListener('click', filterProducts);
}

// Filter products
function filterProducts() {
  const searchTerm = document.getElementById('searchInput').value.toLowerCase();
  const category = document.getElementById('categorySelect').value;
  
  filteredProducts = products.filter(product => {
    const matchesSearch = product.name.toLowerCase().includes(searchTerm) || 
                         product.description.toLowerCase().includes(searchTerm);
    const matchesCategory = !category || product.category === category;
    
    return matchesSearch && matchesCategory;
  });
  
  currentPage = 1;
  sortProducts();
}

// Sort products
function sortProducts() {
  const sortValue = document.getElementById('sortSelect').value;
  
  switch(sortValue) {
    case 'priceAsc':
      filteredProducts.sort((a, b) => a.price - b.price);
      break;
    case 'priceDesc':
      filteredProducts.sort((a, b) => b.price - a.price);
      break;
    case 'rating':
      filteredProducts.sort((a, b) => b.rating - a.rating);
      break;
    default:
      // Default sorting (by ID or name)
      filteredProducts.sort((a, b) => a.id - b.id);
  }
  
  renderProducts();
}

// Render pagination
function renderPagination() {
  const totalPages = Math.ceil(filteredProducts.length / ITEMS_PER_PAGE);
  const pagination = document.getElementById('pagination');
  
  if (totalPages <= 1) {
    pagination.innerHTML = '';
    return;
  }
  
  let html = '';
  
  // Previous button
  html += `
    <li class="page-item ${currentPage === 1 ? 'disabled' : ''}">
      <a class="page-link" href="#" data-page="${currentPage - 1}">Previous</a>
    </li>
  `;
  
  // Page numbers
  for (let i = 1; i <= totalPages; i++) {
    if (i === 1 || i === totalPages || (i >= currentPage - 1 && i <= currentPage + 1)) {
      html += `
        <li class="page-item ${i === currentPage ? 'active' : ''}">
          <a class="page-link" href="#" data-page="${i}">${i}</a>
        </li>
      `;
    } else if (i === currentPage - 2 || i === currentPage + 2) {
      html += `<li class="page-item disabled"><span class="page-link">...</span></li>`;
    }
  }
  
  // Next button
  html += `
    <li class="page-item ${currentPage === totalPages ? 'disabled' : ''}">
      <a class="page-link" href="#" data-page="${currentPage + 1}">Next</a>
    </li>
  `;
  
  pagination.innerHTML = html;
  
  // Add click handlers
  pagination.querySelectorAll('a.page-link').forEach(link => {
    link.addEventListener('click', function(e) {
      e.preventDefault();
      const page = parseInt(this.getAttribute('data-page'));
      if (page && page !== currentPage && page >= 1 && page <= totalPages) {
        currentPage = page;
        renderProducts();
        window.scrollTo({ top: 0, behavior: 'smooth' });
      }
    });
  });
}

// Setup wishlist buttons
function setupWishlistButtons() {
  document.querySelectorAll('.wishlist-btn').forEach(btn => {
    btn.addEventListener('click', function(e) {
      e.preventDefault();
      const icon = this.querySelector('i');
      icon.classList.toggle('bi-heart');
      icon.classList.toggle('bi-heart-fill');
      this.classList.toggle('active');
      
      const productId = this.getAttribute('data-product-id');
      // Here you would make an AJAX call to add/remove from favorites
      console.log('Toggle favorite for product:', productId);
    });
  });
}