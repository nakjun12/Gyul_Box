package jeju.oneroom.openapi.service;

import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenApiService {

    private final HouseInfoRepository houseInfoRepository;
    private final GeoPointService geoPointService;

    public void init(String jsonData) {

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData);
            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");
            JSONObject parseBody2 = (JSONObject) parseBody.get("items");
            JSONArray parseBody3 = (JSONArray) parseBody2.get("item");

            ArrayList<HouseInfo> houseInfos = new ArrayList<>();
            for (Object o : parseBody3) {
                JSONObject jObj = (JSONObject) o;
                String mainPurpsCdNm = jObj.get("mainPurpsCdNm").toString();

                if (checkHouseType(mainPurpsCdNm)) {
                    HouseInfo houseInfo = jsonDataToHouseInfo(jObj);
                    houseInfos.add(houseInfo);
                    for (HouseInfo h : houseInfos) {
                        System.out.println(h.getPlatPlc());
                        System.out.println(h.getHouseName());
                    }
                }
            }

            int cnt = 1;
            for (HouseInfo houseInfo : houseInfos) {

                String location = houseInfo.getPlatPlc();
                // 주소로 위도, 경도 찾을 때, 네이버 클라우드 서비스에 해당 주소에 대한 위도, 경도 정보가 없는 경우가 있음
                // 그럴 경우, coordinate에는 null 저장
                try {
                    double[] geoPoint = geoPointService.findGeoPoint(location);
                    double latitude = geoPoint[0];
                    double longitude = geoPoint[1];
                    Coordinate coordinate = new Coordinate(latitude, longitude);
                    houseInfo.setCoordinate(coordinate);
                } catch (IndexOutOfBoundsException e) {
                    houseInfo.setCoordinate(null);
                }

                // 위도, 경도가 없는 건물이 필요할까?
                if (houseInfo.getCoordinate() == null) {
                    continue;
                }

                houseInfoRepository.save(houseInfo);
                cnt++;

                if (cnt == 101) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkHouseType(String mainPurpsCdNm) {
        String[] houseType = {"다가구주택", "다중주택", "공동주택", "다세대주택", "오피스텔", "단독주택"};
        for (String type : houseType) {
            if (type.equals(mainPurpsCdNm)) {
                return true;
            }
        }

        return false;
    }

    private HouseInfo jsonDataToHouseInfo(JSONObject jsonObject) {
        String mainPurpsCdNm = jsonObject.get("mainPurpsCdNm").toString();
        String houseName = jsonObject.get("bldNm").toString();
        String buildUes = jsonObject.get("mainPurpsCdNm").toString();
        String buildingStructure = jsonObject.get("strctCdNm").toString();
        String useAprDay = jsonObject.get("useAprDay").toString();
        int grndFloor = Integer.parseInt(jsonObject.get("grndFlrCnt").toString());
        int ugrndFloor = Integer.parseInt(jsonObject.get("ugrndFlrCnt").toString());
        int elevator = Integer.parseInt(jsonObject.get("rideUseElvtCnt").toString());
        int houseHold = Integer.parseInt(jsonObject.get("hhldCnt").toString());
        String platPlc = jsonObject.get("platPlc").toString();

        return HouseInfo.builder()
                .mainPurpsCdNm(mainPurpsCdNm)
                .houseName(houseName)
                .buildUes(buildUes)
                .buildingStructure(buildingStructure)
                .useAprDay(useAprDay)
                .grndFloor(grndFloor)
                .ugrndFloor(ugrndFloor)
                .elevator(elevator)
                .houseHold(houseHold)
                .platPlc(platPlc)
                .build();
    }
}
