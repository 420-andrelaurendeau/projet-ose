import Draggable, { DraggableEventHandler } from "react-draggable";
import {FaCheck, FaTimes} from "react-icons/fa";
import {cleanBorder, errorColor, goodColor, primary45} from "./utils/colors";
import {useState, useEffect, useRef, MouseEventHandler, Dispatch} from "react";

export default function DraggableText(props:any) {
  const [text, setText] = useState("Text");
  const inputRef = useRef<any>(null);
  const draggableRef = useRef(null);


  return (
    <Draggable nodeRef={draggableRef} onStop={props.onEnd}>
      <div id="childText" className="absolute top-0 z-[100000] " ref={draggableRef}>
        <div className="flex justify-center bg-white dark:bg-dark border-gray border-2 w-28 rounded dark:border-darkgray shadow">
          <div
              className="inline-block cursor-pointer p-3 my-1 rounded hover:bg-neutral-200 dark:hover:bg-darkgray"
              onClick={props.onSet(text)}>
            <FaCheck color={goodColor} />
          </div>
          <div
              className="inline-block cursor-pointer p-3 my-1 rounded hover:bg-neutral-200 dark:hover:bg-darkgray"
              onClick={props.onCancel}>
            <FaTimes color={errorColor} />
          </div>
        </div>
        <input
          ref={inputRef}
          className=" text-black bg-transparent cursor-move p-3 mt-2 border border-blue dark:border-orange"
          value={text}
          placeholder={'Text'}
          onChange={(e) => setText(e.target.value)}
        />
      </div>
    </Draggable>
  );
}
