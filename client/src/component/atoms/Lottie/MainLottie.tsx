import styles from "../lottie/MainLottie.module.scss";

import Lottie from "react-lottie-player";
import lottieJson from "../../../../public/Lottie/Gyul.json";

export default function MainLottie() {
  return (
    <div className={styles.lottie}>
      <Lottie loop animationData={lottieJson} play />
    </div>
  );
}
