import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import CV from './components/common/student/i18n/CV';


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
        fallbackLng: 'fr',
        interpolation: {
            escapeValue: false, // not needed for react as it escapes by default
        },
        resources: {
            en: {
                translation: {
                    formField: {
                        InternshipsAgreementPage: {
                            header:{
                                totalInternshipsAgreement: "Total",
                                acceptedInternshipsAgreement: "Accepted",
                                declinedInternshipsAgreement: "Declined",
                                pendingInternshipsAgreement: "Pending",
                            },
                            toast: {
                                errorFetchInternshipsAgreement: "An error occurred while fetching the internships agreement",
                                errorFetchNumberStateOfInternshipsAgreement: "An error occurred while fetching the number of internships agreement",
                            },
                            title: "Internships agreement",
                            internshipsAgreement: {
                                title: "Title",
                                enterprise: "Enterprise",
                                student: "Student",
                                status: "Status",
                            }
                        },
                        InternshipOfferList : {
                            header: {
                                total: "Total offers",
                                accepted: "Offers accepted",
                                declined: "Offers declined",
                                pending: "Offers pending",
                            },
                            table: {
                                title: "Title",
                                enterprise: "Enterprise",
                                location: "Location",
                                status: "Status",
                                startDate: "Start date",
                                PENDING: "Pending",
                                ACCEPTED: "Accepted",
                                DECLINED: "Declined",
                            },
                            title: "Internship offers",
                            toast:{
                                errorFetchOffers:"An error occurred while fetching the offers",
                                errorFetchNumberStateOfOffers:"An error occurred while fetching the number of offers",
                            }
                        },
                        InternshipOfferForm : {
                            titleForm: 'Iternship offer form',
                            title: {
                                name : 'title',
                                text : 'Title of the job ',
                                placeholder : 'developper',
                                validation : {
                                    required : 'The title of the job is required',
                                    minLenght : 'The title of the job must contain at least 5 characters',
                                    maxLenght : 'The title of the job must contain at most 20 characters',
                                    badCharactere : 'The title of the job contains unauthorized characters'
                                }
                            },
                            description: {
                                name : 'description',
                                text : 'Description of the job',
                                placeholder : 'a long description',
                                validation : {
                                    required : 'The description of the job is required',
                                    minLenght : 'The description of the job must contain at least 10 characters',
                                    maxLenght : 'The description of the job must contain at most 1000 characters',
                                    badCharactere: "The description of the job contains unauthorized characters",
                                    scriptDetected: "The description of the job contains a script."
                                }
                            },
                            location: {
                                name : 'location',
                                text : 'Location',
                                placeholder : 'Montréal',
                                validation : {
                                    required : 'The location is required',
                                    minLenght : 'The location must contain at least 5 characters',
                                    maxLenght : 'The location must contain at most 20 characters',
                                    badCharactere: "The location contains unauthorized characters"
                                }
                            },
                            program: {
                                name : 'Program',
                                text : 'Program',
                                placeholder : 'Choose a program',
                                validation : {
                                    required : 'Please select a valid category',
                                    badSelect: "The selected category is not valid"
                                }
                            },
                            salary: {
                                name : 'salary',
                                text : 'Salary ($/h)',
                                placeholder : '18',
                                validation : {
                                    required : 'The salary is required',
                                    numberRequired : 'Please enter a valid number',
                                    minLenght: 'The salary must be greater than 0',
                                    maxLenght: "The salary must be less than or equal to 100$ per hour"
                                }
                            },
                            startDate: {
                                name : 'startDate',
                                text : 'Start date',
                                placeholder : 'Start date',
                                validation : {
                                    required : 'The start date is required',
                                    dateNotValid : 'Please enter a valid date',
                                    dateToLow : "The start date must be today or a future date"
                                }
                            },
                            endDate: {
                                name : 'endDate',
                                text : 'End date',
                                placeholder : 'End date',
                                validation : {
                                    required : 'The end date is required',
                                    dateNotValid: 'Please enter a valid date',
                                    dateToHigh: 'The end date must be today or a future date' ,
                                    dateToLow: 'The end date must be after the start date'
                                }
                            },
                            file: {
                                name : 'file',
                                text : 'Drag & drop your files here',
                                smallText: 'or',
                                span : 'browse',
                                placeholder : 'Upload files',
                                validation : {
                                    required : 'Files are required',
                                    toMuchFile: 'You can only upload 5 files maximum',
                                    BigSizeFile: 'The file {{name}} exceeds the maximum size of 5 MB',
                                }
                            },
                            button: {
                                sumbit : 'Submit',
                                close : 'Close',
                            },
                        },
                        GSReviewOfferForm: {
                            button:{
                                refused:"Refused",
                                approved:"Approved"
                            }
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
                                    pattern: 'Example: 514-123-4567',
                                }
                            },
                            matricule: {
                                name : 'matricule',
                                text : 'Matricule :',
                                placeholder : '1236789',
                                validation : {
                                    required : 'The matricule is required',
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
                            reussite: {
                                name : 'Registration successful',
                                link: 'You can login here',
                            },
                            error : {
                                name : 'Registration failed',
                            }
                        },
                        ListItemCounterSelector: {
                            numberItem: "Number of item :",
                        },
                        InternshipOfferModal: {
                            commentary: "Commentary",
                            placeholder: "Add a comment",
                            button: {
                                approved: "Approved",
                                refused: "Refused",
                            },
                            validation: {
                                minLenght : 'The commentary must contain at least 10 characters',
                                maxLenght : 'The commentary must contain at most 1000 characters',
                                badCharactere: "The commentary contains unauthorized characters",
                                scriptDetected: "The commentary contains a script.",
                                required: 'The commentary is required',
                            }
                        },
                        InscriptionEmployeur:{
                            nom:{
                                name:'nom',
                                text:'Last Name :',
                                placeholder:'Your last name ..'
                            },
                            prenom:{
                                name:'prenom',
                                text:'First Name :',
                                placeholder:'Your first name ..'
                            },
                            entreprise:{
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
                                text:'Password : ',
                                placeholder:'Password ...'

                            },
                            programme: {
                                name: 'programme',
                                text: 'Select one programme :',
                                validation: {
                                    required: 'An error occured, please try again'
                                }
                            },
                            passWordShown: {
                                name: 'passWordShown',
                                text: 'Show'
                            },
                            passWordNotShown: {
                                name: 'passWordNotShown',
                                text: 'Hide'
                            },
                            soumettre: {
                                name: 'soumettre',
                                text: 'Submit'
                            },
                            erreur: {
                                name: 'erreur',
                                text: "An error occured, while registering try again"
                            },
                            titre: {
                                name: 'titre',
                                text: 'Registration employer'
                            }

                        },
                        EtudiantStage: {
                            titre: {
                                name: 'titre',
                                text: 'Internship for students'
                            },
                            stage: {
                                name: 'stage',
                                description: {
                                    name: 'description',
                                    text: 'Description :',
                                },
                                location: {
                                    name: 'location',
                                    text: 'Location :',
                                },
                                salary: {
                                    name: 'salary',
                                    text: 'Salary per hour :'
                                },
                                startDate: {
                                    name: 'startDate',
                                    text: 'Start date :',
                                },
                                endDate: {
                                    name: 'endDate',
                                    text: 'End date :',
                                },
                                apply: {
                                    name: 'apply',
                                    text: 'Apply',
                                }
                            }
                        },
                        Header: {
                            stage: {
                                name: 'stage',
                                text: 'Intership Offer',
                            },
                            cv: {
                                name: 'CV',
                                text: 'Upload your CV',
                            },
                            interview : {
                                name: 'Interview',
                                text: 'Interview',
                            },
                            sidebar: {
                                stage: {
                                    name: 'stage',
                                    text: "Offer"
                                },
                                offre_applique: {
                                    name: 'offre applique',
                                    text: "Applied offer"
                                }
                            },
                            profilMenu: {
                                signOut: {
                                    name: 'signOut',
                                    text: "Sign out"
                                },
                                active: {
                                    name: 'active',
                                    text: "Active"
                                },
                                inactive: {
                                    name: 'inactive',
                                    text: "Inactive"
                                },
                                changeTheme: {
                                    name: 'changeTheme',
                                    text: "Change theme"
                                },
                                changeLanguage: {
                                    name: 'changeLanguage',
                                    text: "Change language"
                                }
                            },
                            sidebarEmployeur: {
                                offre: {
                                    name: 'offre',
                                    text: "Offer"
                                },
                                candidature: {
                                    name: 'candidature',
                                    text: "Application"
                                },
                                contract: {
                                    name: 'contract',
                                    text: "Contract"
                                },
                                pendingOffer: {
                                    name: 'pendingOffer',
                                    text: "Pending offer"
                                },
                                newOffre: {
                                    name: 'newOffre',
                                    text: "New offer"
                                }
                            }
                        },
                        Home: {
                            name: 'home',
                            text: 'Welcome to the home page : ',
                        },
                        programs: {
                            techniqueInformatique: {
                                id: 1,
                                name: 'techniqueInformatique',
                                text: 'Computer Science Technology',
                            },
                            techniqueAdministration: {
                                id: 2,
                                name: 'techniqueAdministration',
                                text: 'Administrative Technology',
                            },
                            techniqueLogistique: {
                                id: 3,
                                name: 'techniqueLogistique',
                                text: 'Logistics Technology',
                            },
                            techniqueComptabiliteAndGestion: {
                                id: 4,
                                name: 'techniqueComptabiliteEtGestion',
                                text: 'Accounting and Management Technology',
                            },
                        },
                        homeEmployeur: {
                            titre: {
                                name: 'titre',
                                text: 'Dashboard'
                            },
                            condidature: {
                                name: 'totalCondidature',
                                text: 'Application'
                            },
                            offre: {
                                name: 'totalOffre',
                                text: 'Offer'
                            },
                            newOffre: {
                                name: 'newOffre',
                                text: 'New offer'
                            },
                            contract: {
                                name: 'totalContract',
                                text: 'Contract'
                            },
                            offerTable: {
                                titre: {
                                    name: 'titre',
                                    text: 'Title'
                                },
                                location: {
                                    name: 'location',
                                    text: 'Location'
                                },
                                status: {
                                    name: 'status',
                                    text: 'Status'
                                },
                                startDate: {
                                    name: 'startDate',
                                    text: 'Start date'
                                },
                                edit: {
                                    name: 'edit',
                                    text: 'Edit'
                                },
                                PENDING: {
                                    name: 'PENDING',
                                    text: 'Pending'
                                },
                                ACCEPTED: {
                                    name: 'APPROVED',
                                    text: 'Approved'
                                },
                                DECLINED: {
                                    name: 'DECLINED',
                                    text: 'Declined'
                                }
                            }
                        },
                        profilMenu : {
                            active : {
                                name : 'active',
                                text : "Active"
                            },
                            inactive : {
                                name : 'inactive',
                                text : "Inactive"
                            },
                            changeTheme : {
                                name : 'changeTheme',
                                text : "Change theme"
                            },
                            changeLanguage : {
                                name : 'changeLanguage',
                                text : "Change language"
                            }
                        },
                        sidebarEmployeur : {
                            offre : {
                                name : 'offre',
                                text : "Offer"
                            },
                            candidature : {
                                name : 'candidature',
                                text : "Application"
                            },
                            contract : {
                                name : 'contract',
                                text : "Contract"
                            },
                            pendingOffer : {
                                name : 'pendingOffer',
                                text : "Pending offer"
                            },
                            newOffre : {
                                name : 'newOffre',
                                text : "New offer"
                            }
                        }
                    },
                    GSOfferPage: {
                        placeholder:"Please enter a comment",
                        title: "Internship offer",
                        errorFetchOffer: "An error occurred while fetching the offer",
                        errorSaveOfferReview: "An error occurred while saving the offer review",
                        succesSaveOfferReview: "The offer review has been saved",
                        validiation:{
                            required : 'The comment is required',
                            minLenght : 'The comment must contain at least 10 characters',
                            maxLenght : 'The comment must contain at most 1000 characters',
                            scriptDetected: "The comment contains a script.",
                        }

                    },
                    ErrorPage: {
                        errorMessage: {
                            name: 'errorMessage',
                            text: 'This page does not exist',
                        },
                        ReturnButton: {
                            name: 'ReturnButton',
                            text: 'Return to the home page',
                        }
                    },
                    LoginPage: {
                        SignInButton: {
                            name: 'SignInButton',
                            text: 'Sign in',
                        },
                        Title: {
                            name: 'Title',
                            text: 'Sign in to your account',
                        },
                        ContactUs: {
                            name: 'ContactUs',
                            text: 'Contact us',
                            Question : {
                                name: 'Question',
                                text: 'Not registered already ?',
                            }
                        },
                        ForgotPassword: {
                            name: 'ForgotPassword',
                            text: 'Forgot password ?',
                        },
                        email: {
                            name : 'email',
                            text : 'Email address',
                            placeholder : 'signIn@123.com',
                            validation : {
                                required : 'The email address is required',
                                emailRequired : 'The email address must be a valid email address',
                            }
                        },
                        password: {
                            name : 'password',
                            text : 'Password',
                            placeholder : '*******',
                            validation : {
                                required : 'The password is required',
                                minLenght : 'The password must contain at least 5 characters',
                            }
                        }
                    },
                    cv: CV.en,
                    StudentInterview: {
                        table: {
                            name: 'table',
                            title: 'Title',
                            location: 'Location',
                            date: 'Date of the interview',
                            company: 'Company',
                            action: {
                                name: 'action',
                                text: 'Action',
                                button : {
                                    accept: 'Accept',
                                    decline: 'Decline',
                                },
                                status:{
                                    accepted: "Accepted",
                                    declined: "Declined",
                                }
                            },
                        }
                    }

                },
            },
            fr: {
                translation: {
                    formField: {
                        InternshipsAgreementPage: {
                            header:{
                                totalInternshipsAgreement: "Total",
                                acceptedInternshipsAgreement: "Acceptées",
                                declinedInternshipsAgreement: "Refusées",
                                pendingInternshipsAgreement: "Attente",
                            },
                            toast: {
                                errorFetchInternshipsAgreement: "Une erreur est survenue lors de la récupération des ententes de stage",
                                errorFetchNumberStateOfInternshipsAgreement: "Une erreur est survenue lors de la récupération du nombre d'ententes de stage",
                            },
                            title: "Ententes de stage",
                            internshipsAgreement: {
                                title: "Titre",
                                enterprise: "Entreprise",
                                student: "Étudiant",
                                status: "Statut",
                            }
                        },
                        InternshipOfferList : {
                            header: {
                                total: "Total des offres",
                                accepted: "Offres acceptées",
                                declined: "Offres refusées",
                                pending: "Offres en attente",
                            },
                            table: {
                                title: "Titre",
                                enterprise: "Entreprise",
                                location: "Localisation",
                                status: "Statut",
                                startDate: "Date de commencement",
                                PENDING: "Attente",
                                ACCEPTED: "Accepté",
                                DECLINED: "Refusé",
                            },
                            title: "Offres de stage",
                            toast:{
                                errorFetchOffers:"Une erreur est survenue lors de la récupération des offres",
                                errorFetchNumberStateOfOffers:"Une erreur est survenue lors de la récupération du nombre d'offres",
                            }
                        },
                        InternshipOfferForm : {
                            titleForm: 'Formulaire d\'offre de stage',
                            title: {
                                name : 'title',
                                text : 'Titre de l\'offre d\'emploi :',
                                placeholder : 'Developpeur',
                                validation : {
                                    required : 'Le titre de l\'offre d\'emploi est requis',
                                    minLenght : 'Le titre doit comporter au moins 5 caractères.',
                                    maxLenght : 'Le titre ne doit pas dépasser 20 caractères.',
                                    badCharactere : 'Le titre contient des caractères non autorisés.'
                                }
                            },
                            description: {
                                name : 'description',
                                text : 'Description de l\'offre d\'emploi :',
                                placeholder : 'Une longue description',
                                validation : {
                                    required : 'La description de l\'offre d\'emploi est requise',
                                    minLenght : 'La description doit comporter au moins 10 caractères.',
                                    maxLenght : 'La description ne doit pas dépasser 1000 caractères.',
                                    badCharactere: "La description contient des caractères non autorisés.",
                                    scriptDetected: "La description contient un script."
                                }
                            },
                            location: {
                                name : 'location',
                                text : 'Lieu :',
                                placeholder : 'Montreal',
                                validation : {
                                    required : 'Le lieu est requis',
                                    minLenght : 'La localisation doit comporter au moins 5 caractères.',
                                    maxLenght : 'La localisation ne doit pas dépasser 20 caractères.',
                                    badCharactere: "La localisation contient des caractères non autorisés."
                                }
                            },
                            program: {
                                name : 'programme',
                                text : 'Programmes :',
                                placeholder : 'Choisi un porgramme',
                                validation : {
                                    required : 'Veuillez sélectionner une catégorie valide.',
                                    badSelect: "La catégorie sélectionnée n'est pas valide."
                                }
                            },
                            salary: {
                                name: 'salary',
                                text: 'Salaire ($/h):',
                                placeholder: '18',
                                validation: {
                                    required: 'Le salaire est requis',
                                    numberRequired: 'Veuillez entrer un nombre valide.',
                                    minLenght: 'Le salaire doit être supérieur à 0.',
                                    maxLenght: "Le salaire doit être inférieur ou égal à 100$ par heure."
                                }
                            },
                            startDate: {
                                name: 'startDate',
                                text: 'Date de début :',
                                placeholder: 'Date de début',
                                validation: {
                                    required: 'La date de début est requise',
                                    dateNotValid: 'Veuillez entrer une date valide.',
                                    dateToLow: "La date de début doit être aujourd'hui ou une date future."
                                }
                            },
                            endDate: {
                                name: 'endDate',
                                text: 'Date de fin :',
                                placeholder: 'Date de fin',
                                validation: {
                                    required: 'La date de fin est requise',
                                    dateNotValid: 'Veuillez entrer une date valide.',
                                    dateToHigh: 'La date de fin doit être aujourd\'hui ou une date future.',
                                    dateToLow: 'La date de fin doit être postérieure à la date de début.'

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
                                    toMuchFile: 'Vous ne pouvez télécharger que 5 fichiers au maximum.',
                                    BigSizeFile: 'Le fichier {{name}} dépasse la taille maximale de 5 Mo.',
                                    BadTypeFile: 'Le fichier {{name}} a une extension non autorisée.'
                                }
                            },
                            button: {
                                sumbit : 'Soumettre',
                                close : 'Fermer',
                                button: {
                                    approved: "Approuvé",
                                    refused: "Refusé",
                                }
                            },
                        },
                        ListItemCounterSelector: {
                            numberItem: "Nombre d'item :",
                        },
                        InternshipOfferModal: {
                            commentary: "Commentaire",
                            placeholder: "Ajouter un commentaire",
                            button: {
                                approved: "Approuver",
                                refused: "Refuser",
                            },
                            validation: {
                                minLenght : 'Le commentaire doit comporter au moins 10 caractères.',
                                maxLenght : 'Le commentaire ne doit pas dépasser 1000 caractères.',
                                badCharactere: "Le commentaire contient des caractères non autorisés.",
                                scriptDetected: "La commentaire contient un script.",
                                required: "Le commentaire est requis"
                            }
                        },
                        GSReviewOfferForm: {
                            button:{
                                refused:"Refuser",
                                approved:"Approuver"
                            }
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
                                    pattern: 'Exemple: 514-123-4567',
                                }
                            },
                            matricule: {
                                name : 'matricule',
                                text : 'Matricule :',
                                placeholder : '1236789',
                                validation : {
                                    pattern : 'Le matricule est compose de 7 chiffres',
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
                            },
                            reussite: {
                                name : 'Inscription réussie',
                                link : 'Vous pouvez vous connecter ici',
                            },
                            error : {
                                name : 'Inscription échouée',
                            }
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
                            entreprise:{
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
                            programme: {
                                name: 'programme',
                                text: 'Selectionner un programme',
                                validation: {
                                    required: 'Une erreur est survenu'
                                }
                            },
                            passWordShown: {
                                name: 'passWordShown',
                                text: 'Voir'
                            },
                            passWordNotShown: {
                                name: 'passWordNotShown',
                                text: 'Cachée'
                            },
                            soumettre: {
                                name: 'soumettre',
                                text: 'Soumettre'
                            },
                            erreur: {
                                name: 'erreur',
                                text: "Une erreur c'est produite lors de l'inscription veuillez réessayer"
                            },
                            titre: {
                                name: 'titre',
                                text: 'Inscription Employeur'
                            }

                        },
                        EtudiantStage: {
                            titre: {
                                name: 'titre',
                                text: 'Stages pour étudiants'
                            },
                            stage: {
                                name: 'stage',
                                description: {
                                    name: 'description',
                                    text: 'Description de l\'offre d\'emploi :',
                                },
                                location: {
                                    name: 'location',
                                    text: 'Lieu :',
                                },
                                salary: {
                                    name: 'salary',
                                    text: 'salaire par heure :'
                                },
                                startDate: {
                                    name: 'startDate',
                                    text: 'Date de début :',
                                },
                                endDate: {
                                    name: 'endDate',
                                    text: 'Date de fin :',
                                },
                                apply: {
                                    name: 'Apply',
                                    text: 'Postuler',
                                }
                            }
                        },
                        Header: {
                            stage: {
                                name: 'stage',
                                text: 'Offre stages',
                            },
                            cv: {
                                name: 'CV',
                                text: 'Televerser votre CV',
                            },
                            interview : {
                                name: 'Interview',
                                text: 'Entrevue',
                            },
                            sidebar: {
                                stage: {
                                    name: 'stage',
                                    text: "Offre"
                                },
                                offre_applique: {
                                    name: 'offre applique',
                                    text: "Offre appliqué"
                                },
                            },
                            profilMenu: {
                                signOut: {
                                    name: 'signOut',
                                    text: "Déconnexion"
                                },
                                active: {
                                    name: 'active',
                                    text: "Active"
                                },
                                inactive: {
                                    name: 'inactive',
                                    text: "Inactive"
                                },
                                changeTheme: {
                                    name: 'changeTheme',
                                    text: "Changer le thème"
                                },
                                changeLanguage: {
                                    name: 'changeLanguage',
                                    text: "Changer la langue"
                                }
                            },
                            sidebarEmployeur: {
                                offre: {
                                    name: 'offre',
                                    text: "Offre"
                                },
                                candidature: {
                                    name: 'candidature',
                                    text: "Candidature"
                                },
                                contract: {
                                    name: 'contract',
                                    text: "Contrat"
                                },
                                pendingOffer: {
                                    name: 'pendingOffer',
                                    text: "Offre en attente"
                                },
                                newOffre: {
                                    name: 'newOffre',
                                    text: "Nouvelle offre"
                                }
                            }
                        },
                        Home: {
                            name: 'home',
                            text: 'Bienvenue sur la page d\'accueil : ',
                        },
                        programs: {
                            techniqueInformatique: {
                                id: 1,
                                name: 'techniqueInformatique',
                                text: 'Technique de l\'informatique',
                            },
                            techniqueAdministration: {
                                id: 2,
                                name: 'techniqueAdministration',
                                text: 'Technique de l\'administration',
                            },
                            techniqueLogistique: {
                                id: 3,
                                name: 'techniqueLogistique',
                                text: 'Technique de la logistique',
                            },
                            techniqueComptabiliteAndGestion: {
                                id: 4,
                                name: 'techniqueComptabiliteEtGestion',
                                text: 'Technique de la comptabilité et de la gestion',
                            },
                        },
                        homeEmployeur: {
                            titre: {
                                name: 'titre',
                                text: 'Tableau de bord'
                            },
                            condidature: {
                                name: 'totalCondidature',
                                text: 'Candidature'
                            },
                            offre: {
                                name: 'totalOffre',
                                text: 'Offre'
                            },
                            newOffre: {
                                name: 'newOffre',
                                text: 'Nouvelle offre'
                            },
                            contract: {
                                name: 'totalContract',
                                text: 'Contrat'
                            },
                            offerTable: {
                                titre: {
                                    name: 'titre',
                                    text: 'Titre'
                                },
                                location: {
                                    name: 'location',
                                    text: 'Lieu'
                                },
                                status: {
                                    name: 'status',
                                    text: 'Etat'
                                },
                                startDate: {
                                    name: 'startDate',
                                    text: 'Date de début'
                                },
                                edit: {
                                    name: 'edit',
                                    text: 'Modifier'
                                },
                                PENDING: {
                                    name: 'PENDING',
                                    text: 'En attente'
                                },
                                ACCEPTED: {
                                    name: 'APPROVED',
                                    text: 'Approuvé'
                                },
                                DECLINED: {
                                    name: 'DECLINED',
                                    text: 'Refusé'
                                }
                            }
                        },
                        sidebarEmployeur : {
                            offre : {
                                name : 'offre',
                                text : "Offre"
                            },
                            candidature : {
                                name : 'candidature',
                                text : "Candidature"
                            },
                            contract : {
                                name : 'contract',
                                text : "Contrat"
                            },
                            pendingOffer : {
                                name : 'pendingOffer',
                                text : "Offre en attente"
                            },
                            newOffre : {
                                name : 'newOffre',
                                text : "Nouvelle offre"
                            }
                        }
                    },
                    GSOfferPage: {
                        placeholder:"Veillez entrer un commentaire",
                        title: "Offre de stage",
                        errorFetchOffer: "Une erreur est survenue lors de la récupération de l'offre",
                        errorSaveOfferReview: "Une erreur est survenue lors de la sauvegarde de l'offre",
                        succesSaveOfferReview: "L'offre a été sauvegardée",
                        validiation:{
                            required : 'Le commentaire est requis',
                            minLenght : 'Le commentaire doit contenir au moins 10 caractères',
                            maxLenght : 'Le commentaire doit contenir au plus 1000 caractères',
                            scriptDetected: "Le commentaire contient un script.",
                        }
                    },
                    ErrorPage: {
                        errorMessage: {
                            name: 'errorMessage',
                            text: 'Cette page n\'existe pas',
                        },
                        ReturnButton: {
                            name: 'ReturnButton',
                            text: 'Retourner à la page d\'accueil',
                        }
                    },
                    LoginPage: {
                        SignInButton: {
                            name: 'SignInButton',
                            text: 'Se connecter',
                        },
                        Title: {
                            name: 'Title',
                            text: 'Connexion à votre compte',
                        },
                        ContactUs: {
                            name: 'ContactUs',
                            text: 'Nous contacter',
                            Question : {
                                name: 'Question',
                                text: 'Pas encore enregistré ?',
                            }
                        },
                        ForgotPassword: {
                            name: 'ForgotPassword',
                            text: 'Vous avez oublié votre mot de passe ?',
                        },
                        email: {
                            name: 'email',
                            text: 'Adresse e-mail',
                            placeholder: 'saisir@123.com',
                            validation: {
                                required: 'L\'adresse e-mail est obligatoire',
                                emailRequired: 'L\'adresse e-mail doit être une adresse e-mail valide',
                            }
                        },
                        password: {
                            name: 'password',
                            text: 'Mot de passe',
                            placeholder: '*******',
                            validation: {
                                required: 'Le mot de passe est requis',
                                minLenght: 'Le mot de passe doit contenir au moins 5 caractères',
                            }
                        }
                    },
                    cv: CV.fr,
                    StudentInterview: {
                        table: {
                            name: 'table',
                            title: 'Titre',
                            location: 'Lieu de travail',
                            date: 'Date de l\'entrevue',
                            company: 'Compagnie',
                            action: {
                                name: 'action',
                                text: 'Actions',
                                button : {
                                    accept: 'Accepter',
                                    decline: 'Decliner',
                                },
                                status:{
                                    accepted: "Accepté",
                                    declined: "Decliné",
                                }
                            },
                        }
                    }
                }
            }
        }
    });

export default i18n;