# 웹 어플리케이션

웹 서버는 정적 리소스 실행하는데 더 특화

WAS는 애플리케이션 코드를 실행하는데 더 특화

물론 was도 정적 리소스 실행가능

## 서블릿

<img width="961" alt="Screenshot 2024-08-25 at 16 52 34" src="https://github.com/user-attachments/assets/ccec52b9-5c88-49ba-9a5f-b431d4f96c36">

톰캣처럼 서블릿을 지원하는 was를 서블릿 컨테이너라고 함

서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리

서블릿 객체는 싱글톤으로 관리 : **공유 변수 사용 주의**

JSP도 서블릿으로 변환되어서 사용

**동시 요청을 위한 멀티 쓰레드 처리 지원**

## 동시 요청 멀티쓰레드

### 쓰레드

애플리케이션 코드를 하나하나 순차적으로 실행하는 것은 쓰레드

자바 메인 메서드를 처음 실행하면 main이라는 이름의 쓰레드가 실행

쓰레드가 없다면 자바 애플리케이션 실행이 불가능

쓰레디는 한번에 하나의 코드 라인을 수

동시 처리가 필요하다면 쓰레드 추가 생성

#### 요청마다 쓰레드 생성

* 장점

동시 요청을 처리할 수 있다.

리소스가 허용할 때까지 처리가능

하나의 쓰레드가 지연되어도 나머지가 동작 가능.

* 단점

쓰레드 생성 비용이 비싸다. 응답 속도가 늦어짐

컨텍스트 스위칭 비용이 발생한다.

생성에 제한이 없다. 고객 요청이 많으면 CPU, 메모리 임계점을 넘어서 서버가 죽을 수 있다.

### 쓰레드 풀

애플리케이션이 생성되면 쓰레드를 여러 개 미리 생성 해놓음.

요청이 들어올때 생성해둔 쓰레드를 사용. 다 쓰면 반납

미리 생성되어 있기 때문에 쓰레드 생성, 종료 비요이 절약. -> 응답 시간 빨라짐

생성 가능한 쓰레드 최대치가 있기 때문에 많은 요청이 들어와도 기존 요청은 안전하게 처리 가능.

* 실무 팁

WAS의 주요 튜닝 포인트는 최대 쓰레드(max thread) 수이다.

너무 낮게 설정하면 서버는 여유로우나 클라언트의 응답이 늦어진다.

너무 높게 설정하면 서버는 다운...

클라우드면 서버를 일단 늘리고 튜닝 (근데 서버 cpu 사용률을 잘 보자 최소 50% 이상은 써야...)

아니면 튜닝...

* 그러면 쓰레드 풀 적정 숫자는?

로직의 복잡도, cpu, 메모리, IO 리소스 상황에 따라 다름

최대한 운영과 같은 환경을 만들고 성능 테스트 꼭 해보자. (아파치 ab, 제이미터, nGrinder 같은 tool 사용)

## CSR

<img width="890" alt="Screenshot 2024-08-25 at 17 24 09" src="https://github.com/user-attachments/assets/0d799a42-9971-4a53-a211-e57ec1727759">

클라이언트 사이드 랜더링

HTML 결과를 자바스크립트를 사용해 웹 브라우저에서 동적으로 생성해서 적용

주로 동적인 화면에 사용, 웹 환경을 마치 앱처럼 필요한 부분별로 변경할 수 있음

구글지도, Gmail이 이렇게 작동

React, Vue.js

## SSR

서버 사이드 렌더링

서버에서 최종 HTML을 생성해서 클라이언트에 전달

JSP, 타임리프

# 자바 백앤드 웹 기술 역사

스트럿츠, 웹워크, 과거 spring mvc등이 있었으나 애노테이션 기반의 스프링 MVC가 통일

* 스프링부트의 등장 (서버를 내장함)

과거에는 서버에 was를 설치하고, 소스는 war파일을 만들어서 설치한 was에 배포해야 했다.

그러나 스프링부트는 빌드 결과(Jar)에 WAS서버를 포함시킴

## 최신 기술

Web servelt - Spring MVC

Web Reactive - Spring WebFlux

## WebFlux

비동기 non blocking 처리

최소 쓰레드로 최대 성능 - 쓰레드 컨텍스트 스위칭 비용 효율화

함수형 스타일로 개발 - 동시처리 코드 효율화

서블릿 기술 사용 X

