import { Fragment } from "react";
import { Review_data } from "../../../utils/types/types";
import BestPost from "./BestPost";
import styles from "./BestPosts.module.scss";
type Props = {
  data: Review_data[];
  isData: boolean;
};

export default function BestPosts({ data, isData }: Props) {
  const bestPosts = data || [];
  return (
    <div className={styles.wrapper}>
      <h2 className={styles.title}>사람들이 많이 본 후기 👀</h2>
      {data.map((item: Review_data, index) => (
        <Fragment key={index}>
          <BestPost data={item} isData={isData} />
        </Fragment>
      ))}
    </div>
  );
}
