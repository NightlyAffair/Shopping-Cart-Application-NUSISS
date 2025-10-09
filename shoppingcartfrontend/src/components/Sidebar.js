import {useNavigate} from "react-router-dom";
import "../css/Global.css"

export default function Sidebar() {

    const navigate = useNavigate();

    const navigateToAccountInfo = () => {
        navigate("/accountInfo")
    }

    return (
        <div style={{ height: "100vh",width:"30vh", backgroundColor: "white" }}>
            <p>>Settings</p>
            <div className={"site-navbar"} style={{display: "flex", flexDirection: "column"}}>
                <button onClick={navigateToAccountInfo}>Account Information</button>
                <button onClick={navigateToAccountInfo}>Purchase History</button>
                <button onClick={navigateToAccountInfo}>Logout</button>
            </div>
        </div>

    )
}