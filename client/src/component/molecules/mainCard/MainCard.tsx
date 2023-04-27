import { HiOutlineArrowRight } from "react-icons/hi";
import YellowStar from "../../atoms/yellowStar/YellowStart";
import styles from "./MainCard.module.scss";
export function MainCard() {
  return (
    <>
      <article className={styles.wrapper}>
        <div className={styles.box}>
          <h3 className={styles.title}>서울 서대문구</h3>
          <h4 className={styles.sub_title}>고대</h4>
          <hr />
          <div className={styles.content}>
            쿵쾅쿵쾅 뛰기 좋았어요dddddddddddddd
            ddddddddddddddddddddddddddddddddddddddddddd
          </div>

          <div className={styles.footer}>
            <div className={styles.footer_content}>
              <YellowStar />
              &nbsp; 3.0
            </div>
            <div className={styles.footer_content}>
              리뷰 보기 &nbsp;
              <HiOutlineArrowRight />
            </div>
          </div>
        </div>
      </article>
    </>
  );
}
