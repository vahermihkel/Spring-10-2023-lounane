import React, { useRef, useState } from 'react'

function AddProduct() {
  const [message, setMessage] = useState("Lisa toode!");
  const nameRef = useRef();
  const priceRef = useRef();
  const imageRef = useRef();
  const categoryRef = useRef();
  const stockRef = useRef();
  const url = "https://dummyjson.com/products";

  console.log("Re-renderdan");

  // function add() {}

  const add = () => {
    if (nameRef.current.value === "") {
      setMessage("Tühja nimetusega lisada ei saa!");
      return;
    } 
    
    setMessage("Toode lisatud: " + nameRef.current.value);

    // Entity
    // private String name;
    // private int price;
    const newProduct = {
      "name": nameRef.current.value,
      "price": priceRef.current.value,
      "image": imageRef.current.value,
      "category": categoryRef.current.value,
      "stock": stockRef.current.value,
      // "rating": 0
    }

    fetch(url, 
      {
        "method": "POST", 
        "body": JSON.stringify(newProduct), 
        "headers": {"Content-Type": "application/json"}
      }).then(res => res.json())
        .then(body => console.log(body));
  }

  return (
    <div>
      <div>{message}</div>
      <label>Uue toote nimi</label> <br />
      <input ref={nameRef} type="text" /> <br />
      <label>Uue toote hind</label> <br />
      <input ref={priceRef} type="text" /> <br />
      <label>Uue toote pilt</label> <br />
      <input ref={imageRef} type="text" /> <br />
      <label>Uue toote kategooria</label> <br />
      <input ref={categoryRef} type="text" /> <br />
      <label>Uue toote kogus</label> <br />
      <input ref={stockRef} type="text" /> <br />
      <button onClick={add}>Lisa</button> <br />
    </div>
  )
}

export default AddProduct

// useState <-- HTMLs muutujate muutmiseks
// useRef <-- inputi seest väärtuste kättesaamiseks
// useEffect <-- kui tullakse lehele ja tehakse koheselt päring

// useParams <-- URLi muutujate kasutamiseks
// useNavigate <-- JavaScriptis navigeerimiseks
// useTranslation <-- tõlge
// useContext <-- globaalsete muutujate jaoks

// tegemist on Reacti erikoodiga, mida kasutab ainult React
// reeglid Hookide kohta
// 1. alati algab "use" algusega
// 2. alati importida vaja
// 3. alati lõppeb lõpus sulgudega
// 4. ta ei tohi olla loodud funktsiooni sees
// 5. ta ei tohi olla loodud tingimuslikult