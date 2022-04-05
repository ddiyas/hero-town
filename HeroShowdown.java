import java.util.ArrayList;
import java.util.Scanner;

public class HeroShowdown {
	private ArrayList<Person> townsPeople;
	private ArrayList<Person> unfortunateSouls;
	private ArrayList<Person> safe;
	private Scanner reader;

	public HeroShowdown() {
		unfortunateSouls = new ArrayList<Person>();
		safe = new ArrayList<Person>();
		// the townspeople START
		townsPeople = new ArrayList<Person>();
		reader = new Scanner(System.in);
		townsPeople.add(new BadGuy("Bob Biswas", "Contract Killer", "PEW PEW", false, 22, "hehehehe"));
		townsPeople.add(new BadGuy("Mogambo", "Aspiring Supreme Ruler of the World", "put in burning tank of stuff", true, 31, "HEHAHAHAHEEHA"));
		townsPeople.add(new BadGuy("Aadya Jain", "Professional Awesome Person", "stair jump", true, 29, "AHAHAHAHAHAHA"));
		townsPeople.add(new GoodGuy("The Great Gatsby", "Pharmacist", "American dream", false, 19,
				"Old sport!"));
		townsPeople.add(new GoodGuy("Mr. India", "Babysitter", "invisibility", true, 17,
				"karte hai pyaar Mr. India se!"));
		//Mr.India's catchphrase is a song from the movie Mr. India which means "we love Mr. India!"

		String normals[][] = {{"Noor Bagchi", "Carpenter"}, {"Ahsaas Ganguly", "Bus Driver"},
				{"Gaurav Kashyap", "Architect"}, {"Rakulpreet Sinha", "CEO of Heroworks"},
				{"Akash Singhania", "Teacher"}, {"Veer Thakre", "Doctor"},
				{"Begum Ahuja", "Social Media Influencer"}, {"Krish Verma", "Freelancer"}, {"Sendalan Sasikaran", "Professional Turtle"}, {"Arav Pyati", "Wispish"}};

		for (int i = 0; i < normals.length; i++) {
			townsPeople.add(new NormalGuy(normals[i][0], normals[i][1]));
		}
		// the townspeople END

		// interactions
		System.out.println("\nInteractions:");
		roundInteraction();
	}

	public boolean end () {
		int g = 0;
		int b = 0;
		int n = 0;
		for (Person p : townsPeople) {
			if (p instanceof GoodGuy) {
				g++;
			}
			else if (p instanceof BadGuy){
				b++;
			}
			else {
				n++;
			}
		}
		if (n != 0) {
			return true;
		}
		else if (g == 0 && b != 0) {
			return false;
		}
		else if (b == 0 && g != 0){
			return false;
		}
		return true;
	}

	public void roundInteraction() {
		String exit = "";
		System.out.println("Press 'Q' to quit, otherwise Enter to continue");
		while (!exit.equalsIgnoreCase("q") && end() ) {
			// Take 2 random people out of list/ remove so you don't duplicate
			Person p1 = townsPeople.remove((int) (Math.random() * townsPeople.size()));
			Person p2 = townsPeople.remove((int) (Math.random() * townsPeople.size()));

			// determines interaction code of the situation
			int c = interactionCode(p1, p2);

			int r = (int)(Math.random()*100)+1;

			//describes whether the people should be sent back to the townspeople ArrayList or if that has been taken care of in
			//another method
			boolean o = true;

			if (c == 11) {
				normalInteraction(p1, p2);
			} else if (c == 22) {
				veryGoodInteraction(p1, p2);
			} else if (c == 23 || c == 32) {
				o = false;
				badGoodInteraction(p1, p2, c);
			} else if (c == 13 || c == 31) {
				o = false;
				badNormalInteraction(p1, p2, c);
			} else if (c == 12 || c == 21) {
				o = false;
				goodInteraction(p1, p2, c);
			} else if (c == 33) {
				veryBadInteraction(p1, p2);
			}

			// Put the townsPeople back safely where you found them IF outcome is true
			if (o) {
				townsPeople.add(p1);
				townsPeople.add(p2);
			}

			//there is a 10% chance that a normal human will spontaneously combust
			int temp = (int)(Math.random()*100);
			if (temp < 10) {
				spontaneousCombustion();
			}

			System.out.println("...");
			exit = reader.nextLine(); // enter q to quit, otherwise just press enter
		}
			System.out.print("And the winners are ");
			for (Person p : townsPeople) {
				System.out.print(p.getName() + ", ");
			}
			if (townsPeople.get(0) instanceof GoodGuy) {
				System.out.print("The town has been saved!");
			}
			else {
				System.out.print("The town has fallen to the villains!");
			}
	}

