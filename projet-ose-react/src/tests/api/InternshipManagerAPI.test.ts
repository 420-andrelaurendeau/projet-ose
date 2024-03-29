// Mocker le module où l'instance 'api' est définie
import {
    acceptStudentCv,
    declineStudentCv, getContractById,
    getIntershipOffers,
    getOfferReviewById,
    getStageByEmployeurId,
    getStageByStudentId,
    getStageCountByState,
    getStageCountByStateEmployeur,
    getStages,
    getStudentPendingCv,
    getTotalOfferByState, signDocument
} from "../../api/InternshipManagerAPI";
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

    const params = {
        page: 1,
        size: 10,
        state: 'active',
        sortField: 'date',
        sortDirection: 'desc',
        session: 'sessionToken'
    };

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


            const result = await getTotalOfferByState("");

            expect(api.get).toHaveBeenCalledWith('internshipManager/count', expect.anything());

            expect(result).toEqual(mockData);
        });

        it('handles an error when fetching total offer count by state', async () => {

            (api.get as jest.Mock).mockRejectedValue(new Error('Network error'));

            await expect(getTotalOfferByState("")).rejects.toThrow('Network error');
        });

    });


    describe('getOfferReviewById', () => {
        const mockReview = {
            id: 1,
            content: "Excellent stage",
            rating: 5
        };

        it('should fetch offer review successfully', async () => {
            (api.get as jest.Mock).mockResolvedValue({data: mockReview});

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

    describe('getStudentPendingCv', () => {

        it('fetches pending CVs successfully', async () => {

            const mockData = [{cvId: 1, studentName: 'John Doe'}, {cvId: 2, studentName: 'Jane Doe'}];
            (api.get as jest.Mock).mockResolvedValue({data: mockData});


            const result = await getStudentPendingCv();


            expect(api.get).toHaveBeenCalledWith('internshipManager/studentCv/pending');


            expect(result).toEqual(mockData);
        });

        it('handles errors', async () => {

            const errorMessage = 'Network error';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getStudentPendingCv()).rejects.toThrow(errorMessage);
        });
    });

    describe('acceptStudentCv', () => {
        const id = 123;
        const mockData = {success: true};

        it('should successfully accept a student CV', async () => {
            (api.post as jest.Mock).mockResolvedValue({data: mockData});

            const result = await acceptStudentCv(id);

            expect(api.post).toHaveBeenCalledWith(`internshipManager/studentCv/${id}/accept`);
            expect(result).toEqual(mockData);
        });

        it('should throw an error when the API call fails', async () => {
            const errorMessage = "Erreur lors de l'acceptation du CV";
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(acceptStudentCv(id)).rejects.toThrow(errorMessage);
        });
    });

    describe('declineStudentCv', () => {
        const id = 123;

        it('should successfully decline a student CV', async () => {

            (api.post as jest.Mock).mockResolvedValue({data: 'Success'});

            const result = await declineStudentCv(id);

            expect(api.post).toHaveBeenCalledWith(`internshipManager/studentCv/${id}/decline`);

            expect(result).toBe('Success');
        });

        it('should throw an error when API call fails', async () => {

            (api.post as jest.Mock).mockRejectedValue(new Error('API call failed'));

            await expect(declineStudentCv(id)).rejects.toThrow('API call failed');
        });
    });

    describe('getStageCountByState', () => {

        it('fetches stage count successfully', async () => {
            const mockData = { count: 10 };

            (api.get as jest.Mock).mockResolvedValue({ data: mockData });

            const result = await getStageCountByState("");

            expect(api.get).toHaveBeenCalledWith("stage/count", {"params": {"season": ""}});
            expect(result).toEqual(mockData);
        });

        it('handles error on fetching stage count', async () => {
            (api.get as jest.Mock).mockRejectedValue(new Error('Network error'));

            await expect(getStageCountByState("")).rejects.toThrow('Network error');
        });
    });

    describe('getStageCountByStateEmployeur', () => {
        const mockEmployeurId = 123;

        it('should fetch stage count successfully for an employeur', async () => {
            const mockData = { count: 5 };
            (api.get as jest.Mock).mockResolvedValue({ data: mockData });

            const result = await getStageCountByStateEmployeur(mockEmployeurId);

            expect(api.get).toHaveBeenCalledWith(`stage/countEmployeur/${mockEmployeurId}`);
            expect(result).toEqual(mockData);
        });

        it('should throw an error when the API call fails', async () => {
            const mockError = new Error('API error');
            (api.get as jest.Mock).mockRejectedValue(mockError);

            await expect(getStageCountByStateEmployeur(mockEmployeurId)).rejects.toThrow('API error');
        });
    });

    describe('getStages', () => {

        it('fetches stages successfully', async () => {
            const mockData = { data: [{ id: 1, name: 'Stage 1' }] };
            (api.get as jest.Mock).mockResolvedValue({ data: mockData });

            const result = await getStages(params);

            expect(api.get).toHaveBeenCalledWith('stage/stages', { params: params });
            expect(result).toEqual(mockData);
        });

        it('handles errors when fetching stages', async () => {
            (api.get as jest.Mock).mockRejectedValue(new Error('Error fetching stages'));

            await expect(getStages(params)).rejects.toThrow('Error fetching stages');
        });
    });

    describe('getStageByEmployeurId', () => {

        it('fetches stages for an employer successfully', async () => {
            const mockData = { data: [{ id: 1, name: 'Stage for Employer' }] };
            (api.get as jest.Mock).mockResolvedValue({ data: mockData });

            const employerId = 123;

            const result = await getStageByEmployeurId(params, employerId);

            expect(api.get).toHaveBeenCalledWith(`stage/employeurStage/${employerId}`, { params: params });
            expect(result).toEqual(mockData);
        });

        it('handles errors when fetching stages for an employer', async () => {
            (api.get as jest.Mock).mockRejectedValue(new Error('Error fetching stages'));

            const employerId = 123;

            await expect(getStageByEmployeurId(params, employerId)).rejects.toThrow('Error fetching stages');
        });
    });

    describe('getStageByStudentId', () => {

        it('fetches stages for a student successfully', async () => {
            const mockData = { data: [{ id: 1, name: 'Stage for Student' }] };
            (api.get as jest.Mock).mockResolvedValue({ data: mockData });

            // @ts-ignore
            const param: GetInternshipOffersParams = {
                page: 1,
                size: 10,
                state: 'active',
                sortField: 'date',
                sortDirection: 'desc',
            };

            const studentId = 456;

            const result = await getStageByStudentId(param, studentId);

            expect(api.get).toHaveBeenCalledWith(`stage/studentStage/${studentId}`, { params: param });
            expect(result).toEqual(mockData);
        });

        it('handles errors when fetching stages for a student', async () => {
            (api.get as jest.Mock).mockRejectedValue(new Error('Error fetching stages'));

            const studentId = 456;

            await expect(getStageByStudentId(params, studentId)).rejects.toThrow('Error fetching stages');
        });
    });

    describe('getContractById', () => {
        const mockContractData = { id: '123', details: 'Contract details' };

        it('fetches contract data successfully', async () => {
            (api.get as jest.Mock).mockResolvedValue({ data: mockContractData });
            const contractId = '123';
            const result = await getContractById(contractId);

            expect(api.get).toHaveBeenCalledWith(`contract/${contractId}`);
            expect(result).toEqual(mockContractData);
        });

        it('throws an error when fetching contract data fails', async () => {
            const errorMessage = 'Network error';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));
            const contractId = '123';

            await expect(getContractById(contractId)).rejects.toThrow(errorMessage);
        });

    });

    describe('signDocument', () => {
        const mockForm = { id: '123', signature: 'signature' };

        it('should successfully sign a document', async () => {
            const mockResponse = { data: 'response data' };
            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await signDocument(mockForm);

            expect(api.post).toHaveBeenCalledWith(`contract/save`, mockForm);
            expect(result).toEqual(mockResponse.data);
        });

        it('should throw an error if signing fails', async () => {
            const mockError = new Error('Network error');
            (api.post as jest.Mock).mockRejectedValue(mockError);

            await expect(signDocument(mockForm)).rejects.toThrow('Network error');
        });
    });

});


