import React, {ReactElement, useEffect, useState} from "react";
import {useLocation} from "react-router-dom"
import axios from "axios";

function InterviewForm ():ReactElement{


    const [date, setDate] = useState<string>("")

    const [description, setDescription] = useState<string>("")

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
    }

    function clearInputs(){
        setDate("")
        setDescription("")
    }

    return(
        <div className={"w-1/3"}>
            <label htmlFor={"description"}>Description</label>
            <textarea value={description} onChange={e => setDescription(e.target.value)} name={"description"} className={"mt-1 p-2 w-full border border-black rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"} >
            </textarea>
            <label htmlFor="date">Date</label>
            <input value={date} onChange={e=> setDate(e.target.value)} type="date" name={"date"} className={"mt-1 p-2 w-full border border-black text-black rounded-md dark:bg-softdark dark:border-0"}/>
            <button className={`w-full flex-1 text-black font-bold p-2 rounded-md`} onClick={handleSubmit}>Submit</button>
        </div>
    )
}

export default InterviewForm