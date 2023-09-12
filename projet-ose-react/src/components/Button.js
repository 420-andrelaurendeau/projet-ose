import PropTypes from 'prop-types'

const Button = ({color, text, onClick, disabled}) => {
    return (
        <button
            onClick={onClick}
            style={{backgroundColor: color }}
            disabled={disabled}
            className={'btn text-white'}>{text}</button>
    )
}

Button.defaultProps = {
    color: 'steelblue'
}

//impt pour importer les PropTypes
Button.propTypes = {
    text: PropTypes.string,
    color: PropTypes.string,
    onClick: PropTypes.func
}

export default Button