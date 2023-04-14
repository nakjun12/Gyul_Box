import React from "react";
import styles from "../Header/Header.module.scss";
import logoGyul from "../../icon/gyul_main.png";

type Props = {};

export default function Header({}: Props) {
  return (
    <div className={styles.wrapper}>
      <div className={styles.logoWrapper}>
        <img src={logoGyul} alt="귤 로고"></img>귤박스
      </div>
      <div className={styles.ulWrapper}>
        <ul className={styles.ul}>
          <li className={styles.li}>1</li>
          <li className={styles.li}>2</li>
          <li className={styles.li}>3</li>
          <li className={styles.li}>4</li>
        </ul>
      </div>
      <div className={styles.loginWrapper}>dd</div>
    </div>
  );
}
