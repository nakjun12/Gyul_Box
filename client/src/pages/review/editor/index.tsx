import { useState } from "react";
import { SlArrowDown } from "react-icons/sl";
import Buliding from "../../../component/organisms/editorInformation/Buliding";
import Satisfactions from "../../../component/organisms/editorInformation/Satisfaction";

type Props = {};

export default function Index({}: Props) {
  const [onandoff, setOnandoff] = useState<boolean[]>([false, false, false]);

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
        <div className="editor_title">
          <div className="editor_title_wrapper" onClick={() => handler(0)}>
            <label htmlFor="title">거주지 정보</label>
            <SlArrowDown
              className="icon"
              style={{
                transform: onandoff[0] ? "rotate(90deg)" : "rotate(0deg)",
                transition: "all 0.3s ease-in-out",
              }}
            />
          </div>
        </div>

        <section className="editor_wrapper" style={{}}>
          <div
            className="editor_wrapper"
            style={{
              maxHeight: onandoff[0] ? "0px" : "500px",
              overflow: "hidden",
              transition: "all 1s ease-in-out",
            }}
          >
            <Buliding />
          </div>
        </section>
      </div>
      <div className="editor_wrapper">
        <div className="editor_title">
          <div className="editor_title_wrapper" onClick={() => handler(1)}>
            <label htmlFor="title">만족도</label>
            <SlArrowDown
              className="icon"
              style={{
                transform: onandoff[1] ? "rotate(90deg)" : "rotate(0deg)",
                transition: "all 0.3s ease-in-out",
              }}
            />
          </div>
        </div>
        <section className="editor_wrapper" style={{}}>
          <div
            className="editor_wrapper"
            style={{
              maxHeight: onandoff[1] ? "0px" : "500px",
              overflow: "hidden",
              transition: "all 1s ease-in-out",
            }}
          >
            <Satisfactions />
          </div>
        </section>
      </div>
      <div className="editor_wrapper">
        <div className="editor_title">
          <div className="editor_title_wrapper" onClick={() => handler(2)}>
            <label htmlFor="title">만족도</label>
            <SlArrowDown
              className="icon"
              style={{
                transform: onandoff[2] ? "rotate(90deg)" : "rotate(0deg)",
                transition: "all 0.3s ease-in-out",
              }}
            />
          </div>
        </div>
        <section className="editor_wrapper">
          <div
            className="editor_wrapper"
            style={{
              maxHeight: onandoff[2] ? "0px" : "500px",
              overflow: "hidden",
              transition: "all 1s ease-in-out",
            }}
          >
            <Satisfactions />
          </div>
        </section>
      </div>
    </main>
  );
}
