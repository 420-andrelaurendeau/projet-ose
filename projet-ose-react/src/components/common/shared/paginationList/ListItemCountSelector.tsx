import React, {ChangeEventHandler} from "react";
import {useTranslation} from "react-i18next";
interface ListItemCountSelectorProps {
    numberElement: number;
    handleChangeNumberElement: ChangeEventHandler<HTMLSelectElement>;
}

const ListItemCountSelector: React.FC<ListItemCountSelectorProps> = ({numberElement, handleChangeNumberElement}) => {

    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "formField.ListItemCounterSelector");

    return (
            <div className="flex justify-end pr-4">
                    <p className="dark:text-white">{fields.numberItem}</p>
                    <select className="border rounded dark:text-offwhite dark:bg-dark dark:border-gray ml-4"
                            value={numberElement} onChange={handleChangeNumberElement}>
                        <option value={2}>2</option>
                        <option value={5}>5</option>
                        <option value={10}>10</option>
                        <option value={20}>20</option>
                    </select>
            </div>
    );
}


export default ListItemCountSelector;