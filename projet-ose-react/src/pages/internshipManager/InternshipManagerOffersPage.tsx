import {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import {getIntershipOffers, getTotalOfferByState} from "../../api/InternshipManagerAPI";
import InternshipManagerOffers from "../../components/common/internshipManager/offers/InternshipManagerOffers";
import PaginatedList from "../../components/common/shared/paginationList/PaginatedList";
import {useTranslation} from "react-i18next";
import InternshipManagerOffersDashboardHeader from "../../components/common/internshipManager/offers/InternshipManagerOffersDashboardHeader";
import toast from "../../components/common/shared/toast/Toast";
import {useToast} from "../../hooks/state/useToast";
import {getAllOffers, getAllSeasons, getOffersBySeason} from "../../api/InterOfferJobAPI";


const InternshipManagerOffersPage = () => {
    const [offers, setOffers] = useState([]);

    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState('all');


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
    const toast = useToast();

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
                    sortDirection,
                    session:selectedOption,
                });
                setOffers(response.content);
                setTotalPages(response.totalPages);
            } catch (error) {
                console.log(error);
                toast.error(fields.toast.errorFetchOffers)
            } finally {
                setIsUpdate(false);
                fetchedOffersRef.current = false;
            }
        };

        if (!fetchedOffersRef.current) fetchOffers();

    }, [currentPage, offerState, numberElementByPage, isUpdate, sortField, sortDirection, selectedOption]);

    useEffect(() => {
        const fetchOffersCount = async () => {
            try {
                fetchedOffersCountRef.current = true

                await handleTotalOffersByState();

            } catch (error) {
                toast.error(fields.toast.errorFetchNumberStateOfOffers)
            } finally {
                setIsUpdate(false);
                fetchedOffersCountRef.current = false;
            }
        };
        if (!fetchedOffersCountRef.current) fetchOffersCount();

    }, [isUpdate]);

    useEffect(() => {
        document.title = fields.title;
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

    const handleOptionChange = async (event: any) => {
        const selected = event.target.value;

        setSelectedOption(selected);
    };

    useEffect(() => {
        getAllSeasons().then((res)=>{
            setSeasons(res)
        })
    }, []);


    const renderOffer = <InternshipManagerOffers user={user} offers={offers} isUpdate={setIsUpdate} sortField={sortField} setOffers={setOffers}
                                                 setsortField={setSortField} setSortDirection={setSortDirection}
                                                 sortDirection={sortDirection}/>;

    return (
        <div className="px-4">
            <title>Offres</title>
            <header className="pt-24 pb-4">
                <h1 className="  sm:text-3xl font-bold text-gray-900 dark:text-offwhite">{fields.title}</h1>
            </header>
            <main className="pb-4">
                <div className="p-0">

                    <InternshipManagerOffersDashboardHeader
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
                        selectedOption={selectedOption}
                        handleOptionChange={handleOptionChange}
                        seasons={seasons}
                    />
                </div>
            </main>
        </div>
    );
};

export default InternshipManagerOffersPage;

