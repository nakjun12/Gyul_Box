import Image from "next/image";
import {
  ChangeEvent,
  FormEvent,
  KeyboardEvent,
  Suspense,
  startTransition,
  useRef,
  useState,
} from "react";
import search from "../../../../public/svg/whiteSearch.svg";
import styles from "./SearchBar.module.scss";
type Props = {};

export default function SearchBar({}: Props) {
  const [inputValue, setInputValue] = useState("");
  const searchValueRef = useRef<string>("");
  const [selected, setSelected] = useState(-1);
  const [isComposing, setIsComposing] = useState(false);
  const [isVisible, setIsVisible] = useState(false);
  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    setInputValue(e.target.value);
    startTransition(() => {
      searchValueRef.current = e.target.value;
    });
    setSelected(-1);
    e.target.value.trim() === "" ? setIsVisible(false) : setIsVisible(true);
  };

  const handleInputKeyDown = (e: KeyboardEvent<HTMLInputElement>) => {
    if (isComposing) return;
    if (e.key === "ArrowDown") {
      const max = 5;
      setSelected((p) => (p + 1) % max);
    } else if (e.key === "ArrowUp" && selected > -1) {
      setSelected((p) => p - 1);

      if (selected === 0) setInputValue(searchValueRef.current);
      e.preventDefault();
    } else if (e.key === "Enter" && inputValue.trim() !== "") {
      setIsVisible(false);
      // router.push(`/attractions/search?keyword=${inputValue.replace(/\s/g, "+")}`);
    }
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
  };

  console.log(inputValue, searchValueRef.current, isVisible);
  return (
    <div className={styles.searchBarDiv}>
      <form className={styles.searchBar_bar} onSubmit={handleSubmit}>
        <input
          type="text"
          className={styles.searchBarInput}
          placeholder="동이름으로 검색해주세요"
          value={inputValue}
          onChange={handleInputChange} // 작성
          onKeyDown={handleInputKeyDown} //키보드
          onCompositionStart={() => setIsComposing(true)}
          onCompositionEnd={() => setIsComposing(false)}
          spellCheck={false}
          onFocus={() => setIsVisible(true)}
          onBlur={() => setIsVisible(false)}
        />

        <div className={styles.search_div}>
          <Image
            className={styles.icon}
            src={search}
            alt="돋보기"
            width={22}
            height={22}
          />
        </div>
        {/* {
          <Suspense
            fallback={
              <div className={styles.loading}>
                ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ로딩중
              </div>
            }
          >
            <ul className={styles.autocomplete}>
              <li className={styles.autocomplete_item}>
                ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ로딩중
              </li>
              <li className={styles.autocomplete_item}>
                ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ로딩중
              </li>
            </ul>
          </Suspense>
        } */}
      </form>
      {isVisible && (
        <Suspense
          fallback={
            <div className={styles.loading}>
              ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ로딩중
            </div>
          }
        >
          <ul className={styles.autocomplete}>
            <li className={styles.autocomplete_item}>
              ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ로딩중
            </li>
            <li className={styles.autocomplete_item}>
              ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ로딩중
            </li>
          </ul>
        </Suspense>
      )}
    </div>
  );
}
//구현 할 내용들

//1. 드롭다운 내려오게한다 검색 목록임
//2. 검색 목록 클릭하면 글자를 받아온다.
//3. 키보드로 위 아래로 이동하면서 선택할 수 있게한다.
//4. 엔터를 누르면 넘어간다.
