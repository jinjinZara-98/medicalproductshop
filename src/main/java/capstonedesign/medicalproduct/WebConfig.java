package capstonedesign.medicalproduct;

import capstonedesign.medicalproduct.Login.argumentresolver.LoginMemberArgumentResolver;
import capstonedesign.medicalproduct.Login.interceptor.LogInterceptor;
import capstonedesign.medicalproduct.Login.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    //LogInterceptor 등록, LoginCheckInterceptor() 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor())

                //1순위, 인터셉터의 호출 순서를 지정한다. 낮을 수록 먼저 호출
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/members/memberDetail", "/cart/cart", "/cart/put", "/order/cartItem",
                                 "/order/itemOne", "/member/password/modify", "/member/{modifyInfo}/modify",
                                "order/orderList", "order/{id}/detail",  "order/{id}/cancel",
                                "review/reviewList", "/review/{id}/modify", "review/item/{id}"
                                ,"cart/cartItem/{id}/QuantityMinus", "cart/cartItem/{id}/QuantityPlus", "cart/{cartId}/cancel"
                                ,"members/sendmail/{memberId}", "order/payment", "order/{id}/tryCancel", "review/register");
    }
}