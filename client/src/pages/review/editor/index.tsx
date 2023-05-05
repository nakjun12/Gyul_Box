import Buliding from "../../../component/organisms/editorInformation/Buliding";

type Props = {};

export default function Index({}: Props) {
  return (
    <main className="review_editor_container">
      <div className="editor_wrapper">
        <label htmlFor="title" className="editor_title">
          거주지 정보
        </label>
        <Buliding />
      </div>
      <label htmlFor="title" className="editor_title">
        만족도
      </label>
      <div>하이</div>
    </main>
  );
}
