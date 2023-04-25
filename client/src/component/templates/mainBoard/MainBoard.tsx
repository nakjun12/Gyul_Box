import BoardContents from "../../molecules/boardContents/BoardContents";
import styles from "./MainBoard.module.scss";

type Props = {
  title: string;
};

export default function MainBoard({ title }: Props) {
  return (
    <div className={styles.boardWrapper}>
      <div className={styles.boardContentWrapper}>
        <div className={styles.boardContentDiv}>
          <div className={styles.boardTitle}>
            <div className={styles.boardTitle}>
              <div className={styles.boardCategoryDiv}>{title}</div>
              <button className={styles.moreButton}>더보기</button>
            </div>
          </div>
        </div>
      </div>
      <div className={styles.boardContent}>
        <BoardContents />
      </div>
    </div>
  );
}
