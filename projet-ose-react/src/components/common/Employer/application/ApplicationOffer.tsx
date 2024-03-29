import React, {ReactElement, useContext, useEffect, useRef, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBriefcase, faCheck, faCircleUser, faMagnifyingGlass, faX} from "@fortawesome/free-solid-svg-icons";
import {NavLink, Outlet, useNavigate, useOutletContext, useParams} from "react-router-dom";
import {getInterOfferCandidates} from "../../../../api/intershipCandidatesAPI";
import axios from "axios";
import {useProps} from "../../../../pages/employer/EmployeurHomePage";
import {useTranslation} from "react-i18next";
import {getOfferById} from "../../../../api/InterOfferJobAPI";
import {ToastContext} from "../../../../hooks/context/ToastContext";
import api from "../../../../api/ConfigAPI";
import {ReactComponent as Icon} from '../../../../assets/icons/back_icon.svg';
interface Props {
    user: any
    studentId: number
    offerId: number
    application: any
    handleAccept: (id: string) => void
    handleRefuse: (id: string) => void
    hasStudentApplied: (internOfferCandidate: any, offerId: number) => boolean
    updateCandidature: () => void
    isReviewing: boolean
    setUpdate: any
}


const ApplicationOffer: React.FC<any> = () => {
    const {id} = useParams();
    const {idApplication} = useParams();
    const [interOfferCandidates, setInterOfferCandidates] = useState<any[]>([]);
    const {offers, user} = useProps();
    const [date, setDate] = useState<Date>(new Date());
    const [studentId, setStudentId] = useState<number>(0);
    const [internshipOffer, setinternshipOffer] = useState<any>();
    const {i18n,t} = useTranslation();
    const fetchedOfferRef = useRef(false);
    const fetchedCandidateRef = useRef(false);
    const updateCandidateRef = useRef(false);
    const navigate = useNavigate();
    const toast = useContext(ToastContext);
    const [isReviewing, setIsReviewing] = useState<boolean>(false);
    const [update, setUpdate] = useState<boolean>(false);
    const [application, setApplication] = useState<any>({});

    const loadOffer = async () => {
        try {
            fetchedOfferRef.current = true
            const response = await getOfferById(parseInt(id!));
            setinternshipOffer(response);
            return response;

        } catch (error) {
            toast.error(t("formField.application.errorFetchOffer.text"));
        } finally {
            fetchedOfferRef.current = false;
        }
    };

    const loadCandidates = async (ids:number[]) => {
        try {
            fetchedCandidateRef.current = true
            const response = await getInterOfferCandidates(ids.join(","));
            setInterOfferCandidates(response);
            return response;
        } catch (error) {
            toast.error(t("formField.application.errorFetchCandidate.text"))
        } finally {
            fetchedCandidateRef.current = false;
        }
    }

    function getCandidates(offer:any) {
        if (!fetchedCandidateRef.current) {
            loadCandidates(offer.internshipCandidates!).then(
                (candidatures) => {
                    console.log("Loaded candidates")
                    candidatures.map((candidature: any) => {
                        let interviewList: any[] = []
                        let requestBody = {"studentId": candidature.etudiant.id, "internOfferId": offer.id}
                        api.post("interview/studentHasInterviewWithInternOffer", requestBody,
                        ).then((res) => {
                            interviewList.push({
                                "offerId": offer.id,
                                "candidateId": candidature.etudiant.id,
                                "alreadyApplied": res.data,
                            })
                        })
                        candidature.interviewList = interviewList
                    })
                }).catch(e => {
                    toast.error(t("formField.application.errorFetchCandidate.text"));
                    console.log(e)
                }
            )
        }
    }

    useEffect(() => {
        if (!fetchedOfferRef.current) loadOffer().then((offer:any) => {
            getCandidates(offer)
            setUpdate(false)
        });
        if (!isReviewing){
            navigate("/employer/home/offers/"+id+"/application")
        }
    }, [!updateCandidateRef.current,update]);


    function handleAccept(id: string) {
        api.post(`intershipCandidates/acceptCandidats/${id}`).then(
            (res) => {
                let newList: any[] = [...interOfferCandidates]

                newList.forEach(candidate => {
                    if (candidate.id == id) {
                        candidate.state = "ACCEPTED"
                    }
                })
                setInterOfferCandidates(newList)
            }
        ).catch(e => {
            console.log(e)
        })
    }

    function handleRefuse(id: string) {
        api.post(`intershipCandidates/declineCandidats/${id}`).then(
            (res) => {
                let newList: any[] = [...interOfferCandidates]

                newList.forEach(candidate => {
                    if (candidate.id == id) {
                        candidate.state = "DECLINED"
                    }
                })
                setInterOfferCandidates(newList)
            }
        ).catch(e => {
            console.log(e)
        })
    }


    function UpdateCandidature() {
        offers.forEach((offer: any) => {
            getCandidates(offer);
        });
    }

    function hasStudentApplied(internOfferCandidate: any, offerId: number): boolean {
        let returnBool = false;
        let interviewList = internOfferCandidate["interviewList"]
        interviewList.forEach((interview: any) => {
            if (interview["offerId"] == offerId) {
                returnBool = interview["alreadyApplied"]
            }
        })
        return returnBool
    }

    const handleReview = (app: any,studentId:number) => {
        setIsReviewing(true)
        setApplication(app)
        setStudentId(studentId)
        const props = {
            user: user,
            studentId: studentId,
            offerId: internshipOffer?.id,
            application: app,
            appId : app.id,
            isReviewing: isReviewing,
        }
        navigate(`/employer/home/offers/${id!}/application/${app.id}/review`,{state: props})
    }

    return (
        <div className="justify-center pb-16">
            <div className="py-6 max-md:pt-24 ">
                <div className="flex items-center justify-between space-x-2">
                    <div className="flex gap-2">
                        <button
                            type="button"
                            className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-red hover:bg-rose-900 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                            onClick={() => navigate("/employer/home/offers")}
                        >
                            {t("Shared.ReturnButton.text")} <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                        </button>
                    </div>
                    <h2 className="text-lg font-bold">
                            <span
                                className="px-6 py-2 inline-flex text-lg leading-5  rounded-full justify-center bg-blue dark:bg-orange text-white dark:text-offwhite"
                            >
                                {internshipOffer?.title}
                            </span>
                    </h2>
                </div>
            </div>
            <div className="flex justify-center">
                <div className="w-full px-12 bg-white dark:bg-dark rounded-xl shadow border border-gray dark:border-darkgray">
                <div className=" py-8 flex max-sm:justify-center">
                    <h1 className="text-3xl font-bold text-black dark:text-white">
                        {t("formField.application.title.text")}
                    </h1>
                </div>
                <div className="flex max-md:justify-center border-t border-neutral-200 dark:border-darkergray">
                    <dl className="divide-y divide-neutral-200 dark:divide-darkergray">
                        {interOfferCandidates.map((candidate) => {
                            return (
                                <div key={candidate.id} className="sm:flex justify-between px-4 py-6 md:grid md:grid-cols-3 md:gap-4 sm:px-0">
                                    <dt className=" font-medium space-y-8 leading-6 dark:text-white">
                                        <div className="space-y-3">
                                            <div>
                                                <p className="font-medium text-neutral-500 dark:text-neutral-300">
                                                    {t("formField.application.wordName.text")}

                                                </p>
                                            </div>
                                            <div className="flex space-x-4 h-10 items-center">
                                                <FontAwesomeIcon icon={faCircleUser} size="xl" className="text-blue dark:text-orange h-full"/>
                                                <div>
                                                    <div className="text-sm font-medium text-black dark:text-white">
                                                        {candidate.etudiant.prenom + " " + candidate.etudiant.nom}
                                                    </div>
                                                    <div className="text-sm text-neutral-500 dark:text-neutral-300">
                                                        {candidate.etudiant.programme_id}
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="space-y-3">
                                            <p className=" font-medium text-neutral-500 dark:text-neutral-300">
                                                {t("formField.application.contactInfo.text")}
                                            </p>
                                            <div className=" font-medium text-black dark:text-white">
                                                <p>{candidate.etudiant.phone}</p>
                                                <p>{candidate.etudiant.email}</p>
                                            </div>
                                        </div>

                                    </dt>
                                    <dd className="flex justify-between font-medium space-y-8 leading-6 dark:text-white max-sm:pt-3">
                                        <div className="space-y-8 ">
                                            <div className="space-y-3">
                                                <p className=" font-medium text-neutral-500 dark:text-neutral-300">
                                                    {t("formField.application.interview.text")}
                                                </p>
                                                <div className="flex font-medium h-10 items-center text-black dark:text-white">
                                                    {
                                                        candidate.date ?
                                                            <p>{new Date(candidate.date).toLocaleDateString(i18n.language,{
                                                                weekday: 'long',
                                                                month: 'long',
                                                                day: 'numeric',
                                                                year: 'numeric',

                                                            })}</p>:
                                                            <p>{t("formField.application.noInterview.text")}</p>
                                                    }
                                                </div>
                                            </div>
                                            <div className="space-y-3">
                                                <p className=" font-medium text-neutral-500 dark:text-neutral-300">
                                                    {t("formField.application.status.text")}
                                                </p>
                                                <div className="font-medium text-black dark:text-white">
                                                    <span className={"px-6 py-1 leading-5 font-semibold rounded-full w-3/4 text-white dark:text-offwhite" + (candidate.state == "PENDING" ? " bg-orange" : candidate.state === "DECLINED" ? " bg-red" : " bg-green")}>
                                                        {t(`formField.application.${candidate.state}.text`)}
                                                    </span>
                                                </div>
                                            </div>
                                        </div>

                                    </dd>
                                    <dd className="max-sm:pt-3">
                                        <div className="space-y-8">
                                            <div className="space-y-3">
                                                <p className=" font-medium text-neutral-500 dark:text-neutral-300">
                                                    {t("formField.application.actions.text")}
                                                </p>
                                                <div className="flex space-x-4 font-medium h-10 items-center text-black dark:text-white">
                                                    <button
                                                        type="button"
                                                        className="inline-flex items-center px-4 py-2 border border-transparent disabled:bg-blue dark:FDSdisabled:text-white disabled:dark:bg-orange shadow-sm text-sm font-medium rounded-md text-white bg-dark hover:bg-blue dark:bg-white dark:text-black dark:hover:text-white dark:hover:bg-orange focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                                        onClick={() => handleReview(candidate, candidate.etudiant.id)}
                                                        disabled={idApplication == candidate.id}
                                                    >
                                                        <FontAwesomeIcon icon={faMagnifyingGlass} className="mr-2" size="lg"/>
                                                        {t("formField.application.review.text")}
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </dd>

                                </div>
                            )
                        })}
                    </dl>
                </div>
            </div>
            </div>
        </div>
    );
}

export function useUser() {
    return useOutletContext<Props>();
}

export default ApplicationOffer;