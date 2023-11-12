import React, { useRef } from "react";
import Draggable from "react-draggable";
import { FaCheck, FaTimes } from "react-icons/fa";
import { errorColor, goodColor } from "./utils/colors";

export default function DraggableSignature({ url, onEnd, onSet, onCancel, setOption }:any) {
    const draggableRef = useRef(null);

    return (
        <Draggable nodeRef={draggableRef} onStop={onEnd}>
            <div
                id={"child"}
                className="absolute top-0 z-50 "
                ref={draggableRef}
            >
                <div className="flex justify-center bg-white dark:bg-dark border-gray border-2 w-28 rounded dark:border-darkgray shadow">
                    <div
                        className="inline-block cursor-pointer p-3 my-1 rounded hover:bg-neutral-200 dark:hover:bg-darkgray"
                        onClick={() => {
                            setOption("none");
                            onSet();
                        }}
                    >
                        <FaCheck color={goodColor} />
                    </div>
                    <div
                        className="inline-block cursor-pointer p-3 my-1 rounded hover:bg-neutral-200 dark:hover:bg-darkgray"
                        onClick={onCancel}
                    >
                        <FaTimes color={errorColor} />
                    </div>
                </div>
                <div className="flex justify-center border border-blue dark:border-orange mt-2">
                    <div className="inline-block cursor-move">
                        <img src={url} width={200} draggable={false} />
                    </div>
                </div>
            </div>
        </Draggable>
    );
}

