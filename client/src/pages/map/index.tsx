import { KAKAKO_JAVASCRIPT } from "@/lib/ConfigHelper";
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
  const [map, setMap] = useState<kakao.maps.Map | null>(null);
  const mapRef = useRef<HTMLDivElement>(null);

  const [marker, setMarker] = useState<kakao.maps.Marker | null>(null);
  const [infoWindow, setInfoWindow] = useState<kakao.maps.InfoWindow | null>(
    null
  );

  const [position, setPosition] = useState<Position | null>({
    lat: 33.48972486175701,
    lng: 126.49657010389818,
  });
  const [address, setAddress] = useState<string | null>(null);

  // 마커 이미지의 이미지 주소입니다

  useEffect(() => {
    console.log("기치");
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
  useEffect(() => {
    if (map !== null && position !== null) {
      const imageSrc =
        "https://velog.velcdn.com/images/wns450/post/6bbc8c55-8607-430c-b3f4-bf2ab143145d/image.png";

      // 마커 이미지의 이미지 크기 입니다
      const imageSize = new kakao.maps.Size(60, 60);

      // 마커 이미지를 생성합니다
      const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
      const latlng = new kakao.maps.LatLng(position.lat, position.lng);

      const options = {
        content: "현재 위치",
        position: latlng,
      };

      const infoWindow = new kakao.maps.InfoWindow(options);
      setInfoWindow(infoWindow);

      const marker = new kakao.maps.Marker({
        position: latlng,

        title: "현재 위치",
        image: markerImage,
      });
      setMarker(marker);

      map.setCenter(latlng); //위치 고정
      infoWindow.open(map, marker);
      marker.setMap(map);

      // console.log("position", position);
      // console.log("latlng", latlng);
      // console.log("map", map);
      // console.log("infoWindow", infoWindow);
      // console.log("marker", marker);

      kakao.maps.event.addListener(marker, "mouseover", function () {
        infoWindow.open(map, marker);
      });

      // 마커에 마우스 아웃 이벤트 등록
      kakao.maps.event.addListener(marker, "mouseout", function () {
        infoWindow.close();
      });
    }
  }, [map, position]);

  return <div ref={mapRef} style={{ width: "100%", height: "700px" }} />;
};

export default Map;
