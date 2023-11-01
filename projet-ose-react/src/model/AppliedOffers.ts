import {InternshipOffer} from "./IntershipOffer";
import {File} from "./File";

export interface AppliedOffers {
    appliedOffer: InternshipOffer;
    appliedFiles: File[];
}