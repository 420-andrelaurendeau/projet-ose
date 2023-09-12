import logo from './logo.svg';
import './App.css';
import AppBar from "./components/AppBar";
import React from "react";

function App() {
  return (
    <div className="">
        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Button
        </button>
        <h1 className="text-orange">Bienvenue à React avec TailwindCSS!</h1>
        <h1 className="text-blue">Bienvenue à React avec TailwindCSS!</h1>
    </div>
  );
}


export default App;