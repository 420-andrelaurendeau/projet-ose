import { Dialog } from "./Dialog";
import SignatureCanvas from "react-signature-canvas";
import { ConfirmOrCancel } from "./ConfirmOrCancel";
import { primary45 } from "./utils/colors";
import { useRef } from "react";

export function AddSigDialog({ onConfirm, onClose, autoDate, setAutoDate }:any) {
  const sigRef = useRef<any>(null);

  return (
    <Dialog
        isVisible={true}
        title={"Add signature"}
        body={
      <div className="mx-auto">
          <div className="flex justify-center">
            <div className="inline-block border border-gray dark:border-darkgray">
              <SignatureCanvas
                  velocityFilterWeight={1}
                  ref={sigRef}
                  canvasProps={{
                    width: "600",
                    height: 200,
                    className: "sigCanvas",
                  }}/>
            </div>
          </div>
          <div className="flex justify-between text-center text-blue dark:text-orange mt-8 w-2/3 self-center">
            <div className="flex justify-center">
              <div>Draw your signature above</div>
            </div>
          </div>

          <ConfirmOrCancel
              onCancel={onClose}
              onConfirm={() => {
                const sigURL = sigRef.current!.toDataURL();
                onConfirm(sigURL);
              }}/>
        </div>} onClose={undefined} noPadding={undefined} backgroundColor={undefined} positionTop={undefined}
        style={undefined}    />
  );
}
