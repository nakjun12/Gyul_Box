import styles from "../pages/home.module.scss";
import gyulMain from "../../public/icon/mainGyul.jpg";
import Image from "next/image";
import MainLottie from "@/component/atoms/lottie/MainLottie";
import SearchBar from "@/component/molecules/searchBar/SearchBar";
import MainBoard from "@/component/mainBoard/MainBoard";

type Props = {};

export default function home({}: Props) {
  return (
    <div className={styles.wrapper}>
      <div className={styles.gyulMain}>
        <Image src={gyulMain} alt="메인 로고" height={250} width={800} />
      </div>
      <div className={styles.lottieWrapper}>
        <div className={styles.lottieDiv}>
          <MainLottie />
        </div>
        <div className={styles.whereDiv}>어디로 가볼까?</div>
      </div>
      <SearchBar />
      <div className={styles.boardParts}>
        <MainBoard />
        <MainBoard />
      </div>
      <div className={styles.carouselParts}></div>
    </div>
  );
}
