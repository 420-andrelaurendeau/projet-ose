import React from "react";
import {Route, Routes} from "react-router-dom";
import ProtectedRoute from "./ProtectedRoute";
import Layout from "../components/layout/Layout";
import StudentInternshipPage from "../pages/student/StudentInternshipPage";
import InternshipManagerOffersPage from "../pages/internshipManager/InternshipManagerOffersPage";
import InternshipManagerOfferPage from "../pages/internshipManager/InternshipManagerOfferPage";
import EmployeurHomePage from "../pages/employer/EmployeurHomePage";
import EmployeurOffer from "../components/common/Employer/offer/EmployeurOffer";
import ErrorPage from "../pages/ErrorPage";
import InternshipOfferForm from "../components/common/internshipManager/form/InternshipOfferForm";
import EmployerOfferDetails from "../components/common/Employer/offer/EmployerOfferDetails";
import ApplicationOffer from "../components/common/Employer/application/ApplicationOffer";
import ApplicationDetails from "../components/common/Employer/application/ApplicationDetails";
import SignContract from "../components/common/preparedoc/SignContract";

const EmployerRouter: React.FC = () => {
    return (
        <ProtectedRoute requiredRoles={['employer']}>
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route index path="home" element={<EmployeurHomePage/>}/>
                    <Route path="home" element={<EmployeurHomePage/>}>
                        <Route index path="offers" element={<EmployeurOffer/>}/>
                        <Route path="offers/:id" element={<EmployerOfferDetails/>} />
                        <Route path="offers/:id/application" element={<ApplicationOffer/>} >
                            <Route path=":idApplication/review" element={<ApplicationDetails/>}/>
                        </Route>
                        <Route path="newOffer" element={<InternshipOfferForm/>}/>
                        {/* <Route path="contract" element={<SignContract/>}/> */}
                        <Route path="*" element={<ErrorPage/>}/>
                    </Route>
                    <Route path="*" element={<ErrorPage/>}/>
                </Route>
            </Routes>
        </ProtectedRoute>
    );
};

export default EmployerRouter;