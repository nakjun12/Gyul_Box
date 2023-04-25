import Category from "./../../component/atoms/category/Category";
import GrayStar from "./../../component/atoms/grayStar/GrayStart";
import Interest from "./../../component/atoms/lnterest/Interest";
import Button from "./../../component/atoms/loginButton/LoginButton";
import Gyul from "./../../component/atoms/lottie/ModalGyul";
import MapButton from "./../../component/atoms/mapButton/MapButton";
import ReviewBox from "./../../component/atoms/reviewBox/ReviewBox";
import TextInput from "./../../component/atoms/textInput/TextInput";
import WhiteButton from "./../../component/atoms/whiteButton/WhiteButton";
import YellowStar from "./../../component/atoms/yellowStar/YellowStart";
import styles from "./../../styles/Home.module.scss";

type Props = {};

export default function index({}: Props) {
  return (
    <>
      <div className={styles.btn}>main</div>
      <div>
        <Button /> 버튼
      </div>
      <Category />
      <MapButton />
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
