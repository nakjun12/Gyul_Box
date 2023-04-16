import Tab from "@/component/atoms/tab/Tab";
import MyPagePanel from "@/component/molecules/tabPanel/MypagePanel";
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
      <main>
        {tabNumber === 1 && (
          <>
            <MyPagePanel />
          </>
        )}
        {tabNumber === 2 && <div>내가 쓴 글</div>}
      </main>
    </>
  );
};

export default Tabs;
