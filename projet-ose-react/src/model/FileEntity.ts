export interface FileEntity {
    id?: number;
    fileName: string;
    content: string;
    isAccepted: string;
    uploaderId: number;
    defaultFile?: boolean;
}
