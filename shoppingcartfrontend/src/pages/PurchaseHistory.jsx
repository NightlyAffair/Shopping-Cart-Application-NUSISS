import "../css/global.css"
import "../css/displayProducts.css"
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../components/Header';
import Sidebar from "../components/Sidebar";

const PurchaseHistory = () => {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState(null);

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

  if (error) return <p>{error}</p>;
  if (!orders.length) return <p>Loading purchase history...</p>;

  return (
    <div style={{ padding: "20px" }}>
      <h2 style={{ marginBottom: "20px" }}>Purchase History</h2>

      {orders.map((order) => (
        <div key={order.orderId} style={{ marginBottom: "40px" }}>
          <h3>
            Order #{order.orderId} —{" "}
            <span style={{ color: "#666" }}>{order.status}</span>
          </h3>
          <p>
            <strong>Purchase Date:</strong> {order.purchaseDate} <br />
            <strong>Total:</strong> ${order.unitAmount?.toFixed(2)}
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
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
};

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