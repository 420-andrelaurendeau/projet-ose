import {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClock, faFileLines, faSignature, faThumbsUp, faXmark} from "@fortawesome/free-solid-svg-icons";
import SidebarOptionSwitcher from "./SidebarOptionSwitcher";
import {InterOfferJob} from "../model/IntershipOffer";
import {getIntershipOffers} from "../api/GSManagerAPI";
import GSOffers from "../components/common/GSOffers";
import PaginatedList from "../components/common/PaginatedList";


const GSOffersPage = () => {
    const [offers, setOffers] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [offerState, setOfferState] = useState(undefined);
    const [isUpdate , setIsUpdate] = useState(false);

    const fetchedOffersRef = useRef(false);
    const location = useLocation();
    const user = location.state;


    useEffect(() =>  {
        console.log(isUpdate)
        const fetchOffers = async () => {
            try {
                console.log("BONJOUR");
                fetchedOffersRef.current = true
                const response = await getIntershipOffers({ page, size: 5, state: offerState });
                setOffers(response.content);
                setTotalPages(response.totalPages);

            } catch (error) {
                console.error('Error fetching offers:', error);
            }
            setIsUpdate(false);
            fetchedOffersRef.current = false;
        };
        if (!fetchedOffersRef.current) fetchOffers();

    }, [page, offerState, isUpdate]);

    const handlePageChange = (newPage: number) => {
        setPage(newPage);
    };

    const offersByState = (state: any) => {
        setOfferState(state);
    };

    const renderOffer =  <GSOffers user={user} offers={offers} isUpdate={setIsUpdate}/>;

    return (
        <div>
            <header className="max-md:hidden ">
                <div className="max-w-7xl mx-auto py-6 px-6  lg:px-8">
                    <h1 className="text-3xl font-bold text-gray-900">Dashboard</h1>
                </div>
            </header>
            <main>
                <div className="max-w-7xl mx-auto py-6 xxxs:px-6 lg:px-8">
                    <div className="w-full hidden md:block overflow-x-auto">
                        <div className="flex-row flex md:justify-center space-x-4">

                            <div
                                className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                onClick={() => offersByState(undefined)}
                            >
                                <div className="flex space-x-2 items-center h-16 w-auto">
                                    <div
                                        className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                        <FontAwesomeIcon icon={faFileLines} color="white" size="xl"/>
                                    </div>
                                    <div className="pl-2">
                                        <p className="text-gray">Total offer</p>
                                        <p className="text-xl dark:text-white font-bold">{offers.length}</p>
                                    </div>
                                </div>
                            </div>

                            <div
                                className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                onClick={() => offersByState("ACCEPTED")}
                            >
                                <div className="flex space-x-2 items-center h-16 w-auto">
                                    <div
                                        className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                        <FontAwesomeIcon icon={faThumbsUp} color="white" size="xl"/>
                                    </div>
                                    <div className="pl-2">
                                        <p className="text-gray">Offers accepted</p>
                                        <p className="text-xl dark:text-white font-bold">{offers.length}</p>
                                    </div>
                                </div>
                            </div>

                            <div
                                className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                onClick={() => offersByState("PENDING")}
                            >
                                <div className="flex space-x-2 items-center h-16 w-auto">
                                    <div
                                        className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">
                                        <FontAwesomeIcon icon={faClock} color="white" size="xl"/>
                                    </div>
                                    <div className="pl-2">
                                        <p className="text-gray">Offers pending</p>
                                        <p className="text-xl dark:text-white font-bold">{offers.length}</p>
                                    </div>
                                </div>
                            </div>

                            <div
                                className="border border-gray dark:border-darkgray bg-white dark:bg-dark basis-1/4 text-black hover:bg-gray hover:text-white px-3 py-2 rounded-md text-sm font-medium"
                                onClick={() => offersByState("DECLINED")}
                            >
                                <div className="flex space-x-2 items-center h-16 w-auto">
                                    <div
                                        className="bg-blue dark:bg-orange rounded-full h-12 w-12 flex items-center justify-center">

                                        <FontAwesomeIcon icon={faXmark} color="white" size="xl"/>
                                    </div>
                                    <div className="pl-2">
                                        <p className="text-gray">Offers declined</p>
                                        <p className="text-xl dark:text-white font-bold">{offers.length}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <PaginatedList
                        renderItem={renderOffer}
                        page={page}
                        totalPages={totalPages}
                        onPageChange={handlePageChange}
                    />
                </div>
            </main>
        </div>
    );
};

export default GSOffersPage;

