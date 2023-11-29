import {fireEvent, render, screen} from "@testing-library/react";
import UploadCVForm from "../../components/common/student/form/UploadCVForm";
import React from "react";
import {ToastContextProvider} from "../../hooks/context/ToastContext";
import {getUser} from "../../api/UtilisateurAPI";
import {saveCvStudent} from "../../api/StudentApi";
import {act} from "react-dom/test-utils";


jest.spyOn(console, "error").mockImplementation(() => {
});

jest.mock("../../api/UtilisateurAPI", () => {
    return {
        getUser: jest.fn(),
    };
});

jest.mock("../../api/StudentApi", () => {
    return {
        saveCvStudent: jest.fn(),
    };
});


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

//TODO more coverage

describe("UploadCV Component", () => {
    beforeEach(() => {
        const mockResponse = {data: "mocked data"};
        (getUser as jest.Mock).mockResolvedValue(mockResponse);
    })

    it("renders the component upload button to be greyed out and cursor default", () => {
        render(<UploadCVForm/>);
        const button = screen.getByLabelText("upload_button");
        expect(button).toBeInTheDocument();
        expect(button.classList.contains("bg-gray")).toBe(true)
        expect(button.classList.contains("cursor-default")).toBe(true)
    });

    it("handles file selection and displays the selected file", async () => {
        render(<UploadCVForm/>);
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

    it("validates the selected file error", async () => {
        render(<UploadCVForm/>);
        const invalidFile = new File(["Invalid file content"], "invalid-file.exe", {
            type: "application/octet-stream",
        });
        const fileInput = screen.getByLabelText("file");

        fireEvent.change(fileInput, {
            target: {files: [invalidFile]},
        });

        const errorMessage = await screen.findByText("formField.InternshipOfferForm.file.validation.BadTypeFile");
        expect(errorMessage).toBeInTheDocument();

        fireEvent.load(fileInput, {
            target: {files: [invalidFile]},
        })

        const button = screen.getByLabelText("upload_button");
        expect(button).toBeInTheDocument();
        expect(button.classList.contains("bg-gray")).toBe(true)
        expect(button.classList.contains("cursor-default")).toBe(true)
    });


    it("handles file submission and shows a success message", async () => {
        let response = Promise.resolve({data: "success"});
        (saveCvStudent as jest.Mock).mockResolvedValue(response);
        render(
            <ToastContextProvider>
                <UploadCVForm/>
            </ToastContextProvider>);

        const sampleFile = new File(["Sample file content"], "test.pdf");
        const fileInput = screen.getByLabelText("file");
        act(() => {
            fireEvent.change(fileInput, {
                target: {files: [sampleFile]},
            });
        })

        //VERY IMPORTANT THAT YOU AWAIT SOMETHING THAT CHANGES AFTER A STATE CHANGE LIKE HERE
        const selectedFile = await screen.findByText("test.pdf");
        expect(selectedFile).toBeInTheDocument();


        const submitButton = await screen.findByLabelText("upload_button");
        act(() => {
            fireEvent.click(submitButton);
        })

        const successMessage = await screen.findByText("cv.success");
        try{
            const selectedFileGone = screen.getByText("test.pdf");
            fail("selected file should be gone")
        }catch (e){

        }

        expect(successMessage).toBeInTheDocument();
    });

    it("handles unsuccessful file submission and shows an error message", async () => {
        // Mock the saveCvStudent function to reject the promise
        (saveCvStudent as jest.Mock).mockRejectedValue(new Error("CV upload failed"));

        render(
            <ToastContextProvider>
                <UploadCVForm />
            </ToastContextProvider>
        );

        const sampleFile = new File(["Sample file content"], "test.pdf");
        const fileInput = screen.getByLabelText("file");

        act(() => {
            fireEvent.change(fileInput, {
                target: { files: [sampleFile] },
            });
        });

        // Ensure the selected file is displayed
        const selectedFile = await screen.findByText("test.pdf");
        expect(selectedFile).toBeInTheDocument();

        const submitButton = await screen.findByLabelText("upload_button");

        act(() => {
            fireEvent.click(submitButton);
        });


        // Check that the error message is displayed
        const errorMessage = await screen.findByText("cv.error");
        expect(errorMessage).toBeInTheDocument();

        const selectedFileAgain = await screen.findByText("test.pdf");
        expect(selectedFileAgain).toBeInTheDocument();
    });
});
