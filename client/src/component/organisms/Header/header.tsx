import React from "react";
import styles from "../Header/Header.module.scss";

type Props = {};

export default function Header({}: Props) {
  return (
    <div className={styles.wrapper}>
      <div className={styles.logoWrapper}>dd</div>
      <div className={styles.ulWrapper}>
        <ul className={styles.ul}>
          <li>1</li>
          <li>2</li>
          <li>3</li>
          <li>4</li>
        </ul>
      </div>
      <div className={styles.loginWrapper}>dd</div>
    </div>
  );
}
