import { useRouter } from "next/router";
import { BsPencilFill } from "react-icons/bs";
import { useWindowSize } from "../../../utils/Helper";
import styles from "./EditorButton.module.scss";
type Props = {};

export default function EditorButton({}: Props) {
  const router = useRouter();
  const size = useWindowSize() || 987;

  return (
    <button
      className={styles.editor_button}
      onClick={() => router.push("/review/editor")}
    >
      <BsPencilFill size={size < 600 ? 15 : 30} color="white" />
    </button>
  );
}
