import axios from "axios";
import {getUser} from "../../../api/UtilisateurAPI";
import {fireEvent,screen, render, renderHook} from "@testing-library/react";
import UploadCV from "./UploadCV";
import {FileEntity} from "../../../model/FileEntity";

jest.spyOn(console, "error").mockImplementation(() => {
});

jest.mock("axios", () => ({
    ...jest.requireActual("axios"),
    post: jest.fn(),
    get: jest.fn(),
}))

jest.mock('react-i18next', () => ({
    // this mock makes sure any components using the translate hook can use it without a warning being shown
    useTranslation: () => {
        return {
            t: (str:any) => str,
            i18n: {
                changeLanguage: () => new Promise(() => {}),
            },
        };
    },
    initReactI18next: {
        type: '3rdParty',
        init: () => {},
    }
}));

describe("UploadCV Component", () => {
    it("renders the component upload button to be greyed out and cursor default", () => {
        render(<UploadCV />);
        const fileInput = screen.getByText("cv.upload_button");
        expect(fileInput).toBeInTheDocument();
        expect(fileInput.classList.contains("bg-gray")).toBe(true)
        expect(fileInput.classList.contains("cursor-default")).toBe(true)
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
    });

    // You can write more test cases for other functionalities like handling form submission, etc.
});
