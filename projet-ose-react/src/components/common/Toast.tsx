import React, {FC, useEffect, useRef} from "react";

import {useToast} from "../../hooks/state/useToast";
import {faCircleXmark} from "@fortawesome/free-solid-svg-icons/faCircleXmark";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faCircleCheck,
    faCircleExclamation,
    faExclamation,
    faTriangleExclamation
} from "@fortawesome/free-solid-svg-icons";


interface ToastProps {
    message: string;
    type: 'success' | 'warning' | 'info' | 'error';
    id: number;
}

interface ToastType {
    icon: JSX.Element;
    backgroundColor: string;
    borderColor: string;
    textColor: string;
}

interface ToastHook {
    remove: (id: number) => void;
}

const toastTypes: Record<string, ToastType> = {
    success: {
        icon: <FontAwesomeIcon icon={faCircleCheck} className="h-5 w-5"/>,
        backgroundColor: "bg-green",
        borderColor: "border-green",
        textColor: "text-white",
    },
    warning: {
        icon: <FontAwesomeIcon icon={faTriangleExclamation} className="h-5 w-5"/>,
        backgroundColor: "bg-yellow",
        borderColor: "border-yellow",
        textColor: "text-white",
    },
    info: {
        icon: <FontAwesomeIcon icon={faCircleExclamation} className="h-5 w-5"/>,
        backgroundColor: "bg-blue",
        borderColor: "border-blue",
        textColor: "text-white",
    },
    error: {
        icon: <FontAwesomeIcon icon={faExclamation} className="h-5 w-5"/>,
        backgroundColor: "bg-red-100",
        borderColor: "border-red-500",
        textColor: "text-red-800",
    },
};

const Toast: FC<ToastProps> = ({message, type, id}) => {
    const {icon,backgroundColor, borderColor, textColor} = toastTypes[type];
    const toast = useToast() as ToastHook;
    const timerID = useRef<NodeJS.Timeout | null>(null);
    const [isExiting, setIsExiting] = React.useState(false);

    const handleDismiss = () => {
        setIsExiting(true);
        setTimeout(() => {
            toast.remove(id);
        }, 500);
    };


    useEffect(() => {
        timerID.current = setTimeout(() => {
            handleDismiss();
        }, 1000);

        return () => {
            if (timerID.current) {
                clearTimeout(timerID.current);
            }
        };
    }, []);

    return (
        <div
            className={`fixed top-10 right-4 z-50 p-4 rounded border-l-4 ${backgroundColor} ${borderColor} ${textColor} shadow-lg w-72 ${isExiting ? 'animate-slideOutRight' : 'animate-slideInDown'}`}>
            <div className="flex justify-between items-start">
                <div className={`rounded p-1 ${textColor} hover:bg-opacity-30 hover:${backgroundColor}`}>
                    {icon}
                </div>

                <p className="text-lg font-bold">{message}</p>
                <button
                    className={`rounded p-1 ${textColor} hover:bg-opacity-30 hover:${backgroundColor}`}
                    onClick={handleDismiss}
                >
                    <FontAwesomeIcon icon={faCircleXmark} className="h-5 w-5"/>
                </button>
            </div>
        </div>
    );
};

export default Toast;
