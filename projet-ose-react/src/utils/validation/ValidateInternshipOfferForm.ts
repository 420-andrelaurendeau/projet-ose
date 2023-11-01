import {FileEntity} from "../../model/FileEntity";

export const validateTitle = (title: string, t: Function) => {
    title = title.trim();
    let errorMsg = "";

    if (title.length < 5) {
        errorMsg = t('formField.InternshipOfferForm.title.validation.minLenght');
    } else if (title.length > 20) {
        errorMsg = t('formField.InternshipOfferForm.title.validation.maxLenght');
    } else if (/[^a-zA-Z0-9\s\-.,'&éêèîâàçûüï]/.test(title)) {
        errorMsg = t('formField.InternshipOfferForm.title.validation.badCharactere');
    }

    return errorMsg;
};

export const validateLocation = (location: string, t: Function) => {
    location = location.trim();
    let errorMsg = "";

    if (location.length < 5) {
        errorMsg = t('formField.InternshipOfferForm.location.validation.minLenght');
    } else if (location.length > 20) {
        errorMsg = t('formField.InternshipOfferForm.location.validation.maxLenght');
    } else if (/[^a-zA-Z0-9\s\-.,'&éêèîâàçûüï]/.test(location)) {
        errorMsg = t('formField.InternshipOfferForm.location.validation.badCharactere');
    }

    return errorMsg;
}

export const validateDescription = (description: string, t: Function) => {
    description = description.trim();
    let errorMsg = "";

    if (description.length < 10) {
        errorMsg = t('formField.InternshipOfferForm.description.validation.minLenght');
    } else if (description.length > 1000) {
        errorMsg = t('formField.InternshipOfferForm.description.validation.maxLenght');
    } else if (/<script\b[^<]*(?:(?!<\/script>)<[^<]*)*<\/script>/.test(description.toLowerCase())) {
        errorMsg = t('formField.InternshipOfferForm.description.validation.scriptDetected');
    }

    return errorMsg;
}

export const validateSalary = (salary: number, t: Function) => {
    let errorMsg = "";

    const isNumber = !isNaN(salary) && isFinite(salary);

    if (!isNumber) {
        console.log(isNumber);
        errorMsg = t('formField.InternshipOfferForm.salary.validation.numberRequired');
    } else if (parseFloat(salary.toLocaleString()) <= 0) {
        errorMsg = t('formField.InternshipOfferForm.salary.validation.minLenght');
    } else if (parseFloat(salary.toLocaleString()) > 100) {
        errorMsg = t('formField.InternshipOfferForm.salary.validation.maxLenght');
    }

    return errorMsg;
}

export const validateStartDate = (startDate: Date, t: Function) => {
    const start = new Date(startDate);
    const today = new Date();
    let errorMsg = "";

    start.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);
    console.log(startDate < today);
    if (start < today) {
        errorMsg = t('formField.InternshipOfferForm.startDate.validation.dateToLow');

    } // Ajouter d'autres validations selon vos besoins, comme une date limite

    return errorMsg;
}

export const validateEndDate = (endDate: Date, startDate: Date, t: Function) => {
    const end = new Date(endDate);
    const start = new Date(startDate);
    const today = new Date();

    end.setHours(0, 0, 0, 0);
    start.setHours(0, 0, 0, 0);
    today.setHours(0, 0, 0, 0);

    let errorMsg = "";

    if (end < today) {
        errorMsg = t('formField.InternshipOfferForm.endDate.validation.dateToLow');
    } else if (end <= start) {
        errorMsg = t('formField.InternshipOfferForm.endDate.validation.dateToHigh');
    }

    return errorMsg;
}

export const validateFile = (file: FileEntity, t: Function) => {
    const allowedExtensions = [".pdf"];
    let errorMsg = "";

    const ext = "." + file.fileName.split(".").pop();

    // TODO Vérifier la taille du fichier
    /*

     */
    console.log(!allowedExtensions.includes(ext.toLowerCase()))
    if (!allowedExtensions.includes(ext.toLowerCase())) {
        errorMsg = t('formField.InternshipOfferForm.file.validation.BadTypeFile', {name: file.fileName});
    }

    return errorMsg;
}
