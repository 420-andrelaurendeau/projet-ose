import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faArrowPointer, faDownload, faFileSignature, faPenNib, faPrint} from "@fortawesome/free-solid-svg-icons";
import {useAuth} from "../../../authentication/AuthContext";

function PDFOptions(props: {
    optionsClassname: string,
    selectedOption: string,
    onClick: () => void,
    onClick1: () => void,
    onClick2: () => void,
    onClick3: () => void,
    onClick4: () => void
}) {
    const {userRole } = useAuth();
    return <div className="sticky flex items-center justify-center top-20 left-0 right-0 z-[100] w-full">
        <div
            className="bg-white dark:bg-dark flex items-center p-2 rounded shadow-lg space-x-2 divide-x divide-neutral-200 dark:divide-darkgray">
            <div className="flex space-x-2">
                <div
                    className={`${props.optionsClassname} ${props.selectedOption === "none" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-200 hover:text-white dark:hover:text-white dark:hover:bg-darkgray"}`}
                    onClick={props.onClick}>
                    <FontAwesomeIcon icon={faArrowPointer} size={"lg"}/>
                </div>
                {
                    userRole === "internshipmanager" &&
                    <div
                        className={`${props.optionsClassname} ${props.selectedOption === "write" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray"}`}
                        onClick={props.onClick1}>
                        <FontAwesomeIcon icon={faPenNib} size={"lg"}/>
                    </div>
                }

                <div
                    className={`${props.optionsClassname} ${props.selectedOption === "sign" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray"}`}
                    onClick={props.onClick2}>
                    <FontAwesomeIcon icon={faFileSignature} size={"lg"}/>
                </div>
            </div>
            <div className="flex space-x-2">
                <div
                    className={`${props.optionsClassname} ${props.selectedOption === "print" ? "bg-blue dark:bg-orange text-white" : "hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray"}`}
                    onClick={props.onClick3}
                >
                    <FontAwesomeIcon icon={faPrint} size={"lg"}/>
                </div>
                <div className={`${props.optionsClassname} hover:bg-neutral-100 dark:hover:text-white dark:hover:bg-darkgray`}
                     onClick={props.onClick4}
                >
                    <FontAwesomeIcon icon={faDownload} size={"lg"}/>
                </div>
            </div>
        </div>
    </div>;
}

export default PDFOptions;