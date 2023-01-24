
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
import java.io.File;
import java.util.Random;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * 
 * Superclass for all things in the fish tank
 * @author Cole Johnstone
 *
 */
public class FishTank extends PApplet {
	private PImage backgroundImage; // PImage object which represents the background image
	protected ArrayList<TankListener> objects; // list storing interactive objects
	protected Random randGen; // Generator of random numbers
	private TankObject flower;// flower object in tank
	private TankObject log;// log object in tank
	private TankObject shell;// shell object in tank
	private TankObject ship;// ship object in tank

	/**
	 * sets the size of this PApplet to 800 width x 600 height
	 * 
	 */
	@Override
	public void settings() {
		size(800, 600);
	}

	/**
	 * Defines initial environment properties such as screen size and loads the
	 * background image and fonts as the program starts. It also initializes all
	 * data fields.
	 * 
	 */
	@Override
	public void setup() {
		// Set and display the title of the display window
		this.getSurface().setTitle("Fish Tank 3000");
		// Set the location from which images are drawn to CENTER
		this.imageMode(PApplet.CENTER);
		// Set the location from which rectangles are drawn.
		this.rectMode(PApplet.CORNERS);
		// rectMode(CORNERS) interprets the first two parameters of rect() method
		// as the location of one corner, and the third and fourth parameters as
		// the location of the opposite corner.
		// rect() method draws a rectangle to the display window
		this.focused = true; 
		// Confirms that our Processing program is focused,
		// meaning that it is active and will accept mouse or keyboard input.
		this.textAlign(PApplet.CENTER, PApplet.CENTER);
		// sets the text alignment to center
		backgroundImage = this.loadImage("images" + File.separator + "background.png");
		// load the background image and store the loaded image to backgroundImage
		objects = new ArrayList<TankListener>(0);
		// creates an empty array list of objects
		randGen = new Random();
		// sets randGen to the reference of a new Random object
		TankObject.setProcessing(this);
		
		// adds new objects in the fish tank to the arraylist objects
		flower = new TankObject(430,60,"images" + File.separator + "flower.png");
		addObject(flower);
		log = new TankObject(580,470,"images" + File.separator + "log.png");
		addObject(log);
		shell = new TankObject(65,520,"images" + File.separator + "shell.png");
		addObject(shell);
		ship = new TankObject(280,535,"images" + File.separator + "ship.png");
		addObject(ship);
		addObject(new BlackFish(log, flower));
		addObject(new BlackFish(shell, flower));
		Button.setProcessing(this);
		addObject(new AddBlueFishButton(43,16));
		addObject(new AddOrangeFishButton(129,16));
		addObject(new AddYellowFishButton(215,16));
		addObject(new ClearTankButton(301,16));
	}

	/**
	 * Continuously draws and updates the application display window
	 */
	@Override
	public void draw() {
		// clear the display window by drawing the background image
		this.image(backgroundImage, this.width / 2, this.height / 2);
		// traverse the objects list and draw each of the objects to this display window
		for (int i = 0; i < objects.size(); i++)
			objects.get(i).draw();
	}

	/**
	 * Callback method called each time the user presses the mouse
	 */
	@Override
	public void mousePressed() {
		// traverse the objects array and start dragging an object if the mouse is over it
		// if there is a fish and a tank object over each other will choose fish when mouse pressed
		int j = -2;
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).isMouseOver() && objects.get(i) instanceof Fish) {
				j = i;
				objects.get(i).mousePressed();
				return;
			}	
		}
		if(j != -2)
			return;
		else {
			for (int i = 0; i < objects.size(); i++) {
				if (objects.get(i).isMouseOver() && objects.get(i) instanceof TankObject) {
					objects.get(i).mousePressed();
					return;
				}	
			}
		}
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i).isMouseOver()) {
				objects.get(i).mousePressed();
				return;
			}	
		}
	}

	/**
	 * Callback method called each time the mouse is released
	 * @see TankObject.mouseReleased()
	 */
	@Override
	public void mouseReleased() {
		// traverse the objects list and call each object's mouseReleased() method
		for (int i = 0; i < objects.size(); i++)
			objects.get(i).mouseReleased();
	}

	/**
	 * adds an instance of TankListener passed as input to the objects arraylist
	 * 
	 * @param object
	 */
	public void addObject(TankListener object) {
		objects.add(object);
	}

	/**
	 * Callback method called each time the user presses a key
	 * 
	 */
	@Override
	public void keyPressed() {
		switch (Character.toUpperCase(this.key)) {
			case 'O': // add a new orange fish
				addObject(new Fish());
			break;
			case 'Y': // add a new yellow fish
				addObject(new Fish(2, "images" + File.separator + "yellow.png"));
			break;
			case 'B': // add a new blue fish
				addObject(new BlueFish());
			break;	
			case 'R': // remove fish
				for (int i = 0; i < objects.size(); i++)
					if(objects.get(i) instanceof Fish && objects.get(i).isMouseOver() == true)
						objects.remove(objects.get(i));
						return;
			case 'S': // makes all fish start swimming
				for (int i = 0; i < objects.size(); i++)
					if(objects.get(i) instanceof Fish) {
						Fish fish = (Fish) objects.get(i) ;
						fish.startSwimming();}
			break;
			case 'X': // makes all fish stop swimming
				for (int i = 0; i < objects.size(); i++)
					if(objects.get(i) instanceof Fish) {
						Fish fish = (Fish) objects.get(i) ;
						fish.stopSwimming();}
			break;
			case 'C': // clears all fish from the tank
				clear();
		}
	}
	/**
	 * Removes instances of the class Fish from this tank
	 */
	public void clear() { 
		for(int i = 0; i < objects.size(); i++) {
			  if(objects.get(i) instanceof Fish) {
			    objects.remove(i);
			    i--;
			  }
			}
	}

	

	/**
	 * This main method starts the application
	 * 
	 * @param args input arguments if any
	 */
	public static void main(String[] args) {
		PApplet.main("FishTank");
		// takes a String input parameter which represents
		// the name of your PApplet class.
	}

}
