// Mock the "api" module
import api from "./ConfigAPI";
jest.mock("./ConfigAPI", () => {
    return {
        get: jest.fn(), // You can add more mock functions as needed.
    };
});

// Now you can write your test for the "getUser" function
// Import your "getUser" function after mocking "api" as shown above.
import { getUser } from "./UtilisateurAPI";

describe("getUser function", () => {
    it("should handle a successful API call", async () => {
        // Mock the API response
        const mockResponse = { data: "mocked data" };
        (api.get as jest.Mock).mockResolvedValue(mockResponse);

        // Call the "getUser" function
        const result = await getUser("example@example.com");

        // Assert that the function works as expected
        expect(result).toEqual(mockResponse.data);
        expect(api.get).toHaveBeenCalledWith("utilisateur/utilisateur/example@example.com");
    });

    it("should handle an API error", async () => {
        // Mock the API call to throw an error
        const mockError = new Error("API error");
        (api.get as jest.Mock).mockRejectedValue(mockError);

        // Call the "getUser" function and expect it to throw an error
        await expect(getUser("example@example.com")).rejects.toThrowError("API error");
    });
});
