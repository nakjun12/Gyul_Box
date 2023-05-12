import Image from "next/image";
import Stars from "../stars/Stars";
import styles from "./Review.module.scss";
type Props = {};

export default function Review({}: Props) {
  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <Image src="/icon/gyul1.svg" alt="id" width={50} height={50} />
        <div className={styles.header_id}>
          <div className={styles.header_container}>
            익명 <Stars stars={3} /> 3.0
          </div>
          <div>거주기간</div>
        </div>
      </header>

      <div className={styles.content}>
        <h3 style={{ color: "" }}>관리비</h3>
        <p>관리비가 너무 비싸요</p>
      </div>

      <div className={styles.content}>
        <h3 style={{ color: "#ff6000" }}>장점</h3>
        <p>관리비가 너무 비싸요</p>
      </div>
      <div className={styles.content}>
        <h3 style={{ color: "#8dc53e" }}>단점</h3>
        <p>관리비가 너무 비싸요</p>
      </div>
    </div>
  );
}
