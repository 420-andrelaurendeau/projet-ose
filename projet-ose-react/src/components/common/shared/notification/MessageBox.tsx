import {Message} from "../../../../model/Message";
import {useTranslation} from "react-i18next";

const MessagePage = () => {
    const {t} = useTranslation();

    function getMessageHeaders() {
        return [
            {
                id: 0,
                messageKey: "Hello World",
                isRead: false,
            },
            {
                id: 0,
                messageKey: "Hello World",
                isRead: true,
            },
            {
                id: 0,
                messageKey: "Hello World",
                isRead: true,
            },
            {
                id: 0,
                messageKey: "Hello World",
                isRead: true,
            },
            {
                id: 0,
                messageKey: "Hello World",
                isRead: true,
            },
            {
                id: 0,
                messageKey: "Hello World",
                isRead: true,
            },
        ] as Message[];
    }

    let messages = getMessageHeaders();

    return messages.length > 0
        ? <div className="z-50 min-w-[320px] max-w-[400px] min-h-[240px] rounded-b-md absolute right-[calc(100%-1.5rem)] top-full bg-neutral-50 border-l-1 border-b-1">
                {
                    getMessageHeaders().slice(0,5).map(header =>
                        <>
                            <p className={"px-4 py-2 text-ellipsis whitespace-nowrap overflow-hidden"
                                + (header.isRead
                                    ? " bg-neutral-200"
                                    : "")}>
                                {t(header.messageKey)}
                            </p>
                            <hr/>
                        </>
                    )
                }
                {
                    getMessageHeaders().length > 5
                    ?   <div className="px-4 py-2 text-center">
                            <button className="text-white p-1 w-2/4 text-center bg-blue rounded-sm"><a>Voir autre messages</a></button>
                        </div>
                    : <></>
                }
            </div>
        : <></>;
}

export default MessagePage;