import React, { useEffect, useRef, useState } from 'react';
import { usePdf } from '@mikecousins/react-pdf';
import { PDFDocument, rgb } from 'pdf-lib'; // Import pdf-lib for PDF modification
import pdf from './pdf-lib_modification_example.pdf';

const DocViewer: React.FC = () => {
    const [page, setPage] = useState(1);
    const canvasRef = useRef(null);
    const [modifiedPdfBytes, setModifiedPdfBytes] = useState<any>(null); // To store the modified PDF

    const { pdfDocument, pdfPage } = usePdf({
        file: pdf,
        page,
        canvasRef,
    });

    // Function to add text to a PDF page
    const addTextToPdf = async () => {

        const pdfBytes = await fetch(pdf).then((response) => response.arrayBuffer());
        const toBlob = new Blob([pdfBytes], { type: 'application/pdf' });
        const pdfDoc = await PDFDocument.load(pdfBytes,{ ignoreEncryption: true });
        const [pdfPage] = pdfDoc.getPages();

        const helveticaFont = await pdfDoc.embedFont('Helvetica');
        pdfPage.drawText('Added Text', {
            x: 100,
            y: 100,
            size: 12,
            font: helveticaFont,
            color: rgb(0, 0, 0),
        });

        const modifiedPdfBytes = await pdfDoc.save();
        setModifiedPdfBytes(modifiedPdfBytes);
        console.log(modifiedPdfBytes);
    };

    return (
        <div>
            {!pdfDocument && <span>Loading...</span>}
            <canvas ref={canvasRef} />
            {Boolean(pdfDocument && pdfDocument.numPages) && (
                <nav>
                    <ul className="pager">
                        <li className="previous">
                            <button disabled={page === 1} onClick={() => setPage(page - 1)}>
                                Previous
                            </button>
                        </li>
                        <li className="next">
                            <button
                                disabled={page === pdfDocument!.numPages}
                                onClick={() => setPage(page + 1)}
                            >
                                Next
                            </button>
                        </li>
                    </ul>
                </nav>
            )}
            {pdfDocument && (
                <button onClick={addTextToPdf}>Add Text to PDF</button>
            )}
            {modifiedPdfBytes && (
                <a
                    href={`data:application/pdf;base64,${btoa(
                        String.fromCharCode.apply(null, modifiedPdfBytes)
                    )}`}
                    download="modified.pdf"
                >
                    Download Modified PDF
                </a>
            )}
        </div>
    );
};

export default DocViewer;
