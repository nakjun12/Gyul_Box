import { useState } from "react";
import { AiTwotoneStar } from "react-icons/ai";
import styles from "./Stars.module.scss";
type Props = {
  stars?: number;
};

export default function DynamicStars({ stars = 5 }: Props) {
  const [show, setShow] = useState<number>(0);
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
      {/* {grayStarIcons.map((_, i) => (
        <AiTwotoneStar
          className={styles.star}
          key={i + yellowStars}
          onClick={() => handler(i)}
          size={35}
          style={{ color: "#b2b2b2" }}
        />
      ))} */}
    </div>
  );
}
