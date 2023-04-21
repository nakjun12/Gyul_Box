import { sort_zone } from "@/lib/Dummy";
import React, { useState } from "react";
import styles from "./Modal.module.scss";
interface ModalProps {
  isOpen?: boolean;
  children?: React.ReactNode;
}

const InterestModal: React.FC<ModalProps> = ({ isOpen = false, children }) => {
  const [show, setShow] = useState(isOpen || false);

  const handler = () => {
    setShow(!show);
  };

  return (
    <>
      <div className={styles.button_modal} onClick={handler}>
        {children}
      </div>
      <div
        className={show ? styles.container : styles.none}
        onClick={handler}
      ></div>

      {show && (
        <div className={styles.modalcontainer}>
          <div className={styles.inter_box}>
            {sort_zone.map((item, index) => {
              return (
                <div key={index} className={styles.text}>
                  {item}
                </div>
              );
            })}
          </div>
        </div>
      )}
    </>
  );
};

export default InterestModal;
