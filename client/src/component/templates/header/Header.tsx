import Modal from "@/component/organisms/modal/Modal";
import Image from "next/image";
import gyulLogo from "../../../../public/icon/gyulLogo.png";
import styles from "./Header.module.scss";
type Props = {};

export default function Header({}: Props) {
  return (
    <header className={styles.wrapper}>
      <Image src={gyulLogo} alt="귤 로고" width={75} height={78} />
      <div className={styles.ulWrapper}>
        <ul className={styles.ul}>
          <li className={styles.li}>원룸 리뷰</li>
          <li className={styles.li}>지도</li>
          <li className={styles.li}>양도 게시판</li>
        </ul>
      </div>

      <Modal isOpen={false} />
    </header>
  );
}
