import {useAuth} from "../../../authentication/AuthContext";
import React, {useEffect, useRef, useState} from "react";
import {getUser} from "../../../api/UtilisateurAPI";
import {useToast} from "../../../hooks/state/useToast";
import {useTranslation} from "react-i18next";
import {
    getStageByEmployeurId,
    getStageByStudentId,
    getStageCountByStateStudent
} from "../../../api/InternshipManagerAPI";
import {useNavigate} from "react-router-dom";
import InternshipManagerInternshipsAgreement
    from "../internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreement";
import InternshipManagerInternshipsAgreementDashoardHeader
    from "../internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreementDashoardHeader";
import PaginatedList from "../shared/paginationList/PaginatedList";
import {getAllSeasons} from "../../../api/InterOfferJobAPI";
import {useProps} from "../../../pages/employer/EmployeurHomePage";

export default function EmployerContractsPage() {

    const [user, setUser] = useState<any>(null)
    const [isUpdate, setIsUpdate] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(100)
    const [internshipAgreements, setInternshipsAgreement] = useState([]);
    const [state, setState] = useState(undefined);
    const [sortField, setSortField] = useState("id");
    const [sortDirection, setSortDirection] = useState("desc");
    const auth = useAuth();
    const toast = useToast();
    const internshipAgreementRef = useRef(false);
    const {t} = useTranslation();
    const [totalInternshipsAgreement, setTotalInternshipsAgreement] = useState(0);
    const [totalApprouved, setTotalApprouved] = useState(0);
    const [totalPending, setTotalPending] = useState(0);
    const [totalDeclined, setTotalDeclined] = useState(0);
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.InternshipsAgreementPage");
    //const [seasons,setSeasons] = useState([])
    //const [selectedOption, setSelectedOption] = useState('');

    const { selectedOption, setSelectedOption, seasons, setSeasons } = useProps();

    const navigate = useNavigate();

    const fetchInternshipsAgreement = async (id: number) => {
        try {
            internshipAgreementRef.current = true
            console.log("DATA")
            console.log(selectedOption)
            const response = await getStageByEmployeurId({
                page: currentPage,
                size: numberElementByPage,
                sortField: sortField,
                sortDirection: sortDirection,
                session: selectedOption
            }, id);
            console.log("REPSONSE!!")
            console.log(response)
            let seasons1 = await getAllSeasons()
            setSeasons(seasons1)
            setInternshipsAgreement(response.content);
            setTotalPages(response.totalPages);
        } catch (error) {
            console.log(error);
            toast.error("Error fetching internship agreement data")
        } finally {
            setIsUpdate(false);
            internshipAgreementRef.current = false;
        }
    };

    useEffect(() => {
        const fetch = async () => {
            fetchInternshipsAgreement(auth.userId!)
        }
        fetch()
    }, [selectedOption]);
    const handleOptionChange = async (event: any) => {
        const selected = event.target.value;
        setSelectedOption(selected);
    };

    const handleChangePage = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setCurrentPage(0);
        setNumberElementByPage(Number(event.target.value));
    };

    const handlePageChange = (newPage: number) => {
        setCurrentPage(newPage);
    };


    useEffect(() => {
        getUser(auth.userEmail!).then((res) => {
            setUser(res);
            fetchInternshipsAgreement(res.id).then((internshipAgreements) => {
                console.log("fetched success")
            })
        }).catch((error) => {
            toast.error(`Error fetching user data: ${error}`)
        })
    }, []);


    useEffect(() => {
        if (user) {
            fetchInternshipsAgreement(user.id).then((internshipAgreements) => {
                console.log("fetched success")
            })

        }
    }, [currentPage, state, numberElementByPage, isUpdate, sortField, sortDirection])

    const handleOfferClick = (id: number) => {
        console.log(id)
        navigate(`/employer/home/internshipagreement/${id}`);
    }
    const handleChangeStateSort = (state: any) => {
        setState(state);
        setCurrentPage(0);
    };

    const renderOffer = <InternshipManagerInternshipsAgreement user={user} offers={internshipAgreements}
                                                               isUpdate={setIsUpdate} sortField={sortField}
                                                               setsortField={setSortField}
                                                               setSortDirection={setSortDirection}
                                                               sortDirection={sortDirection}
                                                               handleOfferClick={handleOfferClick}/>;


    return (
        <div className="max-md:pt-24">
            <title>Offres</title>
            <header className=" pb-4">
                <h1 className="xxxs:text-2xl sm:text-3xl font-bold text-gray-900 dark:text-offwhite">{fields.title}</h1>
            </header>
            <main className="pb-4">
                <div className="p-0">
                    <PaginatedList
                        renderItem={renderOffer}
                        page={currentPage}
                        totalPages={totalPages}
                        onPageChange={handlePageChange}
                        numberElement={numberElementByPage}
                        handleChangeNumberElement={handleChangePage}
                        selectedOption={selectedOption}
                        seasons={seasons}
                        handleOptionChange={handleOptionChange}

                    />
                </div>
            </main>
        </div>
    );
};
