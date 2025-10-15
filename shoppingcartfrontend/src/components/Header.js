import { useEffect } from "react";
import "../css/style.css"
//import 'bootstrap/dist/css/bootstrap.min.css';

/**
 * Header
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */

export default function Header() {
  return (
    <header className="site-header fixed-top">
      <div className="container">
        <div className="row align-items-center">
          <div className="col-md-3">
            <div className="logo">
              <h5>Shop @ISS</h5>
            </div>
          </div>
          <div className="col-md-6 text-center d-none d-md-block">
            <span className="promotional-text">Today's Deals: Free shipping over $80 | 10% off on new arrivals</span>
          </div>
          <div className="col-md-3">
            <div className="dropdown user-dropdown d-flex justify-content-end">
              <a href="#"
                 className="d-flex align-items-center gap-2 text-decoration-none dropdown-toggle"
                 data-bs-toggle="dropdown"
                 aria-expanded="false">
                <img
                    src="https://images.unsplash.com/photo-1750535135593-3a8e5def331d?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1480"
                    alt="user profile"
                    width="40"
                    height="40"
                    className="rounded-circle"/>
                <i className="bi bi-chevron-down text-white"></i>
              </a>
              <ul className="dropdown-menu dropdown-menu-end">
                <li><a className="dropdown-item" href="http://localhost:3000/#/accountInfo">
                  <i className="bi bi-gear me-2"></i>Settings
                </a></li>
                <li><a className="dropdown-item" href="http://localhost:3000/#/purchaseHistory">
                  <i className="bi bi-clock-history me-2"></i>View Purchase History
                </a></li>
                <li>
                  <hr className="dropdown-divider"/>
                </li>
                <li><a className="dropdown-item" href="#">
                  <i className="bi bi-box-arrow-right me-2"></i>Sign out
                </a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </header>
  );
}




// export default function Header() {

//     return (<header className="site-header fixed-top">
//     <div className="container">
//       <div className="row align-items-center">
//         <div className="col-md-3">
//           <div className="logo">
//             <h5>Shop @ISS</h5>
//           </div>
//         </div>
//         <div className="col-md-6 text-center d-none d-md-block">
//           <span className="promotional-text">Today's Deals: Free shipping over $80 | 10% off on new arrivals</span>
//         </div>
//         <div className="col-md-3">
//           <div className="dropdown user-dropdown d-flex justify-content-end">
//             <a href="#"
//                className="d-flex align-items-center gap-2 text-decoration-none dropdown-toggle"
//                data-bs-toggle="dropdown"
//                aria-expanded="false">
//               <img
//                   src="https://images.unsplash.com/photo-1750535135593-3a8e5def331d?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1480"
//                   alt="user profile"
//                   width="40"
//                   height="40"
//                   className="rounded-circle"/>
//               <i className="bi bi-chevron-down text-white"></i>
//             </a>
//             <ul className="dropdown-menu dropdown-menu-end">
//               <li><a className="dropdown-item" href="http://localhost:3000/#/accountInfo">
//                 <i className="bi bi-gear me-2"></i>Settings
//               </a></li>
//               <li><a className="dropdown-item" href="http://localhost:3000/#/purchaseHistory">
//                 <i className="bi bi-clock-history me-2"></i>View Purchase History
//               </a></li>
//               <li>
//                 <hr className="dropdown-divider"/>
//               </li>
//               <li><a className="dropdown-item">
//                 <i className="bi bi-box-arrow-right me-2"></i>Sign out
//               </a></li>
//             </ul>
//         </div>
//       </div>
//     </div>
//     </div>
// </header>)
// }