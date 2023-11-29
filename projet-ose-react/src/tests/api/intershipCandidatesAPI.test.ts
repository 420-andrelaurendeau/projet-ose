import api from '../../api/ConfigAPI';
import {getInterOfferCandidates, saveStudentInternshipOffer} from "../../api/intershipCandidatesAPI";


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


describe('intershipCandidatesAPI', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('saveStudentInternshipOffer', () => {
        const mockInterOfferJob = { id: 1};
        const mockStudent = { id: 1};
        const mockCv = { id: 1};

        it('successfully saves a student internship offer', async () => {
            const mockResponse = { data: 'success' };
            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await saveStudentInternshipOffer(mockInterOfferJob, mockStudent, mockCv);

            expect(api.post).toHaveBeenCalledWith('intershipCandidates/saveCandidats', {
                etudiant: mockStudent,
                internOfferJob: mockInterOfferJob,
                files: [mockCv]
            });
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during saving a student internship offer', async () => {
            const errorMessage = 'Error saving offer';
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(saveStudentInternshipOffer(mockInterOfferJob, mockStudent, mockCv)).rejects.toThrow(errorMessage);
        });
    });

    describe('getInterOfferCandidates', () => {
        const mockId = 123;

        it('successfully fetches internship offer candidates by ID', async () => {
            const mockResponse = { data: [{ candidateId: 1, name: 'John Doe' }] };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await getInterOfferCandidates(mockId);

            expect(api.get).toHaveBeenCalledWith('intershipCandidates/getInternshipCandidatesByIds/' + mockId);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during fetching internship offer candidates', async () => {
            const errorMessage = 'Error fetching candidates';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(getInterOfferCandidates(mockId)).rejects.toThrow(errorMessage);
        });
    });
});