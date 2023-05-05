import { useState } from "react";
import RadioButtons from "../../molecules/radioButtons/RadioButtons";
import styles from "./EditorInformation.module.scss";
type Props = {};
const options = [
  { label: "오피스텔", value: "office" },
  { label: "단독 주택", value: "house" },
  { label: "빌라", value: "villa" },
];
export default function Buliding({}: Props) {
  const [address, setAddress] = useState<string>("");
  const [selectedOption, setSelectedOption] = useState("villa");

  const handleOptionChange = (value: string) => {
    setSelectedOption(value);
  };

  const handleClick = () => {};

  return (
    <section className={styles.buliding_wrapper}>
      <label htmlFor="address">주소</label>
      <input
        type="text"
        value={address}
        onClick={handleClick}
        name="address"
        id="address"
        placeholder="주소를 입력해주세요"
        readOnly
      />
      <RadioButtons
        options={options}
        defaultOption={selectedOption}
        onChange={handleOptionChange}
      />
    </section>
  );
}
