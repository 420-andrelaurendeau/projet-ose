import React, {ReactElement, useContext, useEffect, useRef, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBriefcase, faCheck, faCircleUser, faX} from "@fortawesome/free-solid-svg-icons";
import {NavLink, Outlet, useOutletContext, useParams} from "react-router-dom";
import {getInterOfferCandidates} from "../../../api/intershipCandidatesAPI";
import axios from "axios";
import {useProps} from "../../../pages/employer/EmployeurHomePage";
import {InterOfferJob} from "../../../model/IntershipOffer";
import {useTranslation} from "react-i18next";
import {getOfferById} from "../../../api/InterOfferJobAPI";
import {ToastContext} from "../../../hooks/context/ToastContext";

interface User {
    user: any
}

const apiClient = axios.create({
    baseURL: 'http://localhost:8080/api',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        'Accept': 'application/json',
        'Access-Control-Allow-Origin': 'http://localhost:3000',
    },
});

const CandidatureOffer: React.FC<any> = () => {
    const {id} = useParams();
    const [interOfferCandidates, setInterOfferCandidates] = useState<any[]>([]);
    const {offers, user} = useProps();
    const props = {
        user: user,
    }
    const [internshipOffer, setinternshipOffer] = useState<InterOfferJob>();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField");
    const fetchedOfferRef = useRef(false);
    const fetchedCandidateRef = useRef(false);

    const toast = useContext(ToastContext);

    const loadOffer = async () => {
        try {

            fetchedOfferRef.current = true
            const response = await getOfferById(parseInt(id!));
            setinternshipOffer(response);
            console.log(response);
            return response;

        } catch (error) {
            toast.error(fields.InternshipOfferList.errorFetchOffer);
        } finally {
            fetchedOfferRef.current = false;
        }
    };

    const loadCandidates = async (ids:number[]) => {
        try {
            fetchedCandidateRef.current = true
            const response = await getInterOfferCandidates(ids.join(","));
            setInterOfferCandidates(response);
            console.log(response);
            return response;
        }catch (error){
            toast.error(fields.InternshipOfferList.errorFetchOffer);
        } finally {
            fetchedCandidateRef.current = false;
        }
    }

    useEffect(() => {
        if (!fetchedOfferRef.current) loadOffer().then((offer:any) => {
            console.log("Offer loaded")
            if (!fetchedCandidateRef.current){
                loadCandidates(offer.internshipCandidates).then(
                    (candidatures) => {
                        console.log("Candidates loaded")
                        candidatures.forEach((candidature: any) => {
                            let interviewList: any[] = []
                            offers.forEach((offer: any) => {
                                let requestBody = {"studentId": candidature.etudiant.id, "internOfferId": offer.id}
                                apiClient.post("interview/studentHasInterviewWithInternOffer", requestBody,
                                ).then((res) => {
                                    interviewList.push({
                                        "offerId": offer.id,
                                        "candidateId": candidature.etudiant.id,
                                        "alreadyApplied": res.data
                                    })
                                })
                            })
                            candidature.interviewList = interviewList
                        })
                    }
                )
            }
        });
    }, []);


    function handleAccept(id: string) {
        apiClient.post(`intershipCandidates/acceptCandidats/${id}`).then(
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
        apiClient.post(`intershipCandidates/declineCandidats/${id}`).then(
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

    function studentHasInterviewWithInternOffer(studentId: number, internOfferId: number) {
        let requestBody = {"studentId": studentId, "internOfferId": internOfferId}
        let response = false

        apiClient.post("interview/studentHasInterviewWithInternOffer", requestBody).then(
            (res) => {
                response = res.data
                let newList: any[] = [...interOfferCandidates]

                newList.forEach((candidate) => {
                    if (candidate.etudiant.id == studentId) {
                        candidate.interviewList.forEach((interview: any) => {
                            if (interview["offerId"] == internOfferId) {
                                interview["alreadyApplied"] = response
                            }
                        })
                    }
                })
                setInterOfferCandidates(newList)
            }
        ).catch((e) => {
            response = false
        })
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

    return (
        <div className="flex justify-center">
            <div className="w-full md:w-5/6 px-12 bg-white dark:bg-dark rounded-xl shadow border border-gray dark:border-darkgray">
                <div className=" py-8 flex justify-between">
                    <h1 className="text-3xl font-bold text-black dark:text-white">Candidatures</h1>
                </div>
                <div className=" border-t border-neutral-200 dark:border-darkergray">
                    <dl className="divide-y divide-neutral-200 dark:divide-darkergray">
                        {interOfferCandidates.map((candidate) => {
                            return (
                                <div key={candidate.id} className="px-4 py-6 xs:grid xs:grid-cols-3 xs:gap-4 xs:px-0">
                                    <dt className=" font-medium space-y-8 leading-6 dark:text-white">
                                        <div className="space-y-3">
                                            <div>
                                                <p className="font-medium text-neutral-500 dark:text-neutral-300">
                                                    Name
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
                                                Contact Information
                                            </p>
                                            <div className=" font-medium text-black dark:text-white">
                                                <p>{candidate.etudiant.phone}</p>
                                                <p>{candidate.etudiant.email}</p>
                                            </div>
                                        </div>

                                    </dt>
                                    <dd className=" font-medium space-y-8 leading-6 dark:text-white">
                                        <div className="space-y-3">
                                            <p className=" font-medium text-neutral-500 dark:text-neutral-300">
                                                Interview
                                            </p>
                                            <div className="flex font-medium h-10 items-center text-black dark:text-white">
                                                No interview
                                            </div>
                                        </div>
                                        <div className="space-y-3">
                                            <p className=" font-medium text-neutral-500 dark:text-neutral-300">
                                                Status
                                            </p>
                                            <div className=" font-medium text-black dark:text-white">
                                                {candidate.state}
                                            </div>
                                        </div>
                                    </dd>
                                </div>
                            )
                        })}
                    </dl>
                </div>
            </div>
            <Outlet
                context={props}
            />
        </div>
    );
}

export function useUser() {
    return useOutletContext<User>();
}

export default CandidatureOffer;