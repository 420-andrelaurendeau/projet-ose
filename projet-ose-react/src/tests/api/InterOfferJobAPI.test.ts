import api from '../../api/ConfigAPI';
import {
    allStudentInternshipOffers,
    allStudentInternshipOffersBySeason, allStudentOffers,
    getInterOfferStudent,
    offresEtudiant, saveInterOfferJob
} from "../../api/InterOfferJobAPI";
import {InternshipOffer} from "../../model/IntershipOffer";


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



});

