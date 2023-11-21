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
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    it('fetches internship offers successfully', async () => {
        const mockData = { data: 'some data' };
        (api.get as jest.Mock).mockResolvedValue({ data: mockData });
        mockedLocalStorage.getItem.mockReturnValue('fakeToken');

        const params = {
            page: 1,
            size: 10,
            state: 'active',
            sortField: 'date',
            sortDirection: 'desc',
            session: 'autunm2023'
        };

        const result = await getIntershipOffers(params);

        expect(api.get).toHaveBeenCalledWith('internshipManager/offers', expect.anything());
        expect(result).toEqual(mockData);
    });

    it('sends the correct parameters to the API', async () => {
        // Simuler une réponse (peu importe les données dans ce cas)
        (api.get as jest.Mock).mockResolvedValue({ data: {} });

        // Paramètres pour appeler getInternshipOffers
        const params = {
            page: 1,
            size: 10,
            state: 'active',
            sortField: 'date',
            sortDirection: 'desc',
            session: 'sessionToken'
        };

        // Appeler la fonction
        await getIntershipOffers(params);

        // Vérifier si les bons paramètres sont passés à l'API
        expect(api.get).toHaveBeenCalledWith(
            'internshipManager/offers',
            expect.objectContaining({
                params: {
                    page: 1,
                    size: 10,
                    state: 'active',
                    sortField: 'date',
                    sortDirection: 'desc',
                    session: 'sessionToken'
                }
            })
        );
    });

    it('throws an error when the API call fails', async () => {
        // Simuler une erreur
        const errorMessage = 'Network error';
        (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

        // Paramètres pour appeler getInternshipOffers
        const params = {
            page: 1,
            size: 10,
            state: 'active',
            sortField: 'date',
            sortDirection: 'desc',
            session: 'sessionToken'
        };

        // S'attendre à ce que la fonction lance une erreur
        await expect(getIntershipOffers(params)).rejects.toThrow(errorMessage);
    });

});
