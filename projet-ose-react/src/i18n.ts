import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';

i18n
    // detect user language
    // learn more: https://github.com/i18next/i18next-browser-languageDetector
    .use(LanguageDetector)
    // pass the i18n instance to react-i18next.
    .use(initReactI18next)
    // init i18next
    // for all options read: https://www.i18next.com/overview/configuration-options
    .init({
        debug: true,
        fallbackLng: 'en',
        interpolation: {
            escapeValue: false, // not needed for react as it escapes by default
        },
        resources: {
            en: {
                translation: {
                    formField: {
                        InternshipOfferForm : {
                            titleForm: 'Iternship offer form',
                            title: {
                                name : 'title',
                                text : 'Title of the job ',
                                placeholder : 'developper',
                                validation : {
                                    required : 'The title of the job is required',
                                    minLenght : 'The title of the job must contain at least 5 characters',
                                    maxLenght : 'The title of the job must contain at most 100 characters'
                                }
                            },
                            description: {
                                name : 'description',
                                text : 'Description of the job',
                                placeholder : 'a long description',
                                validation : {
                                    required : 'The description of the job is required',
                                    minLenght : 'The description of the job must contain at least 20 characters',
                                    maxLenght : 'The description of the job must contain at most 1000 characters'
                                }
                            },
                            location: {
                                name : 'location',
                                text : 'Location',
                                placeholder : 'Montréal',
                                validation : {
                                    required : 'The location is required',
                                    minLenght : 'The location must contain at least 5 characters',
                                    maxLenght : 'The location must contain at most 60 characters'
                                }
                            },
                            categories: {
                                name : 'categories',
                                text : 'Categories',
                                placeholder : 'Categories',
                                validation : {
                                    required : 'The categories are required',
                                }
                            },
                            salary: {
                                name : 'salary',
                                text : 'Salary ($/h)',
                                placeholder : '18',
                                validation : {
                                    required : 'The salary is required',
                                    numberRequired : 'The salary must be a valid number (you can include 2 digits after the comma)',
                                }
                            },
                            startDate: {
                                name : 'startDate',
                                text : 'Start date',
                                placeholder : 'Start date',
                                validation : {
                                    required : 'The start date is required',
                                    dateRequired : 'The start date must be a valid date and greater than the current date',
                                }
                            },
                            endDate: {
                                name : 'endDate',
                                text : 'End date',
                                placeholder : 'End date',
                                validation : {
                                    required : 'The end date is required',
                                    dateRequired : 'The end date must be a valid date and greater than the current date',
                                }
                            },
                            file: {
                                name : 'file',
                                text : 'Drag & drop your files here',
                                smallText: 'or',
                                span : 'browse',
                                placeholder : 'Upload files',
                                validation : {
                                    required : 'The files are required',
                                }
                            },
                            button: {
                                sumbit : 'Submit',
                                close : 'Close',
                            },
                        }
                    },
                }
            },
            fr: {
                translation: {
                    formField: {
                        InternshipOfferForm : {
                            titleForm: 'Formulaire d\'offre de stage',
                            title: {
                                name : 'title',
                                text : 'Titre de l\'offre d\'emploi :',
                                placeholder : 'Developpeur',
                                validation : {
                                    required : 'Le titre de l\'offre d\'emploi est requis',
                                    minLenght : 'Le titre de l\'offre d\'emploi doit contenir au moins 5 caractères',
                                    maxLenght : 'Le titre de l\'offre d\'emploi doit contenir au maximum 100 caractères'
                                }
                            },
                            description: {
                                name : 'description',
                                text : 'Description de l\'offre d\'emploi :',
                                placeholder : 'Une longue description',
                                validation : {
                                    required : 'La description de l\'offre d\'emploi est requise',
                                    minLenght : 'La description de l\'offre d\'emploi doit contenir au moins 20 caractères',
                                    maxLenght : 'La description de l\'offre d\'emploi doit contenir au maximum 1000 caractères'

                                }
                            },
                            location: {
                                name : 'location',
                                text : 'Lieu :',
                                placeholder : 'Montreal',
                                validation : {
                                    required : 'Le lieu est requis',
                                    minLenght : 'Le lieu doit contenir au moins 5 caractères',
                                    maxLenght : 'Le lieu doit contenir au maximum 60 caractères'
                                }
                            },
                            categories: {
                                name : 'categories',
                                text : 'Catégories :',
                                placeholder : 'Catégories',
                                validation : {
                                    required : 'Les catégories sont requises',
                                }
                            },
                            salary: {
                                name : 'salary',
                                text : 'Salaire ($/h):',
                                placeholder : '18',
                                validation : {
                                    required : 'Le salaire est requis',
                                    numberRequired : 'Le salaire doit être un nombre  valide (vous pouvez inclure 2 chiffres après la virgule)',
                                }
                            },
                            startDate: {
                                name : 'startDate',
                                text : 'Date de début :',
                                placeholder : 'Date de début',
                                validation : {
                                    required : 'La date de début est requise',
                                    dateRequired : 'La date de début doit être une date valide et supérieure à la date du jour',
                                }
                            },
                            endDate: {
                                name : 'endDate',
                                text : 'Date de fin :',
                                placeholder : 'Date de fin',
                                validation : {
                                    required : 'La date de fin est requise',
                                    dateRequired : 'La date de findébut doit être une date valide et supérieure à la date du jour',
                                }
                            },
                            file: {
                                name : 'file',
                                text : 'Glissez et déposez vos fichiers ici',
                                smallText: 'ou',
                                span: 'parcourir',
                                placeholder : 'Télécharger des fichiers',
                                validation : {
                                    required : 'Les fichiers sont requis',
                                }
                            },
                            button: {
                                sumbit : 'Soumettre',
                                close : 'Fermer',
                            },
                        }
                    },
                }
            }
        }
    });

export default i18n;