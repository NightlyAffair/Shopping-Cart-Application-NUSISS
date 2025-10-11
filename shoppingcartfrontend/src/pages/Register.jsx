import React, { useState } from 'react';
import { Link } from 'react-router-dom';

function Register() {
    const [formData, setFormData] = useState({
        username: '',
        fullName: '',
        email: '',
        password: '',
        confirmPassword: '',
        agreeTerms: false,
    });

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: type === 'checkbox' ? checked : value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log('register data:', formData);
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