* 단점

기술적 난이도 매우 높음

아직 RDB 지원 부족

일반 MVC의 쓰레드 모델도 충분히 빠름

실무에서 아직 사용 안함

## 자바 뷰 템플릿 역사

JSP : 속드 느림, 기능 부족

프리마터, 벨로시티 : 속도 문제, 댜양한 기능

타임리프 : 내추럴 템플릿, HTML 모양을 유지하면서 뷰 템플릿 적용 가능, 스프링 mvc와 강력한 기능 통합

# 서블릿

* HttpServletRequest, HttpServletResponse : Http 요청, 응답 메시지를 자동으로 객체에 담아서 조회 할수 있게 만듬

* 기능
    * 임시 저장소 기능
        * 저장 : request.setAttribute(name, value)
        * 조회 : request.getAttribute(name)
    * 세션 관리 기능
        * request.getSession(create: true)

`-Djava.net.preferIPv4Stack=true`

## 요청 데이터

클라이언트가 서버에 요청하는 주요 방식 3가지

1) GET - 쿼리 파라미터

2) POST - HTML FORM
    * content-type : appliction/x-www-form-urlencoded
    * 메시지에 쿼리 파라미터 형식으로 전달. GET 쿼리파라미터와 형태가 같다.

3) HTTP message body에 데이터를 직접 담아서 요청
    * Json, XML

## 응답 데이터

* HTTP 응답코드 지정
  헤더 생성

  바디 생성

  기타 제공 : content-type, 쿠키, redirect

* 응답 3가지
    * text
    * html
    * body (json, xml)

## 서블릿과 JSP(java server page)의 한계

서블릿으로 개발할 때는 뷰 화면을 위한 html을 만드는 작업이 자바 코드에 섞여서 지저분하고 복잡했다.

코드가 자바코드, 데이터 조회 리포지포드 등 모든 코드가 jsp에 노출되어 있다.

수백, 수천 줄이 넘어가는 jsp를 유지보수...?

* MVC 패턴의 등장

비즈니스 로직은 서블릿처럼 다른곳에서 처리하고, JSP는 목적에 맞게 HTML로 화면을 그리는 일에 집중.

## MVC

### 서블릿과 JSP의 문제

* 너무 많은 역할

하나의 서블릿이나 JSP만으로 비즈니스, 뷰 코드까지 한번에 .... 유지보수가 될까?

* 변경의 라이프 사이클

UI수정과 비즈니스 수정은 서로 다르게 발생한다. 근데 하나의 페이지에 이걸 짬뽕...? 영향을 미친다.

* 기능특화

화면은 화면에 특화되어야 한다.

### MVC 등장

3가지 특성으로 나눔

* 컨트롤러

* view

* Model

### MVC 한계

* 포워드 중복
    * RequestDispatcher forward 중복

* viewPath 중복

* 사용하지 않는 코드 ex) HttpServletResponse

* 공통처리가 어렵다. 이게 핵심. 위에 다 같은 문제임

이래서 위의 문제를 해결하기 위해 프런트 컨트롤러 패턴 등장.

스프링 MVC의 핵심도 이 프론트 컨트롤러에 있다. (입구를 하나로)

# MVC 프레임워크

## Front Controller

프론트 컨트롤러 서블릿 하나로 클라이언트 요청을 받음

프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출

입구를 하나로. 공통처리 가능

우리가 아는 스프링 웹 MVC의 DispatcherServlet이 프런트 컨트롤러 패턴으로 구현되어 있다.

### v1

<img width="695" alt="Screenshot 2024-08-28 at 21 20 21" src="https://github.com/user-attachments/assets/e1e7a255-4365-4c85-b00e-6b0afc24bd39">

### v2

<img width="701" alt="Screenshot 2024-08-28 at 21 20 34" src="https://github.com/user-attachments/assets/364d71e9-a420-44ae-9931-b2dac47e5cfc">

### v3

<img width="685" alt="Screenshot 2024-08-28 at 21 20 48" src="https://github.com/user-attachments/assets/f8357b9d-c2ab-409e-9ff2-e78cd6a8c4af">

### v4

<img width="697" alt="Screenshot 2024-08-28 at 21 21 01" src="https://github.com/user-attachments/assets/1285215c-f433-4c5d-94b4-deb68ae7cd8b">

### v5

