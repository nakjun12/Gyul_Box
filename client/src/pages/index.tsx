import React from "react";
import styles from "../styles/Home.module.scss";
import Button from "@/component/atoms/Button/Button";
import Category from "@/component/atoms/Category/Category";
import Map from "@/component/atoms/Map/Map";
import Gyul from "../component/atoms/Lottie/Gyul";

type Props = {};

export default function main({}: Props) {
  return (
    <>
      <div className={styles.btn}>main</div>
      <Button />
      <Category />
      <Map />
      <Gyul />
    </>
  );
}
