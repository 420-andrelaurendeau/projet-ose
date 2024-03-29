import {Message} from "../../../../model/Message";
import {useTranslation} from "react-i18next";
import React, {MutableRefObject, useEffect, useRef, useState} from "react";
import NotificationLinkMap from "../../../../model/NotificationLinkMap";
import {useNavigate} from "react-router-dom";
import {readNotification} from "../../../../api/NotificationAPI";
import {useForceUpdate} from "framer-motion";


type MessageBoxProps = {
    messages: Message[],
    messagesSetter: any,
    openStateSetter: any
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

    const thisRef = useRef(null);

    function useOutsideAlerter(ref: MutableRefObject<any>) {
        useEffect(() => {
            function handleClickOutside(event: MouseEvent) {
                if (ref.current && !ref.current.contains(event.target)) {
                    props.openStateSetter(false);
                }
            }
            document.addEventListener("mousedown", handleClickOutside);
            return () => {
                document.removeEventListener("mousedown", handleClickOutside);
            };
        }, [ref]);
    }

    useOutsideAlerter(thisRef);

    function handleNotificationClick(message: Message) {
        let link = NotificationLinkMap.array[message.message];
        navigate(link);
        props.openStateSetter(false);
    }

    function handleUniqueNotificationClick(message: UniqueUnreadMessage) {
        let link = NotificationLinkMap.array[message.messageKey];

        unreadMessages
            .filter(unreadMessage => message.ids
                .find(value => value == unreadMessage.id))
            .forEach(message => {
                message.read = true;
                readNotification(message.id);
            });

        props.messagesSetter(props.messages);
        navigate(link);
        props.openStateSetter(false);
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

    return  <div ref={thisRef} className="shadow-md w-[66.6vw] z-50 sm:w-auto max-h-[calc(66.6vh)] sm:min-w-[320px] sm:max-w-[400px] sm:max-h-[240px] overflow-y-auto rounded-b-md absolute -translate-x-1/2 sm:!translate-x-0  sm:right-[calc(100%-1.5rem)] translate-y-5 bg-neutral-50 dark:bg-stone-800 dark:text-white">
                {(props.messages.length > 0)
                    ? <>
                        {
                            uniqueUnreadMessage.map(message =>
                                <a key={message.messageKey}
                                    className="block sm:text-ellipsis sm:whitespace-nowrap overflow-hidden px-4 py-2 w-full border-b dark:border-b-gray last:border-b-0 dark:bg-stone-700"
                                    onClick={_ => handleUniqueNotificationClick(message)}>
                                    {t(message.messageKey)} ({message.ids.length})
                                </a>)
                        }
                        {
                            readMessages.map(message =>
                                <a  key={message.id}
                                    className="block sm:text-ellipsis sm:whitespace-nowrap overflow-hidden px-4 py-2 w-full border-b dark:border-b-gray last:border-b-0 bg-neutral-200 dark:bg-inherit"
                                    onClick={_ => handleNotificationClick(message)}>
                                    {t(message.message)}
                                </a>)
                        }
                    </>
                    : <p className="mx-auto">{t("formField.notifications.noNotification")}</p>}
            </div>;
}

export default MessageBox;