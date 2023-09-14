import {ReactElement} from "react";

function TeleversementCV(): ReactElement {
    return (
        <div className={"w-2/4 mt-20 flex flex-col items-center justify-center"}>
            <h1 className={"text-4xl"}>Televerser votre CV</h1>
            <br/>
            <form className={"flex flex-col items-center justify-center"}>
                <input type={"file"} className={""}/>
            </form>
        </div>
    )
}

export default TeleversementCV