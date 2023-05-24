import Stars from "../stars/Stars";
import styles from "./CategoryStars.module.scss";
type Props = {
  rating: number[];
};

export default function CategoryStars({ rating }: Props) {
  const categories = [
    { label: "총점", average: rating[0] },
    { label: "집내부", average: rating[1] },
    { label: "생활 및 입지", average: rating[2] },
    { label: "치안", average: rating[3] },
    { label: "교통", average: rating[4] },
    { label: "건물/단지", average: rating[5] },
  ];

  return (
    <>
      <section className={styles.container}>
        <div className={styles.wrapper}>
          {categories.map((category, index) => (
            <div className={styles.category_stars} key={index}>
              <span className={styles.word}>{category.label}</span>{" "}
              <Stars stars={category.average} />
              <span className={styles.average}>
                {category.average.toFixed(1)}
              </span>
            </div>
          ))}
        </div>
      </section>
    </>
  );
}
