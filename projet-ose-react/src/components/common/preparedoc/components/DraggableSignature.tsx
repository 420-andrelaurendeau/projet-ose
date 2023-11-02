import React, { useRef } from "react";
import Draggable from "react-draggable";
import { FaCheck, FaTimes } from "react-icons/fa";
import { errorColor, goodColor } from "../utils/colors";

export default function DraggableSignature({ url, onEnd, onSet, onCancel }:any) {
    const draggableRef = useRef(null);

    return (
        <Draggable nodeRef={draggableRef} onStop={onEnd}>
            <div
                className="absolute z-50 border-2 border-[hsl(218,49%,66%)]"
                ref={draggableRef}
            >
                <div className="absolute right-0 inline-block bg-[hsl(218,49%,66%)]">
                    <div
                        className="inline-block cursor-pointer p-4"
                        onClick={onSet}
                    >
                        <FaCheck color={goodColor} />
                    </div>
                    <div
                        className="inline-block cursor-pointer p-4"
                        onClick={onCancel}
                    >
                        <FaTimes color={errorColor} />
                    </div>
                </div>
                <img src={url} width={200} draggable={false} />
            </div>
        </Draggable>
    );
}

