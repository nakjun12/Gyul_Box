//my page
import UserBox from "@/component/atoms/box/UserBox";
import Interest from "@/component/atoms/lnterest/Interest";
import { AiOutlineArrowRight } from "react-icons/ai";
import { FaPeopleArrows } from "react-icons/fa";
import { MdOutlineRateReview } from "react-icons/md";
import { RiMessengerLine } from "react-icons/ri";

import styles from "./TabPanel.module.scss";
const MyPagePanel = () => {
  return (
    <section className={styles.tab_panel}>
      <label>회원정보</label>
      <UserBox />
      <label>관심지역</label>
      <Interest text="관심지역" on={true} />

      <label>내가 쓴 글</label>
      <div className={styles.button_group}>
        <MdOutlineRateReview size={60} />
        내가 쓴 리뷰 <AiOutlineArrowRight />
        <FaPeopleArrows size={60} />
        내가 쓴 양도글 <AiOutlineArrowRight />
        <RiMessengerLine size={60} />
        쪽지함 <AiOutlineArrowRight />
      </div>
    </section>
  );
};

export default MyPagePanel;
