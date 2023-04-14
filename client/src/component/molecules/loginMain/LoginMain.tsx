import { useEffect, useState } from "react";
import styles from "./LoginMain.module.scss";
export default function LoginMain() {
  const [isChecked, setIsChecked] = useState<boolean>(false);

  function handleCheckboxChange(event: React.ChangeEvent<HTMLInputElement>) {
    setIsChecked(event.target.checked);
    const newValue = event.target.checked;
    setIsChecked(newValue);
    localStorage.setItem("isChecked", newValue.toString());
  }

  useEffect(() => {
    const savedValue = window.localStorage.getItem("isChecked");
    if (savedValue !== null) {
      setIsChecked(savedValue === "true");
    }
  }, []);

  console.log(isChecked);
  return (
    <section className={styles.login}>
      <h1 className={styles.loginH1}>로그인</h1>
      <button className={styles.kakao}>카카오 로그인</button>
      <button className={styles.loginButton}>게스트 로그인</button>
      <button className={styles.bgButton}>로그인 없이 이용하기</button>
      <div className={styles.checkboxContainer}>
        <input
          type="checkbox"
          id="auto-login"
          className={styles.input}
          checked={isChecked}
          onChange={handleCheckboxChange}
        />
        <label className={styles.label}>자동 로그인</label>
      </div>
    </section>
  );
}
