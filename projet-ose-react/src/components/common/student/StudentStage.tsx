import img from "../../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import imgDark from "../../../assets/images/Cegep-Andre-Laurendeau.png";
import {useTranslation} from "react-i18next";
import Header from "../shared/header/Header";
import {Outlet, useLocation} from "react-router-dom";
import axios from "axios";
import {faBriefcase} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useProps} from "../../../pages/student/StudentInternshipPage";
import {AppliedOffers} from "../../../model/AppliedOffers";
import {useEffect, useState} from "react";
import {useAuth} from "../../../authentication/AuthContext";
import {getUser} from "../../../api/UtilisateurAPI";
import {allStudentInternshipOffers, getStudentAppliedOffers} from "../../../api/InterOfferJobAPI";
import {saveStudentInternshipOffer} from "../../../api/intershipCandidatesAPI";

function StudentStage() {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const { t } = useTranslation()
    // eslint-disable-next-line react-hooks/rules-of-hooks
    let anError = false;
    // eslint-disable-next-line react-hooks/rules-of-hooks
    // const {appliedOffers, setAppliedOffers, offers, user} = useProps();
    const [appliedOffers, setAppliedOffers] = useState<any[]>([])
    const [offers, setOffers] = useState<any[]>([])
    const [user, setUser] = useState<any>(null)
    const auth = useAuth();
    const token = localStorage.getItem('token');

    useEffect(() => {
        getUser(auth.userEmail!).then((res) => {
                setUser(res);
                getStudentAppliedOffers(res.id).then((res) => {
                    setAppliedOffers(res);
                })
            }
        ).finally(() => {
            allStudentInternshipOffers().then((res) => {
                setOffers(res);
            })

        })
    }, []);


    const applyOffer = (offer: any, student: any) => {
        saveStudentInternshipOffer(offer, student).then(
            res => {
                let appliedOffer: AppliedOffers = {
                    appliedOffer: res.internOfferJob,
                    appliedFiles: res.files
                };
                setAppliedOffers([...appliedOffers, appliedOffer]);

            }
        ).catch(
            err => {
                console.log(err);
                anError = true;
            }
        )
    }

    return (
        <div>
            <div className="flex min-h-full flex-1 flex-col justify-center px-6 lg:px-8">
                <div className="sm:mx-auto sm:w-full sm:max-w-sm mt-20">
                    <div className="flex items-center justify-center">
                        <FontAwesomeIcon icon={faBriefcase} className="text-blue dark:text-orange h-16"/>
                    </div>
                    <h1 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black dark:text-white">
                        {t("formField.EtudiantStage.titre.text")} {/* Use t() function */}
                    </h1>
                    <div className={"flex flex-col"}>
                        {offers.map((offer: any) => {
                            return (
                                <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md" key={offer.id}
                                     aria-label={"stage"}>
                                    <div
                                        className="bg-white dark:bg-dark py-8 px-4 shadow border border-gray dark:border-darkgray sm:rounded-lg sm:px-10"
                                    >
                                        <div>
                                            <h2 className="mt-6 text-center text-3xl font-extrabold leading-9 dark:text-white">
                                                {offer.title}
                                            </h2>
                                        </div>

                                        <div className="mt-6">
                                            <div className="w-full">
                                                <div className="flex justify-between">
                                                    <div className="text-sm leading-5 dark:text-white">
                                                        <p>{t("formField.EtudiantStage.stage.description.text")}</p> {/* Use t() function */}
                                                        <p>{t("formField.EtudiantStage.stage.location.text")}</p> {/* Use t() function */}
                                                        <p>{t("formField.EtudiantStage.stage.salary.text")}</p> {/* Use t() function */}
                                                        <p>{t("formField.EtudiantStage.stage.startDate.text")}</p> {/* Use t() function */}
                                                        <p>{t("formField.EtudiantStage.stage.endDate.text")}</p> {/* Use t() function */}
                                                    </div>
                                                    <div className="text-sm leading-5 dark:text-white">
                                                        <p>{offer.description}</p>
                                                        <p>{offer.location}</p>
                                                        <p>{offer.salaryByHour}</p>
                                                        <p>{offer.startDate}</p>
                                                        <p>{offer.endDate}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div className="mt-6">
                                            <div className="w-full">
                                                <div className="flex justify-between">
                                                    <div className="text-sm leading-5">
                                                        <button
                                                            aria-label={"apply"}
                                                            onClick={() => applyOffer(offer, user)}
                                                            type="submit"
                                                            disabled={
                                                                appliedOffers.find((appliedOffer: AppliedOffers) => appliedOffer.appliedOffer.id === offer.id) != null
                                                            }
                                                            className="w-full flex justify-center py-2 px-4 border border-gray dark:border-darkgray text-sm font-medium rounded-md text-white disabled:bg-gray bg-blue dark:disabled:bg-gray dark:bg-orange disabled:hover:bg-gray dark:disabled:hover:bg-gray hover:bg-cyan-300 dark:hover:bg-amber-400 focus:outline-none focus:shadow-outline-blue active:bg-blue transition duration-150 ease-in-out"
                                                        >
                                                            {t("formField.EtudiantStage.stage.apply.text")} {/* Use t() function */}
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            )
                        })}
                    </div>
                </div>
            </div>
            <Outlet context={useProps()}/>
        </div>
    );
}

export default StudentStage;