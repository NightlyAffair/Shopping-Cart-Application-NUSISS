import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../components/Header';

export default function PurchaseHistory() {
  const [orders, setOrders] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [reviewContent, setReviewContent] = useState('');
  const [rating, setRating] = useState(5);

  useEffect(() => {
    axios.get("http://localhost:8080/api/purchaseHistory/customer/1") // Replace with actual user ID
      .then(response => {
        setOrders(response.data);
      })
      .catch(e => {
        console.error("Error fetching purchase history:", e);
      });
  }, []);

  const openReviewForm = (orderId, productId) => {
    setSelectedOrderId({ orderId, productId });
    setShowForm(true);
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
                    order.orderDetails.map((item, idx) => (
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
                  
            </div>
          </div>
        </div>
      </div>
    </div>
    </div>
  );
}