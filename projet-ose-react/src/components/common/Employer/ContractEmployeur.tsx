import {ReactElement, useEffect, useRef, useState} from "react";
import {useLocation} from "react-router-dom";
import {
    getIntershipOffers,
    getStageCountByState,
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


export default function ContractEmployeur() {

    const [totalInternshipsAgreement, setTotalInternshipsAgreement] = useState(0);
    const [totalApprouved, setTotalApprouved] = useState(0);
    const [totalPending, setTotalPending] = useState(0);
    const [totalDeclined, setTotalDeclined] = useState(0);

    const {
        stageAgreement,
        page,
        totalPages,
        onPageChange,
        setSortField,
        setSortDirection,
        sortField,
        sortDirection,
        numberElementByPage,
        handleChangeNumberElement,
        user,
    } = useProps();

    useEffect(() => {
        let totalDec: number = 0
        let totalAp: number = 0
        let totalPen: number = 0
        setTotalInternshipsAgreement(stageAgreement.length);
        stageAgreement.forEach((stage) => {
            if (stage.stateStudent == "DECLINED" || stage.stateEmployeur == "DECLINED") {
                totalDec++;
            } else if (stage.stateStudent == "PENDING" || stage.stateEmployeur == "PENDING") {
                totalPen++;
            } else {
                totalAp++;
            }
        })
        setTotalApprouved(totalAp);
        setTotalPending(totalPen);
        setTotalDeclined(totalDec);
    }, [stageAgreement]);

    const [offerState, setOfferState] = useState(undefined);
    const [isUpdate, setIsUpdate] = useState(false);

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipsAgreementPage");

    const handleChangeStateSort = (state: any) => {
        setOfferState(state);
        onPageChange(0);
    };

    //TODO fix sorting
    //TODO fix paginaiton
    //TODO fix state display //shows declined on approuved

    const renderOffer = <InternshipManagerInternshipsAgreement user={user} offers={stageAgreement}
                                                               isUpdate={setIsUpdate} sortField={sortField}
                                                               setsortField={setSortField}
                                                               setSortDirection={setSortDirection}
                                                               sortDirection={sortDirection}/>;

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
                        page={page}
                        totalPages={totalPages}
                        onPageChange={onPageChange}
                        numberElement={numberElementByPage}
                        handleChangeNumberElement={handleChangeNumberElement}
                    />
                </div>
            </main>
        </div>
    );
};


