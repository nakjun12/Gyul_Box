import { startTransition, useState } from "react";
import type { Editor_type } from "../../../utils/types/types";
import styles from "./EditorInformation.module.scss";

type Props = {
  setEditorData: React.Dispatch<React.SetStateAction<Editor_type>>;
};

export default function GoodBad({ setEditorData }: Props) {
  const [good, setGood] = useState<string>("");
  const [bad, setBad] = useState<string>("");
  const [coast, setCoast] = useState<string>("");

  const handleGoodBad = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCoast(e.target.value);
    startTransition(() => {
      setEditorData((prev) => ({
        ...prev,
        adminCost: e.target.value,
      }));
    });
  };
  const handleGood = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setGood(e.target.value);
    startTransition(() => {
      setEditorData((prev) => ({
        ...prev,
        advantage: e.target.value,
      }));
    });
  };
  const handleBad = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setBad(e.target.value);
    startTransition(() => {
      setEditorData((prev) => ({
        ...prev,
        disadvantage: e.target.value,
      }));
    });
  };

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
          value={coast}
          onChange={handleGoodBad}
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
          value={good}
          onChange={handleGood}
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
          value={bad}
          onChange={handleBad}
        />
      </div>
    </section>
  );
}
