import OrangeButton from "@/component/atoms/orangeButton/OrangeButton";
import React, { useState } from "react";
import styles from "./Modal.module.scss";

interface ModalProps {
  isOpen: boolean;
  children?: React.ReactNode;
}

const Modal: React.FC<ModalProps> = ({ isOpen }) => {
  const [show, setShow] = useState(isOpen || false);

  const handler = () => {
    setShow(!show);
  };

  return (
    <>
      <OrangeButton onClick={handler}>모달</OrangeButton>
      <div className={styles.container}>
        {show && <div className="modal"></div>}
        {show && (
          <div className="backdrop" onClick={handler}>
            젠장
          </div>
        )}
      </div>
    </>
  );
};

export default Modal;
