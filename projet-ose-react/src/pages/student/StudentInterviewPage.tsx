import React, {useEffect, useRef, useState} from "react";
import {fetchInterviews, fetchInterviewsCountForStudent} from "../../api/StudentApi";
import {getUser} from "../../api/UtilisateurAPI";
import {getStudentAppliedOffers, offresEtudiant} from "../../api/InterOfferJobAPI";
import {useAuth} from "../../authentication/AuthContext";
import {Interview} from "../../model/Interview";

export default function StudentInterviewPage() {

    const [user, setUser] = useState<any>(null);
    const [interviewsNb, setInterviewsNb] = React.useState<Interview[]>([]);
    const isLoading = useRef(false);
    const auth = useAuth();

    useEffect(() => {
        const fetchUser = async () => {
            isLoading.current = true;

            getUser(auth.userEmail!)
                .then((resUser) => {
                    setUser(resUser);
                    console.log(resUser);
                    fetchInterviews(resUser.id).then((res) => {
                        setInterviewsNb(res);
                        console.log(interviewsNb);
                    });
                })
                .catch((err) => {
                    console.log(err);
                })
                .finally(() => (isLoading.current = false));
        };
        if (!isLoading.current) fetchUser();
    }, []);
    return (
        <div>
            <h1>StudentInterviewPage</h1>
        </div>
    );
}