	//creates a code for each interaction which corresponds with the order in which the type of person is sent
	private int interactionCode(Person p1, Person p2) {
		int code = 0;
		if (p1 instanceof NormalGuy) {
			code += 10;
		} else if (p1 instanceof GoodGuy) {
			code += 20;
		} else if (p1 instanceof BadGuy) {
			code += 30;
		}

		if (p2 instanceof NormalGuy) {
			code += 1;
		} else if (p2 instanceof GoodGuy) {
			code += 2;
		} else if (p2 instanceof BadGuy) {
			code += 3;
		}
		return code;
	}

	private void normalInteraction(Person one, Person two) {
		String normalThings[] = {" converses with ", " goes to a fancy business conference with ", " goes to Walmart with ", " parades around town playing the kazoo with ", " arm wrestles ", " climbs a mountain with ", " catches a pigeon with "};
		System.out.println(one.getName() + " the " + one.getJob() + normalThings[(int)(Math.random()*normalThings.length)] + two.getName() + " the " + two.getJob());
	}

	private void spontaneousCombustion() {
		ArrayList <Integer> temp = new ArrayList<>();
		for (int i = 0; i < townsPeople.size(); i++) {
			if (townsPeople.get(i) instanceof NormalGuy) {
				temp.add(i);
			}
		}
		System.out.println("Oh no! One of the normal humans spontaneously combusted!");
		int r = temp.get((int)Math.random()*temp.size());
		townsPeople.remove(r);
	}

	private void veryGoodInteraction(Person one, Person two) {
		System.out.println(one.getName() + " smiles ominously at " + two.getName());
		((GoodGuy) two).powerUp();
		((GoodGuy) one).powerUp();
		System.out.println(one.getName() + "\'s power level increases to " + ((GoodGuy) one).getPowerLevel() + " and " + two.getName() + "\'s power level increases to " + ((GoodGuy) two).getPowerLevel() + " through the power of goodness!");
	}

	private void veryBadInteraction(Person one, Person two) {
		String superBadThings[] = {"blow up the town banana farm", "spread mass diarrhea", "trip people with strings", "launch a cockroach invasion"};

		System.out.println(one.getName() + " and " + two.getName() + " come together to " + superBadThings[(int) Math.random() * superBadThings.length]);
		int r1 = (int) (Math.random() * townsPeople.size()-1);
		int r2 = (int) (Math.random() * townsPeople.size()-1);
		if (townsPeople.size() == 1) {
			System.out.println(townsPeople.get(r1).getName() + " gets caught up in the mess :(");
			unfortunateSouls.add(townsPeople.get(r1));
			townsPeople.remove(r1);
		}
		else {
			System.out.println(townsPeople.get(r1).getName() + " and " + townsPeople.get(r2).getName() + " get caught up in the mess :(");
			unfortunateSouls.add(townsPeople.get(r1));
			unfortunateSouls.add(townsPeople.get(r2));
			townsPeople.remove(r1);
			townsPeople.remove(r2);
		}
	}

	private void goodInteraction(Person one, Person two, int code) {
		if (code == 21) {
			System.out.println(one.getName() + " saves " + two.getName() + " the " + two.getJob());
			townsPeople.remove(two);
			safe.add(two);
			townsPeople.add(one);
			System.out.print(one.getName() + " was at power level " + ((SuperHero) one).getPowerLevel() + ". ");
			((GoodGuy) one).powerUp();
			System.out.println(one.getName() + " is now at power level " + ((SuperHero) one).getPowerLevel());
		} else {
			System.out.println(two.getName() + " saves " + one.getName() + " the " + one.getJob());
			townsPeople.remove(one);
			safe.add(one);
			townsPeople.add(two);
			System.out.print(two.getName() + " was at power level " + ((SuperHero) two).getPowerLevel() + ". ");
			((GoodGuy) two).powerUp();
			System.out.println(two.getName() + " is now at power level " + ((SuperHero) two).getPowerLevel());
		}
	}

