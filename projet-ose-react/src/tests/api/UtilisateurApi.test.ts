// Mock the "api" module
import api from "../../api/ConfigAPI";
jest.mock("./ConfigAPI", () => {
    return {
        get: jest.fn(),
    };
});

import { getUser } from "../../api/UtilisateurAPI";

describe("getUser function", () => {
    it("should handle a successful API call", async () => {
        const mockResponse = { data: "mocked data" };
        (api.get as jest.Mock).mockResolvedValue(mockResponse);

        const result = await getUser("example@example.com");

        expect(result).toEqual(mockResponse.data);
        expect(api.get).toHaveBeenCalledWith("utilisateur/utilisateur/example@example.com");
    });

    it("should handle an API error", async () => {
        const mockError = new Error("API error");
        (api.get as jest.Mock).mockRejectedValue(mockError);

        await expect(getUser("example@example.com")).rejects.toThrowError("API error");
    });
});
