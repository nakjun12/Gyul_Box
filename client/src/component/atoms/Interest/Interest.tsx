import React from "react";
import styles from "../Interest/Interest.module.scss";

type Props = {};

export default function Interest({}: Props) {
  return <button className={styles.interest}>관심지역</button>;
}
