import React, { useRef } from 'react'

function AddProduct() {
  const nameRef = useRef();

  return (
    <div>
      <label>Uue toote nimi</label> <br />
      <input type="text" /> <br />
      <button>Lisa</button> <br />
    </div>
  )
}

export default AddProduct