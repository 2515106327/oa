package com.ck.day3.service;

import java.lang.reflect.Method;
import java.sql.Connection;

import com.ck.day3.dao.DbHelper;
import com.ck.day3.exception.ServiceException;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ServiceProxyFactory {
	private Enhancer enhancer = new Enhancer();

	public static <T> T getService(Class<T> c) {
		ServiceProxyFactory f = new ServiceProxyFactory();
		f.enhancer.setSuperclass(c);
		f.enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object o, Method m, Object[] params, MethodProxy proxy) throws Throwable {
				Connection conn = DbHelper.getConnection();
				conn.setAutoCommit(false);
				Object result = null;
				try {
					result = proxy.invokeSuper(o, params);// 调用代理目标的方法
					conn.commit();
				} catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
					throw new ServiceException();
				} finally {
					DbHelper.closeConnection(conn);
				}
				return result;
			}
		});
		return (T) f.enhancer.create();
	}
}