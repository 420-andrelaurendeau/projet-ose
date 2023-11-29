import api from '../../api/ConfigAPI';
import {fetchInterviewsEmployer} from "../../api/InterviewApi";


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


describe('InterviewApi', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('fetchInterviewsEmployer', () => {
        const userId = 123;
        const queryParams = { page: 1, size: 10, sortField: 'date', sortDirection: 'asc' };

        it('successfully fetches interviews for an employer', async () => {
            const mockResponse = { data: [{ interviewId: 1, details: 'Interview Details' }] };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await fetchInterviewsEmployer(userId, queryParams);

            expect(api.get).toHaveBeenCalledWith(`interview/getByEmployerId/${userId}`, { params: queryParams });
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during fetching interviews for an employer', async () => {
            const errorMessage = 'Error fetching interviews';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(fetchInterviewsEmployer(userId, queryParams)).rejects.toThrow(errorMessage);
        });
    });
});
