import React, {useContext, useEffect, useRef, useState} from "react";
import {NavLink, Outlet, useLocation, useNavigate, useOutletContext, useParams} from "react-router-dom";
import {getOfferById} from "../../../../api/InterOfferJobAPI";
import {ToastContext} from "../../../../hooks/context/ToastContext";
import {useTranslation} from "react-i18next";
import {InternshipOffer} from "../../../../model/IntershipOffer";
import {PaperClipIcon} from "@heroicons/react/20/solid";
import {Buffer} from "buffer";
import {base64ToArrayBuffer, blobToURL, downloadURI} from "../../preparedoc/utils/Utils";
import ViewPDFModal from "../../Employer/offer/ViewPDFModal";
import {AppliedOffers} from "../../../../model/AppliedOffers";
import {saveStudentInternshipOffer} from "../../../../api/intershipCandidatesAPI";
import {ReactComponent as Icon} from '../../../../assets/icons/back_icon.svg';


const StudentOfferDetails: React.FC<any> = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const {id} = useParams();
    const toast = useContext(ToastContext);
    const [internshipOffer, setinternshipOffer] = useState<any>();
    const {i18n, t} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.studentOffer");
    const fetchedOfferRef = useRef(false);
    const [pdf, setPdf] = useState(null);
    const [isModalOpen, setIsModalOpen] = useState(false);


    const getProgrammeName = (): string => {
        let prog: string = "";
        if (!fields.programs) return prog;
        Object.keys(fields.programs).forEach((key) => {
            if (fields.programs[key].id === internshipOffer?.programmeId) {
                prog = fields.programs[key].text;
            }
        });
        return prog;
    };

    const getFileSize = (): string => {
        let sizeInBytes = 0
        if (internshipOffer?.file?.content) {
            sizeInBytes = Buffer.from(internshipOffer?.file?.content).length;
            if (sizeInBytes < 1000) {
                return sizeInBytes + " B";
            } else if (sizeInBytes < 1000000) {
                return sizeInBytes / 1000 + " KB";
            } else if (sizeInBytes < 1000000000) {
                return Math.round(sizeInBytes / 10000) / 100 + " MB";
            }
        }
        return sizeInBytes + " B";
    }

    useEffect(() => {
        const loadOffer = async () => {
            try {
                fetchedOfferRef.current = true
                const response = await getOfferById(parseInt(id!));
                setinternshipOffer(response);

                const pdfBytes = base64ToArrayBuffer(response.file.content!)
                if (pdfBytes) {
                    const blob = new Blob([new Uint8Array(pdfBytes)]);
                    const URL: any = await blobToURL(blob);
                    setPdf(URL);
                } else setPdf(null)

            } catch (error) {
                toast.error(fields.errorFetchOffer);
            } finally {
                fetchedOfferRef.current = false;
            }
        };
        if (!fetchedOfferRef.current) loadOffer();

    }, []);

    const context = {
        file: internshipOffer?.file,
        size: getFileSize(),
    }

    return (
        <div className="pb-12">
            <div className="mx-auto max-md:pt-24">
                <div className="py-6">
                    <div className="flex items-center justify-between">
                        <div className="flex ">
                            <button
                                aria-label={"back-button"}
                                type="button"
                                className="inline-flex px-4 py-2 border border-transparent hover:border-black dark:border-white shadow-sm text-sm font-medium rounded-md text-white bg-red focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                onClick={() => navigate("/student/home/offers")}
                            >
                                {t("Shared.ReturnButton.text")} <Icon className="w-5 h-5 fill-current hover:font-bold"/>
                            </button>
                        </div>
                        <h2 className="text-lg font-bold dark:text-white">
                            {internshipOffer?.title}
                        </h2>
                    </div>
                </div>
            </div>
            <div className="bg-white dark:bg-dark rounded-xl py-5 px-6 shadow">
                <div className="px-4 sm:px-0">
                    <h3 className="text-base dark:text-white font-semibold leading-7 text-gray-900">
                        {t("formField.studentOffer.title.text")}
                    </h3>
                    <p className="mt-1 max-w-2xl text-sm leading-6 text-neutral-500 dark:text-neutral-300">
                        {t("formField.studentOffer.subtitle.text")}
                    </p>
                </div>
                <div className="mt-6 border-t border-neutral-200 dark:border-darkgray">
                    <dl className="divide-y divide-neutral-200 dark:divide-darkgray">
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.studentOffer.jobTitle.text")}
                            </dt>
                            <dd aria-label={"offer-title"}
                                className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {internshipOffer?.title}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.studentOffer.location.text")}
                            </dt>
                            <dd aria-label={"offer-location"}
                                className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {internshipOffer?.location}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.studentOffer.program.text")}
                            </dt>
                            <dd aria-label={"program-name"}
                                className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {getProgrammeName()}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.studentOffer.salary.text")}
                            </dt>
                            <dd aria-label={"salary"}
                                className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                ${internshipOffer?.salaryByHour}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.studentOffer.description.text")}
                            </dt>
                            <dd aria-label={"description"}
                                className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">
                                {internshipOffer?.description}
                            </dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">
                                {t("formField.studentOffer.attachments.text")}
                            </dt>
                            <dd className="mt-2 text-sm text-gray-900 sm:col-span-2 sm:mt-0">
                                <ul role="list"
                                    className="divide-y divide-neutral-100 dark:divide-darkergray rounded-md border border-neutral-200 dark:border-darkgray">
                                    <li className="flex items-center justify-between py-4 pl-4 pr-5 text-sm leading-6">
                                        <div className="flex w-0 flex-1 items-center">
                                            <div className="h-5 w-5 flex-shrink-0 text-gray-400" aria-hidden="true"/>
                                            <div className="ml-4 flex min-w-0 flex-1 gap-2">
                                                <span
                                                    aria-label={"file-name"}
                                                    className="truncate font-medium dark:text-white ">
                                                    {internshipOffer?.file?.fileName}
                                                </span>
                                                <span className="flex-shrink-0 text-neutral-500 dark:text-neutral-300">{
                                                    getFileSize()
                                                }</span>
                                            </div>
                                        </div>
                                        <div className="ml-4 flex-shrink-0 space-x-5">
                                            <button
                                                className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800"
                                                onClick={() => {
                                                    setIsModalOpen(true)
                                                }}
                                            >
                                                {t("formField.studentOffer.view.text")}
                                            </button>
                                            <button
                                                type="button"
                                                className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800"
                                                onClick={() => {
                                                    downloadURI(pdf, internshipOffer?.file?.fileName!)
                                                }}
                                            >
                                                {t("formField.studentOffer.download.text")}
                                            </button>
                                        </div>
                                    </li>
                                </ul>
                            </dd>
                        </div>
                    </dl>
                </div>
            </div>
            {
                internshipOffer && isModalOpen &&
                <ViewPDFModal ismodal={true} file={internshipOffer.file} setIsModalOpen={setIsModalOpen}/>
            }
        </div>
    )
}

interface Props {
    file: any;
    size: string;
}

export function useProps() {
    return useOutletContext<Props>();
}

export default StudentOfferDetails;