import React, { useState } from "react";
import ModalGyul from "../../atoms/lottie/ModalGyul";

import OrangeButton from "./../../atoms/orangebutton/OrangeButton";
import LoginMain from "./../../molecules/loginMain/LoginMain";
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
      <OrangeButton onClick={handler}>로그인</OrangeButton>

      {show && (
        <>
          <div className={styles.modalcontainer}>
            <div className={styles.modal}>
              <LoginMain />
              <ModalGyul />
            </div>
          </div>
          <div
            className={show ? styles.container : styles.none}
            onClick={handler}
          />
        </>
      )}
    </>
  );
};

export default Modal;
