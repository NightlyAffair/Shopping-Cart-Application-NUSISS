import "../css/global.css"
import "../css/displayProducts.css"
import 'bootstrap/dist/css/bootstrap.min.css';
/**
 * Header
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */

export default function Header() {
    return (<header className="site-header" style={{display:"flex", width:'100%',height:'100%'}}>
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
          <div className="user-actions d-flex align-items-center justify-content-end gap-2">
            <a href="#" className="text-white"><i className="bi bi-person-circle fs-4"></i></a>
            <a href="@{/login}" className="btn btn-outline-light btn-sm">Login</a>
            <a href="@{/signup}" className="btn btn-warning btn-sm">Sign Up</a>
          </div>
        </div>
      </div>
    </div>
  </header>)
}