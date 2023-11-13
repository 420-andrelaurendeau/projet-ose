import React, {ReactElement, useContext, useEffect, useState} from "react";
import {NavLink, useLocation, useNavigate, useOutletContext} from "react-router-dom"
import axios from "axios";
import {useUser} from "./ApplicationOffer";
import {useTranslation} from "react-i18next";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLock, faTimesCircle} from "@fortawesome/free-solid-svg-icons";
import {ToastContext} from "../../../../hooks/context/ToastContext";
import api from "../../../../api/ConfigAPI";

export default function ApplicationDetails ():ReactElement{

    const navigate = useNavigate()
    const [date, setDate] = useState<string>("")
    const {application,studentId,offerId, handleAccept, handleRefuse,hasStudentApplied, isReviewing, updateCandidature} = useUser()
    const [description, setDescription] = useState<string>("")
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.application." + i18n.language.slice(0, 2) + ".applicant");
    const toast = useContext(ToastContext);

    useEffect(()=>{
        setDate("")
        updateCandidature()
    },[])

    function handleSubmit(){
        clearInputs()
        let requestBody = {"studentId" : studentId, "internOfferId":offerId, "date":date, "description":description}
        api.post("interview/save", requestBody).then((res)=>{
            console.log(res)
            updateCandidature()
            toast.success(fields.success.text)
            navigate("/employer/home/offers/"+offerId+"/application")
        }).catch(e => {
            toast.error("Error", "An error has occurred", "error")
            console.log(e)})
    }

    function clearInputs(){
        setDate("")
        setDescription("")
    }

    return(
        <div className="">
            <div className="bg-white dark:bg-dark rounded-xl py-5 px-6 shadow">
                <div className="px-4 sm:px-0">
                    <h3 className="text-base dark:text-white font-semibold leading-7 text-gray-900">
                        {fields.title.text}
                    </h3>
                    <p className="mt-1 max-w-2xl text-sm leading-6 text-neutral-500 dark:text-neutral-300">
                        {fields.subtitle.text}
                    </p>
                </div>
                <div className="mt-6 border-t border-neutral-200 dark:border-darkgray">
                    <dl className="divide-y divide-neutral-200 dark:divide-darkgray">
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {fields.fullName.text}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.etudiant?.prenom + " " + application?.etudiant?.nom}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {fields.applicationFor.text}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.internOfferJob?.title}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {fields.email.text}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.etudiant?.email}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {fields.phone.text}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.etudiant?.phone}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {fields.attachments.text}
                            </dt>
                            <dd className="mt-2 text-sm text-gray-900 sm:col-span-2 sm:mt-0">
                                <ul role="list" className="divide-y divide-neutral-100 dark:divide-darkergray rounded-md border border-neutral-200 dark:border-darkgray">
                                    <li className="flex items-center justify-between py-4 pl-4 pr-5 text-sm leading-6">
                                        <div className="flex w-0 flex-1 items-center">
                                            <div className="h-5 w-5 flex-shrink-0 text-gray-400" aria-hidden="true" />
                                            <div className="ml-4 flex min-w-0 flex-1 gap-2">
                                                <span className="truncate font-medium dark:text-white "></span>
                                                <span className="flex-shrink-0 text-neutral-500 dark:text-neutral-300">{

                                                }</span>
                                            </div>
                                        </div>
                                        <div className="ml-4 flex-shrink-0">
                                            <a href="#" className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800">
                                                {fields.download.text}
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </dd>
                        </div>
                        {
                            application.state === "PENDING" &&
                                <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                                <dt className="text-sm font-medium leading-6 dark:text-white">
                                    {fields.actions.text}
                                </dt>
                                <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                    <div className="flex gap-2">
                                        <button
                                            type="button"
                                            className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-green hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                            onClick={() => {
                                                handleAccept(application.id);
                                                application.state = "ACCEPTED";
                                                updateCandidature();
                                            }}
                                        >
                                            {fields.accept.text}
                                        </button>
                                        <button
                                            type="button"
                                            className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red hover:bg-rose-900  focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                            onClick={() => {
                                                handleRefuse(application.id)
                                                application.state = "DECLINED";
                                            }}
                                        >
                                            {fields.reject.text}
                                        </button>
                                    </div>
                                </dd>
                            </div>
                        }
                        {
                             !hasStudentApplied(application, offerId) &&
                                <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                                <dt className="text-sm font-medium leading-6 dark:text-white">
                                    {fields.schedule.text}
                                </dt>
                                <dd className="mt-1 text-sm leading-6 text-neutral-500  dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                    <div
                                        className="xs:w-full lg:w-1/2 border px-10 py-10 border-neutral-200 dark:border-darkergray  bg-white dark:bg-dark rounded-lg ">
                                        {
                                            application.state === "ACCEPTED" ?
                                                <div>
                                                    <div className="space-y-3">
                                                        <label className="dark:text-white"
                                                               htmlFor={"description"}>
                                                            {fields.description.text}
                                                        </label>
                                                        <textarea required value={description}
                                                                  onChange={e => setDescription(e.target.value)}
                                                                  name={fields.description.text}
                                                                  className={"mt-1 p-2 w-full border border-black rounded-md placeholder:text-xs dark:bg-softdark text-blue dark:text-orange dark:border-0"}/>
                                                    </div>
                                                    <div className="space-y-3">
                                                        <label className="dark:text-white" htmlFor="date">Date</label>
                                                        <input required value={date} onChange={e => setDate(e.target.value)}
                                                               type="date"
                                                               name={"date"}
                                                               className="mt-1 p-2 w-full border border-black text-blue dark:text-orange rounded-md dark:bg-softdark dark:border-0 "/>
                                                    </div>
                                                    <button
                                                        className="w-full mt-14 flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange"
                                                        onClick={handleSubmit}
                                                    >
                                                        {fields.submit.text}
                                                    </button>
                                                </div> :
                                                <div className="flex justify-center">
                                                    <p className="text-xl dark:text-white font-bold flex justify-center">
                                                        <FontAwesomeIcon icon={faLock}
                                                                         className="w-20 h-auto text-dark dark:text-white flex justify-center"/>
                                                    </p>
                                                </div>
                                        }
                                    </div>
                                </dd>
                            </div>
                        }
                    </dl>
                </div>
            </div>
        </div>
    )
}