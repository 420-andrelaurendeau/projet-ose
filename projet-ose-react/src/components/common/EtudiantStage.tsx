import img from "../../assets/images/logo_AL_COULEURS_FOND_BLANC-scaled-removebg-preview.png";
import imgDark from "../../assets/images/Cegep-Andre-Laurendeau.png";
import {useTranslation} from "react-i18next";
import Header from "../../Header";
import {Outlet, useLocation} from "react-router-dom";
import axios from "axios";
import {faBriefcase} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useProps} from "../../pages/EtudiantStagePage";
import {AppliedOffers} from "../../model/AppliedOffers";

function EtudiantStage() {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.EtudiantStage");
    // eslint-disable-next-line react-hooks/rules-of-hooks
    let anError = false;
    // eslint-disable-next-line react-hooks/rules-of-hooks
    const {appliedOffers, setAppliedOffers, offers, user} = useProps();

    const applyOffer = (offer: any, student: any) => {
        console.log(offer);
        console.log(student);
        axios.post(`http://localhost:8080/api/intershipCandidates/saveCandidats`, {
            etudiant: student,
            internOfferJob: offer,
            files: null
        }).then(
            res => {
                let appliedOffer: AppliedOffers = {
                    appliedOffer: res.data.internOfferJob,
                    appliedFiles: res.data.files
                };
                console.log(appliedOffer);

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
        <div className="flex flex-col mt-14">
            <div className={window.location.pathname != "/etudiant/home/offre" && window.location.pathname != "/etudiant/home/offre/" ? "max-md:hidden" : ""}>
                <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8 ">
                    <div className="overflow-x-hidden xxxs:rounded-lg">
                        <div className="flex items-center justify-center">
                            <FontAwesomeIcon icon={faBriefcase} className="text-blue dark:text-orange h-16" />
                        </div>
                        <h1 className="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-black dark:text-white">
                            {fields.titre.text}
                        </h1>
                        <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                            <table className="w-full divide-y divide-gray dark:divide-darkgray">
                                <thead className="bg-blue dark:bg-orange ">
                                <tr>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.titre.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.stage.location.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.stage.description.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.stage.salary.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.stage.startDate.text}
                                    </th>
                                    <th
                                        scope="col"
                                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                    >
                                        {fields.stage.endDate.text}
                                    </th>
                                    <th scope="col" className="relative px-6 py-3">
                                        <span className="sr-only">{fields.stage.apply.text}</span>
                                    </th>
                                </tr>
                                </thead>
                                {offers.map((offer: any) => (
                                    <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
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
                                                onClick={() => applyOffer(offer, user)}
                                                type="submit"
                                                disabled={
                                                    appliedOffers.find((appliedOffer: AppliedOffers) => appliedOffer.appliedOffer.id === offer.id) != null
                                                }
                                                className="w-full flex justify-center py-2 px-4 border border-gray dark:border-darkgray text-sm font-medium rounded-md text-white disabled:bg-gray bg-blue dark:disabled:bg-gray dark:bg-orange disabled:hover:bg-gray dark:disabled:hover:bg-gray hover:bg-cyan-300 dark:hover:bg-amber-400 focus:outline-none focus:shadow-outline-blue active:bg-blue transition duration-150 ease-in-out"
                                            >
                                                {fields.stage.apply.text}
                                            </button>
                                        </td>
                                    </tr>
                                    </tbody>
                                ))}
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

export default EtudiantStage;