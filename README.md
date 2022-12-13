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
  - [API 설계](#3-api-설계)

- [개발 내용](#개발-내용)

- [마치며](#마치며)
  - [프로젝트 보완사항](#1-프로젝트-보완사항)
  - [후기](#2-후기)

## 들어가며
### 1. 프로젝트 소개

의약품/ 의료기기를 판매하는 쇼핑몰 웹 어플리케이션입니다.
기존에 있는 의약품/ 의료기기 쇼핑몰 웹사이트에서는 의사나 의료사업자가 아닌 일반인은 
판매하는 상품들을 구매 뿐 만 아니라 볼 수 조차 없었습니다. 현재 프로젝트가 실제로 존재하는 쇼핑몰은 아니지만 그런 부분을 개선하여 일반인 회원도 어떤 상품이 존재하는지 그 상품들은 무슨 용도로 사용되는지 확인 할 수 있게 하고 일부 상품인 밴드나 파스, 건강 식품 정도는 구매가 가능하도록 개발하고 싶었습니다

### 2. 프로젝트 기능

프로젝트의 주요 기능은 다음과 같습니다.
- **게시판 -** CRUD 기능, 조회수, 페이징 및 검색 처리
- **사용자 -** Security 회원가입 및 로그인, OAuth 2.0 구글, 네이버 로그인, 회원정보 수정, 회원가입시 유효성 검사 및 중복 검사
- **댓글 -** CRUD 기능

### 3. 사용 기술

#### 3-1 백엔드
 Java, Spring Framework, JPA, QueryDsl, MySql, Thymeleaf, JavaScript, Ajax
##### 주요 프레임워크 / 라이브러리
- Java 11
- SpringBoot 2.6.4
- JPA(Spring Data JPA)
- QueryDsl

##### Build Tool
- Gradle 7.4.1

##### DataBase
- MySQL 8.0.19

#### 3-2 프론트엔드
- Html/Css
- JavaScript
- Thymeleaf
- Bootstrap 5.1

#### 3-3 데브옵스
- EC2
- S3

### 4. 실행 화면
  <details>
    <summary>메인 화면</summary>   
       
  
  **1. 로그인 전 메인 화면**   
  <img src="https://user-images.githubusercontent.com/95284639/204142059-9fa7529f-0811-4989-bcab-185277ef7ea8.png">
  상단 우측에 로그인과 회원가입 버튼이 보여짐.
     
  **2. 로그인 후 메인 화면**   
  ![image](https://user-images.githubusercontent.com/95284639/207355230-7ebd56cb-0d49-444e-9500-be2e6394a5a3.png)
  상단 우측에 로그아웃 버튼이 보여짐.
     
  **3. 페이징 처리**   
  ![ezgif com-gif-maker (6)](https://user-images.githubusercontent.com/95284639/207361329-4fa7e46b-2fe7-45e9-8786-5aba344e0a80.gif)
  
  한 화면에 8개의 판매 상품이 보여지도록 페이징 처리.
     
   **4. 상품 검색**   
   ![ezgif com-gif-maker (7)](https://user-images.githubusercontent.com/95284639/207361551-2cd4d20e-caed-4a2b-bb8d-d40baa780aac.gif)
  
  상단에 있는 검색바를 이용해서 원하는 상품 검색.
  
  **5. 카테고리에 속한 상품 보기**   
  ![ezgif com-gif-maker (5)](https://user-images.githubusercontent.com/95284639/207360902-214e5b3d-77ae-48a6-934d-a3e5b2020cc6.gif)
  
  카테고리 버튼을 누르면 해당 카테고리에 속한 상품들을 볼 수 있음.
     
  </details>
  <br/>   

<details>
    <summary>회원 가입과 회원 종류에 따른 상품 구매</summary>   
     
  **1. 회원가입 화면**   
  ![ezgif com-gif-maker (8)](https://user-images.githubusercontent.com/95284639/207362726-ca8c1042-044a-416b-a4e3-ddf2b65448a3.gif)

  메인 화면 상단 우측에 회원가입 버튼을 클릭하면 회원가입 화면.
     
  **2. 회원가입을 진행할 때 잘못된 값을 입력하는 경우**   
  ![ezgif com-gif-maker (9)](https://user-images.githubusercontent.com/95284639/207363696-cdd5003b-5158-44f0-b218-88182de39755.gif)
  
  회원의 정보를 입력할 때 값이 없거나 잘못된 값을 입력하고 회원가입을 진행하려고 하면 에러가 발생해 사용자에게 다시 회원가입 화면을 보여준다. 이 때 병원/ 사업체명, 사업자등록번호, 의사면허번호는 필수 입력 값이 아님. 이 3개를 입력하지 않고 회원 가입하면 일반인 회원으로 회원가입. spring 의 valid 기능을 이용해 구현. 또한 중복 아이디 체크를 해 다른 회원이 사용하고 있는 아이디를 입력해도 에러가 발생. 회원 가입 화면 외에도 값을 입력해야 하는 화면에는 valid 를 모두 적용함.
  
  **3-1. 일반인 회원으로 회원가입 진행**   
 ![ezgif com-gif-maker (10)](https://user-images.githubusercontent.com/95284639/207365287-96546d04-cb91-47e3-bcf1-0fdf55abfcbd.gif)
  주소 입력은 다음 주소 찾기 API 를 이용했음. 현재 병원/ 사업체명, 사업자등록번호, 의사면허번호 입력하지 않고 일반인 회원으로 회원가입 진행.
     
  **3-2. 일반인 회원이 상품을 장바구니에 담기, 주문 진행**   
  ![ezgif com-gif-maker (11)](https://user-images.githubusercontent.com/95284639/207367521-f304cd15-c87a-4de8-b35e-4d3a8ed1df40.gif)
  회원가입한 일반인 회원으로 로그인한 후, 상품을 장바구니에 담고 주문까지 진행.
  일반인 회원은 건강 식품에 속한 상품은 구매가 가능.
           
  **3-3. 일반인 회원이 상품을 장바구니에 담기, 주문 실패**
  ![ezgif com-gif-maker (12)](https://user-images.githubusercontent.com/95284639/207371537-d37f8716-a62e-477a-b84e-0a03559d7ff6.gif)

  일반인 회원은 의약품이나 의료기기를 구매할 수 없음. 구매할 수 없는 상품을 장바구니에 담가니 주믄히랴고 할 때 경고창이 발생. 
  
  **4-1. 의사/의료사업자 회원으로 회원가입 진행**   
 ![ezgif com-gif-maker (13)](https://user-images.githubusercontent.com/95284639/207373169-905cf8f8-9f2e-48f7-9273-f93ee95e3d31.gif)
  
   현재 병원/ 사업체명, 사업자등록번호, 의사면허번호까지 입력해 의사/의료사업자 회원으로 회원가입 진행.
     
  **4-2. 의사/의료사업자 회원이 상품을 장바구니에 담기, 주문 진행**   
  ![ezgif com-gif-maker (14)](https://user-images.githubusercontent.com/95284639/207373590-f7799aab-7bc8-4221-b669-5daba3a3bfd5.gif)
  
  회원가입한 의사/의료사업자 회원으로 로그인한 후, 상품을 장바구니에 담고 주문까지 진행.
  의사/의료사업자 회원은 상품 종류와 상관없이 모든 상품 구매 가능. 
  
  </details>
  <br/>  
  
 <details>
    <summary>아이디 찾기, 비밀번호 찾기</summary>   
     
  **1. 잘못된 아이디와 비밀번호를 입력해 로그인 실패 **   
  ![ezgif com-gif-maker (15)](https://user-images.githubusercontent.com/95284639/207376919-4042b78d-4a82-48ec-807f-01bb1492bd7b.gif)

  로그인을 할 때 아이디와 비밀번호중에 하나가 틀리면 로그인에 실패해 다시 로그인 화면으로 리다이렉트함.
 
  
  </details>
  <br/>  

회원가입을 할 때 일반인과 의사/의료사업자로 할 수 있고 일반인은 의약품과 의료기기는 구매가 불가하지만 의사/의료사업자는 모든 상품을 구매 할 수 있습니다.

로그인, 회원가입, 마이페이지, 상품상세, 장바구니, 주문, 주문 내역 리스트, 후기 작성, 후기 내역 리스트 등 일반 쇼핑몰에 있는 기능들을 대부분 구현했습니다.

이 외에도 RestAPI 를 개발하고 JUnit 을 이용해 단위 테스트 또한 수행했습니다.

AWS 의 EC2 를 이용해서 배포를 하고 업로드한 후기 이미지 파일을 보관하기 위해 스토리지인 S3 를 사용했었습니다.


사용기술 Java, Spring Framework, JPA, QueryDsl, MySql, Thymeleaf, JavaScript, Ajax

<br/>
<br/>


<br/>
<br/>
<img src="https://user-images.githubusercontent.com/95284639/204142392-9b018e7f-d038-4e68-b41c-f52681986677.png">

<br/>
<br/>
<img src="https://user-images.githubusercontent.com/95284639/204142671-1ad91b7d-2d0a-4560-a9de-ffb16cb84108.png">

<br/>
<br/>
<img src="https://user-images.githubusercontent.com/95284639/204142732-228b9371-1821-424f-8fef-b6d9af9f1649.png">
