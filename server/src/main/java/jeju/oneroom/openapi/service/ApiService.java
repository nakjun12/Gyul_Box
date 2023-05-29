package jeju.oneroom.openapi.service;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.houseInfo.entity.HouseInfo;
import jeju.oneroom.houseInfo.repository.HouseInfoRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@RequiredArgsConstructor
public class ApiService {

    private final AreaRepository areaRepository;
    private final HouseInfoRepository houseInfoRepository;
    private final GeoPointService geoPointService;

    public void init(String jsonData) {
        ArrayList<HouseInfo> houseInfos = new ArrayList<>();

        try {
            JSONObject jObj;
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj = (JSONObject) jsonParser.parse(jsonData);
            JSONObject parseResponse = (JSONObject) jsonObj.get("response");
            JSONObject parseBody = (JSONObject) parseResponse.get("body");
            JSONObject parseBody2 = (JSONObject) parseBody.get("items");
            JSONArray parseBody3 = (JSONArray) parseBody2.get("item");

            for (Object o : parseBody3) {
                jObj = (JSONObject) o;
                HouseInfo houseInfo = HouseInfo.builder().mainPurpsCdNm(jObj.get("mainPurpsCdNm").toString()).houseName(jObj.get("bldNm").toString()).buildUes(jObj.get("mainPurpsCdNm").toString()).buildingStructure(jObj.get("strctCdNm").toString()).useAprDay(jObj.get("useAprDay").toString()).grndFloor(Integer.parseInt(jObj.get("grndFlrCnt").toString())).ugrndFloor(Integer.parseInt(jObj.get("ugrndFlrCnt").toString())).elevator(Integer.parseInt(jObj.get("rideUseElvtCnt").toString())).houseHold(Integer.parseInt(jObj.get("hhldCnt").toString())).platPlc(jObj.get("platPlc").toString()).build();
                houseInfos.add(houseInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int cnt = 1;
        for (HouseInfo d : houseInfos) {
            if (d.getMainPurpsCdNm().equals("다가구주택") || d.getMainPurpsCdNm().equals("다중주택") || d.getMainPurpsCdNm().equals("공동주택") || d.getMainPurpsCdNm().equals("다세대주택") || d.getMainPurpsCdNm().equals("오피스텔") || d.getMainPurpsCdNm().equals("단독주택")) {
                cnt++;
                if (cnt == 50) {
                    Area area = areaRepository.findById(5000000000L).orElse(null);
                    d.setArea(area);

                    String location = d.getPlatPlc();
                    double[] geoPoint = geoPointService.findGeoPoint(location);
                    double latitude = geoPoint[0];
                    double longitude = geoPoint[1];
                    Coordinate coordinate = new Coordinate(latitude, longitude);
                    d.setCoordinate(coordinate);

                    houseInfoRepository.save(d);
                    cnt = 1;
                }
            }
        }
    }
}
