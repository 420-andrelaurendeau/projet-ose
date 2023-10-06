import { useContext } from 'react';
import {ToastContext} from "../context/ToastContext";


export const useToast = () => useContext(ToastContext);