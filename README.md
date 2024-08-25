# 웹 어플리케이션

웹 서버는 정적 리소스 실행하는데 더 특화

WAS는 애플리케이션 코드를 실행하는데 더 특화

물론 was도 정적 리소스 실행가능

# 서블릿

<img width="961" alt="Screenshot 2024-08-25 at 16 52 34" src="https://github.com/user-attachments/assets/ccec52b9-5c88-49ba-9a5f-b431d4f96c36">

톰캣처럼 서블릿을 지원하는 was를 서블릿 컨테이너라고 함

서블릿 컨테이너는 서블릿 객체를 생성, 초기화, 호출, 종료하는 생명주기 관리

서블릿 객체는 싱글톤으로 관리 : **공유 변수 사용 주의**

JSP도 서블릿으로 변환되어서 사용

**동시 요청을 위한 멀티 쓰레드 처리 지원**



