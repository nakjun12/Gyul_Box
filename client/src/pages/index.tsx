import { GetStaticProps } from "next";
import Image from "next/image";
import gyulMain from "../../public/icon/mainGyul.jpg";
import MiniLottie from "../component/atoms/lottie/MiniLottie";
import BestPosts from "../component/molecules/bestPosts/BestPosts";
import SearchBar from "../component/molecules/searchBar/SearchBar";
import DragCarousel from "../component/organisms/dragCarousel/DragCarousel";
import { BACK_URL } from "../utils/ConfigHelper";
import { dragResult, hottest2 } from "../utils/Dummy";
import type { Review_simple } from "../utils/types/types";
import styles from "./../styles/Home.module.scss";
type Props = {
  reviewData: Review_simple;
  userAreaData: Review_simple;
};

export default function Home({ reviewData, userAreaData }: Props) {
  const isData = reviewData.data.length > 0;
  console.log(reviewData.data, userAreaData.data, isData);

  // useEffect(() => {
  //   const area = addressToCode("일도일동");
  //   fetch(`${BACK_URL}/areas/${area}/houseInfos?level=${4}`, {
  //     mode: "cors",
  //     headers: {
  //       "ngrok-skip-browser-warning": "true", // ngrok-skip-browser-warning 헤더 추가
  //     },
  //   })
  //     .then((res) => res.json())
  //     .then((res) => console.log(res, "야"))
  //     .catch((err) => console.error(err));

  //   console.log(area, "하잉");
  // }, []);

  return (
    <>
      <div className={styles.wrapper}>
        <div className={styles.gyulMain}>
          <Image
            src={gyulMain}
            alt="메인 로고"
            height={327}
            width={1200}
            priority
          />
        </div>
        <div className={styles.lottieWrapper}>
          <div className={styles.lottieDiv}>
            <MiniLottie />
          </div>
          <div className={styles.whereDiv}>어디를 알아볼까?</div>
        </div>
        <SearchBar isData={isData} />
        <div className={styles.boardParts}>
          <BestPosts
            data={reviewData.data.length > 0 ? reviewData.data : hottest2.data}
            isData={isData}
          />
        </div>

        <DragCarousel
          data={
            reviewData.data.length > 0 ? userAreaData.data : dragResult.data
          }
          isData={isData}
        />
      </div>
    </>
  );
}

export const getStaticProps: GetStaticProps = async () => {
  // 데이터를 가져오는 비동기 함수 호출
  const reviewResponse = await fetch(`${BACK_URL}/reviews/hottest2`);
  if (!reviewResponse.ok) {
  }

  const userId = 1; // 실제 사용자 ID로 변경해야 함
  const userAreaResponse = await fetch(
    `${BACK_URL}/users/${userId}/user-areas/reviews`
  );
  if (!userAreaResponse.ok) {
    return {
      props: {
        reviewData: { data: [] },
        userAreaData: { data: [] },
      },
    };
  }
  const reviewData = await reviewResponse.json(); // 실제 데이터 타입으로 변경해야 함
  const userAreaData = await userAreaResponse.json(); // 실제 데이터 타입으로 변경해야 함

  // 데이터를 컴포넌트에 전달
  return {
    props: {
      reviewData,
      userAreaData,
    },
  };
};
