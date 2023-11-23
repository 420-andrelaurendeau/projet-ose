import React from "react";
import {Buffer} from "buffer";
import ViewPDFModal from "../Employer/offer/ViewPDFModal";


//TODO DELETE? doesnt seem to be used
const CVStudant = (props:any) => {
    console.log(props.file)
    const getFileSize = (): string => {
        let sizeInBytes = 0
        if (props.file.content){
            sizeInBytes = Buffer.from(props.file.content).length;
            if (sizeInBytes < 1000) {
                return sizeInBytes + " B";
            }else if (sizeInBytes < 1000000) {
                return sizeInBytes / 1000 + " KB";
            }else if (sizeInBytes < 1000000000) {
                return Math.round(sizeInBytes / 10000) / 100 + " MB";
            }
        }
        return sizeInBytes + " B";
    }
    return (
        props.file &&
        <div className="justify-center pb-16">
            <div className="py-6 max-md:pt-24 ">
                <div className="flex items-center justify-between space-x-2">
                    <h2 className="text-lg font-bold">
                                <span
                                    className="px-6 py-2 inline-flex text-lg leading-5  rounded-full justify-center bg-blue dark:bg-orange text-white dark:text-offwhite"
                                >
                                    {props.file.fileName}
                                </span>
                    </h2>
                    <div className="flex gap-2">
                        <button
                            type="button"
                            className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                            onClick={() => {props.setChangeCV(true)}}
                        >
                            Change CV
                        </button>
                    </div>
                </div>
            </div>
            <ViewPDFModal
                ismodal={false}
                file={props.file}
            />
        </div>
    );
}

export default CVStudant;