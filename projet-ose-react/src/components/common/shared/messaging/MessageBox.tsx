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

    function handleClick(message: Message) {
        let link = NotificationLinkMap.array[message.message];
        message.isRead = true;
        readNotification(message.id);
        navigate(link);
    }

    return props.messages.length > 0
        ? <div className="z-50 min-w-[320px] max-w-[400px] max-h-[240px] overflow-y-auto rounded-b-md absolute right-[calc(100%-1.5rem)] bg-neutral-50">
                {
                    props.messages.map(header =>
                            <a  key={header.id}
                                className={"block text-ellipsis whitespace-nowrap overflow-hidden px-4 py-2 w-full border-b last:border-b-0 "
                                + (header.isRead
                                    ? " bg-neutral-200"
                                    : "")}
                                onClick={event => handleClick(header)}>
                                {t(header.message)}
                            </a>
                    )
                }
            </div>
        : <></>;
}

export default MessageBox;