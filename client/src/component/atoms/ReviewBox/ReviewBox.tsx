import React from "react";
import styles from "../ReviewBox/ReviewBox.module.scss";

type Props = {};

export default function ReviewBox({}: Props) {
  return <div className={styles.box}>내용을 입력할꺼에요</div>;
}
