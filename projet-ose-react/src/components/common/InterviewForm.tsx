import React, {ReactElement, useContext, useEffect, useState} from "react";
import {NavLink, useLocation, useNavigate, useOutletContext} from "react-router-dom"
import axios from "axios";
import {useUser} from "./CandidatureOffer";

function InterviewForm ():ReactElement{


    const [date, setDate] = useState<string>("")
    const {user} = useUser()
    const [description, setDescription] = useState<string>("")
    const navigate = useNavigate()

    useEffect(()=>{
        setDate("")
    },[])

    let location = useLocation();

    function handleSubmit(){
        clearInputs()
        let requestBody = {"studentId" : location.state.studentId, "internOfferId":location.state.offerId, "date":date, "description":description}
        axios.post("http://localhost:8080/api/interview/save", requestBody).then((res)=>{
            console.log(res)
        }).catch(e => {
            console.log(e)})
        navigate('/employeur/home/offre/candidature', {
            state: user,
        });
    }

    function clearInputs(){
        setDate("")
        setDescription("")
    }

    return(
        <div className="backdrop-blur-sm flex justify-center fixed z-50 top-0 left-0 w-full h-full bg-black bg-opacity-50 items-center p-3 overflow-auto">
            <NavLink
                to="/employeur/home/offre/candidature"
                className="fixed h-full w-full"
                state={user}
            />
            <div className="relative xs:w-2/3 md:w-1/2 lg:w-1/3 px-10 py-10 bg-white dark:bg-dark rounded-lg ">
                <div className="space-y-3">
                    <label className="dark:text-white" htmlFor={"description"}>Description</label>
                    <textarea required value={description} onChange={e => setDescription(e.target.value)} name={"description"} className={"mt-1 p-2 w-full border border-black rounded-md placeholder:text-xs dark:bg-softdark text-blue dark:text-orange dark:border-0"} />
                </div>
                <div className="space-y-3">
                    <label className="dark:text-white" htmlFor="date">Date</label>
                    <input required value={date} onChange={e=> setDate(e.target.value)} type="date" name={"date"} className="mt-1 p-2 w-full border border-black text-blue dark:text-orange rounded-md dark:bg-softdark dark:border-0 "/>
                </div>


                <button  className="w-full mt-14 flex-1 text-black font-bold p-2 rounded-md dark:text-white bg-blue dark:bg-orange" onClick={handleSubmit}>Submit</button>
            </div>
        </div>
    )
}

export default InterviewForm