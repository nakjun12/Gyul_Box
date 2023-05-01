import Stars from "../stars/Stars";
import styles from "./SimpleHouse.module.scss";
type Props = {};

export default function SimpleHouse({}: Props) {
  return (
    <section className={styles.section}>
      <div>
        <h3>감귤 농장 아파트</h3>
        <p>감귤국 도로 23</p>
      </div>
      <div>
        <p>3.2 (리뷰 5)</p>
        <Stars stars={4} />

        <p>리뷰보기 {"->"}</p>
      </div>
    </section>
  );
}
