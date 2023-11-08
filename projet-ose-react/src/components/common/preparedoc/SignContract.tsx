import {useEffect, useState} from "react";
import { pdfjs } from "react-pdf";
import {blobToURL, downloadURI, getWidth} from "./utils/Utils";
import PagingControl from "./PagingControl";
import { AddSigDialog } from "./AddSigDialog";
import pdff from './pdf/Internshipe_Contract_Contract.pdf';
import PDFOptions from "./PDFOptions";
import ViewPDF from "./ViewPDF";


pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;


function SignContract() {
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
  const [selectedOption, setSelectedOption] = useState<string>("none");
  const optionsClassname = "flex justify-center items-center h-10 w-10 dark:text-white rounded cursor-pointer";

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

  return (
    <div>
      <div id="container" className="m-auto">
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
          <div className="relative">
            <PDFOptions
                optionsClassname={optionsClassname} selectedOption={selectedOption}
                onClick={() => setSelectedOption('none')}
                onClick1={() => {
                  setTextInputVisible(true)
                  setSelectedOption('write')
                }}
                onClick2={() => {
                  setSignatureDialogVisible(true)
                  setSelectedOption('sign')
                }}
                onClick3={() => {
                  setSelectedOption('print')
                }}
                onClick4={() => {
                  downloadURI(pdf, 'contract.pdf')
                }}
            />
            <ViewPDF
                file={pdf} pageDetails={pageDetails} setPdf={setPdf} pdf={pdf}
                setSignatureURL={setSignatureURL} setPosition={setPosition}
                autoDate={autoDate} setTextInputVisible={setTextInputVisible}
                pageNum={pageNum} width={width} signatureURL={signatureURL}
                onEnd={setPosition} textInputVisible={textInputVisible}
                setSelectedOption={setSelectedOption}
                onLoadSuccess={(data: any) => {
                  setTotalPages(data.numPages);
                }}
                onLoadSuccess1={(data: any) => {
                  setPageDetails(data);
                  console.log(data)
                }}
                onCancel={() => {
                  setSignatureURL(null);
                  setSelectedOption('none')
                }}
                onCancel1={() => {
                  setTextInputVisible(false)
                  setSelectedOption('none')
                }}
              />
            <div className="fixed bottom-0 left-0 right-0">
              <PagingControl
                  pageNum={pageNum}
                  setPageNum={setPageNum}
                  totalPages={totalPages}
              />
            </div>
          </div>
        ) : null}
      </div>
    </div>
  );
}

export default SignContract;
