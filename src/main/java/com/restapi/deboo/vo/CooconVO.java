package com.restapi.deboo.vo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CooconVO {

    // 아파트 시세조회 Value Object
    @Getter
    @Setter
    @Data
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Apartment {
        @ApiParam(value="쿠콘 API 키", required=false, example="")
        String API_KEY = "fWCSU3K1B6ohsLxbNXna";
        @ApiParam(value="쿠콘 API 번호", required=false, example="")
        String API_ID = "0121";
        @ApiParam(value="", required=false, example="")
        String ORG_CD = "";

        @ApiParam(value="부동산 구분 (A: 전체, 1: 아파트, 2: 오피스텔)", required=true, example = "")
        String BUILDING_TYPE = "A";               // 부동산 구분   (A: 전체,        1: 아파트, 2: 오피스텔)
        @ApiParam(value="거래유형 (A: 전체, 0: 매물, 1: 시세, 2: 평명도, 3: 단지명)", required=true, example = "")
        String TRADE_TYPE = "A";                  // 거래유형     (A: 전체, 0: 매물, 1: 시세, 2: 평명도, 3: 단지명)
        @ApiParam(value="물건식별자", required=false, example = "")
        String APARTMENT = "";                    // 물건식별자
        @ApiParam(value="법정동코드 (다음api bcode) *군구명+읍면동” 또는 “법정동코드” 둘 중 하나는 반드시 입력해야 했으나 동작하지 않음", required=false, example = "")
        String LDONG_CODE = "";                   // 법정동코드
        @ApiParam(value="시도명 (다음api sido)", required=false, example = "")
        String SIDO_NAME = "";                    // 시도명
        @ApiParam(value="구군명 (다음api sigungu)", required=true, example = "")
        String GUGN_NAME = "영등포구";              // 군구명
        @ApiParam(value="읍면동 (다음api bname)", required=true, example = "")
        String DONG_NAME = "영등포동 7 가";          // 읍면동
        @ApiParam(value="시작순번", required=false, example = "1")
        String SPAGE = "1";                       // 시작순번
        @ApiParam(value="종류순번", required=false, example = "99999")
        String EPAGE = "99999";                   // 종류순번
    }

    // 주택 실거래가 Value Object
    @Getter
    @Setter
    @Data
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class House {
        @ApiParam(value="", required=false, example="")
        String API_KEY = "fWCSU3K1B6ohsLxbNXna";
        @ApiParam(value="", required=false, example="")
        String API_ID = "0120";

        @ApiParam(value="주택구분 (A: 전체, 0: 아파트, 1:다세대/연립, 2: 단독/다가구, 3: 오피스텔)", required=true, example="")
        String SEARCH_GUBUN = "A";
        @ApiParam(value="거래유형 (A:전체, 0: 매매, 1: 전월세)", required=true, example="")
        String TRADE_TYPE = "A";
        @ApiParam(value="시도명", required=false, example="")
        String SIDO_NAME = "";
        @ApiParam(value="(시)구군명", required=true, example = "")
        String GUGN_NAME = "";
        @ApiParam(value="읍면동", required=true, example = "")
        String DONG_NAME = "";
        @ApiParam(value="읍면동코드 * 동작하지않음", required=false, example = "")
        String DONG_CODE = "";
        @ApiParam(value="기준년도 (YYYY)", required=false, example = "")
        String SEARCH_YEAR = "2017";
        @ApiParam(value="분기 (A: 전체, 1~4)", required=false, example = "")
        String SEARCH_SEASON = "A";
        @ApiParam(value="단지명/건물명", required=false, example = "")
        String DANJI_NAME = "";
        @ApiParam(value="번지", required=false, example = "")
        String BUN_JI = "";
        @ApiParam(value="본번", required=false, example = "")
        String BON_NO = "";
        @ApiParam(value="부번", required=false, example = "")
        String BU_NO = "";
        @ApiParam(value="도로명", required=false, example = "")
        String ROAD_NM = "";
        @ApiParam(value="시작순번", required=true, example = "1")
        String SPAGE = "1";
        @ApiParam(value="종류순번", required=true, example = "100")
        String EPAGE = "100";
    }


    // 등기부 등본 Value Object
    @Getter
    @Setter
    @Data
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Attested {
        @ApiParam(value="", required=false, example = "")
        String API_KEY          = "fWCSU3K1B6ohsLxbNXna";
        @ApiParam(value="", required=false, example = "")
        String API_ID           = "1414";

        @ApiParam(value="비밀번호 (결제시 필요한 1회성 임시비밀번호 숫자 4자리)", required=true, example = "")
        String WEB_PW           = "1234";                   // 비밀번호
        @ApiParam(value="전화번호 (결제시 필요한 1회성 핸드폰번호(-없이))", required=true, example = "")
        String PHONE_NO         = "0239970904";             // 전화번호
        @ApiParam(value="선불전자지급수단 번호 (전자민원캐시에 등록된 번호)", required=true, example = "")
        String PREPAYMENT_NO    = "P00001111222";           // 선불전자지급수단 번호
        @ApiParam(value="선불전자지급수단 비밀번호 (전자민원캐시에 등록된 비밀번호)", required=true, example = "")
        String PREPAYMENT_PW    = "password";               // 선불전자지급수단 비밀번호
        @ApiParam(value="등기기록 유형 (1: 말소사항 포함` 2: 현재유효사항, 3: 현재소유현황, 4: 특정인지분, 5: 지분취득이력)", required=true, example = "")
        String REGISTERED_TYPE  = "1";                      // 등기기록 유형 (1: 말소사항 포함` 2: 현재유효사항, 3: 현재소유현황, 4: 특정인지분, 5: 지분취득이력)
        @ApiParam(value="명의인", required=false, example = "")
        String PERSON_NAME      = "";                       // 명의인
        @ApiParam(value="주민등록번호", required=false, example = "")
        String JUMIN_NO         = "";                       // 주민등록번호
        @ApiParam(value="부동산 고유번호", required=true, example = "")
        String REAL_ESTATE_NO   = "21042164923400";         // 부동산 고유번호
        @ApiParam(value="출력옵션 (11: 전체, 10: 공동담보/전세목록, 01: 매매목록록)", required=false, example = "")
        String OUTPUT_OPTION    = "11";                     // 출력옵션 (11: 전체, 10: 공동담보/전세목록, 01: 매매목록록)
        @ApiParam(value="PDF 저장 유무 (0:택스트출력, 1:xps저장, 2:pdf저장)", required=true, example = "")
        String PRINT_SAVE       = "0";                      // PDF 저장 유무 (1: 사용, 그외 : 미사용)
        @ApiParam(value="등기사항 요약 사용유무 (1:사용, 그외 : 미사용)", required=true, example = "")
        String SUMMARY_YN       = "0";                      // 등기사항 요약 사용유무 (1:사용, 그외 : 미사용)
        @ApiParam(value="열람구분 (미입력: 최초조회 1: 미열람문서 조회, 2: 재열람문서 조회)", required=false, example = "")
        String READ_TYPE        = "";                       // 열람구분 (미입력: 최초조회 1: 미열람문서 조회, 2: 재열람문서 조회)
    }
}
