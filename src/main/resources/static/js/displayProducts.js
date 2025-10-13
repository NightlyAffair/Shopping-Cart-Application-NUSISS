/**
 * displayProducts JS
 * Authors: Glenn Min
 * Date: 2025-10-08
 * Last Modified by: Glenn Min
 * New Updates: minor bug fixes
 * Last Modified: 2025-10-11
 */

document.addEventListener("DOMContentLoaded", () => {

  // Favourites
  const wishlistButtons = document.querySelectorAll(".wishlist-btn");

  wishlistButtons.forEach((btn) => {
    const icon = btn.querySelector("i");
    const productId = btn.getAttribute("data-id");

    btn.addEventListener("click", async () => {
      try {
        // POST to backend: toggle favorite in session
        const response = await fetch(`/products/favorite/${productId}`, {
          method: "POST"
        });

        if (!response.ok) {
          console.error("Error toggling favorite:", response.statusText);
          return;
        }

        const result = await response.text();

        // Update heart icon & visual feedback
        if (result === "added") {
          btn.classList.add("active");
          icon.classList.replace("bi-heart", "bi-heart-fill");
          showToast("Added to favorites ❤️");
        } else if (result === "removed") {
          btn.classList.remove("active");
          icon.classList.replace("bi-heart-fill", "bi-heart");
          showToast("Removed from favorites 💔");
        }

      } catch (err) {
        console.error("Favorite toggle failed:", err);
      }
    });
  });

  // Toast

  function showToast(message) {
    const toast = document.createElement("div");
    toast.className = "custom-toast";
    toast.textContent = message;
    document.body.appendChild(toast);

    setTimeout(() => toast.classList.add("show"), 50);
    setTimeout(() => {
      toast.classList.remove("show");
      setTimeout(() => toast.remove(), 300);
    }, 2000);
  }

  const style = document.createElement("style");
  style.textContent = `
    .custom-toast {
      position: fixed;
      bottom: 30px;
      right: 30px;
      background-color: #0d6efd;
      color: #fff;
      padding: 10px 18px;
      border-radius: 8px;
      opacity: 0;
      transform: translateY(20px);
      transition: all 0.3s ease;
      font-size: 0.9rem;
      z-index: 9999;
      box-shadow: 0 4px 8px rgba(0,0,0,0.2);
    }
    .custom-toast.show {
      opacity: 1;
      transform: translateY(0);
    }
  `;
  document.head.appendChild(style);

});
