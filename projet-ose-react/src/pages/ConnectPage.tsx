import React from "react";
import ConnectForm from "../components/common/ConnectForm";
import useDarkSide from "../hooks/useDarkSide";


function ConnectPage() {

    return (

        <div className="h-screen w-full bg-white dark:bg-dark">
            <div className="items-center">
                <ConnectForm/>
            </div>
        </div>
    );
}
export default ConnectPage;