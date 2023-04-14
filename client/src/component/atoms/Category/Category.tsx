import React from "react";
import styles from "../Category/Category.module.scss";

type Props = {};

export default function Category({}: Props) {
  return <button className={styles.category}>Category</button>;
}
