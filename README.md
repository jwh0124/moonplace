---
title: Moon Place

categories:
  - Dev
tags:
  - Plan
  
toc: true
toc_sticky: true
toc_label: "페이지 주요 목차"

last_modified_at: 2021-03-18T15:40:00+09:00
---

## Moon Place ##

> 맛집 리스트 서비스

### Project Dec ###

- 프로젝트명 : MoonPlace
- 프로젝트 기술 스팩
  - Back-end
    - Java, SpringBoot, JDK 11, JWT, Rest API, JPA, Flyway
    - python, django
  - Front-end
    - Vuejs
    - React
  - App : ?
- 기획
  - 요구사항
  - 카테고리
  - 서비스 기능 및 화면 리스트
  - 메뉴 트리
- 설계
  - ERD
  - 화면 설계서
- 개발

### 요구사항 ###

- 쩝쩝이가 갔던 맛집 리스트를 저장할 수 있다.
- 쩝쩝이의 의견을 반영하여 Star를 설정할 수 있다.
- 지도 및 관련 정보를 볼 수 있다. (지도 API 사용)
- 방문 맛집에 리뷰를 작성할 수 있고, 사진등록 및 댓글도 달 수 있다.
- 로그인 처리 (JWT) -> 로그인 연동 (Naver, Google, Kakao) API 사용
- 맛집 주 메뉴에 태깅(나라별, 지역별) 개념을 통해 필터 가능
- 관리자, 사용자 분리
- 음식점(명칭, 주소, 전화번호, 대표음식, 휴일)
- 메뉴 및 음식점 추천 (Open API -> Crawling)
- 네비게이션 App 연동
- 채팅 서비스
- Kakao Message 서비스
