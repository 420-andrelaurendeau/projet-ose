import api from '../../api/ConfigAPI';
import {getInterOfferStudent, offresEtudiant} from "../../api/InterOfferJobAPI";



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


describe('InternOfferJobAPI', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('offresEtudiant', () => {
        const setOffersMock = jest.fn();
        const setTotalPagesMock = jest.fn();
        const mockParams = {
            page: 1,
            size: 10,
            sort: 'id,desc'
        }; // Vos paramètres ici


        it('successfully fetches and sets offers', async () => {
            const mockData = {
                data: {
                    content: ['offer1', 'offer2'],
                    totalPages: 3
                }
            };

            (api.get as jest.Mock).mockResolvedValue(mockData);

            await offresEtudiant(setOffersMock, setTotalPagesMock, mockParams);

            expect(api.get).toHaveBeenCalledWith('interOfferJob/OffersEtudiant', {params: mockParams});
            expect(setOffersMock).toHaveBeenCalledWith(mockData.data.content);
            expect(setTotalPagesMock).toHaveBeenCalledWith(mockData.data.totalPages);
        });

        it('handles API errors without setting offers', async () => {
            const errorMessage = 'Error fetching offers';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await offresEtudiant(setOffersMock, setTotalPagesMock, mockParams);

            expect(api.get).toHaveBeenCalledWith('interOfferJob/OffersEtudiant', {params: mockParams});
            expect(setOffersMock).not.toHaveBeenCalled();
            expect(setTotalPagesMock).not.toHaveBeenCalled();
        });

    });

});

