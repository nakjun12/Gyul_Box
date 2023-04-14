import Gyul from "@/component/atoms/lottie/Gyul";
import OrangeButton from "@/component/atoms/orangeButton/OrangeButton";
import LoginMain from "@/component/molecules/loginMain/LoginMain";
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
  console.log(show);
  return (
    <>
      <OrangeButton onClick={handler}>로그인</OrangeButton>

      <div
        className={show ? styles.container : styles.none}
        onClick={handler}
      ></div>

      {show && (
        <div className={styles.modalcontainer}>
          <div className={styles.modal}>
            <LoginMain />
            <Gyul />
          </div>
        </div>
      )}
    </>
  );
};

export default Modal;
