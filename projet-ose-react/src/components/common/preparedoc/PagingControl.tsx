import { BigButton } from "./BigButton";
import {primary45} from "./utils/colors";

export default function PagingControl({totalPages, pageNum, setPageNum}:any) {
  return (
    <div className="">
      <div className="flex justify-center items-center">
        <button
            onClick={() => setPageNum(pageNum - 1)}
            disabled={pageNum-1===-1}
        >
          <div className="rounded px-4 py-2 bg-blue dark:bg-orange text-lg border border-neutral-200 dark:border-darkergray text-white">
            {"<"}
          </div>
        </button>
        <div className="px-8 dark:text-orange text-xs">
          Page: {pageNum + 1}/{totalPages}
        </div>
        <button
            onClick={() => setPageNum(pageNum + 1)}
            disabled={pageNum+1>totalPages-1}
        >
          <div className="rounded px-4 py-2 bg-blue dark:bg-orange text-lg border border-neutral-200 dark:border-darkergray text-white">
            {">"}
          </div>
        </button>
      </div>
    </div>
  );
}
