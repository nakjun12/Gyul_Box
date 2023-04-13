import React from "react";
import styles from "../YellowStar/yellowstart.module.scss";

type Props = {};

export default function YellowStar({}: Props) {
  return <div className={styles.star}>★</div>;
}
