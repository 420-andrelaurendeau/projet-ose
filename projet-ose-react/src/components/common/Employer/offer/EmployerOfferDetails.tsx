import React, {useContext, useEffect, useRef, useState} from "react";
import {NavLink, Outlet, useNavigate, useOutletContext, useParams} from "react-router-dom";
import {getOfferById} from "../../../../api/InterOfferJobAPI";
import {ToastContext} from "../../../../hooks/context/ToastContext";
import {useTranslation} from "react-i18next";
import {InterOfferJob} from "../../../../model/IntershipOffer";
import {PaperClipIcon} from "@heroicons/react/20/solid";
import {Buffer} from "buffer";
import {base64ToArrayBuffer, blobToURL, downloadURI} from "../../preparedoc/utils/Utils";


const EmployerOfferDetails: React.FC<any> = () => {
    const navigate = useNavigate();
    const {id} = useParams();
    const toast = useContext(ToastContext);
    const [internshipOffer, setinternshipOffer] = useState<InterOfferJob>();
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.employerOffer." + i18n.language.slice(0, 2));
    const fetchedOfferRef = useRef(false);
    const [pdf, setPdf] = useState(null);


    const getProgrammeName = (): string => {
        let prog:string = "";
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
        if (internshipOffer?.file?.content){
            sizeInBytes = Buffer.from(internshipOffer?.file?.content).length;
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

    useEffect(() => {
        const loadOffer = async () => {
            try {

                fetchedOfferRef.current = true
                const response = await getOfferById(parseInt(id!));
                setinternshipOffer(response);
                console.log(response);
                const pdfBytes = base64ToArrayBuffer(response.file.content!)
                const blob = new Blob([new Uint8Array(pdfBytes)]);
                const URL:any = await blobToURL(blob);
                setPdf(URL);

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
            <div className="max-w-3xl mx-auto px-4 sm:px-6 lg:max-w-7xl lg:px-8 max-md:pt-24">
                <div className="py-6">
                    <div className="flex items-center justify-between">
                        <h2 className="text-lg font-bold">
                            <span
                                className={
                                    internshipOffer?.state == "PENDING" ?
                                        "px-6 py-2 inline-flex text-lg leading-5 justify-center  rounded-full bg-orange text-white dark:text-offwhite"
                                        : internshipOffer?.state === "DECLINED" ?
                                            "px-6 py-2 inline-flex text-lg leading-5  justify-center rounded-full bg-red text-white dark:text-offwhite"
                                            : "px-6 py-2 inline-flex text-lg leading-5  rounded-full justify-center bg-green text-white dark:text-offwhite"}
                            >
                                            {internshipOffer ? fields[internshipOffer.state].text : ""}
                                        </span>
                        </h2>
                        <div className="flex gap-2">
                            <button
                                type="button"
                                className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                onClick={() => navigate("/employer/home/offers")}
                            >
                                {fields.back.text}
                            </button>
                            <button
                                type="button"
                                className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-dark hover:bg-red dark:bg-white dark:text-black dark:hover:text-white dark:hover:bg-red focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                            >
                                {fields.edit.text}
                            </button>
                        </div>
                    </div>
                </div>
            </div>
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
                            <dt className="text-sm font-medium leading-6 dark:text-white">{fields.jobTitle.text}</dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">{internshipOffer?.title}</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">{fields.location.text}</dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">{internshipOffer?.location}</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">{fields.program.text}</dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">{getProgrammeName()}</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">{fields.salary.text}</dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">${internshipOffer?.salaryByHour}</dd>
                        </div>
                        <div className="px-4 py-6 sm:grid sm:grid-cols-3 sm:gap-4 sm:px-0">
                            <dt className="text-sm font-medium leading-6 dark:text-white">{fields.description.text}</dt>
                            <dd className="mt-1 text-sm leading-6 text-neutral-500 dark:text-neutral-300 sm:col-span-2 sm:mt-0">{internshipOffer?.description}</dd>
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
                                                <span className="truncate font-medium dark:text-white ">{internshipOffer?.file?.fileName}</span>
                                                <span className="flex-shrink-0 text-neutral-500 dark:text-neutral-300">{
                                                    getFileSize()
                                                }</span>
                                            </div>
                                        </div>
                                        <div className="ml-4 flex-shrink-0 space-x-5">
                                            <NavLink to={internshipOffer?.file?.fileName!} className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800">
                                                {fields.view.text}
                                            </NavLink>
                                            <button
                                                type="button"
                                                className="font-medium text-blue hover:text-cyan-900 dark:text-orange dark:hover:text-amber-800"
                                                onClick={() => {
                                                    downloadURI( pdf, internshipOffer?.file?.fileName!)
                                                }}
                                            >
                                                {fields.download.text}
                                            </button>
                                        </div>
                                    </li>
                                </ul>
                            </dd>
                        </div>
                    </dl>
                </div>
            </div>
            <Outlet
                context={context}
            />
        </div>
    )
}

interface Props {
    file: any;
    size: string;
}

export function useProps(){
    return useOutletContext<Props>();
}
export default EmployerOfferDetails;