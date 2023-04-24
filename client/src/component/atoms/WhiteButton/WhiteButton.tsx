import React from "react";
import styles from "../WhiteButton/WhiteButton.module.scss";

type Props = {};

export default function WhiteButton({}: Props) {
  return <button className={styles.btn}>버튼</button>;
}
