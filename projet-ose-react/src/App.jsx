import React from "react";
import './i18n';
import img from './assets/images/internship.png';
function App() {
  return (
    <div className="">


        <nav className="relative flex flex-wrap items-center justify-between px-2 py-3 bg-gray-500 mb-3 Navbar">
            <div className="container px-4 mx-auto flex flex-wrap items-center justify-between">
                <div className="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
                    <a className="text-sm font-bold leading-relaxed inline-block mr-4 py-2 whitespace-nowrap uppercase text-white" href="#pablo">
                        <img src={img} alt="logo" className="w-20 h-20" />
                    </a>

                </div>
                <div className="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start">
                    <button className="bg-orange hover:bg-blue text-white font-bold py-2 px-4 border-b-4 border-blue hover:border-blue rounded-lg">
                        Sign Up
                    </button>
                </div>

            </div>
        </nav>
    </div>
  );
}


export default App;