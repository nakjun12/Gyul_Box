import Image from "next/image";
import {
  ChangeEvent,
  FormEvent,
  KeyboardEvent,
  Suspense,
  startTransition,
  useEffect,
  useRef,
  useState,
} from "react";
import search from "../../../../public/svg/whiteSearch.svg";
import { searchData } from "../../../pages/api/search";
import { zone } from "../../../utils/Dummy";
import type { SearchAddress_data } from "../../../utils/types/types";
import styles from "./SearchBar.module.scss";
type Props = {
  isData: boolean;
};

export default function SearchBar({ isData }: Props) {
  const [inputValue, setInputValue] = useState<string>("");
  const searchValueRef = useRef<string>("");
  const inputRef = useRef<HTMLInputElement | null>(null);
  const [selected, setSelected] = useState<number>(-1);
  const [isComposing, setIsComposing] = useState<boolean>(false);
  const [isVisible, setIsVisible] = useState<boolean>(false);
  const [searchValue, setSearchValue] = useState<string>("");
  const [isZone, setIsZone] = useState<SearchAddress_data[]>([]);
  const [dummy, setDummy] = useState<string[]>([]); // dummy state
  const divRef = useRef<HTMLDivElement | null>(null);

  console.log(isZone);
  useEffect(() => {
    const handleClickOutside = (event: any) => {
      if (divRef.current && !divRef.current.contains(event.target)) {
        blur(); // 원하는 동작 수행
      }
    };

    if (isVisible) {
      document.addEventListener("click", handleClickOutside);
    } else {
      document.removeEventListener("click", handleClickOutside);
    }
  }, [isVisible]);

  useEffect(() => {
    if (isData) {
      if (searchValue === "") return;

      searchData(searchValue).then((res) => {
        setIsZone(res.data);
      });
    } else {
      const filteredResults = zone.filter(function (item) {
        return item.includes(searchValue);
      });
      setDummy(filteredResults);
    }
  }, [searchValue, isData]);

  const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
    console.log(e.target.value);
    setInputValue(e.target.value);
    startTransition(() => {
      setSearchValue(e.target.value);
    });
    setSelected(-1);
    e.target.value.trim() === "" ? setIsVisible(false) : setIsVisible(true);
  };

  const handleInputKeyDown = (e: KeyboardEvent<HTMLInputElement>) => {
    if (isComposing) return;
    if (e.key === "ArrowDown") {
      if (!isVisible) {
        setIsVisible(true);
        return;
      }

      const max = 5;
      setIsVisible(true);
      setSelected((p) => (p + 1) % max);
      setInputValue(zone[(selected + 1) % max]);
    } else if (e.key === "ArrowUp") {
      if (selected === -1) setIsVisible(false);
      else if (selected === 0) {
        setIsVisible(false);
        inputRef.current?.focus();
        setSelected(-1);
        setInputValue(searchValueRef.current);
      } else {
        setIsVisible(true);
        setSelected((p) => p - 1);
        setInputValue(zone[selected - 1]);
      }
      e.preventDefault();
    } else if (e.key === "Enter" && selected > -1) {
      setInputValue(zone[selected]);
      setIsVisible(false);

      setSelected(-1);

      // router.push(`/attractions/search?keyword=${inputValue.replace(/\s/g, "+")}`);
    }
  };

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
  };
  const blur = () => {
    setIsVisible(false);
    setSelected(-1);
    if (inputRef.current) inputRef.current.blur();
    //밖에 눌렀을떄 -1되야함
    //1의 경우의수 밖 눌렀을때
    //2의 경우의 수 안을 눌렀을떄
  };
  console.log(isZone.length > 0);

  return (
    <div className={styles.searchBarDiv} ref={divRef}>
      <form className={styles.searchBar_bar} onSubmit={handleSubmit}>
        <input
          type="text"
          ref={inputRef}
          className={styles.searchBarInput}
          placeholder="동이름으로 검색해주세요"
          value={inputValue}
          onChange={handleInputChange} // 작성
          onKeyDown={handleInputKeyDown} //키보드
          onCompositionStart={() => setIsComposing(true)}
          onCompositionEnd={() => setIsComposing(false)}
          spellCheck={false}
          onFocus={() => setIsVisible(true)}
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
      </form>
      {isVisible && (
        <Suspense fallback={<div className={styles.loading}>로딩중</div>}>
          <ul className={styles.autocomplete}>
            {isData ? (
              isZone.length > 0 ? (
                isZone.map((item: SearchAddress_data, index: number) => (
                  <li
                    className={styles.autocomplete_item}
                    key={`${item.areaCode}${index}`}
                    onClick={() => {
                      setInputValue(item.areaName);
                      setSelected(-1);
                      setIsVisible(false);
                    }}
                    onMouseOver={() => setSelected(index)}
                    style={{ background: selected === index ? "#ffab4f" : "" }}
                  >
                    {item.areaName}
                  </li>
                ))
              ) : (
                <li className={styles.autocomplete_item}>
                  {"검색 결과가 없습니다."}
                </li>
              )
            ) : dummy.length > 0 ? (
              dummy.slice(0, Math.min(dummy.length, 5)).map((item, index) => (
                <li
                  className={styles.autocomplete_item}
                  key={`${item}${index}`}
                  onClick={() => {
                    setInputValue(item);
                    setSelected(-1);
                    setIsVisible(false);
                  }}
                  onMouseOver={() => setSelected(index)}
                  style={{ background: selected === index ? "#ffab4f" : "" }}
                >
                  {item}
                </li>
              ))
            ) : (
              <>
                <li className={styles.autocomplete_item}>
                  {"검색 결과가 없습니다."}
                </li>
              </>
            )}
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
//마우스 올리면 그값이 들어오고 누르면
//d엔터치면 바로 거기로
//내용 없을땐 없습니다.
