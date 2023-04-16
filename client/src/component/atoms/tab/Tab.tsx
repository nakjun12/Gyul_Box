import styles from "./Tab.module.scss";

interface TabProps {
  text: string;
  index: number;
  handleClick: (index: number) => void;
  active: boolean;
}

const Tab = ({ text, index, handleClick, active }: TabProps) => {
  return (
    <>
      <button
        className={active ? styles.active : styles.tab}
        onClick={() => handleClick(index)}
      >
        {text}
      </button>
    </>
  );
};

export default Tab;
