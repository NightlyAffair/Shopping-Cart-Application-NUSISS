import {useNavigate} from "react-router-dom";

export default function LoginRedirect() {
    const navigate = useNavigate();
    function navLogin() {
        navigate("/login");
    }
    function navSignup() {
        navigate("/signup");
    }
    return(
        <div>
            <h1>Please login to view this page</h1>
            <button onClick={navLogin}>Login</button>
            <button onClick={navSignup}>Register</button>
        </div>
    )
}