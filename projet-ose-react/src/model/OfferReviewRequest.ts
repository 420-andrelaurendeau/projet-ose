import {FileEntity} from "./FileEntity";
import {State} from "./State";

export interface OfferReviewRequest {
    id?: number;
    comment: string;
    state: String;
    internOfferId: number;
    internshipmanagerId: number;
}
