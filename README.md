# :paperclip: 의약품/ 의료기기 쇼핑몰
> 첫번째 토이 프로젝트입니다.

<img src="https://user-images.githubusercontent.com/95284639/204142059-9fa7529f-0811-4989-bcab-185277ef7ea8.png">

## 목차
- [들어가며](#들어가며)
  - [프로젝트 소개](#1-프로젝트-소개)    
  - [프로젝트 기능](#2-프로젝트-기능)    
  - [사용 기술](#3-사용-기술)   
     - [백엔드](#3-1-백엔드)
     - [프론트엔드](#3-2-프론트엔드)
  - [실행 화면](#4-실행-화면)   

- [구조 및 설계](#구조-및-설계)
  - [패키지 구조](#1-패키지-구조)
  - [DB 설계](#2-db-설계)

## 들어가며
### 1. 프로젝트 소개

의약품/ 의료기기를 판매하는 쇼핑몰 웹 어플리케이션입니다. <br/>
책과 강의를 통해 학습한 기술들을 사용해서 프로젝트를 직접 만들어보고 싶은 마음에 시작하게 되었습니다.


### 2. 프로젝트 기능

프로젝트의 주요 기능은 다음과 같습니다.
- **메인 화면 -** 판매 상품 확인, 판매 상품 페이징, 상품 이름으로 상품 검색, 카테고리별로 상품 검색
- **회원 -** 회원가입, 로그인, 아이디 찾기, 비밀번호 찾기, 회원정보 변경
- **상품 -** 상품 상세 화면에서 상품의 용도 확인, 장바구니에 담기, 주문 
- **장바구니 -** 장바구니에 담긴 상품 확인, 장바구니 상품 삭제, 수량 조정, 원하는 상품 선택해 주문
- **주문 -** 주문 상품 수령자 정보 작성, 최종 주문 진행, 주문 내역 리스트에서 주문 취소, 
             주문 상품 이름과 주문 상태로 주문 상품 검색, 주문 상세 정보 확인, 후기 작성 여부 확인
- **후기 -** 주문 완료된 상품에 한해서 후기 작성, 작성된 후기는 상품 상세 화면과 후기 내역 리스트에서 확인, 후기 내역 리스트에서 후기 삭제

### 3. 사용 기술

#### 3-1 백엔드
 Java, Spring Framework, JPA, QueryDsl, MySql
##### 주요 프레임워크 / 라이브러리
- Java 11
- SpringBoot 2.6.4
- JPA(Spring Data JPA)
- QueryDsl
- JUnit

##### Build Tool
- Gradle 7.4.1

##### DataBase
- MySQL 8.0.19
- H2

#### 3-2 프론트엔드
- Html/Css
- JavaScript
- Thymeleaf
- Bootstrap 5.1

#### 3-3 데브옵스
- EC2
- S3
- RDS(MariaDB)

### 4. 실행 화면
  <details>
    <summary>메인 화면</summary>        
  <br/>
  
  **1. 로그인 전 메인 화면**   
  <img src="https://user-images.githubusercontent.com/95284639/204142059-9fa7529f-0811-4989-bcab-185277ef7ea8.png">
  상단 우측에 로그인과 회원가입 버튼이 보여짐.
     
  **2. 로그인 후 메인 화면**   
  ![image](https://user-images.githubusercontent.com/95284639/207355230-7ebd56cb-0d49-444e-9500-be2e6394a5a3.png)
  상단 우측에 로그아웃 버튼이 보여짐.
     
  **3. 페이징 처리**   
  ![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/95284639/207361329-4fa7e46b-2fe7-45e9-8786-5aba344e0a80.gif)
  
  한 화면에 8개의 판매 상품이 보여지도록 페이징 처리. 총 22개의 상품이 존재해 3개의 페이지로 구성. <br/>
  메인 화면에 보여지는 상품 가격은 1,000단위로 쉼표가 적히도록 포맷팅.
     
   **4. 상품 검색**   
   ![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/95284639/207361551-2cd4d20e-caed-4a2b-bb8d-d40baa780aac.gif)
  
  상단에 있는 검색바를 이용해서 원하는 상품 검색.
  
  **5. 카테고리에 속한 상품 보기**   
  ![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/95284639/207360902-214e5b3d-77ae-48a6-934d-a3e5b2020cc6.gif)
  
  카테고리 버튼을 누르면 해당 카테고리에 속한 상품들을 볼 수 있음.
     
  </details>
  <br/>   

  <details>
    <summary>회원 가입과 회원 종류에 따른 상품 구매 </summary>   
  </br>

  **1. 회원가입 화면** 
  
  ![ezgif com-gif-maker (8)](https://user-images.githubusercontent.com/95284639/207362726-ca8c1042-044a-416b-a4e3-ddf2b65448a3.gif)

  메인 화면 상단 우측에 회원가입 버튼을 클릭하면 회원가입 화면으로 이동.
     
  **2. 회원가입을 진행할 때 잘못된 값을 입력하는 경우**   
  ![ezgif com-gif-maker (9)](https://user-images.githubusercontent.com/95284639/207363696-cdd5003b-5158-44f0-b218-88182de39755.gif)
  
  회원의 정보를 입력할 때 값이 없거나 잘못된 값을 입력하고 회원가입을 진행하려고 하면 에러가 발생해 <br/> 
  사용자에게 다시 회원가입 화면으로 리다이렉트되어 사용자에게 어떤 정보를 잘못 입력했는지 인지시켜준다. <br/> 
  이 때 병원/ 사업체명, 사업자등록번호, 의사면허번호는 필수 입력 값이 아님. 이 3개를 입력하지 않고 회원 가입하면 일반인 회원으로 회원가입. <br/>
  검증 기능은 spring 의 valid 를 이용해 구현. 또한 중복 아이디 체크를 해 다른 회원이 사용하고 있는 아이디를 입력해도 에러가 발생. <br/> 
  회원 가입 화면 외에도 값을 입력해야 하는 화면에는 valid 를 모두 적용함.
  
  **3-1. 일반인 회원으로 회원가입 진행**   
 ![ezgif com-gif-maker (10)](https://user-images.githubusercontent.com/95284639/207365287-96546d04-cb91-47e3-bcf1-0fdf55abfcbd.gif)
 
  주소 입력은 다음 주소 찾기 API 를 이용했음. <br/> 
  현재 병원/ 사업체명, 사업자등록번호, 의사면허번호 입력하지 않고 일반인 회원으로 회원가입 진행.
     
  **3-2. 일반인 회원이 상품을 장바구니에 담기, 주문 진행**   
  ![ezgif com-gif-maker (11)](https://user-images.githubusercontent.com/95284639/207367521-f304cd15-c87a-4de8-b35e-4d3a8ed1df40.gif)
  
  회원가입한 일반인 회원으로 로그인한 후, 상품을 장바구니에 담고 주문까지 진행. <br/>
  일반인 회원은 건강 식품에 속한 상품은 구매가 가능.
           
  **3-3. 일반인 회원이 상품을 장바구니에 담기, 주문 실패**
  ![ezgif com-gif-maker (12)](https://user-images.githubusercontent.com/95284639/207371537-d37f8716-a62e-477a-b84e-0a03559d7ff6.gif)

  일반인 회원은 의약품이나 의료기기를 구매할 수 없음. 구매할 수 없는 상품을 장바구니에 담거나 주믄하려고 할 때 경고창이 발생. 
  
  **4-1. 의사/의료사업자 회원으로 회원가입 진행**   
 ![ezgif com-gif-maker (13)](https://user-images.githubusercontent.com/95284639/207373169-905cf8f8-9f2e-48f7-9273-f93ee95e3d31.gif)
  
   현재 병원/ 사업체명, 사업자등록번호, 의사면허번호까지 입력해 의사/의료사업자 회원으로 회원가입 진행.
     
  **4-2. 의사/의료사업자 회원이 상품을 장바구니에 담기, 주문 진행**   
  ![ezgif com-gif-maker (14)](https://user-images.githubusercontent.com/95284639/207373590-f7799aab-7bc8-4221-b669-5daba3a3bfd5.gif)
  
  회원가입한 의사/의료사업자 회원으로 로그인한 후, 상품을 장바구니에 담고 주문까지 진행. <br/>
  의사/의료사업자 회원은 상품 종류와 상관없이 모든 상품 구매 가능. 
  
  </details>
  <br/>  
  
 <details>
    <summary>아이디 찾기, 비밀번호 찾기</summary>   
  <br/>
  
  **1. 잘못된 아이디와 비밀번호를 입력해 로그인 실패**   
  ![ezgif com-gif-maker (15)](https://user-images.githubusercontent.com/95284639/207376919-4042b78d-4a82-48ec-807f-01bb1492bd7b.gif)

  로그인을 할 때 아이디와 비밀번호중에 하나가 틀리면 로그인에 실패해 다시 로그인 화면으로 리다이렉트한다.
 
  **2. 아이디 찾기**   
  ![ezgif com-gif-maker (15)](https://user-images.githubusercontent.com/95284639/207376919-4042b78d-4a82-48ec-807f-01bb1492bd7b.gif)
  
  회원의 이름과 전화번호를 입력하면 회원의 아이디를 보여줌. 
  
  **3. 비밀번호 찾기**   

  ![ezgif com-gif-maker](https://user-images.githubusercontent.com/95284639/208607904-fbdf9d0e-29bd-42ff-a0fc-d51a69e89a99.gif)
  
  회원의 아이디를 입력하면 회원의 비밀번호를 회원가입할 때 입력한 이메일 주소로 메일로 송신함. <br/>
  구글의 SMTP 서버를 이용해 메일 송신 기능을 구현.
  

  </details>
  <br/>  
  
  <details>
    <summary>장바구니에 상품 담기와 상품 주문</summary>   
  <br/>
  
  **1. 장바구니에 상품 담기**   
  ![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/95284639/207553948-9b41357f-596e-44d9-8db0-dbd5f0740ec2.gif)

  의사/ 의료사업자 회원으로 로그인해서 원하는 상품을 장바구니에 담음. <br/>
  메인화면에서 장바구니 담기 버튼을 누르면 1개, 상품 상세 화면에서 장바구니 버튼을 누르면 설정한 개수만큼 징바구니에 담는다.
 
  **2. 장바구니 화면에서 상품 확인, 상품 검색, 상품 삭제**   
![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/95284639/207553731-ac151e5f-4fae-404e-91d0-8e3f6729ea6c.gif)
  
  메인화면 우측 상단에서 장바구니 버튼을 눌러 장바구니 화면으로 이동. <br/>
  이전에 장바구니에 담았던 상품을 확인하고 원하는 상품을 검색바를 통해 검색 가능. <br/>
  삭제하기 버튼을 클릭해 장바구니에서 상품 삭제 가능.
  
  **3. 장바구니 설정에 따른 장바구니 정보 변경**   
![ezgif com-gif-maker (8)](https://user-images.githubusercontent.com/95284639/207555491-46546891-dbe9-4a4a-855b-223adf1f50e8.gif)

  장바구니에 담긴 상품의 수량을 버튼을 이용해서 조정할 수 있고, 주문할 상품을 체크박스를 통해 선택할 수 있음. <br/>
  이 때 각 상품의 개수와 체크박스 체크여부에 따라 하단의 상품 정보가 변경됨. <br/> 
  
  **4. 최종 주문**   
  ![ezgif com-gif-maker (9)](https://user-images.githubusercontent.com/95284639/207559508-9f1157cf-e9a8-401c-adfd-19da627de44e.gif)
  
  장바구니 화면에서 주문하기 버튼을 누르면 주문 화면으로 이동됨.  <br/>
  이 때 장바구니 화면에서 선택했던 상품들은 장바구니에서 삭제된다. <br/>
  상품 상세 화면에서도 주문하기 버튼을 눌러서 주문 화면으로 이동 가능. <br/>
  주문 화면에서는 장바구니 화면에서 체크했던 상품의 정보가 출력. <br/> 
  상품 수령자는 꼭 회원 정보와 일치할 필요가 없으므로 수정이 가능하고 배송 메시지도 선택할 수 있음. <br/>
  주문 정보를 확인하고 하단에 주문하기 버튼을 누르면 최종 주문 완료.
  
  </details>
  <br/>  
  
  <details>
    <summary>마이페이지</summary>   
  <br/>
  
  **1. 마이페이지에서 회원의 정보 변경**   
![ezgif com-gif-maker (11)](https://user-images.githubusercontent.com/95284639/207568415-e8c36a8b-7eb4-4c60-9aa3-611df80dbee7.gif)

  마이페이지에서 회원의 상세 정보를 확인할 수 있고 아이디를 제외한 나머지 정보들은 변경이 가능.<br/>
  비밀번호는 중요하므로 따로 비밀번호 변경 화면을 개발했음.<br/>
  정보를 변경 후 다시 로그인하고 마이페이지에 들어가보면 변경된 정보를 확인 할 수 있다.

  </details>
  <br/>  
  
  <details>
    <summary>주문 내역 리스트</summary>   
  <br/>  
  
  **1. 주문 내역 리스트 확인**   
  ![ezgif com-gif-maker (12)](https://user-images.githubusercontent.com/95284639/207570782-1c60b34b-6347-4530-a15b-856a50ff4124.gif)

  마이페이지에서 우측 상단에 있는 주문 내역 조회 버튼을 누르면 주문 내역 리스트를 확인할 수 있다. <br/>
  주문 상품은 주문 날짜 최신순으로 정렬되어 있음. 주문 날짜는 스프링 포맷터를 이용해 포맷팅 구현.

  **2. 주문 취소**     
  ![ezgif com-gif-maker (13)](https://user-images.githubusercontent.com/95284639/207571701-ebc3d5b3-8ede-41f7-8f5a-95960a73229b.gif)
  
  주문 취소 버튼을 누르면 주문 취소하려는 상품이 맞는지 사용자에게 다시 한 번 보여준다. <br/>
  사용자가 하단에 주문 취소 버튼을 누르면 최종적으로 주문 취소됨. <br/>
  주문이 취소되면 상품의 주문 상태가 주문 완료에서 주문 취소로 변경됨.
  
  **3. 주문 상품 검색**
  <br/>
  ![ezgif com-gif-maker (14)](https://user-images.githubusercontent.com/95284639/207575882-85a56b4c-c55d-431a-938b-361ea307d71d.gif)
  
  주문 상품은 상품 이름과 상품 상태를 이용해 검색 할 수 있다.
  
  **4. 주문 상세 화면**
  <br/>
  
  ![ezgif com-gif-maker (16)](https://user-images.githubusercontent.com/95284639/207576817-1de90e41-b7e3-4f41-9d14-b94dad9024af.gif)

  주문 상품들은 주문 번호를 이용해 어떤 주문에 속해있는지 확인할 수 있다. <br/>
  주문 번호를 누르면 주문 상세 화면으로 이동하고 해당 주문에 속해있는 주문 상품들과 상품 수령자 정보를 출력한다.
  
  </details>
  <br/>  

<details>
    <summary>후기 작성과 후기 내역 리스트</summary>   
<br/> 

  **1. 주문한 상품 후기 작성**   
![ezgif com-gif-maker (17)](https://user-images.githubusercontent.com/95284639/207578589-5a0333ad-1799-482b-a118-777e01eeac41.gif)

  주문 상품중 주문 완료인 상품들만 한해서 상품 후기를 작성할 수 있다. 후기는 제목, 내용을 작성하고 후기 사진을 업로드 해야한다. <br/>
  업로드한 후기 사진은 AWS 의 S3 스토리지에 저장된다.

  **2. 상품 상세 화면에서 작성된 후기 확인** 
![ezgif com-gif-maker (18)](https://user-images.githubusercontent.com/95284639/207579654-947ce6fc-ea8b-4f33-b56c-f3424166efb9.gif)
  
  후기가 작성된 상품의 상품 상세 화면으로 가면 하단에 작성된 후기를 확인할 수 있다. <br/>
  후기 리스트는 후기 작성 날짜 최신순으로 정렬하고 후기 작성 날짜를 스프링 포맷터로 포맷팅 구현. <br/>
  후기 사진을 클릭하면 다운로드를 할 수 있다.
  
   **3. 주문 내역 리스트에서 후기 작성 여부 확인**
![ezgif com-gif-maker (19)](https://user-images.githubusercontent.com/95284639/207580466-e031023d-6354-4e38-9329-c38ae7926fdc.gif)
  
  주문 내역 리스트 화면으로 가면 후기를 작성한 주문 상품의 후기 작성 버튼이 없어지고 후기 작성 완료 문구가 출력된 것을 확인할 수 있다.
  
  **4. 후기 내역 리스트에서 작성된 후기 확인, 삭제**
![ezgif com-gif-maker (21)](https://user-images.githubusercontent.com/95284639/207581642-801c8aa9-58c8-42d8-a6fe-a3b8bfaa17fa.gif)
  
  마이 페이지에서 후기 내역 리스트 버튼을 눌러서 후기 내역 리스트 화면으로 가면 로그인한 회원이 작성한 후기 리스트를 확인할 수 있다. <br/>
  후기 삭제 버튼을 클릭해 후기 삭제 가능.
  
  </details>
  <br/>  

## 구조 및 설계   
   
### 1. 패키지 구조
   
<details>
  
<summary>패키지 구조 보기</summary>   

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂capstonedesign
 ┃ ┃ ┃ ┗ 📂medicalproduct
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 mvc
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜HomeController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂restapi
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemAPIController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberAPIController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderApiController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewAPIController.java
 ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Cart.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Item.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Order.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderItem.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Review.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartSearch.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemSearch.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderSearch.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Uploadfile.java
 ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┗ 📂api
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartAPIDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberAPIDto.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂mvc
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂order
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderItemDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂ordered
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderedDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderedItemDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IdAndResult.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜IdFindForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemDetailDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberDetailForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRegisterForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ModifyResult.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordFindForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜PasswordUpdateDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜RecipientInfo.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderStatus.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewRegisterForm.java
 ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┣ 📜DuplicateIdExceptiony.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜InvalidRegisteredValueException.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜InvalidUpdatedValueException.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜NotExistMemberException.java
 ┃ ┃ ┃ ┃ ┣ 📂exhandler
 ┃ ┃ ┃ ┃ ┃ ┗ 📂advice
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExControllerAdvice.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ErrorResult.java
 ┃ ┃ ┃ ┃ ┗ 📂login
 ┃ ┃ ┃ ┃ ┃ ┣ 📂argumentresolver
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Login.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LoginMemberArgumentResolver.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginCheckInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜LogInterceptor.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜 SessionConst.java
 ┃ ┃ ┃ ┃ ┗ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemQueryRepository.java
 ┃ ┃ ┃ ┃ ┣ 📜ItemRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderItemRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewRepository.java
 ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AwsS3Service.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberService.java  
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderService.java  
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewService.java  
 ┃ ┃ ┃ ┃ ┣ 📜AwsS3Config
 ┃ ┃ ┃ ┃ ┣ 📜MedicalproductApplication
 ┃ ┃ ┃ ┃ ┗ 📜WebConfig
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┗ 📜bootstrap.css
 ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┣ 📜app.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜home.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜itemDetail.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜loginHome.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜loginItemDetails.js
 ┃ ┃ ┃ ┃ ┃ ┗ 📜bootstrap.js
 ┃ ┃ ┃ ┃ ┗ 📜app.js
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂cart
 ┃ ┃ ┃ ┃ ┗ 📜cartItems.html
 ┃ ┃ ┃ ┣ 📂fragements
 ┃ ┃ ┃ ┃ ┣ 📜bodyHeader.html
 ┃ ┃ ┃ ┃ ┣ 📜footer.html
 ┃ ┃ ┃ ┃ ┣ 📜header.html
 ┃ ┃ ┃ ┃ ┗ 📜memberHeader.html
 ┃ ┃ ┃ ┣ 📂item
 ┃ ┃ ┃ ┃ ┣ 📜itemDetail.html
 ┃ ┃ ┃ ┃ ┗ 📜loginItemDetail.html
 ┃ ┃ ┃ ┣ 📂login
 ┃ ┃ ┃ ┃ ┗ 📜loginForm.html
 ┃ ┃ ┃ ┣ 📂members
 ┃ ┃ ┃ ┃ ┣ 📜addMemberForm.html
 ┃ ┃ ┃ ┃ ┣ 📜idFind.html
 ┃ ┃ ┃ ┃ ┣ 📜idFindResult.html
 ┃ ┃ ┃ ┃ ┣ 📜myPage.html
 ┃ ┃ ┃ ┃ ┣ 📜passwordFind.html
 ┃ ┃ ┃ ┃ ┣ 📜passwordFindResult.html
 ┃ ┃ ┃ ┃ ┗ 📜passwordUpdate.html
 ┃ ┃ ┃ ┣ 📂orders
 ┃ ┃ ┃ ┃ ┣ 📜order.html
 ┃ ┃ ┃ ┃ ┣ 📜orderCancel.html
 ┃ ┃ ┃ ┃ ┣ 📜loginForm.html
 ┃ ┃ ┃ ┃ ┗ 📜loginForm.html
 ┃ ┃ ┃ ┗ 📂reviews
 ┃ ┃ ┃ ┃ ┣ 📜reviewList.html
 ┃ ┃ ┃ ┃ ┗ 📜reviewRegister.html
 ┃ ┃ ┃ ┣ 📜home.html
 ┃ ┃ ┃ ┗ 📜loginHome.html
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂capstonedesign
 ┃ ┃ ┃ ┗ 📂medicalproduct
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┗ 📜HomeControllerTest.java
 ┃ ┃ ┃ ┃ ┣ 📂restAPIController
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberAPIControllerTest.java
 ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberService.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderService.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewService.java
 ┃ ┃ ┃ ┃ ┗ 📜BoardApplicationTests.java
 ```
  
 </details>   
 <br/>     
    
 ### 2. DB 설계
 # :paperclip: ERD

![image](https://user-images.githubusercontent.com/95284639/208683868-b827e49c-7613-485c-8343-e2255cde1530.png)

<img src="https://user-images.githubusercontent.com/95284639/207638228-e170cce9-f0ec-4b1a-a42b-039e1f7b3067.png"  width="400" height="400"/>
<img src="https://user-images.githubusercontent.com/95284639/207608463-c074dfcd-f60f-46b2-9b12-205ba3fe259d.png"  width="400" height="400"/>
<img src="https://user-images.githubusercontent.com/95284639/207608582-841b17ba-b97f-4118-a07e-d125032e9645.png"  width="400" height="400"/>
<img src="https://user-images.githubusercontent.com/95284639/207608649-fa7c119c-b271-43ad-82a2-a888ed833660.png"  width="400" height="400"/>
<img src="https://user-images.githubusercontent.com/95284639/207608735-37e28cd9-8f0f-4a3d-a69c-1203b48ca4e5.png"  width="400" height="400"/>
<img src="https://user-images.githubusercontent.com/95284639/207608876-d745547e-100f-4c97-8e23-07d009c36d39.png"  width="400" height="400"/>
<br/>
