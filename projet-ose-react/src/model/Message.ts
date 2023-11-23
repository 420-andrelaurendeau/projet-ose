export interface Message {
    id: number,
    title: string,
    messageKey: string | undefined,
    parameters: String[],
    isRead: boolean
}