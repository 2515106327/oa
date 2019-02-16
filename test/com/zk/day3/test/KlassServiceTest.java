package com.zk.day3.test;

import org.junit.Test;

import com.ck.day3.entity.Klass;
import com.ck.day3.entity.PageBean;
import com.ck.day3.service.KlassService;
import com.ck.day3.service.ServiceProxyFactory;

public class KlassServiceTest {

	@Test
	public void testGetKlassByPage() {
		KlassService klassService = ServiceProxyFactory.getService(KlassService.class);
		PageBean<Klass> pageBean = klassService.getKlassesByPage(2);
		System.out.println(pageBean);
	}
}
