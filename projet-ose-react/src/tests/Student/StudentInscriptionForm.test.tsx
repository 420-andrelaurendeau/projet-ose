import React from "react";
import {render, screen, fireEvent, waitFor, act} from "@testing-library/react";
import axios from "axios"; // You may want to mock axios requests
import StudentInscriptionForm from "../../components/common/student/form/StudentInscriptionForm";

jest.mock("axios"); // Mock axios requests

(axios.post as jest.Mock).mockResolvedValue({data: {}});
(axios.get as jest.Mock).mockResolvedValue({data: []});

//TODO REDO WITH API DOESNT USE PURE AXIOS ANYMORE

describe("StudentInscriptionForm Component", () => {
    it("renders the form with input fields", () => {
        render(<StudentInscriptionForm/>);

        const lastNameInput = screen.getByPlaceholderText("Last Name");
        const firstNameInput = screen.getByPlaceholderText("First Name");
        const emailInput = screen.getByPlaceholderText("Email");
        const passwordInput = screen.getByPlaceholderText("Password");
        const phoneInput = screen.getByPlaceholderText("Phone");
        const matriculeInput = screen.getByPlaceholderText("Matricule");
        const programSelect = screen.getByText("Select a Program");

        expect(lastNameInput).toBeInTheDocument();
        expect(firstNameInput).toBeInTheDocument();
        expect(emailInput).toBeInTheDocument();
        expect(passwordInput).toBeInTheDocument();
        expect(phoneInput).toBeInTheDocument();
        expect(matriculeInput).toBeInTheDocument();
        expect(programSelect).toBeInTheDocument();
    });

    it("submits the form with valid data", async () => {
        render(<StudentInscriptionForm/>);

        const lastNameInput = screen.getByPlaceholderText("Last Name");
        const firstNameInput = screen.getByPlaceholderText("First Name");
        const emailInput = screen.getByPlaceholderText("Email");
        const passwordInput = screen.getByPlaceholderText("Password");
        const phoneInput = screen.getByPlaceholderText("Phone");
        const matriculeInput = screen.getByPlaceholderText("Matricule");
        const programSelect = screen.getByText("Select a Program");
        const submitButton = screen.getByText("Submit");

        fireEvent.change(lastNameInput, {target: {value: "Doe"}});
        fireEvent.change(firstNameInput, {target: {value: "John"}});
        fireEvent.change(emailInput, {target: {value: "john.doe@example.com"}});
        fireEvent.change(passwordInput, {target: {value: "Password1"}});
        fireEvent.change(phoneInput, {target: {value: "123-456-7890"}});
        fireEvent.change(matriculeInput, {target: {value: "1234567"}});
        fireEvent.change(programSelect, {target: {value: "1"}});

        fireEvent.click(submitButton);

    });
});
