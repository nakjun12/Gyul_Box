import styles from "../pages/home.module.scss";
import gyulMain from "../../public/icon/mainGyul.jpg";
import Image from "next/image";
import MainLottie from "@/component/atoms/lottie/MainLottie";
import SearchBar from "@/component/molecules/searchBar/SearchBar";

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

        <div className={styles.whereDiv}>어디로 가볼까???</div>
      </div>
      <SearchBar />
      <div className={styles.boardWrapper}>
        <div className={styles.boardCategoryWrapper}>
          <div className={styles.boardCategoryDiv}>최신순</div>
          <div className={styles.boardCategoryDiv}>추천순</div>
        </div>
        <div className={styles.boardContentWrapper}>
          <div className={styles.boardContentDiv}>
            <div className={styles.boardContentDiv}>
              <div className={styles.boardTitle}>
                <div>공지사항</div>
                <button className={styles.moreButton}>더보기</button>
              </div>
            </div>
          </div>
          <div className={styles.boardContentDiv}>
            <div className={styles.boardContentDiv}>
              <div className={styles.boardTitle}>
                <div>공지사항</div>
                <button className={styles.moreButton}>더보기</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
