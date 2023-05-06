import { useState } from "react";
import RadioButtons from "../../molecules/radioButtons/RadioButtons";
import styles from "./EditorInformation.module.scss";
type Props = {};
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

const IndexPage = () => {
  const [startYear, setStartYear] = useState(2019);
  const [endYear, setEndYear] = useState(2023);

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

export default function Buliding({}: Props) {
  const [address, setAddress] = useState<string>("");
  const [selectedOption, setSelectedOption] = useState("villa");
  const [isPosition, setPosition] = useState("high");
  const handleOptionChange = (value: string) => {
    setSelectedOption(value);
  };
  const handlePositionChange = (value: string) => {
    setPosition(value);
  };
  console.log(selectedOption);
  const handleClick = () => {};

  return (
    <section className={styles.buliding_wrapper}>
      <label htmlFor="address" className={styles.label_style}>
        주소
      </label>
      <input
        type="text"
        value={address}
        onClick={handleClick}
        name="address"
        id="address"
        placeholder="주소를 입력해주세요"
        readOnly
      />
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

      <IndexPage />
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
