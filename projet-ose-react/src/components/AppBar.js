import React from "react";
import "../Style.css";
import { Button } from "@material-tailwind/react";
const NavBar = () => {
    return (
        <div>
            <header className="Navbar">
                <div className="Toolbar">
                    <div className="Logo">
                        {" "}
                        <span role="img" aria-label="logo">
              😏
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
