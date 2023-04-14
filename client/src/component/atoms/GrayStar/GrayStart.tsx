import React from "react";
import styles from "../GrayStar/GrayStar.module.scss";

type Props = {};

export default function GrayStar({}: Props) {
  return <div className={styles.star}>★</div>;
}
