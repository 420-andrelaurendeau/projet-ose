import {ReactElement} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCheck, faPause, faX} from "@fortawesome/free-solid-svg-icons";

function Placeholder(): ReactElement {

    const handleAccept = () => {
        console.log("accept")
    }

    const handleRefuse = () => {
        console.log("refuse")
    }

    const handlePause = () => {
        console.log("pause")
    }

    return (
        <div className={"container flex flex-row justify-around"}>
            <div>
                <FontAwesomeIcon icon={faCheck} style={{color: "#00ff4c",}} onClick={handleAccept}
                                 className={"cursor-pointer"}/>
            </div>
            <div>
                <FontAwesomeIcon icon={faX} style={{color: "#cc0000",}} onClick={handleRefuse}
                                 className={"cursor-pointer"}/>
            </div>
            <div>
                <FontAwesomeIcon icon={faPause} style={{color: "#e1d22d",}} onClick={handlePause} className={"cursor-pointer"} />
            </div>
        </div>
    )
}

export default Placeholder