import {File} from "./File";

export interface InternshipOffer {
    id?: number;
    title: string;
    location: string;
    description: string;
    salaryByHour: number;
    startDate?: Date;
    endDate?: Date;
    internshipCandidates?: any[]; //TODO à remplacer par le bon type
    file?: File;
    state: string;
    programmeId: number;
    programmeNom?: String;
    employeurId: number;
    employeurNom?: String;
    employeurPrenom?: String;
    employeurEntreprise?: String;
}
