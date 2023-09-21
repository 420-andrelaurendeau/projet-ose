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
                InscriptionEmployeur:{
                    nom:{
                        name:'nom',
                        text:'Last Name :',
                        placeholder:'Your last name ..'
                    },
                    prenom:{
                        name:'prenom',
                        text:'First Na,e :',
                        placeholder:'Your first name ..'
                    },
                    nomEntreprise:{
                        name:'nomEntreprise',
                        text:'Entreprise Name :',
                        placeholder:'Your entreprise name ..'
                    },
                    email:{
                        name:'email',
                        text:'Email :',
                        placeholder:'Your email ..'
                    },
                    telephone:{
                        name:'telephone',
                        text:'Phone Number :',
                        placeholder:'ex : 450-450-4500'
                    },
                    password:{
                        name:'password',
                        text:'Password',
                        placeholder:'Password ...'

                    },
                    programme:{
                        name:'programme',
                        text:'Select one programme'
                    },
                    passWordShown:{
                        name:'passWordShown',
                        text:'Show'
                    },
                    passWordNotShown:{
                        name:'passWordNotShown',
                        text:'Hide'
                    },
                    soumettre:{
                        name:'soumettre',
                        text:'Submit'
                    }, erreur:{
                        name:'erreur',
                        text:"An error occured, while registering try again"
                    },
                    titre:{
                        name:'titre',
                        text:'Registration employer'
                    }

                }
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
                InscriptionEmployeur:{
                    nom:{
                        name:'nom',
                        text:'Nom :',
                        placeholder:'Votre nom ..'
                    },
                    prenom:{
                        name:'prenom',
                        text:'Prenom :',
                        placeholder:'Votre prénom ..'
                    },
                    nomEntreprise:{
                        name:'nomEntreprise',
                        text:'Nom de l\'entreprise :',
                        placeholder:'Nom de l\'entreprise ..'
                    },
                    email:{
                        name:'email',
                        text:'Email :',
                        placeholder:'Email ...'
                    },
                    telephone:{
                        name:'telephone',
                        text:'Téléphone :',
                        placeholder:'ex : 450-450-4500'
                    },
                    password:{
                        name:'password',
                        text:'Mot de passe :',
                        placeholder:'Mot de passe ...'

                    },
                    programme:{
                        name:'programme',
                        text:'Selectionner un programme'
                    },
                    passWordShown:{
                        name:'passWordShown',
                        text:'Voir'
                    },
                    passWordNotShown:{
                        name:'passWordNotShown',
                        text:'Cachée'
                    },
                    soumettre:{
                        name:'soumettre',
                        text:'Soumettre'
                    },
                    erreur:{
                        name:'erreur',
                        text:"Une erreur c'est produite lors de l'inscription veuillez réessayer"
                    },
                    titre:{
                        name:'titre',
                        text:'Inscription Employeur'
                    }

                }
            },
        }
      }
    }
  });

export default i18n;