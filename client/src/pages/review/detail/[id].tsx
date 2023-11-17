import { GetStaticPaths, GetStaticProps } from "next";
import { useEffect } from "react";
import Stars from "../../../component/molecules/stars/Stars";
import Tabs from "../../../component/organisms/tabs/Tabs";
import { BACK_URL } from "../../../utils/ConfigHelper";
import type { Detail_data } from "../../../utils/types/types";

type Props = {
  data: Detail_data;
};

export default function Index({ data }: Props) {
  console.log(data);
  useEffect(() => {
    kakao.maps.load(() => {
      const staticMapContainer = document.getElementById("staticMap"); // 지도를 표시할 div
      if (staticMapContainer !== null) {
        const coordinate = data.coordinate;
        const staticMapOption = {
          center: new kakao.maps.LatLng(
            coordinate.latitude || 33.450701,
            coordinate.longitude || 126.570667
          ), // 이미지 지도의 중심좌표
          level: 3, // 이미지 지도의 확대 레벨
          marker: true,
        };

        const staticMap = new kakao.maps.StaticMap(
          staticMapContainer,
          staticMapOption
        );
      }
    });
  }, [data.coordinate]);

  return (
    <main className="detail_wrapper">
      <div id="staticMap" style={{ width: "100%", height: "200px" }} />
      <header className="detail_header">
        <div>
          <h3>
            {data.houseName || "제주특별자치도 제주시 삼도일동 롯데아파트"}
          </h3>
          <p>{data.platPlc || "서울 구로구 구일동 130"}</p>
        </div>
        <div>
          <span>{data.rate.avgRate || 3.0}</span>
          <Stars stars={data.rate.avgRate || 3.0} />
        </div>
      </header>
      <Tabs data={data} />
    </main>
  );
}

export const getStaticPaths: GetStaticPaths = async () => {
  return {
    paths: [],
    fallback: "blocking",
  };
};

export const getStaticProps: GetStaticProps = async ({ params }) => {
  try {
    const houseInfo_id = params?.id as string;
    //worldcups/1

    const res = await fetch(`${BACK_URL}/houseInfos/${1}`);
    console.log(res, "res");
    const data = await res.json();

    if (res.status === 404) {
      return { notFound: true };
    }

    return { props: { data } };
  } catch (error) {
    console.error("An error occurred during the fetch request:", error);
    return { props: {} };
  }
};
