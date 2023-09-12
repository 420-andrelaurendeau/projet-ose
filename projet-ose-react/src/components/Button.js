import PropTypes from 'prop-types'
import { Button } from "@material-tailwind/react";
const Buttons = ({color, text, onClick, disabled}) => {
    return (
        <Button
            onClick={onClick}
            style={{backgroundColor: color }}
            disabled={disabled}
            className={'btn text-white'}>{text}</Button>
    )
}

Buttons.defaultProps = {
    color: 'steelblue'
}

//impt pour importer les PropTypes
Buttons.propTypes = {
    text: PropTypes.string,
    color: PropTypes.string,
    onClick: PropTypes.func
}

export default Buttons