import "../css/Global.css"
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";

export default function AccountInfo() {

    return(
        <div>
            <Header/>
            <div style={{display:"flex", flexDirection:"row"}}>
                <Sidebar/>
                <div style={{display:"flex", flexDirection:"column", justifyContent:"space-between", height:"20vh"}}>
                    <div style={{display: "flex", flexDirection: "row"}}>
                        <p style={{margin: 10}}>Full Name</p>
                        <input/>
                    </div>
                    <div style={{display: "flex", flexDirection: "row"}}>
                        <p style={{margin: 10}}>Username</p>
                        <input/>
                    </div>
                    <div style={{display: "flex", flexDirection: "row"}}>
                        <p style={{margin: 10}}>Password</p>
                        <input/>
                    </div>

                </div>
            </div>

        </div>
    )
}