// @ts-ignore
import { InternshipOfferJob } from "./InternshipOffer";
import { User } from "./User";
export interface Stage {
    id: number;
    student: User;
    offer: InternshipOfferJob;
    stateStudent: string;
    stateEmployeur: string;
}