import "../css/Global.css"
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../components/Header';
import Sidebar from "../components/Sidebar";

/**
 * PurchaseHistoryPage
 * Author: Aung Kyaw Kyaw 
 * Date: 2025-10-10
 * Modifier by :
 * Last Modified by :
 * Last Modified: 2025-10-10 11:00
 */

export default function PurchaseHistory() {
  const [orders, setOrders] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [selectedProductId, setSelectedProductId] = useState(null);
  const [reviewContent, setReviewContent] = useState('');
  const [rating, setRating] = useState(5);

  useEffect(() => {
    axios.get("http://localhost:8080/api/purchaseHistory/customer/1") // Replace with actual user ID
      .then(response => {
        console.log(response.data);
        setOrders(response.data);
      })
      .catch(e => {
        console.error("Error fetching purchase history:", e);
      });
  }, []);
  //end
  //   axios.get("http://localhost:8080/api/purchaseHistory/customer/1") // Replace with actual user ID
  //     .then(response => {
  //       setOrders(response.data);
  //     })
  //     .catch(e => {
  //       console.error("Error fetching purchase history:", e);
  //     });
  // }, []);

  const openReviewForm = (orderId, productId) => {
    setSelectedOrderId ({orderId, productId});
    //setCustomerId(customerId);
    setShowForm(true);
  };


  //Add submitReview implementation so (thae)
  const submitReview = async () => {
    if (!selectedOrderId || !selectedProductId) return;

    const payload = {
      rating: Number(rating),
      description: reviewContent,
      productId: selectedProductId,
      customerId:customerId,
      orderId: selectedOrderId
    };



    try {
      const url = `http://localhost:8080/api/reviews/add/${selectedProductId}/${customerId}/${selectedOrderId}`;
      const resp =await axios.post(url, payload, { headers: { 'Content-Type': 'application/json' }});
      console.log('Submitting review payload:', resp.data);
      // reset form
      setShowForm(false);
      setReviewContent('');
      setRating(5);
      setSelectedOrderId(null);
      setSelectedProductId(null);
    } catch (err) {
      console.error('Error submitting review:', err.response?.data || err.message);
      alert('Failed to submit review. Please try again.');
    }
  };

  //add helper to close modal (thae)
  const closeModal = () => {
    setShowForm(false);
  };

  //inline styles for modal (thae)
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
    <div>
    <Header />
    <div className="container">
      <div className="row">
        <div className="col">
          <div>
            <div className="table-responsive">
              <table className="table table-bordered">
                <thead>
                  <tr>
                    <th scope="col">Order ID</th>
                    <th scope="col">Product</th>
                    <th scope="col">Date</th>
                    <th scope="col">Total Amount</th>
                    <th scope="col">Quantity</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                  {orders.map(order =>
                    (order.orderDetails.map((item, idx) => (
                      <tr key={`${order.orderId}-${idx}`}>
                        <td className="id">{order.orderId}</td>
                        <td>
                          <div className="d-flex align-items-center">
                            <div>
                              <img src="https://via.placeholder.com/60" className="img-fluid rounded-3" alt="Product" />
                            </div>
                            <div className="flex-column ms-3">
                              <a href="productDetails.html">
                                <h6>{item.product.productName}</h6>
                              </a>
                              <p>Category: {item.product.category}</p>
                            </div>
                          </div>
                        </td>
                        <td className="date"><span>{order.orderDate}</span></td>
                        <td className="price"><span>${order.totalAmount}</span></td>
                        <td className="quantity"><span>{item.quantity}</span></td>
                        <td>
                          <button type="button" onClick={() => openReviewForm(order.orderId, item.product.productId)}>
                            Review
                          </button>
                        </td>
                        <td>
                          
                        </td>
                      </tr>
                    ))
                  )}
                </tbody>
              </table>
              {/* show form as modal overlay when click review button */}
                 {showForm && (
        <div
                    style={modalOverlayStyle}
                    onClick={closeModal} /* clicking backdrop closes modal */
                  >
                    <div
                      style={modalStyle}
                      onClick={(e) => e.stopPropagation()} /* prevent backdrop close when clicking inside */
                    >
                      <button aria-label="Close review" style={closeBtnStyle} onClick={closeModal}>×</button>

                      <h3 style={{ marginTop: 0 }}>Write Review</h3>

                      <div style={{ marginBottom: 8 }}>
                        <label style={{ display: 'block', marginBottom: 6 }}>Your review</label>
                        <textarea
                          value={reviewContent}
                          onChange={(e) => setReviewContent(e.target.value)}
                          placeholder="Write your review..."
                          style={{ width: '100%', minHeight: 100, padding: 8, boxSizing: 'border-box' }}
                        />
                      </div>
                       <div style={{ display: 'flex', alignItems: 'center', gap: 12, marginBottom: 12 }}>
                        <label style={{ minWidth: 60 }}>Rating</label>
                        <select value={rating} onChange={(e) => setRating(Number(e.target.value))}>
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
                        <button onClick={submitReview} style={{ padding: '8px 14px', background: '#007bff', color: '#fff', border: 'none', borderRadius: 4 }}>Submit</button>
                        <button onClick={closeModal} style={{ padding: '8px 14px', background: '#f0f0f0', border: 'none', borderRadius: 4 }}>Cancel</button>
                      </div>
                    </div>
                  </div>
                )}
           
            </div>
          </div>
        </div>
      </div>
    </div>
      </div>
  );
}