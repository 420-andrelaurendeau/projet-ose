import {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import {getIntershipOffers, getTotalOfferByState} from "../api/GSManagerAPI";
import GSOffers from "../components/common/GSOffers";
import PaginatedList from "../components/common/PaginatedList";
import {useTranslation} from "react-i18next";
import GSOffersDashboardHeader from "../components/common/GSOffersDashboardHeader";


const GSOffersPage = () => {
    const [offers, setOffers] = useState([]);

    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(5)

    const [totalOffers, setTotalOffers] = useState(0);
    const [totalApprouved, setTotalApprouved] = useState(0);
    const [totalPending, setTotalPending] = useState(0);
    const [totalDeclined, setTotalDeclined] = useState(0);

    const [offerState, setOfferState] = useState(undefined);
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("desc");

    const [isUpdate, setIsUpdate] = useState(false);

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipOfferList");

    const fetchedOffersRef = useRef(false);
    const fetchedOffersCountRef = useRef(false);
    const location = useLocation();

    //TODO Temporaire
    const user = location.state;


    useEffect(() => {
        const fetchOffers = async () => {
            try {
                fetchedOffersRef.current = true

                const response = await getIntershipOffers({
                    page: currentPage,
                    size: numberElementByPage,
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

    }, [currentPage, offerState, numberElementByPage, isUpdate, sortField, sortDirection]);

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

    useEffect(() => {
        document.title = "Offres de stage";

    }, []);

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
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

    const handleChangePage = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCurrentPage(0);
        setNumberElementByPage(Number(event.target.value));
    };

    const handleChangeStateSort = (state: any) => {
        setOfferState(state);
        setCurrentPage(0);
    };

    const renderOffer = <GSOffers user={user} offers={offers} isUpdate={setIsUpdate} sortField={sortField}
                                  setsortField={setSortField} setSortDirection={setSortDirection}
                                  sortDirection={sortDirection}/>;

    return (
        <div className="">
            <title>Offres</title>
            <header className="pb-4">
                <h1 className="  sm:text-3xl font-bold text-gray-900 dark:text-offwhite">Offres de stage</h1>
            </header>
            <main className="pb-4">
                <div className="p-0">

                    <GSOffersDashboardHeader
                        offersByState={handleChangeStateSort}
                        fields={fields}
                        totalOffers={totalOffers}
                        totalApprouved={totalApprouved}
                        totalPending={totalPending}
                        totalDeclined={totalDeclined}
                    />


                    <PaginatedList
                        renderItem={renderOffer}
                        page={currentPage}
                        totalPages={totalPages}
                        onPageChange={handlePageChange}
                        numberElement={numberElementByPage}
                        handleChangeNumberElement={handleChangePage}
                    />
                </div>
            </main>
        </div>
    );
};

export default GSOffersPage;

