package capstonedesign.medicalproduct.Login.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

//로그 남기는 인터셉터
//사용하려면 ebConfig에 등록
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    //메서드가 분리되어있어서 좋음

    //컨트롤러 호출 전, 인터셉터에 예외가 발생해도 호출
    //더 정확히는 핸들러 어댑터 호출 전에 호출된다.
    //preHandle 의 응답값이 true 이면 다음으로 진행하고, 다음 인터셉터 호출, false 이면 더는 진행하지 않는다. false
    //인 경우 나머지 인터셉터는 물론이고, 핸들러 어댑터도 호출되지 않는다
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //사용자uri 가져오고
        String requestURI = request.getRequestURI();

        //sessionid생성, 요청 로그를 구분하기 위한 uuid 를 생성
        String uuid = UUID.randomUUID().toString();

        //로그 response할 때 postHandle에 넘겨야하므로 요청에 sessionid 담기
        //서블릿 필터의 경우 지역변수로 해결이 가능하지만, 스프링 인터셉터는 호출 시점이 완전히 분리되어 있다.
        //따라서 preHandle 에서 지정한 값을 postHandle , afterCompletion 에서 함께 사용하려면 어딘가에 담아두어야 한다.
        //LogInterceptor도 싱글톤 처럼 사용되기 때문에 맴버변수를 사용하면 위험하다. 따라서 request 에 담아두었다.
        //이 값은 afterCompletion 에서 request.getAttribute(LOG_ID) 로 찾아서 사용
        request.setAttribute(LOG_ID, uuid);

        //@RequestMapping: HandlerMethod
        //정적 리소스: ResourceHttpRequestHandler
        //HandlerMethod
        //핸들러 정보는 어떤 핸들러 매핑을 사용하는가에 따라 달라진다.
        //스프링을 사용하면 일반적으로 @Controller , @RequestMapping 을 활용한 핸들러 매핑을 사용하는데,
        //이 경우 핸들러 정보로 HandlerMethod 가 넘어온다

        //ResourceHttpRequestHandler
        //@Controller 가 아니라 /resources/static 와 같은 정적 리소스가 호출 되는 경우
        //ResourceHttpRequestHandler 가 핸들러 정보로 넘어오기 때문에 타입에 따라서 처리가 필요하다.
        if (handler instanceof HandlerMethod) {

            HandlerMethod hm = (HandlerMethod) handler;//이 객체에 호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
        }

        //어떤 컨트롤러가 호출되는지 handler도 사용가능
        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);

        //false면 끝나고 true면 파라미터로 들어온 컨트롤러 handler가 호출됨
        return true;
    }

    //컨트롤러 호출 후
    //컨트롤러에서 예외가 발생하면 postHandle 은 호출되지 않는다
    //컨트롤러 호출 후에 호출된다. 더 정확히는 핸들러 어댑터 호출 후에 호출
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //뷰이름과 모델에 담긴게 있는지 확인가능
        log.info("postHandle [{}]", modelAndView);
    }

    //postHandle, afterCompletion
    //종료 로그를 postHandle 이 아니라 afterCompletion 에서 실행한 이유는,
    //예외가 발생한 경우 postHandle 가 호출되지 않기 때문이다. afterCompletion은 예외가 발생해도 호출 되는 것을 보장한다

    //완전하게 요청이 끝난 이 후, 뷰를 랜더링하고 호출
    //항상 호출된다. 이 경우 예외( ex )를 파라미터로 받아서 어떤예외가 발생했는지 로그로 출력할 수 있다
    //정상흐름에는 예외가 넘어오지 않음
    //예외가 발생하면 postHandle() 는 호출되지 않으므로 예외와 무관하게 공통 처리를 하려면 afterCompletion() 을 사용해야 한다.
    //예외가 발생하면 afterCompletion() 에 예외 정보( ex )를 포함해서 호출

    //인터셉터는 어떤 컨트롤러( handler) 호출되는지 호출 정보도 받을 수 있다. 그리고 어떤 modelAndView 가 반환되는지 응답 정보도 받을 수 있다.
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //사용자 uri 가져옴
        String requestURI = request.getRequestURI();

        //sessionid 받기, HttpServletRequest는 갔다가 오는게 보장되므로
        String logId = (String) request.getAttribute(LOG_ID);

        log.info("RESPONSE [{}][{}][{}]", logId, requestURI, handler);

        //예외가 있다면
        if (ex != null) {
            log.error("afterCompletion error!!", ex);
        }
    }
}
