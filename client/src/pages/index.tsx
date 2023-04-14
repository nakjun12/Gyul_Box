import Category from "@/component/atoms/Category/Category";
import GrayStar from "@/component/atoms/GrayStar/GrayStart";
import Interest from "@/component/atoms/Interest/Interest";
import Map from "@/component/atoms/Map/Map";
import Button from "@/component/atoms/OrangeButton/OrangeButton";
import ReviewBox from "@/component/atoms/ReviewBox/ReviewBox";
import TextInput from "@/component/atoms/TextInput/TextInput";
import WhiteButton from "@/component/atoms/WhiteButton/WhiteButton";
import YellowStar from "@/component/atoms/YellowStar/YellowStart";
import Modal from "@/component/templates/modal/Modal";
import Gyul from "../component/atoms/Lottie/Gyul";

type Props = {};

export default function main({}: Props) {
  return (
    <>
      <Modal isOpen={true}>하이</Modal>
      그치
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
