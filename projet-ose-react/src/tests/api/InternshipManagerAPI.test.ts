// Mocker le module où l'instance 'api' est définie
import { getIntershipOffers } from "../../api/InternshipManagerAPI";

jest.mock('../../api/ConfigAPI', () => {
    return {
        __esModule: true,
        default: {
            get: jest.fn().mockImplementation(() => Promise.resolve({ data: {} })), // Mock implémenté ici
            post: jest.fn(),
            interceptors: {
                request: { use: jest.fn(), eject: jest.fn() },
                response: { use: jest.fn(), eject: jest.fn() }
            }
        }
    };
});

import api from '../../api/ConfigAPI';

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

describe('getInternshipOffers', () => {
    afterEach(() => {
        api.get.mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    it('fetches internship offers successfully', async () => {
        const mockData = { data: 'some data' };
        api.get.mockResolvedValue({ data: mockData });
        mockedLocalStorage.getItem.mockReturnValue('fakeToken');

        const params = {
            page: 1,
            size: 10,
            state: 'active',
            sortField: 'date',
            sortDirection: 'desc',
            session: 'sessionToken'
        };

        const result = await getIntershipOffers(params);

        expect(api.get).toHaveBeenCalledWith('internshipManager/offers', expect.anything());
        expect(result).toEqual(mockData);
    });
});
