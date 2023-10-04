import React, {useEffect} from "react";
import {getStudentAppliedOffers} from "../../api/InterOfferJobAPI";
import {AppliedOffers} from "../../model/AppliedOffers";
import {InterOfferJob} from "../../model/IntershipOffer";
import {FileEntity} from "../../model/FileEntity";

export default function StudentAppliedOffers(props:any) {

    const tempId = 5;
    const [listStudentAppliedOffers, setListStudentAppliedOffers] = React.useState<AppliedOffers[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data:AppliedOffers[] = await getStudentAppliedOffers(tempId);
                console.log(data);
                setListStudentAppliedOffers(data);
            } catch (error) {
                console.error("Erreur lors de la récupération des offres:", error);
            }
        };

        fetchData();
    }, []);

    console.log(listStudentAppliedOffers);
    return (
        <div className="flex flex-col mt-14">
            <div className=" xs:-mx-6 lg:-mx-8">
                <div className="max-md:pt-2 min-w-full xs:px-6 lg:px-8">
                    <div className=" overflow-auto border border-gray xxxs:rounded-lg">
                        <table className="w-full divide-y divide-gray">
                            <thead className="bg-blue ">
                            <tr>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Title
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Location
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Start date
                                </th>
                                <th
                                    scope="col"
                                    className="px-6 py-3 text-left text-xs font-medium text-gray uppercase tracking-wider"
                                >
                                    Files
                                </th>
                            </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray">
                            {listStudentAppliedOffers.map((offer:AppliedOffers) => (
                                <tr key={offer.appliedOffer.id}>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <div className="flex items-center">
                                            <div className="ml-4">
                                                <div className="text-sm font-medium ">{offer.appliedOffer.title}</div>
                                            </div>
                                        </div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <div className="text-sm ">{offer.appliedOffer.location}</div>
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap text-sm ">
                                        {offer.appliedOffer.startDate?.toString()}
                                    </td>
                                    <td className="px-6 py-4 whitespace-nowrap">
                                        <div className="space-x-2">
                                            {
                                                offer.appliedFiles.map((file:FileEntity) => (
                                                    <span className="px-2 text-xs font-semibold rounded-full bg-green-100 text-green-800">
                                                        {file.fileName}
                                                    </span>
                                                ))
                                            }
                                        </div>
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