import axios from "axios";
import {getUser} from "../../../api/UtilisateurAPI";
import {renderHook} from "@testing-library/react";
import UploadCV from "./UploadCV";

jest.spyOn(console, "error").mockImplementation(() => {
});

jest.mock("axios", () => ({
    ...jest.requireActual("axios"),
    post: jest.fn(),
    get: jest.fn(),
}))

describe("getUser", ()=>{
    beforeEach(()=>{
        jest.useRealTimers();
    })

    test("should return user", ()=>{
        const {result} = renderHook(()=>UploadCV());

    })
})