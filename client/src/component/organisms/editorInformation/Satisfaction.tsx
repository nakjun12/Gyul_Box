import type { SatisfactionMenu } from "../../../utils/types/types";
import Satisfaction from "../../molecules/satisfaction/Satisfaction";
import styles from "./EditorInformation.module.scss";
type Props = {};

const menu: SatisfactionMenu[] = [
  {
    label: "집내부",
    value: "inside",
    question: "채광, 환기, 수납, 방음, 등에 만족하시나요?",
  },
  {
    label: "건물 / 단지",
    value: "building",
    question: "주차나 보안에 만족하시나요?",
  },
  {
    label: "교통",
    value: "traffic",
    question: "버스나 지하철, 교통수단 이용이 편리한가요?",
  },
  {
    label: "치안",
    value: "safety",
    question: "치안, 공원 및 주변환경에 만족하시나요?",
  },
  {
    label: "생활 및 입지",
    value: "life",
    question: "주변 상권, 편의시설, 문화생활에 만족하시나요?",
  },
];

export default function Satisfactions({}: Props) {
  return (
    <>
      <section className={styles.buliding_wrapper}>
        {menu.map((item: SatisfactionMenu) => (
          <Satisfaction key={item.value} item={item} />
        ))}
      </section>
    </>
  );
}
