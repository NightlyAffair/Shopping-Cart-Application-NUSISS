// Sample product data (in production, this would come from your backend)
const allProducts = Array.from({ length: 50 }, (_, i) => ({
  id: i + 1,
  name: `Product ${i + 1}`,
  price: (Math.random() * 100 + 10).toFixed(2),
  rating: (Math.random() * 5).toFixed(1),
  category: ['Stationery', 'Electronics', 'Snacks', 'Beverages', 'Books'][Math.floor(Math.random() * 5)],
  image: `https://via.placeholder.com/200x200?text=Product+${i + 1}`,
  inWishlist: false
}));

let currentPage = 1;
const itemsPerPage = 10;
let filteredProducts = [...allProducts];
let wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];

// Initialize wishlist state from localStorage
allProducts.forEach(product => {
  product.inWishlist = wishlist.includes(product.id);
});

// Render products
function renderProducts(page = 1) {
  const start = (page - 1) * itemsPerPage;
  const end = start + itemsPerPage;
  const productsToShow = filteredProducts.slice(start, end);

  const container = document.getElementById('productContainer');
  container.innerHTML = '';

  if (productsToShow.length === 0) {
    container.innerHTML = '<div class="col-12 text-center py-5"><p class="text-muted">No products found.</p></div>';
    document.getElementById('productCount').textContent = '0 products';
    document.getElementById('pagination').innerHTML = '';
    return;
  }

  productsToShow.forEach(product => {
    const col = document.createElement('div');
    col.className = 'col';

    col.innerHTML = `
      <div class="card h-100 shadow-sm position-relative">
        <button class="btn btn-link position-absolute top-0 end-0 m-2 p-1 bg-white rounded-circle border-0 wishlist-btn" 
                style="z-index: 10; width: 35px; height: 35px;"
                data-product-id="${product.id}"
                aria-label="Add to wishlist">
          <i class="bi bi-heart${product.inWishlist ? '-fill text-danger' : ''} fs-5"></i>
        </button>
        <img src="${product.image}" class="card-img-top" alt="${product.name}" style="height: 200px; object-fit: cover;">
        <div class="card-body">
          <h6 class="card-title text-truncate">${product.name}</h6>
          <p class="card-text mb-1">$${product.price}</p>
          <div class="d-flex align-items-center">
            <span class="badge bg-warning text-dark me-2">${product.rating} ★</span>
            <span class="badge bg-secondary">${product.category}</span>
          </div>
        </div>
        <div class="card-footer bg-transparent border-0 pb-3">
          <button class="btn btn-primary btn-sm w-100">Add to Cart</button>
        </div>
      </div>
    `;

    container.appendChild(col);
  });

  // Add event listeners to all wishlist buttons
  document.querySelectorAll('.wishlist-btn').forEach(btn => {
    btn.addEventListener('click', function(e) {
      e.preventDefault();
      const productId = parseInt(this.getAttribute('data-product-id'));
      toggleWishlist(productId, this);
    });
  });

  // Update product count
  document.getElementById('productCount').textContent = `${filteredProducts.length} products`;

  renderPagination();
}

// Toggle wishlist
function toggleWishlist(productId, button) {
  const product = allProducts.find(p => p.id === productId);
  const icon = button.querySelector('i');

  if (product.inWishlist) {
    // Remove from wishlist
    product.inWishlist = false;
    wishlist = wishlist.filter(id => id !== productId);
    icon.className = 'bi bi-heart fs-5';
  } else {
    // Add to wishlist
    product.inWishlist = true;
    wishlist.push(productId);
    icon.className = 'bi bi-heart-fill text-danger fs-5';
  }

  // Save to localStorage
  localStorage.setItem('wishlist', JSON.stringify(wishlist));

  // Optional: Show a toast notification
  showToast(product.inWishlist ? 'Added to favorites!' : 'Removed from favorites!');
}

// Show toast notification (optional)
function showToast(message) {
  // Create toast element if it doesn't exist
  let toastContainer = document.getElementById('toastContainer');
  if (!toastContainer) {
    toastContainer = document.createElement('div');
    toastContainer.id = 'toastContainer';
    toastContainer.className = 'position-fixed bottom-0 end-0 p-3';
    toastContainer.style.zIndex = '11';
    document.body.appendChild(toastContainer);
  }

  const toastId = 'toast-' + Date.now();
  const toastHTML = `
    <div id="${toastId}" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="toast-header">
        <strong class="me-auto">Wishlist</strong>
        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
      <div class="toast-body">
        ${message}
      </div>
    </div>
  `;

  toastContainer.insertAdjacentHTML('beforeend', toastHTML);
  const toastElement = document.getElementById(toastId);
  const toast = new bootstrap.Toast(toastElement);
  toast.show();

  // Remove toast element after it's hidden
  toastElement.addEventListener('hidden.bs.toast', function() {
    this.remove();
  });
}

