package org.redisson.tomcat;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1243830648280853203L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (req.getPathInfo().equals("/write-coll")) {
            String[] params = req.getQueryString().split("&");
            List<String> attrs = new ArrayList<>();
            String key = null;
            String value = null;
            for (String param : params) {
                String[] paramLine = param.split("=");
                String keyParam = paramLine[0];
                String valueParam = paramLine[1];

                if ("key".equals(keyParam)) {
                    key = valueParam;
                }
                if ("value".equals(keyParam)) {
                    value = valueParam;
                }
                attrs.add(key);
                attrs.add(value);
            }
            session.setAttribute(key, attrs);

            resp.getWriter().print("OK");
        } else if (req.getPathInfo().equals("/write")) {
            String[] params = req.getQueryString().split("&");
            String key = null;
            String value = null;
            for (String param : params) {
                String[] paramLine = param.split("=");
                String keyParam = paramLine[0];
                String valueParam = paramLine[1];
                
                if ("key".equals(keyParam)) {
                    key = valueParam;
                }
                if ("value".equals(keyParam)) {
                    value = valueParam;
                }
            }
            session.setAttribute(key, value);
            
            resp.getWriter().print("OK");
        } else if (req.getPathInfo().equals("/read")) {
            String[] params = req.getQueryString().split("&");
            String key = null;
            for (String param : params) {
                String[] line = param.split("=");
                String keyParam = line[0];
                if ("key".equals(keyParam)) {
                    key = line[1];
                }
            }
            
            Object attr = session.getAttribute(key);
            resp.getWriter().print(attr);
        } else if (req.getPathInfo().equals("/writeInternal")) {
            String[] params = req.getQueryString().split("&");
            String value = null;
            for (String param : params) {
                String[] paramLine = param.split("=");
                String keyParam = paramLine[0];
                String valueParam = paramLine[1];
                if ("value".equals(keyParam)) {
                    value = valueParam;
                }
            }
            
            if (value == null) {
                resp.getWriter().print("ERROR");
            } else {
                session.setMaxInactiveInterval(Integer.parseInt(value));
                resp.getWriter().print("OK");
            }
        } else if (req.getPathInfo().equals("/readInternal")) {
            resp.getWriter().print(session.getMaxInactiveInterval());
        }  else if (req.getPathInfo().equals("/remove")) {
            String[] params = req.getQueryString().split("&");
            String key = null;
            for (String param : params) {
                String[] line = param.split("=");
                String keyParam = line[0];
                if ("key".equals(keyParam)) {
                    key = line[1];
                }
            }
            
            session.removeAttribute(key);
            resp.getWriter().print(session.getAttribute(key));
        } else if (req.getPathInfo().equals("/invalidate")) {
            session.invalidate();
            
            resp.getWriter().print("OK");
        } else if (req.getPathInfo().equals("/recreate")) {
            session.invalidate();
            
            session = req.getSession();
            
            String[] params = req.getQueryString().split("&");
            String key = null;
            String value = null;
            for (String param : params) {
                String[] paramLine = param.split("=");
                String keyParam = paramLine[0];
                String valueParam = paramLine[1];
                
                if ("key".equals(keyParam)) {
                    key = valueParam;
                }
                if ("value".equals(keyParam)) {
                    value = valueParam;
                }
            }
            session.setAttribute(key, value);
            
            resp.getWriter().print("OK");
        }
    }
    
}
