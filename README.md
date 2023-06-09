<p align="center">
<img src = 'https://github.com/dh1010a/advisorChatbot/assets/51228946/1442adf7-2378-41b9-b6ab-e828d05f5b9e' width="30%" height="30%">
</p>

### <p align="center">기술 스택</p>
<div align="center">
 <img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white" />
 <img src="https://img.shields.io/badge/html5-E34F26?style=flat&logo=html5&logoColor=white"/>
 <img src="https://img.shields.io/badge/css3-1572B6?style=flat&logo=css3&logoColor=white"/>
 <img src="https://img.shields.io/badge/javascript-F7DF1E?style=flat&logo=javascript&logoColor=white"/>
<img src="https://img.shields.io/badge/spring-6DB33F?style=flat&logo=spring&logoColor=white"/>
 <img src="https://img.shields.io/badge/docker-2496ED?style=flat&logo=docker&logoColor=white"/>
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=flat&logo=springboot&logoColor=white"/>
  <img src="https://img.shields.io/badge/kubernetes-326CE5?style=flat&logo=kubernetes&logoColor=white"/>
 <img src="https://img.shields.io/badge/mysql-4479A1?style=flat&logo=mysql&logoColor=white"/>
</div>

<br>

# 컨테이너 기반 챗봇을 활용한 업무처리 시스템
산업 영역에서 챗봇에 대한 유지 보수 및 도입 이후 관리의 어려움을 해소하고자 <br>
쿠버네티스 기반의 효율적인 운영 및 관리를 지원하는 업무지원 챗봇을 만들었습니다.
 
## 🔎 미리보기
<p align="center">
<img src = "https://github.com/dh1010a/advisorChatbot/assets/51228946/e71e1af1-3d05-4b44-8f91-96ca6c7d0f51" height="310px" width="200px">
 <img src = "https://github.com/dh1010a/advisorChatbot/assets/51228946/628ef58f-ebe7-4245-8c44-253fc18a5277" height="310px" width="200px">
<img src = "https://github.com/dh1010a/advisorChatbot/assets/51228946/6fe1fa0d-0c33-4a41-a2b4-9a2fc1d91fd4" height="310px" width="200px">
 <img src = "https://github.com/dh1010a/advisorChatbot/assets/51228946/4ca2426d-649c-4d3c-8656-6cc680db1650" height="290px" width="200px">
</p>

## 📏 업무지원 기능
<p align="center">
<img src = 'https://github.com/dh1010a/advisorChatbot/assets/51228946/87d0cc7a-88ac-4278-9336-f68f968bb7ef' width="70%" height="70%">
</p>

- 연차 : 연차를 신청, 취소하고 신청 내역을 조회할 수 있는 기능
- 회의실 예약 : 잔여 회의실을 조회하고, 회의실을 신청하고 취소하며, 회의실 예약내역을 조회할 수 있는 기능
- 근무 : 자신이 오늘 몇 시간 근무를 했는지 알 수 잇는 근무 시간 조회 기능과 근무 시 사용할 자리를 선택할 수 있는 근무 자리 배정 기능
- 출장 : 출장에 필요한 비용과 출장의 목적, 일정, 인원 등을 기재하여 출장을 신청하는 기능과 그에 따라 출장 신청의 결재 현황을  <br> 볼 수 있는 기능,차량을 신청할 수 있는 차량 신청 기능, 회사의 차량을 조회해볼 수 있는 차량 조회 기능
- 사원 정보 조회 기능: 회사 사원의 이름을 입력하면 그 사원의 연락처와 이메일, 직위를 볼 수 있는 기능과 사원의 오늘 출근 현황을 알 수 있는 4가지 기능
- 공지사항 조회 : 키워드를 통해 공지사항을 조회할 수 있는 키워드 검색 기능과 회사 전체의 공지사항을 볼 수 있는 <br> 전체 공지사항 기능, 회사의 부서별 공지사항을 볼 수 있는 부서 공지사항 기능

## ⚙️ 쿠버네티스 동작 원리
<p align="center">
<img src = 'https://github.com/dh1010a/advisorChatbot/assets/51228946/c0a412a4-c475-47a9-ae57-bd23d63c55f3' width="50%" height="50%">
 </p>
노드 포트를 3개 구성하여 클러스터링 기법으로 연결하여 한 서버가 다운되더라도 즉시 재시작할 수 있으며 트래픽 부하를 나눠 받을 수 있다.\\

## 📃 서비스 구상도
<p align="center">
<img src = 'https://github.com/dh1010a/advisorChatbot/assets/51228946/2aa52360-8caa-435c-b2e6-7b489ea5ea59' width="60%" height="60%">
 </p>
