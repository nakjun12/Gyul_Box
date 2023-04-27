import Image from "next/image";
import gyulMain from "../../public/icon/mainGyul.jpg";
import MiniLottie from "../component/atoms/lottie/MiniLottie";
import SearchBar from "../component/molecules/searchBar/SearchBar";
import DragCarousel from "../component/organisms/dragCarousel/DragCarousel";
import styles from "./../styles/Home.module.scss";
type Props = {};

export default function home({}: Props) {
  return (
    <>
      <div className={styles.wrapper}>
        <div className={styles.gyulMain}>
          <Image
            src={gyulMain}
            alt="메인 로고"
            height={327}
            width={1200}
            priority
          />
        </div>
        <div className={styles.lottieWrapper}>
          <div className={styles.lottieDiv}>
            <MiniLottie />
          </div>
          <div className={styles.whereDiv}>어디를 알아볼까?</div>
        </div>
        <SearchBar />
        <div className={styles.boardParts}></div>

        <DragCarousel />
      </div>
    </>
  );
}
