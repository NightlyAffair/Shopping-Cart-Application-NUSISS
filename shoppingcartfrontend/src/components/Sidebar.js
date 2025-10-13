import {useNavigate} from "react-router-dom";
import "../css/style.css"
import 'bootstrap/dist/css/bootstrap.min.css';
/**
 * Sidebar
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */
export default function Sidebar() {

    const navigate = useNavigate();

    const navigateToAccountInfo = () => {
        navigate("/accountInfo")
    }

    const navigateToPurchaseHistory = () => {
        navigate("/purchaseHistory")
    }

    return (
        <div style={{height: "100vh", width: "30vh", backgroundColor: "white", borderRight: '1px solid black'}}>
            <h4 className="font-weight-bold py-3 mb-4">
                Account settings
            </h4>
            <div className="row no-gutters row-bordered row-border-light" style={{width: "30vh"}}>
                <div className="list col-md-3 pt-0" style={{width: "30vh"}}>
                        <div className="list-group list-group-flush account-settings-links" style={{width: "29vh"}}>
                            <a className="list-group-item list-group-item-action" data-toggle="list "
                               onClick={navigateToAccountInfo}>Account Info</a>
                            <a className="list-group-item list-group-item-action" data-toggle="list"
                               onClick={navigateToPurchaseHistory}>Purchase History</a>
                            <a className="list-group-item list-group-item-action" data-toggle="list"
                               onClick={navigateToAccountInfo}>Logout</a>
                        </div>
                </div>
            </div>
        </div>
    )}