import api from '../../api/ConfigAPI';
import {fetchInterviewsEmployer} from "../../api/InterviewApi";
import {employeurGetContractById, employeurSaveContract} from "../../api/ContractAPI";


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


describe('InterviewApi', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });


    describe('employeurGetContractById', () => {
        const contractId = 123;

        it('successfully fetches contract details by ID', async () => {
            const mockResponse = { data: { id: contractId, details: 'Contract Details' } };
            (api.get as jest.Mock).mockResolvedValue(mockResponse);

            const result = await employeurGetContractById(contractId);

            expect(api.get).toHaveBeenCalledWith(`contract/${contractId}`);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during fetching contract by ID', async () => {
            const errorMessage = 'Error fetching contract';
            (api.get as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(employeurGetContractById(contractId)).rejects.toThrow(errorMessage);
        });
    });

    describe('employeurSaveContract', () => {
        const contract = {id:1};
        const userRole = "employer";

        it('successfully saves a contract', async () => {
            const mockResponse = { data: 'success' };
            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await employeurSaveContract(contract, userRole);

            expect(api.post).toHaveBeenCalledWith(`contract/${userRole}/save`, contract);
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during saving a contract', async () => {
            const errorMessage = 'Error saving contract';
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(employeurSaveContract(contract, userRole)).rejects.toThrow(errorMessage);
        });
    });
});