import styles from "../Header/Header.module.scss";
import gyulLogo from "../../../../public/icon/gyulLogo.png";
import Image from "next/image";
import OrangeButton from "@/component/atoms/orangeButton/OrangeButton";

type Props = {};

export default function Header({}: Props) {
  return (
    <header className={styles.wrapper}>
      <div className={styles.logoWrapper}>
        <Image src={gyulLogo} alt="귤 로고" width={80} height={50} />
        귤박스
      </div>
      <div className={styles.ulWrapper}>
        <ul className={styles.ul}>
          <li className={styles.li}>리뷰</li>
          <li className={styles.li}>게시판</li>
          <li className={styles.li}>지도</li>
        </ul>
      </div>
      <div className={styles.loginWrapper}>
        <OrangeButton>로그인</OrangeButton>
      </div>
    </header>
  );
}
