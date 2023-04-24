import styles from "../Lottie/gyul.module.scss";

import Lottie from "react-lottie-player";
import lottieJson from "../../../../public/Lottie/Gyul.json";

export default function Animation() {
  return (
    <div className={styles.lottie}>
      <Lottie loop animationData={lottieJson} play />
    </div>
  );
}
