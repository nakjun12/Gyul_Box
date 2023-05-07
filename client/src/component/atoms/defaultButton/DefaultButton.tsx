import styles from "./DefaultButton.module.scss";

type Props = {};

export default function DefaultButton({}: Props) {
  return <button className={styles.btn}>버튼</button>;
}
