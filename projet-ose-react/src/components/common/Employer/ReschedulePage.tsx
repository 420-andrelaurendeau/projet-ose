import React, {useContext, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {ReactComponent as Icon} from '../../../assets/icons/back_icon.svg';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLock} from "@fortawesome/free-solid-svg-icons";
import api from "../../../api/ConfigAPI";
import {ToastContext} from "../../../hooks/context/ToastContext";
import {updateInterview} from "../../../api/InterviewApi";
import {useAuth} from "../../../authentication/AuthContext";

export const ReschedulePage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const {t} = useTranslation();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.application.applicant");
    const [description, setDescription] = useState<string>("")
    const [date, setDate] = useState<string>("")
    const [time, setTime] = useState<string>("")
    const toast = useContext(ToastContext);
    const {userRole} = useAuth();

    function handleSubmit(){
        clearInputs()
        console.log()
        let requestBody = {"id": location.state.interview?.id, "studentId" : location.state.student?.id, "internOfferId":location.state.offer?.id, "date":date, "description":description}
        updateInterview(requestBody).then((res)=>{
            console.log(res)
            toast.success(fields.success.text)
            navigate(`/${userRole}/home/interview`)
        }).catch(e => {
            toast.error(fields.failed.text)
            console.log(e)
        })
    }

    console.log(location.state.interview)
    function clearInputs(){
        setDate("")
        setDescription("")
    }

    function setTheTime(value: string) {
        setTime(value)
        setDate(date.split("T")[0] + "T" + value + ":00.000Z")
        console.log(date)
    }

    return (
        <div className="">
                <button
                    type="button"
                    className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red hover:bg-rose-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                    onClick={() => navigate(`/employer/home/interview`)}
                >
                    {t("Shared.ReturnButton.text")} <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                </button>
                <div className="items-center text-center">
                    <div className="space-y-3">
                        <label className="dark:text-white"
                               htmlFor={"description"}>
                            {fields.description.text}
                        </label>
                        <textarea required value={description}
                                  onChange={e => setDescription(e.target.value)}
                                  name={fields.description.text}
                                  className={"mt-1 p-2 w-full border border-black rounded-md placeholder:text-xs dark:bg-white text-blue dark:text-orange dark:border-0"}/>
                    </div>
                    <div className="space-y-3">
                        <label className="dark:text-white" htmlFor="date">Date</label>
                        <input required value={date.split("T")[0]} onChange={e => setDate(e.target.value)}
                               type="date"
                               name={"date"}
                               className="dark:bg-white mt-1 p-2 w-full border border-black text-blue dark:text-orange rounded-md dark:border-0 "/>
                    </div>
                    <div className="space-y-3">
                        <label className="dark:text-white" htmlFor="time">{fields.hours}</label>
                        <input required value={time} onChange={e => setTheTime(e.target.value)}
                               type="time"
                               name={"time"}
                               className="mt-1 p-2 w-full border border-black text-blue dark:text-orange rounded-md dark:white dark:border-0 "/>
                    </div>
                    <button
                        className="w-full mt-14 flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange"
                        onClick={handleSubmit}
                    >
                        {fields.submit.text}
                    </button>
                </div>
        </div>
    )
}