import React from "react";
import {Route, Routes} from "react-router-dom";
import ProtectedRoute from "./ProtectedRoute";
import Layout from "../components/layout/Layout";
import EmployeurHomePage from "../pages/employer/EmployeurHomePage";
import EmployeurOffer from "../components/common/Employer/offer/EmployeurOffer";
import ErrorPage from "../pages/ErrorPage";
import InternshipOfferForm from "../components/common/internshipManager/form/InternshipOfferForm";
import EmployerOfferDetails from "../components/common/Employer/offer/EmployerOfferDetails";
import ApplicationOffer from "../components/common/Employer/application/ApplicationOffer";
import ApplicationDetails from "../components/common/Employer/application/ApplicationDetails";
import SignContract from "../components/common/preparedoc/SignContract";
import ViewPDFModal from "../components/common/Employer/offer/ViewPDFModal";
import EmployerStagePage from "../components/common/Employer/employerStagePage";
import InternshipContractPage from "../pages/internshipManager/InternshipContractPage";
import EmployerContractsPage from "../components/common/Employer/employerContractsPage";

import {EmployerInterviewPage} from "../components/common/Employer/EmployerInterviewPage";
import Notifications from "../components/common/shared/notification/Notifications";

const EmployerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['employer']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index path="home" element={<EmployeurHomePage/>}/>
                    <Route path="home" element={<EmployeurHomePage/>}>
                        <Route index path="offers" element={<EmployeurOffer/>}/>
                        <Route path="notifications" element={<Notifications/>}/>
                        <Route path={"stage"} element={<EmployerStagePage/>}>
                            <Route path=":id" element={<ViewPDFModal/>}/>
                        </Route>
                        <Route path="offers/:id" element={<EmployerOfferDetails/>}>
                            <Route path=":fileName" element={<ViewPDFModal/>}/>
                        </Route>
                        <Route path="offers/:id/application" element={<ApplicationOffer/>} >
                            <Route path=":idApplication/review" element={<ApplicationDetails/>}/>
                        </Route>
                        <Route path="newOffer" element={<InternshipOfferForm/>}/>
                        <Route path="interview" element={<EmployerInterviewPage/>}/>
                        <Route path="*" element={<ErrorPage/>}/>
                        <Route path="internshipagreement" element={<EmployerContractsPage/>}/>
                        <Route path="internshipagreement/:id" element={<InternshipContractPage/>}>
                            <Route path=":fileName" element={<ViewPDFModal/>}/>
                        </Route>
                        <Route path="internshipagreement/:id/contract" element={<SignContract/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default EmployerRouter;