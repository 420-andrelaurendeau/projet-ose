import {useEffect, useRef, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {
    getIntershipOffers,
    getStageCountByState,
    getStages,
    getTotalOfferByState
} from "../../api/InternshipManagerAPI";
import InternshipManagerOffers from "../../components/common/internshipManager/offers/InternshipManagerOffers";
import PaginatedList from "../../components/common/shared/paginationList/PaginatedList";
import {useTranslation} from "react-i18next";
import InternshipManagerOffersDashboardHeader from "../../components/common/internshipManager/offers/InternshipManagerOffersDashboardHeader";
import toast from "../../components/common/shared/toast/Toast";
import {useToast} from "../../hooks/state/useToast";
import InternshipManagerInternshipsAgreementDashoardHeader
    from "../../components/common/internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreementDashoardHeader";
import InternshipManagerInternshipsAgreement
    from "../../components/common/internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreement";
import {getAllOffers, getAllSeasons, getOffersBySeason} from "../../api/InterOfferJobAPI";


const getActualSeason = () => {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth();
    let session = '';

    if (currentMonth >= 5 && currentMonth <= 8) {
        session = 'Été';
    } else if (currentMonth >= 9 || currentMonth <= 1) {
        session = 'Automne';
    } else {
        session = 'Hiver';
    }

    if (session === 'Été' || session === 'Automne') {
        return `Hiver${currentYear + 1}`;
    } else {
        return `Été${currentYear}`;
    }
}


const InternshipManagerInternshipsAgreementPage = () => {
    const [internshipsAgreement, setInternshipsAgreement] = useState([]);


    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState(getActualSeason());


    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(100)

    const [totalInternshipsAgreement, setTotalInternshipsAgreement] = useState(0);
    const [totalApprouved, setTotalApprouved] = useState(0);
    const [totalPending, setTotalPending] = useState(0);
    const [totalDeclined, setTotalDeclined] = useState(0);

    const [offerState, setOfferState] = useState(undefined);
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("desc");

    const [isUpdate, setIsUpdate] = useState(false);

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipsAgreementPage");

    const fetchedInternshipsAgreementRef = useRef(false);
    const fetchedInternshipsAgreementCountRef = useRef(false);
    const location = useLocation();
    const toast = useToast();
    const navigate = useNavigate();

    //TODO Temporaire
    const user = location.state;


    useEffect(() => {
        const fetchInternshipsAgreement = async () => {
            try {
                fetchedInternshipsAgreementRef.current = true

                const response = await getStages({
                    page: currentPage,
                    size: numberElementByPage,
                    state: offerState,
                    sortField,
                    sortDirection,
                    session: selectedOption
                });
                setInternshipsAgreement(response.content);
                setTotalPages(response.totalPages);
                await handleTotalOffersByState();
            } catch (error) {
                console.log(error);
                toast.error(fields.toast.errorFetchInternshipsAgreement)
            } finally {
                setIsUpdate(false);
                fetchedInternshipsAgreementRef.current = false;
            }
        };
        if (!fetchedInternshipsAgreementRef.current) fetchInternshipsAgreement();

    }, [currentPage, selectedOption,offerState, numberElementByPage, isUpdate, sortField, sortDirection]);

    useEffect(() => {
        const fetchInternshipsAgreementCount = async () => {
            try {
                fetchedInternshipsAgreementCountRef.current = true

                await handleTotalOffersByState();

            } catch (error) {
                toast.error(fields.toast.errorFetchNumberStateOfInternshipsAgreement)
            } finally {
                setIsUpdate(false);
                fetchedInternshipsAgreementCountRef.current = false;
            }
        };
        if (!fetchedInternshipsAgreementCountRef.current) fetchInternshipsAgreementCount();

    }, [isUpdate]);

    useEffect(() => {
        document.title = fields.title;
    }, []);

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
    };

    const handleTotalOffersByState = async () => {
        const responseTotal = await getStageCountByState(selectedOption);
        setTotalInternshipsAgreement(0);
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
            setTotalInternshipsAgreement(responseTotal["TOTAL"])
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

        console.log(selected)
        setSelectedOption(selected);

    };

    useEffect(() => {
        getAllSeasons().then((res)=>{
            setSeasons(res)
        })
    }, []);

    const handleOfferClick = (id: number) => {
        console.log(id)
        navigate(`/internshipmanager/home/internshipagreement/${id}`);
    };

    const renderOffer = <InternshipManagerInternshipsAgreement user={user} offers={internshipsAgreement} isUpdate={setIsUpdate} sortField={sortField}
                                                 setsortField={setSortField} setSortDirection={setSortDirection}
                                                 sortDirection={sortDirection}
                                                                handleOfferClick={handleOfferClick}
    />;

    return (
        <div className="max-md:pt-24">
            <title>Offres</title>
            <header className="pb-4">
                <h1 className="xxxs:text-2xl sm:text-3xl font-bold text-gray-900 dark:text-offwhite">{fields.title}</h1>
            </header>
            <main className="pb-4">
                <div className="p-0">

                    <InternshipManagerInternshipsAgreementDashoardHeader
                        internshipsAgreementByState={handleChangeStateSort}
                        fields={fields}
                        totalOffers={totalInternshipsAgreement}
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

export default InternshipManagerInternshipsAgreementPage;

