import styles from "./LoginButton.module.scss";

type Props = {
  onClick?: () => void;
  children?: React.ReactNode;
};

export default function LoginButton({ onClick, children }: Props) {
  return (
    <button className={styles.btn} onClick={onClick}>
      <div>{children}</div>
    </button>
  );
}