	private void badNormalInteraction(Person one, Person two, int code) {
		String superBadThings[] = {" HEEEEYAAAS ", " yeets away ", " aggressively tickles "};
		int temp = (int)(Math.random()*10);
		if (code % 10 == 1) {
			if (temp == 7) {
				System.out.println("In an act of incredible bravery, " + two.getName() + superBadThings[(int)(Math.random() * superBadThings.length)] + one.getName() + "!");
				townsPeople.add(new GoodGuy("The Great " + two.getName(), "Super " + two.getJob(), "bravery", false, 27,
						"I'm the new guy"));
				townsPeople.remove(two);
				unfortunateSouls.add(one);
				townsPeople.remove(one);
			}
			else {
				System.out.println(one.getName() + superBadThings[(int) (Math.random() * superBadThings.length)] + two.getName() + " the " + two.getJob() + ". " + two.getName() + " has unfortunately passed.");
				townsPeople.remove(two);
				unfortunateSouls.add(two);
				townsPeople.add(one);
			}
		} else {
			if (temp == 7) {
				System.out.println("In an act of incredible bravery, " + one.getName() + superBadThings[(int)(Math.random() * superBadThings.length)] + two.getName() + "!");
				townsPeople.add(new GoodGuy("The Great " + one.getName(), "Super " + one.getJob(), "bravery", false, 27,
						"I'm the new guy"));
				townsPeople.remove(one);
				unfortunateSouls.add(two);
				townsPeople.remove(two);
			}
			else {
				System.out.println(two.getName() + superBadThings[(int) (Math.random() * superBadThings.length)] + one.getName() + " the " + one.getJob() + ". " + one.getName() + " has unfortunately passed.");
				townsPeople.remove(one);
				unfortunateSouls.add(one);
				townsPeople.add(two);
			}
		}
	}

	private void badGoodInteraction(Person one, Person two, int code) {
		if (code == 23) {
			System.out.println(one.getName() + " has an epic showdown with " + two.getName());
			System.out.println(one.getName() + " uses " + ((SuperHero) one).getSuperPower() + "\n" + two.getName() + " says " + ((BadGuy) two).getEvilLaugh() + " and uses " + ((SuperHero) two).getSuperPower());
			System.out.println(one.getName() + " is at power level " + ((SuperHero) one).getPowerLevel() + ". " + two.getName() + " is at power level " + ((SuperHero) two).getPowerLevel());
			if (((SuperHero) one).getPowerLevel() > ((SuperHero) two).getPowerLevel()) {
				System.out.println(one.getName() + " has won the showdown! Hero Town is safe again (for now). RIP " + two.getName());
			}
			else {
				System.out.println(two.getName() + " has won the showdown! Hero Town is falling to the villains! RIP " + one.getName() + ".");
			}
			unfortunateSouls.add(two);
			townsPeople.add(one);
		} else {
			System.out.println(two.getName() + " has an epic showdown with " + one.getName() + ".");
			System.out.println(two.getName() + " says " + ((GoodGuy) two).getCatchPhrase() + " and uses " + ((SuperHero) two).getSuperPower() + "\n" + one.getName() + " says " + ((BadGuy) one
			).getEvilLaugh() + ".");
			System.out.println(two.getName() + " is at power level " + ((SuperHero) two).getPowerLevel() + ". " + one.getName() + " is at power level " + ((SuperHero) one).getPowerLevel() + ".");
			if (((SuperHero) two).getPowerLevel() > ((SuperHero) one).getPowerLevel()) {
				System.out.println(two.getName() + " has won the showdown! Hero Town is safe again (for now). RIP " + one.getName() + ".");
			}
			else{
				System.out.println(one.getName() + " has won the showdown! Hero Town is falling to the villains! RIP " + two.getName());
			}
			unfortunateSouls.add(one);
			townsPeople.add(two);
		}
	}
}