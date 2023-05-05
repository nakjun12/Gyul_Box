const zone = [
  "구좌읍",
  "남원읍",
  "대정읍",
  "중문동",
  "대천동",
  "예래동",
  "한림읍",
  "애월읍",
  "조천읍",
  "이도이동",
  "삼도일동",
  "삼도이동",
  "삼도삼동",
  "용담이동",
  "용담삼동",
  "건입동",
  "화북이동",
  "화북삼동",
  "봉개동",
  "아라일동",
  "아라이동",
  "아라삼동",
  "오라일동",
  "오라이동",
  "외도일동",
  "외도이동",
  "외도삼동",
  "이호일동",
  "이호이동",
  "도두일동",
  "도두이동",
  "도두삼동",
  "연동",
  "노형동",
  "일도일동",
  "일도이동",
  "이도일동",
  "우도면",
  "조천읍",
  "중산리",
  "한경면",
  "추자면",
  "성산읍",
  "안덕면",
];
const sort_zone = zone.sort();
export { zone, sort_zone };

export const addressToCode = (address: string) => {
  if (address !== null) {
    switch (address) {
      case "제주":
        return "5011000000";
      case "제주시":
        return "5011000000";
      case "일도일동":
        return "5011010100";
      case "일도이동":
        return "5011010200";
      case "이도일동":
        return "5011010300";
      case "이도이동":
        return "5011010400";
      case "삼도일동":
        return "5011010500";
      case "삼도이동":
        return "5011010600";
      case "건입동":
        return "5011010700";
      case "용담일동":
        return "5011010800";
      case "용담이동":
        return "5011010900";
      case "용담삼동":
        return "5011011000";
      case "화북일동":
        return "5011011100";
      case "화북이동":
        return "5011011200";
      case "삼양일동":
        return "5011011300";
      case "삼양이동":
        return "5011011400";
      case "삼양삼동":
        return "5011011500";
      case "봉개동":
        return "5011011600";
      case "아라일동":
        return "5011011700";
      case "아라이동":
        return "5011011800";
      case "오라일동":
        return "5011011900";
      case "오라이동":
        return "5011012000";
      case "오라삼동":
        return "5011012100";
      case "노형동":
        return "5011012200";
      case "외도일동":
        return "5011012300";
      case "외도이동":
        return "5011012400";
      case "이호일동":
        return "5011012500";
      case "이호이동":
        return "5011012600";
      case "도두일동":
        return "5011012700";
      case "도두이동":
        return "5011012800";
      case "도남동":
        return "5011012900";
      case "도련일동":
        return "5011013000";
      case "도련이동":
        return "5011013100";
      case "용강동":
        return "5011013200";
      case "회천동":
        return "5011013300";
      case "오등동":
        return "5011013400";
      case "월평동":
        return "5011013500";
      case "영평동":
        return "5011013600";
      case "연동":
        return "5011013700";
      case "도평동":
        return "5011013800";
      case "해안동":
        return "5011013900";
      case "내도동":
        return "5011014000";
      case "한림읍":
        return "5011025000";
      case "애월읍":
        return "5011025300";
      case "구좌읍":
        return "5011025600";
      case "조천읍":
        return "5011025900";
      case "한경면":
        return "5011031000";
      case "추자면":
        return "5011032000";
      case "우도면":
        return "5011033000";
      case "서귀포":
        return "5013000000";
      case "서귀포시":
        return "5013000000";
      case "서귀동":
        return "5013010100";
      case "법환동":
        return "5013010200";
      case "서호동":
        return "5013010300";
      case "호근동":
        return "5013010400";
      case "동홍동":
        return "5013010500";
      case "서홍동":
        return "5013010600";
      case "상효동":
        return "5013010700";
      case "하효동":
        return "5013010800";
      case "신효동":
        return "5013010900";
      case "보목동":
        return "5013011000";
      case "토평동":
        return "5013011100";
      case "중문동":
        return "5013011200";
      case "회수동":
        return "5013011300";
      case "대포동":
        return "5013011400";
      case "월평동":
        return "5013011500";
      case "강정동":
        return "5013011600";
      case "도순동":
        return "5013011700";
      case "하원동":
        return "5013011800";
      case "색달동":
        return "5013011900";
      case "상예동":
        return "5013012000";
      case "하예동":
        return "5013012100";
      case "영남동":
        return "5013012200";
      case "대정읍":
        return "5013025000";
      case "남원읍":
        return "5013025300";
      case "성산읍":
        return "5013025900";
      case "안덕면":
        return "5013031000";
      case "표선면":
        return "5013032000";
    }
  }
};
