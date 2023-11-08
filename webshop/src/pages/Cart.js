import React, { useState } from 'react'
import { Link } from 'react-router-dom';

function Cart() {
  const [cart, setCart] = useState([{name: "Coca", price: 2}, {name: "Fanta", price: 1.5}, {name: "Sprite", price: 1.7}]);

  const remove = (index) => {
    cart.splice(index,1); // cart.delete(index)    .splice   mitmendat ma kustutan ja mitu tk kustutan
    console.log(cart);
    setCart(cart.slice()); // setCart(cart) ei toimi, sest JavaScript leiab, et tegemist on sama mälukohaga
    //setCart([...cart]); 
  }

  console.log("re-renderdan");

  const calculateTotalSum = () => {
    let sum = 0;
    cart.forEach(product => sum += product.price);
    return sum;
  }

  return (
    <div>
      {cart.length > 0 && <div>Ostukorvis on kokku: {cart.length} ese(t)</div>}
      <div>{cart.map((product, index) => 
        <div key={index}>
          {product.name} - {product.price} € 
          <button onClick={() => remove(index)}>x</button>
        </div>  )}
      </div>
      {cart.length === 0 &&
      <>
        <div>Ostukorv on tühi</div>
        <Link to="/avaleht">
          <button>Lisa tooteid</button>
        </Link>
      </>}
      <div>{calculateTotalSum()}</div>

    </div>
  )
}

export default Cart