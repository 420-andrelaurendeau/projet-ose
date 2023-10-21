import React, {ChangeEventHandler} from "react";
interface ListItemCountSelectorProps {
    numberElement: number;
    handleChangeNumberElement: ChangeEventHandler<HTMLSelectElement>;
}

const ListItemCountSelector: React.FC<ListItemCountSelectorProps> = ({numberElement, handleChangeNumberElement}) => {
    return (
            <div className="flex justify-end pr-4">
                <label className="">
                    <select className="border rounded dark:text-offwhite dark:bg-dark dark:border-dark"
                            value={numberElement} onChange={handleChangeNumberElement}>
                        <option value={2}>2</option>
                        <option value={5}>5</option>
                        <option value={10}>10</option>
                        <option value={20}>20</option>
                    </select>
                </label>
            </div>
    );
}


export default ListItemCountSelector;