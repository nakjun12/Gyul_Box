import Tab from "@/component/atoms/tab/Tab";
import { useState } from "react";
import styles from "./Tabs.module.scss";

interface TabsProps {}

const Tabs = ({}: TabsProps) => {
  const [tabNumber, setTabNumber] = useState<number>(1);

  const handleClick = (index: number) => {
    setTabNumber(index);
  };

  console.log(tabNumber);
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
      <section>
        {tabNumber === 1 && <div>회원정보</div>}
        {tabNumber === 2 && <div>내가 쓴 글</div>}
      </section>
    </>
  );
};

export default Tabs;
