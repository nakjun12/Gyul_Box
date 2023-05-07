import { useState } from "react";
import { MainCard } from "../../molecules/mainCard/MainCard";
import {
  inrange,
  registDragEvent,
  useCarouselSize,
} from "./../../../utils/Drag";
import styles from "./DragCarousel.module.scss";
const imageList = [
  "https://blog.kakaocdn.net/dn/dpxiAT/btqUBv6Fvpn/E8xUMncq7AVuDeOim0LrMk/img.jpg",
  "https://blog.kakaocdn.net/dn/BGT7X/btqUzvTqi5h/flp39GdJH0GU6mo7cTbbhk/img.jpg",
  "https://blog.kakaocdn.net/dn/bWnmfv/btqUBwqZvwA/3CiXGt3SR0TXoOveRJxV91/img.jpg",
  "https://blog.kakaocdn.net/dn/XsLCO/btqUL8PQLwp/NZWCU2jAYKkKSXwcohBKTK/img.jpg",
  "https://blog.kakaocdn.net/dn/bG3iVL/btqUvCZPaRL/ofIjkNWJP1mj2bOG9fie51/img.jpg",
];

export default function CarouselExample() {
  const [currentIndex, setCurrentIndex] = useState(0);
  const [transX, setTransX] = useState(0);

  const { ref, width, height } = useCarouselSize(); //1:1 비율 유지하게 도와줌

  return (
    <>
      <h2 className={styles.title}>관심지역 🔍</h2>
      <div
        ref={ref}
        className={styles.carousel_wrapper}
        style={{
          overflow: "hidden",
        }}
      >
        <div
          className={styles.carousel_slider}
          style={{
            transform: `translateX(${-currentIndex * 310 + transX}px)`, //넘김수
            transition: `transform ${300}ms ease-in-out 0s`, //시간초
          }}
          {...registDragEvent({
            //...속성으로 추가함
            onDragChange: (deltaX: any) => {
              setTransX(inrange(deltaX, -width, width)); //최대값 임시값임
            },
            onDragEnd: (deltaX: any) => {
              const maxIndex = imageList.length - 2;

              if (deltaX < -10) {
                setCurrentIndex(inrange(currentIndex + 1, 0, maxIndex));
                if (inrange(currentIndex + 1, 0, maxIndex) === maxIndex) {
                  setTransX(140);
                } else {
                  setTransX(0);
                }
              } else if (deltaX > 10) {
                setCurrentIndex(inrange(currentIndex - 1, 0, maxIndex));

                setTransX(0);
              }
            },
          })}
        >
          {imageList.map((url, i) => (
            <div key={i} className={styles.carousel_slide}>
              <MainCard />
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
