import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faE, faF, faN, faR} from "@fortawesome/free-solid-svg-icons";
import React from "react";

function LanguageIconFr(props: { bounce: boolean }) {
    return <div className="flex">
        <FontAwesomeIcon icon={faF} bounce={props.bounce} size="sm" className="text-black dark:text-gray mt-1.5"/>
        <FontAwesomeIcon icon={faR} bounce={props.bounce} size="2xs" className="text-black dark:text-gray mt-2.5"/>
    </div>;
}

function LanguageIconEn(props: { bounce: boolean }) {
    return <div className="flex">
        <FontAwesomeIcon icon={faE} bounce={props.bounce} size="sm" className="text-black dark:text-gray mt-1.5"/>
        <FontAwesomeIcon icon={faN} bounce={props.bounce} size="2xs" className="text-black dark:text-gray mt-2.5"/>
    </div>;
}

export {LanguageIconFr, LanguageIconEn};