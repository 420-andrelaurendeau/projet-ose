import {FileEntity} from "./FileEntity";
import {Programme} from "./Programme";

export interface InterOfferJob {
    id?: number;
    title: string;
    location: string;
    description: string;
    salaryByHour: number;
    startDate?: Date;
    endDate?: Date;
    internshipCandidates?: any[]; //TODO à remplacer par le bon type
    file?: FileEntity;
    programmeId: number;
    programmeNom?: String;
    employeurId: number;
    employeurNom?: String;
    employeurPrenom?: String;
    employeurEntreprise?: String;
}
