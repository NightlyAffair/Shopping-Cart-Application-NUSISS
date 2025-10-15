import "../css/style.css"
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";
import NavBar from "../components/NavBar";
import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
/**
 * AccountInfoPage
 * Author: Nithvin Leelakrishnan
 * Date: 2025-10-09
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-09 14:00
 */

const LoadingPage = () => {
    return(
        <div>
            <p>Loading...</p>
        </div>
    )
}

export default function AccountInfo() {
    const [customerInfo, setCustomerInfo] = useState(null)
    const [loading,setLoading] = useState(true);
    const navigate = useNavigate();

    const [fullName, setFullName] = useState("")
    const [email, setEmail] = useState("")
    const [address, setAddress] = useState("")
    const [password, setPassword] = useState("")
    const [username, setUsername] = useState("")

    const loadAccountInfo = async () => {
        try{
            const response = await axios.get("http://localhost:8080/api/account-info", {withCredentials:true})
            console.log(response)
            setCustomerInfo(response.data)
            setFullName(response.data.fullName)
            setEmail(response.data.email)
            setUsername(response.data.userName)
            setPassword(response.data.password)
            setAddress(response.data.address)
            setLoading(false);

        } catch (e) {
            console.error("Error fetching customer info:", e);
            navigate("/login")
        }
    }
    useEffect(() => {
        loadAccountInfo();
    }, []);

    const saveCustomerInfo = async () => {
        const updatedCustomerInfo = {
            ...customerInfo,
            fullName: fullName,
            email: email,
            address: address,
            password: password,
            userName: username,
        };
        setCustomerInfo(updatedCustomerInfo);
        try{
            const response = await axios.post("http://localhost:8080/api/account-info/save", updatedCustomerInfo);
            if(response.status === 200){
                alert("success");
            } else {
                alert("error");
            }
        } catch (e) {
            console.error("Error saving customer info:", e);
        }
    }

    return (
        <div style={{display: 'flex', flexDirection: 'column', width: '100vw', minHeight: '100vh'}}>
            <div>
                <Header/>
            </div>
            <div>
                <NavBar/>
            </div>


            <div style={{display: "flex", flexDirection: "row", flex: 1}}>
                <Sidebar/>
                {
                    loading ? <LoadingPage/> : (
                        <div style={{
                            display: "flex",
                            flexDirection: "column",
                            justifyContent: "flex-start",
                            flex: 1,
                            marginTop: "10px",
                            padding: "20px"
                        }}>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Full Name</p>
                                <input value={fullName} onChange={(e) => setFullName(e.target.value)}
                                       style={{width: "100%"}}/>
                            </div>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Username</p>
                                <input value={username} onChange={(e) => setUsername(e.target.value)}
                                       style={{width: "100%"}}/>
                            </div>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Email</p>
                                <input value={email} onChange={(e) => setEmail(e.target.value)}
                                       style={{width: "100%"}}/>
                            </div>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Address</p>
                                <input value={address} onChange={(e) => setAddress(e.target.value)}
                                       style={{width: "100%"}}/>
                            </div>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Password</p>
                                <input value={password} onChange={(e) => setPassword(e.target.value)}
                                       style={{width: "100%"}}/>
                            </div>
                            <button type={"submit"} onClick={saveCustomerInfo}
                                    style={{marginLeft: "20px", maxWidth: "400px", padding: "10px 20px"}}>submit
                            </button>
                        </div>
                    )
                }
            </div>

        </div>
    )
}