import React from "react";
import styles from "../lnterest/lnterest.module.scss";

type Props = {};

export default function Interest({}: Props) {
  return <button className={styles.interest}>관심지역</button>;
}
