import Chart from "chart.js/auto";
import React, { useEffect, useMemo, useRef } from "react";
import type { Detail_data } from "../../../utils/types/types";
interface ChartProps {
  dataList: Detail_data;
}

const Pantagon: React.FC<ChartProps> = ({ dataList }) => {
  const chartRef = useRef<Chart>();
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const rate = dataList.rate;

  const data = useMemo(
    () => [
      rate.interiorRate,
      rate.buildingRate,
      rate.trafficRate,
      rate.securityRate,
      rate.locationRate,
    ],
    [rate]
  );
  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) {
      return;
    }

    const ctx = canvas.getContext("2d");
    if (!ctx) {
      return;
    }

    chartRef.current = new Chart(ctx, {
      type: "radar",
      data: {
        labels: ["집내부", "건물/단지", "교통", "치안", "생활 및 입지"],
        datasets: [
          {
            label: "만족도",
            data,
            backgroundColor: "rgba(255, 96, 0, 0.2)",
            borderColor: "rgba(255, 108, 61, 1)",
          },
        ],
      },
      options: {
        responsive: false,
        elements: {
          line: {
            borderWidth: 3,
          },
        },
        scales: {
          r: {
            suggestedMin: 0,
            suggestedMax: 5,
            pointLabels: {
              font: {
                size: 12,

                family: "Pretendard-Regular",
              },
            },
          },
        },
        plugins: {
          legend: {
            labels: {
              font: {
                size: 16,

                family: "Pretendard-Regular",
              },
            },
          },
        },
      },
    });

    return () => {
      if (chartRef.current) {
        chartRef.current.destroy();
      }
    };
  }, [data]);

  return (
    <canvas
      ref={canvasRef}
      id="chart"
      aria-label="chart"
      height={220}
      width={300}
    />
  );
};

export default Pantagon;
