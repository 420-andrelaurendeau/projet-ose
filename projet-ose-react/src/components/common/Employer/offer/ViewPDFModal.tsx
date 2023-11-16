import React, {useEffect, useState} from "react";
import {base64ToArrayBuffer, blobToURL, getWidth} from "../../preparedoc/utils/Utils";
import PagingControl from "../../preparedoc/PagingControl";
import ViewPDF from "../../preparedoc/ViewPDF";
//import {useProps} from "./EmployerOfferDetails";
import {useNavigate, useParams} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSpinner} from "@fortawesome/free-solid-svg-icons";
import {useAuth} from "../../../../authentication/AuthContext";




const ViewPDFModal = (props:any) => {
    const navigate = useNavigate();
    const {id} = useParams();
    const [pdf, setPdf] = useState(null);
    const [width, setWidth] = useState(0);
    const [pageNum, setPageNum] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const {userRole} = useAuth();
    useEffect(() => {
        setWidth(getWidth());
        window.addEventListener("resize", () => {
            setWidth(getWidth());
        });
    }, []);

    useEffect(() => {
        const loadPDF = async () => {
            const pdfBytes = base64ToArrayBuffer(props.file.content)
            if (pdfBytes) {
                const blob = new Blob([new Uint8Array(pdfBytes)]);
                const URL: any = await blobToURL(blob);
                setPdf(URL);
            } else setPdf(null)
        }
        loadPDF();
    }, []);

    return (
        // modal
        <div className="flex justify-center items-center min-h-screen max-md:pt-24">
            <div className={`${props.ismodal ? "fixed z-50 top-0 left-0 bg-black dark:bg-gray bg-opacity-50 dark:bg-opacity-80": "rounded bg-white dark:bg-dark"} w-full h-full p-12`}>
                <div className="overflow-y-auto h-full">
                    {
                        props.ismodal &&
                        <div className="fixed top-10 left-10 p-3 z-[60]">
                            <button
                                type="button"
                                className="inline-flex items-center px-4 py-2 border border-transparent shadow-sm text-sm font-medium rounded-md bg-red hover:bg-black focus:bg-black text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-neutral-500"
                                onClick={() => {
                                    props.setIsModalOpen(false);
                                }}
                            >
                                Close
                            </button>
                        </div>
                    }
                    <div id="container" className="m-auto h-full">
                        {pdf ? (
                            <ViewPDF
                                file={pdf} setPdf={setPdf} pdf={pdf}
                                pageNum={pageNum} width={width}
                                onLoadSuccess={(data: any) => {
                                    setTotalPages(data.numPages);
                                }}
                            />
                        ) :
                        <div className="flex justify-center items-center h-full">
                            <FontAwesomeIcon icon={faSpinner}  spin size="5x" className="text-blue dark:text-orange" />
                        </div>
                        }
                    </div>
                    <div className="fixed bottom-0 left-0 right-0">
                        <PagingControl
                            pageNum={pageNum}
                            setPageNum={setPageNum}
                            totalPages={totalPages}
                        />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ViewPDFModal;