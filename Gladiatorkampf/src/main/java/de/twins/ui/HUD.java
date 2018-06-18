package de.twins.ui;

import de.twins.gladiator.domain.AbstractFighter;

import java.awt.Color;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class HUD {
private AbstractFighterUI gameObject;

public HUD(AbstractFighterUI gameObject) {
	this.gameObject = gameObject;
}
	public void tick(){
		gameObject.getFighter().setCurrentHealthPoints(gameObject.getFighter().getCurrentHealthPoints().subtract(BigDecimal.ONE));
		System.out.println(gameObject.getFighter().getCurrentHealthPoints());
	}
	
	public void render(Graphics g){
		
	
		
		BigDecimal currentHealthPoints = gameObject.getFighter().getCurrentHealthPoints();
		BigDecimal totalHealthPoints = gameObject.getFighter().getTotalHealthPoints();
		double percentageOfLife = currentHealthPoints.divide(totalHealthPoints,new MathContext(2)).doubleValue();
		System.out.println(percentageOfLife);
		g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.GREEN);
		g.fillRect(15, 15,200 , 32);	g.setColor(Color.GRAY);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.GREEN);
		g.fillRect(15, 15,(int)(200*percentageOfLife) , 32);

	}
}
