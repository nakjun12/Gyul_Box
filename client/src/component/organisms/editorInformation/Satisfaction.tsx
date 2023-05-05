import DynamicStars from "../../molecules/stars/DynamicStars";
import styles from "./EditorInformation.module.scss";
type Props = {};

export default function Satisfaction({}: Props) {
  return (
    <>
      <section className={styles.buliding_wrapper}>
        <DynamicStars />
      </section>
    </>
  );
}
