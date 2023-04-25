import Image from "next/image";
import { useRouter } from "next/router";
import gyulLogo from "../../../../public/icon/gyulLogo.png";
import Modal from "./../../organisms/modal/Modal";
import styles from "./Header.module.scss";
type Props = {};

export default function Header({}: Props) {
  const router = useRouter();

  const { pathname } = router;
  console.log(pathname);
  const handler = (path: string) => {
    router.push(`/${path}`);
  };

  return (
    <header className={styles.wrapper}>
      <div>
        <Image
          src={gyulLogo}
          alt="귤 로고"
          width={75}
          height={78}
          className={styles.logo}
          onClick={() => handler("")}
        />
      </div>
      <div className={styles.ulWrapper}>
        <ul className={styles.ul}>
          <li className={styles.li} onClick={() => handler("mypage")}>
            원룸 리뷰
          </li>
          <li className={styles.li} onClick={() => handler("map")}>
            지도
          </li>
          <li className={styles.li}>양도 게시판</li>
        </ul>
      </div>

      <Modal isOpen={false} />
    </header>
  );
}
