import { BigButton } from "./BigButton";
import React from "react";

export function ConfirmOrCancel({
  onCancel,
  onConfirm,
  confirmTitle = "Confirm",
  leftBlock,
  hideCancel,
  disabled
}:any) {

  return (
    <div className="flex justify-between">
      <div>{leftBlock}</div>
      <div>
        {!hideCancel ? (
          <BigButton
              title={"Cancel"}
              className="mr-8"
              onClick={onCancel}/>
        ) : null}
        <BigButton title={confirmTitle} inverted={true} onClick={onConfirm} disabled={disabled}/>
      </div>
    </div>
  );
}
