import {FileEntity} from "./FileEntity";
import {Programme} from "./Programme";

export interface InterOfferJob {
    id?: number; // Si vous avez un champ d'identifiant
    title: string;
    location: string;
    description: string;
    salaryByHour: number;
    startDate?: Date;
    endDate?: Date;
    internshipCandidates?: any[]; //TODO à remplacer par le bon type
    programme?: Programme;
    file?: FileEntity,
}