// Render pagination
function renderPagination() {
  const totalPages = Math.ceil(filteredProducts.length / itemsPerPage);
  const pagination = document.getElementById('pagination');
  pagination.innerHTML = '';

  if (totalPages <= 1) return;

  // Previous button
  const prevLi = document.createElement('li');
  prevLi.className = `page-item ${currentPage === 1 ? 'disabled' : ''}`;
  prevLi.innerHTML = `<a class="page-link" href="#" data-page="${currentPage - 1}">Previous</a>`;
  pagination.appendChild(prevLi);

  // Always show first page
  const firstLi = document.createElement('li');
  firstLi.className = `page-item ${currentPage === 1 ? 'active' : ''}`;
  firstLi.innerHTML = `<a class="page-link" href="#" data-page="1">1</a>`;
  pagination.appendChild(firstLi);

  // Show ellipsis if needed
  if (currentPage > 3) {
    const ellipsis = document.createElement('li');
    ellipsis.className = 'page-item disabled';
    ellipsis.innerHTML = `<span class="page-link">...</span>`;
    pagination.appendChild(ellipsis);
  }

  // Show pages around current page
  for (let i = Math.max(2, currentPage - 1); i <= Math.min(totalPages - 1, currentPage + 1); i++) {
    const li = document.createElement('li');
    li.className = `page-item ${i === currentPage ? 'active' : ''}`;
    li.innerHTML = `<a class="page-link" href="#" data-page="${i}">${i}</a>`;
    pagination.appendChild(li);
  }

  // Show ellipsis if needed
  if (currentPage < totalPages - 2) {
    const ellipsis = document.createElement('li');
    ellipsis.className = 'page-item disabled';
    ellipsis.innerHTML = `<span class="page-link">...</span>`;
    pagination.appendChild(ellipsis);
  }

  // Always show last page (if more than 1 page)
  if (totalPages > 1) {
    const lastLi = document.createElement('li');
    lastLi.className = `page-item ${currentPage === totalPages ? 'active' : ''}`;
    lastLi.innerHTML = `<a class="page-link" href="#" data-page="${totalPages}">${totalPages}</a>`;
    pagination.appendChild(lastLi);
  }

  // Next button
  const nextLi = document.createElement('li');
  nextLi.className = `page-item ${currentPage === totalPages ? 'disabled' : ''}`;
  nextLi.innerHTML = `<a class="page-link" href="#" data-page="${currentPage + 1}">Next</a>`;
  pagination.appendChild(nextLi);

  // Add click event listeners to pagination links
  pagination.querySelectorAll('a.page-link').forEach(link => {
    link.addEventListener('click', function(e) {
      e.preventDefault();
      const page = parseInt(this.getAttribute('data-page'));
      if (page >= 1 && page <= totalPages) {
        goToPage(page);
      }
    });
  });
}

// Go to page
function goToPage(page) {
  currentPage = page;
  renderProducts(page);
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

// Filter and search products
function filterProducts() {
  const searchTerm = document.getElementById('searchInput').value.toLowerCase();
  const category = document.getElementById('categorySelect').value;
  const sortBy = document.getElementById('sortSelect').value;

  // Filter by search term and category
  filteredProducts = allProducts.filter(product => {
    const matchesSearch = product.name.toLowerCase().includes(searchTerm);
    const matchesCategory = !category || product.category === category;
    return matchesSearch && matchesCategory;
  });

  // Sort products
  switch(sortBy) {
    case 'priceAsc':
      filteredProducts.sort((a, b) => parseFloat(a.price) - parseFloat(b.price));
      break;
    case 'priceDesc':
      filteredProducts.sort((a, b) => parseFloat(b.price) - parseFloat(a.price));
      break;
    case 'rating':
      filteredProducts.sort((a, b) => parseFloat(b.rating) - parseFloat(a.rating));
      break;
  }

  currentPage = 1;
  renderProducts(1);
}

// Event listeners
document.addEventListener('DOMContentLoaded', function() {
  // Filter button
  document.getElementById('filterBtn').addEventListener('click', filterProducts);

  // Search on Enter key
  document.getElementById('searchInput').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
      filterProducts();
    }
  });

  // Auto-filter on select change
  document.getElementById('categorySelect').addEventListener('change', filterProducts);
  document.getElementById('sortSelect').addEventListener('change', filterProducts);

  // Initial render
  renderProducts(1);
});