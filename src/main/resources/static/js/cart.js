document.addEventListener("DOMContentLoaded", function () {
    const checkboxes = document.querySelectorAll(".select-product");
    const totalSpan = document.getElementById("selectedTotal");
    const selectedList = document.getElementById("selectedList");
    const selectAll = document.getElementById("selectAll");

    function updateTotal() {
        let sum = 0, details = "", hasProduct = false;
        checkboxes.forEach(cb => {
            if (cb.checked) {
                hasProduct = true;
                const row = cb.closest("tr");
                const name = row.querySelector(".pname").textContent;
                const qty = parseInt(row.querySelector(".qty").textContent);
                const subtotal = parseFloat(row.querySelector(".subtotal").textContent);
                details += `<div>${name} (${qty}) &nbsp;&nbsp; $${subtotal.toFixed(2)}</div>`;
                sum += subtotal;
            }
        });
        selectedList.innerHTML = hasProduct
            ? details
            : "<div class='text-center text-muted'>No Selected Product</div>";
        totalSpan.textContent = `$${sum.toFixed(2)}`;
    }

    checkboxes.forEach(cb => {
        cb.addEventListener("change", function () {
            const productId = cb.value;
            const checked = cb.checked;
            fetch("/shoppingCartDetail/select", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `productId=${productId}&checked=${checked}`
            });
            updateTotal();
        });
    });

    selectAll.addEventListener("change", function () {
        checkboxes.forEach(cb => cb.checked = this.checked);
        checkboxes.forEach(cb => cb.dispatchEvent(new Event("change")));
    });

    updateTotal();
});
