import Recommand from "../../atoms/recommand/Recommand";
import YellowStar from "../../atoms/yellowStars/YellowStars";
import styles from "./BestPosts.module.scss";
type Props = {};

export default function BestPosts({}: Props) {
  return (
    <div className={styles.wrapper}>
      <h2 className={styles.title}>사람들이 많이 본 후기 👀</h2>
      <article className={styles.bestposts}>
        <header className={styles.bestposts_title}>
          <div>서울 광진구</div>
          <div className={styles.left}>
            <YellowStar />
            &nbsp; 1.0 &emsp;
            <Recommand />
            &nbsp; 37
          </div>
        </header>
        <div className={styles.bestposts_list}>
          <h3 className={styles.bestposts_list_left_good}>장점</h3>
          <div className={styles.bestposts_list_right}>
            역이랑 편의점 좋아요 가까운 점은 여기저기 다닐 수 있고 최고의
            놀이터지만 짜증이 나는 경우도 있지만 최고에요 아이들이 많아서
            강아지들도 많이 살고 최고의
            놀ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
          </div>
        </div>
        <div className={styles.bestposts_list}>
          <h3 className={styles.bestposts_list_left_bad}>단점</h3>
          <div className={styles.bestposts_list_right}>
            역이랑 편의점 좋아요 가까운 점은 여기저기 다닐 수 있고 최고의
            놀이터지만 짜증이 나는 경우도 있지만 최고에요 아이들이 많아서
            강아지들도 많이 살고 최고의
            놀ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
          </div>
        </div>
      </article>
      <article className={styles.bestposts}>
        <header className={styles.bestposts_title}>
          <div>서울 광진구</div>
          <div className={styles.left}>
            <YellowStar />
            &nbsp; 1.0 &emsp;
            <Recommand />
            &nbsp; 37
          </div>
        </header>
        <div className={styles.bestposts_list}>
          <h3 className={styles.bestposts_list_left_good}>장점</h3>
          <div className={styles.bestposts_list_right}>
            역이랑 편의점 좋아요 가까운 점은 여기저기 다닐 수 있고 최고의
            놀이터지만 짜증이 나는 경우도 있지만 최고에요 아이들이 많아서
            강아지들도 많이 살고 최고의
            놀ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
          </div>
        </div>
        <div className={styles.bestposts_list}>
          <h3 className={styles.bestposts_list_left_bad}>단점</h3>
          <div className={styles.bestposts_list_right}>
            역이랑 편의점 좋아요 가까운 점은 여기저기 다닐 수 있고 최고의
            놀이터지만 짜증이 나는 경우도 있지만 최고에요 아이들이 많아서
            강아지들도 많이 살고 최고의
            놀ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ
          </div>
        </div>
      </article>
    </div>
  );
}
