import { useState } from "react";

import styles from "./EditorInformation.module.scss";
type Props = {};

export default function Buliding({}: Props) {
  const [address, setAddress] = useState<string>("");

  console.log(address);
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
      buliding2
    </section>
  );
}
