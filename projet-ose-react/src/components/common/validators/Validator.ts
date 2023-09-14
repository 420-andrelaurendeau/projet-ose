import {ChangeEvent} from "react";

abstract class Validator {
    abstract validate(event: ChangeEvent): string[];
}

export default Validator;