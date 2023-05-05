import { Dispatch, SetStateAction } from "react";
import { AiTwotoneStar } from "react-icons/ai";
import styles from "./Stars.module.scss";
type Props = {
  stars?: number;
  show: number;
  setShow: Dispatch<SetStateAction<number>>;
};

export default function DynamicStars({ stars = 5, show, setShow }: Props) {
  const Stars = 5;

  const StarIcons = Array.from({ length: Stars });

  const handler = (i: number) => {
    setShow(i + 1);
  };
  console.log(show, Stars);
  return (
    <div className={styles.stars}>
      {StarIcons.map((_, i) => (
        <AiTwotoneStar
          className={styles.star}
          onClick={() => handler(i)}
          key={i}
          style={{ color: i >= show ? "b2b2b2" : "FFE86C" }}
          size={35}
        />
      ))}
    </div>
  );
}
