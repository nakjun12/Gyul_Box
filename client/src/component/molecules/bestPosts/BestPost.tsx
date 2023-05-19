import type { Review_data } from "../../../utils/types/types";
import Recommand from "../../atoms/recommand/Recommand";
import YellowStar from "../../atoms/yellowStars/YellowStars";
import styles from "./BestPosts.module.scss";

type Props = {
  data: Review_data;
  isData: boolean;
};

export default function BestPost({ data }: Props) {
  console.log(data.address);
  return (
    <article className={styles.bestposts}>
      <header className={styles.bestposts_title}>
        <div>{data.address || "서울 광진구"}</div>
        <div className={styles.left}>
          <YellowStar />
          &nbsp; {data.avgRate || 4} &emsp;
          <Recommand />
          &nbsp; {data.reviewLikes || 2}
        </div>
      </header>
      <div className={styles.bestposts_list}>
        <h3 className={styles.bestposts_list_left_good}>장점</h3>
        <div className={styles.bestposts_list_right}>
          {data.advantage ||
            `역이랑 편의점 좋아요 가까운 점은 여기저기 다닐 수 있고 최고의
          놀이터지만 짜증이 나는 경우도 있지만 최고에요 아이들이 많아서
          강아지들도 많이 살고 최고의
          놀ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
          `}
        </div>
      </div>
      <div className={styles.bestposts_list}>
        <h3 className={styles.bestposts_list_left_bad}>단점</h3>
        <div className={styles.bestposts_list_right}>
          {data.disadvantage ||
            `역이랑 편의점 좋아요 가까운 점은 여기저기 다닐 수 있고 최고의
          놀이터지만 짜증이 나는 경우도 있지만 최고에요 아이들이 많아서
          강아지들도 많이 살고 최고의
          놀ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
          `}{" "}
        </div>
      </div>
    </article>
  );
}
