import api from '../../api/ConfigAPI';
import {fetchInterviews, fetchInterviewsCountForStudent, saveCvStudent} from "../../api/StudentApi";



jest.mock('../../api/ConfigAPI', () => {
    return {
        __esModule: true,
        default: {
            get: jest.fn().mockImplementation(() => Promise.resolve({data: {}})), // Mock implémenté ici
            post: jest.fn(),
            interceptors: {
                request: {use: jest.fn(), eject: jest.fn()},
                response: {use: jest.fn(), eject: jest.fn()}
            }
        }
    };
});

const mockedLocalStorage = {
    getItem: jest.fn(),
    setItem: jest.fn(),
    removeItem: jest.fn(),
    clear: jest.fn(),
    key: jest.fn(),
    length: 0
};

Object.defineProperty(window, 'localStorage', {
    value: mockedLocalStorage,
    writable: true
});


describe('StudentAPI', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('saveCvStudent', () => {
        const matricule = 123;
        const cv = {id:1};

        it('successfully saves a student CV', async () => {
            const mockResponse = { data: 'success' };
            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await saveCvStudent(matricule, cv);

            expect(api.post).toHaveBeenCalledWith(`student/addCv/${matricule}`, cv);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during saving a student CV', async () => {
            const errorMessage = 'Error saving CV';
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(saveCvStudent(matricule, cv)).rejects.toThrow(errorMessage);
        });
    });

    describe('fetchInterviews', () => {
        const userId = 123;
        const queryParams = { page: 1, size: 10, sortField: 'date', sortDirection: 'asc' };

        it('successfully fetches interviews for a user', async () => {
            const mockResponse = { data: [{ interviewId: 1, details: 'Interview Details' }] };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await fetchInterviews(userId, queryParams);

            expect(api.get).toHaveBeenCalledWith(`interview/getByStudentId/${userId}`, { params: queryParams });
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during fetching interviews for a user', async () => {
            const errorMessage = 'Error fetching interviews';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(fetchInterviews(userId, queryParams)).rejects.toThrow(errorMessage);
        });
    });

    describe('fetchInterviewsCountForStudent', () => {
        const userId = 123;

        it('successfully fetches interview count for a student', async () => {
            const mockResponse = { data: 5 };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await fetchInterviewsCountForStudent(userId);

            expect(api.get).toHaveBeenCalledWith(`interview/getCountByStudentId/${userId}`);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during fetching interview count for a student', async () => {
            const errorMessage = 'Error fetching interview count';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(fetchInterviewsCountForStudent(userId)).rejects.toThrow(errorMessage);
        });
    });

});