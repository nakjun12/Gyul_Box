import BoardContents from "../../molecules/boardContents/BoardContents";
import styles from "../mainBoard/MianBpard.module.scss";

type Props = {};

export default function MainBoard({}: Props) {
  return (
    <div className={styles.boardWrapper}>
      <div className={styles.boardCategoryWrapper}>
        <div className={styles.boardCategoryDiv}>최신순</div>
      </div>
      <div className={styles.boardContentWrapper}>
        <div className={styles.boardContentDiv}>
          <div className={styles.boardTitle}>
            <div className={styles.boardTitle}>
              <div>공지사항</div>
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
