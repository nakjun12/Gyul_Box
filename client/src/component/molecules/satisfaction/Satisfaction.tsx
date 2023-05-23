import { useEffect, useState } from "react";
import type { SatisfactionMenu } from "../../../utils/types/types";
import DynamicStars from "../stars/DynamicStars";
import styles from "./Satisfaction.module.scss";
type Props = {
  item: SatisfactionMenu;
  handleSatisfaction: (value: string, rate: number) => void;
};

export default function Satisfaction({ item, handleSatisfaction }: Props) {
  const [show, setShow] = useState<number>(0);
  useEffect(() => {
    handleSatisfaction(item.value, show);
  }, [show, item.value, handleSatisfaction]);

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
