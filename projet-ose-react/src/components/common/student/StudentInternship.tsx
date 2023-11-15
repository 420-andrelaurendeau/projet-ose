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
import {fetchDefaultCvByStudentId} from "../../../api/StudentApi";
import {FileEntity} from "../../../model/FileEntity";
import {useToast} from "../../../hooks/state/useToast";
import toast from "../shared/toast/Toast";

function StudentInternship() {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.EtudiantStage");
    let anError = false;
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
                console.log('cv par defaut', res)
                if (res.isAccepted != "ACCEPTED") {
                    toast.error("Votre CV par défaut n'est pas encore accepté")
                }
                else {
                    toast.success("Vous avez un CV par défaut, vous pouvez postuler")
                }
            }).catch((error) => {
                console.log("Error fetching user data:", error)
                toast.error("Vous n'avez pas de CV par défaut, veuillez en ajouter un")
            })
            }
        ).finally(() => {
            offresEtudiant().then((res) => {
                setOffers(res);
            })

        })



    }, []);


    const applyOffer = (offer: any, student: any, cv: any) => {
        console.log(offer);
        console.log(student);
        console.log(cv);

        saveStudentInternshipOffer(offer, student, cv).then(
            res => {
                let appliedOffer: AppliedOffers = {
                    appliedOffer: res.internOfferJob,
                    appliedFiles: res.files
                };
                console.log(appliedOffer);

                setAppliedOffers([...appliedOffers, appliedOffer]);

                console.log(appliedOffers)
            }
        ).catch(
            err => {
                console.log(err);
                anError = true;
            }
        )
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
                            {fields.EtudiantStage.titre.text}
                        </h1>
                        <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                            <table className="w-full divide-y divide-gray dark:divide-darkgray">
                                <thead className="bg-blue dark:bg-orange ">
                                <tr>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.EtudiantStage.titre.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.EtudiantStage.stage.location.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.EtudiantStage.stage.description.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.EtudiantStage.stage.salary.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.EtudiantStage.stage.startDate.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.EtudiantStage.stage.endDate.text}
                                    </th>
                                    <th scope="col" className="relative px-6 py-3">
                                        <span className="sr-only">{fields.EtudiantStage.stage.apply.text}</span>
                                    </th>
                                </tr>
                                </thead>
                                <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
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
                                                    appliedOffers.find((appliedOffer: AppliedOffers) => appliedOffer.appliedOffer.id === offer.id) != null || cv == null || cv.isAccepted != "ACCEPTED"
                                                }
                                                className="w-full flex justify-center py-2 px-4 border border-gray dark:border-darkgray text-sm font-medium rounded-md text-white disabled:bg-gray bg-blue dark:disabled:bg-gray dark:bg-orange disabled:hover:bg-gray dark:disabled:hover:bg-gray hover:bg-cyan-300 dark:hover:bg-amber-400 focus:outline-none focus:shadow-outline-blue active:bg-blue transition duration-150 ease-in-out"
                                            >
                                                {fields.EtudiantStage.stage.apply.text}
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