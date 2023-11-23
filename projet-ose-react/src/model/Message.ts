export interface Message {
    id: number,
    title: string,
    messageKey: string | undefined,
    parameters: Map<string,string>,
    isRead: boolean
}