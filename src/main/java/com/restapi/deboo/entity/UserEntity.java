package com.restapi.deboo.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value="유저 인덱스  NOTNULL  AI  INTEGER")
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    @ApiModelProperty(value="유저 이름  VARCHAR(50)  NOTNULL")
    private String name;

    @Email
    @Column(name = "email", length = 100, nullable = false)
    @ApiModelProperty(value="유저 이메일  VARCHAR(100)  NOTNULL")
    private String email;

    @Column(name = "phone", length = 50, nullable = false)
    @ApiModelProperty(value="유저 휴대전화  VARCHAR(50)  NOTNULL")
    private String phone;

    @Column(name = "type", nullable = false)
    @ApiModelProperty(value="유저 타입 (0:로컬, 1: 카카오, 2: 페이스북)  INTEGER  NOTNULL  DEFAULT 0")
    private int type = 0;

    @Column(name = "gender", nullable = false)
    @ApiModelProperty(value="유저 성별 (0:남자, 1:여자)  INTEGER  NOTNULL")
    private int gender;

    @Column(name = "access_token", length = 50, nullable = true)
    @ApiModelProperty(value="소셜회원 발급 토큰  VARCHAR(50)")
    private String access_token;

    @Column(name = "isActive", nullable = false)
    @ApiModelProperty(value="회원상태 (0: 비활성화, 1:활성화)  BOOLEAN  NOTNULL  DEFAULT 1")
    private Boolean isActive = true;

    @Column(name = "auth", nullable = false)
    @ApiModelProperty(value="유저 JWT 권한 구분자  VARCHAR(255)  NOTNULL  DEFAULT 'ROLE_USER'")
    private String auth = "ROLE_USER";

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @ApiModelProperty(value="유저 레코드 생성일  DATETIME  NOTNULL  DEFAULT NOW")
    private Date createAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @ApiModelProperty(value="유저 레코드 최근 수정일  DATETIME  NOTNULL  DEFAULT NOW")
    private Date updateAt;

    @Getter
    @Setter
    @Data
    public static class localLoginEntity {
        @NotNull(message = "phone은 null을 허용하지 않습니다")
        @Size(min=1, message="phone을 입력해 주세요")
        String phone;

        @NotNull(message = "name은 null을 허용하지 않습니다")
        @Size(min=1, message="name을 입력해 주세요")
        String name;
    }

    @Getter
    @Setter
    @Data
    public static class socialLoginEntity {
        @NotNull(message = "access_token은 null을 허용하지 않습니다")
        @Size(min=1, message="access_token을 입력해 주세요")
        String access_token;

        @NotNull(message = "phone은 null을 허용하지 않습니다")
        @Size(min=1, message="phone을 입력해 주세요")
        String phone;

        @NotNull(message = "name은 null을 허용하지 않습니다")
        @Size(min=1, message="name을 입력해 주세요")
        String name;
    }

    @Getter
    @Setter
    @Data
    public static class profile {
        @NotNull(message = "name은 null을 허용하지 않습니다")
        @Size(min=1, message="name을 입력해 주세요")
        String name;

        @NotNull(message = "gender은 null을 허용하지 않습니다")
        int gender;

        @Email
        @NotNull(message = "email은 null을 허용하지 않습니다")
        @Size(min=1, message="email을 입력해 주세요")
        String email;

        @NotNull(message = "phone은 null을 허용하지 않습니다")
        @Pattern(message = "phone은 숫자만 허용합니다", regexp="^[0-9]*$")
        @Size(min=1, message="phone을 입력해 주세요")
        String phone;
    }

    @Getter
    @Setter
    @Data
    public static class create {
        @NotNull(message = "name은 null을 허용하지 않습니다")
        @Size(min=1, message="name을 입력해 주세요")
        @ApiParam(value = "유저 이름  VARCHAR(50)  NOTNULL", required=true, example = "")
        String name;

        @NotNull(message = "gender은 null을 허용하지 않습니다")
        @ApiParam(value = "유저 성별 (0:남자, 1:여자)  INTEGER  NOTNULL", required=true, example = "")
        int gender;

        @Email
        @NotNull(message = "email은 null을 허용하지 않습니다")
        @Size(min=1, message="email을 입력해 주세요")
        @ApiParam(value = "유저 이메일  VARCHAR(100)  NOTNULL", required=true, example = "")
        String email;

        @NotNull(message = "phone은 null을 허용하지 않습니다")
        @Pattern(message = "phone은 숫자만 허용합니다", regexp="^[0-9]*$")
        @Size(min=1, message="phone을 입력해 주세요")
        @ApiParam(value = "유저 휴대전화  VARCHAR(50)  NOTNULL", required=true, example = "")
        String phone;

        @NotNull(message = "type은 null을 허용하지 않습니다")
        @ApiParam(value = "유저 타입 (0:로컬, 1: 카카오, 2: 페이스북)  INTEGER  NOTNULL  DEFAULT 0", required=true, example = "")
        int type = 0;

        @ApiParam(value = "유저 JWT 권한 구분자  VARCHAR(255)  NOTNULL  DEFAULT 'ROLE_USER'", required=false, example = "")
        String auth = "ROLE_USER";

        @ApiParam(value = "소셜회원 발급 토큰  VARCHAR(50)", required=false, example = "")
        String access_token = "";

        @ApiParam(value = "회원상태 (0: 비활성화, 1:활성화)  BOOLEAN  NOTNULL  DEFAULT 1", required=false, example = "")
        boolean isActive = true;
    }
}