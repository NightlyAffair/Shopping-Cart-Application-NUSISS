import {HashRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import AccountInfo from "./pages/AccountInfo"
import PurchaseHistory from "./pages/PurchaseHistory";
import './App.css';
/**
 * App.js
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */
function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={<Navigate to="/accountinfo" />} />
              <Route path={"/accountinfo"} element={<AccountInfo />} />
              <Route path={"/purchasehistory"} element={<PurchaseHistory />} />
          </Routes>
      </Router>
  );
}

export default App;
