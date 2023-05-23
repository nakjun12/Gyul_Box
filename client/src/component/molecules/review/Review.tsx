import Image from "next/image";
import type { Review_detail, writer } from "../../../utils/types/types";
import Stars from "../stars/Stars";
import styles from "./Review.module.scss";

type Props = {
  review: Review_detail;
};

export default function Review({ review }: Props) {
  const writer: writer = review.writer || {};

  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <Image src="/icon/gyul1.svg" alt="id" width={50} height={50} />
        <div className={styles.header_id}>
          <div className={styles.header_container}>
            {writer.nickname || "익명"} <Stars stars={review.avgRate || 3} />
            {review.avgRate || 3}
          </div>
          <div>거주기간 {review.residenceYear || "3년"}</div>
        </div>
      </header>

      <div className={styles.content}>
        <h3>관리비</h3>
        <p>{review.adminCost || "관리비가 너무 비싸요"}</p>
      </div>

      <div className={styles.content}>
        <h3 style={{ color: "#ff6000" }}>장점</h3>
        <p>{review.advantage || "관리비가 너무 비싸요"}</p>
      </div>
      <div className={styles.content}>
        <h3 style={{ color: "#8dc53e" }}>단점</h3>
        <p>{review.disadvantage || "관리비가 너무 비싸요"}</p>
      </div>
    </div>
  );
}
