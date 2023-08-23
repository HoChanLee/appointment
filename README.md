## [매장, 점포 예약 서비스]
* __매장이나 점포를 이용하기 전에 미리 예약을 하여 편하게 이용할 수 있는 서비스 개발__
    
------------------
### [기술스택]
* Spring Boot, Java, JPA, JWT, MySQL
------------------

### [ERD]
<img width="854" alt="image" src="https://github.com/HoChanLee/appointment/assets/106670454/f4463136-2cde-4ecd-bd04-4d83c4e2a619">


### [API 리스트]
* __POST - store/register__
    * 매장 등록을 위한 API (JWT 필요)


* __GET - store__
    * 매장 전체 보기 API
    * Pageable을 이용하여 파라미터로 size 와 page를 키벨류로 설정


* __GET - store/{storeName}__
    * 매장 자세히 보기 API
    * 매장이름을 파라미터로 확인


* __POST - appointment/register__
    * 매장 예약 API
    * 사용자id와 매장id를 body를 통해 확인 (JWT 필요)


* __PATCH - appointment/visit/{id}__
    * 예약된 매장 방문 API
    * 예약id를 파라미터로 확인하여 방문


* __POST - review/write__
  * 예약 방문한 매장 리뷰 API
  * 예약id를 body를 통해 확인


* __POST - auth/signup__
    * 회원가입 API
    * 중복 ID 는 허용하지 않음
    * 패스워드는 암호화된 형태로 저장


* __POST - auth/signin__
    * 로그인 API
    * 회원가입이 되어있고, 아이디/패스워드 정보가 옳은 경우 JWT 발급
