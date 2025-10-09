import {HashRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import AccountInfo from "./pages/AccountInfo"
import './App.css';

function App() {
  return (
      <Router>
          <Routes>
              <Route path="/" element={<Navigate to="/accountInfo" />} />
              <Route path={"/accountInfo"} element={<AccountInfo />} />
          </Routes>
      </Router>
  );
}

export default App;
