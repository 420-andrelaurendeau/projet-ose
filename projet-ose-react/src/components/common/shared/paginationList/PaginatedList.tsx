import React from 'react';
import ListItemCountSelector from "./ListItemCountSelector";
import ListItemPageSelector from "./ListItemPageSelector";

interface PaginatedListProps<T> {
    renderItem: JSX.Element;
    page: number;
    totalPages: number;
    onPageChange: (newPage: number) => void;
    numberElement: number;
    handleChangeNumberElement: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

function PaginatedList<T>({
                              renderItem,
                              page,
                              totalPages,
                              onPageChange,
                              numberElement,
                              handleChangeNumberElement,
                          }: PaginatedListProps<T>) {

    return (
        <div>
            <ListItemCountSelector
                numberElement={numberElement}
                handleChangeNumberElement={handleChangeNumberElement}
            />

            {renderItem}

            <ListItemPageSelector page={page} totalPages={totalPages} onPageChange={onPageChange}/>
        </div>
    );
}


export default PaginatedList;
