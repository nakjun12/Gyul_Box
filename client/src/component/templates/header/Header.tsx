import Image from "next/image";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { GiHamburgerMenu } from "react-icons/gi";
import gyulLogo from "../../../../public/icon/gyulLogo.png";
import { useWindowSize } from "../../../utils/Helper";
import EditorButton from "./../../molecules/editorButton/EditorButton";
import Modal from "./../../organisms/modal/Modal";
import styles from "./Header.module.scss";
type Props = {};

export default function Header({}: Props) {
  const [editorButton, setEditorButton] = useState<boolean>(true);
  const router = useRouter();
  const [showDropdown, setShowDropdown] = useState(false);

  function handleDropdownClick() {
    setShowDropdown(!showDropdown);
    console.log("하이");
  }
  const { pathname } = router;
  console.log(pathname);
  useEffect(() => {
    if (pathname === "/review/editor" || pathname === "/mypage") {
      setEditorButton(false);
    } else {
      setEditorButton(true);
    }
  }, [pathname]);
  const handler = (path: string) => {
    router.push(`/${path}`);
    setShowDropdown(false);
  };
  console.log(useWindowSize());

  const size = useWindowSize() || 987;

  return (
    <>
      <header className={styles.wrapper}>
        <div>
          <Image
            src={gyulLogo}
            alt="귤 로고"
            width={size < 600 ? 50 : 75}
            height={size < 600 ? 52 : 78}
            className={styles.logo}
            onClick={() => handler("")}
          />
        </div>
        {size < 600 ? (
          <>
            <div className={styles.mobile} onClick={handleDropdownClick}>
              <GiHamburgerMenu size={30} />
            </div>
            {showDropdown && (
              <div className={styles.dropdown_menu}>
                <ul>
                  <li>로그인</li>
                  <li>원룸 리뷰</li>
                  <li onClick={() => handler("map")}>지도</li>
                  <li onClick={() => handler("review/list")}>양도게시판</li>
                </ul>
              </div>
            )}
          </>
        ) : (
          <>
            <div className={styles.ulWrapper}>
              <ul className={styles.ul}>
                <li className={styles.li} onClick={() => handler("mypage")}>
                  원룸 리뷰
                </li>
                <li className={styles.li} onClick={() => handler("map")}>
                  지도
                </li>
                <li
                  className={styles.li}
                  onClick={() => handler("review/list")}
                >
                  양도 게시판
                </li>
              </ul>
            </div>
            <Modal isOpen={false} />
          </>
        )}
        {editorButton && <EditorButton />}
      </header>
    </>
  );
}
