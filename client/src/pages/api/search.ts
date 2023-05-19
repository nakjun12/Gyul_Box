import { BACK_URL } from "../../utils/ConfigHelper";

const searchData = async (keyword: string) => {
  // 데이터를 가져오는 비동기 함수 호출
  console.log(keyword);
  const response = await fetch(`${BACK_URL}/areas/search?name=${keyword}`, {
    headers: {
      "ngrok-skip-browser-warning": "true", // ngrok-skip-browser-warning 헤더 추가
    },
  });
  const data = await response.json(); // 실제 데이터 타입으로 변경해야 함

  // 데이터 처리 로직 작성

  // 예시: 가져온 데이터를 콘솔에 출력
  console.log(data);
  return data;
};
export { searchData };
