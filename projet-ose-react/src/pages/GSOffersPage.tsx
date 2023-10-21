import {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faClock, faFileLines, faThumbsUp, faXmark} from "@fortawesome/free-solid-svg-icons";
import {getIntershipOffers, getTotalOfferByState} from "../api/GSManagerAPI";
import GSOffers from "../components/common/GSOffers";
import PaginatedList from "../components/common/PaginatedList";
import {useTranslation} from "react-i18next";
import ListItemCountSelector from "../components/common/ListItemCountSelector";
import GSOffersDashboardHeader from "../components/common/GSOffersDashboardHeader";


const GSOffersPage = () => {
    const [offers, setOffers] = useState([]);
    const [page, setPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const [totalOffers, setTotalOffers] = useState(0);
    const [totalApprouved, setTotalApprouved] = useState(0);
    const [totalPending, setTotalPending] = useState(0);
    const [totalDeclined, setTotalDeclined] = useState(0);

    const [numberElement, setNumberElement] = useState<number>(5)

    const [offerState, setOfferState] = useState(undefined);
    const [isUpdate, setIsUpdate] = useState(false);

    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("desc");

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipOfferList");

    const fetchedOffersRef = useRef(false);
    const fetchedOffersCountRef = useRef(false);
    const location = useLocation();
    const user = location.state;


    useEffect(() => {
        const fetchOffers = async () => {
            try {
                fetchedOffersRef.current = true

                const response = await getIntershipOffers({
                    page,
                    size: numberElement,
                    state: offerState,
                    sortField,
                    sortDirection
                });
                setOffers(response.content);
                setTotalPages(response.totalPages);

            } catch (error) {
                console.error('Error fetching offers:', error);
            }
            setIsUpdate(false);
            fetchedOffersRef.current = false;
        };
        if (!fetchedOffersRef.current) fetchOffers();

    }, [page, offerState, numberElement, isUpdate, sortField, sortDirection]);

    useEffect(() => {
        const fetchOffersCount = async () => {
            try {
                fetchedOffersCountRef.current = true

                await handleTotalOffersByState();

            } catch (error) {
                console.error('Error fetching the numbers of offers:', error);
            }
            setIsUpdate(false);
            fetchedOffersCountRef.current = false;
        };
        if (!fetchedOffersCountRef.current) fetchOffersCount();

    }, [isUpdate]);

    const handlePageChange = (newPage: number) => {
        setPage(newPage);
    };

    const handleTotalOffersByState = async () => {
        const responseTotal = await getTotalOfferByState();
        setTotalOffers(0);
        setTotalApprouved(0);
        setTotalPending(0);
        setTotalDeclined(0);

        if (responseTotal["PENDING"])
            setTotalPending(responseTotal["PENDING"]);

        if (responseTotal["ACCEPTED"])
            setTotalApprouved(responseTotal["ACCEPTED"])

        if (responseTotal["DECLINED"])
            setTotalDeclined(responseTotal["DECLINED"])

        if (responseTotal["TOTAL"])
            setTotalOffers(responseTotal["TOTAL"])
    }

    const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setPage(0);
        setNumberElement(Number(event.target.value));
    };

    const offersByState = (state: any) => {
        setOfferState(state);
        setPage(0);
    };

    const renderOffer = <GSOffers user={user} offers={offers} isUpdate={setIsUpdate} sortField={sortField}
                                  setsortField={setSortField} setSortDirection={setSortDirection}
                                  sortDirection={sortDirection}/>;

    return (
        <div className="">
            <header className="">
                <h1 className="text-3xl font-bold text-gray-900">Dashboard</h1>
            </header>
            <main className="">
                <div className="p-0">

                    <GSOffersDashboardHeader offersByState={offersByState} fields={fields} totalOffers={totalOffers} totalApprouved={totalApprouved} totalPending={totalPending} totalDeclined={totalDeclined}/>


                    <PaginatedList
                        renderItem={renderOffer}
                        page={page}
                        totalPages={totalPages}
                        onPageChange={handlePageChange}
                        numberElement={numberElement}
                        handleChangeNumberElement={handleChange}
                    />
                </div>
            </main>
        </div>
    );
};

export default GSOffersPage;

