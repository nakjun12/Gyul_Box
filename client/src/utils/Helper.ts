import { useEffect, useState } from "react";

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
  console.log("렌더링");
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

export { useHasMounted, useWindowSize };
