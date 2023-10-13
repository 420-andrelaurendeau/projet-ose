import {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClock, faFileLines, faSignature, faThumbsUp, faXmark} from "@fortawesome/free-solid-svg-icons";
import SidebarOptionSwitcher from "./SidebarOptionSwitcher";
import {InterOfferJob} from "../model/IntershipOffer";
import {getIntershipOffers} from "../api/GSManagerAPI";
import GSOffers from "../components/common/GSOffers";


function GSOffersPage() {
    const [isModalOpen, setIsModalOpen] = useState(true)
    const location = useLocation();
    const user = location.state;

    const [offers, setOffers] = useState<InterOfferJob[]>([]);
    const [offersApproved, setOffersApproved] = useState<InterOfferJob[]>([]);
    const [offersPending, setOffersPending] = useState<InterOfferJob[]>([]);
    const [offersRejected, setOffersRejected] = useState<InterOfferJob[]>([]);

    const [currentOffers, setCurrentOffers] = useState<InterOfferJob[]>();

    const offersFetchedRef = useRef(false);

    useEffect(() => {

        if (offersFetchedRef.current) return;
        offersFetchedRef.current = true;
        const fetchData = async () => {
            try {
                const data = await getIntershipOffers();

                setOffers(data)
                setCurrentOffers(data)
                setOffersApproved(data.filter((offer: any) => offer.state == "ACCEPTED"));
                setOffersPending(data.filter((offer: any) => offer.state == "PENDING"));
                setOffersRejected(data.filter((offer: any) => offer.state == "DECLINED"));

                offersFetchedRef.current = false;

            } catch (error) {
                console.error("Erreur lors de la récupération des offres:", error);
            }
        };

        fetchData();

    }, []);


    useEffect(() => {
        if(offersFetchedRef.current) return;
        offersFetchedRef.current = true;

        console.log("BONJOUR")

        setOffersApproved(offers.filter((offer: any) => offer.state == "ACCEPTED"));
        setOffersPending(offers.filter((offer: any) => offer.state == "PENDING"));
        setOffersRejected(offers.filter((offer: any) => offer.state == "DECLINED"));
        offersFetchedRef.current = false;
    }, [offers]);

    const updateInternshipOffer = (updatedOffer: InterOfferJob) => {
        console.log("dans la fonction update")
        setOffers(prevList => prevList.map(offer =>
            offer.id === updatedOffer.id ? updatedOffer : offer
        ));
    };

    const offersByState = (state: string) => {
        switch (state) {
            case "ACCEPTED":
                setCurrentOffers(offersApproved);
                return
            case "PENDING":
                setCurrentOffers(offersPending);
                return
            case "DECLINED":
                console.log("DECLINED");
                setCurrentOffers(offersRejected);
                return
            default:
                setCurrentOffers(offers);
                return
        }
    }


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
                                onClick={() => offersByState("DEFAULT")}
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
                                        <p className="text-xl dark:text-white font-bold">{offersApproved.length}</p>
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
                                        <p className="text-xl dark:text-white font-bold">{offersPending.length}</p>
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
                                        <p className="text-xl dark:text-white font-bold">{offersRejected.length}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    {
                        offers.length > 0 ?
                            <GSOffers user={user} offers={currentOffers} onUpdateInternshipOffer={updateInternshipOffer}/> : <></>
                    }

                </div>
            </main>
        </div>
    );
}

export default GSOffersPage;