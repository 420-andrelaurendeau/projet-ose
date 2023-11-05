import React, {useState, useEffect} from 'react';

export function useWindowSize() {
  const isClient = typeof window === 'object';

  function getSize() {
    return {
      width: isClient ? window.innerWidth : undefined,
      height: isClient ? window.innerHeight : undefined,
    };
  }

  const [windowSize, setWindowSize] = useState(getSize);
  const [state, setState] = <any>useState(null);

  useEffect(()=> {
    if (!isClient) {
      setState(false);
    }

    window.addEventListener('resize', handleResize);
    setState(true);
  }, []); // Empty array ensures that effect is only run on mount and unmount

  function handleResize() {
    setWindowSize(getSize());
  }

  if (state === null) {
    return windowSize;
  }else if (state === false) {
    return false;
  }else if (state === true) {

    return () => window.removeEventListener('resize', handleResize);
  }
}
