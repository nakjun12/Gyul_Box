import { useRouter } from "next/router";
import { BsPencilFill } from "react-icons/bs";
import styles from "./EditorButton.module.scss";
type Props = {};

export default function EditorButton({}: Props) {
  const router = useRouter();

  return (
    <button
      className={styles.editor_button}
      onClick={() => router.push("/review/editor")}
    >
      <BsPencilFill size={30} color="white" />
    </button>
  );
}
