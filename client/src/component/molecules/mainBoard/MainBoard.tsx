import styles from "./MainBoard.module.scss";

type Props = {
  title: string;
};

export default function MainBoard({ title }: Props) {
  return <div className={styles.boardWrapper}>하이</div>;
}
