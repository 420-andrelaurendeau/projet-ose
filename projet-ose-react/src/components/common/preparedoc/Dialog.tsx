import React from 'react';
import {primary45} from './utils/colors';
import {FaTimes} from 'react-icons/fa';
import {Modal} from './Modal';

export function Dialog({
  isVisible,
  body,
  onClose,
  title,
  noPadding,
  backgroundColor,
  positionTop,
  style,
}:any) {
  if (!isVisible) {
    return null;
  }

  const paddingClass = noPadding ? 'p-0' : 'p-14';
  const backgroundClass = backgroundColor ? `bg-${backgroundColor}` : 'bg-white';

  return (
    <Modal onClose={onClose} isVisible={isVisible} positionTop={positionTop} style={style}>
      <div className="mx-auto">
        <div className="bg-white dark:bg-dark text-white dark:text-black p-1 text-xs flex justify-between items-center">
          <div>{title}</div>
          <FaTimes
            color={'#FFF'}
            size={16}
            className="dialogClose dark:text-black cursor-pointer"
            onClick={onClose}
          />
        </div>
        <div className={`${paddingClass} ${backgroundClass}`}>{body}</div>
      </div>
    </Modal>
  );
}
