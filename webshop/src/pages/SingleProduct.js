import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'

function SingleProduct() {
  // const [] = useState()    kindel arv
  // {} <- kindlaksmääramata arv
  const { productId } = useParams();
  const [product, setProduct] = useState({});
  const url = "https://dummyjson.com/products/";

  // uef - Simple React Snippets extension
  useEffect(() => {
    fetch(url + productId)
      .then(res => res.json())
      .then(body => setProduct(body));
  }, [productId]);

  if (product.id === undefined) {
    return <div>Loading...</div>
  }

  return (
    <div>
      <div>ID: {product.id}</div>
      <div>Title: {product.title}</div>
      <div>Description: {product.description}</div>
      <div>Price: {product.price} €</div>
      <div>Rating: {product.rating} / 5.0</div>
      <div>Stock: {product.stock} pcs</div>
      <div>Category: {product.category}</div>
      <img src={product.thumbnail} alt="" />
    </div>
  )
}

export default SingleProduct