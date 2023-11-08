import React, { useState } from 'react'

function HomePage() {
  const [quantity, setQuantity] = useState(6);

  return (
    <div>
      <button onClick={() => setQuantity(0)}>Nulli</button>
      <br /><br />
      <button onClick={() => setQuantity( quantity - 1 )}>-</button>
      <span>{quantity}</span>
      <button onClick={() => setQuantity( quantity + 1 )}>+</button>
    </div>
  )
}

export default HomePage