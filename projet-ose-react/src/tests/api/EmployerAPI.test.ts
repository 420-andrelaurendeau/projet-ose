import api from '../../api/ConfigAPI';
import {saveEmployer} from "../../api/EmployerAPI";


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


describe('EmployerApi', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('saveEmployer', () => {
        const employer = { name: "Employer Name", email: "employer@example.com" };

        it('successfully saves an employer', async () => {
            const mockResponse = { data: 'success' };
            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await saveEmployer(employer);

            expect(api.post).toHaveBeenCalledWith(`auth/register/employeur`, employer);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during saving an employer', async () => {
            const errorMessage = 'Error saving employer';
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(saveEmployer(employer)).rejects.toThrow(errorMessage);
        });
    });
});