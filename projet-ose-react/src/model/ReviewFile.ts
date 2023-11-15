import {User} from "./User";

export interface ReviewFile {
    id: number;
    fileName: string;
    content: string;
    isAccepted: string;
    etudiant?: User;
    defaultFile?: boolean;
}