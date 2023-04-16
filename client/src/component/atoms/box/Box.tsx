import styles from "./Box.module.scss";

const Box = () => {
  return (
    <ul className={styles.box}>
      <li>
        <div className={styles.first_row}>이메일</div>
        <div className={styles.second_row}>wns450@naver.com</div>
      </li>

      <li>
        <div className={styles.first_row}>닉네임</div>
        <div className={styles.second_row}>김치맨</div>
      </li>
    </ul>
  );
};

export default Box;
