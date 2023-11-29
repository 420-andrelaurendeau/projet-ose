import api from '../../api/ConfigAPI';
import {
    allEmployeurInternshipOffersBySeason,
    allStudentInternshipOffers,
    allStudentInternshipOffersBySeason,
    allStudentOffers,
    getAllPendingInterOfferJob, getEmployeurOffers,
    getEmployeurSeason,
    getInterOfferJob,
    getInterOfferStudent, getOfferApprovedSeasons, getOfferById,
    getOfferReviewRequestById, getStudentAppliedOffers,
    offresEtudiant,
    saveInterOfferJob,
    saveOfferReviewRequest
} from "../../api/InterOfferJobAPI";
import {InternshipOffer} from "../../model/IntershipOffer";
import axios from "axios";


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

    describe('allStudentInternshipOffersBySeason', () => {
        const selectedOption = 'Summer2023';

        it('successfully fetches internship offers by season', async () => {
            const mockData = {
                data: ['offer1', 'offer2', 'offer3']
            };

            (api.get as jest.Mock).mockResolvedValue(mockData);

            const result = await allStudentInternshipOffersBySeason(selectedOption);

            expect(api.get).toHaveBeenCalledWith('interOfferJob/student/season/' + selectedOption);
            expect(result).toEqual(mockData.data);
        });

        it('handles API errors without returning offers', async () => {
            const errorMessage = 'Error fetching offers by season';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(allStudentInternshipOffersBySeason(selectedOption)).rejects.toThrow(errorMessage);
        });
    });

    describe('allStudentInternshipOffers', () => {

        it('successfully fetches all internship offers for students', async () => {
            const mockData = {
                data: ['offer1', 'offer2', 'offer3']
            };

            (api.get as jest.Mock).mockResolvedValue(mockData);

            const result = await allStudentInternshipOffers();

            expect(api.get).toHaveBeenCalledWith('interOfferJob/allOffers');
            expect(result).toEqual(mockData.data);
        });

        it('handles API errors without returning offers', async () => {
            const errorMessage = 'Error fetching all offers';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(allStudentInternshipOffers()).rejects.toThrow(errorMessage);
        });
    });

    describe('allStudentOffers', () => {

        it('successfully fetches all offers for students', async () => {
            const mockData = {
                data: ['offer1', 'offer2', 'offer3']
            };

            (api.get as jest.Mock).mockResolvedValue(mockData);

            const result = await allStudentOffers();

            expect(api.get).toHaveBeenCalledWith('interOfferJob/student/allOffers');
            expect(result).toEqual(mockData.data);
        });

        it('handles API errors without returning offers', async () => {
            const errorMessage = 'Error fetching student offers';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(allStudentOffers()).rejects.toThrow(errorMessage);
        });
    });

    describe('saveInterOfferJob', () => {
        const mockInterOfferJob : InternshipOffer = {
            title: "title",
            location: "location",
            description: "description",
            salaryByHour: 10,
            startDate: Date.prototype,
            endDate: Date.prototype,
            programmeId: 1,
            file: {
                id: 1,
                fileName: "name",
                content: "type",
                isAccepted: "data",
                uploaderId: 1,
            },
            employeurId: 1,
            state: "state",
        }
        const mockId = 1;

        const mockInterOffer = {
            title: mockInterOfferJob.title,
            location: mockInterOfferJob.location,
            description: mockInterOfferJob.description,
            salaryByHour: mockInterOfferJob.salaryByHour,
            startDate: mockInterOfferJob.startDate,
            endDate: mockInterOfferJob.endDate,
            programmeId: mockInterOfferJob.programmeId!,
            file: mockInterOfferJob.file,
            employeurId: mockId,
        }

        it('successfully saves an InterOfferJob', async () => {
            const mockResponse = { data: 'response data' };

            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await saveInterOfferJob(mockInterOfferJob, mockId);

            expect(api.post).toHaveBeenCalledWith('interOfferJob/save', {
                ...mockInterOffer,
                //employeurId: mockId,
            });
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during saving InterOfferJob', async () => {
            const errorMessage = 'Error saving offer';
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(saveInterOfferJob(mockInterOfferJob, mockId)).rejects.toThrow(errorMessage);
        });
    });

    describe('saveOfferReviewRequest', () => {
        const mockOfferReviewRequest = {
            id: 1,
            comment: "Good offer",
            state: "state",
            internOfferId: 1,
            internshipmanagerId: 1,
        };

        it('successfully saves an OfferReviewRequest', async () => {
            const mockResponse = { data: 'response data' };
            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await saveOfferReviewRequest(mockOfferReviewRequest);

            expect(api.post).toHaveBeenCalledWith('offerReviewRequest/save', mockOfferReviewRequest);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during saving OfferReviewRequest', async () => {
            const errorMessage = 'Error saving offer review request';
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(saveOfferReviewRequest(mockOfferReviewRequest)).rejects.toThrow(errorMessage);
        });
    });

    describe('getOfferReviewRequestById', () => {
        const mockId = 123;

        it('successfully fetches an OfferReviewRequest by ID', async () => {
            const mockResponse = {
                data: { id: mockId, comment: 'Review Comment' }
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getOfferReviewRequestById(mockId);

            expect(api.get).toHaveBeenCalledWith(`offerReviewRequest/get/${mockId}`);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching OfferReviewRequest by ID', async () => {
            const errorMessage = 'Error fetching offer review request';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getOfferReviewRequestById(mockId)).rejects.toThrow(errorMessage);
        });
    });

    describe('getInterOfferJob', () => {
        const mockEmail = "test@example.com"; // Exemple d'email
        const mockParams = { page: 1, size: 10 }; // Exemple de paramètres

        it('successfully fetches InterOfferJobs based on email and params', async () => {
            const mockResponse = {
                data: [{ id: 1, title: 'Offer 1' }, { id: 2, title: 'Offer 2' }] // Données de réponse simulées
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getInterOfferJob(mockEmail, mockParams);

            expect(api.get).toHaveBeenCalledWith(`interOfferJob/OffersEmp/${mockEmail}`, { params: mockParams });
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching InterOfferJobs', async () => {
            const errorMessage = 'Error fetching offers';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getInterOfferJob(mockEmail, mockParams)).rejects.toThrow(errorMessage);
        });
    });

    describe('allEmployeurInternshipOffersBySeason', () => {
        const selectedOption = 'Summer2023'; // Exemple de saison sélectionnée
        const email = 'employer@example.com'; // Exemple d'email d'employeur

        it('successfully fetches internship offers by season for an employer', async () => {
            const mockResponse = {
                data: ['offer1', 'offer2', 'offer3'] // Données de réponse simulées
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await allEmployeurInternshipOffersBySeason(selectedOption, email);

            expect(api.get).toHaveBeenCalledWith(`interOfferJob/${email}/season/${selectedOption}`);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching offers by season for an employer', async () => {
            const errorMessage = 'Error fetching offers for employer by season';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(allEmployeurInternshipOffersBySeason(selectedOption, email)).rejects.toThrow(errorMessage);
        });
    });

    describe('getEmployeurSeason', () => {
        const email = 'employer@example.com';

        it('successfully fetches seasons for an employer', async () => {
            const mockResponse = {
                data: ['Summer2023', 'Winter2023']
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getEmployeurSeason(email);

            expect(api.get).toHaveBeenCalledWith(`interOfferJob/${email}/getSeason`);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching seasons for an employer', async () => {
            const errorMessage = 'Error fetching seasons for employer';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getEmployeurSeason(email)).rejects.toThrow(errorMessage);
        });
    });

    describe('getEmployeurOffers', () => {
        const email = 'employer@example.com';

        it('successfully fetches offers for an employer', async () => {
            const mockResponse = {
                data: ['offer1', 'offer2', 'offer3']
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getEmployeurOffers(email);

            expect(api.get).toHaveBeenCalledWith(`interOfferJob/${email}/getOffers`);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching offers for an employer', async () => {
            const errorMessage = 'Error fetching offers for employer';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getEmployeurOffers(email)).rejects.toThrow(errorMessage);
        });
    });

    describe('getInterOfferStudent', () => {
        const mockParams = { page: 1, size: 10 };

        it('successfully fetches InterOfferJobs based on params', async () => {
            const mockResponse = {
                data: [{ id: 1, title: 'Offer 1' }, { id: 2, title: 'Offer 2' }]
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getInterOfferStudent(mockParams);

            expect(api.get).toHaveBeenCalledWith(`interOfferJob/OffersEtudiant`, { params: mockParams });
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching InterOfferJobs', async () => {
            const errorMessage = 'Error fetching offers';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getInterOfferStudent(mockParams)).rejects.toThrow(errorMessage);
        });
    });

    describe('getStudentAppliedOffers', () => {
        const studentId = 123;

        it('successfully fetches offers applied by a student', async () => {
            const mockResponse = {
                data: [
                    { appliedOffer: 'Offer 1', appliedFiles: ['File 1', 'File 2'] },
                    { appliedOffer: 'Offer 2', appliedFiles: ['File 3'] }
                ]
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getStudentAppliedOffers(studentId);

            expect(api.get).toHaveBeenCalledWith(`/student/${studentId}/offersApplied`, {});
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching applied offers for a student', async () => {
            const errorMessage = 'Error fetching applied offers for student';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getStudentAppliedOffers(studentId)).rejects.toThrow(errorMessage);
        });
    });

    describe('getOfferById', () => {
        const offerId = 123;

        it('successfully fetches offer details by ID', async () => {
            const mockResponse = {
                data: { id: offerId, title: 'Offer Title', description: 'Offer Description' } // Données de réponse simulées
            };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getOfferById(offerId);

            expect(api.get).toHaveBeenCalledWith(`internshipManager/offer/${offerId}`);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching offer by ID', async () => {
            const errorMessage = 'Error fetching offer';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getOfferById(offerId)).rejects.toThrow(errorMessage);
        });
    });

    describe('getOfferApprovedSeasons', () => {
        it('successfully fetches approved seasons', async () => {
            const mockResponse = { data: ['Spring 2023', 'Summer 2023'] };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getOfferApprovedSeasons();

            expect(api.get).toHaveBeenCalledWith('interOfferJob/getOfferApprovedSeasons');
            expect(result).toEqual(mockResponse.data);
        });

        it('handles API errors during fetching approved seasons', async () => {
            const errorMessage = 'Error fetching approved seasons';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getOfferApprovedSeasons()).rejects.toThrow(errorMessage);
        });
    });
});

