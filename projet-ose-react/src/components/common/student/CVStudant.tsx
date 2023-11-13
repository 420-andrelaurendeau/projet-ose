import React from "react";
import {useProps} from "../../../pages/student/StudentInternshipPage";

const CVStudant = () => {
    const useprops = useProps();
    console.log(useprops.user);
    return (
        <div className="py-6 max-md:pt-24 ">
            <div className="flex items-center justify-between space-x-2">
                <h2 className="text-lg font-bold">
                            <span
                                className="px-6 py-2 inline-flex text-lg leading-5  rounded-full justify-center bg-blue dark:bg-orange text-white dark:text-offwhite"
                            >
                                {useprops.user.cv.fileName}
                            </span>
                </h2>
                <div className="flex gap-2">
                    <button
                        type="button"
                        className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md text-neutral-900 bg-white hover:bg-neutral-50 dark:bg-dark dark:hover:bg-black dark:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                        onClick={() => {}}
                    >
                        Change CV
                    </button>
                </div>
            </div>
        </div>
    );
}

export default CVStudant;