import { BigButton } from "./BigButton";
import React from "react";
import {useTranslation} from "react-i18next";

export function ConfirmOrCancel({
  onCancel,
  onConfirm,
  confirmTitle = "Confirm",
  leftBlock,
  hideCancel,
  disabled
}:any) {
    const { t } = useTranslation();

  return (
    <div className="flex justify-between">
      <div>{leftBlock}</div>
      <div>
        {!hideCancel ? (
          <BigButton
              title={t("signDialog.button.cancel")}
              className="mr-8"
              onClick={onCancel}/>
        ) : null}
        <BigButton title={t("signDialog.button.confirm")} inverted={true} onClick={onConfirm} disabled={disabled}/>
      </div>
    </div>
  );
}
