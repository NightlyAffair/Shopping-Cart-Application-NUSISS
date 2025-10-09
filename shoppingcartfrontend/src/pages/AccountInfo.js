import "../css/Global.css"
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";
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
        <div>
            <Header/>
            <div style={{display:"flex", flexDirection:"row"}}>
                <Sidebar/>
                <div style={{display:"flex", flexDirection:"column", justifyContent:"space-between", height:"20vh"}}>
                    <div style={{display: "flex", flexDirection: "row"}}>
                        <p style={{margin: 10}}>Full Name</p>
                        <input value={customerInfo.fullName} onChange={(e) => changeName(e.target.value)}/>
                    </div>
                    <div style={{display: "flex", flexDirection: "row"}}>
                        <p style={{margin: 10}}>Username</p>
                        <input value={customerInfo.userName} onChange={(e) => changeUserName(e.target.value)}/>
                    </div>
                    <div style={{display: "flex", flexDirection: "row"}}>
                        <p style={{margin: 10}}>Password</p>
                        <input value={customerInfo.password} onChange={(e) => changePassword(e.target.value)}/>
                    </div>
                    <button type={"submit"} onClick={saveCustomerInfo}>submit</button>

                </div>
            </div>

        </div>
    )
}