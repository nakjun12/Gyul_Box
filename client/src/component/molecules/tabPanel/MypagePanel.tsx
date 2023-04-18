//my page
import UserBox from "@/component/atoms/box/UserBox";
import Interest from "@/component/atoms/lnterest/Interest";
import styles from "./TabPanel.module.scss";
const MyPagePanel = () => {
  return (
    <section className={styles.tab_panel}>
      <label>회원정보</label>
      <UserBox />
      <label>회원정보</label>
      <Interest text="쪽지함" on={true} />
      <label>회원정보</label>
      <Interest text="관심지역" on={true} />
    </section>
  );
};

export default MyPagePanel;
