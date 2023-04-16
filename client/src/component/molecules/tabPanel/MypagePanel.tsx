//my page
import Box from "@/component/atoms/box/box";
import Interest from "@/component/atoms/lnterest/Interest";
import styles from "./tabPanel.module.scss";
const MyPagePanel = () => {
  return (
    <section className={styles.tab_panel}>
      김치
      <Interest text="내 정보" />
      <Box />
      <Interest text="내 쪽지" on={true} />
      <Interest text="관심지역" on={true} />
    </section>
  );
};

export default MyPagePanel;
