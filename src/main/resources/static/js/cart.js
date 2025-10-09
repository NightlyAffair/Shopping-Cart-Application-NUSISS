// CART LOGIC (using localStorage)
document.addEventListener("DOMContentLoaded", () => {
  const cartItemsContainer = document.getElementById("cartItems");
  const subtotalEl = document.getElementById("subtotal");
  const totalEl = document.getElementById("total");

  let cart = JSON.parse(localStorage.getItem("cart")) || [];

  function updateCartDisplay() {
    cartItemsContainer.innerHTML = "";
    let subtotal = 0;

    if (cart.length === 0) {
      cartItemsContainer.innerHTML = `<tr><td colspan="5" class="text-muted py-5">Your cart is empty.</td></tr>`;
      subtotalEl.textContent = "$0.00";
      totalEl.textContent = "$0.00";
      return;
    }

    cart.forEach((item, index) => {
      const itemSubtotal = item.price * item.quantity;
      subtotal += itemSubtotal;

      const row = document.createElement("tr");
      row.innerHTML = `
        <td class="text-start">
          <img src="${item.image}" alt="${item.name}" width="60" class="me-2 rounded shadow-sm"/>
          <span>${item.name}</span>
        </td>
        <td>$${item.price.toFixed(2)}</td>
        <td>
          <div class="d-flex justify-content-center align-items-center gap-2">
            <button class="btn btn-sm btn-outline-secondary decrease" data-index="${index}"><i class="bi bi-dash"></i></button>
            <span>${item.quantity}</span>
            <button class="btn btn-sm btn-outline-secondary increase" data-index="${index}"><i class="bi bi-plus"></i></button>
          </div>
        </td>
        <td>$${itemSubtotal.toFixed(2)}</td>
        <td><button class="btn btn-sm btn-outline-danger remove" data-index="${index}"><i class="bi bi-trash"></i></button></td>
      `;
      cartItemsContainer.appendChild(row);
    });

    subtotalEl.textContent = `$${subtotal.toFixed(2)}`;
    totalEl.textContent = `$${subtotal.toFixed(2)}`;
    localStorage.setItem("cart", JSON.stringify(cart));
  }

  // Handle buttons
  cartItemsContainer.addEventListener("click", (e) => {
    const index = e.target.closest("button")?.dataset.index;
    if (e.target.closest(".increase")) cart[index].quantity++;
    if (e.target.closest(".decrease") && cart[index].quantity > 1) cart[index].quantity--;
    if (e.target.closest(".remove")) cart.splice(index, 1);
    updateCartDisplay();
  });

  updateCartDisplay();
});
