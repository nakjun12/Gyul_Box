import React from "react";
import styles from "../Button/button.module.scss";

type Props = {};

export default function Button({}: Props) {
  return <button className={styles.btn}>버튼</button>;
}
