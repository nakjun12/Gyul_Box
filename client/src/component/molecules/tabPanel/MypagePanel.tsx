//my page
import UserBox from "@/component/atoms/box/UserBox";
import InterestModal from "@/component/templates/modal/InterestModal";
import { BiMap } from "react-icons/bi";
import { FaPeopleArrows } from "react-icons/fa";
import { GrClose } from "react-icons/gr";
import { MdOutlineRateReview } from "react-icons/md";
import { RiMessengerLine } from "react-icons/ri";
import styles from "./TabPanel.module.scss";
const MyPagePanel = () => {
  return (
    <section className={styles.tab_panel}>
      <label>회원정보</label>
      <UserBox />
      <label>관심지역</label>
      <InterestModal>
        <div className={styles.cardbox}>
          <BiMap size={50} />
          <div className={styles.text}>관심지역</div>
        </div>
      </InterestModal>
      <div className={styles.box}>
        <span> 서울 서대문구 대천동 </span>
        <GrClose size={20} />
      </div>

      <ul className={styles.button_group}>
        <li className={styles.cardbox}>
          <MdOutlineRateReview size={50} />
          <div className={styles.text}>내가 쓴 리뷰</div>
        </li>
        <li className={styles.cardbox}>
          <FaPeopleArrows size={50} />
          <div className={styles.text}>내가 쓴 양도글</div>
        </li>
        <li className={styles.cardbox}>
          <RiMessengerLine size={50} />
          <div className={styles.text}>쪽지함</div>
        </li>
      </ul>
    </section>
  );
};

export default MyPagePanel;
