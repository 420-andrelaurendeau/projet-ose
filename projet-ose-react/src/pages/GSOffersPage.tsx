import {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClock, faFileLines, faThumbsUp, faXmark} from "@fortawesome/free-solid-svg-icons";
import {getIntershipOffers, getTotalOfferByState} from "../api/GSManagerAPI";
import GSOffers from "../components/common/GSOffers";
import PaginatedList from "../components/common/PaginatedList";


const GSOffersPage = () => {
    const [offers, setOffers] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const [totalOffers, setTotalOffers] = useState(0);
    const [totalApprouved, setTotalApprouved] = useState(0);
    const [totalPending, setTotalPending] = useState(0);
    const [totalDeclined, setTotalDeclined] = useState(0);

    const [offerState, setOfferState] = useState(undefined);
    const [isUpdate, setIsUpdate] = useState(false);

    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("desc");

    const fetchedOffersRef = useRef(false);
    const location = useLocation();
    const user = location.state;


    useEffect(() => {
        const fetchOffers = async () => {
            try {
                fetchedOffersRef.current = true

                const response = await getIntershipOffers({page, size: 2, state: offerState, sortField, sortDirection});
                setOffers(response.content);
                setTotalPages(response.totalPages);

                await handleTotalOffersByState();

            } catch (error) {
                console.error('Error fetching offers:', error);
            }
            setIsUpdate(false);
            fetchedOffersRef.current = false;
        };
        if (!fetchedOffersRef.current) fetchOffers();

    }, [page, offerState, isUpdate, sortField, sortDirection]);

    const handlePageChange = (newPage: number) => {
        setPage(newPage);
    };

    const handleTotalOffersByState = async () => {
        const responseTotal = await getTotalOfferByState();

        if (responseTotal["PENDING"])
            setTotalPending(responseTotal["PENDING"]);

        if (responseTotal["ACCEPTED"])
            setTotalApprouved(responseTotal["ACCEPTED"])

        if (responseTotal["DECLINED"])
            setTotalDeclined(responseTotal["DECLINED"])

        if (responseTotal["TOTAL"])
            setTotalOffers(responseTotal["TOTAL"])
    }

    const offersByState = (state: any) => {
        setOfferState(state);
    };

    const renderOffer = <GSOffers user={user} offers={offers} isUpdate={setIsUpdate} sortField={sortField}
                                  setsortField={setSortField} setSortDirection={setSortDirection}
                                  sortDirection={sortDirection}/>;

    return (
        <div className="h-screen">
            <header className="">
                <div className="max-w-7xl mx-auto py-6 px-6  lg:px-8">
                    <h1 className="text-3xl font-bold text-gray-900">Dashboard</h1>
                </div>
            </header>
            <main className="">
                <div className="p-0">
                    <div className="lg:mx-auto sm:w-3/4 sm:mx-auto px-2 sm:px-9">
                        <div className="flex w-full justify-center gap-x-4">
                            <div className="w-full">
                                <div className="block md:flex justify-between gap-x-4 w-full">
                                    <div
                                        className="cursor-pointer flex-1 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md text-sm font-medium"
                                        onClick={() => offersByState(undefined)}
                                    >
                                        <div className="flex space-x-2 items-center h-16 w-auto">
                                            <div
                                                className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                                <FontAwesomeIcon icon={faFileLines} color="white" size="xl"/>
                                            </div>
                                            <div className="pl-2">
                                                <p className="dark:text-offwhite">Total offer</p>
                                                <p className="text-xl dark:text-white font-bold">{totalOffers}</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div
                                        className="cursor-pointer flex-1 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md text-sm font-medium"
                                        onClick={() => offersByState("ACCEPTED")}
                                    >
                                        <div className="flex space-x-2 items-center h-16 w-auto">
                                            <div
                                                className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                                <FontAwesomeIcon icon={faThumbsUp} color="white" size="xl"/>
                                            </div>
                                            <div className="pl-2">
                                                <p className="dark:text-offwhite">Offers accepted</p>
                                                <p className="text-xl dark:text-white font-bold">{totalApprouved}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div className="w-full">

                                <div className=" block md:flex justify-around gap-x-4 md:w-full">
                                    <div
                                        className="cursor-pointer flex-1 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md text-sm font-medium "
                                        onClick={() => offersByState("PENDING")}
                                    >
                                        <div className="flex space-x-2 items-center h-16 w-auto">
                                            <div
                                                className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                                <FontAwesomeIcon icon={faClock} color="white" size="xl"/>
                                            </div>
                                            <div className="pl-2">
                                                <p className="dark:text-offwhite">Offers pending</p>
                                                <p className="text-xl dark:text-white font-bold">{totalPending}</p>
                                            </div>
                                        </div>
                                    </div>

                                    <div
                                        className="cursor-pointer flex-1 border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black px-3 py-2 rounded-md text-sm font-medium"
                                        onClick={() => offersByState("DECLINED")}
                                    >
                                        <div className="flex space-x-2 items-center h-16 w-auto">
                                            <div
                                                className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">

                                                <FontAwesomeIcon icon={faXmark} color="white" size="xl"/>
                                            </div>
                                            <div className="pl-2">
                                                <p className="dark:text-offwhite">Offers declined</p>
                                                <p className="text-xl dark:text-white font-bold">{totalDeclined}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div className="px-3 mx-3">
                        <PaginatedList
                            renderItem={renderOffer}
                            page={page}
                            totalPages={totalPages}
                            onPageChange={handlePageChange}
                        />
                    </div>

                </div>
            </main>
        </div>
    );
};

export default GSOffersPage;

