import { IoReloadOutline } from "react-icons/io5";
import styles from "./Reload.module.scss";
type Props = {};

export default function Reload({}: Props) {
  return (
    <div className={styles.container}>
      <IoReloadOutline />
      <span>연동김치</span>검색
    </div>
  );
}
