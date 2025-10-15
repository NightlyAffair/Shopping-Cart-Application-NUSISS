import {HashRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import AccountInfo from "./pages/AccountInfo"
import PurchaseHistory from "./pages/PurchaseHistory";
import './App.css';
import Register from "./pages/Register";
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import 'bootstrap/dist/css/bootstrap.min.css';
/**
 * App.js
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */
function App() {
    function RedirectProducts() {
        window.location.replace("http://localhost:8080/products");
    }

    function RedirectFavourites() {
        window.location.replace("http://localhost:8080/favorites");
    }

    function RedirectCart() {
        window.location.replace("http://localhost:8080/products/cart");
    }
  return (
      <Router>
          <Routes>
              <Route path="/" element={<Navigate to="/accountinfo" />} />
              <Route path={"/accountinfo"} element={<AccountInfo />} />
              <Route path={"/purchaseHistory"} element={<PurchaseHistory />} />
              <Route path={"/products"} element={<RedirectProducts />} />
              <Route path={"/favourites"} element={<RedirectFavourites />} />
              <Route path={"/cart"} element={<RedirectCart />} />

              <Route path="/signup" element={<Register />} />
          </Routes>
      </Router>
  );
}

export default App;
