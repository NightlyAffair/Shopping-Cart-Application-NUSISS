import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/style.css';
import '../css/login.css';

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

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value,
        }));
    };

    // form
    const handleSubmit = async (e) => {
        e.preventDefault();

        // validation
        if (formData.password !== formData.confirmPassword) {
            setMessage('❌ Passwords do not match.');
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
                setMessage('✅ ' + data.message);
                alert('Registration successful!');
                // 注册成功后跳转到登录页
                window.location.href = '/login';
            } else {
                setMessage('⚠️ ' + (data.message || 'Registration failed.'));
            }
        } catch (error) {
            console.error('Error:', error);
            setMessage('🚨 Connection error, please check backend.');
        }
    };

    return (
        <main className="form-signin" style={{ maxWidth: 400, margin: 'auto', padding: '1rem' }}>
            <form onSubmit={handleSubmit}>
                <img src="/static/images/shop-logo.png" alt="Shop @ISS Logo" width="auto" height={57} />
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
                    <i className="bi bi-person-plus me-2"></i>Create Account
                </button>

                {message && (
                    <div className="alert alert-info mt-3" role="alert">
                        {message}
                    </div>
                )}

                <div className="text-center mt-3">
                    <p className="mb-0">
                        Already have an account? <Link to="/login">Sign in</Link>
                    </p>
                </div>
            </form>
        </main>
    );
}

export default Register;
