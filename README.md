# 일정 관리 API

ScheduleManagement는 일정 관리 기능을 제공하는 RESTful API 프로젝트입니다.  
이 프로젝트를 통해 스케줄 관리 기능 구현뿐만 아니라, 실제 개발 과정에서 마주한 설계·구조·데이터 매핑 문제를 해결하며 학습한 경험을 담고 있습니다.


---

## 프로젝트 개요

- 일정 생성, 조회, 수정, 삭제 가능
- 일정별 댓글 작성 가능
- 간단한 일정 관리 및 소셜 기능(댓글)을 제공

---

## 프로젝트 기간

- 2025.11.03 ~ 2025.11.05

---

## 기술 스택

- **Language**: Java 17
- **Framework**: Spring Boot 3.x
- **Persistence**: JPA / Hibernate
- **Build Tool**: Gradle
- **Database**: MySQL
- **Validation**: Jakarta Bean Validation
- **Lombok**: Getter, Setter, Constructor 자동 생성
- **API 문서:** Postman
- **협업툴:** GitHub 
---

## ERD

![댓글포함된 ERD](https://github.com/user-attachments/assets/ea299014-c93d-4483-a445-f7f40756a86e)

---

## 주요 기능

| 기능 | 설명 |
|------|------|
| 일정 생성 | 새로운 일정을 등록합니다 |
| 일정 전체 조회 | 등록된 모든 일정을 조회합니다 |
| 일정 단건 조회 | 특정 ID의 일정을 조회합니다 |
| 일정 수정 | 특정 일정의 정보를 수정합니다 |
| 일정 삭제 | 특정 일정을 삭제합니다 |
| 댓글 생성 | 일정에 댓글을 작성합니다 |

---

## API 명세

Postman에서 확인 가능합니다: [API 문서 링크](https://documenter.getpostman.com/view/24302728/2sB3WpRgG6)


## 패키지구조
org.example.schedulemanagement
controller # REST API 컨트롤러
dto # 요청/응답 DTO
schedule
entity # JPA 엔티티
repository # Spring Data JPA 레포지토리
service # 비즈니스 로직
exception # 예외 처리 클래스
config # 환경/설정 관련 클래스


