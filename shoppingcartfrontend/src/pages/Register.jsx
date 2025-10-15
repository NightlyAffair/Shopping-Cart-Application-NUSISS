import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap-icons/font/bootstrap-icons.css';
import '../css/style.css';
import '../css/login.css';


/**
 * react of register
 * Author: Zhou Jason
 * Date: 2025-10-10
 * Participants: Jason
 * Modified by: Jason
 * Last Modified: 2025-10-13 11:00
 */


function Register() {
    const [formData, setFormData] = useState({
        username: '',
        fullName: '',
        email: '',
        password: '',
        confirmPassword: '',
        address: '',
        agreeTerms: false,
    });

    const [message, setMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value,
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage('');
        setSuccessMessage('');

        if (formData.password !== formData.confirmPassword) {
            setMessage('Passwords do not match.');
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/api/register', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    userName: formData.username,
                    fullName: formData.fullName,
                    email: formData.email,
                    password: formData.password,
                    address: formData.address,
                }),
            });

            const data = await response.json();

            if (response.ok && data.success) {
                setSuccessMessage(data.message || 'Registration successful!');
                setTimeout(() => {
                    window.location.href = 'http://localhost:8080/login';
                }, 2000);
            } else {
                setMessage(data.message || 'Registration failed.');
            }
        } catch (error) {
            console.error('Error:', error);
            setMessage('Connection error, please check backend.');
        }
    };



    return (
        <main className="form-signin">
            <div className="text-center mb-3">
                <a href="http://localhost:8080/products" className="back-btn">
                    <i className="bi bi-arrow-left"></i> Back to Homepage
                </a>
            </div>
            <form onSubmit={handleSubmit}>
                <img src="/images/shop-logo.png" alt="Shop @ISS Logo" width="100" height="auto"/>
                <h1 className="h3">Create Account</h1>


                <div className="form-floating mb-3">
                    <input
                        type="text"
                        className="form-control"
                        id="floatingUsername"
                        placeholder="Username"
                        name="username"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                    <label htmlFor="floatingUsername">Username</label>
                </div>


                <div className="form-floating mb-3">
                    <input
                        type="text"
                        className="form-control"
                        id="floatingFullName"
                        placeholder="Full Name"
                        name="fullName"
                        value={formData.fullName}
                        onChange={handleChange}
                        required
                    />
                    <label htmlFor="floatingFullName">Full Name</label>
                </div>

                <div className="form-floating mb-3">
                    <input
                        type="email"
                        className="form-control"
                        id="floatingEmail"
                        placeholder="name@example.com"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                    <label htmlFor="floatingEmail">Email address</label>
                </div>

                <div className="form-floating mb-3">
                    <input
                        type="text"
                        className="form-control"
                        id="floatingAddress"
                        placeholder="Address"
                        name="address"
                        value={formData.address}
                        onChange={handleChange}
                    />
                    <label htmlFor="floatingAddress">Address</label>
                </div>

                {/* Password */}
                <div className="form-floating mb-3">
                    <input
                        type="password"
                        className="form-control"
                        id="floatingPassword"
                        placeholder="Password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                    <label htmlFor="floatingPassword">Password</label>
                </div>

                <div className="form-floating mb-3">
                    <input
                        type="password"
                        className="form-control"
                        id="floatingConfirmPassword"
                        placeholder="Confirm Password"
                        name="confirmPassword"
                        value={formData.confirmPassword}
                        onChange={handleChange}
                        required
                    />
                    <label htmlFor="floatingConfirmPassword">Confirm Password</label>
                </div>

                <div className="form-check text-start mb-3">
                    <input
                        className="form-check-input"
                        type="checkbox"
                        value="agree-terms"
                        id="checkTerms"
                        name="agreeTerms"
                        checked={formData.agreeTerms}
                        onChange={handleChange}
                        required
                    />
                    <label className="form-check-label" htmlFor="checkTerms">
                        I agree to the Terms and Conditions
                    </label>
                </div>

                <button className="btn btn-primary w-100" type="submit" disabled={!formData.agreeTerms}>
                    <i className="bi bi-box-arrow-in-right me-2"></i>Create Account
                </button>


                {message && (
                    <div className="alert alert-info mt-3" role="alert">
                        {message}
                    </div>
                )}

                {successMessage && (
                    <div className="alert alert-success mt-3" role="alert">
                        {successMessage}
                    </div>
                )}

                <div className="text-center mt-3">
                    <p className="mb-0">
                        Already have an account? <a href="http://localhost:8080/login">Sign in</a>
                    </p>
                </div>
            </form>
        </main>
    );
}

export default Register;
