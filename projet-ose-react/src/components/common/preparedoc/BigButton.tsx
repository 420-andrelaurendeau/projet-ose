import React from "react";
import { primary45 } from "./utils/colors";
import useHover from "./hooks/useHover";

export function BigButton({
  title,
  onClick,
  inverted,
  fullWidth,
  customFillColor,
  customWhiteColor,
  noHover,
  id,
  small,
  disabled,
  marginRight,
}:any) {
  const [hoverRef, isHovered] = useHover();

  let fillColor = customFillColor || primary45;
  const whiteColor = customWhiteColor || "#FFF";

  let initialBg = null;
  let hoverBg = fillColor;

  let initialColor = fillColor;
  let hoverColor = whiteColor;

  if (inverted) {
    initialBg = fillColor;
    hoverBg = null;
    initialColor = whiteColor;
    hoverColor = fillColor;
  }

  if (disabled) {
    initialBg = "#ddd";
    hoverBg = "#ddd";
    fillColor = "#ddd";
  }

  const containerClasses = [
    'inline-flex',
    'items-center',
    'justify-center',
    fullWidth ? 'w-full' : null,
    isHovered && !noHover ? `bg-${hoverBg}` : `bg-${initialBg}`,
    isHovered && !noHover && !disabled ? `text-${hoverColor}` : disabled ? 'text-gray-400' : `text-${initialColor}`,
    'rounded-lg',
    small ? 'text-sm' : null,
    `border-${fillColor}`,
    !disabled ? 'cursor-pointer' : null,
    'select-none',
    'box-border',
    marginRight,
  ];

  return (
    <div
      id={id}
      ref={hoverRef as any}
      className={containerClasses.filter((c) => c).join(' ') + " bg-blue mx-4" }
      onClick={() => {
        if (!disabled) {
          onClick();
        }
      }}
    >
      {title}
    </div>
  );
}
