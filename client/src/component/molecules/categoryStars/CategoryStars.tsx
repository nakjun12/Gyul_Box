import Stars from "../stars/Stars";
import styles from "./CategoryStars.module.scss";
type Props = {};

export default function CategoryStars({}: Props) {
  return (
    <>
      <section className={styles.container}>
        <div className={styles.wrapper}>
          <div className={styles.category_stars}>
            <span className={styles.word}>총점</span> <Stars stars={3} />
            <span className={styles.average}>3.0</span>
          </div>
          <div className={styles.category_stars}>
            <span className={styles.word}>집내부</span> <Stars stars={3} />
            <span className={styles.average}>3.0</span>
          </div>
          <div className={styles.category_stars}>
            <span className={styles.word}>생활 및 입지</span>{" "}
            <Stars stars={3} />
            <span className={styles.average}>3.0</span>
          </div>

          <div className={styles.category_stars}>
            <span className={styles.word}>치안</span> <Stars stars={3} />
            <span className={styles.average}>3.0</span>
          </div>
          <div className={styles.category_stars}>
            <span className={styles.word}>교통</span> <Stars stars={3} />
            <span className={styles.average}>3.0</span>
          </div>
          <div className={styles.category_stars}>
            <span className={styles.word}>건물/단지</span> <Stars stars={3} />
            <span className={styles.average}>3.0</span>
          </div>
        </div>
      </section>
    </>
  );
}
