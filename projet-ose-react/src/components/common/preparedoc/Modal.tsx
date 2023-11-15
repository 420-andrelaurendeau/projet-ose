import React from 'react';
import {primary45} from './utils/colors';
import {useIsSmallScreen} from './hooks/useIsSmallScreen';

export function Modal({onClose, children, isVisible, style, positionTop}:any) {
  const isSmallScreen = useIsSmallScreen();
  if (!isVisible) {
    return null;
  }

  return (
    <div className="mx-auto">
      <div className="fixed w-4/5 h-full top-0 left-0 bg-white dark:bg-dark z-[999]" onClick={onClose} />
      <div className={`relative ${ isSmallScreen ? 'fixed' : 'absolute' } bg-white border-2 border-primary45 rounded-4 top-${ positionTop ? positionTop : isSmallScreen ? 60 : 150 } left-1/2 transform -translate-x-1/2 w-94 font-open-sans z-[1000] shadow-md`}>{children}</div>
    </div>
  );
}
