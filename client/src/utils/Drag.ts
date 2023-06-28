import React, { useEffect, useRef, useState } from "react";

const isTouchScreen =
  typeof window !== "undefined" &&
  window.matchMedia("(hover: none) and (pointer: coarse)").matches;
//터치 스크린 지원 여부 확인

export function registDragEvent({
  onDragChange, // 드래그가 발생한 방향과 거리
  onDragEnd, // 드래그 이벤트가 종료
  stopPropagation, // 이벤트 전파 막기 여부
}: {
  onDragChange?: (deltaX: number, deltaY: number) => void;
  onDragEnd?: (deltaX: number, deltaY: number) => void;
  stopPropagation?: boolean;
}) {
  if (isTouchScreen) {
    return {
      onTouchStart: (touchEvent: React.TouchEvent<HTMLDivElement>) => {
        if (stopPropagation) touchEvent.stopPropagation(); // 트루일 경우 멈춤

        const touchMoveHandler = (moveEvent: TouchEvent) => {
          if (moveEvent.cancelable) moveEvent.preventDefault();

          const deltaX =
            moveEvent.touches[0].pageX - touchEvent.touches[0].pageX;
          const deltaY =
            moveEvent.touches[0].pageY - touchEvent.touches[0].pageY;
          console.log(deltaX, deltaY);
          console.log(moveEvent.touches[0].pageX, touchEvent.touches[0].pageX);
          onDragChange?.(deltaX, deltaY);
        };

        const touchEndHandler = (moveEvent: TouchEvent) => {
          const deltaX =
            moveEvent.changedTouches[0].pageX -
            touchEvent.changedTouches[0].pageX;
          const deltaY =
            moveEvent.changedTouches[0].pageY -
            touchEvent.changedTouches[0].pageY;
          onDragEnd?.(deltaX, deltaY);
          document.removeEventListener("touchmove", touchMoveHandler);
        };

        document.addEventListener("touchmove", touchMoveHandler, {
          passive: false,
        });
        document.addEventListener("touchend", touchEndHandler, { once: true });
      },
    };
  }

  return {
    onMouseDown: (clickEvent: React.MouseEvent<Element, MouseEvent>) => {
      if (stopPropagation) clickEvent.stopPropagation();

      const mouseMoveHandler = (moveEvent: MouseEvent) => {
        const deltaX = moveEvent.pageX - clickEvent.pageX;
        const deltaY = moveEvent.pageY - clickEvent.pageY;
        onDragChange?.(deltaX, deltaY);
      };

      const mouseUpHandler = (moveEvent: MouseEvent) => {
        const deltaX = moveEvent.pageX - clickEvent.pageX;
        const deltaY = moveEvent.pageY - clickEvent.pageY;
        onDragEnd?.(deltaX, deltaY);
        document.removeEventListener("mousemove", mouseMoveHandler);
      };

      document.addEventListener("mousemove", mouseMoveHandler);
      document.addEventListener("mouseup", mouseUpHandler, { once: true });
    },
  };
}

export const inrange = (v: number, min: number, max: number) => {
  //최대 최소
  console.log(v, min, max);
  if (v < min) return min;
  if (v > max) return max;
  return v;
};

export interface useCarouselSizeProps {
  initWidth?: number;
  initHeight?: number;
  aspectRadio?: number;
}

export function useCarouselSize(
  { aspectRadio = 1, initWidth = 0, initHeight = 0 }: useCarouselSizeProps = {
    aspectRadio: 1,
    initWidth: 0,
    initHeight: 0,
  } //앞 부분은 default value 뒤는 default parameter
) {
  const carouselRef = useRef<HTMLDivElement>(null);
  const [{ width, height }, setCarouselSize] = useState({
    width: initWidth,
    height: initHeight,
  });

  useEffect(() => {
    if (!carouselRef.current) return;

    const carouselRect = carouselRef.current.getBoundingClientRect();
    setCarouselSize({
      width: carouselRect.width,
      height: carouselRect.width * aspectRadio,
    });
  }, [carouselRef, aspectRadio]);

  return {
    ref: carouselRef,
    width,
    height,
  };
}
