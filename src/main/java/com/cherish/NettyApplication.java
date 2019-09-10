package com.cherish;

import com.cherish.netty.NettyServerSide;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NettyApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(NettyApplication.class, args);
		NettyServerSide nettyServerSide = context.getBean(NettyServerSide.class);
		nettyServerSide.start();
	}

}
