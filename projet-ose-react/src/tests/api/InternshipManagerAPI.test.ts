// Mocker le module où l'instance 'api' est définie
import {getIntershipOffers, getOfferReviewById, getTotalOfferByState} from "../../api/InternshipManagerAPI";
import api from '../../api/ConfigAPI';

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


describe('InternshipManagerAPI', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('getInternshipOffers', () => {

        it('fetches internship offers successfully', async () => {
            const mockData = {data: 'some data'};
            (api.get as jest.Mock).mockResolvedValue({data: mockData});
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
            (api.get as jest.Mock).mockResolvedValue({data: {}});

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

    describe('getTotalOfferByState', () => {

        it('fetches total offer count by state successfully', async () => {

            const mockData = {total: 5};


            (api.get as jest.Mock).mockResolvedValue({data: mockData});


            const result = await getTotalOfferByState();

            expect(api.get).toHaveBeenCalledWith('internshipManager/count', expect.anything());

            expect(result).toEqual(mockData);
        });

        it('handles an error when fetching total offer count by state', async () => {

            (api.get as jest.Mock).mockRejectedValue(new Error('Network error'));

            await expect(getTotalOfferByState()).rejects.toThrow('Network error');
        });

    });


    describe('getOfferReviewById', () => {
        const mockReview = {
            id: 1,
            content: "Excellent stage",
            rating: 5
        };

        it('should fetch offer review successfully', async () => {
            (api.get as jest.Mock).mockResolvedValue({ data: mockReview });

            const reviewId = 1;
            const review = await getOfferReviewById(reviewId);

            expect(api.get).toHaveBeenCalledWith(`internshipManager/offer/${reviewId}/review`);
            expect(review).toEqual(mockReview);
        });

        it('should throw an error when request fails', async () => {
            const errorMessage = 'Network error';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            const reviewId = 1;

            await expect(getOfferReviewById(reviewId)).rejects.toThrow(errorMessage);
        });
    });



});


