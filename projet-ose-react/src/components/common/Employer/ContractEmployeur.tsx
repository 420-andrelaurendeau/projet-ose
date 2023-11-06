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

    const [offerState, setOfferState] = useState(undefined);
    const [isUpdate, setIsUpdate] = useState(false);

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipsAgreementPage");

    const handleChangeStateSort = (state: any) => {
        setOfferState(state);
        onPageChange(0);
    };

    const renderOffer = <InternshipManagerInternshipsAgreement user={user} offers={stageAgreement} isUpdate={setIsUpdate} sortField={sortField}
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

                    <InternshipManagerInternshipsAgreementDashoardHeader
                        internshipsAgreementByState={handleChangeStateSort}
                        fields={fields}
                        totalOffers={1}
                        totalApprouved={1}
                        totalPending={1}
                        totalDeclined={1}
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


