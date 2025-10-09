import "../css/Global.css"
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../components/Header';
import Sidebar from "../components/Sidebar";

export default function PurchaseHistory() {
  const [orders, setOrders] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState(null);
  const [selectedProductId, setSelectedProductId] = useState(null);
  const [customerId, setCustomerId] = useState(1); // Replace with actual logged-in user ID 
  const [reviewContent, setReviewContent] = useState('');
  const [rating, setRating] = useState(5);

  useEffect(() => {
      const sampleOrders = [
      {
        //start
        orderId: 101,
        orderDate: '2025-10-01',
        totalAmount: 49.99,
        customerId: 1,
        orderDetails: [
          {
            quantity: 1,
            product: { productId: 201, productName: 'Test Product A', category: 'Electronics' }
          },
          {
            quantity: 2,
            product: { productId: 202, productName: 'Test Product B', category: 'Books' }
          }
        ]
      },
      {
        orderId: 102,
        orderDate: '2025-09-20',
        totalAmount: 19.99,
        customerId: 1,
        orderDetails: [
          {
            quantity: 1,
            product: { productId: 203, productName: 'Test Product C', category: 'Home' }
          }
        ]
      }
    ];
     // Use hard-coded data instead of axios for testing
    setOrders(sampleOrders);

    // If you later want to re-enable real fetch, replace setOrders(sampleOrders) with axios.get(...)
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
    setSelectedOrderId({ orderId, productId });
    setCustomerId(customerId);
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
                    order.orderDetail.map((item, idx) => (
                      <tr key={`${order.orderId}-${idx}`}>
                        <td className="id">{order.orderId}</td>
                        <td>
                          <div className="d-flex align-items-center">
                            <div>
                              <img src="https://via.placeholder.com/60" className="img-fluid rounded-3" alt="Product" />
                            </div>
                            <div className="flex-column ms-3">
                              <a href="productDetails.html">
                                <h6>{item.Product.productName}</h6>
                              </a>
                              <p>Category: {item.Product.category}</p>
                            </div>
                          </div>
                        </td>
                        <td className="date"><span>{order.purchaseDate}</span></td>
                        <td className="price"><span>${order.unitAmount}</span></td>
                        <td className="quantity"><span>{item.quantity}</span></td>
                        <td>
                          <button type="button" onClick={() => openReviewForm(order.orderId, item.Product.productId)}>
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
    </div>
  );
}