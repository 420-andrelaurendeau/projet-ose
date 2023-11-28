import ApplicationDetails from "../../components/common/Employer/application/ApplicationDetails";
import {useUser} from "../../components/common/Employer/application/ApplicationOffer";
import {ToastContextProvider} from "../../hooks/context/ToastContext";
import React from "react";
import {fireEvent, render, screen} from "@testing-library/react";
import api from "../../api/ConfigAPI";
import {act} from "react-dom/test-utils";

const mockApplications = {
    "id": 1,
    "etudiant": {
        "id": 1,
        "nom": "Marc",
        "prenom": "Max",
        "phone": "4387999889",
        "email": "max@gmail.com",
        "matricule": "2045888",
        "programme_id": 1,
        "cv": [],
        "internships_id": [
            1
        ]
    },
    "internOfferJob": {
        "id": 1,
        "title": "Stage Informatique",
        "location": "Laval",
        "description": "En tant que stagiaire en informatique chez Cisco, vous aurez l'opportunité de travailler au sein de notre équipe de professionnels de l'informatique, d'apprendre de nouvelles compétences",
        "salaryByHour": 20,
        "startDate": "2023-11-27",
        "endDate": "2023-11-27",
        "internshipCandidates": [
            1
        ],
        "programmeId": 1,
        "file": {
            "id": 1,
            "content": "aGVsbG8=",
            "fileName": "Test",
            "isAccepted": "PENDING"
        },
        "employeurId": 3,
        "programmeNom": "Techniques de l'informatique",
        "employeurPrenom": "Lemieux",
        "employeurNom": "Patrique",
        "employeurEntreprise": "Cisco",
        "offerReviewRequestId": 0,
        "state": "PENDING",
        "session": "Automne2023"
    },
    "files": [
        {
            "id": 4,
            "content": "aGVsbG8=",
            "fileName": "Test",
            "isAccepted": "PENDING"
        }
    ],
    "state": "PENDING",
    "date": null,
    "interviewList": [
        {
            "offerId": 1,
            "candidateId": 1,
            "alreadyApplied": false
        }
    ]
}
const studentId = 1;
const offerId = 1;
let setUpdate = jest.fn()
let handleAccept = jest.fn()
let handleRefuse = jest.fn()
let hasStudentApplied = false
let updateCandidature = jest.fn()

const mockUseUser = {
    application: mockApplications,
    studentId: studentId,
    offerId: offerId,
    setUpdate: setUpdate,
    handleAccept: handleAccept,
    handleRefuse: handleRefuse,
    hasStudentApplied: () => {
        return false
    },
    updateCandidature: updateCandidature,
}
jest.mock("../../api/ConfigAPI", () => ({
    api: {
        post: jest.fn()
    }
}))
jest.mock("../../components/common/Employer/application/ApplicationOffer", () => ({
    useUser: jest.fn()
}))


const mockedUsedNavigate = jest.fn();
jest.mock("react-router-dom", () => ({
    ...jest.requireActual('react-router-dom') as any,
    useNavigate: () => mockedUsedNavigate,
}))

jest.mock('react-i18next', () => ({
    useTranslation: () => {
        return {
            t: (str: any) => str,
            i18n: {
                language: "en",
                changeLanguage: () => new Promise(() => {
                }),
                getResource: (lang: string, ns: string, key: string) => {
                    return key
                },
            },

        };
    },
    initReactI18next: {
        type: '3rdParty',
        init: () => {
        },
    }
}));

describe("test application details", () => {
    beforeEach(() => {
        api.post = jest.fn().mockReturnValue({data: {}});
        (useUser as jest.Mock).mockReturnValue(mockUseUser);
    })
    afterEach(() => {
        mockUseUser.application.state = "PENDING"
    })
    it("should render without errors", async () => {
        render(
            <ToastContextProvider>
                <ApplicationDetails/>
            </ToastContextProvider>
        )
        const etudiantEmail = await screen.findByText(mockApplications.etudiant.email)
        const etudiantPhone = await screen.findByText(mockApplications.etudiant.phone)
        expect(etudiantEmail).toBeInTheDocument()
        expect(etudiantPhone).toBeInTheDocument()
    })
    it("refuse application", async () => {
        const {rerender} = render(
            <ToastContextProvider>
                <ApplicationDetails/>
            </ToastContextProvider>
        )
        const refuseButton = await screen.findByLabelText("refuse-button")
        expect(refuseButton).toBeInTheDocument()

        act(() => {
            fireEvent.click(refuseButton)
            expect(mockUseUser.handleRefuse).toHaveBeenCalled()
        })


    })
    it("accept application", async () => {
        render(
            <ToastContextProvider>
                <ApplicationDetails/>
            </ToastContextProvider>
        )
        const acceptButton = await screen.findByLabelText("accept-button")
        expect(acceptButton).toBeInTheDocument()

        await act(async () => {
            fireEvent.click(acceptButton)
            expect(mockUseUser.handleAccept).toHaveBeenCalled()
        })
    })

})