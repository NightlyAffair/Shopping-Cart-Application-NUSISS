import "../css/global.css"
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';



export default function Navbar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-dark site-navbar">
            <div className="container">
                <a className="navbar-brand" href="/products">
                    <i className="bi bi-grid-3x3-gap me-2"></i>Browse
                </a>
                <div className="collapse navbar-collapse" id="navbarFav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <a className="nav-link" href="/favourites">
                                <i className="bi bi-heart-fill me-1"></i>Favourites
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link active" href="/shoppingCartDetail/view">
                                <i className="bi bi-cart3 me-1"></i>My Cart
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}