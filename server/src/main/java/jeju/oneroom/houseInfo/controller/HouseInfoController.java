package jeju.oneroom.houseInfo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HouseInfoController {
    @PostMapping
    public ResponseEntity<?> post(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/houseInfos/{houseInfo-id}")
    public ResponseEntity<?> patch(@PathVariable("houseInfo-id") long houseInfoId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/houseInfos/{houseInfo-id}")
    public ResponseEntity<?> delete(@PathVariable("houseInfo-id") long houseInfoId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/houseInfos/{houseInfo-id}")
    public ResponseEntity<?> findHouseInfo(@PathVariable("houseInfo-id") long houseInfoId){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 동, 면, 웁에 속해있는 건물 좌표와 리뷰 수
    @GetMapping("towns/{town-id}/houseInfos")
    public ResponseEntity<?> findTownHouseInfos(@PathVariable("town-id") long townCode){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
