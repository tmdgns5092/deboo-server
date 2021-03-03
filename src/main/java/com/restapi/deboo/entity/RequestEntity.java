package com.restapi.deboo.entity;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="loan_request")
public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="유저 인덱스 NOTNULL AI INTEGER)")
    private int id;


    // 7.자금용도
    // ===================================================================
    @NotNull(message = "fund_purpose은 null을 허용하지 않습니다")
    @Column(name = "fund_purpose", length=1000, nullable = false)
    @ApiModelProperty(value="자금용도 (1:신탁혜지 자금, 2:전월세 퇴거자금(보증금), 3:매매잔금 대출, 4:사업자 대출, 5:기타 혜지자금 대출, 6:대환 및 추가 대출, 7:선순위 대출, 8:후순위 대출)  INTEGER  NOTNULL")
    @ApiParam(value = "자금용도 (1:신탁혜지 자금, 2:전월세 퇴거자금(보증금), 3:매매잔금 대출, 4:사업자 대출, 5:기타 혜지자금 대출, 6:대환 및 추가 대출, 7:선순위 대출, 8:후순위 대출)  INTEGER  NOTNULL", required=true, example = "")
    private int fund_purpose;


    // 8.담보 제공자
    // ===================================================================
    @NotNull(message = "same_person은 null을 허용하지 않습니다")
    @Column(name = "same_person", nullable = false)
    @ApiModelProperty(value="담보 제공자 동일인  BOOELAN  NOTNULL")
    @ApiParam(value = "담보 제공자 동일인  BOOELAN  NOTNULL", required=true, example = "")
    private Boolean same_person;

    @NotNull(message = "provider_sortation은 null을 허용하지 않습니다")
    @Column(name = "provider_sortation", nullable = false)
    @ApiModelProperty(value="담보 제공자 (0:내국인, 1:외국인, 2:법인, 3:기타)  INTEGER NOTNULL")
    @ApiParam(value = "담보 제공자 (0:내국인, 1:외국인, 2:법인, 3:기타)  INTEGER NOTNULL", required=true, example = "")
    private int provider_sortation;

    @Column(name = "debtor_sortation", nullable = true)
    @ApiModelProperty(value="채무자 구분 (0:없음, 1:내국인, 2:외국인, 3:법인, 4:기타)  INTEGER")
    @ApiParam(value = "채무자 구분 (0:없음, 1:내국인, 2:외국인, 3:법인, 4:기타)  INTEGER", required=false, example = "")
    private int debtor_sortation;

    @Column(name = "relationship", nullable = true)
    @ApiModelProperty(value="담보 제공자 관계 (0:본인, 1:부, 2:모, 3:제, 4:친구, 5:기)  INTEGER")
    @ApiParam(value = "담보 제공자 관계 (0:본인, 1:부, 2:모, 3:제, 4:친구, 5:기)  INTEGER", required=false, example = "")
    private int relationship;


    // 11.담보 제공지 종류
    // ===================================================================
    @NotNull(message = "provision_place_type은 null을 허용하지 않습니다")
    @Column(name = "provision_place_type", nullable = false)
    @ApiModelProperty(value="담보 제공지 종류 (1:아파트, 2:아파트(주상 복합), 3:오피스텔, 4:빌라, 5:상가, 6:단독주택, 7:토지) INTEGER  NOTNULL")
    @ApiParam(value = "담보 제공지 종류 (1:아파트, 2:아파트(주상 복합), 3:오피스텔, 4:빌라, 5:상가, 6:단독주택, 7:토지) INTEGER  NOTNULL", required=true, example = "")
    private int provision_place_type;


    // 12.담보 소재지
    // ===================================================================
    @NotNull(message = "jibun_address은 null을 허용하지 않습니다")
    @Size(min=1, message="jibun_address을 입력해 주세요")
    @Column(name = "jibun_address", length = 60, nullable = false)
    @ApiModelProperty(value="지번 주소  VARCHAR(60)  NOTNULL")
    @ApiParam(value = "지번 주소  VARCHAR(60)  NOTNULL", required=true, example = "")
    private String jibun_address;

    @NotNull(message = "road_address은 null을 허용하지 않습니다")
    @Size(min=1, message="road_address을 입력해 주세요")
    @Column(name = "road_address", length = 60, nullable = false)
    @ApiModelProperty(value="도로명 주소  VARCHAR(60)  NOTNULL")
    @ApiParam(value = "도로명 주소  VARCHAR(60)  NOTNULL", required=true, example = "")
    private String road_address;

    @NotNull(message = "gugn_name은 null을 허용하지 않습니다")
    @Size(min=1, message="gugn_name을 입력해 주세요")
    @Column(name = "gugn_name", length = 60, nullable = false)
    @ApiModelProperty(value="구군명  VARCHAR(60)  NOTNULL")
    @ApiParam(value = "구군명  VARCHAR(60)  NOTNULL", required=true, example = "")
    private String gugn_name;

    @NotNull(message = "dong_name은 null을 허용하지 않습니다")
    @Size(min=1, message="dong_name을 입력해 주세요")
    @Column(name = "dong_name", length = 60, nullable = false)
    @ApiModelProperty(value="읍면동  VARCHAR(60)  NOTNULL")
    @ApiParam(value = "읍면동  VARCHAR(60)  NOTNULL", required=true, example = "")
    private String dong_name;

    @NotNull(message = "ldong_code은 null을 허용하지 않습니다")
    @Size(min=1, message="ldong_code을 입력해 주세요")
    @Column(name = "ldong_code", length = 15, nullable = false)
    @ApiModelProperty(value="법정동 코드  VARCHAR(15)  NOTNULL")
    @ApiParam(value = "법정동 코드  VARCHAR(15)  NOTNULL", required=true, example = "")
    private String ldong_code;

    @NotNull(message = "sido_name은 null을 허용하지 않습니다")
    @Size(min=1, message="sido_name을 입력해 주세요")
    @Column(name = "sido_name", length = 30, nullable = false)
    @ApiModelProperty(value="시도명  VARCHAR(30)  NOTNULL")
    @ApiParam(value = "시도명  VARCHAR(30)  NOTNULL", required=true, example = "")
    private String sido_name;


    // 13.소유 형태
    // ===================================================================
    @NotNull(message = "address_form은 null을 허용하지 않습니다")
    @Column(name = "address_form", nullable = false)
    @ApiModelProperty(value="주소지의 소유 형태 (1:단독소유, 2:지분대출, 3:공동담보대출) INTEGER  NOTNULL")
    @ApiParam(value = "주소지의 소유 형태 (1:단독소유, 2:지분대출, 3:공동담보대출) INTEGER  NOTNULL", required=true, example = "")
    private int address_form;


    // 16.담보 임대상태 확인
    // ===================================================================
    @NotNull(message = "is_retal은 null을 허용하지 않습니다")
    @Column(name = "is_retal", nullable = false)
    @ApiModelProperty(value="담보 제공지 임대중 여부  BOOELAN  NOTNULL")
    @ApiParam(value = "담보 제공지 임대중 여부  BOOELAN  NOTNULL", required=true, example = "")
    private Boolean is_retal;

    @NotNull(message = "owner_of_rental은 null을 허용하지 않습니다")
    @Column(name = "owner_of_rental", nullable = false)
    @ApiModelProperty(value="임대인 (0:해당안함, 1:내국인, 2:외국인, 3:법인, 4:기타)  INTEGER  NOTNULL")
    @ApiParam(value = "임대인 (0:해당안함, 1:내국인, 2:외국인, 3:법인, 4:기타)  INTEGER  NOTNULL", required=true, example = "")
    private int owner_of_rental;

    @NotNull(message = "rental_type은 null을 허용하지 않습니다")
    @Column(name = "rental_type", nullable = false)
    @ApiModelProperty(value="임대 형태 (0:해당안함, 1:전세, 2:월세)  INTEGER  NOTNULL")
    @ApiParam(value = "임대 형태 (0:해당안함, 1:전세, 2:월세)  INTEGER  NOTNULL", required=true, example = "")
    private int rental_type;

    @NotNull(message = "rental_deposit은 null을 허용하지 않습니다")
    @Size(min=1, message="rental_deposit을 입력해 주세요")
    @Column(name = "rental_deposit", length = 1000, nullable = false)
    @ApiModelProperty(value="전세 보증금  varchar(1000)  NOTNULL")
    @ApiParam(value = "전세 보증금  varchar(1000)  NOTNULL", required=true, example = "")
    private String rental_deposit;

    @NotNull(message = "tenant은 null을 허용하지 않습니다")
    @Column(name = "tenant", nullable = false)
    @ApiModelProperty(value="세입자 동의 BOOELAN NOTNULL")
    @ApiParam(value = "세입자 동의 BOOELAN NOTNULL", required=true, example = "")
    private boolean tenant;


    // (대출신청-5)페이지 - 등기부등본 조회 API에게 넘기는 정보
    // ===================================================================
    @NotNull(message = "registered_records은 null을 허용하지 않습니다")
    @Column(name = "registered_records", nullable = false)
    @ApiModelProperty(value="등기 기록 유형 (0:말소사항포함, 1:현재 유효사항, 2:현재소유현황, 3:특정인지분, 4:지분취득이력) INTEGER  NOTNULL")
    @ApiParam(value = "등기 기록 유형 (0:말소사항포함, 1:현재 유효사항, 2:현재소유현황, 3:특정인지분, 4:지분취득이력) INTEGER  NOTNULL", required=true, example = "")
    private int registered_records;

    @NotNull(message = "owner_name은 null을 허용하지 않습니다")
    @Size(min=1, message="owner_name을 입력해 주세요")
    @Column(name = "owner_name", length = 10, nullable = false)
    @ApiModelProperty(value="명의인 성명 VARCHAR(10)  NOTNULL")
    @ApiParam(value = "명의인 성명 VARCHAR(10)  NOTNULL", required=true, example = "")
    private String owner_name;

    @NotNull(message = "owner_number은 null을 허용하지 않습니다")
    @Size(min=1, message="owner_number을 입력해 주세요")
    @Column(name = "owner_number", length = 20, nullable = false)
    @ApiModelProperty(value="부동산 고유 번호 VARCHAR(20)  NOTNULL")
    @ApiParam(value = "부동산 고유 번호 VARCHAR(20)  NOTNULL", required=true, example = "")
    private String owner_number;








    // (대출신청-8)페이지 - 담보 제공지상태 확인
    // ===================================================================
    @Column(name = "provision_place_square", length = 255, nullable = true)
    @ApiModelProperty(value="담보제공지 면적  varchar(255)")
    @ApiParam(value = "담보제공지 면적  varchar(255)", required=false, example = "")
    private String provision_place_square;

    @Column(name = "provision_place_state", length = 50, nullable = true)
    @ApiModelProperty(value="담보제공지 상태  varchar(50)")
    @ApiParam(value = "담보제공지 상태  varchar(50)", required=false, example = "")
    private String provision_place_state;

    @Column(name = "provision_place_amount", length = 1000, nullable = true)
    @ApiModelProperty(value="압류금액  varchar(1000)")
    @ApiParam(value = "압류금액  varchar(1000)", required=false, example = "")
    private String provision_place_amount;


    // (대출신청-9)페이지 - 미납내역 확인
    // ===================================================================
    @NotNull(message = "unpaid_0은 null을 허용하지 않습니다")
    @Column(name = "unpaid_0", nullable = false)
    @ApiModelProperty(value="압류금액 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL")
    @ApiParam(value = "압류금액 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL", required=true, example = "")
    private int unpaid_0 = 0;

    @NotNull(message = "unpaid_1은 null을 허용하지 않습니다")
    @Column(name = "unpaid_1", nullable = false)
    @ApiModelProperty(value="지방세 미납 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL")
    @ApiParam(value = "지방세 미납 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL", required=true, example = "")
    private int unpaid_1 = 0;

    @NotNull(message = "unpaid_2은 null을 허용하지 않습니다")
    @Column(name = "unpaid_2", nullable = false)
    @ApiModelProperty(value="4대보험 미납 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL")
    @ApiParam(value = "4대보험 미납 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL", required=true, example = "")
    private int unpaid_2 = 0;

    @NotNull(message = "unpaid_3은 null을 허용하지 않습니다")
    @Column(name = "unpaid_3", nullable = false)
    @ApiModelProperty(value="카드연체 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL")
    @ApiParam(value = "카드연체 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL", required=true, example = "")
    private int unpaid_3 = 0;

    @NotNull(message = "unpaid_4은 null을 허용하지 않습니다")
    @Column(name = "unpaid_4", nullable = false)
    @ApiModelProperty(value="기타연체 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL")
    @ApiParam(value = "기타연체 (0이 아니면 국세미납금액이 있는 것)  INTEGER  NOTNULL", required=true, example = "")
    private int unpaid_4 = 0;

    @NotNull(message = "unpaid_no은 null을 허용하지 않습니다")
    @Column(name = "unpaid_no", nullable = false)
    @ApiModelProperty(value="미납내역 없음  BOOLEAN  NOTNULL")
    @ApiParam(value = "미납내역 없음  BOOLEAN  NOTNULL", required=true, example = "")
    private boolean unpaid_no = false;

    @NotNull(message = "unpaid_idk은 null을 허용하지 않습니다")
    @Column(name = "unpaid_idk", nullable = false)
    @ApiModelProperty(value="미납내역 모름  BOOLEAN  NOTNULL")
    @ApiParam(value = "미납내역 모름  BOOLEAN  NOTNULL", required=true, example = "")
    private boolean unpaid_idk = false;


    // (대출신청-10)페이지 - 조회된 채권 금액 확인
    // ===================================================================
    @Column(name = "first_bond_amount", nullable = true)
    @ApiModelProperty(value="1순위 채권금액  INTEGER")
    @ApiParam(value = "1순위 채권금액  INTEGER", required=false, example = "")
    private int first_bond_amount = 0;

    @Column(name = "first_bond_original_amount", nullable = true)
    @ApiModelProperty(value="1순위 원금 또는 잔액  INTEGER")
    @ApiParam(value = "1순위 원금 또는 잔액  INTEGER", required=false, example = "")
    private int first_bond_original_amount = 0;

    @Column(name = "second_bond_amount", nullable = true)
    @ApiModelProperty(value="2순위 채권금액  INTEGER")
    @ApiParam(value = "2순위 채권금액  INTEGER", required=false, example = "")
    private int second_bond_amount = 0;

    @Column(name = "second_bond_original_amount", nullable = true)
    @ApiModelProperty(value="2순위 원금 또는 잔액  INTEGER")
    @ApiParam(value = "2순위 원금 또는 잔액  INTEGER", required=false, example = "")
    private int second_bond_original_amount = 0;

    @Column(name = "third_bond_amount", nullable = true)
    @ApiModelProperty(value="3순위 채권금액  INTEGER")
    @ApiParam(value = "3순위 채권금액  INTEGER", required=false, example = "")
    private int third_bond_amount = 0;

    @Column(name = "third_bond_original_amount", nullable = true)
    @ApiModelProperty(value="3순위 원금 또는 잔액  INTEGER")
    @ApiParam(value = "3순위 원금 또는 잔액  INTEGER", required=false, example = "")
    private int third_bond_original_amount = 0;


    // (대출신청-11)페이지 - 필요 자금 선택
    // ===================================================================
    @NotNull(message = "necessary_fund은 null을 허용하지 않습니다")
    @Size(min=1, message="necessary_fund을 입력해 주세요")
    @Column(name = "necessary_fund", length=1000, nullable = false)
    @ApiModelProperty(value="필요자금  VARCHAR(1000)  NOTNULL")
    @ApiParam(value = "필요자금  VARCHAR(1000)  NOTNULL", required=true, example = "")
    private String necessary_fund;


    // (대출신청-12)페이지 - 자금 용도 선택
    // ===================================================================


    @NotNull(message = "balance_of_trade은 null을 허용하지 않습니다")
    @Size(min=1, message="balance_of_trade을 입력해 주세요")
    @Column(name = "balance_of_trade", length=1000, nullable = false)
    @ApiModelProperty(value="기타 - 매매잔금  VARCHAR(1000)  NOTNULL")
    @ApiParam(value = "기타 - 매매잔금  VARCHAR(1000)  NOTNULL", required=true, example = "")
    private String balance_of_trade;


    // (대출신청-13)페이지 - 자금 필요 시점 선택
    // ===================================================================
    @NotNull(message = "need_date은 null을 허용하지 않습니다")
    @Column(name = "need_date", nullable = false)
    @ApiModelProperty(value="자금 필요 날짜  DATETIME  NOTNULL")
    @ApiParam(value = "자금 필요 날짜  DATETIME  NOTNULL", required=true, example = "")
    private Date need_date = new Date();


    // (대출신청-14)페이지 - 상환 방법 선택
    // ===================================================================
    @NotNull(message = "repayment_method은 null을 허용하지 않습니다")
    @Column(name = "repayment_method", nullable = false)
    @ApiModelProperty(value="상환 방법 (0:원리금 균등상환, 1:만기 일시상환)  INTEGER  NOTNULL")
    @ApiParam(value = "상환 방법 (0:원리금 균등상환, 1:만기 일시상환)  INTEGER  NOTNULL", required=true, example = "")
    private int repayment_method;


    // (대출신청-15)페이지 - 직업 선택
    // ===================================================================
    @NotNull(message = "job은 null을 허용하지 않습니다")
    @Column(name = "job", nullable = false)
    @ApiModelProperty(value="직업 (0:직장인, 1:무직, 2:프리랜서, 3:법인 사업자, 4:개인 사업자, 5:주부, 6:기타)  INTEGER  NOTNULL")
    @ApiParam(value = "직업 (0:직장인, 1:무직, 2:프리랜서, 3:법인 사업자, 4:개인 사업자, 5:주부, 6:기타)  INTEGER  NOTNULL", required=true, example = "")
    private int job;

    @NotNull(message = "month_income은 null을 허용하지 않습니다")
    @Column(name = "month_income", nullable = false)
    @ApiModelProperty(value="월 소득액 or 배우자 월 소득액  INTEGER  NOTNULL")
    @ApiParam(value = "월 소득액 or 배우자 월 소득액  INTEGER  NOTNULL", required=true, example = "")
    private int month_income;

    @NotNull(message = "year_income은 null을 허용하지 않습니다")
    @Column(name = "year_income", nullable = false)
    @ApiModelProperty(value="연 소득액 or 배우자 연 소득액  INTEGER  NOTNULL")
    @ApiParam(value = "연 소득액 or 배우자 연 소득액  INTEGER  NOTNULL", required=true, example = "")
    private int year_income;

    @NotNull(message = "proof_income은 null을 허용하지 않습니다")
    @Column(name = "proof_income", nullable = false)
    @ApiModelProperty(value="소득증빙 가능 여부  INTEGER  NOTNULL")
    @ApiParam(value = "소득증빙 가능 여부  INTEGER  NOTNULL", required=true, example = "")
    private boolean proof_income;

    @NotNull(message = "four_insurances은 null을 허용하지 않습니다")
    @Column(name = "four_insurances", nullable = false)
    @ApiModelProperty(value="4대보험 가입 유무  INTEGER  NOTNULL")
    @ApiParam(value = "4대보험 가입 유무  INTEGER  NOTNULL", required=true, example = "")
    private boolean four_insurances;


    // (대출신청-16)페이지 -  추가 컨설팅 상담유형 선택
    // ===================================================================
    @NotNull(message = "counsel_type은 null을 허용하지 않습니다")
    @Column(name = "counsel_type", nullable = false)
    @ApiModelProperty(value="상담유형 (0:전화상담, 1:메신저상담)  INTEGER  NOTNULL")
    @ApiParam(value = "상담유형 (0:전화상담, 1:메신저상담)  INTEGER  NOTNULL", required=true, example = "")
    private int counsel_type;

    @NotNull(message = "phone은 null을 허용하지 않습니다")
    @Size(min=1, message="phone을 입력해 주세요")
    @Column(name = "phone", length = 15, nullable = false)
    @ApiModelProperty(value="전화번호  VARCHAR(15)  NOTNULL")
    @ApiParam(value = "전화번호  VARCHAR(15)  NOTNULL", required=true, example = "")
    private String phone;


    // (대출신청-18)페이지 - 추가담보(자동차) 선택 & 차량상태 확인
    // ===================================================================
    @NotNull(message = "append_car은 null을 허용하지 않습니다")
    @Column(name = "append_car", nullable = false)
    @ApiModelProperty(value="자동차 담보 추가 여부  BOOLEAN  NOTNULL")
    @ApiParam(value = "자동차 담보 추가 여부  BOOLEAN  NOTNULL", required=true, example = "")
    private boolean append_car;


    // (대출신청-17)페이지 - 자동차 담보를 추가 했을 경우
    // ===================================================================
    @Column(name = "car_number", length = 20, nullable = true)
    @ApiModelProperty(value="차량번호  VARCHAR(20)")
    @ApiParam(value = "차량번호  VARCHAR(20)", required=false, example = "")
    private String car_number;

    @Column(name = "car_kind", length = 100, nullable = true)
    @ApiModelProperty(value="차종  VARCHAR(100)")
    @ApiParam(value = "차종  VARCHAR(100)", required=false, example = "")
    private String car_kind;

    @Column(name = "car_date", length= 10, nullable = true)
    @ApiModelProperty(value="연식  VARCHAR(10)")
    @ApiParam(value = "연식  VARCHAR(10)", required=false, example = "")
    private String car_date;

    @Column(name = "car_distance", nullable = true)
    @ApiModelProperty(value="주행거리  INTEGER")
    @ApiParam(value = "주행거리  INTEGER", required=false, example = "")
    private int car_distance;

    // (대출신청-18)페이지 - 자동차 담보를 추가 했을 경우
    // ===================================================================
    @Column(name = "car_state", nullable = true)
    @ApiModelProperty(value="차량의 상태 (0:압류, 1:가압류, 2:경매, 3:없음, 4:모름)  INTEGER")
    @ApiParam(value = "차량의 상태 (0:압류, 1:가압류, 2:경매, 3:없음, 4:모름)  INTEGER", required=false, example = "")
    private int car_state;

    @Column(name = "car_lose_money", nullable = true)
    @ApiModelProperty(value="압류금액, 가압류금액, 경매금액  INTEGER")
    @ApiParam(value = "압류금액, 가압류금액, 경매금액  INTEGER", required=false, example = "")
    private int car_lose_money;

    // (대출신청-19)페이지 - 자동차 담보를 추가 했을 경우
    // ===================================================================
    @Column(name = "car_unpaid_0", nullable = true)
    @ApiModelProperty(value="차량 국세 미납 (0이 아니면 국세미납액이 있는 것)  INTEGER")
    @ApiParam(value = "차량 국세 미납 (0이 아니면 국세미납액이 있는 것)  INTEGER", required=false, example = "")
    private int car_unpaid_0;

    @Column(name = "car_unpaid_1", nullable = true)
    @ApiModelProperty(value="차량 지방세 미납 (0이 아니면 국세미납액이 있는 것)  INTEGER")
    @ApiParam(value = "차량 지방세 미납 (0이 아니면 국세미납액이 있는 것)  INTEGER", required=false, example = "")
    private int car_unpaid_1;

    @Column(name = "car_unpaid_2", nullable = true)
    @ApiModelProperty(value="차량 4대보험 미납 (0이 아니면 국세미납액이 있는 것)  INTEGER")
    @ApiParam(value = "차량 4대보험 미납 (0이 아니면 국세미납액이 있는 것)  INTEGER", required=false, example = "")
    private int car_unpaid_2;

    @Column(name = "car_unpaid_3", nullable = true)
    @ApiModelProperty(value="차량 카드 연체 (0이 아니면 국세미납액이 있는 것)  INTEGER")
    @ApiParam(value = "차량 카드 연체 (0이 아니면 국세미납액이 있는 것)  INTEGER", required=false, example = "")
    private int car_unpaid_3;

    @Column(name = "car_unpaid_4", nullable = true)
    @ApiModelProperty(value="차량 기타 대출 연체(이자) (0이 아니면 국세미납액이 있는 것)  INTEGER")
    @ApiParam(value = "차량 기타 대출 연체(이자) (0이 아니면 국세미납액이 있는 것)  INTEGER", required=false, example = "")
    private int car_unpaid_4;

    @Column(name = "car_unpaid_no", nullable = true)
    @ApiModelProperty(value="차량 미납내역 없음  BOOLEAN")
    @ApiParam(value = "차량 미납내역 없음  BOOLEAN", required=false, example = "")
    private boolean car_unpaid_no;

    @Column(name = "car_unpaid_idk", nullable = true)
    @ApiModelProperty(value="차량 미납내역 모름  BOOLEAN")
    @ApiParam(value = "차량 미납내역 모름  BOOLEAN", required=false, example = "")
    private boolean car_unpaid_idk;


    // common
    // ===================================================================
    @Column(name = "user_id", nullable = false)
    @ApiModelProperty(value="신청자(유저) id")
    @ApiParam(value = "신청자(유저) id", required=false, example = "")
    private int user_id;

    @Column(name = "status", nullable = false)
    @ApiModelProperty(value="신청 상태 (0: 매칭대기, 1:매칭완료, 2:대출진행, 3:대출부결)  INTEGER  NOTNULL  DEFAULT 0")
    @ApiParam(value = "신청 상태 (0: 매칭대기, 1:매칭완료, 2:대출진행, 3:대출부결)", required=false, example = "0")
    private int status = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ApiModelProperty(value="레코드 생성일                        NOTNULL DATETIME")
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ApiModelProperty(value="레코드 최근 수정일                    NOTNULL DATETIME")
    private Date updateAt;
}
