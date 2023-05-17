import { useEffect } from "react";
import Stars from "../../../component/molecules/stars/Stars";
import Tabs from "../../../component/organisms/tabs/Tabs";
type Props = {};

export default function Index({}: Props) {
  useEffect(() => {
    kakao.maps.load(() => {
      const staticMapContainer = document.getElementById("staticMap"); // 지도를 표시할 div
      if (staticMapContainer !== null) {
        const staticMapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667), // 이미지 지도의 중심좌표
          level: 3, // 이미지 지도의 확대 레벨
          marker: true,
        };

        const staticMap = new kakao.maps.StaticMap(
          staticMapContainer,
          staticMapOption
        );
      }
    });
  }, []);

  return (
    <main className="detail_wrapper">
      <div id="staticMap" style={{ width: "100%", height: "200px" }} />
      <header className="detail_header">
        <div>
          <h3>제주특별자치도 제주시 삼도일동 롯데아파트</h3>
          <p>서울 구로구 구일동 130</p>
        </div>
        <div>
          <span>3.0</span>
          <Stars stars={3} />
        </div>
      </header>
      <Tabs />
    </main>
  );
}
