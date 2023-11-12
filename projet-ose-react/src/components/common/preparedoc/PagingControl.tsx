import { BigButton } from "./BigButton";
import {primary45} from "./utils/colors";

export default function PagingControl({totalPages, pageNum, setPageNum}:any) {
  return (
    <div className="mt-8 mb-8 ">
      <div className="flex justify-center items-center ">
        <div className="bg-white dark:bg-dark flex p-2 rounded shadow-lg">
        <button
          onClick={() => setPageNum(pageNum - 1)}
          disabled={pageNum-1===-1}
        >
          <div className="rounded px-4 py-2 bg-blue dark:bg-orange text-lg border border-neutral-200 dark:border-darkergray text-white">
            {"<"}
          </div>
        </button>
        <div className="px-8 py-2 dark:text-white ">
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
    </div>
  );
}
