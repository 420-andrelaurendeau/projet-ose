import { useState } from 'react';

export default function useModal() {
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
