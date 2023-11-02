import {useEffect, useRef, useState} from "react";
import { Document, Page, pdfjs } from "react-pdf";
import { PDFDocument, rgb } from "pdf-lib";
import { fileToDataURL,blobToURL } from "./utils/Utils";
import PagingControl from "./components/PagingControl";
import { AddSigDialog } from "./components/AddSigDialog";
import { Header } from "./Header";
import { BigButton } from "./components/BigButton";
import DraggableSignature from "./components/DraggableSignature";
import dayjs from "dayjs";
import pdff from './TP4-1-Tripwire.pdf';
import pdf from "./pdf-lib_modification_example.pdf";


pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

function downloadURI(uri:any, name:any) {
  var link = document.createElement("a");
  link.download = name;
  link.href = uri;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

function Test() {
  const styles = {
    container: {
      maxWidth: 900,
      margin: "0 auto",
    },
    sigBlock: {
      display: "inline-block",
      border: "1px solid #000",
    },
    documentBlock: {
      maxWidth: 800,
      margin: "20px auto",
      marginTop: 8,
      border: "1px solid #999",
    },
    controls: {
      maxWidth: 800,
      margin: "0 auto",
      marginTop: 8,
    },
  };
  const [pdf, setPdf] = useState(null);
  const [autoDate, setAutoDate] = useState(true);
  const [signatureURL, setSignatureURL] = useState(null);
  const [position, setPosition] = useState<any>(null);
  const [signatureDialogVisible, setSignatureDialogVisible] = useState(false);
  const [textInputVisible, setTextInputVisible] = useState<any>(false);
  const [pageNum, setPageNum] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [pageDetails, setPageDetails] = useState({
    originalHeight: 0,
    originalWidth:0
  });
  const documentRef = useRef<any>(null);

  function click(e:any) {
    // e = Mouse click event.
    let rect = e.target.getBoundingClientRect();
    let x = e.clientX - rect.left; //x position within the element.
    let y = e.clientY - rect.top;  //y position within the element.
    console.log("X : " + x + " ; Y : " + y + ".");
    return {'x': x,'y':y}
  }

  useEffect( () => {
    const getPDF = async () => {

      const pdfBytes = await fetch(pdff).then((response) => response.arrayBuffer());
      const blob = new Blob([new Uint8Array(pdfBytes)]);
      const URL:any = await blobToURL(blob);
      setPdf(URL);
    }
    getPDF();
  }, []);

  return (
    <div>
      <Header />
      <div style={styles.container}>
        {signatureDialogVisible ? (
          <AddSigDialog
            autoDate={autoDate}
            setAutoDate={setAutoDate}
            onClose={() => setSignatureDialogVisible(false)}
            onConfirm={(url:any) => {
              setSignatureURL(url);
              setSignatureDialogVisible(false);
            }}
          />
        ) : null}
        {pdf ? (
          <div>
            <div style={styles.controls}>
              {!signatureURL ? (
                <BigButton
                    marginRight={8}
                    title={"Add signature"}
                    onClick={() => setSignatureDialogVisible(true)} inverted={undefined} fullWidth={undefined}
                    customFillColor={undefined} customWhiteColor={undefined} style={undefined} noHover={undefined}
                    id={undefined} small={undefined} disabled={undefined}                />
              ) : null}
              {pdf ? (
                <BigButton
                    marginRight={8}
                    inverted={true}
                    title={"Download"}
                    onClick={() => {
                      downloadURI(pdf, "file.pdf");
                    }} fullWidth={undefined} customFillColor={undefined} customWhiteColor={undefined} style={undefined}
                    noHover={undefined} id={undefined} small={undefined} disabled={undefined}                />
              ) : null}
            </div>
            <div ref={documentRef} style={styles.documentBlock}>
              {signatureURL ? (
                <DraggableSignature
                  url={signatureURL}
                  onCancel={() => {
                    setSignatureURL(null);
                  }}
                  onSet={async (e:any) => {
                    const { originalHeight, originalWidth } = pageDetails;
                    const scale = originalWidth / documentRef.current.clientWidth;
                    console.log(position.x + ', ' + position.y)
                    console.log(documentRef)
                    console.log(documentRef.current.clientWidth + ', ' + documentRef.current.clientHeight)
                    const y =
                      documentRef.current.clientHeight -
                      (position.y -
                        position.offsetY +
                        64 -
                        documentRef.current.offsetTop);
                    const x =
                      position.x -
                      160 -
                      position.offsetX -
                      documentRef.current.offsetLeft;

                    // new XY in relation to actual document size
                    const newY =
                      (y * originalHeight) / documentRef.current.clientHeight;
                    const newX =
                      (x * originalWidth) / documentRef.current.clientWidth;

                    const pdfDoc = await PDFDocument.load(pdf);

                    const pages = pdfDoc.getPages();
                    const firstPage = pages[pageNum];

                    const pngImage = await pdfDoc.embedPng(signatureURL);
                    const pngDims = pngImage.scale( scale * .3);

                    firstPage.drawImage(pngImage, {
                      x: newX,
                      y: newY,
                      width: pngDims.width,
                      height: pngDims.height,
                    });

                    if (autoDate) {
                      firstPage.drawText(
                        `Signed ${dayjs().format(
                          "M/d/YYYY HH:mm:ss ZZ"
                        )}`,
                        {
                          x: newX,
                          y: newY - 10,
                          size: 14 * scale,
                          color: rgb(0.074, 0.545, 0.262),
                        }
                      );
                    }

                    const pdfBytes = await pdfDoc.save();
                    const blob = new Blob([new Uint8Array(pdfBytes)]);

                    const URL:any = await blobToURL(blob);
                    setPdf(URL);
                    setPosition(null);
                    setSignatureURL(null);
                  }}
                  onEnd={setPosition}
                />
              ) : null}
              <Document
                className="w-full overflow-x-hidden"
                file={pdf}
                onLoadSuccess={(data) => {
                  setTotalPages(data.numPages);
                }}
              >
                <Page
                    onClick={click}
                    className="w-full flex justify-start "
                  renderTextLayer={false}
                  pageNumber={pageNum + 1}
                  width={800}
                  onLoadSuccess={(data:any) => {
                    setPageDetails(data);
                  }}
                />
              </Document>
            </div>
            <PagingControl
              pageNum={pageNum}
              setPageNum={setPageNum}
              totalPages={totalPages}
            />
          </div>
        ) : null}
      </div>
    </div>
  );
}

export default Test;
