import "../css/style.css"
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";
import NavBar from "../components/NavBar";
import {useEffect, useState} from "react";
import axios from "axios";
/**
 * AccountInfoPage
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */
export default function AccountInfo() {
    const [customerInfo, setCustomerInfo] = useState
    ()
    const [loading,setLoading] = useState(true);

    const loadAccountInfo = async () => {
        try{
            const response = await axios.get("http://localhost:8080/api/account-info/1") // Replace with actual user ID
            console.log(response.data)
            setCustomerInfo(response.data)
            setLoading(false);
        } catch (e) {
            console.error("Error fetching customer info:", e);
        }
    }
    useEffect(() => {
        loadAccountInfo();

    }, []);

    if(loading) {
        return(
            <div>
                <p>Loading...</p>
            </div>
        )
    }

    function changeName(string) {
        const newCustomer = {...customerInfo, fullName: string};
        setCustomerInfo(newCustomer);
    }
    function changeUserName(string) {
        const newCustomer = {...customerInfo, userName: string};
        setCustomerInfo(newCustomer);
    }
    function changePassword(string) {
        const newCustomer = {...customerInfo, password: string};
        setCustomerInfo(newCustomer);
    }

    const saveCustomerInfo = async () => {
        try{
            const response = await axios.post("http://localhost:8080/api/account-info/save", customerInfo);
            if(response.status === 200){
                alert("success");
            } else {
                alert("error");
            }
        } catch (e) {
            console.error("Error saving customer info:", e);
        }
    }

    return(
        <div style={{display:'flex', flexDirection:'column', width:'100vw', minHeight:'100vh'}}>
            <div>
                <Header />
            </div>
            <div>
                <NavBar />
            </div>



            <div style={{display:"flex", flexDirection:"row", flex: 1}}>
                <Sidebar/>
                <div style={{display:"flex", flexDirection:"column", justifyContent:"flex-start", flex: 1, marginTop:"10px", padding:"20px"}}>
                    <div style={{display: "flex", flexDirection: "column", marginLeft:"20px", marginBottom:"40px", maxWidth:"400px"}}>
                        <p>Full Name</p>
                        <input value={customerInfo.fullName} onChange={(e) => changeName(e.target.value)} style={{width:"100%"}}/>
                    </div>
                    <div style={{display: "flex", flexDirection: "column", marginLeft:"20px", marginBottom:"40px", maxWidth:"400px"}}>
                        <p >Username</p>
                        <input value={customerInfo.userName} onChange={(e) => changeUserName(e.target.value)} style={{width:"100%"}}/>
                    </div>
                    <div style={{display: "flex", flexDirection: "column", marginLeft:"20px", marginBottom:"40px", maxWidth:"400px"}}>
                        <p>Password</p>
                        <input value={customerInfo.password} onChange={(e) => changePassword(e.target.value)} style={{width:"100%"}}/>
                    </div>
                    <button type={"submit"} onClick={saveCustomerInfo} style={{marginLeft:"20px", maxWidth:"400px", padding:"10px 20px"}}>submit</button>
                </div>
            </div>

        </div>
    )
}