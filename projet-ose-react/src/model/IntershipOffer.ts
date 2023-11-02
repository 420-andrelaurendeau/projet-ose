import {FileEntity} from "./FileEntity";

export interface InternshipOffer {
    id?: number;
    title: string;
    location: string;
    description: string;
    salaryByHour: number;
    startDate?: Date;
    endDate?: Date;
    internshipCandidates?: any[]; //TODO à remplacer par le bon type
    file?: FileEntity;
    state: string;
    offerReviewRequestId?: number;
    programmeId: number;
    programmeNom?: String;
    employeurId: number;
    employeurNom?: String;
    employeurPrenom?: String;
    employeurEntreprise?: String;
}
