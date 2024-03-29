import {useAuth} from "../../../authentication/AuthContext";
import React, {useEffect, useRef, useState} from "react";
import {getUser} from "../../../api/UtilisateurAPI";
import {useToast} from "../../../hooks/state/useToast";
import {useTranslation} from "react-i18next";
import {
    getStageByStudentId,
    getStageCountByStateStudent
} from "../../../api/InternshipManagerAPI";
import {useNavigate} from "react-router-dom";
import InternshipManagerInternshipsAgreement
    from "../internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreement";
import InternshipManagerInternshipsAgreementDashoardHeader
    from "../internshipManager/internshipsAgreement/InternshipManagerInternshipsAgreementDashoardHeader";
import PaginatedList from "../shared/paginationList/PaginatedList";
import {selectOptions} from "@testing-library/user-event/dist/select-options";
import {getAllSeasons} from "../../../api/InterOfferJobAPI";


const getActualSeason = () => {
    const currentDate = new Date();
    const currentYear = currentDate.getFullYear();
    const currentMonth = currentDate.getMonth();
    let session = '';

    if (currentMonth >= 5 && currentMonth <= 8) {
        session = 'Été';
    } else if (currentMonth >= 9 || currentMonth <= 1) {
        session = 'Automne';
    } else {
        session = 'Hiver';
    }

    if (session === 'Été' || session === 'Automne') {
        return `Hiver${currentYear + 1}`;
    } else {
        return `Été${currentYear}`;
    }
}
export default function StudentContractPage() {

    //TODO Create test for shared components

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
    const [seasons,setSeasons] = useState([])
    const [selectedOption, setSelectedOption] = useState(getActualSeason());

    const navigate = useNavigate();
    const handleTotalOffersByState = async (id: number) => {
        const responseTotal = await getStageCountByStateStudent(id);
        setTotalInternshipsAgreement(0);
        setTotalApprouved(0);
        setTotalPending(0);
        setTotalDeclined(0);

        if (responseTotal["PENDING"])
            setTotalPending(responseTotal["PENDING"]);

        if (responseTotal["ACCEPTED"])
            setTotalApprouved(responseTotal["ACCEPTED"])

        if (responseTotal["DECLINED"])
            setTotalDeclined(responseTotal["DECLINED"])

        if (responseTotal["TOTAL"])
            setTotalInternshipsAgreement(responseTotal["TOTAL"]-(responseTotal["IRRELEVANT"] ? responseTotal["IRRELEVANT"] : 0));
    }
    const fetchInternshipsAgreement = async (id: number) => {
        try {
            handleTotalOffersByState(id);
            internshipAgreementRef.current = true
            console.log("DATA")
            const response = await getStageByStudentId({
                page: currentPage,
                size: numberElementByPage,
                state: state,
                sortField: sortField,
                sortDirection: sortDirection,
                session:selectedOption
            }, id);
            console.log("REPSONSE!!")
            console.log(response)
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

    const handleOptionChange = async (event: any) => {
        const selected = event.target.value;

        console.log(selected)
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


        const fetchSeasons = async () => {
            let response = await getAllSeasons()
            setSeasons(response);
        };

        fetchSeasons()
    }, []);


    useEffect(() => {
        if (user) {
            fetchInternshipsAgreement(user.id).then((internshipAgreements) => {
                console.log("fetched success")
            })

        }
    }, [currentPage, state, numberElementByPage, isUpdate, sortField, sortDirection, selectedOption])

    const handleOfferClick = (id: number) => {
        console.log(id)
        navigate(`/student/home/internshipagreement/${id}`);
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
        <div className="max-md:pt-16">
            <title>Offres</title>
            <header className="pb-4">
                <h1 className=" mt-7 xxxs:text-2xl sm:text-3xl font-bold text-gray-900 dark:text-offwhite">{fields.title}</h1>
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
                        handleOptionChange={handleOptionChange}
                        seasons={seasons}
                    />
                </div>
            </main>
        </div>
    );
};
