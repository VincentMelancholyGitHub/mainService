package com.cjx.core;

import java.sql.Connection;
import java.sql.SQLException;

import com.cjx.utils.ConnectionHelper;

import flex.messaging.config.ConfigMap;
import flex.messaging.services.AbstractBootstrapService;

public class StartupService extends AbstractBootstrapService {

	public static void main(String[] args)
	{
		StartupService ss = new StartupService();
		ss.initialize("", new ConfigMap());
	}
	
	@Override
	public void initialize(String arg0, ConfigMap arg1) {
		
		Connection c = null;
		
		try {
			c = ConnectionHelper.getConnection();
			System.out.println("数据库连接成功! by cjx!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally
		{
			ConnectionHelper.close(c);
		}
		
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

}
