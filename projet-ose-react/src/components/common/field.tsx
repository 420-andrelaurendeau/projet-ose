import Validator from "./validators/Validator";
import "./field.css";
import {useState} from "react";

function Field(fieldName: string, fieldType: string, fieldId: string, fieldValidator?: Validator) {
    const [empty, setEmpty] = useState(true);
    const [messages, setMessages] = useState(new Array<string>())
    return (
        <>
            <label htmlFor={fieldId} className={`inline-block relative pl-2 text-sm font-medium transition-all ${empty ? "empty" : ""} ${messages.length > 0 ? "text-red-500" : ""}`} >
                {fieldName}
            </label>
            <input id={fieldId}
                   name={fieldId}
                   type={fieldType}
                   className={`appearance-none block w-full rounded-md py-2 pl-2 shadow-sm sm:text-sm sm:leading-6 ${messages.length > 0 ? "text-red-500 outline-red-500" : "text-black"}`}
                   onBlur={(e) => {setMessages(fieldValidator?.validate(e) ?? []); setEmpty(e.target.value == "")}}
                   onChange={(e) => setEmpty(e.target.value == "")}
                   onClick={(e) => setEmpty(false)} />
            {messages.map(e => <p className="pl-2 text-red-500">{e}</p>)}
        </>
    );
}


export default Field;