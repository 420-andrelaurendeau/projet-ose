declare module "*.png";
declare module "*.jpg";
declare module "*.jpeg";
declare module "*.gif";
declare module "*.svg";
declare module "*.pdf";

declare global {
    interface Window {
        PDFViewer: any; // Remplacez 'any' par le type correct si vous avez une définition de type
    }
}
