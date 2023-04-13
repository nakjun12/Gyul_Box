import React from "react";
import styles from "../Interest/interest.module.scss";

type Props = {};

export default function Interest({}: Props) {
  return <button className={styles.interest}>관심지역</button>;
}
