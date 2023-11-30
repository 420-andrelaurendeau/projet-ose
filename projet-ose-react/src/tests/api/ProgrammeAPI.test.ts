import api from '../../api/ConfigAPI';
import {getProgrammes} from "../../api/ProgrammeAPI";

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


describe('ProgrammeAPI', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('getProgrammes', () => {

        it('successfully fetches programmes', async () => {
            const mockResponse = { data: [{ programmeId: 1, name: 'Programme 1' }] }; // Données de réponse simulées
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getProgrammes();

            expect(api.get).toHaveBeenCalledWith('programme/programmes');
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during fetching programmes', async () => {
            const errorMessage = 'Error fetching programmes';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getProgrammes()).rejects.toThrow(errorMessage);
        });
    });
});