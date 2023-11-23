import React from "react";
import {render, screen, fireEvent, waitFor, act} from "@testing-library/react";
import axios from "axios";
import StudentInscriptionForm from "../../components/common/student/form/StudentInscriptionForm";
import {saveStudent} from "../../api/StudentApi";
import {MemoryRouter, Route, Routes} from "react-router-dom";
import StudentAppliedOffers from "../../components/common/student/offers/StudentAppliedOffers";
import {ToastContextProvider} from "../../hooks/context/ToastContext";
import {fetchProgrammes} from "../../api/ProgrammeAPI";
import exp from "constants";


jest.mock("../../api/ProgrammeAPI", () => {
    return {
        fetchProgrammes: jest.fn()
    }
})
jest.mock("../../api/StudentApi", () => {
    return {
        saveStudent: jest.fn()
    }
})


jest.mock("axios", () => {
    return {
        get: jest.fn()
    }
})
jest.mock('react-i18next', () => ({
    useTranslation: () => {
        return {
            t: (str: any) => str,
            i18n: {
                changeLanguage: () => new Promise(() => {
                }),
            },
        };
    },
    initReactI18next: {
        type: '3rdParty',
        init: () => {
        },
    }
}));

interface programs {
    id: number,
    nom: string,
    description: string
}

const mock_programs: programs[] = [
    {
        "id": 1,
        "nom": "Techniques de l'informatique",
        "description": "Programme de formation en techniques de l'informatique"
    },
    {
        "id": 2,
        "nom": "Techniques de l'administration",
        "description": "Programme de formation en techniques de l'administration"
    },
    {
        "id": 3,
        "nom": "Techniques de la logistique",
        "description": "Programme de formation en techniques de la logistique"
    },
    {
        "id": 4,
        "nom": "Techniques de la comptabilité et de la gestion",
        "description": "Programme de formation en techniques de la comptabilité et de la gestion"
    }
]


describe("StudentInscriptionForm Component", () => {
    beforeEach(() => {
        (saveStudent as jest.Mock).mockResolvedValue({data: {}});

        (fetchProgrammes as jest.Mock).mockResolvedValue(mock_programs)
    })

    it("renders the form with input fields", async () => {
        render(
            <MemoryRouter initialEntries={['/inscription']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/inscription"
                        element={
                            <ToastContextProvider>
                                <StudentInscriptionForm/>
                            </ToastContextProvider>
                        }
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );

        const lastNameInput = screen.getByLabelText("last-name-label");
        const firstNameInput = screen.getByLabelText("first-name-label");
        const emailInput = screen.getByLabelText("email-label");
        const passwordInput = screen.getByLabelText("password-label");
        const phoneInput = screen.getByLabelText("phone-label");
        const matriculeInput = screen.getByLabelText("matricule-label");
        const programSelect = screen.getByLabelText("programme-label");
        const option1 = await screen.findByLabelText(mock_programs[0].id)
        const option2 = await screen.findByLabelText(mock_programs[1].id)
        const option3 = await screen.findByLabelText(mock_programs[2].id)
        const option4 = await screen.findByLabelText(mock_programs[3].id)


        expect(lastNameInput).toBeInTheDocument();
        expect(firstNameInput).toBeInTheDocument();
        expect(emailInput).toBeInTheDocument();
        expect(passwordInput).toBeInTheDocument();
        expect(phoneInput).toBeInTheDocument();
        expect(matriculeInput).toBeInTheDocument();
        expect(programSelect).toBeInTheDocument();

        expect(option1).toBeInTheDocument()
        expect(option2).toBeInTheDocument()
        expect(option3).toBeInTheDocument()
        expect(option4).toBeInTheDocument()


    });

    it("submits the form with valid data", async () => {
        render(
            <MemoryRouter initialEntries={['/inscription']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/inscription"
                        element={
                            <ToastContextProvider>
                                <StudentInscriptionForm/>
                            </ToastContextProvider>
                        }
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );

        const mock_user = {
            nom: "Doe",
            prenom: "John",
            email: "john.doe@example.com",
            password: "Password1",
            phone: "123-456-7890",
            matricule: "1234567",
            programme_id: "1",
            cv: null,
            activeCv: null,
            internshipsCandidate: null
        }

        const lastNameInput = screen.getByLabelText("last-name-label");
        const firstNameInput = screen.getByLabelText("first-name-label");
        const emailInput = screen.getByLabelText("email-label");
        const passwordInput = screen.getByLabelText("password-label");
        const phoneInput = screen.getByLabelText("phone-label");
        const matriculeInput = screen.getByLabelText("matricule-label");
        const programSelect = screen.getByLabelText("programme-label");
        const submitButton = screen.getByLabelText("submit-button");
        const option1 = await screen.findByLabelText(mock_programs[0].id)
        const option2 = await screen.findByLabelText(mock_programs[1].id)
        const option3 = await screen.findByLabelText(mock_programs[2].id)
        const option4 = await screen.findByLabelText(mock_programs[3].id)


        fireEvent.change(lastNameInput, {target: {value: "Doe"}});
        fireEvent.change(firstNameInput, {target: {value: "John"}});
        fireEvent.change(emailInput, {target: {value: "john.doe@example.com"}});
        fireEvent.change(passwordInput, {target: {value: "Password1"}});
        fireEvent.change(phoneInput, {target: {value: "123-456-7890"}});
        fireEvent.change(matriculeInput, {target: {value: "1234567"}});
        fireEvent.change(programSelect, {target: {value: mock_programs[0].id}});

        fireEvent.click(submitButton);

        expect(saveStudent).toHaveBeenCalledWith(mock_user)
    });

    it('should fail to save and give error toast', async () => {
        render(
            <MemoryRouter initialEntries={['/inscription']} initialIndex={0}>
                <Routes>
                    <Route
                        path="/inscription"
                        element={
                            <ToastContextProvider>
                                <StudentInscriptionForm/>
                            </ToastContextProvider>
                        }
                    >
                    </Route>
                </Routes>
            </MemoryRouter>
        );

        const mock_user = {
            nom: "Doe",
            prenom: "John",
            email: "john.doe@example.com",
            password: "Password1",
            phone: "123-456-7890",
            matricule: "1234567",
            programme_id: "1",
            cv: null,
            activeCv: null,
            internshipsCandidate: null
        }

        const lastNameInput = screen.getByLabelText("last-name-label");
        const firstNameInput = screen.getByLabelText("first-name-label");
        const emailInput = screen.getByLabelText("email-label");
        const passwordInput = screen.getByLabelText("password-label");
        const phoneInput = screen.getByLabelText("phone-label");
        const matriculeInput = screen.getByLabelText("matricule-label");
        const submitButton = screen.getByLabelText("submit-button");


        fireEvent.change(lastNameInput, {target: {value: "Doe"}});
        fireEvent.change(firstNameInput, {target: {value: "John"}});
        fireEvent.change(emailInput, {target: {value: "john.doe@example.com"}});
        fireEvent.change(passwordInput, {target: {value: "Password1"}});
        fireEvent.change(phoneInput, {target: {value: "123-456-7890"}});
        fireEvent.change(matriculeInput, {target: {value: "1234567"}});

        fireEvent.click(submitButton);

        expect(saveStudent).not.toHaveBeenCalled()
        const toast_message = await screen.findByLabelText("toast-message");
        expect(toast_message).toBeInTheDocument();
        const toast_container = await screen.findByLabelText("toast-container");
        expect(toast_container.className).toContain("bg-red");
    });
});
