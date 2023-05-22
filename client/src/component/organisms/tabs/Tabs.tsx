import { useEffect, useRef, useState } from "react";
import CategoryStars from "../../molecules/categoryStars/CategoryStars";
import DetailTable from "../../molecules/detailTable/DetailTable";
import Pantagon from "../../molecules/pentagon/Pantagon";
import Reviews from "../reviews/Reviews";
import Tab from "./../../atoms/tab/Tab";
import styles from "./Tabs.module.scss";
interface TabsProps {}

const data = [5, 3, 2.2, 4, 1];

const Tabs = ({}: TabsProps) => {
  const [tabNumber, setTabNumber] = useState<number>(1);
  const [isCheck, setIsCheck] = useState<boolean>(false);
  const reviewSectionRef = useRef<HTMLDivElement>(null);
  useEffect(() => {
    if (reviewSectionRef.current) {
      // tabNumber의 값이 변경될 때마다 스크롤 위치를 조정합니다.
      if (tabNumber === 2) {
        // 리뷰 섹션으로 스크롤합니다.
        reviewSectionRef.current.scrollIntoView({
          behavior: "smooth",
          block: "start",
        });
      } else if (tabNumber === 1) {
        // 페이지 맨 위로 스크롤합니다.
        window.scrollTo({ top: 0, behavior: "smooth" });
      }
    }
  }, [tabNumber, isCheck]);
  const handleClick = (index: number) => {
    setTabNumber(index);
    setIsCheck(!isCheck);
  };

  return (
    <>
      <nav className={styles.tabs}>
        <Tab
          text="건물 정보"
          index={1}
          handleClick={handleClick}
          active={tabNumber === 1}
        />
        <Tab
          text="리뷰 댓글"
          index={2}
          handleClick={handleClick}
          active={tabNumber === 2}
        />
      </nav>
      <main className={styles.detail_wrapper}>
        <h4 className={styles.detail_top}>건물 정보</h4>
        <DetailTable />
        <h4 className={styles.detail_top}>종합 평가</h4>
        <div className={styles.group}>
          <Pantagon data={data} />
          <CategoryStars />
          <div ref={reviewSectionRef} />
        </div>
        <div className={styles.scroll} ref={reviewSectionRef} />
        <h4 className={styles.detail_top}>n개의 리뷰</h4>

        <Reviews />
        <button className={styles.more_btn}>더보기 1/4</button>

        {/* <DragCarousel /> */}
      </main>
    </>
  );
};

export default Tabs;
