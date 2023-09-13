import React from "react";
import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
const NavBar = () => {
    return (
        <div className="">
            <nav className="relative flex flex-wrap justify-between px-2 py-3 mb-3 items-center  ">
                <div className="container px-4 mx-auto flex flex-wrap justify-between w-4/6 shadow-xl rounded-lg mt-4">
                    <div className="w-full relative flex justify-between lg:w-auto lg:static lg:block lg:justify-start ">
                        <a className="flex justify-between text-sm font-bold leading-relaxed mr-4 py-2 whitespace-nowrap uppercase text-white" href="#pablo">
                            <img src={img} alt="logo" className="w-20 h-20" />
                            <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white text-black">Ose 2.0</span>
                        </a>

                    </div>
                    <div className="lg:flex flex-grow items-center justify-end ">
                        <button className="bg-orange hover:bg-blue text-white font-bold py-2 px-4 border-b-4 border-blue hover:border-blue rounded-lg ">
                            Sign Up
                        </button>
                    </div>
                </div>
            </nav>
        </div>
    );
}
export default NavBar;
