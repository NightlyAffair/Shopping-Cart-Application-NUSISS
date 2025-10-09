import "../css/Global.css"

export default function Header() {
    return (<header className="site-header">
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