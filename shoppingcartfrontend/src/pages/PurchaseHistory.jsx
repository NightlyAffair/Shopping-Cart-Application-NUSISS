import "../css/global.css";
import "../css/displayProducts.css";
import React, { useEffect, useState } from "react";
import axios from "axios";
import Header from "../components/Header";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/NavBar";

const PurchaseHistory = () => {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState(null);
  
  const [showForm, setShowForm] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [selectedProductId, setSelectedProductId] = useState(null);
  const [customerId, setCustomerId] = useState(1);
  const [reviewContent, setReviewContent] = useState('');
  const [rating, setRating] = useState(5);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');


  useEffect(() => {
    axios
      .get("http://localhost:8080/api/purchaseHistory/customer/1")
      .then((res) => {
        console.log(res.data);
        const data = Array.isArray(res.data) ? res.data : [res.data];
        setOrders(data);
      })
      .catch((err) => {
        console.error("Error fetching orders:", err);
        setError("Failed to load purchase history.");
      });
  }, []);

  

  const openReviewForm = (orderId, productId, custId) => {
    setSelectedOrderId(orderId);
    setSelectedProductId(productId);
    
    if (custId !== undefined && custId !== null) {
      setCustomerId(custId);
    }
    setShowForm(true);
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
      console.error('Error submitting review:', err.response?.data || err.message);

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

  const closeModal = () => {
    setShowForm(false);
    setErrorMessage('');
    setSuccessMessage('');
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

<<<<<<< HEAD
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
              </tr>
            </thead>
            <tbody>
              {order.orderDetails?.map((detail) => {
                const product = detail.product || {};
                const subtotal = product.unitPrice
                  ? product.unitPrice * detail.quantity
                  : 0;
                return (
                  <tr key={`${order.orderId}-${detail.productId}`}>
                    <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                      {product.imageUrl ? (
                        <img
                          src={product.imageUrl}
                          alt={product.productName}
                          style={{
                            width: "60px",
                            height: "60px",
                            objectFit: "cover",
                            borderRadius: "8px",
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
                      <button type="button"  onClick={() => openReviewForm(order.orderId, detail.productId, order.customerId)}>Review</button>

                    </td>
                    <td style={{ border: "1px solid #ddd", padding: "8px" }}>
                      <button type="button">Refund</button>

                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>


              {showForm && (
                <div style = {modalOverlayStyle} onClick={closeModal}>
                <div style = {modalStyle} onClick= {(e) => e.stopPropagation()}>
                <button aria-label="Close review" style={closeBtnStyle} onClick={closeModal}>×</button>
                <h3 style={{marginTop: 0}}>Write Review</h3>
                { successMessage && (<div style={{backgroundColor: '#d4edda', color: '#155724', padding: '10px', borderRadius: '4px', marginBottom: '12px', border: '1px solid #c3e6cb'}}>{successMessage}</div>)}
                { errorMessage && (<div style={{backgroundColor: '#f8d7da', color: '#721c24', padding: '10px', borderRadius: '4px', marginBottom: '12px', border: '1px solid #f5c6cb'}}>{errorMessage}</div>)}
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
                    <div style={{ marginLeft: 'auto', color: '#666', fontSize: 14 }}>Order: {selectedOrderId} • Product: {selectedProductId}

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
=======
          {orders.map((order) => (
            <div key={order.orderId} style={{ marginBottom: "40px" }}>
              <h3>
                Order #{order.orderId} —{" "}
                <span style={{ color: "#666" }}>{order.status}</span>
              </h3>
              <p>
                <strong>Purchase Date:</strong> {order.purchaseDate} <br />
                
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
                    <th style={{ padding: "8px", border: "1px solid #ddd" }}>
                      Photo
                    </th>
                    <th style={{ padding: "8px", border: "1px solid #ddd" }}>
                      Product
                    </th>
                    <th style={{ padding: "8px", border: "1px solid #ddd" }}>
                      Price
                    </th>
                    <th style={{ padding: "8px", border: "1px solid #ddd" }}>
                      Quantity
                    </th>
                    <th style={{ padding: "8px", border: "1px solid #ddd" }}>
                      Subtotal
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {order.orderDetails?.map((detail) => {
                    const product = detail.product || {};
                    const subtotal = product.unitPrice
                      ? product.unitPrice * detail.quantity
                      : 0;
                    return (
                      <tr key={`${order.orderId}-${detail.productId}`}>
                        <td
                          style={{ border: "1px solid #ddd", padding: "8px" }}
                        >
                          {product.imageUrl ? (
                            <img
                              src={product.imageUrl}
                              alt={product.productName}
                              style={{
                                width: "60px",
                                height: "60px",
                                objectFit: "cover",
                                borderRadius: "8px",
                              }}
                            />
                          ) : (
                            <span>No image</span>
                          )}
                        </td>
                        <td
                          style={{ border: "1px solid #ddd", padding: "8px" }}
                        >
                          {product.productName}
                        </td>
                        <td
                          style={{ border: "1px solid #ddd", padding: "8px" }}
                        >
                          ${product.unitPrice?.toFixed(2) || "0.00"}
                        </td>
                        <td
                          style={{ border: "1px solid #ddd", padding: "8px" }}
                        >
                          {detail.quantity}
                        </td>
                        <td
                          style={{ border: "1px solid #ddd", padding: "8px" }}
                        >
                          ${subtotal.toFixed(2)}
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          ))}
>>>>>>> eb0c0dfb811039608cdcf6ebc529f29dc886f08e
        </div>
      </div>
    </div>
  );
};
<<<<<<< HEAD
export default PurchaseHistory;
=======

export default PurchaseHistory;

// import "../css/Global.css"
// import React, { useEffect, useState } from 'react';
// import axios from 'axios';
// import Header from '../components/Header';
// import Sidebar from "../components/Sidebar";

// export default function PurchaseHistory() {
//   const [orders, setOrders] = useState([]);
//   const [showForm, setShowForm] = useState(false);
//   const [selectedOrderId, setSelectedOrderId] = useState(null);
//   const [reviewContent, setReviewContent] = useState('');
//   const [rating, setRating] = useState(5);

//   useEffect(() => {
//     axios.get("http://localhost:8080/api/purchaseHistory/customer/1") // Replace with actual user ID
//       .then(response => {
//         console.log(response.data);
//         setOrders(response.data);
//       })
//       .catch(e => {
//         console.error("Error fetching purchase history:", e);
//       });
//   }, []);

//   const openReviewForm = (orderId, productId) => {
//     setSelectedOrderId({ orderId, productId });
//     setShowForm(true);
//   };

//   return (
//     <div>
//     <Header />
//       <div style={{display: 'flex', flexDirection: 'row'}}>
//         <Sidebar />

//         <div className="container">
//       <div className="row">
//         <div className="col">
//           <div>
//             <div className="table-responsive">
//               <table className="table table-bordered">
//                 <thead>
//                   <tr>
//                     <th scope="col">Order ID</th>
//                     <th scope="col">Product</th>
//                     <th scope="col">Date</th>
//                     <th scope="col">Total Amount</th>
//                     <th scope="col">Quantity</th>
//                     <th scope="col"></th>
//                     <th scope="col"></th>
//                   </tr>
//                 </thead>
//                 <tbody>
//                   {orders.map(order =>
//                     order.orderDetail.map((item, idx) => (
//                       <tr key={`${order.orderId}-${idx}`}>
//                         <td className="id">{order.orderId}</td>
//                         <td>
//                           <div className="d-flex align-items-center">
//                             <div>
//                               <img src="https://via.placeholder.com/60" className="img-fluid rounded-3" alt="Product" />
//                             </div>
//                             <div className="flex-column ms-3">
//                               <a href="productDetails.html">
//                                 <h6>{item.Product.productName}</h6>
//                               </a>
//                               <p>Category: {item.Product.category}</p>
//                             </div>
//                           </div>
//                         </td>
//                         <td className="date"><span>{order.purchaseDate}</span></td>
//                         <td className="price"><span>${order.unitAmount}</span></td>
//                         <td className="quantity"><span>{item.quantity}</span></td>
//                         <td>
//                           <button type="button" onClick={() => openReviewForm(order.orderId, item.Product.productId)}>
//                             Review
//                           </button>
//                         </td>
//                         <td>

//                         </td>
//                       </tr>
//                     ))
//                   )}
//                 </tbody>
//               </table>

//             </div>
//           </div>
//         </div>
//       </div>
//     </div>
//       </div>
//     </div>
//   );
// }
>>>>>>> eb0c0dfb811039608cdcf6ebc529f29dc886f08e
