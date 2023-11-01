import {fireEvent, render, screen} from "@testing-library/react";
import UploadCV from "./UploadCV";
import React from "react";
import {ToastContextProvider} from "../../../hooks/context/ToastContext";
import {saveCvStudent} from "../../../api/StudentApi";
import axios from "axios";
import api from "../../../api/ConfigAPI"
import mocked = jest.mocked;

jest.spyOn(console, "error").mockImplementation(() => {
});

jest.mock("../../../api/ConfigAPI", () => {
    return {
        get: jest.fn(() => {
        }),
        post: jest.fn(),
    }
})

jest.mock("../../../api/UtilisateurAPI", () => {
    return {
        getUser: jest.fn(()=>{return Promise.resolve({
            matricule: "123456",
        })})
    }
})
jest.mock('axios', () => {
    return {
        create: jest.fn(() => ({
                interceptors: {
                    request: {use: jest.fn(), eject: jest.fn()},
                    response: {use: jest.fn(), eject: jest.fn()}
                },
            }
        )),
        get: jest.fn(),
        post: jest.fn(),
    }
})

const mockedAxios: jest.Mocked<typeof axios> = axios as jest.Mocked<typeof axios>;
const mockedUser: any = jest.genMockFromModule('../../../api/UtilisateurAPI');

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

describe("UploadCV Component", () => {
    beforeEach(()=>{

    })

    it("renders the component upload button to be greyed out and cursor default", () => {
        render(<UploadCV/>);
        const button = screen.getByLabelText("upload_button");
        expect(button).toBeInTheDocument();
        expect(button.classList.contains("bg-gray")).toBe(true)
        expect(button.classList.contains("cursor-default")).toBe(true)
    });

    it("handles file selection and displays the selected file", async () => {
        render(<UploadCV/>);
        const sampleFile = new File(["Sample file content"], "test.pdf");
        const fileInput = screen.getByLabelText("file");


        fireEvent.change(fileInput, {
            target: {files: [sampleFile]},
        });

        const selectedFile = await screen.findByText("test.pdf");
        expect(selectedFile).toBeInTheDocument();

        const button = screen.getByLabelText("upload_button");
        expect(button).toBeInTheDocument();
        expect(button.classList.contains("bg-blue")).toBe(true)
        expect(button.classList.contains("cursor-pointer")).toBe(true)

    });

    it("validates the selected file", async () => {
        render(<UploadCV/>);
        const invalidFile = new File(["Invalid file content"], "invalid-file.exe", {
            type: "application/octet-stream",
        });
        const fileInput = screen.getByLabelText("file");

        fireEvent.change(fileInput, {
            target: {files: [invalidFile]},
        });

        const errorMessage = await screen.findByText("formField.InternshipOfferForm.file.validation.BadTypeFile");
        expect(errorMessage).toBeInTheDocument();

        const button = screen.getByLabelText("upload_button");
        expect(button).toBeInTheDocument();
        expect(button.classList.contains("bg-gray")).toBe(true)
        expect(button.classList.contains("cursor-default")).toBe(true)
    });


    it("handles file submission and shows a success message", async () => {
        //TODO test with backend calls need to be figured out
        let responsePost: any = {data: "Success"}
        mockedAxios.post.mockResolvedValue(responsePost);

        render(
            <ToastContextProvider>
                <UploadCV/>
            </ToastContextProvider>);

        const sampleFile = new File(["Sample file content"], "test.pdf");
        const fileInput = screen.getByLabelText("file");

        fireEvent.change(fileInput, {
            target: {files: [sampleFile]},
        });

        const submitButton = screen.getByLabelText("upload_button");
        fireEvent.click(submitButton);
        const successMessage = await screen.findByText("cv.upload_success");
        expect(successMessage).toBeInTheDocument();
    });
});
