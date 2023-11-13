import {useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
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


const InternshipManagerInternshipsAgreementPage = () => {
    const [internshipsAgreement, setInternshipsAgreement] = useState([]);

    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(5)

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
                    sortDirection
                });
                setInternshipsAgreement(response.content);
                setTotalPages(response.totalPages);
            } catch (error) {
                console.log(error);
                toast.error(fields.toast.errorFetchInternshipsAgreement)
            } finally {
                setIsUpdate(false);
                fetchedInternshipsAgreementRef.current = false;
            }
        };
        if (!fetchedInternshipsAgreementRef.current) fetchInternshipsAgreement();

    }, [currentPage, offerState, numberElementByPage, isUpdate, sortField, sortDirection]);

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
        const responseTotal = await getStageCountByState();
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

    const renderOffer = <InternshipManagerInternshipsAgreement user={user} offers={internshipsAgreement} isUpdate={setIsUpdate} sortField={sortField}
                                                 setsortField={setSortField} setSortDirection={setSortDirection}
                                                 sortDirection={sortDirection}/>;

    return (
        <div className="px-4">
            <title>Offres</title>
            <header className="pt-4 pb-4">
                <h1 className="  sm:text-3xl font-bold text-gray-900 dark:text-offwhite">{fields.title}</h1>
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
                    />
                </div>
            </main>
        </div>
    );
};

export default InternshipManagerInternshipsAgreementPage;

