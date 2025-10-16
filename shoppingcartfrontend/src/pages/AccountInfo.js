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
    const [successMessage, setSuccessMessage] = useState("")
    const [errorMessage, setErrorMessage] = useState("")
    const [passwordError, setPasswordError] = useState("")
    const [usernameError, setUsernameError] = useState("")
    const [emailError, setEmailError] = useState("")
    const [fullNameError, setFullNameError] = useState("")

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

    const validateUsername = (uname) => {
        if (!uname || uname.trim() === "") {
            return "Username cannot be empty";
        }
        if (uname.length < 4) {
            return "Username must be at least 4 characters";
        }
        if (uname.length > 20) {
            return "Username must be 4–20 characters";
        }
        return "";
    };

    const validateEmail = (emailAddr) => {
        if (!emailAddr || emailAddr.trim() === "") {
            return "Email cannot be empty";
        }

        const hasAtSymbol = emailAddr.includes('@');

        if (!hasAtSymbol) {
            return "Invalid email format";
        }
        return "";
    };

    const validateFullName = (name) => {
        if (!name || name.trim() === "") {
            return "Full name is required";
        }
        return "";
    };

    const validatePassword = (pwd) => {
        if (!pwd || pwd.trim() === "") {
            return "Password cannot be empty";
        }
        if (pwd.length < 6) {
            return "Password must be at least 6 characters";
        }
        if (pwd.length > 1000) {
            return "Password must be less than 1000 characters";
        }
        return "";
    };

    const handleUsernameChange = (e) => {
        const newUsername = e.target.value;
        setUsername(newUsername);
        const error = validateUsername(newUsername);
        setUsernameError(error);
    };

    const handleEmailChange = (e) => {
        const newEmail = e.target.value;
        setEmail(newEmail);
        const error = validateEmail(newEmail);
        setEmailError(error);
    };

    const handleFullNameChange = (e) => {
        const newFullName = e.target.value;
        setFullName(newFullName);
        const error = validateFullName(newFullName);
        setFullNameError(error);
    };

    const handlePasswordChange = (e) => {
        const newPassword = e.target.value;
        setPassword(newPassword);
        const error = validatePassword(newPassword);
        setPasswordError(error);
    };

    const saveCustomerInfo = async () => {
        setErrorMessage("");
        setSuccessMessage("");

        // Validate all fields before saving
        const unameError = validateUsername(username);
        const emailErr = validateEmail(email);
        const nameError = validateFullName(fullName);
        const pwdError = validatePassword(password);

        setUsernameError(unameError);
        setEmailError(emailErr);
        setFullNameError(nameError);
        setPasswordError(pwdError);

        if (unameError || emailErr || nameError || pwdError) {
            setErrorMessage("Please fix all errors before saving.");
            return;
        }

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
                setSuccessMessage("Account information updated successfully!");
                setTimeout(() => {
                    setSuccessMessage("");
                }, 3000);
            } else {
                setErrorMessage("Failed to update account information. Please try again.");
                setTimeout(() => {
                    setErrorMessage("");
                }, 3000);
            }
        } catch (e) {
            console.error("Error saving customer info:", e);
            setErrorMessage("Failed to update account information. Please try again.");
            setTimeout(() => {
                setErrorMessage("");
            }, 3000);
        }
    }

    return (
        <div style={{display: 'flex', flexDirection: 'column', width: '100vw', height: '100vh'}}>
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
                            {successMessage && (
                                <div className="alert alert-success" style={{marginLeft: "20px", maxWidth: "400px"}}>
                                    {successMessage}
                                </div>
                            )}
                            {errorMessage && (
                                <div className="alert alert-danger" style={{marginLeft: "20px", maxWidth: "400px"}}>
                                    {errorMessage}
                                </div>
                            )}
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Full Name</p>
                                <input
                                    value={fullName}
                                    onChange={handleFullNameChange}
                                    className={fullNameError ? "form-control is-invalid" : "form-control"}
                                    style={{width: "100%"}}
                                />
                                {fullNameError && (
                                    <small className="text-danger mt-1">{fullNameError}</small>
                                )}
                            </div>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Username</p>
                                <input
                                    value={username}
                                    onChange={handleUsernameChange}
                                    className={usernameError ? "form-control is-invalid" : "form-control"}
                                    style={{width: "100%"}}
                                />
                                {usernameError && (
                                    <small className="text-danger mt-1">{usernameError}</small>
                                )}
                            </div>
                            <div style={{
                                display: "flex",
                                flexDirection: "column",
                                marginLeft: "20px",
                                marginBottom: "40px",
                                maxWidth: "400px"
                            }}>
                                <p>Email</p>
                                <input
                                    value={email}
                                    onChange={handleEmailChange}
                                    className={emailError ? "form-control is-invalid" : "form-control"}
                                    style={{width: "100%"}}
                                />
                                {emailError && (
                                    <small className="text-danger mt-1">{emailError}</small>
                                )}
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
                                <input
                                    value={password}
                                    onChange={handlePasswordChange}
                                    className={passwordError ? "form-control is-invalid" : "form-control"}
                                    style={{width: "100%"}}
                                />
                                {passwordError && (
                                    <small className="text-danger mt-1">{passwordError}</small>
                                )}
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