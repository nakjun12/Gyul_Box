import { useEffect, useState } from "react";
import { Map_data } from "./types/types";
function useHasMounted() {
  const [hasMounted, setHasMounted] = useState(false);

  useEffect(() => {
    setHasMounted(true);
  }, []);

  return hasMounted;
}

const useWindowSize = () => {
  const isClient = typeof window === "object";
  const [windowSize, setWindowSize] = useState(
    isClient ? window.innerWidth : undefined
  );

  useEffect(() => {
    if (isClient) {
      let timeoutId: NodeJS.Timeout;

      const handleResize = () => {
        clearTimeout(timeoutId);
        console.log("일반 실행");
        timeoutId = setTimeout(() => {
          console.log("디바운스");
          setWindowSize(window.innerWidth);
        }, 200);
      };

      window.addEventListener("resize", handleResize);

      return () => {
        window.removeEventListener("resize", handleResize);
      };
    }
  }, [isClient]);
  return windowSize;
};
function convertTimeAgo(dateString: string): string {
  const date = new Date(dateString);
  const now = new Date();
  const diff = Math.abs(now.getTime() - date.getTime());

  const minutes = Math.floor(diff / (1000 * 60));
  const hours = Math.floor(diff / (1000 * 60 * 60));
  const days = Math.floor(diff / (1000 * 60 * 60 * 24));
  const weeks = Math.floor(diff / (1000 * 60 * 60 * 24 * 7));

  if (minutes < 60) {
    return `${minutes}분 전`;
  } else if (hours < 24) {
    return `${hours}시간 전`;
  } else if (days < 7) {
    return `${days}일 전`;
  } else {
    return `${weeks}주 전`;
  }
}

const getAddress = (lat: number, lng: number): Promise<string> => {
  return new Promise((resolve, reject) => {
    const geocoder = new kakao.maps.services.Geocoder();
    const coord = new kakao.maps.LatLng(lat, lng);
    const callback = function (
      result: any,
      status: kakao.maps.services.Status
    ) {
      // console.log(status);
      if (status === kakao.maps.services.Status.OK) {
        const addressName = result[0].address.address_name;
        resolve(addressName); // Promise를 통해 addressName 반환
      } else {
        reject(new Error("Failed to get address"));
      }
    };
    geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
  });
};

const addMarker = (arr: Map_data[], map: kakao.maps.Map) => {
  const completeMarker: kakao.maps.CustomOverlay[] = [];
  for (let i = 0; i < arr.length; i++) {
    const latlng = new kakao.maps.LatLng(
      arr[i].coordinate.latitude,
      arr[i].coordinate.longitude
    );
    const popupWindow = new kakao.maps.CustomOverlay({
      position: latlng,
      clickable: true,
    });
    // //id단축
    const markerBox = document.createElement("button");
    markerBox.setAttribute("class", "marker_box");
    markerBox.setAttribute("value", `${arr[i].id}`);
    markerBox.onclick = function () {
      console.log(popupWindow.getPosition());
    };
    markerBox.textContent = String(arr[i].reviewCount);

    popupWindow.setContent(markerBox);
    popupWindow.setMap(map);
    completeMarker.push(popupWindow);
  }

  return completeMarker;
};
function removeMarker(markers: kakao.maps.CustomOverlay[]) {
  for (let i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers.length = 0; //주의 수정
}
//사용법
// // addMarker 호출 후 반환된 배열을 변수에 할당
// const markers = addMarker(arr, map);

// // markers 배열 사용 및 제거
// removeMarker(markers);

export {
  useHasMounted,
  useWindowSize,
  convertTimeAgo,
  getAddress,
  addMarker,
  removeMarker,
};
