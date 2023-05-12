import { useState } from "react";
import CategoryStars from "../../molecules/categoryStars/CategoryStars";
import DetailTable from "../../molecules/detailTable/DetailTable";
import Pantagon from "../../molecules/pentagon/Pantagon";
import Tab from "./../../atoms/tab/Tab";
import styles from "./Tabs.module.scss";
interface TabsProps {}

const data = [5, 3, 2.2, 4, 1];

const Tabs = ({}: TabsProps) => {
  const [tabNumber, setTabNumber] = useState<number>(1);

  const handleClick = (index: number) => {
    setTabNumber(index);
  };

  return (
    <>
      <nav className={styles.tabs}>
        <Tab
          text="회원정보"
          index={1}
          handleClick={handleClick}
          active={tabNumber === 1}
        />
        <Tab
          text="내가 쓴 글"
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
        </div>
        <h4 className={styles.detail_top}>리뷰</h4>
      </main>
    </>
  );
};

export default Tabs;
