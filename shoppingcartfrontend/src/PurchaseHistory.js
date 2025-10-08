import React, { useState } from 'react';

// Dummy data for purchased products (replace with real API call)
const purchasedProducts = [
  { productId: 1, name: 'Product 1', orderId: 101 },
  { productId: 2, name: 'Product 2', orderId: 102 },
];

function PurchaseHistory({ customerId }) {
  const [showForm, setShowForm] = useState(null);
  const [form, setForm] = useState({ rating: 5, description: '' });
  const [message, setMessage] = useState('');

  const handleOpenForm = (productId, orderId) => {
    setShowForm({ productId, orderId });
    setForm({ rating: 5, description: '' });
    setMessage('');
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const review = {
      productId: showForm.productId,
      customerId: customerId, // Pass the logged-in customer ID
      orderId: showForm.orderId,
      rating: parseInt(form.rating),
      description: form.description,
    };
    try {
      const res = await fetch('/PurchaseHistory/reviews/add', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(review),
      });
      if (res.ok) {
        setMessage('Review submitted!');
        setShowForm(null);
      } else {
        setMessage('Failed to submit review.');
      }
    } catch (err) {
      setMessage('Error submitting review.');
    }
  };

  return (
    <div>
      <h2>Purchase History</h2>
      <ul>
        {purchasedProducts.map((prod) => (
          <li key={prod.productId}>
            {prod.name}
            <button onClick={() => handleOpenForm(prod.productId, prod.orderId)}>
              Write Review
            </button>
          </li>
        ))}
      </ul>
      {showForm && (
        <form onSubmit={handleSubmit} style={{ marginTop: '1em', border: '1px solid #ccc', padding: '1em' }}>
          <h3>Write a Review</h3>
          <label>
            Rating:
            <select name="rating" value={form.rating} onChange={handleChange} required>
              {[1,2,3,4,5].map((n) => (
                <option key={n} value={n}>{n}</option>
              ))}
            </select>
          </label>
          <br />
          <label>
            Review:
            <textarea name="description" value={form.description} onChange={handleChange} required />
          </label>
          <br />
          <button type="submit">Submit Review</button>
          <button type="button" onClick={() => setShowForm(null)} style={{ marginLeft: '1em' }}>Cancel</button>
          <div>{message}</div>
        </form>
      )}
    </div>
  );
}

export default PurchaseHistory;
