import React from "react";
import ConnectForm from "../components/common/shared/authentication/ConnectForm";


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