import { Fragment, useState } from "react";
import type { Review_detail } from "../../../utils/types/types";
import Review from "../../molecules/review/Review";
import styles from "./Reviews.module.scss";

type Props = {
  reviews: Review_detail[];
};

export default function Reviews({ reviews }: Props) {
  const [visibleReviews, setVisibleReviews] = useState(reviews.slice(0, 2));
  const [page, setPage] = useState(1);
  const reviewsPerPage = 4;
  const final = Math.ceil((reviews.length - 2) / reviewsPerPage) + 1;

  // 2 , 4 4 4

  const handleSeeMore = () => {
    const nextPage = page + 1;
    const startIndex = (nextPage - 1) * reviewsPerPage - 2;
    const endIndex = startIndex + reviewsPerPage;
    const nextReviews = reviews.slice(startIndex, endIndex);
    console.log(reviews.length, visibleReviews.length, "start end");
    setVisibleReviews((prevReviews) => [...prevReviews, ...nextReviews]);
    setPage((el) => {
      if (el >= final) return el;
      return nextPage;
    });
  };

  return (
    <div className={styles.container}>
      {visibleReviews.map((review: Review_detail, i: number) => (
        <Fragment key={i}>
          <Review review={review} />
        </Fragment>
      ))}

      {reviews.length > visibleReviews.length && (
        <div className={styles.seemore}>
          <button className={styles.more_btn} onClick={handleSeeMore}>
            더보기 {page}/{final}
          </button>
        </div>
      )}
    </div>
  );
}
