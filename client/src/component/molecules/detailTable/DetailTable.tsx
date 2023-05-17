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
          <td className={styles.title}>건물 용도</td>
          <td className={styles.content}>업무시설</td>
        </tr>
        <tr>
          <td className={styles.title}>건물 구조</td>
          <td className={styles.content}>철근 콘크리트 구조</td>
        </tr>
        <tr>
          <td className={styles.title}>건물 용도</td>
          <td className={styles.content}>업무시설</td>
        </tr>
        <tr>
          <td className={styles.title}>건물 구조</td>
          <td className={styles.content}>철근 콘크리트 구조</td>
        </tr>
      </tbody>
    </table>
  );
}
