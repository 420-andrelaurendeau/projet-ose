import React from "react";
import "../Style.css";
import { Button } from "@material-tailwind/react";
import logo from "../internship.png";
const NavBar = () => {
    return (
        <div>
            <header className="Navbar">
                <div className="Toolbar">
                    <div className="Logo">
                        {" "}
                        <span role="img" aria-label="logo">
              <img src={logo} alt={"internship"} width={50}/>
            </span>{" "}
                    </div>
                    <div className="Title"> My Sweet App </div>
                    <div>
                        <Button variant="gradient">gradient</Button>
                    </div>
                </div>
            </header>
        </div>
    );
}
export default NavBar;
