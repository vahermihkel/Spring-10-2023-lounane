import React from 'react'
import { Link } from 'react-router-dom';

function Cart() {
  return (
    <div>
      <div>Ostukorv on tühi</div>
      <Link to="/avaleht">
        <button>Lisa tooteid</button>
      </Link>
    </div>
  )
}

export default Cart