import "../css/style.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import React, { useEffect, useState } from "react";
import axios from "axios";
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";
import NavBar from "../components/NavBar";

/**
 * Purchase History Page (React)
 * Author: Aung Kyaw Kyaw
 * Modified by: Thae Thae Hsu
 * Last Updated: 2025-10-15
 */

const PurchaseHistory = () => {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [showForm, setShowForm] = useState(false);
  const [showExistingReview, setShowExistingReview] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [selectedProductId, setSelectedProductId] = useState(null);
  const [customerId, setCustomerId] = useState(1);
  const [reviewContent, setReviewContent] = useState("");
  const [rating, setRating] = useState(5);
  const [isSubmitting, setIsSubmitting] = useState(false);

  // Load purchase history
  const loadOrders = async () => {
    try {
      const res = await axios.get("http://localhost:8080/api/purchaseHistory/customer", {withCredentials: true});
      const data = Array.isArray(res.data) ? res.data : [res.data];
      setOrders(data);
    } catch (err) {
      console.error("Error loading orders:", err);
      setErrorMessage("Failed to load purchase history.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadOrders();
  }, []);

  // Handle refund
  const handleRefund = async (orderId, productId, refunded) => {
    if (refunded) {
      alert(`Refund already processed for Order #${orderId}, Product #${productId}`);
      return;
    }
    try {
      const res = await axios.post(`http://localhost:8080/api/purchaseHistory/refund/${orderId}/${productId}`, {}, { withCredentials: true });
      if (res.status === 200) {
        setSuccessMessage(`Refund requested for Order #${orderId}, Product #${productId}`);
        loadOrders();
      } else {
        alert("Refund failed. Please try again.");
      }
    } catch (err) {
      console.error("Refund error:", err);
    }
  };

  // Open review modal
  const openReviewForm = async (orderId, productId, custId) => {
    setSelectedOrderId(orderId);
    setSelectedProductId(productId);
    if (custId) setCustomerId(custId);

    try {
      const res = await axios.get(`http://localhost:8080/api/reviews/product/${productId}`);
      const existingReview = res.data.find(
          (r) => r.customerId === customerId && r.orderId === orderId
      );

      if (existingReview) {
        setReviewContent(existingReview.description);
        setRating(existingReview.rating);
        setShowExistingReview(true);
        setShowForm(false);
      } else {
        setShowExistingReview(false);
        setShowForm(true);
      }
    } catch (err) {
      console.error("Error fetching review:", err);
      setShowExistingReview(false);
      setShowForm(true);
    }
  };

  // Submit new review
  const submitReview = async () => {
    if (!selectedOrderId || !selectedProductId) return;
    setErrorMessage("");
    setSuccessMessage("");
    setIsSubmitting(true);

    const payload = {
      rating: Number(rating),
      description: reviewContent,
    };

    try {
      const url = `http://localhost:8080/api/reviews/add/${selectedProductId}/${customerId}/${selectedOrderId}`;
      const res = await axios.post(url, payload, {
        headers: { "Content-Type": "application/json" },
      });
      if (res.status === 200) {
        setSuccessMessage("Review submitted successfully!");
        setTimeout(() => {
          setShowForm(false);
          setReviewContent("");
          setRating(5);
          setSelectedOrderId(null);
          setSelectedProductId(null);
        }, 2000);
      }
    } catch (err) {
      console.error("Error submitting review:", err);
      if (err.response?.status === 400)
        setErrorMessage("Review already exists for this product/order.");
      else setErrorMessage("Failed to submit review.");
    } finally {
      setIsSubmitting(false);
    }
  };

  // Close modals
  const closeModal = () => {
    setShowForm(false);
    setShowExistingReview(false);
    setReviewContent("");
    setRating(5);
    setSelectedOrderId(null);
    setSelectedProductId(null);
    setErrorMessage("");
    setSuccessMessage("");
  };

  if (loading) return <p className="text-center mt-4">Loading purchase history...</p>;

  return (
      <div style={{display: 'flex', flexDirection: 'column', width: '100vw', minHeight: '100vh'}}>
        <div>
          <Header />
        </div>
        <div className="navbar navbar-expand-lg navbar-dark site-navbar" style={{paddingTop:'150px'}}>
          <NavBar />
        </div>
        <div className="d-flex flex-grow-1">
          <Sidebar />
          <main className="flex-grow-1 p-4">
            <div className="container-fluid">
              <h2 className="mb-4 text-primary">Purchase History</h2>

              {orders.length === 0 && (
                  <p className="text-muted">No purchase history found.</p>
              )}

              {orders.map((order) => (
                  <div key={order.orderId} className="card mb-4 shadow-sm">
                    <div className="card-body">
                      <h5>
                        Order #{order.orderId} —{" "}
                        <span className="text-muted">{order.status}</span>
                      </h5>
                      <p>
                        <strong>Purchase Date:</strong>{" "}
                        {new Date(order.purchaseDate).toLocaleDateString()}
                      </p>

                      <div className="table-responsive">
                        <table className="table table-bordered align-middle">
                          <thead className="table-light">
                          <tr>
                            <th>Photo</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Qty</th>
                            <th>Subtotal</th>
                            <th>Actions</th>
                          </tr>
                          </thead>
                          <tbody>
                          {order.orderDetails?.map((detail) => {
                            const product = detail.product || {};
                            const subtotal = (product.unitPrice || 0) * detail.quantity;

                            return (
                                <tr key={`${order.orderId}-${detail.productId}`}>
                                  <td>
                                    <img
                                        src={product.imageUrl}
                                        alt={product.productName}
                                        className="product-thumb"
                                        onClick={() =>
                                            (window.location.href = `http://localhost:8080/products/details/${product.productId}`)
                                        }
                                        style={{ cursor: "pointer" }}
                                    />
                                  </td>
                                  <td>{product.productName}</td>
                                  <td>${product.unitPrice?.toFixed(2) || "0.00"}</td>
                                  <td>{detail.quantity}</td>
                                  <td>${subtotal.toFixed(2)}</td>
                                  <td>
                                    <button
                                        className="btn btn-primary btn-sm me-2"
                                        onClick={() =>
                                            openReviewForm(order.orderId, detail.productId, order.customerId)
                                        }
                                    >
                                      Review
                                    </button>
                                    <button
                                        className={`btn btn-sm ${
                                            detail.refunded ? "btn-success" : "btn-danger"
                                        }`}
                                        onClick={() =>
                                            handleRefund(order.orderId, detail.productId, detail.refunded)
                                        }
                                    >
                                      {detail.refunded ? "Refunded" : "Refund"}
                                    </button>
                                  </td>
                                </tr>
                            );
                          })}
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
              ))}
            </div>
          </main>
        </div>

        {/* Review Modal */}
        {(showForm || showExistingReview) && (
            <div
                className="position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center"
                style={{ background: "rgba(0,0,0,0.5)", zIndex: 9999 }}
                onClick={closeModal}
            >
              <div
                  className="bg-white p-4 rounded shadow"
                  style={{ width: "500px", maxWidth: "95%" }}
                  onClick={(e) => e.stopPropagation()}
              >
                <button
                    className="btn-close position-absolute top-0 end-0 m-3"
                    onClick={closeModal}
                ></button>

                {showExistingReview ? (
                    <>
                      <h5>Your Existing Review</h5>
                      <p><strong>Rating:</strong> {rating}/5</p>
                      <div className="p-3 bg-light border rounded">{reviewContent}</div>
                    </>
                ) : (
                    <>
                      <h5>Write a Review</h5>
                      {successMessage && (
                          <div className="alert alert-success">{successMessage}</div>
                      )}
                      {errorMessage && (
                          <div className="alert alert-danger">{errorMessage}</div>
                      )}

                      <div className="mb-3">
                        <label className="form-label">Your Review</label>
                        <textarea
                            className="form-control"
                            value={reviewContent}
                            onChange={(e) => setReviewContent(e.target.value)}
                            rows="3"
                            disabled={isSubmitting}
                        />
                      </div>

                      <div className="mb-3 d-flex align-items-center gap-3">
                        <label>Rating</label>
                        <select
                            className="form-select w-auto"
                            value={rating}
                            onChange={(e) => setRating(Number(e.target.value))}
                            disabled={isSubmitting}
                        >
                          <option value={5}>Very Good</option>
                          <option value={4}>Good</option>
                          <option value={3}>Normal</option>
                          <option value={2}>Bad</option>
                          <option value={1}>Very Bad</option>
                        </select>
                      </div>

                      <div className="d-flex justify-content-end gap-2">
                        <button
                            className="btn btn-secondary"
                            onClick={closeModal}
                            disabled={isSubmitting}
                        >
                          Cancel
                        </button>
                        <button
                            className="btn btn-primary"
                            onClick={submitReview}
                            disabled={isSubmitting}
                        >
                          {isSubmitting ? "Submitting..." : "Submit"}
                        </button>
                      </div>
                    </>
                )}
              </div>
            </div>
        )}
      </div>
  );
};

export default PurchaseHistory;