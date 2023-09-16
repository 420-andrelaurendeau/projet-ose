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
                    title: {
                        name : 'title',
                        text : 'Title of the job :',
                        placeholder : 'Title of the job',
                        validation : {
                            required : 'The title of the job is required',
                            minLenght : 'The title of the job must contain at least 5 characters',
                            maxLenght : 'The title of the job must contain at most 100 characters'
                        }
                    },
                    description: {
                        name : 'description',
                        text : 'Description of the job :',
                        placeholder : 'Description of the job',
                        validation : {
                            required : 'The description of the job is required',
                            minLenght : 'The description of the job must contain at least 20 characters',
                            maxLenght : 'The description of the job must contain at most 1000 characters'
                        }
                    },
                    location: {
                        name : 'location',
                        text : 'Location :',
                        placeholder : 'Location',
                        validation : {
                            required : 'The location is required',
                            minLenght : 'The location must contain at least 5 characters',
                            maxLenght : 'The location must contain at most 60 characters'
                        }
                    },
                    categories: {
                        name : 'categories',
                        text : 'Categories :',
                        placeholder : 'Categories',
                        validation : {
                            required : 'The categories are required',
                        }
                    },
                    contract: {
                        name : 'contract',
                        text : 'Contract :',
                        placeholder : 'Contract',
                        validation : {
                            required : 'The contract is required',
                            minLenght : 'The contract must contain at least 5 characters',
                            maxLenght : 'The contract must contain at most 60 characters'
                        }
                    },
                    salary: {
                        name : 'salary',
                        text : 'Salary :',
                        placeholder : 'Salary',
                        validation : {
                            required : 'The salary is required',
                            numberRequired : 'The salary must be a valid number (you can include 2 digits after the comma)',
                        }
                    },
                    startDate: {
                        name : 'startDate',
                        text : 'Start date :',
                        placeholder : 'Start date',
                        validation : {
                            required : 'The start date is required',
                            dateRequired : 'The start date must be a valid date and greater than the current date',
                        }
                    },
                    file: {
                        name : 'file',
                        text : 'Upload files :',
                        placeholder : 'Upload files',
                        validation : {
                            required : 'The files are required',
                        }
                    },
                    submitButton: {
                        text : 'Submit',
                    },
                },
                InscriptionFormEtudiant : {
                    title : {
                        name : 'title',
                        text : 'Registration for students',
                    },
                    firstName: {
                        name : 'firstName',
                        text : 'First name :',
                        placeholder : 'Jack',
                        validation : {
                            required : 'The first name is required',
                        }
                    },
                    lastName : {
                        name : 'lastName',
                        text : 'Last name :',
                        placeholder : 'Sparrow',
                        validation : {
                            required : 'The last name is required',
                        }
                    },
                    email : {
                        name : 'email',
                        text : 'Email :',
                        placeholder : 'email@email.com',
                        validation : {
                            required : 'The email is required',
                        }
                    },
                    password : {
                        name : 'password',
                        text : 'Password :',
                        placeholder : 'abcas123',
                        validation : {
                            required : 'The password is required',
                        }
                    },
                    phone: {
                        name : 'phone',
                        text : 'Phone :',
                        placeholder : '123-456-7890',
                        validation : {
                            required : 'The phone is required',
                        }
                    },
                    programme: {
                        name : 'programme',
                        text : 'Program :',
                        placeholder : 'Choose a program',
                        validation : {
                            required : 'The program is required',
                        }
                    },
                    submitButton: {
                        text : 'Create account',
                    },
                },
            },
        }
      },
      fr: {
        translation: {
            formField: {
                InternshipOfferForm : {
                    title: {
                        name : 'title',
                        text : 'Titre de l\'offre d\'emploi :',
                        placeholder : 'Titre de l\'offre d\'emploi',
                        validation : {
                            required : 'Le titre de l\'offre d\'emploi est requis',
                            minLenght : 'Le titre de l\'offre d\'emploi doit contenir au moins 5 caractères',
                            maxLenght : 'Le titre de l\'offre d\'emploi doit contenir au maximum 100 caractères'
                      }
                    },
                    description: {
                        name : 'description',
                        text : 'Description de l\'offre d\'emploi :',
                        placeholder : 'Description de l\'offre d\'emploi',
                        validation : {
                            required : 'La description de l\'offre d\'emploi est requise',
                            minLenght : 'La description de l\'offre d\'emploi doit contenir au moins 20 caractères',
                            maxLenght : 'La description de l\'offre d\'emploi doit contenir au maximum 1000 caractères'

                        }
                    },
                    location: {
                        name : 'location',
                        text : 'Lieu :',
                        placeholder : 'Lieu',
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
                    contract: {
                        name : 'contract',
                        text : 'Contrat :',
                        placeholder : 'Contrat',
                        validation : {
                            required : 'Le contrat est requis',
                            minLenght : 'Le contrat doit contenir au moins 5 caractères',
                            maxLenght : 'Le contrat doit contenir au maximum 60 caractères'
                        }
                    },
                    salary: {
                        name : 'salary',
                        text : 'Salaire :',
                        placeholder : 'Salaire',
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
                    file: {
                        name : 'file',
                        text : 'Télécharger des fichiers :',
                        placeholder : 'Télécharger des fichiers',
                        validation : {
                            required : 'Les fichiers sont requis',
                        }
                    },
                    submitButton: {
                        text : 'Soumettre',
                    },
                },
                InscriptionFormEtudiant : {
                    title : {
                        name : 'title',
                        text : 'Inscription de l\'étudiant',
                    },
                    firstName: {
                        name : 'firstName',
                        text : 'Prénom :',
                        placeholder : 'Jean',
                        validation : {
                            required : 'Le prénom est requis',
                        }

                    },
                    lastName: {
                        name : 'lastName',
                        text : 'Nom :',
                        placeholder : 'Pierre',
                        validation : {
                            required : 'Le nom est requis',
                        }
                    },
                    email: {
                        name : 'email',
                        text : 'Courriel :',
                        placeholder : 'email@email.com',
                        validation : {
                            required : 'Le courriel est requis',
                        }
                    },
                    password: {
                        name : 'password',
                        text : 'Mot de passe :',
                        placeholder : 'abc123',
                        validation : {
                            required : 'Le mot de passe est requis',
                        }
                    },
                    phone: {
                        name : 'phone',
                        text : 'Téléphone :',
                        placeholder : '514-123-4567',
                        validation : {
                            required : 'Le téléphone est requis',
                        }
                    },
                    programme: {
                        name : 'programme',
                        text : 'Programme :',
                        placeholder : 'Choisir un programme',
                        validation : {
                            required : 'Le programme est requis',
                        }
                    },
                    submitButton: {
                        name : 'submitButton',
                        text : 'Cree un compte',
                    }
                }
            },
        }
      }
    }
  });

export default i18n;