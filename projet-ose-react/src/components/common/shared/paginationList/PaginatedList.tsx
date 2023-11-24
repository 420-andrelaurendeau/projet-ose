import React from 'react';
import ListItemCountSelector from "./ListItemCountSelector";
import ListItemPageSelector from "./ListItemPageSelector";
import {useTranslation} from "react-i18next";

interface PaginatedListProps<T> {
    renderItem: JSX.Element;
    page: number;
    totalPages: number;
    onPageChange: (newPage: number) => void;
    numberElement: number;
    handleChangeNumberElement: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    selectedOption: string;
    handleOptionChange: (event: React.ChangeEvent<HTMLSelectElement>) => void;
    seasons: string[];

}

function PaginatedList<T>({
                              renderItem,
                              page,
                              totalPages,
                              onPageChange,
                              numberElement,
                              handleChangeNumberElement,
                              selectedOption,
                              handleOptionChange,
                              seasons
                          }: PaginatedListProps<T>) {

    const {t} = useTranslation();

    return (
        <div>
            <ListItemCountSelector
                numberElement={numberElement}
                handleChangeNumberElement={handleChangeNumberElement}
            />
            <div>
                <label htmlFor="options" className="text-bold dark:text-white">{t("Shared.FilterBySeason.text")}</label>
                <select id="options" value={selectedOption} onChange={handleOptionChange}>
                    <option value="">Tout</option>
                    {seasons.map((season: string, index: number) => (
                        <option key={index} value={season}>
                            {season}
                        </option>
                    ))}
                </select>
            </div>

            {renderItem}

            <ListItemPageSelector page={page} totalPages={totalPages} onPageChange={onPageChange}/>
        </div>
    );
}


export default PaginatedList;
