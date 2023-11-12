import {useAuth} from "../../../authentication/AuthContext";
import {useEffect, useState} from "react";
import {getUser} from "../../../api/UtilisateurAPI";
import {useToast} from "../../../hooks/state/useToast";
import {useTranslation} from "react-i18next";

export default function StudentContractPage() {

    const [user, setUser] = useState<any>(null)
    const auth = useAuth();
    const toast  = useToast();

    const {t} = useTranslation();



    useEffect(() => {
        let useEffectUser = null;

        getUser(auth.userEmail!).then((res) => {
            setUser(res);
        }).catch((error) => {
            toast.error(`Error fetching user data: ${error}`)
        })
    }, []);
    return (
        <div>
            works
        </div>
    )
}