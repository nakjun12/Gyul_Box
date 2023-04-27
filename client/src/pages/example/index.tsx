import { KAKAKO_JAVASCRIPT } from "../../utils/ConfigHelper";
import MapButton from "./../../component/atoms/mapButton/MapButton";

import { useEffect, useRef, useState } from "react";
interface Position {
  lat: number;
  lng: number;
}

interface SearchResult {
  x: number;
  y: number;
}
const Map = () => {
  const [XBoundary, setXBoundary] = useState([0, 0]);
  const [YBoundary, setYBoundary] = useState([0, 0]);
  const [map, setMap] = useState<kakao.maps.Map | null>(null);
  const mapRef = useRef<HTMLDivElement>(null);
  const [name, setName] = useState<string | null>(null);
  const [marker, setMarker] = useState<kakao.maps.Marker | null>(null);
  const [infoWindow, setInfoWindow] = useState<kakao.maps.InfoWindow | null>(
    null
  );
  const isCheck = useRef<boolean>(false);

  // lat: 33.48972486175701,33.450701, 126.570667
  // lng: 126.49657010389818,
  const [position, setPosition] = useState<Position | null>({
    lat: 33.48972486175701,
    lng: 126.49657010389818,
  });
  const [address, setAddress] = useState<string | null>(null);

  // 마커 이미지의 이미지 주소입니다

  useEffect(() => {
    const script = document.createElement("script");
    script.async = true;
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${KAKAKO_JAVASCRIPT}&autoload=false`;
    document.head.appendChild(script);

    script.onload = () => {
      kakao.maps.load(() => {
        const options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 5, //줌 레벨
        };

        if (mapRef.current !== null) {
          // null 체크 추가

          setMap(new kakao.maps.Map(mapRef.current, options));
        }
      });
    };

    return () => {
      document.head.removeChild(script);
    };
  }, []);

  useEffect(() => {
    if (map !== null) {
      kakao.maps.event.addListener(map, "click", (e: any) => {
        const latlng = e.latLng;
        const message = `클릭한 위치의 위도는 ${latlng.getLat()} 이고,`;
        console.log(message);
        const message2 = `경도는 ${latlng.getLng()} 입니다`;
        console.log(message2);
      });
    }
  }, [map]);

  // useEffect(() => {
  //   if (map !== null) {
  //     // 위치 권한을 허용한 후에 현재 위치 가져오기
  //     navigator.geolocation.getCurrentPosition(
  //       (position) => {
  //         const { latitude, longitude } = position.coords;
  //         setPosition({ lat: latitude, lng: longitude });
  //       },
  //       (error) => {
  //         console.error(error);
  //       }
  //     );
  //   }
  // }, [map]);
  console.log(name);
  useEffect(() => {
    console.log(isCheck.current);
    if (map !== null && position !== null) {
      //체크필요

      const imageSrc =
        "https://velog.velcdn.com/images/wns450/post/6bbc8c55-8607-430c-b3f4-bf2ab143145d/image.png";

      // 마커 이미지의 이미지 크기 입니다
      const imageSize = new kakao.maps.Size(60, 60);

      // 마커 이미지를 생성합니다
      const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      const latlng = new kakao.maps.LatLng(position.lat, position.lng);
      const num = 1;
      const id = 24;

      const popupWindow = new kakao.maps.CustomOverlay({
        position: latlng,
        clickable: true,
      });
      //id단축
      const markerBox = document.createElement("button");
      markerBox.setAttribute("class", "marker_box");
      markerBox.setAttribute("value", `${"이름"}`);
      markerBox.onclick = function () {
        setName(markerBox.value);
      };
      markerBox.textContent = String(num);

      popupWindow.setContent(markerBox);
      // popupWindow.setMap(map);

      const options = {
        map: map,
        clickable: true,
        content: `<div style="cursor: pointer; font-weight: bold; height: 30px; width: 30px; background-color: white;  color: #ff6000; border: 1px solid #ff6000; border-radius: 50%; "
        onmouseover="this.style.color='white'; this.style.backgroundColor='#ff6000';"
    onmouseout="this.style.color='#ff6000'; this.style.backgroundColor='white';"
   >${num}</div>`,
        position: latlng,
      };

      // const customOverlay = new kakao.maps.CustomOverlay(options);

      const marker = new kakao.maps.Marker({
        position: latlng,

        title: "현재 위치",
        image: markerImage,
      });

      setMarker(marker);
      const markers = [marker, marker, marker, marker, marker];

      // clusterer.setMap(map);
      map.setCenter(latlng); //위치 고정
      // customOverlay.setMap(map);

      // console.log("position", position);
      // console.log("latlng", latlng);
      // console.log("map", map);
      // console.log("infoWindow", infoWindow);
      // console.log("marker", marker);
      // 지도에 컨트롤 추가

      if (!isCheck.current) {
        const mapTypeControl = new kakao.maps.MapTypeControl(); // 맵타입?
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
        const zoomControl = new kakao.maps.ZoomControl(); //줌아웃 인,?
        map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
        isCheck.current = true;
      }

      // 맵 이벤트 핸들러 추가
      kakao.maps.event.addListener(map, "zoom_changed", () => {
        const bounds = map.getBounds();
        const sePoint = bounds.getSouthWest();
        const nePoint = bounds.getNorthEast();

        const XBoundaryValue = [sePoint.getLat(), nePoint.getLat()];
        const YBoundaryValue = [sePoint.getLng(), nePoint.getLng()];

        setXBoundary(XBoundaryValue);
        setYBoundary(YBoundaryValue);
      });

      kakao.maps.event.addListener(map, "dragend", () => {
        const bounds = map.getBounds();
        const sePoint = bounds.getSouthWest();
        const nePoint = bounds.getNorthEast();

        const XBoundaryValue = [sePoint.getLat(), nePoint.getLat()];
        const YBoundaryValue = [sePoint.getLng(), nePoint.getLng()];

        setXBoundary(XBoundaryValue);
        setYBoundary(YBoundaryValue);
      });
    }
  }, [map, position]);

  return (
    <>
      <MapButton />
      <div ref={mapRef} style={{ width: "100%", height: "700px" }} />
    </>
  );
};

export default Map;
