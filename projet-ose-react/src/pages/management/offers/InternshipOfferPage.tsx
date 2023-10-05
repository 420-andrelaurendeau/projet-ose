import React from "react";
import GSInternOfferList from "../../../components/common/InternOfferList";

function InternshipOfferPage() {


    return (
        <html lang="en">
            <head>
                <title>Offre d'emploie</title>
            </head>
            <body>
                <div className="items-center border border-red w-screen">
                    <GSInternOfferList/>
                </div>
            </body>
        </html>
    )
}

export default InternshipOfferPage;