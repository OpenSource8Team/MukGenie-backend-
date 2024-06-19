# 🍟[Muk_Geine] 모바 애플리케이션  - 활용한 질문형 음식 추천 프로그램 


<br>



## 프로그램 실행 방법 

#1. 안드로이드 스튜디오와 MongoDB등 시스템 환경 변수 설정 
#2. MukGeine 레파지토뢰 :- Matster(최종) 브랜치 체크아웃 이후 ex) VS codde 환경에서 
 npm i   이후  npm run android 
#3.  ex) (IntelliJ) 백앤드 환경에서 MongoDB(MUK DB) 를 연동한 이후 MuKGeine.Application 실행 
#4. Android Virtual device 애뮬레이터 실행 + 안드로이드 스튜디오 IDE  TERMINAL 에서  
 adb -s emulator-5554 reverse tcp:8080 tcp:8080 
명령어를 입력한 이후 애플리케이션을 테스트 한다.


 시스템 환경변수에 안드로이드 스튜디오 값을 삽입해준다 .![image](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/a7650d27-83b1-4f04-adf3-11fba24a8dc9)

 ![image](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/75e4cdcf-ef1e-4e1c-8303-54a06afb1fb4)

 '
## 팀원 구성


| **박주민** | **이승민** | **이재원** | **장우진** |

</div>

<br>

## 1. 개발 환경

- Front : HTML, ReactNative, styled-components, Recoil
- Back-end : java17 , MongoDB
- 버전 관리 : Github, Github Project
- 협업 툴 : Discord,KakaoTalk, Github  
<br>



## 2. 프로젝트 구조

![image](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/caf1a8fc-ba35-4119-a5fe-585c3af07765)

**React Native App**: 사용자 인터페이스를 제공하는 애플리케이션입니다.


**안드로이드 스튜디오 (에뮬레이터)**: React Native 앱을 테스트하는 가상 장치입니다.

**로그인/회원가입 페이지**: 사용자가 앱에 접속하여 로그인 또는 회원가입을 진행합니다.
회원가입 시, 패스워드를 암호화하여 백엔드로 전송합니다.

**Backend (JAVA)**: 모든 데이터 처리 및 로직을 담당하는 서버 측 Java 애플리케이션입니다.

회원가입 시, 암호화된 패스워드를 MongoDB에 저장합니다.

로그인 시, 토큰을 생성하여 로그인 상태를 유지합니다.

**MongoDB**: 데이터베이스로서 사용자 정보와 메뉴 관련 데이터를 저장합니다.

**알러지 유발 식품 필터링 페이지**: 사용자가 설정한 알레르기 식품을 필터링하고 관리하는 페이지입니다.

**J48 알고리즘 및 데이터 처리**: 사용자의 입력을 기반으로 음식 추천과 관련된 데이터 처리를 수행합니다.

**로그 기록 및 토큰 관리**: 사용자 로그인 상태를 유지하기 위한 토큰 관리와 사용자 활동 로그를 저장합니다.

**메인 질문 페이지**: 사용자의 음식 선호도를 파악하고 추가 질문을 통해 추천을 수행합니다.

**최종 메뉴 결과 제공 페이지**: 최종적으로 추천된 음식의 메뉴와 사진을 제공하며, 외부 링크를 통해 유용한 정보를 제공합니다.

**menu.arff 파일**: J48 알고리즘을 사용하는 데 필요한 데이터 파일로서, 자바 프로젝트 내에 위치하고 있습니다.




### 작업 관리

- 디스코드 채팅앱과 카카오톡 채팅방을 사용하여 진행 상황을 공유했습니다.
- 주간회의를 진행하며 작업 순서와 방향성에 대한 고민을 나누고 따 회의 내용을 기록했습니다.

	프로그램 실행 준비 과정 : 
#1. 안드로이드 스튜디오와 MongoDB등 시스템 환경 변수 설정 
#2. MukGeine 레파지토뢰 :- Matster(최종) 브랜치 체크아웃 이후 ex) VS codde 환경에서 
 npm i   이후  npm run android 
#3.  ex) (IntelliJ) 백앤드 환경에서 MongoDB(MUK DB) 를 연동한 이후 MuKGeine.Application 실행 
#4. Android Virtual device 애뮬레이터 실행 + 안드로이드 스튜디오 IDE  TERMINAL 에서  
 adb -s emulator-5554 reverse tcp:8080 tcp:8080 
명령어를 입력한 이후 애플리케이션을 테스트 한다.
—--> 프로그램 실행 화면 (시나리오 과정) 
1, 음식(메뉴) 선택에 고민을 가지는 사용자가 메뉴 추천 프로그램을 사용한다.
![스크린샷 2024-06-19 125821](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/6264059c-5310-4465-851c-c9080461e4a6)

2. 회원가입/로그인 기능을 통해 회원정보를 입력하여서 메인 페이지로 이동한다.
![스크린샷 2024-06-19 125926](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/e9320069-e8e5-4d43-a2f6-ebcb2dc2edd4)

2-1 질문 대기페이지에서 토근 인증시스템과,로그 기록 기능으로 이전에 프로그램으로부터 받았던 최종 결과를 확인할 수 있다. )
![스크린샷 2024-06-19 132324](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/87a08ef7-7704-47b0-9734-0e043b9d8008)

3. 본격적으로 메뉴 선별을 하기위한 질문을 하기에 앞서 호불호/알러지 유발 식품의         식재료를 필터링 하는 작업을 거친다 
![스크린샷 2024-06-19 130229](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/162e31ea-5fa4-4c23-8f82-9fd09b96f470)

4. 메뉴 선정의 질문타입에 따라 최소 7개 이상의 질문을 거쳐 최종적으로 프로그램으로부터 유저는 메뉴를 추천받는다 [나라 , 재료, 온도, 맵기, 국물 ,기름기,조리타입  질문]
![스크린샷 2024-06-19 130318](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/08d8e8a3-0e9e-4612-9358-a54d3c58cd9a)

5.  선택된 메뉴에 대한 (유튜브) 레시피 영상과 (네이버) 지도를 통한 내 근처 해당 음식집을 찾아주는 서비스를 제공 받을 수 있다.  

![스크린샷 2024-06-19 131120](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/9727c761-2980-4b9c-8181-9c6fd6b2b7cb)

 6. 결과 메뉴 추천받은 이후 유튜브 , 네이버로 음식점 검색 화면 
![스크린샷 2024-06-19 132048](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/18b7ac7f-eda6-4837-b1b1-ef5b97ae31dd)

![스크린샷 2024-06-19 132231](https://github.com/OpenSource8Team/MukGenie-backend-/assets/108183797/c2cf807b-26bb-46e0-a172-75a0be5d0053)



