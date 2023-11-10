import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom';

function Products() {
  const [products, setProducts] = useState([]);
  const url = "https://dummyjson.com/products";

  // useEffect ja võta URLst kõik tooted ja pane setProducts(body.products) sisse

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(body => setProducts(body.products))
  }, []);

  return (
    <div>
      {products.map(product => 
        <div key={product.id}>
          <div>{product.title}</div>
          <div>{product.price} €</div>
          <img style={{width: "100px"}} src={product.thumbnail} alt="" />
          <Link to={"/toode/" + product.id}>
            <button>Vaata detailsemalt</button>
          </Link>
        </div>)}
    </div>
  )
}

export default Products