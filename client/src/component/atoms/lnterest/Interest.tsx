import styles from "./lnterest.module.scss";

type Props = {
  text?: string;
  on?: boolean;
  handler?: () => void;
};

export default function Interest({ text, on = false, handler }: Props) {
  return (
    <button
      onClick={handler}
      className={on ? styles.click_able : styles.interest}
    >
      {text}
    </button>
  );
}
