import {useRef} from "react";
import {PDFDocument, rgb} from "pdf-lib";
import {blobToURL} from "./utils/Utils";
import dayjs from "dayjs";
import {Document, Page, pdfjs } from "react-pdf";
pdfjs.GlobalWorkerOptions.workerSrc = `//unpkg.com/pdfjs-dist@${pdfjs.version}/legacy/build/pdf.worker.min.js`;
import DraggableSignature from "./DraggableSignature";
import DraggableText from "./DraggableText";


function ViewPDF(props: any) {
    const documentRef = useRef<any>(null);

    function getOnSetText(text:any)  {
        return async () => {
            console.log('getOnSetText', text)
            const {height, width, originalHeight, originalWidth} = props.pageDetails;
            const scale = width / documentRef.current.clientWidth;
            console.log(1)

            if (props.pdf) {
                const pdfDoc = await PDFDocument.load(props.pdf);
                const pages = pdfDoc.getPages();
                const firstPage = pages[props.pageNum];

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
                const newOffsetY = offsetY * originalHeight / height - (15 * scale);

                firstPage.drawText(text, {
                    x: newOffsetX,
                    y: newOffsetY,
                    size: 10 * scale,
                });

                const pdfBytes = await pdfDoc.save();
                const blob = new Blob([new Uint8Array(pdfBytes)]);

                const URL: any = await blobToURL(blob);
                props.setPdf(URL);
                props.setPosition(null);
                props.setTextInputVisible(false);
                props.setSelectedOption("none")
            }
        }
    }

    function getOnSet() {
        return async () => {
            const {height, width, originalHeight, originalWidth} = props.pageDetails;
            const scale = width / documentRef.current.clientWidth;
            if (props.pdf && props.signatureURL) {
                const pdfDoc = await PDFDocument.load(props.pdf);
                const pages = pdfDoc.getPages();
                const firstPage = pages[props.pageNum];
                const pngImage = await pdfDoc.embedPng(props.signatureURL);
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

                if (props.autoDate) {
                    firstPage.drawText(
                        `Signed ${dayjs().format(
                            "M/d/YYYY HH:mm:ss "
                        )}`,
                        {
                            x: newOffsetX,
                            y: newOffsetY - 10,
                            size: 14 * scale,
                            color: rgb(0, 0, 0),
                        }
                    );
                }

                const pdfBytes = await pdfDoc.save();
                const blob = new Blob([new Uint8Array(pdfBytes)]);

                const URL: any = await blobToURL(blob);
                props.setPdf(URL);
                props.setPosition(null);
                props.setSignatureURL(null);
            }

        };
    }

    return <div ref={documentRef}>
        <Document
            className="w-full"
            file={props.file}
            onLoadSuccess={props.onLoadSuccess}
        >
            <div id="parent" className={"relative"}>
                <Page
                    className="relative"
                    renderTextLayer={false}
                    renderAnnotationLayer={false}
                    pageNumber={props.pageNum + 1}
                    width={props.width}
                    onLoadSuccess={props.onLoadSuccess1}
                >
                    {props.signatureURL ? (
                        <DraggableSignature
                            url={props.signatureURL}
                            onCancel={props.onCancel}
                            onSet={getOnSet()}
                            onEnd={props.onEnd}
                            setOption={props.setSelectedOption}
                        />
                    ) : null}
                    {props.textInputVisible ? (
                        <DraggableText
                            onCancel={props.onCancel1}
                            onEnd={props.onEnd}
                            onSet={(text:any) => getOnSetText(text)}
                        />
                    ) : null}
                </Page>
            </div>
        </Document>
    </div>;
}

export default ViewPDF;