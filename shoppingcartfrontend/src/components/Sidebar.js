import {useNavigate} from "react-router-dom";
import "../css/Global.css"
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
        <div style={{ height: "100vh",width:"30vh", backgroundColor: "white" }}>
            <h1>Profile</h1>
            <div className={"site-navbar"} style={{display: "flex", flexDirection: "column"}}>
                <button onClick={navigateToAccountInfo}>Account Information</button>
                <button onClick={navigateToPurchaseHistory}>Purchase History</button>
                <button onClick={navigateToAccountInfo}>Logout</button>
            </div>
        </div>

    )
}