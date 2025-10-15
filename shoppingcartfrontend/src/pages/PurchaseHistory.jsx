import "../css/style.css"
import "../css/displayProducts.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/NavBar";
import {useNavigate} from 'react-router-dom'
/**
 * Purchase History Class
 * Author: Aung Kyaw Kyaw
 * Date: 2025-10-02
 * Modifier by : Thae Thae Hsu (review feature)
 * Last Modified by :
 * Last Modified: 2025-10-15 10:00
 */
const PurchaseHistory = () => {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  const [showExistingReview, setShowExistingReview] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [selectedProductId, setSelectedProductId] = useState(null);
  const [customerId, setCustomerId] = useState(1);
  const [reviewContent, setReviewContent] = useState('');
  const [rating, setRating] = useState(5);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const navigate = useNavigate()

  useEffect(() => {
    axios
        .get("http://localhost:8080/api/purchaseHistory/customer" , { withCredentials: true })
        .then((res) => {
          console.log(res.data);
          const data = Array.isArray(res.data) ? res.data : [res.data];
          setOrders(data);
        })
        .catch((error) => {
          console.error("Error:", error);
          navigate("/login");
        })
        .finally(() => {
          setLoading(false);
        });
  }, []);
  if (loading) return <p>Loading purchase history...</p>;
  if (error) return <p>(error)</p>;

  const openReviewForm = async (orderId, productId, custId) => {
    setSelectedOrderId(orderId);
    setSelectedProductId(productId);

    if (custId !== undefined && custId !== null) {
      setCustomerId(custId);
    }

    try {
      const response = await axios.get(`http://localhost:8080/api/reviews/product/${productId}`);
      const existingReviews = response.data.find((review) => review.customerId === customerId && review.orderId === orderId);

      if (existingReviews) {
        // show existing review
        setReviewContent(existingReviews.description);
        setRating(existingReviews.rating);
        setShowExistingReview(true);
        setShowForm(false);
      } else {
        // show form for new review
        setShowExistingReview(false);
        setShowForm(true);
      }
    } catch (err) {
      console.error('Error fetching existing review: ', err);
      setShowExistingReview(false);
      setShowForm(true);
    }
  };
  const handleRefund = (orderId, productId) => {
    console.log("Refund requested for order:", orderId, "product:", productId);
    // Add your refund logic here
    alert(`Refund requested for Order #${orderId}, Product #${productId}`);
  };

  const submitReview = async () => {
    if (!selectedOrderId || !selectedProductId) return;

    setErrorMessage('');
    setSuccessMessage('');
    setIsSubmitting(true);

    const payload = {
      rating: Number(rating),
      description: reviewContent,
    };

    try {
      const url = `http://localhost:8080/api/reviews/add/${selectedProductId}/${customerId}/${selectedOrderId}`;
      const response = await axios.post(url, payload, {
        headers: { 'Content-Type': 'application/json' }
      });
      setSuccessMessage('Review submitted successfully!');

      setTimeout(() => {
        setShowForm(false);
        setReviewContent('');
        setRating(5);
        setSelectedOrderId(null);
        setSelectedProductId(null);
        setErrorMessage('');
        setSuccessMessage('');
      }, 2000);
    } catch (err) {
      console.error('Error submitting review:', err);
    }
  };

  const closeModal = () => {
    setShowForm(false);
    setShowExistingReview(false);
    setErrorMessage('');
    setSuccessMessage('');
    setReviewContent('');
    setRating(5);
    setSelectedOrderId(null);
    setSelectedProductId(null);
  };

  const closeExistingReviewModal = () => {
    setShowExistingReview(false);
    setReviewContent('');
    setRating(5);
    setSelectedOrderId(null);
    setSelectedProductId(null);
  };

  const modalOverlayStyle = {
    position: "fixed",
    top: 0, left: 0, right: 0, bottom: 0,
    backgroundColor: "rgba(0,0,0,0.5)",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    zIndex: 9999
  };

  const modalStyle = {
    width: 520,
    maxWidth: '95%',
    backgroundColor: "#fff",
    padding: 20,
    borderRadius: 8,
    boxShadow: "0 2px 10px rgba(0,0,0,0.1)"
  };

  const closeBtnStyle = {
    position: "absolute",
    top: 8,
    right: 12,
    background: 'transparent',
    border: 'none',
    fontSize: 22,
    cursor: 'pointer'
  };

  return (
      <div style={{ display: "flex", width: "100vw", flexDirection: "column" }}>
        <Header />
        <Navbar />
        <div style={{ display:"flex", flexDirection:"row", flex: 1 }}>
          <Sidebar />

          <div style={{ padding: "20px" }}>
            <h2 style={{ marginBottom: "20px" }}>Purchase History</h2>
            {orders.map((order) => (
                <div key={order.orderId} style={{ marginBottom: "40px" }}>
                  <h3>
                    Order #{order.orderId} —{" "}
                    <span style={{ color: "#666" }}>{order.status}</span>
                  </h3>
                  <p>
                    <strong>Purchase Date:</strong> {new Date(order.purchaseDate).toLocaleDateString()} <br />

                  </p>
                  <table
                      style={{
                        width: "100%",
                        borderCollapse: "collapse",
                        marginTop: "10px",
                      }}
                  >
                    <thead>
                    <tr style={{ background: "#f2f2f2", textAlign: "left" }}>
                      <th style={{ padding: "8px", border: "1px solid #ddd" }}>Photo</th>
                      <th style={{ padding: "8px", border: "1px solid #ddd" }}>Product</th>
                      <th style={{ padding: "8px", border: "1px solid #ddd" }}>Price</th>
                      <th style={{ padding: "8px", border: "1px solid #ddd" }}>Quantity</th>
                      <th style={{ padding: "8px", border: "1px solid #ddd" }}>Subtotal</th>
                      <th style={{ padding: "8px", border: "1px solid #ddd" }}>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {order.orderDetails?.map((detail) => {
                      const product = detail.product || {};
                      const subtotal = product.unitPrice
                          ? product.unitPrice * detail.quantity
                          : 0;
                      //Go to product detail page (thymeleaf)
                      const handleProductClick = (productId) => {
                        window.location.href =
                            "http://localhost:8080/products/details/" + productId;
                      };
                      return (
                          <tr key={`${order.orderId}-${detail.productId}`}>
                            <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                              {product.imageUrl ? (
                                  <img
                                      src={product.imageUrl}
                                      alt={product.productName}
                                      onClick={() =>
                                          handleProductClick(product.productId)
                                      }
                                      style={{
                                        width: "60px",
                                        height: "60px",
                                        objectFit: "cover",
                                        borderRadius: "8px",
                                        cursor: "pointer"
                                      }}
                                  />
                              ) : (
                                  <span>No image</span>
                              )}
                            </td>
                            <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                              {product.productName || "Unknown Product"}
                            </td>
                            <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                              ${product.unitPrice?.toFixed(2) || "0.00"}
                            </td>
                            <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                              {detail.quantity}
                            </td>
                            <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                              ${subtotal.toFixed(2)}
                            </td>
                            <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                              <button
                                  type="button"
                                  onClick={() => openReviewForm(order.orderId, detail.productId, order.customerId)}
                                  style={{ marginRight: "5px", padding: "5px 10px", background: "#007bff", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}
                              >
                                Review
                              </button>
                              <button
                                  type="button"
                                  onClick={() => handleRefund(order.orderId, detail.productId)}
                                  style={{ padding: "5px 10px", background: "#dc3545", color: "white", border: "none", borderRadius: "4px", cursor: "pointer" }}
                              >
                                Refund
                              </button>
                            </td>
                          </tr>
                      );
                    })}
                    </tbody>
                  </table>

                  {showForm && selectedOrderId === order.orderId && (
                      <div style={modalOverlayStyle} onClick={closeModal}>
                        <div style={modalStyle} onClick={(e) => e.stopPropagation()}>
                          <button aria-label="Close review" style={closeBtnStyle} onClick={closeModal}>×</button>
                          <h3 style={{marginTop: 0}}>Write Review</h3>
                          {successMessage && (<div style={{backgroundColor: '#d4edda', color: '#155724', padding: '10px', borderRadius: '4px', marginBottom: '12px', border: '1px solid #c3e6cb'}}>{successMessage}</div>)}
                          {errorMessage && (<div style={{backgroundColor: '#f8d7da', color: '#721c24', padding: '10px', borderRadius: '4px', marginBottom: '12px', border: '1px solid #f5c6cb'}}>{errorMessage}</div>)}
                          <div style={{marginBottom: 8}}>
                            <label style={{display: 'block', marginBottom: 6}}>Your review</label>
                            <textarea value={reviewContent} onChange={(e) => setReviewContent(e.target.value)} placeholder="Write your review..." style={{width: '100%', minHeight: 100, padding: 8, boxSizing: 'border-box'}} disabled={isSubmitting || successMessage} />
                          </div>
                          <div style={{ display: 'flex', alignItems: 'center', gap: 12, marginBottom: 12 }}>
                            <label style={{ minWidth: 60 }}>Rating</label>
                            <select value={rating} onChange={(e) => setRating(Number(e.target.value))} disabled={isSubmitting || successMessage}>
                              <option value={5}>Very Good</option>
                              <option value={4}>Good</option>
                              <option value={3}>Normal</option>
                              <option value={2}>Bad</option>
                              <option value={1}>Very Bad</option>
                            </select>
                            <div style={{ marginLeft: 'auto', color: '#666', fontSize: 14 }}>
                              Order: {selectedOrderId} • Product: {selectedProductId}
                            </div>
                          </div>
                          <div style={{ display: 'flex', gap: 8, justifyContent: 'flex-end' }}>
                            <button onClick={submitReview} disabled={isSubmitting || successMessage}
                                    style={{ padding: '8px 14px',
                                      background: (isSubmitting || successMessage) ? '#6c757d' : '#007bff',
                                      color: '#fff', border: 'none', borderRadius: 4, cursor: (isSubmitting || successMessage) ? 'not-allowed' : 'pointer'}}>
                              {isSubmitting ? 'Submitting...' : successMessage ? 'Submitted!' : 'Submit'}
                            </button>
                            <button onClick={closeModal} style={{ padding: '8px 14px', background: '#f0f0f0', border: 'none', borderRadius: 4 }}>
                              Cancel
                            </button>
                          </div>
                        </div>
                      </div>
                  )}

                  { showExistingReview && (
                      <div style={modalOverlayStyle} onClick={closeExistingReviewModal}>
                        <div style={modalStyle} onClick={(e) => e.stopPropagation()}>
                          <button aria-label="Close" style={closeBtnStyle} onClick={closeExistingReviewModal}>×</button>
                          <h3 style={{marginTop: 0}}>Your Existing Review</h3>
                          <div style={{marginBottom: 12}}>
                            <label><strong>Rating: </strong>{rating}/5</label>
                            <p style={{marginTop: 8, padding: '10px', backgroundColor: '#f8f9fa', borderRadius: '4px', border: '1px solid #dee2e6'}}>{reviewContent}</p>
                          </div>
                          <div style={{marginButtom:12, fontSize: 14, color: '#666'}}>
                            Order: {selectedOrderId} • Product: {selectedProductId}
                          </div>
                          <button onClick={closeExistingReviewModal} style={{ padding: '8px 14px', background: '#007bff', color: 'white', border: 'none', borderRadius: 4}}>Close</button>
                        </div>
                      </div>
                  )}
                </div>
            ))}
          </div>
          {/* No orders found but no error */}
          {!loading && !error && orders.length === 0 && (
              <p>No purchase history found</p>
          )}
        </div>
      </div>
  );
};

export default PurchaseHistory;