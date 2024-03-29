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
            <div className="flex justify-between">
                <div>
                    <label htmlFor="options" className="text-bold dark:text-white">
                        {t("formField.Header.sidebarEtudiant.appliedOffer.filter.title")}
                    </label>
                    <select className="rounded border border-black dark:border-white dark:bg-dark dark:text-white" id="options" value={selectedOption} onChange={handleOptionChange}  defaultValue={selectedOption}>
                        <option value="">{t("formField.Header.sidebarEtudiant.appliedOffer.filter.All")}</option>
                        {seasons.map((season: string, index: number) => (
                            <option key={index} value={season}>
                                {t("formField.Header.sidebarEtudiant.appliedOffer.filter."+season.slice(0, -4)) + " " + season.slice(-4)}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="pb-4">
                    <ListItemCountSelector
                        numberElement={numberElement}
                        handleChangeNumberElement={handleChangeNumberElement}
                    />
                </div>
            </div>

            {renderItem}

            <ListItemPageSelector page={page} totalPages={totalPages} onPageChange={onPageChange}/>
        </div>
    );
}


export default PaginatedList;
