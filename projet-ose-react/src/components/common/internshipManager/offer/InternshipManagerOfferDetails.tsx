import React, {useEffect, useRef, useState} from "react";
import {ReactComponent as Icon} from '../../../../assets/icons/back_icon.svg';
import {useNavigate} from "react-router-dom";
import {useTranslation} from "react-i18next";
import {InternshipOffer} from "../../../../model/IntershipOffer";
import {getOfferReviewById} from "../../../../api/InternshipManagerAPI";
import {getOfferReviewRequestById} from "../../../../api/InterOfferJobAPI";
import {comment} from "postcss";
import {useToast} from "../../../../hooks/state/useToast";
import ViewPDFModal from "../../Employer/offer/ViewPDFModal";
import {faFilePdf} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {ReviewFile} from "../../../../model/ReviewFile";

interface GSOfferDetailsProps {
    handleFormChange: any;
    internshipOffer: InternshipOffer;
    renderError: any;
}

const InternshipManagerOfferDetails: React.FC<GSOfferDetailsProps> = ({
                                                                          handleFormChange,
                                                                          internshipOffer,
                                                                          renderError
                                                                      }) => {

    const navigate = useNavigate();
    const {t} = useTranslation();
    const [theme, setTheme] = useState("light");
    const [isAlreadyReviewed, setIsAlreadyReviewed] = useState(false);
    const loadOfferReviewRef = useRef(false);
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.InternshipOfferList");
    const [formStateReview, setFormStateReview] = React.useState({
        comment: "", state: ""
    });
    const [isModalOpen, setIsModalOpen] = useState(false);
    const toast = useToast();


    useEffect(() => {
            const loadOfferReview = async () => {
                try {
                    loadOfferReviewRef.current = true;

                    const response = await getOfferReviewRequestById(internshipOffer.offerReviewRequestId!);
                    setFormStateReview(prevState => ({
                        ...prevState, comment: response.comment!,
                    }));
                    console.log(internshipOffer)
                    console.log(response)
                    return
                } catch (error) {
                    toast.error("Une erreur est survenue lors du chargement de l'offre")
                } finally {
                    loadOfferReviewRef.current = false;
                }
            }


            if (internshipOffer.state !== "PENDING" && !loadOfferReviewRef.current) {
                setIsAlreadyReviewed(true);
                setFormStateReview(prevState => ({
                    ...prevState, state: internshipOffer.state!,
                }));
                loadOfferReview()
            } else {
                setFormStateReview(prevState => ({
                    ...prevState, state: "PENDING",
                }));
            }
        }, []
    )


    return (
        <div className="">

            <div className="flex w-full justify-between sm:w-3/4 sm:mx-auto items-center pb-4">
                <div className="">
                    <button
                        type="button"
                        className="inline-flex items-center px-4 py-2 border hover:border-black border-transparent dark:border-white shadow-sm text-sm font-medium rounded-md bg-red hover:bg-rose-950 text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                        onClick={() => navigate("/internshipmanager/home/offers")}
                    >
                        {t("Shared.ReturnButton.text")}<Icon className="w-5 h-5 fill-current hover:font-bold"/>
                    </button>
                </div>
                <div role="cell" className="md:w-1/5 w-1/3 2 whitespace-nowrap truncate mt-3 items-center">
                                            <span
                                                className={
                                                    internshipOffer.state! == "PENDING" ?
                                                        "px-2  xxxs:text-xs sm:text-sm inline-flex leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                                                        : internshipOffer.state! === "DECLINED" ?
                                                            "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite "
                                                            : "px-2 xxxs:text-xs sm:text-sm inline-flex leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite "}
                                            >
                                                {fields.table[internshipOffer.state!]}
                                            </span>
                </div>
            </div>

            <h1 className='font-bold text-center text-dark text-2xl dark:text-offwhite'>{internshipOffer!.title}</h1>

            <div className="block sm:flex mt-5 sm:justify-between sm:items-start sm:w-3/4 sm:mx-auto">
                <div className='block items-center min-h-50'>
                    {/* Employeur field */}
                    <div className={"flex"}>
                        <p className={"p-1 text-lg dark:text-offwhite"}>
                            {internshipOffer!.employeurNom!}
                        </p>
                        <p className={"p-1 text-lg dark:text-offwhite"}>
                            {internshipOffer!.employeurPrenom!}
                        </p>
                    </div>

                    {/* Location field */}
                    <p className="p-1 dark:text-offwhite">
                        {internshipOffer!.location}
                    </p>

                    {/* Company field */}
                    <p className="p-1 text-lg dark:text-offwhite">
                        {internshipOffer!.employeurEntreprise}
                    </p>
                </div>
                <div className="block sm:flex flex-col sm:justify-end sm:items-end ">
                    {/* Start date field */}
                    <div className="flex">
                        <p className="p-1 dark:text-offwhite">
                            {new Date(internshipOffer!.startDate!).toLocaleDateString('fr-FR', {
                                day: '2-digit', month: '2-digit', year: 'numeric',
                            })}
                        </p>


                        {/* End date field */}
                        <p className="p-1 dark:text-offwhite">
                            {new Date(internshipOffer!.endDate!).toLocaleDateString('fr-FR', {
                                day: '2-digit', month: '2-digit', year: 'numeric',
                            })}
                        </p>
                    </div>


                    {/* Programme field */}
                    <p className="p-1 text-lg w-full dark:text-offwhite">
                        {internshipOffer!.programmeNom}
                    </p>

                    {/* Salary field */}
                    <div className="flex">
                        <p className="p-1 text-lg w-full dark:text-offwhite">
                            {internshipOffer!.salaryByHour}&nbsp;$/h
                        </p>
                    </div>
                </div>
            </div>

            {/* Description field */}
            <div className="mb-5 justify-center items-center sm:mx-auto sm:w-3/4">
                <p className="mt-1 p-2 w-full dark:text-offwhite ">
                    {internshipOffer!.description}
                </p>

            </div>

            {/* File field */}
            <div className="justify-center items-center sm:mx-auto sm:w-3/4">
                <button className="flex px-4 mb-5 justify-start border hover:border-black border-transparent dark:border-white shadow-sm  font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                        onClick={() => {
                            setIsModalOpen(true)
                            console.log(internshipOffer.file)
                        }
                        }>
                    <FontAwesomeIcon icon={faFilePdf} className="pt-3 flex text-blue dark:text-orange" size="lg"/>
                    <p className="mt-1 p-2 dark:text-white">{internshipOffer!.file!.fileName}</p>
                </button>
            </div>



            {internshipOffer.state! == "PENDING" && (
                <div className="sm:w-3/4 mx-auto">
                    <textarea
                        name='comment'
                        className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-offwhite dark:border-0"
                        id="commentary_placeholder"
                        onChange={(e) => handleFormChange(e)}
                        placeholder={t("formField.InternshipOfferModal.placeholder")}>

                    </textarea>
                    {renderError()}
                </div>
            )}

            {internshipOffer.state! !== "PENDING" && (
                <div  className="sm:w-3/4 sm:mx-auto">
                    <p className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-offwhite dark:border-0">
                        {formStateReview.comment!}
                    </p>
                </div>

            )}
            {
                internshipOffer.file.content != "" && isModalOpen &&
                <div className="">
                    <ViewPDFModal ismodal={true} setIsModalOpen={setIsModalOpen} file={internshipOffer.file}/>
                </div>
            }
        </div>
    )

}

export default InternshipManagerOfferDetails;


