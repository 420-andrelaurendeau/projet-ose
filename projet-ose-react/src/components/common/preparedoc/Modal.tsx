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
      <div className="fixed w-full h-full top-0 left-0 bg-white dark:bg-gray z-[999] bg-opacity-20 dark:bg-opacity-50" onClick={onClose} />
      <div className={`fixed bg-white border-2 border-primary45 rounded-4 top-${ positionTop ? positionTop : isSmallScreen ? 60 : 150 } left-1/2 transform -translate-x-1/2 w-94 font-open-sans z-[1000] shadow-md`}>{children}</div>
    </div>
  );
}
