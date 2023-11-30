import {Message} from "../../../../model/Message";
import {useTranslation} from "react-i18next";
import React, {useState} from "react";
import NotificationLinkMap from "../../../../model/NotificationLinkMap";
import {useNavigate} from "react-router-dom";
import {readNotification} from "../../../../api/NotificationAPI";


type MessageBoxProps = {
    messages: Message[]
}

type UniqueUnreadMessage = {
    ids: number[]
    messageKey: string
}

const MessageBox: React.FC<MessageBoxProps> = (props) => {
    const {t} = useTranslation();
    let navigate = useNavigate()

    let unreadMessages = props.messages.filter((message) => !message.read);
    let readMessages = props.messages.filter((message) => message.read);
    let uniqueUnreadMessage: UniqueUnreadMessage[] = [];

    function handleNotificationClick(message: Message) {
        let link = NotificationLinkMap.array[message.message];
        navigate(link);
    }

    function handleUniqueNotificationClick(message: UniqueUnreadMessage) {
        let link = NotificationLinkMap.array[message.messageKey];

        message.ids.forEach(i => readNotification(i))
        let messagesToPush = unreadMessages.filter(unreadMessage => unreadMessage.id in message.ids);
        messagesToPush.forEach(unreadMessage => unreadMessage.read = true);
        readMessages = [...messagesToPush, ...readMessages];

        navigate(link);
    }

    unreadMessages.forEach((message) => {
        let uniqueMessageIndex = uniqueUnreadMessage.findIndex((uniqueMessage) => uniqueMessage.messageKey == message.message)
        if (uniqueMessageIndex == -1) {
            uniqueUnreadMessage.push({
                ids: [message.id],
                messageKey: message.message
            });
            return;
        }

        uniqueUnreadMessage[uniqueMessageIndex].ids.push(message.id);
    });

    return  <div className="shadow-md z-50 min-w-[320px] max-w-[400px] max-h-[240px] overflow-y-auto rounded-b-md absolute right-[calc(100%-1.5rem)] bg-neutral-50 dark:bg-stone-800 dark:text-white">
                {(props.messages.length > 0)
                    ? <>
                        {
                            uniqueUnreadMessage.map(message =>
                                <a
                                    className="block text-ellipsis whitespace-nowrap overflow-hidden px-4 py-2 w-full border-b dark:border-b-gray last:border-b-0"
                                    onClick={_ => handleUniqueNotificationClick(message)}>
                                    {t(message.messageKey)} ({message.ids.length})
                                </a>)
                        }
                        {
                            readMessages.map(message =>
                                <a  key={message.id}
                                    className="block text-ellipsis whitespace-nowrap overflow-hidden px-4 py-2 w-full border-b dark:border-b-gray last:border-b-0 bg-neutral-200 dark:bg-stone-700"
                                    onClick={_ => handleNotificationClick(message)}>
                                    {t(message.message)}
                                </a>)
                        }
                    </>
                    : <p>{t("formField.notifications.noNotification")}</p>}
            </div>;
}

export default MessageBox;