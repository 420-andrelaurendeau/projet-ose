import React, {useEffect, useState} from "react";
import { pdfjs } from "react-pdf";
import {base64ToArrayBuffer, blobToURL, downloadURI, getWidth, URLToBase64} from "./utils/Utils";
import PagingControl from "./PagingControl";
import { AddSigDialog } from "./AddSigDialog";
import PDFOptions from "./PDFOptions";
import ViewPDF from "./ViewPDF";
import {useLocation, useNavigate} from "react-router-dom";
import {employeurGetContractById, employeurSaveContract} from "../../../api/ContractAPI";
import {faSpinner} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";


pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;


function SignContract(props:any) {
  const location = useLocation();
  const stage = location.state;
  const navigate = useNavigate();
  const [contract, setContract] = useState<any>(null);
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
  const [isLoaded, setIsLoaded] = useState(false);
  const [newContent, setNewContent] = useState<any>(false);

  useEffect(() => {
    console.log(stage)
    setWidth(getWidth());
    window.addEventListener("resize", () => {
      setWidth(getWidth());
    });
  }, [isLoaded]);

  useEffect( () => {
    setIsLoaded(false)
    const getContract = async () => {
      await employeurGetContractById(stage.contractId).then(async r => {
        console.log(r)
        setContract(r)
        const pdfBytes = base64ToArrayBuffer(r.content)
        if (pdfBytes) {
          const blob = new Blob([new Uint8Array(pdfBytes)]);
          const URL: any = await blobToURL(blob);
          console.log(URL)
          setPdf(URL);
        } else setPdf(null)
        setIsLoaded(true)
      })
    }
    getContract();
    setWidth(getWidth());
  }, []);

  const submitContract = async () => {
    contract.content = URLToBase64(pdf!,contract.content)
    await employeurSaveContract(contract).then(r => {
        console.log(r)
    })
    navigate("/employer/home/contract")
  }

  return (
      isLoaded ?
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
                newContent={newContent}
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
                submitContract={submitContract}
            />
            <ViewPDF
                file={pdf} pageDetails={pageDetails} setPdf={setPdf} pdf={pdf}
                setSignatureURL={setSignatureURL} setPosition={setPosition}
                autoDate={autoDate} setTextInputVisible={setTextInputVisible}
                pageNum={pageNum} width={width} signatureURL={signatureURL}
                onEnd={setPosition} textInputVisible={textInputVisible}
                setNewContent={setNewContent} contract={contract}
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
    </div>:
          <div className="flex justify-center items-center">
            <FontAwesomeIcon icon={faSpinner}  spin size="5x" className="text-blue dark:text-orange" />
          </div>

  );
}

export default SignContract;
