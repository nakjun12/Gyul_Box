import { useState } from "react";
import type { SatisfactionMenu } from "../../../utils/types/types";
import DynamicStars from "../stars/DynamicStars";
import styles from "./Satisfaction.module.scss";
type Props = {
  item: SatisfactionMenu;
};

export default function Satisfaction({ item }: Props) {
  const [show, setShow] = useState<number>(0);
  console.log(item);
  return (
    <article className={styles.ment}>
      <div>
        <label htmlFor="title" className={styles.ment_label}>
          {item.label}
        </label>
        <p className={styles.ment_p}>{item.question}</p>
      </div>

      <DynamicStars show={show} setShow={setShow} />
    </article>
  );
}
