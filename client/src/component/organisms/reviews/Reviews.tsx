import Review from "../../molecules/review/Review";
import styles from "./Reviews.module.scss";
type Props = {};

export default function Reviews({}: Props) {
  return (
    <div className={styles.container}>
      <Review />
      <Review />
    </div>
  );
}
