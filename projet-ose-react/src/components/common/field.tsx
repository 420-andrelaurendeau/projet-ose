import Validator from "./validators/Validator";

function Field(fieldName: string, fieldType: string, fieldId: string, fieldValidator?: Validator) {
    return (
        <div className="flex flex-col">
            <input id={fieldId}
                   name={fieldId}
                   type={fieldType}
                   className="appearance-none  block w-full bg-white rounded-md py-2 text-blue shadow-sm sm:text-sm sm:leading-6 pl-2"
                   placeholder={fieldName}
                   onBlur={(e) => fieldValidator?.validate(e)}/>
        </div>
    );
}


export default Field;