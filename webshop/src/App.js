// import logo from './logo.svg';
import './App.css';
import { Link, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import Cart from './pages/Cart';
import AddProduct from './pages/AddProduct';
import NotFound from './pages/NotFound';
import Products from './pages/Products';
import SingleProduct from './pages/SingleProduct';

function App() {
  return (
    <div className="App">
      <Link to="/avaleht">
        <img className="pilt" src="https://estonia.ee/wp-content/uploads/nobe_netist_4.jpg" alt="" />
      </Link>

      <Link to="/ostukorv">
        <button className="nupp">Ostukorvi</button>
      </Link>

      <Link to="/lisa-toode">
        <button className="nupp">Toodet lisama</button>
      </Link>

      <Link to="/tooted">
        <button className="nupp">Tooted</button>
      </Link>

{/* // alustan nokast ->    <Home    --> pean vajutama "Enter". Peab üleval tab lahti olema */}
      <Routes>
        <Route path="avaleht" element={ <HomePage /> } />
        <Route path="ostukorv" element={ <Cart /> } />
        <Route path="lisa-toode" element={ <AddProduct /> } />
        <Route path="tooted" element={ <Products /> } />
        <Route path="toode/:productId" element={ <SingleProduct /> } />
        <Route path="*" element={ <NotFound /> } />
      </Routes>
    </div>
  );
}

export default App;
