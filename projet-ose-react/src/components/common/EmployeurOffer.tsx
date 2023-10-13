import {useTranslation} from "react-i18next";

export default function EmployeurOffer(props:any) {
    const {i18n} = useTranslation();
    const fields = i18n.getResource(i18n.language.slice(0,2),"translation","formField.homeEmployeur.offerTable");
    console.log(props.offers);
    return (
        <div className="flex flex-col mt-14">
            <div className=" xs:-mx-6 lg:-mx-8">
                <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8">
                    <div className="overflow-x-hidden hover:overflow-auto border border-gray dark:border-darkgray xxxs:rounded-lg">
                        <table className="w-full divide-y divide-gray dark:divide-darkgray">
                            <thead className="bg-blue dark:bg-orange ">
                            <tr>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    {fields.titre.text}
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    {fields.location.text}
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    {fields.status.text}
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    {fields.startDate.text}
                                </th>
                                <th scope="col" className="relative px-6 py-3">
                                    <span className="sr-only">{fields.edit.text}</span>
                                </th>
                            </tr>
                            </thead>
                            <tbody className="bg-white dark:bg-dark divide-y divide-gray dark:divide-darkgray">
                            {props.offers.map((offer:any) => (
                                <tr key={offer.id}>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <div className="flex items-center">
                                            <div className="ml-4">
                                                <div className="text-sm font-medium dark:text-offwhite">{offer.title}</div>
                                            </div>
                                        </div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <div className="text-sm dark:text-offwhite">{offer.location}</div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                      <span
                          className={
                          offer.state == "PENDING" ?
                              "px-2 inline-flex text-xs leading-5 justify-center font-semibold rounded-full w-3/4 bg-orange text-white dark:text-offwhite"
                              : offer.state === "DECLINED" ?
                                  "px-2 inline-flex text-xs leading-5 font-semibold justify-center rounded-full w-3/4 bg-red text-white dark:text-offwhite"
                                  : "px-2 inline-flex text-xs leading-5 font-semibold rounded-full w-3/4 justify-center bg-green text-white dark:text-offwhite"}
                      >
                        {fields[offer.state].text}
                      </span>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm dark:text-offwhite">
                                        {offer.startDate}
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                                        <a href="#" className="text-red hover:text-indigo">
                                            {fields.edit.text}
                                        </a>
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    );
}