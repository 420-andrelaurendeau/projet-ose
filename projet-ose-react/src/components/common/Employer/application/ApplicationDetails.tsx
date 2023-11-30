import React, {ReactElement, useContext, useEffect, useState} from "react";
import {NavLink, useLocation, useNavigate, useOutletContext, useParams} from "react-router-dom"
import axios from "axios";
import {useUser} from "./ApplicationOffer";
import {useTranslation} from "react-i18next";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLock, faTimesCircle} from "@fortawesome/free-solid-svg-icons";
import {ToastContext} from "../../../../hooks/context/ToastContext";
import api from "../../../../api/ConfigAPI";
import {base64ToArrayBuffer, blobToURL, downloadURI} from "../../preparedoc/utils/Utils";
import {Buffer} from "buffer";
import ViewPDFModal from "../offer/ViewPDFModal";
import {getInterOfferCandidates} from "../../../../api/intershipCandidatesAPI";
import {ReactComponent as Icon} from '../../../../assets/icons/back_icon.svg';
import {studentHasInterviewWithInternOffer} from "../../../../api/InterviewApi";

export default function ApplicationDetails ():ReactElement{

    const {idApplication,id} = useParams()
    const navigate = useNavigate()
    const location = useLocation()
    const [date, setDate] = useState<string>("")
    const [time, setTime] = useState<string>("")
    const {studentId,offerId, isReviewing} = location.state
    const [application, setApplication] = useState<any>(null)
    const [description, setDescription] = useState<string>("")
    const {i18n,t} = useTranslation();
    const toast = useContext(ToastContext);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [file, setFile] = useState(null);

    useEffect(()=>{
        setDate("")
        const load = async () => {
            try {
                const response = await getInterOfferCandidates(idApplication)
                let interviewList: any[] = []
                let requestBody = {"studentId": response[0].etudiant.id, "internOfferId": response[0].internOfferJob.id}
                    studentHasInterviewWithInternOffer(requestBody)
                    .then((res) => {
                    interviewList.push({
                        "offerId": response[0].internOfferJob.id,
                        "candidateId": response[0].etudiant.id,
                        "alreadyApplied": res.data,
                    })
                })
                response[0].interviewList = interviewList
                setApplication(response[0]);
                console.log(response[0])
            }catch (error){
                console.log(error)
                toast.error(t("formField.application.applicant.errorFetchCandidate.text"));
            }
        }
        load()
    },[])

    function handleAccept(id: string) {
        api.post(`intershipCandidates/acceptCandidats/${id}`)
        setApplication({...application, state: "ACCEPTED"})
    }
    function handleRefuse(id: string) {
        api.post(`intershipCandidates/declineCandidats/${id}`)
        setApplication({...application, state: "DECLINED"})
    }

    function hasStudentApplied(internOfferCandidate: any, offerId: number): boolean {
        let returnBool = false;
        let interviewList = internOfferCandidate["interviewList"]
        interviewList.map((interview: any) => {
            if (interview["offerId"] == offerId) {
                returnBool = interview["alreadyApplied"]
            }
        })
        return returnBool
    }

    const getFileSize = (file:any): string => {
        let sizeInBytes = 0
        if (file.content){
            sizeInBytes = Buffer.from(file.content).length;
            if (sizeInBytes < 1000) {
                return sizeInBytes + " B";
            }else if (sizeInBytes < 1000000) {
                return sizeInBytes / 1000 + " KB";
            }else if (sizeInBytes < 1000000000) {
                return Math.round(sizeInBytes / 10000) / 100 + " MB";
            }
        }
        return sizeInBytes + " B";
    }

    const getPDF = async (file:any) => {
        const pdfBytes = base64ToArrayBuffer(file.content)
        if (pdfBytes) {
            const blob = new Blob([new Uint8Array(pdfBytes)]);
            return await blobToURL(blob);
        }else return null
    }

    function handleSubmit(){
        clearInputs()
        let requestBody = {"studentId" : studentId, "internOfferId":offerId, "date":date, "description":description}
        api.post("interview/save", requestBody).then((res)=>{
            console.log(res)
            application.state = "ACCEPTED"
            application.date = res.data.date
            toast.success(t("formField.application.applicant.success.text"));

            navigate("/employer/home/offers/"+offerId+"/application")
        }).catch(e => {
            toast.error("Error", "An error has occurred", "error")
            console.log(e)})
    }

    function clearInputs(){
        setDate("")
        setDescription("")
    }

    function setTheTime(value: string) {
        setTime(value)
        setDate(date.split("T")[0] + "T" + value + ":00.000Z")
        console.log(date)
    }

    return(
        application &&
        <div className="pb-4 max-md:pt-24">
            <div className="flex items-center justify-between space-x-2">
                <div className="flex gap-2 pb-4">
                    <button
                        type="button"
                        className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red hover:bg-rose-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                        onClick={() => navigate(`/employer/home/offers/${id}/application`)}
                    >
                        {t("formField.application.applicant.back.text")}
                        <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                    </button>
                </div>
            </div>
            <div className="bg-white dark:bg-dark rounded-xl py-5 px-6 shadow">
                <div className="px-4 sm:px-0">
                    <h3 className="text-base dark:text-white font-semibold leading-7 text-gray-900">
                        {t("formField.application.applicant.title.text")}
                    </h3>
                    <p className="mt-1 max-w-2xl text-sm leading-6 text-neutral-500 dark:text-neutral-300">
                        {t("formField.application.applicant.subtitle.text")}
                    </p>
                </div>
                <div className="mt-6 border-t border-neutral-200 dark:border-darkgray">
                    <dl className="divide-y divide-neutral-200 dark:divide-darkgray">
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.application.applicant.fullName.text")}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.etudiant?.prenom + " " + application?.etudiant?.nom}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.application.applicant.applicationFor.text")}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.internOfferJob?.title}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.application.applicant.email.text")}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.etudiant?.email}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.application.applicant.phone.text")}
                            </dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {application?.etudiant?.phone}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.application.applicant.attachments.text")}
                            </dt>
                            <dd className="mt-2 text-sm text-gray-900 sm:col-span-2 sm:mt-0">
                                <ul role="list" className="divide-y divide-neutral-100 dark:divide-darkergray rounded-md border border-neutral-200 dark:border-darkgray">
                                    {
                                        application?.files.map((file:any) => (
                                            <li key={file.id} className="flex items-center justify-between py-4 pl-4 pr-5 text-sm leading-6">
                                                <div className="flex w-0 flex-1 items-center">
                                                    <div className="h-5 w-5 flex-shrink-0 text-gray-400"
                                                         aria-hidden="true"/>
                                                    <div className="ml-4 flex min-w-0 flex-1 gap-2">
                                                        <span
                                                            className="truncate font-medium dark:text-white ">{file.fileName}</span>
                                                        <span
                                                            className="flex-shrink-0 text-neutral-500 dark:text-neutral-300">{
                                                            getFileSize(file)
                                                        }</span>
                                                    </div>
                                                </div>
                                                <div className="ml-4 flex-shrink-0 space-x-5">
                                                    <button
                                                        className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800"
                                                        onClick={() => {
                                                            setIsModalOpen(true)
                                                            setFile(file)
                                                        }}
                                                    >
                                                        {t("formField.application.applicant.view.text")}
                                                    </button>
                                                    <button
                                                        type="button"
                                                        className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800"
                                                        onClick={() => {
                                                            downloadURI(getPDF(file), file.fileName!)
                                                        }}
                                                    >
                                                        {t("formField.application.applicant.download.text")}
                                                    </button>
                                                </div>
                                            </li>
                                        ))
                                    }
                                </ul>
                            </dd>
                        </div>
                        {
                            application.state === "PENDING" &&
                                <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                                <dt className="text-sm font-medium leading-6 dark:text-white">
                                    {t("formField.application.applicant.actions.text")}
                                </dt>
                                <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                    <div className="flex gap-2">
                                        <button
                                            aria-label={"accept-button"}
                                            type="button"
                                            className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-green hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                            onClick={() => {
                                                handleAccept(application.id);
                                                application.state = "ACCEPTED";
                                            }}
                                        >
                                            {t("formField.application.applicant.accept.text")}
                                        </button>
                                        <button
                                            aria-label={"refuse-button"}
                                            type="button"
                                            className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red hover:bg-rose-900  focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                            onClick={() => {
                                                handleRefuse(application.id)
                                                application.state = "DECLINED";
                                            }}
                                        >
                                            {t("formField.application.applicant.reject.text")}
                                        </button>
                                    </div>
                                </dd>
                            </div>
                        }
                        {
                             !application.date &&
                                <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                                <dt className="text-sm font-medium leading-6 dark:text-white">
                                    {t("formField.application.applicant.schedule.text")}
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
                                                            {t("formField.application.applicant.description.text")}
                                                        </label>
                                                        <textarea
                                                            aria-label={"interview-desc"}
                                                            required value={description}
                                                                  onChange={e => setDescription(e.target.value)}
                                                                  name={t("formField.application.applicant.description.text")}
                                                                  className={"mt-1 p-2 w-full border border-black rounded-md placeholder:text-xs dark:bg-softdark text-blue dark:text-orange dark:border-0"}/>
                                                    </div>
                                                    <div className="space-y-3">
                                                        <label className="dark:text-white" htmlFor="date">Date</label>
                                                        <input required value={date.split("T")[0]} onChange={e => setDate(e.target.value)}
                                                               type="date"
                                                               name={"date"}
                                                               className="mt-1 p-2 w-full border border-black text-blue dark:text-orange rounded-md dark:bg-softdark dark:border-0 "/>
                                                    </div>
                                                    <div className="space-y-3">
                                                        <label className="dark:text-white" htmlFor="time">
                                                            {t("formField.application.applicant.hours")}
                                                        </label>
                                                        <input required value={time} onChange={e => setTheTime(e.target.value)}
                                                               type="time"
                                                               name={"time"}
                                                               className="mt-1 p-2 w-full border border-black text-blue dark:text-orange rounded-md dark:bg-softdark dark:border-0 "/>
                                                    </div>
                                                    <button
                                                        aria-label={"submit-button"}
                                                        className="w-full mt-14 flex-1 text-white font-bold p-2 rounded-md bg-blue dark:bg-orange"
                                                        onClick={handleSubmit}
                                                    >
                                                        {t("formField.application.applicant.submit.text")}
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
            {
                isModalOpen &&
                <ViewPDFModal ismodal={true} file={file} setIsModalOpen={setIsModalOpen} />
            }
        </div>
    )
}