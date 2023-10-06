import {useEffect, useRef} from "react";
import {useToast} from "../../hooks/state/useToast";


const toastTypes = {
    success: {
        icon: <></>,
        iconClass: "success-icon",
        progressBarClass: "success",
    },
    warning: {
        icon: <></>,
        iconClass: "warning-icon",
        progressBarClass: "warning",
    },
    info: {
        icon: <></>,
        iconClass: "info-icon",
        progressBarClass: "info",
    },
    error: {
        icon: <></>,
        iconClass: "error-icon",
        progressBarClass: "error",
    },
};
// @ts-ignore
const Toast = ({message, type, id}) => {
    // @ts-ignore
    const {icon, iconClass, progressBarClass} = toastTypes[type];
    const toast = useToast()
    const timerID = useRef(null); // create a Reference

    const handleDismiss = () => {
        toast.remove(id);
    };

    useEffect(() => {
        // @ts-ignore
        timerID.current = setTimeout(() => {
            handleDismiss();
        }, 1000000);

        return () => {
            // @ts-ignore
            clearTimeout(timerID.current);
        };
    }, []);

    return (
        <div className="toast fixed top-4 left-4 z-50 bg-blue">
            <span className={iconClass}>{icon}</span>
            <p className="toast-message">{message}</p>
            <button className="dismiss-btn" onClick={handleDismiss}></button>

            {/* Toast Progress Bar */}
            <div className="toast-progress">
                <div className={`toast-progress-bar ${progressBarClass}`}></div>
            </div>
        </div>

    )
}

export default Toast;