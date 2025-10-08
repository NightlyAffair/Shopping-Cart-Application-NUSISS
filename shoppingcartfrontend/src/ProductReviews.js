import React, { useEffect, useState } from 'react';

function ProductReviews({ productId }) {
  const [reviews, setReviews] = useState([]);
  const [average, setAverage] = useState(null);

  useEffect(() => {
    // Fetch reviews
    fetch(`/PurchaseHistory/reviews/product/${productId}`)
      .then(res => res.json())
      .then(data => setReviews(data));

    // Fetch average rating
    fetch(`/PurchaseHistory/reviews/product/${productId}/average-rating`)
      .then(res => res.json())
      .then(data => setAverage(data));
  }, [productId]);

  return (
    <div>
      <h3>Average Rating: {average ? average.toFixed(2) : 'No ratings yet'}</h3>
      <h4>Reviews:</h4>
      {reviews.length === 0 ? (
        <p>No reviews yet.</p>
      ) : (
        <ul>
          {reviews.map((r, idx) => (
            <li key={idx}>
              <strong>Rating:</strong> {r.rating} <br />
              <strong>Review:</strong> {r.description}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default ProductReviews;
