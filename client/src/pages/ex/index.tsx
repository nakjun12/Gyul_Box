import WhiteButton from "@/component/atoms/whiteButton/WhiteButton";
import { useEffect, useRef, useState } from "react";

const kakao = typeof window !== "undefined" ? (window as any).kakao : null;

interface Position {
  lat: number;
  lng: number;
}

export default function Ex() {
  const [position, setPosition] = useState<Position>({
    lat: 33.48972486175701,
    lng: 126.49657010389818,
  });
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
      const geocoder = new kakao.maps.services.Geocoder();
      const mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(33.48972486175701, 126.49657010389818), // 지도의 중심좌표
          level: 7, // 지도의 확대 레벨
        };
      if (!mapContainer!.hasChildNodes() && mapContainer !== null) {
        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        const map = new kakao.maps.Map(mapContainer, mapOption);
        mapElement.current = map;
        const imageSrc =
          "https://velog.velcdn.com/images/wns450/post/6bbc8c55-8607-430c-b3f4-bf2ab143145d/image.png";

        map.setMaxLevel(10);
        const imageSize = new kakao.maps.Size(60, 60);
        const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        const latlng = new kakao.maps.LatLng(position.lat, position.lng);
        const num = 1;
        const id = 24;
        const latlng2 = new kakao.maps.LatLng(
          33.48865701020525,
          126.49173155558593
        );

        const getTexts = (count: number) => {
          // 한 클러스터 객체가 포함하는 마커의 개수에 따라 다른 텍스트 값을 표시합니다
          if (count < 10) {
            return "삐약";
          } else if (count < 30) {
            return "꼬꼬";
          } else if (count < 50) {
            return "꼬끼오";
          } else {
            return "치멘";
          }
        };

        // 클릭한 위치의 위도는 33.48865701020525 이고,
        // index.tsx:69 경도는 126.49173155558593 입니다
        const marker = new kakao.maps.Marker({
          position: [33.48972486175701, 126.49657010389818],

          title: "현재 위치",
          clickable: true,
          image: markerImage,
        });

        const popupWindow = new kakao.maps.CustomOverlay({
          position: latlng,
          clickable: true,
        });
        //id단축
        const markerBox = document.createElement("button");
        markerBox.setAttribute("class", "marker_box");
        markerBox.setAttribute("value", `${id}`);
        markerBox.onclick = function () {
          console.log("마커마커");
        };
        markerBox.textContent = String(num);

        popupWindow.setContent(markerBox);
        // popupWindow.setMap(map);
        //동계산하는거와 줌에 따른 비교가 필요함
        const clusterer = new kakao.maps.MarkerClusterer({
          map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
          averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
          minLevel: 5, // 클러스터 할 최소 지도 레벨

          styles: [
            {
              width: "100%",
              height: "30px",
              background: "white",
              borderRadius: "20px",
              color: "#ff6000",
              textAlign: "center",
              border: "1px solid #ff6000",
              cursor: "pointer",
              fontWeight: "400",
              padding: "0 10px",
              paddingTop: "3.5px",
            },
          ],
        });
        const setClustererTexts = async function () {
          const coord = new kakao.maps.LatLng(
            37.56496830314491,
            126.93990862062978
          );
          const geocoder = new kakao.maps.services.Geocoder();
          const result = await new Promise<string>((resolve) => {
            geocoder.coord2RegionCode(
              coord.getLng(),
              coord.getLat(),
              (result: any, status: any) => {
                if (status === kakao.maps.services.Status.OK) {
                  const address_name = result[0].address_name;
                  console.log(address_name);
                  resolve(address_name);
                } else {
                  resolve("");
                }
              }
            );
          });
          console.log(result);
          return result;
        };

        const setTextsForClusterer = async function () {
          const texts = await setClustererTexts();
          clusterer.setTexts(function (size: number) {
            return texts;
          });
        };

        setTextsForClusterer();

        clusterer.setCalculator(function (size: number) {
          var index;
          // 지도의  레벨을 얻어옵니다
          const level = map.getLevel();

          // 클러스터에 포함된 마커의 개수가 50개 미만이면 리턴할 index값을 0으로 설정한다.
          if (level > 4 && level < 8) {
            index = 0;
          } else {
            index = 1;
          }

          return index;
        });
        // 클러스터러 객체 생성

        // const markerList: kakao.maps.Marker[] = [
        //   marker,
        //   marker,
        //   marker,
        //   marker,
        // ];
        // const markers: kakao.maps.CustomOverlay | kakao.maps.Marker[] = [
        //   popupWindow,
        //   marker,
        //   popupWindow,
        // ];
        // clusterer.addMarkers(markers);
        const options = {
          map: map,
          clickable: true,
          content: `<div style="cursor: pointer; font-weight: bold; height: 30px; width: 30px; background-color: white;  color: #ff6000; border: 1px solid #ff6000; border-radius: 50%; "
          onmouseover="this.style.color='white'; this.style.backgroundColor='#ff6000';"
      onmouseout="this.style.color='#ff6000'; this.style.backgroundColor='white';"
     >${num}</div>`,
          position: latlng2,
        };
        const customOverlay = new kakao.maps.CustomOverlay(options);
        // customOverlay.setMap(map);
        // popupWindow.setMap(map);
        const markers: kakao.maps.CustomOverlay | kakao.maps.Marker[] = [
          popupWindow,
          customOverlay,
          popupWindow,
        ];
        clusterer.addMarkers(markers);
        //첫번재 경우의 수, 오버레이 2개를 넣어봄
        //두번째 마커가 안되는 지 확인할 것
        kakao.maps.event.addListener(
          clusterer,
          "clusterclick",
          function (cluster: kakao.maps.Cluster) {
            // 현재 지도 레벨에서 1레벨 확대한 레벨
            var level = map.getLevel() - 1;
            console.log("하이");
            // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
            map.setLevel(level, { anchor: cluster.getCenter() });
          }
        );
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

        kakao.maps.event.addListener(map, "center_changed", function () {
          // 지도의  레벨을 얻어옵니다
          const level = map.getLevel();

          // 지도의 중심좌표를 얻어옵니다
          const latlng = map.getCenter();

          const message =
            "지도 레벨은 " +
            level +
            " 이고" +
            "중심 좌표는 위도 " +
            latlng.getLat() +
            ", 경도 " +
            latlng.getLng() +
            "입니다";

          console.log(message);
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
  }, [position]);

  return (
    <>
      <WhiteButton />
      <div>index</div>
      <div id="map" style={{ width: "100%", height: "450px" }}></div>
      <div className="btn">index</div>
    </>
  );
}
