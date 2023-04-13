import React from "react";
import styles from "../GrayStar/graystar.module.scss";

type Props = {};

export default function GrayStar({}: Props) {
  return <div className={styles.star}>★</div>;
}
