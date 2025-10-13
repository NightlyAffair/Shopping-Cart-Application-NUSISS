import "../css/style.css"
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import {useNavigate} from "react-router-dom";



export default function Navbar() {

    const navigate = useNavigate();
    return (
        <nav className="navbar navbar-expand-lg navbar-dark site-navbar" style={{paddingTop:'200px'}}>
            <div className="container">
                <a className="navbar-brand" href="localhost:8080/products" onClick={e => navigate('/products')}>
                    <i className="bi bi-grid-3x3-gap me-2"></i>Browse
                </a>
                <div className="collapse navbar-collapse" id="navbarFav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <a className="nav-link" href="localhost:8080/favourites" onClick={e => navigate('/favourites')}>
                                <i className="bi bi-heart-fill me-1"></i>Favourites
                            </a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link active" href="localhost:8080/shoppingCartDetail/view" onClick={e => navigate('/cart')}>
                                <i className="bi bi-cart3 me-1"></i>My Cart
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}