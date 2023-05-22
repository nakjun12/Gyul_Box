import styles from "./DetailTable.module.scss";

export default function DetailTable() {
  return (
    <table className={styles.table}>
      <tbody className={styles.table_wrapper}>
        <tr>
          <td className={styles.title}>건물 용도</td>
          <td className={styles.content}>업무시설</td>
        </tr>
        <tr>
          <td className={styles.title}>건물 구조</td>
          <td className={styles.content}>철근 콘크리트 구조</td>
        </tr>
        <tr>
          <td className={styles.title}>사용 승인일</td>
          <td className={styles.content}>2019.09.23(5년차)</td>
        </tr>
        <tr>
          <td className={styles.title}>세대 수</td>
          <td className={styles.content}>1세대</td>
        </tr>

        <tr>
          <td className={styles.title}>층수</td>
          <td className={styles.content}>지상 3층</td>
        </tr>
      </tbody>
    </table>
  );
}
