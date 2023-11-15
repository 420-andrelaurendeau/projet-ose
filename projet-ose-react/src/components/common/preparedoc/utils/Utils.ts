export function blobToURL(blob:Blob) {
  return new Promise((resolve) => {
    const reader = new FileReader();
    reader.readAsDataURL(blob);
    reader.onloadend = function () {
      const base64data = reader.result;
      resolve(base64data);
    };
  });
}

export function blobToBase64(blob:Blob) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onloadend = () => resolve(reader.result!.toString().split(",")[1]);
        reader.onerror = reject;
        reader.readAsDataURL(blob);
    });
}

export async function urlToBase64(url:string) {
    try {
        const response = await fetch(url);
        const blob = await response.blob();
        return await blobToBase64(blob);
    } catch (error) {
        console.error("Erreur lors de la conversion de l'URL en chaîne Base64 :", error);
        return null;
    }
}

export function downloadURI(uri:any, name:any) {
  var link = document.createElement("a");
  link.download = name;
  link.href = uri;
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

export function base64ToArrayBuffer(base64:string) {
  try {
    const binaryString = atob(base64);
    const length = binaryString.length;
    const buffer = new ArrayBuffer(length);
    const view = new Uint8Array(buffer);

    for (let i = 0; i < length; i++) {
      view[i] = binaryString.charCodeAt(i);
    }

    return buffer;
  }catch (e) {
    return null;
  }
}

export  function getWidth() {
  const container = document.getElementById("container");
  if (container) {
    return container.clientWidth;
  }
  return 0;
}

export function fileToDataURL(file:File) {
  return new Promise((resolve) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = function () {
      const base64data = reader.result;
      resolve(base64data);
    };
  });
}

export async function fileToBlob(file:File, handleUpdate:Function) {
  const { content, size }:any = file;
  let chunks = [];
  let i = 0;
  const totalCount = Math.round(size / 250000);

  for await (const chunk of content) {
    if (handleUpdate) {
      handleUpdate(i, totalCount);
    }
    chunks.push(chunk);
    i++;
  }
  // eslint-disable-next-line no-undef
  return new Blob(chunks);
}


export function URLToBase64(url:string,content:any) {
  //pas de promesse car on ne peut pas attendre le résultat
    const xhr = new XMLHttpRequest();
    xhr.onload = function() {
        const reader = new FileReader();
        reader.onloadend = function() {
            content = reader.result?.toString();
        }
        reader.readAsDataURL(xhr.response);
    }
    xhr.open('GET', url);
    xhr.responseType = 'blob';
    xhr.send();
    return content;
}