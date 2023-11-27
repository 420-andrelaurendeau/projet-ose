import PaginatedList from "../paginationList/PaginatedList";
import React, {useRef, useState} from "react";
import i18n from "i18next";
import {Interview} from "../../../../model/Interview";
import {useAuth} from "../../../../authentication/AuthContext";

const Notifications = () => {

    const fields = i18n.getResource(i18n.language.slice(0, 2), "translation", "StudentInterview");
    const [user, setUser] = useState<any>(null);
    const [interviews, setInterviews] = React.useState<Interview[]>([]);
    const isLoading = useRef(false);
    const auth = useAuth();
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);
    const [numberElementByPage, setNumberElementByPage] = useState<number>(5);

    const renderInterviews = (
        <main className={"pb-4"}>
            <table className=" w-full divide-y divide-gray dark:divide-darkgray">
                <thead className="bg-blue dark:bg-orange ">
                <tr>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >
                        {fields.table.title}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >
                        {fields.table.location}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >

                        {fields.table.date}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >

                        {fields.table.company}
                    </th>
                    <th
                        scope="col"
                        className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                    >

                        {fields.table.action.text}
                    </th>
                </tr>
                </thead>
                <tbody className="bg-white text-black divide-y divide-gray dark:bg-darkgray dark:divide-darkgray">
                {interviews.length === 0 && (
                    <tr>
                        <td colSpan={5} className="text-center bg-red text-white my-auto">
                            {fields.table.empty}
                        </td>
                    </tr>
                )}
                {interviews.map((interview) => (
                    <tr key={interview.id}>
                        <td className="px-6 py-4 whitespace-nowrap
                                        text-center text-sm font-medium">
                            {interview.internOffer.title}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap
                                        text-center text-sm font-medium">
                            {interview.internOffer.location}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap
                                        text-center text-sm font-medium">
                            {new Date(Date.parse(interview.date)).toISOString().split('T')[0]}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap
                                        text-center text-sm font-medium">
                            {interview.internOffer.employeurEntreprise}
                        </td>
                        <td className="px-6 py-4 whitespace-nowrap">
                            <div className="flex justify-center space-x-2">
                                <button
                                    disabled={interview.state != "PENDING"}
                                    onClick={() => handleOnAccept(interview.id)}
                                    className="disabled:bg-gray text-white bg-green hover:bg-green-700 px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    {fields.table.action.button.accept}
                                </button>
                                <button
                                    disabled={interview.state != "PENDING"}
                                    onClick={() => handleOnDecline(interview.id)}
                                    className="disabled:bg-gray text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-medium"
                                >
                                    {fields.table.action.button.decline}
                                </button>
                                {interview.state === "ACCEPTED" &&
                                    <p className="text-white bg-blue hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.accepted}</p>}
                                {interview.state === "DECLINED" &&
                                    <p className="text-white bg-red hover:bg-red-700 px-3 py-2 rounded-md text-sm font-bold">{fields.table.action.status.declined}</p>}
                            </div>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </main>
    )

    return (
        <div className="dark:bg-softdark">
            <div className="flex flex-col items-center">
                <div className=" lg:-mx-8 mt-28 w-11/12 ">
                    <div
                        className=" md:z-50 md:top-0 md:left-0 justify-center md:w-full md:h-full md:flex md:p-3 max-md:w-full dark:text-white ">
                        <div className=" w-full">
                            <PaginatedList renderItem={renderInterviews}
                                           page={currentPage}
                                           totalPages={totalPages}
                                           onPageChange={handlePageChange}
                                           numberElement={numberElementByPage}
                                           handleChangeNumberElement={handleChangeNbElement}
                                           selectedOption=""
                                           handleOptionChange={() => {}}
                                           seasons={["",""]}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Notifications;