package com.henry;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;

public class RobotManager {

	private List<Robot> Robots = null;
	

	public RobotManager() {
		
		Robots = new ArrayList<Robot>();
		
		
	}
		
	
	public void addRobot(Robot robot){
		
		
		Robots.add(robot);
		
	}
	
	
	public void drawAllRobot(Canvas canvas){
		
		for (int i = 0; i < Robots.size(); i++) {
			
			Robot robot = Robots.get(i);
			
			robot.drawRobotSelf(canvas);
			
		}
	}
}
