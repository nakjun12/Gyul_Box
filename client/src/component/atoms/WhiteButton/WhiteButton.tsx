import styles from "./WhiteButton.module.scss";

type Props = {};

export default function WhiteButton({}: Props) {
  return <button className={styles.btn}>버튼</button>;
}
