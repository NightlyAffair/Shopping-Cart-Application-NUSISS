import "../css/Global.css"
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";

export default function AccountInfo() {

    return(
        <div>
            <Header/>
            <div style={{display:"flex", flexDirection:"row"}}>
                <Sidebar/>
                <div>
                    <input/>
                </div>
            </div>

        </div>
    )
}