import React, { useState } from 'react';
import {data} from "autoprefixer";

const FilterBySeasons = (props:any) => {
    const [selectedOption, setSelectedOption] = useState(''); // State to store the selected option
    const offers = props.data


    const handleOptionChange = (event:any) => {
        setSelectedOption(event.target.value); // Update the selected option in the state
    };

    return (
        <div>
            <label htmlFor="options">Select an option:</label>
            <select id="options" value={selectedOption} onChange={handleOptionChange}>
                <option value="">Select an option</option>
                {offers.map((offer:any) => (
                    <option key={offer.id} value={offer.value}>{offer.title}</option>
                ))}
            </select>
            <p>Selected option: {selectedOption}</p>
        </div>
    );
};

export default FilterBySeasons;