import styles from "./GrayStar.module.scss";

type Props = {};

export default function GrayStar({}: Props) {
  return <div className={styles.star}>★</div>;
}
