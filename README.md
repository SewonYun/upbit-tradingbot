# Upbit trading bot sample

### Warning
```
절대적으로 스프링 연습을 위한 개인 프로젝트 입니다
이 프로젝트는 수익을 목적으로 만든게 아님을 명시합니다
```

### Project purpose description
* 스프링 배치를 사용한 시분할 거래 요청 및 코인 가격 탐지, 거래 기능
* 계속하여 탐색하는 거래 데이터를 처리 할 수 있게 메모리 최적화
* 거래 api hangup 대응 처리
* test code 작성 연습
* Actor model 처럼 현재 상태 메모리에 두고 mode를 설정해서(코인보유, 미보유, 거래 대기중 등 개발시 상세 정의 )

### Reference api
* [upbit api](https://docs.upbit.com/docs): trading platform

### Feature explanation

* 코인 타입 미설정 시 기본 설정 및 알림
  * 슬랙 봇 에게 알림
  * 기본값 (const) 값 으로 코인 종류 세팅
* 매수 요청 api
* 매도 요청 api
* 대기중 거래를 특정 시간 이상시 거래 취소
  * 거래 취소 요청 api
  * 유휴 모드로 전환
* 익절: 특정 거래 조건 로직에 맞을 때 매매 요청 
* 손절: 특정 거래 조건 로직에 맞을 때 매매 요청
* 주문 예약 (대기중 인 거래) 처리
* 적절한 가격 탐색
  * 매물대 를 참고해서 머리끝이 아닌 어깨 가격 계산
* 코인의 거래량 및 가격 내려 받기 배치
  * rsi14 지표 계산 로직
  * perb 지표 계산 배치
* 원격 조종
  * slack bot 으로 앱 웹 개발 대체
  * slack channel message polling
* 실시간 업비트 지수 탐색: 지금 bull market 인지 bear market 인지 참고 데이터
* 내 지갑과 계정에 코인과 돈이 얼만큼 있는지 확인
