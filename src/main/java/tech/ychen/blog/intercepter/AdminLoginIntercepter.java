package tech.ychen.blog.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AdminLoginIntercepter implements HandlerInterceptor {

    public final static String SESSION_KEY= "admin";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        String path = request.getServletPath();
        if(path.contains("login")){
            return true;
        }

        for(Cookie cookie :cookies){
            if(cookie.getName().equals(SESSION_KEY)){
                return true;
            }
        }

        response.sendRedirect("/api/v1/articles");
        return false;
    }
}
