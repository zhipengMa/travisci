package com.yx.travisci.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloWordController.
 *
 * @author Zhipeng Ma.
 * @since 2020/7/3 5:29 下午
 */
@RestController
@RequestMapping("/hello")
public class HelloWordController {

	@GetMapping("/world")
	public String helloWorld() {
		return "hello world!";
	}

}
