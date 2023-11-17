import { startTransition, useCallback, useEffect, useState } from "react";
import { searchContent } from "../../../pages/api/search";
import type { Editor_type } from "../../../utils/types/types";
import RadioButtons from "../../molecules/radioButtons/RadioButtons";
import styles from "./EditorInformation.module.scss";
type Props = {
  setEditorData: React.Dispatch<React.SetStateAction<Editor_type>>;
};
const options = [
  { label: "빌라", value: "villa" },
  { label: "오피스텔", value: "office" },
  { label: "단독 주택", value: "house" },
];

const position = [
  { label: "고층", value: "high" },
  { label: "중층", value: "middle" },
  { label: "저층", value: "low" },
];

type PlatPlc = { id: number; houseName: string; platPlc: string };
type Content = {
  data: PlatPlc[];
};
type Index = {
  handleYear: (residenceYear: string) => void;
};

const IndexPage = ({ handleYear }: Index) => {
  const [startYear, setStartYear] = useState(2018);
  const [endYear, setEndYear] = useState(2023);
  useEffect(() => {
    handleYear(`${startYear}-${endYear}`);
  }, [startYear, endYear, handleYear]);

  const handleChangeStartYear = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    const year = parseInt(event.target.value);
    if (year <= endYear) {
      setStartYear(year);
    }
  };

  const handleChangeEndYear = (event: React.ChangeEvent<HTMLInputElement>) => {
    const year = parseInt(event.target.value);
    if (year >= startYear) {
      setEndYear(year);
    }
  };

  return (
    <div className={styles.container}>
      <div>
        <div>{startYear}</div>
        <input
          type="range"
          min={2019}
          max={2023}
          step={1}
          value={startYear}
          className={styles.range}
          onChange={handleChangeStartYear}
        />
      </div>

      <div>
        <div>{endYear}</div>
        <input
          type="range"
          min={startYear}
          max={2023}
          step={1}
          value={endYear}
          className={styles.range}
          onChange={handleChangeEndYear}
        />
      </div>
    </div>
  );
};

export default function Buliding({ setEditorData }: Props) {
  const [address, setAddress] = useState<string>("");
  const [selectedOption, setSelectedOption] = useState("villa");
  const [isPosition, setPosition] = useState("high");
  const [searchValue, setSearchValue] = useState<string>("");
  const [isZone, setIsZone] = useState<PlatPlc[]>([]);

  useEffect(() => {
    //문제 있음
    if (searchValue === "") return;
    searchContent(searchValue)
      .then((res: Content) => {
        setIsZone(res.data as PlatPlc[]);
      })
      .catch((err) => {
        setIsZone([{ id: 36, houseName: "", platPlc: "검색 결과가 없습니다" }]);
      });
    // searchData(searchValue).then((res) => {
    //   setIsZone(res.data);
    // });
    // } else {
    //   const filteredResults = zone.filter(function (item) {
    //     return item.includes(searchValue);
    //   });
    // setDummy(filteredResults);
  }, [searchValue]);
  const handleYear = useCallback(
    (residenceYear: string) => {
      setEditorData((prev) => ({ ...prev, residenceYear }));
    },
    [setEditorData]
  );

  const handleOptionChange = (buildingType: string) => {
    setSelectedOption(buildingType);
    setEditorData((prev) => ({ ...prev, buildingType }));
  };
  const handlePositionChange = (floor: string) => {
    setPosition(floor);
    setEditorData((prev) => ({ ...prev, floor }));
  };
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setAddress(value);
    startTransition(() => {
      setSearchValue(value);
    });
    // Do something with the updated value
  };

  const handleClickSearch = (address: string) => {
    const addressWords = address.split(" ").filter((word) => word !== ""); // 주소를 공백 문자열을 기준으로 분할하고, 빈 문자열을 제거합니다.
    const lastWord = addressWords.pop(); // 마지막 단어를 추출하고 배열에서 제거합니다.
    const firstWord = addressWords.join(" "); // 마지막 단어를 제외한 부분을 다시 합쳐서 문자열로 저장합니다.

    setEditorData((prev) => ({
      ...prev,
      address: firstWord as string,
      buildingName: lastWord as string,
    }));
    setAddress(address);
    setIsZone([]);
    console.log(addressWords, "히히", lastWord, "히히", firstWord);
  };

  return (
    <section className={styles.buliding_wrapper}>
      <label htmlFor="address" className={styles.label_style}>
        주소
      </label>
      <div className={styles.search}>
        <input
          type="text"
          value={address}
          onChange={handleChange} // Connect onchange event
          name="address"
          id="address"
          placeholder="주소를 입력해주세요"
        />
        {isZone.length > 0 && (
          <ul className={styles.search_list}>
            {isZone.map((item: PlatPlc) => (
              <li
                key={item.id}
                className={styles.search_item}
                onClick={() =>
                  handleClickSearch(item.platPlc + " " + item.houseName)
                }
              >
                {item.platPlc + " " + item.houseName}
              </li>
            ))}
          </ul>
        )}
      </div>

      <label htmlFor="type" className={styles.label_style}>
        원룸 유형
      </label>
      <RadioButtons
        options={options}
        defaultOption={selectedOption}
        onChange={handleOptionChange}
      />
      <label htmlFor="type" className={styles.label_style}>
        거주 기간
      </label>

      <IndexPage handleYear={handleYear} />
      <label htmlFor="type" className={styles.label_style}>
        거주층
      </label>
      <RadioButtons
        options={position}
        defaultOption={isPosition}
        onChange={handlePositionChange}
      />
    </section>
  );
}
