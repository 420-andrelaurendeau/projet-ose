// @ts-ignore
import { InternshipOfferJob } from "./InternshipOffer";
import { User } from "./User";
export interface Interview {
    id: number;
    student: User;
    internOffer: InternshipOfferJob;
    date: string;
    description: string;
    state: number;
}