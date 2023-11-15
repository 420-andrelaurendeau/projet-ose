import React, {useContext, useEffect, useRef, useState} from 'react';
import {useTranslation} from 'react-i18next';
import {InternshipOffer} from "../../../../model/IntershipOffer";
import {Program} from "../../../../model/Program";
import {saveInterOfferJob, UpdateOffers} from "../../../../api/InterOfferJobAPI";
import {getProgrammes} from "../../../../api/ProgrammeAPI";
import {
    validateDescription,
    validateEndDate,
    validateFile,
    validateLocation,
    validateSalary,
    validateStartDate,
    validateTitle
} from "../../../../utils/validation/ValidateInternshipOfferForm";
import {NavLink, useLocation} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faUpload} from "@fortawesome/free-solid-svg-icons";
import {useProps} from "../../../../pages/employer/EmployeurHomePage";
import {ToastContext} from "../../../../hooks/context/ToastContext";


const initialFormState: InternshipOffer = {
    title: '',
    location: '',
    description: '',
    salaryByHour: 0,
    startDate: undefined,
    endDate: undefined,
    programmeId: 0,
    employeurId: 0,
    state: "PENDING",
    file: {
        fileName: '',
        content: '',
        isAccepted: "PENDING",
        uploaderId: 0,
    },
};

