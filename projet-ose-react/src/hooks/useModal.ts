import { useState } from 'react';

const useModal = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);

    const handleOpenModal = () => {
        setIsModalOpen(true);
        document.body.style.overflow = "hidden";
    }

    const handleCloseModal = () => {
        setIsModalOpen(false);
        document.body.style.overflow = "auto";
    }

    return {
        isModalOpen,
        handleOpenModal,
        handleCloseModal
    };
};

export default useModal;
