import {InterOfferJob} from "./IntershipOffer";
import {FileEntity} from "./FileEntity";

export interface AppliedOffers {
    appliedOffer: InterOfferJob;
    appliedFiles: FileEntity[];
}