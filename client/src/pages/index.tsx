import React from "react";
import styles from "../styles/Home.module.scss";
import Button from "@/component/atoms/orangeButton/OrangeButton";
import Category from "@/component/atoms/category/Category";
import Map from "@/component/atoms/map/Map";
import Gyul from "../component/atoms/lottie/Gyul";
import Interest from "@/component/atoms/interest/Interest";
import WhiteButton from "@/component/atoms/whiteButton/WhiteButton";
import ReviewBox from "@/component/atoms/reviewBox/ReviewBox";
import TextInput from "@/component/atoms/textInput/TextInput";
import YellowStar from "@/component/atoms/yellowStar/YellowStart";
import GrayStar from "@/component/atoms/grayStar/GrayStart";
import Header from "@/component/organisms/header/Header";

type Props = {};

export default function main({}: Props) {
  return (
    <>
      <Header />
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
