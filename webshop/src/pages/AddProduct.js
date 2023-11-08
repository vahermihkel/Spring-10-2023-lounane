import React, { useRef, useState } from 'react'

function AddProduct() {
  const [message, setMessage] = useState("Lisa toode!");
  const nameRef = useRef();

  console.log("Re-renderdan");

  // function add() {}

  const add = () => {
    if (nameRef.current.value === "") {
      setMessage("Tühja nimetusega lisada ei saa!");
    } else {
      setMessage("Toode lisatud: " + nameRef.current.value);
    }
  }

  return (
    <div>
      <div>{message}</div>
      <label>Uue toote nimi</label> <br />
      <input ref={nameRef} type="text" /> <br />
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