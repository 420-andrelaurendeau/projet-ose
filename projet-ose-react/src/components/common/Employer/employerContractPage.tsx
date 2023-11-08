import {ReactElement, useEffect, useRef, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {
    getIntershipOffers,
    getStageCountByState, getStageCountByStateEmployeur,
    getStages,
    getTotalOfferByState
} from "../../../api/InternshipManagerAPI";
import InternshipManagerOffers from "../../../components/common/internshipManager/offers/InternshipManagerOffers";
import PaginatedList from "../../../components/common/shared/paginationList/PaginatedList";
import {useTranslation} from "react-i18next";
import InternshipManagerOffersDashboardHeader
    from "../../../components/common/internshipManager/offers/InternshipManagerOffersDashboardHeader";
import toast from "../../../components/common/shared/toast/Toast";
import {useToast} from "../../../hooks/state/useToast";
import InternshipManagerInternshipsAgreementDashoardHeader
    from "../../../components/common/internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreementDashoardHeader";
import InternshipManagerInternshipsAgreement
    from "../../../components/common/internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreement";
import {useProps} from "../../../pages/employer/EmployeurHomePage";


export default function EmployerContractPage() {

    const [totalInternshipsAgreement, setTotalInternshipsAgreement] = useState(0);
    const [totalApprouved, setTotalApprouved] = useState(0);
    const [totalPending, setTotalPending] = useState(0);
    const [totalDeclined, setTotalDeclined] = useState(0);
    const navigate = useNavigate();

    const {
        stageAgreement,
        pageAgreement,
        totalPageAgreement,
        onPageChangeAgreement,
        numberElementAgreementByPage,
        handleChangeNumberElementAgreement,
        setAgreementIsUpdate,
        user,
        sortAgreementDirection,
        sortAgreementField,
        setAgreementSortField,
        setAgreementSortDirection,
        setAgreementState,
    } = useProps();
    const handleTotalOffersByState = async () => {
        const responseTotal = await getStageCountByStateEmployeur(user.id);
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


    useEffect(() => {
        handleTotalOffersByState();
    }, [stageAgreement]);

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipsAgreementPage");

    const handleChangeStateSort = (state: any) => {
        setAgreementState(state);
        onPageChangeAgreement(0);
    };

    //TODO fix sorting (back-end needs to have proper field names"

    const handleOfferClick = (id: number) => {
        console.log(id)
        navigate(`/employer/home/internshipagreement/${id}`);
    };


    const renderOffer = <InternshipManagerInternshipsAgreement user={user} offers={stageAgreement}
                                                               isUpdate={setAgreementIsUpdate} sortField={sortAgreementField}
                                                               setsortField={setAgreementSortField}
                                                               setSortDirection={setAgreementSortDirection}
                                                               sortDirection={sortAgreementDirection} handleOfferClick={handleOfferClick}/>;

    return (
        <div className="px-4">
            <title>Offres</title>
            <header className="pt-24 pb-4">
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
                        page={pageAgreement}
                        totalPages={totalPageAgreement}
                        onPageChange={onPageChangeAgreement}
                        numberElement={numberElementAgreementByPage}
                        handleChangeNumberElement={handleChangeNumberElementAgreement}
                    />
                </div>
            </main>
        </div>
    );
};


