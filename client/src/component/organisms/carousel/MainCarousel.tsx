import React, { useEffect, useRef, useState } from "react";
import styles from "./MainCarousel.module.scss";

interface CarouselProps {
  items: string[];
}

const MainCarousel: React.FC<CarouselProps> = ({ items }) => {
  const [currentIndex, setCurrentIndex] = useState(0);
  const containerRef = useRef<HTMLDivElement>(null);
  const itemWidth = 310; // 각 아이템의 너비

  useEffect(() => {
    if (containerRef.current) {
      containerRef.current.style.transform = `translateX(-${
        currentIndex * itemWidth
      }px)`;
    }
  }, [currentIndex, itemWidth]);

  const handleClickPrev = () => {
    if (currentIndex > 0) {
      setCurrentIndex(currentIndex - 1);
    }
  };

  const handleClickNext = () => {
    if (currentIndex < items.length - 3) {
      setCurrentIndex(currentIndex + 1);
    }
  };
  console.log("items", items);
  console.log("currentIndex", currentIndex);

  return (
    <div className={styles.carousel_wrapper}>
      <div className={styles.carousel_container} ref={containerRef}>
        {items.map((item, index) => (
          <div key={index} className={styles.carousel_item}>
            {item}
          </div>
        ))}
      </div>
      <div className={styles.carousel_controls}>
        <button className={styles.carousel_control} onClick={handleClickPrev}>
          이전
        </button>
        <button className={styles.carousel_control} onClick={handleClickNext}>
          다음
        </button>
      </div>
    </div>
  );
};

export default MainCarousel;
