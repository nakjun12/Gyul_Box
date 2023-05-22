import { Suspense, useEffect, useRef, useState } from "react";
import SearchBar from "../../component/molecules/searchBar/SearchBar";
import MapFooter from "../../component/organisms/mapfooter/MapFooter";
import { 삼도일동 } from "../../utils/Dummy";
import { addMarker, getAddress, removeMarker } from "../../utils/Helper";
import { Map_circle } from "../../utils/types/types";
const kakao = typeof window !== "undefined" ? (window as any).kakao : null;

interface Position {
  lat: number;
  lng: number;
}

export default function Ex() {
  const [isCheck, setIsCheck] = useState<boolean>(false);
  const [isGeo, setIsGeo] = useState<[string, number]>(["삼도일동", 4]);
  const [position, setPosition] = useState<Position>({
    lat: 33.48972486175701,
    lng: 126.49657010389818,
  });
  const [address, setAddress] = useState<Map_circle>({ data: [] });
  const [deleteMarker, setDeleteMarker] = useState<kakao.maps.CustomOverlay[]>(
    []
  );
  const [serverOn, setServerOn] = useState<boolean>(false);
  const mapElement: React.MutableRefObject<kakao.maps.Map | null> =
    useRef(null);

  useEffect(() => {
    if (mapElement.current !== null) {
      console.log(address.data);
      if (address.data.length === 0) {
        console.log("없음");
      } else {
        console.log("있음");

        const newMarkers = addMarker(address.data, mapElement.current);
        setDeleteMarker((el) => {
          removeMarker(el);
          return newMarkers;
        });
      }
    }

    console.log(mapElement.current);
  }, [address]);

  useEffect(() => {
    kakao.maps.load(() => {
      const geocoder = new kakao.maps.services.Geocoder();
      const mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(33.502212, 126.519043), // 지도의 중심좌표
          level: 4, // 지도의 확대 레벨
        };
      if (mapContainer !== null && !mapContainer!.hasChildNodes()) {
        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        const map = new kakao.maps.Map(mapContainer, mapOption);
        mapElement.current = map;
        // const imageSrc =
        //   "https://velog.velcdn.com/images/wns450/post/6bbc8c55-8607-430c-b3f4-bf2ab143145d/image.png";

        map.setMaxLevel(10);

        //marker
        // const imageSize = new kakao.maps.Size(60, 60);
        // const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        // const latlng = new kakao.maps.LatLng(position.lat, position.lng);
        // const num = 3;
        // const id = 24;
        // const latlng2 = new kakao.maps.LatLng(
        //   33.48865701020525,
        //   126.49173155558593
        // );

        // const getTexts = (count: number) => {
        //   // 한 클러스터 객체가 포함하는 마커의 개수에 따라 다른 텍스트 값을 표시합니다
        //   if (count < 10) {
        //     return "삐약";
        //   } else if (count < 30) {
        //     return "꼬꼬";
        //   } else if (count < 50) {
        //     return "꼬끼오";
        //   } else {
        //     return "치멘";
        //   }
        // };

        // 클릭한 위치의 위도는 33.48865701020525 이고,
        // index.tsx:69 경도는 126.49173155558593 입니다
        // const marker = new kakao.maps.Marker({
        //   position: [33.48972486175701, 126.49657010389818],

        //   title: "현재 위치",
        //   clickable: true,
        //   image: markerImage,
        // });

        // const popupWindow = new kakao.maps.CustomOverlay({
        //   position: latlng,
        //   clickable: true,
        // });
        // // //id단축
        // const markerBox = document.createElement("button");
        // markerBox.setAttribute("class", "marker_box");
        // markerBox.setAttribute("value", `${id}`);
        // markerBox.onclick = function () {
        //   console.log(popupWindow.getPosition());
        // };
        // markerBox.textContent = String(num);

        // popupWindow.setContent(markerBox);
        // popupWindow.setMap(map);
        //동계산하는거와 줌에 따른 비교가 필요함

        // const setClustererTexts = async function () {
        //   const coord = new kakao.maps.LatLng(
        //     37.56496830314491,
        //     126.93990862062978
        //   );

        //   const result = await new Promise<string>((resolve) => {
        //     console.log(coord.getLng(), coord.getLat());
        //     geocoder.coord2RegionCode(
        //       coord.getLng(),
        //       coord.getLat(),
        //       (result: any, status: any) => {
        //         if (status === kakao.maps.services.Status.OK) {
        //           const address_name = result[0].address_name;
        //           console.log(address_name);

        //           resolve(address_name);
        //         } else {
        //           resolve("");
        //         }
        //       }
        //     );
        //   });
        //   console.log(result);
        //   return result;
        // };

        // const getAddress = (lat: number, lng: number) => {
        //   const coord = new kakao.maps.LatLng(lat, lng);
        //   const callback = function (
        //     result: any,
        //     status: kakao.maps.services.Status
        //   ) {
        //     console.log(status, "하이");
        //     if (status === kakao.maps.services.Status.OK) {
        //       console.log(result[0].address.address_name, "하이");
        //     }
        //   };
        //   geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
        // // };
        // console.log(getAddress(37.554722, 126.970833), "히히");

        //     const options = {
        //       map: map,
        //       clickable: true,
        //       content: `<div style="cursor: pointer; font-weight: bold; height: 30px; width: 30px; background-color: white;  color: #ff6000; border: 1px solid #ff6000; border-radius: 50%; "
        //       onmouseover="this.style.color='white'; this.style.backgroundColor='#ff6000';"
        //   onmouseout="this.style.color='#ff6000'; this.style.backgroundColor='white';"
        //  >${num}</div>`,
        //       position: latlng2,
        //     };
        //     const customOverlay = new kakao.maps.CustomOverlay(options);
        // customOverlay.setMap(map);
        // popupWindow.setMap(map);
        // const markers: kakao.maps.CustomOverlay | kakao.maps.Marker[] = [
        //   popupWindow,
        //   customOverlay,
        //   popupWindow,
        // ];

        //첫번재 경우의 수, 오버레이 2개를 넣어봄
        //두번째 마커가 안되는 지 확인할 것
        // kakao.maps.event.addListener(
        //   clusterer,
        //   "clusterclick",
        //   function (cluster: kakao.maps.Cluster) {
        //     // 현재 지도 레벨에서 1레벨 확대한 레벨
        //     var level = map.getLevel() - 1;
        //     console.log("하이");
        //     // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        //     map.setLevel(level, { anchor: cluster.getCenter() });
        //   }
        // );
        // 지도에 컨트롤 추가

        setDeleteMarker(addMarker(삼도일동.data, map));
        const mapTypeControl = new kakao.maps.MapTypeControl(); // 맵타입?
        map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
        const zoomControl = new kakao.maps.ZoomControl(); //줌아웃 인,?
        map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

        // 맵 이벤트 핸들러 추가
        kakao.maps.event.addListener(map, "zoom_changed", () => {
          //   // 지도의  레벨을 얻어옵니다
          const level = map.getLevel();

          // 지도의 중심좌표를 얻어옵니다
          const latlng = map.getCenter();

          // const message =
          //   "지도 레벨은 " +
          //   level +
          //   " 이고" +
          //   "중심 좌표는 위도 " +
          //   latlng.getLat() +
          //   ", 경도 " +
          //   latlng.getLng() +
          //   "입니다";
          getAddress(latlng.getLat(), latlng.getLng())
            .then((addressName: string) => {
              const splitAddress = addressName.split(" ");
              const location = splitAddress[2];
              if (level <= 8) {
                setIsGeo([location, level]);
              } else {
                setIsGeo(["제주시", level]);
              }
              console.log(addressName); // 결과 출력
            })
            .catch((error: Error) => {
              console.log("없어");
              console.error(error); // 에러 처리
            });

          // console.log(message, "히히");
        });

        // kakao.maps.event.addListener(map, "center_changed", function () {
        //   // 지도의  레벨을 얻어옵니다
        //   const level = map.getLevel();

        //   // 지도의 중심좌표를 얻어옵니다
        //   const latlng = map.getCenter();

        //   const message =
        //     "지도 레벨은 " +
        //     level +
        //     " 이고" +
        //     "중심 좌표는 위도 " +
        //     latlng.getLat() +
        //     ", 경도 " +
        //     latlng.getLng() +
        //     "입니다";

        //   console.log(message, "히히");
        // });

        kakao.maps.event.addListener(map, "dragend", () => {
          //   // 지도의  레벨을 얻어옵니다
          const level = map.getLevel();

          // 지도의 중심좌표를 얻어옵니다
          const latlng = map.getCenter();

          // const message =
          //   "지도 레벨은 " +
          //   level +
          //   " 이고" +
          //   "중심 좌표는 위도 " +
          //   latlng.getLat() +
          //   ", 경도 " +
          //   latlng.getLng() +
          //   "입니다";

          getAddress(latlng.getLat(), latlng.getLng())
            .then((addressName: string) => {
              const splitAddress = addressName.split(" ");
              const location = splitAddress[2];
              const si = splitAddress[1];
              if (level <= 8) {
                setIsGeo([location, level]);
              } else {
                setIsGeo([si, level]);
              }
              console.log(addressName); // 결과 출력
            })
            .catch((error) => {
              console.error(error); // 에러 처리
            });
        });
      }
    });
  }, [position]);

  //1 눌렀을대 데이터 받아오는 것 가능
  //2 데이터를 빼와서 리로드 시키기
  //3 그리고 그것들을 맵에 넣고 뻇기

  return (
    <div className="map_wrap">
      <div className="map_top">
        <SearchBar isData={serverOn} />
      </div>
      <div className="map_bottom">
        <MapFooter geo={isGeo} setAddress={setAddress} serverOn={serverOn} />
      </div>
      <Suspense>
        <div id="map" style={{ width: "100%", height: "100%" }} />
      </Suspense>
    </div>
  );
}
