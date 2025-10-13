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
  const [customerId, setCustomerId] = useState(1); // Replace with actual logged-in user ID 
  const [reviewContent, setReviewContent] = useState('');
  const [rating, setRating] = useState(5);
  //state-based error message
  const [errorMessage, setErrorMessage] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');

  useEffect(() => {
     // Use dummy data for testing Review button (no backend needed)
    const dummyOrders = [
      {
        orderId: 1,
        customerId: 1,
        orderDate: "2025-10-01",
        totalAmount: 149.99,
        orderDetails: [
          {
            quantity: 2,
            product: {
              productId: 2,
              productName: "Sample Product A",
              category: "Electronics"
            }
          },
          {
            quantity: 1,
            product: {
              productId: 6,
              productName: "Sample Product B", 
              category: "Books"
            }
          }
        ]
      }
    ];
    console.log('Using dummy data:', dummyOrders);
    setOrders(dummyOrders);
    //real data test start
    // axios.get("http://localhost:8080/api/purchaseHistory/customer/1") // Replace with actual user ID
    //   .then(response => {
    //     console.log(response.data);
    //     setOrders(response.data);
    //   })
    //   .catch(e => {
    //     console.error("Error fetching purchase history:", e);
    //   });
  }, []);
  //real data test end
  const openReviewForm = (orderId, productId, custId) => {
    setSelectedOrderId(orderId);
    setSelectedProductId(productId);
    //setCustomerId(customerId);
    if (custId !== undefined && custId !== null) {
      setCustomerId(custId);
    }
    setShowForm(true);
  };


  //Add submitReview implementation so (thae)
  const submitReview = async () => {
    if (!selectedOrderId || !selectedProductId) return;

    setErrorMessage('');
    setSuccessMessage('');
    setIsSubmitting(true);

    const payload = {
      rating: Number(rating),
      description: reviewContent,
     
    };

    console.log('Submitting review payload:', payload);
    console.log('URL will be:', `http://localhost:8080/api/reviews/add/${selectedProductId}/${customerId}/${selectedOrderId}`);

    try {
      const url = `http://localhost:8080/api/reviews/add/${selectedProductId}/${customerId}/${selectedOrderId}`;
      const response = await axios.post(url, payload, {
        headers: { 'Content-Type': 'application/json' }
      });

      console.log('Review submitted successfully:', response.data);
      setSuccessMessage('Review submitted successfully!');
      
    setTimeout(() => { // reset form
     setShowForm(false);
      setReviewContent('');
      setRating(5);
      setSelectedOrderId(null);
      setSelectedProductId(null);
      setErrorMessage('');
      setSuccessMessage('');
    }, 2000);
   } catch (err) {
      console.error('Error submitting review:', err.response?.data || err.message);
      
      //setErrorMessage
       if (err.response?.status === 400) {
       setErrorMessage('Review already exists for this product in this order.');
      } else if (err.response?.status === 500) {
       setErrorMessage('Server error. Please check if the product and order exist.');
      } else if (err.request) {
       setErrorMessage('Cannot connect to server. Please check if the backend is running.');
      } else {
       setErrorMessage('Failed to submit review: ' + err.message);
      }
    } finally {
     setIsSubmitting(false);
    }
  };

  //add helper to close modal (thae)
  const closeModal = () => {
    setShowForm(false);
    setErrorMessage('');
    setSuccessMessage('');
    setReviewContent('');
    setRating(5);
    setSelectedOrderId(null);
    setSelectedProductId(null);
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
                  {/* {orders.map(order =>
                    (order.orderDetails.map((item, idx) => ( */}
                      {/* //thae add some codes */}
{(orders || []).map(order => {
  const details = order.orderDetails || [];
  return details.map((item, idx) => (
    <tr key={`${order.orderId}-${idx}`}>
      <td className="id">{order.orderId}</td>
      <td>
        <div className="d-flex align-items-center">
          <div>
            <img src="https://via.placeholder.com/60" className="img-fluid rounded-3" alt="Product" />
          </div>
          <div className="flex-column ms-3">
            <a href="productDetails.html">
              {/* <h6>{item.product.productName}</h6> */}
              <h6>{item.product?.productName || 'Unknown Product'}</h6>
            </a>
            {/* <p>Category: {item.product.category}</p> */}
            <p>Category: {item.product?.category || 'N/A'}</p>
          </div>
        </div>
      </td>
      {/* <td className="date"><span>{order.orderDate}</span></td>
      <td className="price"><span>${order.totalAmount}</span></td>
      <td className="quantity"><span>{item.quantity}</span></td> */}
      <td className="date"><span>{order.orderDate || order.purchaseDate || 'N/A'}</span></td>
      <td className="price"><span>${order.totalAmount || order.unitAmount || '0.00'}</span></td>
      <td className="quantity"><span>{item.quantity || 0}</span></td>
      <td>
        {/* <button type="button" onClick={() => openReviewForm(order.orderId, item.product.productId)}>
          Review
        </button> */}
        <button type="button" onClick={() => openReviewForm(order.orderId, item.product?.productId, order.customerId)}>
          Review
        </button>
      </td>
      <td>
        
      </td>
    </tr>
  ));
})}
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
                       {successMessage && (
                       <div style={{ 
                         backgroundColor: '#d4edda',
                         color: '#155724', 
                         padding: '10px', 
                         borderRadius: '4px', 
                         marginBottom: '12px',
                         border: '1px solid #c3e6cb'
                       }}>
                         {successMessage}
                       </div>
                     )}                      
                       {errorMessage && (
                        <div style={{ 
                          backgroundColor: '#f8d7da', 
                          color: '#721c24', 
                          padding: '10px', 
                          borderRadius: '4px', 
                          marginBottom: '12px',
                          border: '1px solid #f5c6cb'
                        }}>
                          {errorMessage}
                        </div>
                      )}


                      <div style={{ marginBottom: 8 }}>
                        <label style={{ display: 'block', marginBottom: 6 }}>Your review</label>
                        <textarea
                          value={reviewContent}
                          onChange={(e) => setReviewContent(e.target.value)}
                          placeholder="Write your review..."
                          style={{ width: '100%', minHeight: 100, padding: 8, boxSizing: 'border-box' }}
                          disabled={isSubmitting || successMessage}
                        />
                      </div>
                       <div style={{ display: 'flex', alignItems: 'center', gap: 12, marginBottom: 12 }}>
                        <label style={{ minWidth: 60 }}>Rating</label>
                        <select value={rating} onChange={(e) => setRating(Number(e.target.value))}
                          disabled={isSubmitting || successMessage}
                          >
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
                        <button 
                        onClick={submitReview}
                        disabled={isSubmitting || successMessage}
                         style={{ 
                           padding: '8px 14px', 
                           background: (isSubmitting || successMessage) ? '#6c757d' : '#007bff', 
                           color: '#fff', 
                           border: 'none', 
                           borderRadius: 4, 
                           cursor: (isSubmitting || successMessage) ? 'not-allowed' : 'pointer' 
                         }}

                           >
                          {isSubmitting ? 'Submitting...' : successMessage ? 'Submitted!' : 'Submit'}
                        </button>
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