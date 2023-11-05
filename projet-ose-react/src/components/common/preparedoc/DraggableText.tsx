import Draggable from "react-draggable";
import { FaCheck, FaTimes } from "react-icons/fa";
import { cleanBorder, errorColor, goodColor, primary45 } from "./utils/colors";
import { useState, useEffect, useRef } from "react";

export default function DraggableText({ onEnd, onSet, onCancel, initialText }:any) {
  const [text, setText] = useState("Text");
  const inputRef = useRef<any>(null);

  useEffect(() => {
    if (initialText) {
      setText(initialText)
    } else {
      inputRef.current.focus();
      inputRef.current.select()
    }
  }, [])

  return (
    <Draggable onStop={onEnd}>
      <div id="childText" className="absolute top-0 z-[100000] border-2 border-gray dark:border-darkgray">
        <div className="absolute right-0 inline-block bg-white dark:bg-dark">
          <div
              className="inline-block cursor-pointer p-4"
              onClick={()=> {
                console.log('onSet', text)
                onSet(text)
              }}>
            <FaCheck color={goodColor} />
          </div>
          <div
              className="inline-block cursor-pointer p-4"
              onClick={onCancel}>
            <FaTimes color={errorColor} />
          </div>
        </div>
        <input
          ref={inputRef}
          className="border-0 text-black dark:text-white bg-transparent cursor-move p-3"
          value={text}
          placeholder={'Text'}
          onChange={(e) => setText(e.target.value)}
        />
      </div>
    </Draggable>
  );
}
