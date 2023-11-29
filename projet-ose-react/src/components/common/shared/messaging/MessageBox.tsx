import {Message} from "../../../../model/Message";
import {useTranslation} from "react-i18next";
import React, {useState} from "react";
import NotificationLinkMap from "../../../../model/NotificationLinkMap";
import {useNavigate} from "react-router-dom";
import {readNotification} from "../../../../api/NotificationAPI";


type MessageBoxProps = {
    messages: Message[]
}

const MessageBox: React.FC<MessageBoxProps> = (props) => {
    const {t} = useTranslation();
    let navigate = useNavigate()

    function handleClick() {
    function handleClick(message: Message) {
        let link = NotificationLinkMap.array[message.message];

        navigate(link);
    }

    return props.messages.length > 0
        ? <div className="z-50 min-w-[320px] max-w-[400px] min-h-[240px] max-h-[240px] overflow-y-auto rounded-b-md absolute right-[calc(100%-1.5rem)] bg-neutral-50 border-l-1 border-b-1 last:border-b-0">
                {
                    props.messages.map(header =>
                        <>
                            <a className={"px-4 py-2 text-ellipsis whitespace-nowrap overflow-hidden"
                                + (header.isRead
                                    ? " bg-neutral-200"
                                    : "")}
                                onClick={event => handleClick(header)}>
                                {t(header.message)}
                            </a>
                            <hr/>
                        </>
                    )
                }
            </div>
        : <></>;
}

export default MessageBox;