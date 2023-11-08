import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
//import reportWebVitals from './reportWebVitals';
import { BrowserRouter } from 'react-router-dom';

// react-router-dom

// Navigeerimiseks (route-miseks)
// 1. npm install react-router-dom
// 2. import BrowserRouter
// 3. <BrowserRouter> --> <App> ümber
// 4. App.js failis lisada URLi ja HTMLi seosed

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </React.StrictMode>
);


