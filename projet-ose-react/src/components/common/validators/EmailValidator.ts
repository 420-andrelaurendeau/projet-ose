import Validator from "./Validator";
import {ChangeEvent} from "react";

class EmailValidator extends Validator {
    validate(event: ChangeEvent<HTMLInputElement>): string[] {
        const errors: string[] = [];

        let value = event.target.value;
        if (value.length === 0) {
            errors.push("L'adresse email est obligatoire");
        } else if (!value.match(/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i)) {
            errors.push("L'adresse email est invalide");
        }

        console.log(errors)

        return errors;
    }
}

export default EmailValidator;