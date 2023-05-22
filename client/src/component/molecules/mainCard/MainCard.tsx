import { HiOutlineArrowRight } from "react-icons/hi";
import { convertTimeAgo } from "../../../utils/Helper";
import type { Review_data } from "../../../utils/types/types";
import YellowStar from "../../atoms/yellowStars/YellowStars";
import styles from "./MainCard.module.scss";
type props = {
  data: Review_data;
};

export function MainCard({ data }: props) {
  console.log(data);
  return (
    <>
      <article className={styles.wrapper}>
        <div className={styles.box}>
          <h3 className={styles.title}>{data.address || "서울 서대문구"}</h3>
          <h4 className={styles.sub_title}>
            {convertTimeAgo(data.createdAt) || "주공아파트"}
          </h4>
          <hr />
          <div className={styles.content}>
            {data.advantage ||
              `쿵쾅쿵쾅 뛰기 좋았어요dddddddddddddd
            ddddddddddddddddddddddddddddddddddddddddddd`}
          </div>

          <div className={styles.footer}>
            <div className={styles.footer_content}>
              <YellowStar />
              &nbsp; {data.avgRate || 3.0}
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
