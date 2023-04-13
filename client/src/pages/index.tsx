import React from "react";
import styles from "../styles/Home.module.scss";
import Button from "@/component/atoms/OrangeButton/OrangeButton";
import Category from "@/component/atoms/Category/Category";
import Map from "@/component/atoms/Map/Map";
import Gyul from "../component/atoms/Lottie/Gyul";
import Interest from "@/component/atoms/Interest/Interest";
import WhiteButton from "@/component/atoms/WhiteButton/WhiteButton";
import ReviewBox from "@/component/atoms/ReviewBox/ReviewBox";
import TextInput from "@/component/atoms/TextInput/TextInput";
import YellowStar from "@/component/atoms/YellowStar/YellowStart";
import GrayStar from "@/component/atoms/GrayStar/GrayStart";

type Props = {};

export default function main({}: Props) {
  return (
    <>
      <div className={styles.btn}>main</div>
      <Button />
      <Category />
      <Map />
      <Gyul />
      <Interest />
      <WhiteButton />
      <ReviewBox />
      <TextInput />
      <YellowStar />
      <GrayStar />
    </>
  );
}
