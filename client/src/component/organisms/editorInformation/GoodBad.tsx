import styles from "./EditorInformation.module.scss";

type Props = {};

export default function GoodBad({}: Props) {
  return (
    <section className={styles.buliding_wrapper}>
      <div className={styles.goodbad_container}>
        <label htmlFor="coast" className={styles.label_style}>
          관리비 🏠
        </label>
        <input
          type="text"
          name="coast"
          placeholder="관리비를 입력해주세요"
          className={styles.input_style}
          maxLength={50}
        />
      </div>

      <div className={styles.goodbad_container}>
        <label htmlFor="good" className={styles.label_style}>
          장점 😀
        </label>
        <textarea
          name="good"
          className={styles.textarea_style}
          maxLength={200}
          placeholder="장점을 입력해주세요"
        />
      </div>

      <div className={styles.goodbad_container}>
        <label htmlFor="bad" className={styles.label_style}>
          단점 😥
        </label>
        <textarea
          name="bad"
          className={styles.textarea_style}
          maxLength={200}
          placeholder="단점을 입력해주세요"
        />
      </div>

      <div className={styles.goodbad_container}>
        <label htmlFor="address" className={styles.label_style}>
          비고 📝
        </label>
        <input
          type="text"
          id="address"
          name="address"
          className={styles.input_style}
          maxLength={50}
          placeholder="특이사항을 입력해주세요"
        />
      </div>
    </section>
  );
}
