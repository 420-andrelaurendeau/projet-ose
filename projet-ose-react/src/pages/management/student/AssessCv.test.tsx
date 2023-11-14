import { act, fireEvent, render, screen } from '@testing-library/react';
import AssessCv from './AssessCv';
import { acceptStudentCv, declineStudentCv, getStudentPendingCv } from '../../../api/InternshipManagerAPI';
import { ReviewFile } from '../../../model/ReviewFile';
import { useToast } from '../../../hooks/state/useToast';

jest.mock('../../../api/InternshipManagerAPI');
jest.mock('../../../hooks/state/useToast');
jest.mock('./AssessCv')

describe('AssessCv', () => {
    const mockFiles: ReviewFile[] = [
        {
            id: 1,
            fileName: 'cv1.pdf',
            content: 'JVBERi0xLjQKJcfs...',
            etudiant: {
                id: 1,
                nom: 'Doe',
                prenom: 'John',
                matricule: '123456',
                email: 'john.doe@example.com',
                phone : '123456789',
                entreprise: "entreprise",
                programme: "as",
            },
            isAccepted: "PENDING",
        },
        {
            id: 2,
            fileName: 'cv2.pdf',
            content: 'JVBERi0xLjQKJcfs...',
            etudiant: {
                id: 1,
                nom: 'Doe',
                prenom: 'John',
                matricule: '123456',
                email: 'john.doe@example.com',
                phone : '123456789',
                entreprise: "entreprise",
                programme: "as",
            },
            isAccepted: "PENDING",
        },
    ];

    beforeEach(() => {
        (getStudentPendingCv as jest.MockedFunction<typeof getStudentPendingCv>).mockResolvedValue(mockFiles);
    });

    afterEach(() => {
        jest.resetAllMocks();
    });

    it('should render a list of files to review', async () => {
        await act(async () => {
            render(<AssessCv />);
        });

        expect(screen.getByText('Doe, John')).toBeInTheDocument();
        expect(screen.getByText('Doe, John')).toBeInTheDocument();
    });

    it('should download the file when handleDownloadFile is called', async () => {
        const spy = jest.spyOn(window.URL, 'createObjectURL');
        await act(async () => {
            render(<AssessCv />);
        });

        const downloadButton = screen.getAllByTestId('download-button')[0];
        fireEvent.click(downloadButton);

        expect(spy).toHaveBeenCalled();
        spy.mockRestore();
    });

    it('should call acceptStudentCv when the accept button is clicked', async () => {
        const acceptSpy = jest.spyOn(window, await acceptStudentCv(1));
        await act(async () => {
            render(<AssessCv />);
        });

        const acceptButton = screen.getAllByText('Accept')[0];
        fireEvent.click(acceptButton);

        expect(acceptSpy).toHaveBeenCalledWith(mockFiles[0].id);
    });

    it('should call declineStudentCv when the decline button is clicked', async () => {
        const declineSpy = jest.spyOn(window, await declineStudentCv(1));
        await act(async () => {
            render(<AssessCv />);
        });

        const declineButton = screen.getAllByText('Decline')[0];
        fireEvent.click(declineButton);

        expect(declineSpy).toHaveBeenCalledWith(mockFiles[0].id);
    });

    it('should show a success toast when a file is accepted', async () => {
        (acceptStudentCv as jest.MockedFunction<typeof acceptStudentCv>).mockResolvedValueOnce({});
        const toastSuccessSpy = jest.spyOn(useToast(), 'success');
        await act(async () => {
            render(<AssessCv />);
        });

        const acceptButton = screen.getAllByText('Accept')[0];
        fireEvent.click(acceptButton);

        expect(toastSuccessSpy).toHaveBeenCalled();
        toastSuccessSpy.mockRestore();
    });

    it('should show an error toast when accepting a file fails', async () => {
        (acceptStudentCv as jest.MockedFunction<typeof acceptStudentCv>).mockRejectedValueOnce({});
        const toastErrorSpy = jest.spyOn(useToast(), 'error');
        await act(async () => {
            render(<AssessCv />);
        });

        const acceptButton = screen.getAllByText('Accept')[0];
        fireEvent.click(acceptButton);

        expect(toastErrorSpy).toHaveBeenCalled();
        toastErrorSpy.mockRestore();
    });

    it('should show a success toast when a file is declined', async () => {
        (declineStudentCv as jest.MockedFunction<typeof declineStudentCv>).mockResolvedValueOnce({});
        const toastSuccessSpy = jest.spyOn(useToast(), 'success');
        await act(async () => {
            render(<AssessCv />);
        });

        const declineButton = screen.getAllByText('Decline')[0];
        fireEvent.click(declineButton);

        expect(toastSuccessSpy).toHaveBeenCalled();
        toastSuccessSpy.mockRestore();
    });

    it('should show an error toast when declining a file fails', async () => {
        (declineStudentCv as jest.MockedFunction<typeof declineStudentCv>).mockRejectedValueOnce({});
        const toastErrorSpy = jest.spyOn(useToast(), 'error');
        await act(async () => {
            render(<AssessCv />);
        });

        const declineButton = screen.getAllByText('Decline')[0];
        fireEvent.click(declineButton);

        expect(toastErrorSpy).toHaveBeenCalled();
        toastErrorSpy.mockRestore();
    });
});