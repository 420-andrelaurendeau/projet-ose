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


export default function ContractEmployeur(){
    const {stageAgreement,page , totalPages, onPageChange, setSortField, setSortDirection,  sortField, sortDirection, numberElementByPage,handleChangeNumberElement} = useProps();


    return (<div>
        works
    </div>)
};


