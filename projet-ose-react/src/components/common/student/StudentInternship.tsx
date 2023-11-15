import {useTranslation} from "react-i18next";
import {Outlet} from "react-router-dom";
import {faBriefcase} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useProps} from "../../../pages/student/StudentInternshipPage";
import {AppliedOffers} from "../../../model/AppliedOffers";
import {useEffect, useRef, useState} from "react";
import {useAuth} from "../../../authentication/AuthContext";
import {getUser} from "../../../api/UtilisateurAPI";
import {offresEtudiant, getStudentAppliedOffers} from "../../../api/InterOfferJobAPI";
import {saveStudentInternshipOffer} from "../../../api/intershipCandidatesAPI";
import {FileEntity} from "../../../model/FileEntity";
import {useToast} from "../../../hooks/state/useToast";
import {fetchDefaultCvByStudentId} from "../../../api/StudentApi";

interface Props {
    user: any;
    appliedOffers: AppliedOffers[];
}

function StudentInternship() {
    const {t} = useTranslation();
    const [appliedOffers, setAppliedOffers] = useState<any[]>([])
    const [offers, setOffers] = useState<any[]>([])
    const [cv,setCv] = useState<FileEntity>()
    const [user, setUser] = useState<any>(null)
    const auth = useAuth();
    //const token = localStorage.getItem('token');
    const isloading = useRef(false);
    const toast = useToast();

    useEffect(() => {
        if (!isloading.current)
        getUser(auth.userEmail!).then((res) => {
            console.log(res);
                setUser(res);
            getStudentAppliedOffers(res.id).then((res) => {
                setAppliedOffers(res);
            })
            fetchDefaultCvByStudentId(res.id).then((res) => {
                setCv(res)
                console.log(res)
            }).catch((error) => {
                console.log("Error fetching user data:", error)
            })
            }
        ).finally(async () => {
            await offresEtudiant(setOffers, setAppliedOffers, {});
        })
    }, []);


    const applyOffer = (offer: any, student: any, cv: any) => {
        console.log(offer);
        console.log(student);
        console.log(cv);
        if (cv == null) {
            toast.error(t("formField.EtudiantStage.toast.ErrorNoCv"))
        }
        else {
            saveStudentInternshipOffer(offer, student, cv).then(
                res => {
                    let appliedOffer: AppliedOffers = {
                        appliedOffer: res.internOfferJob,
                        appliedFiles: res.files
                    };
                    setAppliedOffers([...appliedOffers, appliedOffer]);
                    toast.success(t("formField.EtudiantStage.toast.SuccessOfferApplication") + " " + offer.title)
                }
            ).catch(
                err => {
                    console.log(err);
                    toast.error(t("formField.EtudiantStage.toast.ErrorOfferApplication"))
                }
            )
        }
    }

    return (
        <div className="flex flex-col mt-14">
            <div className={window.location.pathname != "/etudiant/home/offre" && window.location.pathname != "/etudiant/home/offre/" ? "max-md:hidden" : ""}>
                <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8 ">
                    <div className="overflow-x-hidden xxxs:rounded-lg">
                        <div className="flex items-center justify-center">
                            <FontAwesomeIcon icon={faBriefcase} className="text-blue dark:text-orange h-16" />
                        </div>
                        <h1 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black dark:text-white">
                            {t("formField.EtudiantStage.titre.text")}
                        </h1>
                        <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                            <table className="w-full divide-y divide-gray dark:divide-darkgray">
                                <thead className="bg-blue dark:bg-orange ">
                                <tr>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t("formField.EtudiantStage.titre.text")}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t("formField.EtudiantStage.stage.location.text")}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t("formField.EtudiantStage.stage.description.text")}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t("formField.EtudiantStage.stage.salary.text")}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t("formField.EtudiantStage.stage.startDate.text")}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {t("formField.EtudiantStage.stage.endDate.text")}
                                    </th>
                                    <th scope="col" className="relative px-6 py-3">
                                        <span className="sr-only">{t("formField.EtudiantStage.stage.apply.text")}</span>
                                    </th>
                                </tr>
                                </thead>
                                <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                                {offers.length === 0 && (
                                    <tr>
                                        <td colSpan={7}>
                                            <div className="w-full text-center bg-red text-white">
                                                <div className="">{t("formField.EtudiantStage.empty.text")}</div>
                                            </div>
                                        </td>
                                    </tr>
                                )}
                                {offers.map((offer: any) => (
                                    <tr key={offer.id}>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="flex items-center">
                                                <div className="ml-4">
                                                    <div className="text-sm font-medium dark:text-offwhite">{offer.title}</div>
                                                </div>
                                            </div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.location}</div>
                                        </td>
                                        <td className="px-6 py-4 break-all">
                                            <div className="text-sm dark:text-offwhite">{offer.description}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.salaryByHour}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.startDate}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap">
                                            <div className="text-sm dark:text-offwhite">{offer.endDate}</div>
                                        </td>
                                        <td className="px-6 py-4 whitespace-nowrap text-sm dark:text-offwhite">
                                            <button
                                                onClick={() => applyOffer(offer, user, cv)}
                                                type="submit"
                                                disabled={
                                                    appliedOffers.find((appliedOffer: AppliedOffers) => appliedOffer.appliedOffer.id === offer.id) != null
                                                }
                                                className="w-full flex justify-center py-2 px-4 border border-gray dark:border-darkgray text-sm font-medium rounded-md text-white disabled:bg-gray bg-blue dark:disabled:bg-gray dark:bg-orange disabled:hover:bg-gray dark:disabled:hover:bg-gray hover:bg-cyan-300 dark:hover:bg-amber-400 focus:outline-none focus:shadow-outline-blue active:bg-blue transition duration-150 ease-in-out"
                                            >
                                                {t("formField.EtudiantStage.stage.apply.text")}
                                            </button>
                                        </td>
                                    </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <Outlet
                context={useProps()}
            />
        </div>

    )
}

export default StudentInternship;