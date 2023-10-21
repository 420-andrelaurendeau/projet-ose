import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClock, faFileLines, faThumbsUp, faXmark} from "@fortawesome/free-solid-svg-icons";
import React from "react";

interface GSOffersDashboardHeaderProps {
    offersByState: (state: string | undefined) => void;
    fields: any;
    totalOffers: number;
    totalApprouved: number;
    totalPending: number;
    totalDeclined: number;
}

const GSOffersDashboardHeader: React.FC<GSOffersDashboardHeaderProps> = ({
                                                                             offersByState,
                                                                             fields,
                                                                             totalOffers,
                                                                             totalDeclined,
                                                                             totalApprouved,
                                                                             totalPending
                                                                         }) => {


    return (
        <div className="lg:mx-auto xxl:w-2/3 mm:mx-auto px-2 pb-4">
            <div className="flex w-full justify-center gap-x-4">
                <div className="w-full">
                    <div className="block md:flex justify-between gap-x-4 w-full gap-4">
                        <div
                            className="cursor-pointer flex-1 xxxs:mb-2 md:mb-0 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md font-bold hover:border-black dark:hover:border-offwhite"
                            onClick={() => offersByState(undefined)}
                        >
                            <div className="flex space-x-2 items-center h-16 w-auto">
                                <div
                                    className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                    <FontAwesomeIcon icon={faFileLines} color="white" size="xl"/>
                                </div>
                                <div className="pl-2">
                                    <p className="dark:text-offwhite">{fields.header.total}</p>
                                    <p className="text-2xl dark:text-white font-bold">{totalOffers}</p>
                                </div>
                            </div>
                        </div>

                        <div
                            className="cursor-pointer flex-1 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md font-bold hover:border-black dark:hover:border-offwhite"
                            onClick={() => offersByState("ACCEPTED")}
                        >
                            <div className="flex space-x-2 items-center h-16 w-auto">
                                <div
                                    className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                    <FontAwesomeIcon icon={faThumbsUp} color="white" size="xl"/>
                                </div>
                                <div className="pl-2">
                                    <p className="dark:text-offwhite">{fields.header.accepted}</p>
                                    <p className="text-2xl dark:text-white font-bold">{totalApprouved}</p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div className="w-full">

                    <div className=" block md:flex justify-around gap-x-4 md:w-full">
                        <div
                            className="cursor-pointer xxxs:mb-2 md:mb-0 flex-1 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md font-bold hover:border-black dark:hover:border-offwhite"
                            onClick={() => offersByState("PENDING")}
                        >
                            <div className="flex space-x-2 items-center h-16 w-auto">
                                <div
                                    className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                    <FontAwesomeIcon icon={faClock} color="white" size="xl"/>
                                </div>
                                <div className="pl-2">
                                    <p className="dark:text-offwhite">{fields.header.pending}</p>
                                    <p className="text-2xl dark:text-white font-bold">{totalPending}</p>
                                </div>
                            </div>
                        </div>

                        <div
                            className="cursor-pointer flex-1 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md font-bold hover:border-black dark:hover:border-offwhite"
                            onClick={() => offersByState("DECLINED")}
                        >
                            <div className="flex space-x-2 items-center h-16 w-auto">
                                <div
                                    className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">

                                    <FontAwesomeIcon icon={faXmark} color="white" size="xl"/>
                                </div>
                                <div className="pl-2">
                                    <p className="dark:text-offwhite">{fields.header.declined}</p>
                                    <p className="text-2xl dark:text-white font-bold">{totalDeclined}</p>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    );
}
export default GSOffersDashboardHeader;