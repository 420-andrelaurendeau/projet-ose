import {useEffect, useRef, useState} from "react";
import { Document, Page, pdfjs } from "react-pdf";
import { PDFDocument, rgb } from "pdf-lib";
import { fileToDataURL,blobToURL } from "./utils/Utils";
import PagingControl from "./PagingControl";
import { AddSigDialog } from "./AddSigDialog";
import { BigButton } from "./BigButton";
import DraggableSignature from "./DraggableSignature";
import dayjs from "dayjs";
import pdff from './pdf/Internshipe_Contract_Contract.pdf';
import DraggableText from "./DraggableText";


pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

function downloadURI(uri:any, name:any) {
  var link = document.createElement("a");
  link.download = name;
  link.href = uri;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

type Props = {
    pdfBase64: String;
    signContract: Function;
}

function SignContract( {pdfBase64, signContract}: Props) {
  const styles = {
    container: {
      margin: "0 auto",
    },
    sigBlock: {
      display: "inline-block",
      border: "1px solid #000",
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
  const [width, setWidth] = useState<number>(0);
  const [pageDetails, setPageDetails] = useState({
    height: 0,
    width:0,
    originalHeight: 0,
    originalWidth: 0,
  });
  const [changePosition, setChangePosition] = useState<boolean>(false);
  const documentRef = useRef<any>(null);

  function click(e:any) {
    // e = Mouse click event.
    const parent = document.getElementById("parent");
    if (parent) {
      const parentRect = parent.getBoundingClientRect();
      let x = e.clientX - parentRect.left; //x position within the element.
      let y = e.clientY - parentRect.top;  //y position within the element.
      console.log("X : " + x + " ; Y : " + y + ".");
      setChangePosition(true)
      return {'x': x,'y':y}
    }
  }
  const getWidth = ():number => {
    const container = document.getElementById("container");
    if (container) {
        return container.clientWidth;
    }
    return 0;
  }

  useEffect(() => {
    setWidth(getWidth());
    window.addEventListener("resize", () => {
      setWidth(getWidth());
    });
  }, []);

  useEffect( () => {
    const getPDF = async () => {

      const pdfBytes = await fetch(pdff).then((response) => response.arrayBuffer());
      const blob = new Blob([new Uint8Array(pdfBytes)]);
      const URL:any = await blobToURL(blob);
      setPdf(URL);
    }
    getPDF();
  }, []);

  function getOnSetText(text:any)  {
    return async () => {
      console.log('getOnSetText', text)
      const {height, width, originalHeight, originalWidth} = pageDetails;
      const scale = width / documentRef.current.clientWidth;
      console.log(1)

      if (pdf) {
        const pdfDoc = await PDFDocument.load(pdf);
        const pages = pdfDoc.getPages();
        const firstPage = pages[pageNum];

        const child = document.getElementById("childText");
        const parent = document.getElementById("parent");
        let offsetX = 0;
        let offsetY = 0;
        if (child && parent) {
          const childRect = child.getBoundingClientRect();
          const parentRect = parent.getBoundingClientRect();

          offsetX = (childRect.left - parentRect.left);
          offsetY = height - (childRect.top - parentRect.top);
        }

        const newOffsetX = offsetX * originalWidth / width;
        const newOffsetY = offsetY * originalHeight / height;

        firstPage.drawText(text, {
          x: newOffsetX,
          y: newOffsetY,
          size: 20 * scale,
        });

        const pdfBytes = await pdfDoc.save();
        const blob = new Blob([new Uint8Array(pdfBytes)]);

        const URL: any = await blobToURL(blob);
        setPdf(URL);
        setPosition(null);
        setTextInputVisible(false);
      }
    }
  }

  function getOnSet() {
    return async () => {
      const {height, width, originalHeight, originalWidth} = pageDetails;
      const scale = width / documentRef.current.clientWidth;
      if (pdf && signatureURL) {
        const pdfDoc = await PDFDocument.load(pdf);
        const pages = pdfDoc.getPages();
        const firstPage = pages[pageNum];
        const pngImage = await pdfDoc.embedPng(signatureURL);
        const pngDims = pngImage.scale(scale * .3);

        const child = document.getElementById("child");
        const parent = document.getElementById("parent");
        let offsetX = 0;
        let offsetY = 0;
        if (child && parent) {
          const childRect = child.getBoundingClientRect();
          const parentRect = parent.getBoundingClientRect();

          offsetX = (childRect.left - parentRect.left);
          offsetY = height - (childRect.top - parentRect.top) - pngDims.height;
        }

        const newOffsetX = offsetX * originalWidth / width;
        const newOffsetY = offsetY * originalHeight / height;

        console.log(newOffsetX + ', ' + newOffsetY)

        firstPage.drawImage(pngImage, {
          x: newOffsetX,
          y: newOffsetY,
          width: pngDims.width,
          height: pngDims.height,
        });

        if (autoDate) {
          firstPage.drawText(
              `Signed ${dayjs().format(
                  "M/d/YYYY HH:mm:ss "
              )}`,
              {
                x: newOffsetX,
                y: newOffsetY - 10,
                size: 14 * scale,
                color: rgb(0.074, 0.545, 0.262),
              }
          );
        }

        const pdfBytes = await pdfDoc.save();
        const blob = new Blob([new Uint8Array(pdfBytes)]);

        const URL: any = await blobToURL(blob);
        setPdf(URL);
        setPosition(null);
        setSignatureURL(null);
      }

    };
  }

  return (
    <div>
      <div id="container" style={styles.container}>
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
            <div style={styles.controls} className=" mx-auto">
              {!signatureURL ? (
                <BigButton
                    marginRight={8}
                    title={"Add signature"}
                    onClick={() => setSignatureDialogVisible(true)}                />
              ) : null}
              <BigButton
                  marginRight={8}
                  title={"Add Date"}
                  onClick={() => setTextInputVisible("date")}
              />

              <BigButton
                  marginRight={8}
                  title={"Add Text"}
                  onClick={() => setTextInputVisible(true)}
              />
              {pdf ? (
                <BigButton
                    marginRight={8}
                    inverted={true}
                    title={"Download"}
                    onClick={() => {
                      downloadURI(pdf, "file.pdf");
                    }}              />
              ) : null}
              <BigButton
                  marginRight={8}
                  title={"Validate signature"}
                  onClick={() => signContract(pdf)}
              />
            </div>
            <div ref={documentRef} >
              <Document
                className="mt-8 w-full"
                file={pdf}
                onLoadSuccess={(data) => {
                  setTotalPages(data.numPages);
                }}
              >
                <div id="parent" className={"relative" }>
                  <Page
                      onClick={click}
                      className="relative border border-[#999]"
                      renderTextLayer={false}
                      renderAnnotationLayer={false}
                      pageNumber={pageNum + 1}
                      width={width}
                      onLoadSuccess={(data:any) => {
                        setPageDetails(data);
                        console.log(data)
                      }}
                  >
                    {signatureURL ? (
                        <DraggableSignature
                            url={signatureURL}
                            onCancel={() => {
                              setSignatureURL(null);
                            }}
                            onSet={getOnSet()}
                            onEnd={setPosition}
                        />
                    ) : null}
                    {textInputVisible ? (
                        <DraggableText
                            initialText={
                              textInputVisible === "date"
                                  ? dayjs().format("M/d/YYYY")
                                  : null
                            }
                            onCancel={() => setTextInputVisible(false)}
                            onEnd={setPosition}
                            onSet={getOnSetText}
                        />
                    ) : null}
                  </Page>
                </div>
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

export default SignContract;
