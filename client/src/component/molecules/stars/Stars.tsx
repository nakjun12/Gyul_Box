import { AiTwotoneStar } from "react-icons/ai";
import styles from "./Stars.module.scss";
type Props = {
  stars: number;
};

export default function Stars({ stars = 5 }: Props) {
  const yellowStars = stars > 0 ? stars : 0;
  const grayStars = 5 - yellowStars;
  const yellowStarIcons = Array.from({ length: yellowStars });
  const grayStarIcons = Array.from({ length: grayStars });
  console.log(yellowStarIcons);
  return (
    <div className={styles.star_wrapper}>
      {yellowStarIcons.map((_, i) => (
        <AiTwotoneStar key={i} style={{ color: "var(--yellow)" }} />
      ))}
      {grayStarIcons.map((_, i) => (
        <AiTwotoneStar key={i + yellowStars} style={{ color: "#eeeeee" }} />
      ))}
    </div>
  );
}
