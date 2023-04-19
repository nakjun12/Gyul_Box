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
  const [position, setPosition] = useState<Position | null>(null);
  const [address, setAddress] = useState<string | null>(null);
  console.log(KAKAKO_JAVASCRIPT);
  useEffect(() => {
    const script = document.createElement("script");
    script.async = true;
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${KAKAKO_JAVASCRIPT}&autoload=false`;
    document.head.appendChild(script);

    script.onload = () => {
      kakao.maps.load(() => {
        const options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 3,
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

  return <div ref={mapRef} style={{ width: "100%", height: "700px" }} />;
};

export default Map;
