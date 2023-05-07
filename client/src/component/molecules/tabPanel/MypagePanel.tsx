//my page
import { BiMap } from "react-icons/bi";
import { FaPeopleArrows } from "react-icons/fa";
import { GrClose } from "react-icons/gr";
import { MdOutlineRateReview } from "react-icons/md";
import { RiMessengerLine } from "react-icons/ri";
import UserBox from "./../../atoms/box/UserBox";
import styles from "./MypagePanel.module.scss";
const MyPagePanel = () => {
  return (
    <section className={styles.tab_panel}>
      <label>회원정보</label>
      <UserBox />
      <label>관심지역</label>

      <div className={styles.cardbox}>
        <div className={styles.text}>관심지역</div>
        <BiMap size={16} />
      </div>

      <div className={styles.box}>
        <span> 서울 서대문구 대천동 </span>
        <GrClose size={20} />
      </div>

      <ul className={styles.button_group}>
        <li className={styles.cardbox}>
          <div className={styles.text}>내가 쓴 리뷰</div>
          <MdOutlineRateReview size={16} />
        </li>
        <li className={styles.cardbox}>
          <div className={styles.text}>내가 쓴 양도글</div>
          <FaPeopleArrows size={16} />
        </li>
        <li className={styles.cardbox}>
          <div className={styles.text}>쪽지함</div>
          <RiMessengerLine size={16} />
        </li>
      </ul>
    </section>
  );
};

export default MyPagePanel;