<img width="690" alt="Screenshot 2024-08-28 at 21 21 17" src="https://github.com/user-attachments/assets/5191d162-e236-4afc-96c4-a55105cb069e">

# 스프링 MVC 구조 이해

## 핸들러 매핑과 핸들러 어댑터

* HandlerMapping
    * 핸들러 매핑에서 내가 호출한 컨트롤러를 찾을 수 있어야 한다.
    * ex) 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑이 필요하다.
* HandlerAdapter
    * 핸들러 매핑을 통해서 찾을 핸들러를 실행할 수 있는 어댑터가 필요하다.
    * Controller 인터페이스를 실행할 수 있는 핸들러 어댑터를 찾고 실행해야 한다.

스프링 부트가 자동 등록하는 핸들러 매핑과 핸들러 어댑터 (실제로는 많지만 아래는 주요 예제)

* HandlerMapping
    * RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    * BeanNameUrlHandlerMApping : 스프링 빈의 이름으로 핸드러를 찾는다.
* HandlerAdapter
    * RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    * HttpRequestHandlerAdapter : HttpRequestHandler 처리
    * SimpleControllerHandlerAdapter : 과거 Controller 인터페이스

**동작 순서**

1. 핸들러 매핑으로 핸들러 조회
    1. `HandlerMapping` 을 순서대로 실행해서, 핸들러를 찾는다.
    2. 이경우빈이름으로핸들러를찾아야하기때문에이름그대로빈이름으로핸들러를찾아주는
       `BeanNameUrlHandlerMapping` 가 실행에 성공하고 핸들러인 `OldController` 를 반환한다.
2. 핸들러 어댑터 조회
    1. `HandlerAdapter` 의 `supports()` 를 순서대로 호출한다.
    2. `SimpleControllerHandlerAdapter` 가 `Controller` 인터페이스를 지원하므로 대상이 된다.
3. 핸들러 어댑터 실행
    1. 디스패처 서블릿이 조회한 `SimpleControllerHandlerAdapter` 를 실행하면서 핸들러 정보도 함께 넘겨준다.
    2. `SimpleControllerHandlerAdapter` 는 핸들러인 `OldController` 를 내부에서 실행하고, 그 결과를 반환한다.

***당연히 요즘은 다 애노테이션 기반으로 개발 : @RequestMapping***

## ViewResolver

* 스프링 부트가 자동 등록하는 뷰 리졸버
    * BeanNameViewResolver : 빈 이름으로 뷰를 찾아서 반환한다. (예: 엑셀 파일 생성 기능에 사용)
    * InternalResourceViewResolver : JSP를 처리할 수 있는 뷰를 반환한다.

1. 핸들러 어댑터 호출

핸들러 어댑터를 통해 `new-form` 이라는 논리 뷰 이름을 획득한다.

2. ViewResolver 호출

`new-form` 이라는 뷰 이름으로 viewResolver를 순서대로 호출한다.
`BeanNameViewResolver` 는 `new-form` 이라는 이름의 스프링 빈으로 등록된 뷰를 찾아야 하는데 없다. `InternalResourceViewResolver` 가 호출된다.

3. InternalResourceViewResolver

이 뷰 리졸버는 `InternalResourceView` 를 반환한다.

4. 뷰 - InternalResourceView

`InternalResourceView` 는 JSP처럼 포워드 `forward()` 를 호출해서 처리할 수 있는 경우에 사용한다.

5. view.render()

`view.render()` 가 호출되고 `InternalResourceView` 는 `forward()` 를 사용해서 JSP를 실행한다.

***타임리프면 타임리프 뷰리졸버가 또 있다. gradle에 추가하면 알아서 스프링부트가 등록한다.***

### RequestMapping

`RequestMappingHandlerMapping` : 클래스 level에서 @Component, @RequestMapping이 붙은 클래스들을 찾아 bean으로 등록

`RequestMappingHandlerAdapter`

앞서 보았듯이 가장 우선순위가 높은 핸들러 매핑과 핸들러 어댑터는 `RequestMappingHandlerMapping` , `RequestMappingHandlerAdapter` 이다.
`@RequestMapping` 의 앞글자를 따서 만든 이름인데, 이것이 바로 지금 스프링에서 주로 사용하는 애노테이션 기반의 컨트롤러를 지원하는 핸들러 매핑과 어댑터이다.

**실무에서는 99.9% 이 방식의 컨트롤러를 사용한다.**


