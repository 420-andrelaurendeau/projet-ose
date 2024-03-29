import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
import CV from './components/common/student/i18n/CV';
import application from "./components/common/Employer/application/i18n/application";
import employerOffer from "./components/common/Employer/offer/i18n/offer";
import contractPage from "./components/common/Employer/contract/i18n/contract";
import header from "./pages/shared/i18n/homeI18N";
import {StudentInternshipI18n} from "./components/common/student/i18n/StudentInternshipI18n";
import SidebarEtudiant from "./components/common/student/SidebarEtudiant";
import {SideBarEtudiantI18n} from "./components/common/student/i18n/SideBarEtudiantI18n";
import {StudentInternshipPagei18n} from "./pages/student/i18n/StudentInternshipPagei18n";
import internshipAgreement from "./components/common/internshipManager/internshipsAgreement/agreement";
import studentOffer from "./components/common/student/i18n/Offer";


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
                        filter: {
                            title: "Filter by season: ",
                            Hiver: "Winter",
                            Été: "Summer",
                            All: "All",
                        },
                        internshipAgreement,
                        application:application.en,
                        employerOffer:employerOffer.en,
                        studentOffer:studentOffer.en,
                        contractPage:contractPage.en,
                        header,
                        notifications: {
                            noNotification: "You have no notifications",
                            newOfferAvailable: "There`s a new offer.",
                            cvAccepter: "A CV has been approved.",
                            cvRefuser: "A CV has been refused.",
                            studentApplyOnOffer: "You have a new candidate.",
                            revueCv: "A CV is available for review.",
                            youAreAcceptedForStage: "You have been accepted for an internship.",
                            newOfferSavedByEmployeur: "A job offer is available for review.",
                            contractSignedByGSForEmployer: "A contract has been signed by an internship manager.",
                            contractSignedByGSForStudent: "Your internship manager has signed a contract.",
                            contractSignedByStudentForEmployer: "A candidate has signed a contract.",
                            contractSignedByStudentForGS: "A student has signed a contract.",
                            contractSignedByEmployerForStudent: "An employer has signed a contract.",
                            contractSignedByEmployerForGS: "An employer has signed a contract.",
                            newContractAsBeenCreated: "A new contract is now available.",
                            contractAsBeenSignedByThreeParties: "A contract has been signed by all parties.",
                            offerAsBeenAccepted: "A job offer has been approved.",
                            offerAsBeenDeclined: "A job offer has been denied.",
                            newStageAsBeenCreated: "There`s a new internship application."
                        },
                        InternshipsAgreementPage: {
                            header:{
                                totalInternshipsAgreement: "Total",
                                acceptedInternshipsAgreement: "Accepted",
                                declinedInternshipsAgreement: "Declined",
                                pendingInternshipsAgreement: "Pending",
                            },
                            toast: {
                                errorFetchInternshipsAgreement: "An error occurred while fetching the internships agreement.",
                                errorFetchNumberStateOfInternshipsAgreement: "An error occurred while fetching the number of internships agreement.",
                            },
                            title: "Contracts of internship",
                            internshipsAgreement: {
                                title: "Title",
                                enterprise: "Enterprise",
                                student: "Student",
                                statut: "Status",
                                status: {
                                    accepted: "Accepted",
                                    declined: "Declined",
                                    pending: "Pending",
                                },
                                view:{
                                    name: 'view',
                                    text: "View"
                                },
                                sign: {
                                    student: "Refused by the student",
                                    employer: "Refused by the employer",
                                },
                                empty : "No internships agreement available"
                            },
                        },
                        InternshipOfferList : {

                            succesSaveOfferReview: "The offer review has been saved",
                            header: {
                                total: "Total offers",
                                accepted: "Offers accepted",
                                declined: "Offers declined",
                                pending: "Offers pending",
                                back: "Back"
                            },
                            table: {
                                title: "Title",
                                enterprise: "Enterprise",
                                location: "Location",
                                status: "Status",
                                startDate: "Start date",
                                salary: "Salary",
                                PENDING: "Pending",
                                ACCEPTED: "Accepted",
                                DECLINED: "Declined",
                            },
                            title: "Internship offers",
                            toast:{
                                errorFetchOffers:"An error occurred while fetching the offers.",
                                errorFetchNumberStateOfOffers:"An error occurred while fetching the number of offers.",
                            },
                            validation : {
                                required : 'A comment is required.',
                                minLenght : 'The comment must contain at least 10 characters.',
                                maxLenght : 'The comment must contain at most 1000 characters.',
                                badCharactere : 'The comment contains unauthorized characters.',
                            }
                        },
                        InternshipOfferForm : {
                            titleForm: 'Iternship offer form',
                            title: {
                                name : 'title',
                                text : 'Job Title ',
                                placeholder : 'developer',
                                validation : {
                                    required : 'The job title is required.',
                                    minLenght : 'The job title must contain at least 5 characters.',
                                    maxLenght : 'The job title must contain at most 20 characters.',
                                    badCharactere : 'The title of the job contains unauthorized characters.'
                                }
                            },
                            description: {
                                name : 'description',
                                text : 'Description of the job',
                                placeholder : 'a long description',
                                validation : {
                                    required : 'The description of the job is required.',
                                    minLenght : 'The description of the job must contain at least 10 characters.',
                                    maxLenght : 'The description of the job must contain at most 1000 characters.',
                                    badCharactere: "The description of the job contains unauthorized characters.",
                                    scriptDetected: "The description of the job contains a script."
                                }
                            },
                            location: {
                                name : 'location',
                                text : 'Location',
                                placeholder : 'Montréal',
                                validation : {
                                    required : 'The location is required.',
                                    minLenght : 'The location must contain at least 5 characters.',
                                    maxLenght : 'The location must contain at most 20 characters.',
                                    badCharactere: "The location contains unauthorized characters."
                                }
                            },
                            program: {
                                name : 'Program',
                                text : 'Program',
                                placeholder : 'Choose a program',
                                validation : {
                                    required : 'Please select a valid program',
                                    badSelect: "The selected program is not valid."
                                }
                            },
                            salary: {
                                name : 'salary',
                                text : 'Salary ($/h)',
                                placeholder : '18',
                                validation : {
                                    required : 'A salary is required.',
                                    numberRequired : 'Please enter a valid number',
                                    minLenght: 'The salary must be greater than 0$ an hour.',
                                    maxLenght: "The salary must be less than or equal to 100$ an hour."
                                }
                            },
                            startDate: {
                                name : 'startDate',
                                text : 'Start date',
                                placeholder : 'Start date',
                                validation : {
                                    required : 'A start date is required.',
                                    dateNotValid : 'Please enter a valid date.',
                                    dateToLow : "The start date must be today or a future date."
                                }
                            },
                            endDate: {
                                name : 'endDate',
                                text : 'End date',
                                placeholder : 'End date',
                                validation : {
                                    required : 'An end date is required.',
                                    dateNotValid: 'Please enter a valid date.',
                                    dateToHigh: 'The end date must be today or a future date.' ,
                                    dateToLow: 'The end date must be after the start date.'
                                }
                            },
                            file: {
                                name : 'file',
                                text : 'Drag & drop your files here',
                                smallText: 'or',
                                span : 'browse',
                                placeholder : 'Upload files',
                                validation : {
                                    required : 'Files are required to be uploaded.',
                                    toMuchFile: 'You can only upload 5 files maximum.',
                                    BigSizeFile: 'The file {{name}} exceeds the maximum size of 5 MB.',
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
                                text : 'Student Registration',
                            },
                            firstName: {
                                name : 'firstName',
                                text : 'First name :',
                                placeholder : 'Jack',
                                validation : {
                                    required : 'The first name is required.',
                                }
                            },
                            lastName : {
                                name : 'lastName',
                                text : 'Last name :',
                                placeholder : 'Sparrow',
                                validation : {
                                    required : 'The last name is required.',
                                }
                            },
                            email : {
                                name : 'email',
                                text : 'Email :',
                                placeholder : 'email@email.com',
                                validation : {
                                    required : 'The email is required.',
                                }
                            },
                            password : {
                                name : 'password',
                                text : 'Password :',
                                placeholder : 'abcas123',
                                validation : {
                                    required : 'The password is required.',
                                }
                            },
                            phone: {
                                name : 'phone',
                                text : 'Phone :',
                                placeholder : '123-456-7890',
                                validation : {
                                    required : 'The phone is required.',
                                    pattern: 'Example: 514-123-4567',
                                }
                            },
                            matricule: {
                                name : 'matricule',
                                text : 'Matricule :',
                                placeholder : '1236789',
                                validation : {
                                    required : 'The matricule is required.',
                                }
                            },
                            programme: {
                                name : 'programme',
                                text : 'Program :',
                                placeholder : 'Choose a program',
                                validation : {
                                    required : 'The program is required.',
                                }
                            },
                            submitButton: {
                                text : 'Create account',
                            },
                            reussite: {
                                name : 'Registration successful.',
                                link: 'You can login here',
                            },
                            error : {
                                name : 'Registration failed.',
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
                                minLenght : 'The commentary must contain at least 10 characters.',
                                maxLenght : 'The commentary must contain at most 1000 characters.',
                                badCharactere: "The commentary contains unauthorized characters.",
                                scriptDetected: "The commentary contains a script.",
                                required: 'A commentary is required.',
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
                                text:'Enterprise Name :',
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
                                text: 'Select one program :',
                                validation: {
                                    required: 'An error occured. Please try again.'
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
                                text: "An error occured, while registering. Please try again."
                            },
                            titre: {
                                name: 'titre',
                                text: 'Employer Registration'
                            }

                        },
                        EtudiantStage: {
                            filter: {
                                title: "Filter by season: ",
                                Hiver: "Winter",
                                Été: "Summer",
                                All: "All",
                            },
                            noOffers: {
                                name: 'noOffers',
                                text: 'No offers available.',
                                noAppliedOffers: "No applied offers."
                            },
                            titre: {
                                name: 'titre',
                                text: 'Internship for students'
                            },
                            options: {
                                name: 'options',
                                text: 'Options',
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
                                },
                                view: {
                                    name: 'view',
                                    text: 'View',
                                }
                            },
                            empty: {
                                name: 'empty',
                                text: 'No internship available.'
                            },
                            toast:{
                                ErrorNoCv:"You must upload your CV and set it as default before applying for an internship.",
                                ErrorOfferApplication: "An error occurred while applying for the internship.",
                                SuccessOfferApplication: "The internship application has been submitted for.",
                            },
                            cv : "CV is loading please wait...",
                        },
                        Header: {
                            stage: {
                                name: 'stage',
                                text: 'Internship Offer',
                            },
                            cvReview : {
                                name: 'CV Review',
                                text: 'Student CV Review',
                            },
                            cv: {
                                name: 'CV',
                                text: 'Your CV',
                            },
                            sidebarEtudiant: {
                                offre: {
                                    name: 'offre',
                                    text: "Offer"
                                },
                                appliedOffer: {
                                    name: 'appliedOffer',
                                    text: "Applied offer",
                                    filter: {
                                        title: "Filter by season: ",
                                        Hiver: "Winter",
                                        Été: "Summer",
                                        All: "All",
                                    }
                                },
                                cv: {
                                    name: 'cv',
                                    text: "Your CV"
                                },
                                stage: {
                                    name: 'stage',
                                    text: "Internship"
                                },
                                contract: {
                                    name: "contract",
                                    text: "Contract"
                                },
                                interview : {
                                    name: 'interview',
                                    text: 'Interview',
                                },
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
                                    text: "Change Theme"
                                },
                                changeLanguage: {
                                    name: 'changeLanguage',
                                    text: "Change Language"
                                },
                                gsProfession: {
                                    name: 'gsProfession',
                                    text: "Internship manager"
                                }
                            },
                            sidebarInternshipManager: {
                                internshipagreement: {
                                    name: 'internshipagreement',
                                    text: "Internship Agreement"
                                },
                                offre: {
                                    name: 'offre',
                                    text: "Offer"
                                },
                                reviewCV: {
                                    name: 'reviewCV',
                                    text: "Review CV"
                                },
                            },
                            sidebarEmployeur: {
                                offre: {
                                    name: 'offre',
                                    text: "Offer"
                                },
                                interview: {
                                    name: 'interview',
                                    text: "Interview"
                                },
                                stage: {
                                    name: 'stage',
                                    text: "Internship"
                                },
                                internshipagreement: {
                                    name: "internshipagreement",
                                    text: "Internship agreement"
                                },
                                newOffre: {
                                    name: 'newOffre',
                                    text: "New offer"
                                }
                            },
                            InscriptionEmployer : "Employer registration",
                            InscriptionStudent : "Student registration",
                        },
                        Home: {
                            name: 'home',
                            text: 'Welcome to OSE, ',
                            text2: "the application for internships at cégep André-Laurendeau",
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
                            filter: {
                                title: "Filter by season: ",
                                Hiver: "Winter",
                                Été: "Summer",
                                All: "All",
                            },
                            titre: {
                                name: 'titre',
                                text: 'Dashboard'
                            },
                            title: {
                                name: 'title',
                                text: 'Your internship offers'
                            },
                            interview:{
                                name: 'interview',
                                text: 'Interview'
                            },
                            candidature: {
                                name: 'totalCondidature',
                                text: 'Application'
                            },
                            view: {
                                name: 'view',
                                text: 'View'
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
                            internship: {
                                name: "internship",
                                text: "Internship"
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
                                },
                                salary: {
                                    name: "salary",
                                    text: "Salary"
                                }
                            },
                            toast:{
                                errorFetchOffers:"An error has occurred while fetching the offers.",
                                errorFetchNumberStateOfOffers:"An error has occurred while fetching the number of offers.",
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
                            internship: {
                                name: "internship",
                                text: "Internship"
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
                        placeholder:"Please enter a comment.",
                        title: "Internship offer",
                        errorFetchOffer: "An error occurred while fetching the offer.",
                        errorSaveOfferReview: "An error occurred while saving the offer review.",
                        validiation:{
                            required : 'The comment is required.',
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
                            text: 'Register',
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
                        },
                        error : {
                            text : "The email address or password is incorrect",
                        }
                    },
                    cv: CV.en,
                    StudentInternship: StudentInternshipI18n.en,
                    StudentInternshipPage:StudentInternshipPagei18n.en,
                    SideBarEtudiant: SideBarEtudiantI18n.en,
                    StudentInterview: {
                        title: {
                            name: 'title',
                            text: 'Your interviews',
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
                        },
                        table: {
                            name: 'table',
                            title: 'Title',
                            location: 'Location',
                            date: 'Date of the interview',
                            company: 'Company',
                            status_text:{
                                name: 'status_text',
                                text: "Status"
                            },
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
                            empty: "No interview available.",
                            noStage: "No internship available.",
                        },
                        reschedule : "Reschedule",
                    },
                    StudentCvEvaluation: {
                        name: 'StudentCvEvaluation',
                        title: 'CV to evaluate',
                        button: {
                            accept: 'Accept',
                            decline: 'Decline',
                            download: "Download the CV",
                        },
                        toast: {
                            acceptSuccess: "The CV has been accepted.",
                            acceptError: "An error occurred while accepting the CV.",
                            declineSuccess: "The CV has been declined.",
                            declineError: "An error occurred while declining the CV.",
                        },
                        noCv: "No CV to evaluate.",
                        view: "View CV",
                    },
                    Shared: {
                        FilterBySeason : {
                            name: 'FilterBySeason',
                            text: 'Filter by season',
                        },
                        ReturnButton : {
                            name: 'ReturnButton',
                            text: 'Return',
                        },
                    },
                    PDFoptions : {
                        download : "Download",
                        write : "Write",
                        sign : "Sign",
                        cursor : "Cursor"
                    },
                    signDialog : {
                        text : "Draw your signature above",
                        button : {
                            cancel : "Cancel",
                            confirm : "Confirm",
                        }
                    }

                },
            },
            fr: {
                translation: {
                    formField: {
                        internshipAgreement,
                        application:application.fr,
                        employerOffer:employerOffer.fr,
                        studentOffer:studentOffer.fr,
                        header,
                        contractPage:contractPage.fr,
                        notifications: {
                            noNotification: "Vous n'avez pas des notifications",
                            newOfferAvailable: "Il y a une nouvelle offre.",
                            cvAccepter: "Un CV a été approuvé.",
                            cvRefuser: "Un CV a été réfusé.",
                            studentApplyOnOffer: "Vous avez un nouveau candidat.",
                            revueCv: "Un CV est en attente d'approbation.",
                            youAreAcceptedForStage: "Vous êtes accepté pour un stage.",
                            newOfferSavedByEmployeur: "Un offre d'emploi est en attente d'approbation.",
                            contractSignedByGSForEmployer: "Un contrat a été signé par un gestionnaire des stages.",
                            contractSignedByGSForStudent: "Votre gestionnaire des stages a signé un contrat.",
                            contractSignedByStudentForEmployer: "Un candidat a signé un contrat.",
                            contractSignedByStudentForGS: "Un étudiant a signé un contrat.",
                            contractSignedByEmployerForStudent: "Un employeur a signé un contrat.",
                            contractSignedByEmployerForGS: "Un employeur a signé un contrat.",
                            newContractAsBeenCreated: "Un nouveau contrat est disponible.",
                            contractAsBeenSignedByThreeParties: "Un contrat est signé par les trois parties prenants.",
                            offerAsBeenAccepted: "Un offre de stage a été approuvé.",
                            offerAsBeenDeclined: "Un offre de stage a été refusé.",
                            newStageAsBeenCreated: "Il y a une nouvelle application de stage."
                        },
                        InternshipsAgreementPage: {
                            header:{
                                totalInternshipsAgreement: "Total",
                                acceptedInternshipsAgreement: "Acceptées",
                                declinedInternshipsAgreement: "Refusées",
                                pendingInternshipsAgreement: "Attente",
                            },
                            toast: {
                                errorFetchInternshipsAgreement: "Une erreur est survenue lors de la récupération des ententes de stage.",
                                errorFetchNumberStateOfInternshipsAgreement: "Une erreur est survenue lors de la récupération du nombre d'ententes de stage.",
                            },
                            title: "Contrats de stage",
                            internshipsAgreement: {
                                title: "Titre",
                                enterprise: "Entreprise",
                                student: "Étudiant",
                                statut: "Statut",
                                status: {
                                    accepted: "Accepté",
                                    declined: "Refusé",
                                    pending: "Attente",
                                },
                                view:{
                                    name: 'view',
                                    text: "Voir"
                                },
                                sign: {
                                    student: "Refusé par l'étudiant",
                                    employer: "Refusé par l'employeur",
                                },
                                empty : "Aucune entente de stage disponible"
                            },
                        },
                        InternshipOfferList : {
                            succesSaveOfferReview: "La revue de l'offre a été sauvegardée",
                            header: {
                                total: "Total des offres",
                                accepted: "Offres acceptées",
                                declined: "Offres refusées",
                                pending: "Offres en attente",
                                back: "Retour"
                            },
                            table: {
                                title: "Titre",
                                enterprise: "Entreprise",
                                location: "Localisation",
                                status: "Statut",
                                startDate: "Date de commencement",
                                salary: "Salaire",
                                PENDING: "Attente",
                                ACCEPTED: "Accepté",
                                DECLINED: "Refusé",
                            },
                            title: "Offres de stage",
                            toast:{
                                errorFetchOffers:"Une erreur est survenue lors de la récupération des offres.",
                                errorFetchNumberStateOfOffers:"Une erreur est survenue lors de la récupération du nombre d'offres.",
                            },
                            validation : {
                                required : 'Le commentaire de la revue de l\'offre d\'emploi est requis',
                                minLenght : 'Le commentaire de la revue de l\'offre d\'emploie doit comporter au moins 5 caractères.',
                                maxLenght : 'Le commentaire ne doit pas dépasser 20 caractères.',
                                badCharactere : 'Le commentaire contient des caractères non autorisés.'
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
                                    required : 'Le lieu est requis.',
                                    minLenght : 'La localisation doit comporter au moins 5 caractères.',
                                    maxLenght : 'La localisation ne doit pas dépasser 20 caractères.',
                                    badCharactere: "La localisation contient des caractères non autorisés."
                                }
                            },
                            program: {
                                name : 'programme',
                                text : 'Programmes :',
                                placeholder : 'Choisir un programme',
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
                                    required: 'Le salaire est requis.',
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
                                    required: 'La date de début est requise.',
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
                                scriptDetected: "Le commentaire contient un script.",
                                required: "Le commentaire est requis"
                            }
                        },
                        GSReviewOfferForm: {
                            button:{
                                refused:"Réfuser",
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
                                    required : 'Le prénom est requis.',
                                }

                            },
                            lastName: {
                                name : 'lastName',
                                text : 'Nom :',
                                placeholder : 'Pierre',
                                validation : {
                                    required : 'Le nom est requis.',
                                }
                            },
                            email: {
                                name : 'email',
                                text : 'Courriel :',
                                placeholder : 'email@email.com',
                                validation : {
                                    required : 'Le courriel est requis.',
                                }
                            },
                            password: {
                                name : 'password',
                                text : 'Mot de passe :',
                                placeholder : 'abc123',
                                validation : {
                                    required : 'Le mot de passe est requis.',
                                }
                            },
                            phone: {
                                name : 'phone',
                                text : 'Téléphone :',
                                placeholder : '514-123-4567',
                                validation : {
                                    required : 'Le téléphone est requis.',
                                    pattern: 'Exemple: 514-123-4567',
                                }
                            },
                            matricule: {
                                name : 'matricule',
                                text : 'Matricule :',
                                placeholder : '1236789',
                                validation : {
                                    pattern : 'Le matricule est compose de 7 chiffres.',
                                }
                            },
                            programme: {
                                name : 'programme',
                                text : 'Programme :',
                                placeholder : 'Choisir un programme',
                                validation : {
                                    required : 'Le programme est requis.',
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
                                    required: 'Une erreur est survenue.'
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
                                text: "Une erreur c'est produite lors de l'inscription. Veuillez réessayer."
                            },
                            titre: {
                                name: 'titre',
                                text: 'Inscription Employeur'
                            }

                        },
                        EtudiantStage: {
                            filter: {
                                title: "Filtrer par saison: ",
                                Hiver: "Hiver",
                                Été: "Été",
                                All: "Tout"
                            },
                            noOffers: {
                                name: 'noOffers',
                                text: 'Aucune offre disponible',
                                noAppliedOffers: "Aucune offre appliquée"
                            },
                            titre: {
                                name: 'titre',
                                text: 'Stages pour étudiants'
                            },
                            options: {
                                name: 'options',
                                text: 'Options',
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
                                },
                                view: {
                                    name: 'view',
                                    text: 'Voir',
                                }
                            },
                            empty: {
                                name: 'empty',
                                text: 'Aucun stage disponible'
                            },
                            toast: {
                                ErrorNoCv:"Vous devez télécharger votre CV et le définir comme par défaut avant de postuler à un stage.",
                                ErrorOfferApplication: "Une erreur est survenue lors de la postulation au stage.",
                                SuccessOfferApplication: "Votre postulation au stage a été soumise pour.",
                            },
                            cv : "Votre CV est en cours de chargement veuillez patienter...",
                        },
                        Header: {
                            stage: {
                                name: 'stage',
                                text: 'Offre stages',
                            },
                            cvReview : {
                                name: 'CV Review',
                                text: 'Revue de CV',
                            },
                            cv: {
                                name: 'CV',
                                text: 'Votre CV',
                            },
                            interview : {
                                name: 'Interview',
                                text: 'Entrevue',
                            },
                            sidebarEtudiant: {
                                offre: {
                                    name: 'offre',
                                    text: "Offres"
                                },
                                appliedOffer: {
                                    name: 'appliedOffer',
                                    text: "Offres appliquées",
                                    filter: {
                                        title: "Filtrer par session: ",
                                        Hiver: "Hiver",
                                        Été: "Été",
                                        All: "Tout"
                                    }
                                },
                                cv: {
                                    name: 'cv',
                                    text: "Votre CV"
                                },
                                stage: {
                                    name: 'stage',
                                    text: "Stage"
                                },
                                contract: {
                                    name: "contract",
                                    text: "Contrat"
                                },
                                interview : {
                                    name: 'Interview',
                                    text: 'Entrevue',
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
                                },
                                gsProfession: {
                                    name: 'gsProfession',
                                    text: "Gestionnaire de stage"
                                }
                            },
                            sidebarInternshipManager: {
                                internshipagreement: {
                                    name: 'internshipagreement',
                                    text: "Entente de stage"
                                },
                                offre: {
                                    name: 'offre',
                                    text: "Offre"
                                },
                                reviewCV: {
                                    name: 'reviewCV',
                                    text: "Revue de CV"
                                },
                            },
                            sidebarEmployeur: {
                                offre: {
                                    name: 'offre',
                                    text: "Offre"
                                },
                                interview: {
                                    name: 'interview',
                                    text: "Entrevue"
                                },
                                stage: {
                                    name: 'stage',
                                    text: "Stage"
                                },
                                internshipagreement: {
                                    name: "internshipagreement",
                                    text: "Entente de stage"
                                },
                                newOffre: {
                                    name: 'newOffre',
                                    text: "Nouvelle offre"
                                }
                            },
                            InscriptionEmployer : "Enregistrement employeur",
                            InscriptionStudent : "Enregistrement étudiant",
                        },
                        Home: {
                            name: 'home',
                            text: 'Bienvenue sur OSE, ',
                            text2: "l'application pour les stages du cégep André-Laurendeau"
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
                            filter: {
                                title: "Filtrer par saison: ",
                                Hiver: "Hiver",
                                Été: "Été",
                                All: "Tout"
                            },
                            titre: {
                                name: 'titre',
                                text: 'Tableau de bord'
                            },
                            title: {
                                name: 'title',
                                text: 'Vos offres de stage'
                            },
                            interview:{
                                name: 'interview',
                                text: 'Entrevue'
                            },
                            candidature: {
                                name: 'totalCondidature',
                                text: 'Candidature'
                            },
                            offre: {
                                name: 'totalOffre',
                                text: 'Offre'
                            },
                            view: {
                                name: 'view',
                                text: 'Voir'
                            },
                            newOffre: {
                                name: 'newOffre',
                                text: 'Nouvelle offre'
                            },
                            contract: {
                                name: 'totalContract',
                                text: 'Contrat'
                            },
                            internship: {
                                name: "internship",
                                text: "Stage"
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
                                    text: 'État'
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
                                },
                                salary: {
                                    name: "salary",
                                    text: "Salaire"
                                }
                            },
                            toast:{
                                errorFetchOffers:"Une erreur est survenue lors de la récupération des offres",
                                errorFetchNumberStateOfOffers:"Une erreur est survenue lors de la récupération du nombre d'offres.",
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
                            internship: {
                                name: "internship",
                                text: "Stage"
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
                        errorFetchOffer: "Une erreur est survenue lors de la récupération de l'offre.",
                        errorSaveOfferReview: "Une erreur est survenue lors de la sauvegarde de l'offre.",
                        succesSaveOfferReview: "L'offre a été sauvegardée.",
                        validiation:{
                            required : 'Le commentaire est requis',
                            minLenght : 'Le commentaire doit contenir au moins 10 caractères.',
                            maxLenght : 'Le commentaire doit contenir au plus 1000 caractères.',
                            scriptDetected: "Le commentaire contient un script.",
                        }
                    },
                    ErrorPage: {
                        errorMessage: {
                            name: 'errorMessage',
                            text: 'Cette page n\'existe pas.',
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
                            text: 'Inscription',
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
                            text: 'Adresse courriel',
                            placeholder: 'saisir@123.com',
                            validation: {
                                required: 'L\'adresse courriel est obligatoire.',
                                emailRequired: 'L\'adresse courriel doit être une adresse courriel valide.',
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
                        },
                        error : {
                            text : "Votre adresse courriel ou votre mot de passe est incorrect",
                        }
                    },
                    cv: CV.fr,
                    StudentInternshipPage:StudentInternshipPagei18n.fr,
                    StudentInternship: StudentInternshipI18n.fr,
                    SideBarEtudiant: SideBarEtudiantI18n.fr,
                    StudentInterview: {
                        title: {
                            name: 'title',
                            text: 'Vos entrevues',
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
                        },
                        table: {
                            name: 'table',
                            title: 'Titre',
                            location: 'Lieu de travail',
                            date: 'Date de l\'entrevue',
                            company: 'Compagnie',
                            status_text:{
                                name: 'status_text',
                                text: "Etat"
                            },
                            action: {
                                name: 'action',
                                text: 'Actions',
                                button : {
                                    accept: 'Approuver',
                                    decline: 'Refuser',
                                },
                                status:{
                                    accepted: "Approuvé",
                                    declined: "Refusé",
                                }
                            },
                            empty: "Aucune entrevue disponible",
                            noStage: "Aucun stage disponible",
                        },
                        reschedule : "Réplanifier",
                    },
                    StudentCvEvaluation: {
                        name: 'StudentCvEvaluation',
                        title: 'CV à évaluer',
                        button: {
                            accept: 'Approuver',
                            decline: 'Refuser',
                            download: "Télécharger le CV",
                        },
                        toast: {
                            acceptSuccess: "Le CV a été accepté",
                            acceptError: "Une erreur est survenue lors de l'acceptation du CV",
                            declineSuccess: "Le CV a été refusé",
                            declineError: "Une erreur est survenue lors du refus du CV",
                        },
                        noCv: "Aucun CV à évaluer",

                        view: "Voir CV",
                    },
                    Shared: {
                        FilterBySeason : {
                            name: 'FilterBySeason',
                            text: 'Filtre par saison',
                        },
                        ReturnButton : {
                            name: 'ReturnButton',
                            text: 'Retour',
                        },
                    },
                    PDFoptions : {
                        download : "Télécharger",
                        write : "Écrire",
                        sign : "Signer",
                        cursor : "Curseur"
                    },
                    signDialog : {
                        text : "Dessinez votre signature ci-dessus",
                        button : {
                            cancel : "Annuler",
                            confirm : "Confirmer",
                        }
                    }
                }
            }
        }
    });

export default i18n;