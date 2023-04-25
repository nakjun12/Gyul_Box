import Image from "next/image";
import gyulMain from "../../public/icon/mainGyul.jpg";
import MainLottie from "../component/atoms/lottie/MainLottie";
import SearchBar from "../component/molecules/searchBar/SearchBar";
import MainCarousel from "../component/organisms/carousel/MainCarousel";
import MainBoard from "../component/templates/mainBoard/MainBoard";
import styles from "./../styles/Home.module.scss";
type Props = {};

export default function home({}: Props) {
  return (
    <>
      <div className={styles.wrapper}>
        <div className={styles.gyulMain}>
          <Image src={gyulMain} alt="메인 로고" height={800} width={1200} />
        </div>
        <div className={styles.lottieWrapper}>
          <div className={styles.lottieDiv}>
            <MainLottie />
          </div>
          <div className={styles.whereDiv}>어디를 알아볼까?</div>
        </div>
        <SearchBar />
        <div className={styles.boardParts}>
          <MainBoard title={"최신"} />
          <MainBoard title={"추천"} />
        </div>

        <MainCarousel
          items={[
            "김치",
            "짜장",
            "짬뽕",
            "김치",
            "짜장",
            "짬뽕",
            "김치",
            "짜장",
            "짬뽕",
          ]}
        />
      </div>
    </>
  );
}
