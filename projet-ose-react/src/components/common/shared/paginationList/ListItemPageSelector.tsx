import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faAngleLeft, faAngleRight} from "@fortawesome/free-solid-svg-icons";
import React from "react";

interface listItemPageSelectorProps {
    page: number;
    totalPages: number;
    onPageChange: (newPage: number) => void;
}

const listItemPageSelector: React.FC<listItemPageSelectorProps> = ({page, totalPages, onPageChange}) => {

    const getPaginationGroup = () => {
        let start = Math.max(page - 2, 0);
        let end = Math.min(page + 3, totalPages);


        while ((end - start) < 5 && end < totalPages) {
            end++;
        }
        while ((end - start) < 5 && start > 0) {
            start--;
        }

        const range = [];
        for (let i = start; i < end; i++) {
            range.push(i);
        }
        return range;
    };

    const paginationGroup = getPaginationGroup();


    return (
        <div className="font-light flex space-x-1">

            <button
                className={`${page === 0 ? 'cursor-not-allowed font opacity-50' : 'cursor-pointer hover:text-offwhite hover:bg-blue'} dark:hover:bg-orange border-gray rounded w-7 h-7 flex items-center justify-center`}
                onClick={() => onPageChange(page - 1)}
                disabled={page === 0}
            >
                <FontAwesomeIcon icon={faAngleLeft} className="dark:text-offwhite"/>
            </button>

            {page > 2 && totalPages > 5 && (
                <>
                    <button
                        className="cursor-pointer hover:bg-blue dark:hover:bg-orange dark:text-offwhite font-medium hover:font-bold hover:text-offwhite border-gray rounded w-7 h-7 flex items-center justify-center"
                        onClick={() => onPageChange(0)}
                    >
                        1
                    </button>
                    {page > 3 &&
                        <div className="w-7 h-7 flex items-center justify-center dark:text-offwhite">
                            ...
                        </div>
                    }
                </>
            )}

            {paginationGroup.map((pageIndex) => (
                <button
                    key={pageIndex}
                    className={`${pageIndex === page ? 'bg-blue dark:bg-orange font-bold dark:font-bold text-offwhite' : 'hover:bg-blue hover:font-bold font-medium  cursor-pointer dark:text-offwhite'} hover:text-offwhite  dark:hover:bg-orange border-gray rounded w-7 h-7 flex items-center justify-center`}
                    onClick={() => onPageChange(pageIndex)}
                    disabled={pageIndex === page}
                >
                    {pageIndex + 1}
                </button>
            ))}

            {page < totalPages - 3 && totalPages > 5 && (
                <>
                    {page < totalPages - 4 &&
                        <div className="w-7 h-7 flex items-center justify-center dark:text-offwhite">
                            ...
                        </div>
                    }
                    <button
                        className="cursor-pointer hover:bg-blue font-medium dark:hover:bg-orange hover:font-bold hover:text-offwhite dark:text-offwhite  border-gray rounded w-7 h-7 flex items-center justify-center"
                        onClick={() => onPageChange(totalPages - 1)}
                    >
                        {totalPages}
                    </button>
                </>
            )}

            <button
                className={` ${page >= totalPages - 1 ? 'cursor-not-allowed opacity-50' : 'cursor-pointer hover:bg-blue hover:text-offwhite'}  dark:hover:bg-orange border-gray rounded w-7 h-7 flex items-center justify-center`}
                onClick={() => onPageChange(page + 1)}
                disabled={page >= totalPages - 1}
            >
                <FontAwesomeIcon icon={faAngleRight} className="dark:text-offwhite"/>
            </button>
        </div>
    )
}

export default listItemPageSelector;