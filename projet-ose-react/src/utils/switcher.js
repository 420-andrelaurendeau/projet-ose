import { useState } from "react";
import { DarkModeSwitch } from "react-toggle-dark-mode";
import useDarkSide from "../hooks/useDarkSide";

export default function Switcher({darkSide}) {
    function onChange() {
        console.log(darkSide);
    }
    console.log(darkSide);
    return (
        <>
            <DarkModeSwitch
                style={{ marginBottom: "2rem" }}
                checked={darkSide}
                onChange={onChange}
                size={18}
                moonColor="#F57A00"
                sunColor="#306bac"
            />
        </>
    );
}