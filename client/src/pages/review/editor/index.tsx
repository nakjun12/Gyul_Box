import { useState } from "react";
import { SlArrowDown } from "react-icons/sl";
import LoginButton from "../../../component/atoms/loginButton/LoginButton";
import Buliding from "../../../component/organisms/editorInformation/Buliding";
import GoodBad from "../../../component/organisms/editorInformation/GoodBad";
import Satisfactions from "../../../component/organisms/editorInformation/Satisfaction";
import type { Editor_type, Rate } from "../../../utils/types/types";
import { review_create } from "../../api/editor";
type Props = {};

export default function Index({}: Props) {
  const [onandoff, setOnandoff] = useState<boolean[]>([false, false, false]);
  const [editorData, setEditorData] = useState<Editor_type>({
    buildingName: "",
    advantage: "",
    disadvantage: "",
    adminCost: "",
    residenceYear: "2018-2023",
    floor: "고층",
    buildingType: "빌라",
    rate: {
      interiorRate: 0,
      buildingRate: 0,
      trafficRate: 0,
      securityRate: 0,
      locationRate: 0,
      avgRate: null,
    },
    address: "",
  });
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
  const postHandler = () => {
    // 필수 입력값 체크
    if (!editorData.buildingName) {
      console.log("buildingName이 비어있습니다.");
      return;
    }
    if (!editorData.advantage) {
      console.log("advantage가 비어있습니다.");
      return;
    }
    if (!editorData.disadvantage) {
      console.log("disadvantage가 비어있습니다.");
      return;
    }
    if (!editorData.adminCost) {
      console.log("adminCost가 비어있습니다.");
      return;
    }
    if (!editorData.residenceYear) {
      console.log("residenceYear가 비어있습니다.");
      return;
    }
    if (!editorData.floor) {
      console.log("floor가 비어있습니다.");
      return;
    }
    if (!editorData.buildingType) {
      console.log("buildingType이 비어있습니다.");
      return;
    }
    if (!editorData.address) {
      console.log("address가 비어있습니다.");
      return;
    }
    if (!isValidRate(editorData.rate)) {
      console.log("rate가 유효하지 않습니다.");
      return;
    }

    // 필수 입력값이 모두 채워져 있고 rate가 유효할 경우에 대한 처리
    console.log("게시물을 등록합니다.");
    review_create(editorData);
    // 등록 로직 작성
  };

  const isValidRate = (rate: Rate) => {
    // 각 속성의 값이 유효한지 확인
    if (
      rate.interiorRate === 0 ||
      rate.buildingRate === 0 ||
      rate.trafficRate === 0 ||
      rate.securityRate === 0 ||
      rate.locationRate === 0
    ) {
      return false;
    }

    return true;
  };

  console.log(editorData);
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

        <div
          className="editor_wrapper"
          style={{
            maxHeight: onandoff[0] ? "0px" : "500px",
            overflow: "hidden",
            transition: "all 1s ease-in-out",
          }}
        >
          <Buliding setEditorData={setEditorData} />
        </div>
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

        <div
          className="editor_wrapper"
          style={{
            maxHeight: onandoff[1] ? "0px" : "500px",
            overflow: "hidden",
            transition: "all 1s ease-in-out",
          }}
        >
          <Satisfactions setEditorData={setEditorData} />
        </div>
      </div>
      <div className="editor_wrapper">
        <div className="editor_title">
          <div className="editor_title_wrapper" onClick={() => handler(2)}>
            <label htmlFor="title">장단점 및 비고</label>
            <SlArrowDown
              className="icon"
              style={{
                transform: onandoff[2] ? "rotate(90deg)" : "rotate(0deg)",
                transition: "all 0.3s ease-in-out",
              }}
            />
          </div>
        </div>

        <div
          className="editor_wrapper"
          style={{
            maxHeight: onandoff[2] ? "0px" : "800px",
            overflow: "hidden",
            transition: "all 1.2s ease-in-out",
          }}
        >
          <GoodBad setEditorData={setEditorData} />
        </div>
      </div>
      <div
        className="editor_wrapper"
        style={{ marginTop: "30px", height: "70px" }}
      >
        <div style={{ marginTop: "30px", height: "70px" }}>
          <LoginButton onClick={postHandler}>작성하기</LoginButton>
        </div>
      </div>
    </main>
  );
}
