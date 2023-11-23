import {Message} from "../../../../model/Message";

const MessagePage = () => {
    function getMessageHeaders() {
        return [{
            id: 0,
            title: "Hello World",
        }] as Message[];
    }

    return <div className="sr-only">
        {
            getMessageHeaders().map(header =>
                <p>{header.title}</p>
            )
        }
    </div>;
}

export default MessagePage;