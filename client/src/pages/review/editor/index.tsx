import { useState } from "react";
import { SlArrowDown } from "react-icons/sl";
import Buliding from "../../../component/organisms/editorInformation/Buliding";
import Satisfactions from "../../../component/organisms/editorInformation/Satisfaction";

type Props = {};

export default function Index({}: Props) {
  const [onandoff, setOnandoff] = useState<boolean[]>([false, false, false]);
  const [isExpanded, setIsExpanded] = useState(false);

  const toggleAccordion = () => {
    setIsExpanded(!isExpanded);
  };
  const handler = (index: number) => {
    setOnandoff((prev) => {
      return prev.map((_, i) => {
        if (i === index) {
          return !prev[i];
        }
        return prev[i];
      });
    });
  };
  return (
    <main className="review_editor_container">
      <div className="editor_wrapper">
        <h2 className="editor_title">
          <div className="editor_title_wrapper" onClick={toggleAccordion}>
            <label htmlFor="title">거주지 정보</label>
            <SlArrowDown className="icon" onClick={() => handler(1)} />
          </div>
        </h2>

        <section
          className="editor_wrapper"
          style={{
            overflow: "hidden",
            transition: "all 0.3s ease-in-out",
          }}
        >
          <div
            className="editor_wrapper"
            style={{
              transform: isExpanded ? "translateY(-50vh)" : "translateY(0)",
              height: isExpanded ? "0" : "auto",
              overflow: "hidden",
              transition: "all 2s ease-in-out",
            }}
          >
            <Buliding />
          </div>
        </section>
      </div>
      <div className="editor_wrapper">
        <label htmlFor="title" className="editor_title">
          만족도
        </label>
        <Satisfactions />
      </div>
    </main>
  );
}
