// noinspection JSConstantReassignment

import {getUser} from "./UtilisateurAPI";

class LocalStorageMock extends Storage{
    constructor(){
        super();
        this.getItem = jest.fn();
        this.setItem = jest.fn();
        this.removeItem = jest.fn();
    }
}

global.localStorage = new LocalStorageMock;

describe("getUser",()=>{
    test("should return a user",async ()=>{
        const user = await getUser("max@gmail.com")

    })
})