import {getUser} from "./UtilisateurAPI";

describe("getUser",()=>{
    test("should return a user",async ()=>{
        const user = await getUser("max@gmail.com")

    })
})