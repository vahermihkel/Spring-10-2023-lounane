import { useEffect, useRef, useState } from 'react';
import './App.css';

function App() {
  const [tooted, setTooted] = useState([]);
  const idRef = useRef();
  const nimiRef = useRef();
  const hindRef = useRef();
  const aktiivneRef = useRef();


  useEffect(() => {
    fetch("http://localhost:8080/tooted")
      .then(res => res.json())
      .then(json => setTooted(json));
  }, []);

  function kustuta(index) {
    fetch("http://localhost:8080/tooted/" + index, {"method": "DELETE"})
      .then(res => res.json())
      .then(json => setTooted(json));
  }

  ////////////////////////
  function lisa() {
    const uusToode = {
      "id": Number(idRef.current.value),
      "nimi": nimiRef.current.value,
      "hind": Number(hindRef.current.value),
      "aktiivne": aktiivneRef.current.checked
    }
    fetch("http://localhost:8080/tooted", {"method": "POST", "body": JSON.stringify(uusToode), "headers": {"Content-Type": "application/json"}})
      .then(res => res.json())
      .then(json => setTooted(json));
  }
  ////////////////////////

  function dollariteks() {
    const kurss = 1.1;
    fetch("http://localhost:8080/hind-dollaritesse/" + kurss, {"method": "PATCH"})
      .then(res => res.json())
      .then(json => setTooted(json));
  }

  return (
    <div className="App">
      <label>ID</label> <br />
      <input ref={idRef} type="number" /> <br />
      <label>Nimi</label> <br />
      <input ref={nimiRef} type="text" /> <br />
      <label>Hind</label> <br />
      <input ref={hindRef} type="number" /> <br />
      <label>Aktiivne</label> <br />
      <input ref={aktiivneRef} type="checkbox" /> <br />
      <button onClick={() => lisa()}>Lisa</button>
      {tooted.map((toode, index) => 
        <div>
          <div>{toode.id}</div>
          <div>{toode.nimi}</div>
          <div>{toode.hind}</div>
          <button onClick={() => kustuta(index)}>x</button>
        </div>)}
      <button onClick={() => dollariteks()}>Muuda dollariteks</button>
    </div>
  );
}

export default App;