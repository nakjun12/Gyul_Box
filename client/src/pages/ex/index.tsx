import { useEffect, useRef } from "react";
const kakao = typeof window !== "undefined" ? (window as any).kakao : null;

export default function Ex() {
  //컴포넌트만 안에서 렌더링?

  // Make sure it's client-only

  const markerImageSrc =
    "https://velog.velcdn.com/images/wns450/post/6bbc8c55-8607-430c-b3f4-bf2ab143145d/image.png";
  // const markerImageSize = new kakao.maps.Size(64, 64);
  // const markerImageOption = { offset: new kakao.maps.Point(27, 69) };
  // const markerImage = new kakao.maps.MarkerImage(
  //   markerImageSrc,
  //   markerImageSize,
  //   markerImageOption
  // );
  const mapElement: React.MutableRefObject<kakao.maps.Map | null> =
    useRef(null);

  useEffect(() => {
    kakao.maps.load(() => {
      const mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(33.48972486175701, 126.49657010389818), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };
      if (!mapContainer!.hasChildNodes() && mapContainer !== null) {
        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        const map = new kakao.maps.Map(mapContainer, mapOption);
        mapElement.current = map;

        map.setMaxLevel(9);

        // 클러스터러 객체 생성

        // 초기에 데이터가 존재하면 마커 추가

        // 지도에 컨트롤 추가
        const mapTypeControl = new kakao.maps.MapTypeControl(); // 맵타입?
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
        const zoomControl = new kakao.maps.ZoomControl(); //줌아웃 인,?
        map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

        // 맵 이벤트 핸들러 추가
        kakao.maps.event.addListener(map, "zoom_changed", () => {
          const bounds = map.getBounds();
          const sePoint = bounds.getSouthWest();
          const nePoint = bounds.getNorthEast();

          const XBoundaryValue = [sePoint.getLat(), nePoint.getLat()];
          const YBoundaryValue = [sePoint.getLng(), nePoint.getLng()];
        });

        kakao.maps.event.addListener(map, "dragend", () => {
          const bounds = map.getBounds();
          const sePoint = bounds.getSouthWest();
          const nePoint = bounds.getNorthEast();

          const XBoundaryValue = [sePoint.getLat(), nePoint.getLat()];
          const YBoundaryValue = [sePoint.getLng(), nePoint.getLng()];
        });
      }
    });
  }, []);

  return (
    <>
      <div>index</div>
      <div id="map" style={{ width: "100%", height: "450px" }}></div>
      <div className="btn">index</div>
    </>
  );
}
