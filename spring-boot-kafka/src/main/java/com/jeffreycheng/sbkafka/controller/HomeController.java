package com.jeffreycheng.sbkafka.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	/*
	 * Session Beans (Session beans cannot use @Autowired)
	 */
	private String selectedCaptchaPosition;
	private String userId;
	private String userAvatar;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/* Home Controller */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session,
		Map<String, String> propertiesMap) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>Inside HomeController->/");
		/*Device device = DeviceUtils.getCurrentDevice(request);

		if (device.isMobile() || device.isTablet()) {
			System.out.println(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<\nA mobile or a tablet is accessing Hivesplace.com");
				request.setAttribute("Request-Attribute", "Value of Attribute ");
			    try {
					response.sendRedirect("http://www.hivesplace.com/mobileHome/");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else {
			System.out.println(">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<\nA desktop is accessing Hivesplace.com");
		}*/
		return "home";
	}

}
