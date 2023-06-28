import styles from "./List.module.scss";

type Props = {
  text?: string;
};

export default function List({ text }: Props) {
  return <button className={styles.category}>{text}</button>;
}
