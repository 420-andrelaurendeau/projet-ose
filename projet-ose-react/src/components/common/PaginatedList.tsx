import React from 'react';

interface PaginatedListProps<T> {
    renderItem: JSX.Element;
    page: number;
    totalPages: number;
    onPageChange: (newPage: number) => void;
}

function PaginatedList<T>({

                              renderItem,
                              page,
                              totalPages,
                              onPageChange,
                          }: PaginatedListProps<T>) {
    return (
        <div>
            <>
                {renderItem}
            </>

            <div>
                <button onClick={() => onPageChange(page - 1)} disabled={page <= 0}>
                    Previous
                </button>
                <span>
          Page {page + 1} of {totalPages}
        </span>
                <button onClick={() => onPageChange(page + 1)} disabled={page >= totalPages - 1}>
                    Next
                </button>
            </div>
        </div>
    );
}

export default PaginatedList;
