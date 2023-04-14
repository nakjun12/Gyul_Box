import styles from "../OrangeButton/orangebutton.module.scss";

type Props = {
  onClick?: () => void;
  children?: React.ReactNode;
};

export default function OrangeButton({ onClick, children }: Props) {
  return (
    <button className={styles.btn} onClick={onClick}>
      {children}
    </button>
  );
}
