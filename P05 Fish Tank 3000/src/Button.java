//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Fish Tank 3000
// Course:   CS 300 Fall 2021
//
// Author:   Cole Johnstone
// Email:    cjohnstone@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    -
// Partner Email:   -
// Partner Lecturer's Name: -
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         -
// Online Sources:  zybooks
//
///////////////////////////////////////////////////////////////////////////////
/**
 * Superclass that deals with button behaviors
 * @author Cole Johnstone
 *
 */
public class Button implements TankListener{
	private static final int WIDTH = 85; // Width of this Button
	private static final int HEIGHT = 32; // Height of this Button
	protected static FishTank tank; // PApplet object where this button will be displayed
	private float x; // x-position of this button in the display window
	private float y; // y-position of this button in the display window
	protected String label; // text/label which represents this button

	/**
	 * Creates a new Button at a given position within the display window
	 * and sets its label
	 * @param label
	 * @param x
	 * @param y
	 */
	public Button(String label, float x, float y) { 
		this.label = label;
		this.x = x;
		this.y = y;
	}
	/**
	 * Sets the PApplet graphic display window for all Buttons
	 * @param tank
	 */
	public static void setProcessing(FishTank tank) {
		Button.tank = tank;
	}
	/**
	 * Draws this button to the display window.
	 * overrides PApplet draw method
	 */
	@Override
	public void draw() {
		tank.stroke(0);// set line value to black
		if(isMouseOver()) {tank.fill(100);}
		else {tank.fill(200);}
		tank.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f,
		        x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
		tank.fill(0); // set the fill color to black
		tank.text(label, x, y);
	}
	/**
	 * called each time the mouse is Pressed
	 * overrides PApplet mousePressed method
	 */
	@Override
	public void mousePressed() {
		if(isMouseOver()) {System.out.println("A button was pressed.");};
	}
	/**
	 * called each time the mouse is Released
	 * overrides PApplet mouseReleased method
	 */
	@Override
	public void mouseReleased() {
		
	}
	/**
	 * checks whether the mouse is over this Tank GUI
	 * return true if the mouse is over this tank GUI object, false otherwise
	 * overrides PApplet isMouseOver method
	 * @return boolean isMouseOver
	 */
	@Override
	public boolean isMouseOver() {
		 // checks if the mouse is over this object
	    return tank.mouseX >= x - WIDTH / 2 && tank.mouseX <= x + WIDTH / 2
	        && tank.mouseY >= y - HEIGHT / 2 && tank.mouseY <= y + HEIGHT / 2;
	}
	
}
