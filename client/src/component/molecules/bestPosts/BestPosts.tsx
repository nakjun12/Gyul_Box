import BestPost from "./BestPost";
import styles from "./BestPosts.module.scss";
type Props = {};

export default function BestPosts({}: Props) {
  return (
    <div className={styles.wrapper}>
      <h2 className={styles.title}>사람들이 많이 본 후기 👀</h2>
      <BestPost />
      <BestPost />
    </div>
  );
}
