import { BigButton } from "./BigButton";
import {primary45} from "./utils/colors";

export default function PagingControl({totalPages, pageNum, setPageNum}:any) {
  return (
    <div className="mt-8 mb-8">
      <div className="flex justify-center items-center">
        <BigButton
          title={"<"}
          onClick={() => setPageNum(pageNum - 1)}
          disabled={pageNum-1===-1}
        />
        <div className="px-8 py-8 text-blue dark:text-orange text-xs">
          Page: {pageNum + 1}/{totalPages}
        </div>
        <BigButton
          title={">"}
          onClick={() => setPageNum(pageNum + 1)}
          disabled={pageNum+1>totalPages-1}
        />
      </div>
    </div>
  );
}
