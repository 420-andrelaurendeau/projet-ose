import api from '../../api/ConfigAPI';
import {saveEmployerOpinion} from "../../api/StageAPI";


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


describe('StageApi', () => {
    afterEach(() => {
        (api.get as jest.Mock).mockReset();
        mockedLocalStorage.getItem.mockReset();
    });

    describe('saveEmployerOpinion', () => {
        const stageId = 123;
        const opinion = "Excellent stage";

        it('successfully saves employer opinion', async () => {
            const mockResponse = { data: 'success' };
            (api.post as jest.Mock).mockResolvedValue(mockResponse);

            const result = await saveEmployerOpinion(stageId, opinion);

            expect(api.post).toHaveBeenCalledWith(`stage/saveEmployerOpinion`, {stageId, opinion});
            expect(result).toEqual(mockResponse.data);
        });

        it('handles errors during saving employer opinion', async () => {
            const errorMessage = 'Error saving opinion';
            (api.post as jest.Mock).mockRejectedValue(new Error(errorMessage));

            await expect(saveEmployerOpinion(stageId, opinion)).rejects.toThrow(errorMessage);
        });
    });

});