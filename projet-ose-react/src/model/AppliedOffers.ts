import {InternshipOffer} from "./IntershipOffer";
import {FileEntity} from "./FileEntity";

export interface AppliedOffers {
    appliedOffer: InternshipOffer;
    appliedFiles: FileEntity[];
}