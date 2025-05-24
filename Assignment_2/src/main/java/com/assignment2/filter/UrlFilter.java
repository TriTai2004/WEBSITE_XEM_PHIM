package com.assignment2.filter;
//Nguyen Tri Tai
//Mssv: PC09496
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment2.entity.User;

@WebFilter({"/trang-chu", "/video", "/video-chi-tiet/*", "/yeu-thich", "/lien-he"})
public class UrlFilter implements HttpFilter{
	public static final String SUCURITY_URI = "securityUri";
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String uri = request.getQueryString();
		String url = request.getRequestURL().toString();
		if(uri != null) {
			url = url + "?" + uri;
		}
		request.getSession().setAttribute(SUCURITY_URI, url);

		chain.doFilter(request, response);
	}
	
}
