import { IoReloadOutline } from "react-icons/io5";
import { BACK_URL } from "../../../utils/ConfigHelper";
import { addressToCode, 삼도일동, 일도일동 } from "../../../utils/Dummy";
import type { Map_circle } from "../../../utils/types/types";
import styles from "./Reload.module.scss";
type Props = {
  geo: [string, number];
  setAddress: React.Dispatch<React.SetStateAction<Map_circle>>; //임시
  serverOn: boolean;
};

export default function Reload({ geo, setAddress, serverOn }: Props) {
  const dataDummies: { [key: string]: Map_circle } = {
    삼도일동,
    일도일동,
  };
  // useEffect(() => {
  //   const area = addressToCode(geo[0]);
  //   fetch(`${BACK_URL}/areas/${area}/houseInfos?level=${geo[1]}`, {
  //     mode: "cors",
  //     headers: {
  //       "ngrok-skip-browser-warning": "true", // ngrok-skip-browser-warning 헤더 추가
  //     },
  //   })
  //     .then((res) => res.json())
  //     .then((res) => console.log(res))
  //     .catch((err) => console.error(err));

  //   console.log(addressToCode(geo[0]));
  // }, [geo]); //임시

  const areaSearch = () => {
    if (serverOn) {
      const area = addressToCode(geo[0]);
      fetch(`${BACK_URL}/areas/${area}/houseInfos?level=${geo[1]}`, {
        mode: "cors",
        headers: {
          "ngrok-skip-browser-warning": "true", // ngrok-skip-browser-warning 헤더 추가
        },
      })
        .then((res) => res.json())
        .then((res) => {
          console.log(res, "야호");
          setAddress(res);
        })
        .catch((err) => console.log("야호")); //언디파인드처리
    } else {
      if (dataDummies[geo[0]] === undefined) setAddress({ data: [] });
      else {
        setAddress(dataDummies[geo[0]]);
      }
      console.log(dataDummies[geo[0]]);
    }
    console.log(addressToCode(geo[0]));
  };

  console.log(geo);
  return (
    <div className={styles.container} onClick={() => areaSearch()}>
      <IoReloadOutline />
      <span>{geo[0]}</span>검색
    </div>
  );
}
