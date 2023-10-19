import React, {ReactElement, useEffect, useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBriefcase, faCheck, faCircleUser, faX} from "@fortawesome/free-solid-svg-icons";
import {NavLink, Outlet, useOutletContext} from "react-router-dom";
import {getInterOfferCandidates} from "../../api/intershipCandidatesAPI";
import axios from "axios";
import {useProps} from "../../pages/EmployeurHomePage";

interface User {
    user: any
}

const CandidatureOffer: React.FC<any> = () => {
    const [open, setOpen] = React.useState({
        id: -1,
        open: false
    });
    const [interOfferCandidates, setInterOfferCandidates] = useState<any[]>([]);
    const {offers, user} = useProps();
    const props = {
        user: user,
    }
    const toggle = (id: number) => {
        const newState = {
            id: id,
            open: !open.open
        };
        if (open.id !== id) {
            newState.open = true;
        }
        setOpen(newState);
    }
    useEffect(() => {
        let listIds: number[] = [];
        offers.map((offer: any) => {
            offer.internshipCandidates.map((interOfferCandidate: any) => {
                listIds.push(interOfferCandidate);
            })
        })

        if (listIds.length === 0) return;
        getInterOfferCandidates(listIds.join(",")).then((response) => {
            response.forEach((student: any) => {
                let interviewList: any[] = []
                offers.forEach((offer: any) => {

                    let requestBody = {"studentId": student.etudiant.id, "internOfferId": offer.id}
                    axios.post("http://localhost:8080/api/interview/studentHasInterviewWithInternOffer", requestBody).then((res) => {
                        interviewList.push({
                            "offerId": offer.id,
                            "candidateId": student.etudiant.id,
                            "alreadyApplied": res.data
                        })
                    })
                })
                student.interviewList = interviewList
            })
            setInterOfferCandidates(response);
        })
    }, [offers]);


    function handleAccept(id: string) {
        axios.post(`http://localhost:8080/api/intershipCandidates/acceptCandidats/${id}`).then(
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
        axios.post(`http://localhost:8080/api/intershipCandidates/declineCandidats/${id}`).then(
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

        axios.post("http://localhost:8080/api/interview/studentHasInterviewWithInternOffer", requestBody).then(
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
            <div
                className="md:fixed md:z-50 md:top-0 md:left-0 w-full md:h-full md:bg-black md:bg-opacity-50 md:items-start md:p-3 max-md:w-5/6 md:overflow-auto">
                <NavLink
                    to="/employeur/home/offre"
                    className="md:fixed max-md:hidden h-full w-full"
                    state={user}
                />
                {
                    offers.map((offer: any) => (
                        <div className="md:flex md:justify-center" key={offer.id}>
                            <div key={offer.id}
                                 className="md:relative md:w-3/4 lg:w-1/2 max-sm:w-full mt-14 items-center rounded-2xl px-6 bg-white dark:bg-dark py-6 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                                <FontAwesomeIcon icon={faBriefcase} className="h-12 dark:text-white "/>
                                <br/>
                                <input
                                    id={offer.id}
                                    checked={open.open && open.id === offer.id}
                                    onChange={() => console.log("change")}
                                    type="checkbox"
                                    className="peer sr-only"
                                />
                                <br/>
                                <p className="text-black dark:text-white tracking-wide font-bold text-lg ">{offer.title}</p>
                                <br/>
                                <p className="text-darkgray dark:text-gray tracking-wide text-sm">{offer.description}</p>
                                <br/>
                                <div className="flex -space-x-1 overflow-hidden ">
                                    {
                                        Array.from(Array(offer.internshipCandidates.length), (e, i) => {
                                            if (i <= 5) {
                                                return (
                                                    <FontAwesomeIcon key={i} icon={faCircleUser}
                                                                     className="text-blue dark:text-orange inline-block h-6 w-6 rounded-full ring-2 ring-white dark:ring-dark"
                                                                     size="xl"/>
                                                )
                                            } else if (5 > 1 && i <= 6) {
                                                return (
                                                    <div key={i}
                                                         className="flex items-center bg-blue dark:bg-orange justify-center text-white h-6 w-6 rounded-full ring-2 ring-white dark:ring-dark text-xs">
                                                        +{offer.internshipCandidates.length - 1}
                                                    </div>
                                                )
                                            }
                                        })
                                    }
                                </div>
                                <br/>
                                <label
                                    htmlFor={offer.id}
                                    className="w-full flex justify-center items-center bg-blue-100 hover:bg-blue-500 transition-colors duration-1000 ease-in-out"
                                >
                                    <button
                                        className="w-4/5 py-4 text-white bg-blue dark:bg-orange font-bold rounded-xl cursor-pointer"
                                        onClick={() => {
                                            toggle(offer.id)
                                        }}>
                                        {open.open && open.id === offer.id ? "Hide candidature" : "Show candidature"}
                                    </button>

                                </label>
                                <br/>
                                <div
                                    className=" h-0 rounded-lg overflow-hidden bg-slate-300 dark:bg-amber-400 peer-checked:h-52  peer-checked:overflow-scroll transition-[height] duration-1000 ease-in-out "
                                >
                                    {
                                        interOfferCandidates.map((interOfferCandidate: any) => {
                                            if (interOfferCandidate.internOfferJob.id === offer.id) {
                                                return (
                                                    <div key={interOfferCandidate.id}
                                                         className="flex justify-center pt-2">
                                                        <div
                                                            className="md:w-3/4 w-5/6 flex justify-between border-b border-blue ">
                                                            <div className="flex px-2 items-center space-x-2">
                                                                <FontAwesomeIcon icon={faCircleUser}
                                                                                 className="text-blue dark:text-orange"
                                                                                 size="xl"/>
                                                                <p className="text-black dark:text-white tracking-wide font-bold text-lg ">{interOfferCandidate.etudiant.prenom} {" "} {interOfferCandidate.etudiant.nom}</p>

                                                            </div>
                                                            <div
                                                                className={"ml-auto my-auto h-fit w-1/6 flex flex-row items-center "}>
                                                                <div

                                                                    className={"container flex flex-row items-center justify-around " + (interOfferCandidate.state == "ACCEPTED" ? "hidden" : "")}>
                                                                    <div>
                                                                        <FontAwesomeIcon icon={faCheck}
                                                                                         style={{color: "#00ff4c",}}
                                                                                         onClick={() => {
                                                                                             handleAccept(interOfferCandidate.id)
                                                                                         }}
                                                                                         className={"cursor-pointer"}/>
                                                                    </div>
                                                                    <div>
                                                                        <FontAwesomeIcon icon={faX}
                                                                                         style={{color: "#cc0000",}}
                                                                                         onClick={() => {
                                                                                             handleRefuse(interOfferCandidate.id)
                                                                                         }}
                                                                                         className={"cursor-pointer"}/>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div className="flex px-2 py-2">
                                                                <button
                                                                    hidden={interOfferCandidate.state == "ACCEPTED"}
                                                                    className="px-2 py-2 rounded-lg bg-blue dark:bg-orange font-bold text-white">
                                                                    Review
                                                                </button>
                                                                <button
                                                                    disabled={hasStudentApplied(interOfferCandidate, offer.id)}
                                                                    className={`px-2 py-2 rounded-lg bg-blue dark:bg-orange disabled:bg-gray dark:disabled:bg-gray font-bold text-white disabled:cursor-auto cursor-pointer`}
                                                                    hidden={interOfferCandidate.state != "ACCEPTED"}
                                                                    onClick={() => {
                                                                        let studentId: number = interOfferCandidate.etudiant.id
                                                                        let offerId: number = offer.id
                                                                        studentHasInterviewWithInternOffer(studentId, offerId)
                                                                    }}>
                                                                    {hasStudentApplied(interOfferCandidate, offer.id) ?
                                                                        <p>ENTREVUE</p> :
                                                                        <NavLink to={"InterviewForm"} state={{
                                                                            "offerId": offer.id,
                                                                            "studentId": interOfferCandidate.etudiant.id
                                                                        }} onClick={() => toggle(offer.id)}>
                                                                            ENTREVUE
                                                                        </NavLink>}
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                )
                                            }
                                        })
                                    }
                                </div>

                            </div>
                        </div>

                    ))
                }
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