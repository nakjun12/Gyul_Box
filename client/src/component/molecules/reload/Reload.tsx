import { useEffect } from "react";
import { IoReloadOutline } from "react-icons/io5";
import { BACK_URL } from "../../../utils/ConfigHelper";
import { addressToCode } from "../../../utils/Dummy";
import styles from "./Reload.module.scss";
type Props = {
  geo: [string, number];
};

export default function Reload({ geo }: Props) {
  useEffect(() => {
    const area = addressToCode(geo[0]);
    fetch(`${BACK_URL}/areas/${area}/houseInfos?level=${geo[1]}`, {
      mode: "cors",
      headers: {
        "ngrok-skip-browser-warning": "true", // ngrok-skip-browser-warning 헤더 추가
      },
    })
      .then((res) => res.json())
      .then((res) => console.log(res))
      .catch((err) => console.error(err));

    console.log(addressToCode(geo[0]));
  }, [geo]); //임시
  console.log(geo);
  return (
    <div className={styles.container}>
      <IoReloadOutline />
      <span>{geo[0]}</span>검색
    </div>
  );
}
