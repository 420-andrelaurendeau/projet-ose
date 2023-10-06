import Toast from './Toast';
// @ts-ignore
const ToastsContainer = ({ toasts }) => {
    return (
        <div className="toasts-container">

            {// @ts-ignore
                toasts.map((toast) => (
                <Toast key={toast.id} {...toast} />
            ))}
        </div>
    );
};

export default ToastsContainer;