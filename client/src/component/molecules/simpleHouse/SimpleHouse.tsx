import { FiArrowRight } from "react-icons/fi";
import Stars from "../stars/Stars";
import styles from "./SimpleHouse.module.scss";

type Props = {};

export default function SimpleHouse({}: Props) {
  return (
    <section className={styles.section}>
      <div>
        <h3>감귤 농장 아파트</h3>
        <p className={styles.left}>감귤국 도로 23</p>
      </div>
      <div>
        <p className={styles.right_up}>
          3.2 <span className={styles.right_up_span}>(리뷰 5)</span>
        </p>
        <Stars stars={4} />
        <p className={styles.right_down}>
          리뷰보기 <FiArrowRight size={"20px"} />
        </p>
      </div>
    </section>
  );
}