const InternshipOfferForm: React.FC<any> = () => {
    const {t} = useTranslation();
    const ref = useRef<HTMLInputElement>(null);
    const [errors, setErrors] = useState<{
        title?: string,
        location?: string,
        description?: string,
        programme?: string,
        salaryByHour?: string,
        startDate?: string,
        endDate?: string,
        file?: string
    }>({});
    const [formState, setFormState] = useState<InternshipOffer>(initialFormState);
    const {setIsModalOpen, setOffers, user} = useProps()
    const [programmes, setProgrammes] = useState<Program[]>([]);
    const toast = useContext(ToastContext);


    useEffect(() => {
        const loadProgrammes = async () => {
            try {
                const data = await getProgrammes();
                setProgrammes(data);
            } catch (error) {
                console.error('Erreur lors du chargement des programmes:', error);
            }
        };

        loadProgrammes();
    }, []);

    const handleFormChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
        const {name, value} = e.target;


        // @ts-ignore
        setFormState(prevState => ({
            ...prevState, [name]: value
        }));

    };

    const handleClick = () => {
        ref.current?.click();
    };

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const files = e.target.files;
        if (files && files[0]) {
            const file = files[0];
            const reader = new FileReader();

            reader.onloadend = () => {
                const base64String = reader.result?.toString().split(',')[1];

                setFormState((prevState: any) => ({
                    ...prevState, file: {
                        fileName: file.name, content: base64String || ""
                    }
                }));
            };

            reader.readAsDataURL(file);
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            console.log(formState);
            formState.file.isAccepted = "PENDING";
            const savedInterOfferJob = await saveInterOfferJob(formState, user.id);
            console.log('InterOfferJob sauvegardé avec succès:', savedInterOfferJob);
            setFormState(initialFormState);
            setOffers((prevOffers: InternshipOffer[]) => ([...prevOffers, savedInterOfferJob]));
            setIsModalOpen(false);
            toast.success("Offre ajoutée avec succès");
        } catch (error) {
            console.error('Erreur lors de la sauvegarde:', error);
        }
    };

    const renderError = (errorMsg: string | undefined) => (
        <p className="text-red text-xs mt-1 error-message" style={{minHeight: '30px'}}>
            {errorMsg || ""}
        </p>);

    const isFormValid = () => {
        const hasErrors = Object.values(errors).some(errorMsg => !!errorMsg);

        const areFieldsFilled = formState.title && formState.location && formState.description && formState.salaryByHour !== 0 && formState.startDate && formState.endDate && formState.programmeId !== 0 && formState.file;

        return !hasErrors && areFieldsFilled;
    }
    const handleValidation = (fieldName: string) => {
        let fieldError = "";

        switch (fieldName) {
            case "title":
                fieldError = validateTitle(formState.title!, t);
                break;
            case "location":
                fieldError = validateLocation(formState.location!, t);
                break;
            case "description":
                fieldError = validateDescription(formState.description!, t);
                break;
            case "salaryByHour":
                fieldError = validateSalary(formState.salaryByHour!, t);
                break;
            case "startDate":
                let fieldErrorEnd = validateEndDate(formState.endDate!, formState.startDate!, t) || "";
                setErrors(prevErrors => ({
                    ...prevErrors, ["endDate"]: fieldErrorEnd
                }));
                fieldError = validateStartDate(formState.startDate!, t);
                break;
            case "endDate":
                let fieldErrorStart = validateStartDate(formState.startDate!, t) || "";
                setErrors(prevErrors => ({
                    ...prevErrors, ["startDate"]: fieldErrorStart
                }));
                fieldError = validateEndDate(formState.endDate!, formState.startDate!, t);
                break;
            case "file":
                fieldError = validateFile(formState.file!, t);
                break;
        }

        setErrors(prevErrors => ({
            ...prevErrors, [fieldName]: fieldError
        }));
    };


    return (
        <div className="flex justify-center items-center min-h-screen max-md:pt-24">
            <div className="w-5/6">
                <div className="bg-white rounded-lg p-6 w-full  dark:bg-dark border border-gray dark:border-darkgray">

                    <form className="space-y-4 mx-auto w-full " onSubmit={handleSubmit}>

                        <h1 className='font-bold text-center text-dark text-xl dark:text-offwhite pb-6'>{t('formField.InternshipOfferForm.titleForm')}</h1>

                        <div
                            className='block sm:flex items-center space-x-0 sm:space-x-4 space-y-4 sm:space-y-0 min-h-50'>
                            {/* Title field */}
                            <div className='flex-auto sm:w-1/2 '>
                                <label className="block text-xs font-bold dark:text-offwhite"
                                       htmlFor="title_placeholder">{t('formField.InternshipOfferForm.title.text')}</label>
                                <input name='title'
                                       className={"mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs placeholder:h-10 dark:bg-softdark dark:text-orange dark:border-0"}
                                       onBlur={() => handleValidation("title")} type="text" id="title_placeholder"
                                       onChange={handleFormChange} value={formState.title}
                                       placeholder={t("formField.InternshipOfferForm.title.placeholder")}/>
                                {renderError(errors.title)}
                            </div>

                            {/* Location field */}
                            <div className='flex-auto sm:w-1/2'>
                                <label className="block text-xs font-bold dark:text-offwhite"
                                       htmlFor="location_placeholder">{t('formField.InternshipOfferForm.location.text')}</label>
                                <input name="location"
                                       className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"
                                       type="text" value={formState.location} id="location_placeholder"
                                       onChange={handleFormChange} onBlur={() => handleValidation("location")}
                                       placeholder={t("formField.InternshipOfferForm.location.placeholder")}/>
                                {renderError(errors.location)}
                            </div>

                        </div>

                        {/* Description field */}
                        <div>
                            <label className="block text-xs font-bold dark:text-offwhite"
                                   htmlFor="description_placeholder">{t('formField.InternshipOfferForm.description.text')}</label>
                            <textarea name='description'
                                      className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"
                                      id="description_placeholder" onBlur={() => handleValidation("description")}
                                      onChange={(e) => handleFormChange(e)} value={formState.description}
                                      placeholder={t("formField.InternshipOfferForm.description.placeholder")}></textarea>
                            {renderError(errors.description)}
                        </div>

                        <div className='block sm:flex space-x-0 sm:space-x-4 space-y-4 sm:space-y-0'>
                            {/* Categories field */}
                            <div className='sm:w-1/2'>
                                <label className="block text-xs font-bold dark:text-offwhite"
                                       htmlFor="categories_placeholder">{t('formField.InternshipOfferForm.program.text')}</label>
                                <select name="programmeId"
                                        className="mt-1 p-2 w-full border border-gray dark:text-white text-black rounded-md placeholder:text-xs dark:bg-softdark dark:border-0" /**onBlur={validateCategory}**/
                                        onChange={(e) => handleFormChange(e)} defaultValue={"default"}
                                        id="categories_placeholder">
                                    <option value="default"
                                            disabled>{t('formField.InternshipOfferForm.program.placeholder')}</option>
                                    {programmes.map((programme) => (<option key={programme.id} value={programme.id}>
                                        {programme.nom}
                                    </option>))}
                                </select>
                                {renderError(errors.programme)}
                            </div>

                            {/* Salary field */}
                            <div className='sm:w-1/2'>
                                <label className="block text-xs font-bold dark:text-offwhite"
                                       htmlFor="salary_placeholder">{t('formField.InternshipOfferForm.salary.text')}</label>
                                <input name='salaryByHour'
                                       className="mt-1 p-2 w-full border border-gray rounded-md placeholder:text-xs dark:bg-softdark dark:text-orange dark:border-0"
                                       type="text" id="salary_placeholder"
                                       onBlur={() => handleValidation("salaryByHour")} onChange={handleFormChange}
                                       value={formState.salaryByHour}
                                       placeholder={t("formField.InternshipOfferForm.salary.placeholder") + " $"}/>
                                {renderError(errors.salaryByHour)}
                            </div>

                        </div>

                        <div className='block sm:flex space-x-0 sm:space-x-4 space-y-4 sm:space-y-0'>
                            {/* Start date field */}
                            <div className='flex-auto sm:w-1/2'>
                                <label className="block text-xs font-bold dark:text-offwhite"
                                       htmlFor="start_date_placeholder">{t('formField.InternshipOfferForm.startDate.text')}</label>
                                <input name='startDate'
                                       className="mt-1 p-2 w-full border border-gray text-gray rounded-md dark:bg-softdark dark:border-0"
                                       type="date" id="start_date_placeholder"
                                       onBlur={() => handleValidation("startDate")} onChange={handleFormChange}/>
                                {renderError(errors.startDate)}
                            </div>

                            {/* End date field */}
                            <div className='flex-auto sm:w-1/2'>
                                <label className="block text-xs font-bold dark:text-offwhite"
                                       htmlFor="end_date_placeholder">{t('formField.InternshipOfferForm.endDate.text')}</label>
                                <input name='endDate'
                                       className="mt-1 p-2 w-full border border-gray text-gray rounded-md dark:bg-softdark dark:border-0"
                                       type="date" id="end_date_placeholder"
                                       onBlur={() => handleValidation("endDate")} onChange={handleFormChange}/>
                                {renderError(errors.endDate)}
                            </div>
                        </div>

                        {/* File field */}
                        <div className="flex flex-col items-center justify-center">
                            <div
                                className="bg-darkwhite h-48 w-4/5 dark:bg-softdark pb-5 cursor-pointer"
                                onClick={handleClick}>
                                <input
                                    name='file'
                                    type="file"
                                    className="hidden"
                                    ref={ref}
                                    onChange={(e) => {
                                        handleFileChange(e);
                                    }}
                                    onLoad={() => handleValidation("file")}
                                >

                                </input>
                                <div className="flex flex-col items-center justify-center py-10 text-center">
                                    <FontAwesomeIcon icon={faUpload} className="w-14 h-auto text-blue dark:text-orange" />
                                    <p className="mb-2 dark:text-gray">{t('formField.InternshipOfferForm.file.text')}</p>
                                    <p className="text-xs dark:text-gray">{t('formField.InternshipOfferForm.file.smallText')}
                                        {" "} <span
                                            className="text-blue-600 cursor-pointer dark:text-gray">{t('formField.InternshipOfferForm.file.span')}</span>
                                    </p>
                                </div>

                                {renderError(errors.file)}
                            </div>
                        </div>
                        {formState.file?.fileName && (<div className="mt-4 m-4">
                            <p className="mb-2 dark:text-gray">{formState.file.fileName}</p>
                        </div>)}

                        {/* Submit button */}
                        <div className="block space-y-4 sm:space-y-0 sm:flex sm:space-x-4 pt-5">
                            <button
                                className={`w-full flex-1 text-white font-bold p-2 rounded-md ${isFormValid() ? 'bg-blue dark:bg-orange' : 'bg-gray cursor-not-allowed'}`}
                                type="submit" disabled={!isFormValid()}>
                                Submit

                            </button>
                            <NavLink
                                to="/employeur/home/offre"
                                className="max-md:hidden w-full flex-1 bg-red text-center text-white font-bold p-2 rounded-md dark:bg-red"
                                state={user}
                                type="button"
                                onClick= {() =>
                                    setIsModalOpen(false)
                                }>
                                Close
                            </NavLink>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}


export default InternshipOfferForm;