# 🍟 Muk_Geine 모바일 애플리케이션 - 질문형 음식 추천 프로그램

<br>

## 프로그램 실행 방법 

1. **안드로이드 스튜디오와 MongoDB 시스템 환경 변수 설정**
2. **MukGeine 레파지토리 Clone:**
   - Matster(최종) 브랜치 체크아웃 후, VS Code 등에서 실행
   - npm i 후 npm run android 실행
3. **백엔드 환경 설정:**
   - IntelliJ 등에서 MongoDB(MUK DB) 연동 후 MuKGeine.Application 실행
4. **안드로이드 가상 장치 실행:**
   - 안드로이드 스튜디오 IDE TERMINAL에서 adb -s emulator-5554 reverse tcp:8080 tcp:8080 명령어 입력 후 애플리케이션 테스트

시스템 환경 변수에 안드로이드 스튜디오 값을 삽입해줍니다.

## 팀원 구성

| **박주민** | **이승민** | **이재원** | **장우진** |

<br>

## 1. 개발 환경

- **Front:** HTML, React Native, styled-components, Recoil
- **Back-end:** Java 17, MongoDB
- **버전 관리:** GitHub, GitHub Project
- **협업 툴:** Discord, KakaoTalk, GitHub

<br>

## 2. 프로젝트 구조

![프로젝트 구조](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/caf1a8fc-ba35-4119-a5fe-585c3af07765)

- **React Native App:** 사용자 인터페이스를 제공하는 애플리케이션
- **안드로이드 스튜디오 (에뮬레이터):** React Native 앱을 테스트하는 가상 장치
- **로그인/회원가입 페이지:** 사용자가 앱에 접속하여 로그인 또는 회원가입을 진행
- **Backend (JAVA):** 데이터 처리 및 로직을 담당하는 서버 측 Java 애플리케이션
- **MongoDB:** 사용자 정보와 메뉴 관련 데이터를 저장
- **알러지 유발 식품 필터링 페이지:** 사용자가 설정한 알레르기 식품을 관리하는 페이지
- **J48 알고리즘 및 데이터 처리:** 사용자의 입력을 기반으로 음식 추천과 데이터 처리
- **로그 기록 및 토큰 관리:** 사용자 로그인 상태 관리와 활동 로그 저장
- **메인 질문 페이지:** 사용자의 음식 선호도를 파악하고 추가 질문을 통해 추천
- **최종 메뉴 결과 제공 페이지:** 추천된 음식의 메뉴와 사진 제공, 외부 링크를 통한 유용한 정보 제공
- **menu.arff 파일:** J48 알고리즘을 위한 데이터 파일

<br>

## 작업 관리

- **디스코드**와 **카카오톡**을 활용한 작업 공유
- **주간 회의**를 통한 작업 방향성 고민 및 기록

### 프로그램 실행 준비 과정

1. **안드로이드 스튜디오와 MongoDB 시스템 환경 변수 설정**
2. **MukGeine 레파지토리 Clone:** Matster(최종) 브랜치 체크아웃 후, VS Code 등에서 실행
   - `npm i` 후 `npm run android` 실행
3. **백엔드 환경 설정:** IntelliJ 등에서 MongoDB(MUK DB) 연동 후 MuKGeine.Application 실행
4. **안드로이드 가상 장치 실행:** 안드로이드 스튜디오 IDE TERMINAL에서 `adb -s emulator-5554 reverse tcp:8080 tcp:8080` 명령어 입력 후 애플리케이션 테스트

### 프로그램 실행 화면 (시나리오 과정)

1. 사용자가 메뉴 추천 프로그램을 사용하여 음식(메뉴)을 선택하는 과정
   ![화면 1](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/6264059c-5310-4465-851c-c9080461e4a6)
   
2. 회원가입/로그인 기능을 통해 회원정보를 입력하고 메인 페이지로 이동
   ![화면 2](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/e9320069-e8e5-4d43-a2f6-ebcb2dc2edd4)
   
3. 질문 대기 페이지에서 토큰 인증 시스템과 로그 기록 기능으로 이전 결과 확인
   ![화면 3](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/87a08ef7-7704-47b0-9734-0e043b9d8008)
   
4. 호불호/알러지 유발 식품 필터링을 거친 후 메뉴 선정 질문 시작
   ![화면 4](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/162e31ea-5fa4-4c23-8f82-9fd09b96f470)
   
5. 질문 타입에 따른 메뉴 선택과 관련된 질문을 최소 7개 이상 거친 후 최종 결과 확인
   ![화면 5](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/08d8e8a3-0e9e-4612-9358-a54d3c58cd9a)
   
6. 선택된 메뉴에 대한 유튜브 레시피 영상과 네이버 지도를 통한 음식점 검색 화면
   ![화면 6](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/18b7ac7f-eda6-4837-b1b1-ef5b97ae31dd)
   ![화면 7](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/c2cf807b-26bb-46e0-a172-75a0be5d0053)
