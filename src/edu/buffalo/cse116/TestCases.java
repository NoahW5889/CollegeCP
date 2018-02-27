package edu.buffalo.cse116;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class TestCases {

	Board board = new Board();
	String filename = "src/GameWords.txt";
	String[] file = {"AFRICA","AGENT","AIR","ALIEN","ALPS","AMAZON","AMB"
			+ "ULANCE","AMERICA","ANGEL","ANTARCTICA","APPLE","ARM","ATLANTIS"
			,"AUSTRALIA","AZTEC","BACK","BALL","BAND","BANK","BAR","BARK","BAT"
			,"BATTERY","BEACH","BEAR","BEAT","BED","BEIJING","BELL","BELT","BERLIN"
			,"BERMUDA","BERRY","BILL","BLOCK","BOARD","BOLT","BOMB","BOND","BOOM"
			,"BOOT","BOTTLE","BOW","BOX","BRIDGE","BRUSH","BUCK","BUFFALO","BUG"
			,"BUGLE","BUTTON","CALF","CANADA","CAP","CAPITAL","CAR","CARD","CARROT"
			,"CASINO","CAST","CAT","CELL","CENTAUR","CENTER","CHAIR","CHANGE","CHARGE"
			,"CHECK","CHEST","CHICK","CHINA","CHOCOLATE","CHURCH","CIRCLE","CLIFF"
			,"CLOAK","CLUB","CODE","COLD","COMIC","COMPOUND","CONCERT","CONDUCTOR"
			,"CONTRACT","COOK","COPPER","COTTON","COURT","COVER","CRANE","CRASH"
			,"CRICKET","CROSS","CROWN","CYCLE","CZECH","DANCE","DATE","DAY","DEATH"
			,"DECK","DEGREE","DIAMOND","DICE","DINOSAUR","DISEASE","DOCTOR","DOG"
			,"DRAFT","DRAGON","DRESS","DRILL","DROP","DUCK","DWARF","EAGLE","EGYPT"
			,"EMBASSY","ENGINE","ENGLAND","EUROPE","EYE","FACE","FAIR","FALL","FAN"
			,"FENCE","FIELD","FIGHTER","FIGURE","FILE","FILM","FIRE","FISH","FLUTE"
			,"FLY","FOOT","FORCE","FOREST","FORK","FRANCE","GAME","GAS","GENIUS"
			,"GERMANY","GHOST","GIANT","GLASS","GLOVE","GOLD","GRACE","GRASS","GREECE"
			,"GREEN","GROUND","HAM","HAND","HAWK","HEAD","HEART","HELICOPTER","HIMALAYAS"
			,"HOLE","HOLLYWOOD","HONEY","HOOD","HOOK","HORN","HORSE","HORSESHOE","HOSPITAL"
			,"HOTEL","ICE","ICE CREAM","INDIA","IRON","IVORY","JACK","JAM","JET","JUPITER"
			,"KANGAROO","KETCHUP","KEY","KID","KING","KIWI","KNIFE","KNIGHT","LAB","LAP"
			,"LASER","LAWYER","LEAD","LEMON","LEPRECHAUN","LIFE","LIGHT","LIMOUSINE","LINE"
			,"LINK","LION","LITTER","LOCH NESS","LOCK","LOG","LONDON","LUCK","MAIL","MAMMOTH"
			,"MAPLE","MARBLE","MARCH","MASS","MATCH","MERCURY","MEXICO","MICROSCOPE"
			,"MILLIONAIRE","MINE","MINT","MISSILE","MODEL","MOLE","MOON","MOSCOW","MOUNT"
			,"MOUSE","MOUTH","MUG","NAIL","NEEDLE","NET","NEW YORK","NIGHT","NINJA","NOTE"
			,"NOVEL","NURSE","NUT","OCTOPUS","OIL","OLIVE","OLYMPUS","OPERA","ORANGE","ORGAN"
			,"PALM","PAN","PANTS","PAPER","PARACHUTE","PARK","PART","PASS","PASTE","PENGUIN"
			,"PHOENIX","PIANO","PIE","PILOT","PIN","PIPE","PIRATE","PISTOL","PIT","PITCH","PLANE"
			,"PLASTIC","PLATE","PLATYPUS","PLAY","PLOT","POINT","POISON","POLE","POLICE","POOL"
			,"PORT","POST","POUND","PRESS","PRINCESS","PUMPKIN","PUPIL","PYRAMID","QUEEN","RABBIT"
			,"RACKET","RAY","REVOLUTION","RING","ROBIN","ROBOT","ROCK","ROME","ROOT","ROSE"
			,"ROULETTE","ROUND","ROW","RULER","SATELLITE","SATURN","SCALE","SCHOOL","SCIENTIST"
			,"SCORPION","SCREEN","SCUBA DIVER","SEAL","SERVER","SHADOW","SHAKESPEARE","SHARK"
			,"SHIP","SHOE","SHOP","SHOT","SINK","SKYSCRAPER","SLIP","SLUG","SMUGGLER","SNOW"
			,"SNOWMAN","SOCK","SOLDIER","SOUL","SOUND","SPACE","SPELL","SPIDER","SPIKE","SPINE"
			,"SPOT","SPRING","SPY","SQUARE","STADIUM","STAFF","STAR","STATE","STICK","STOCK"
			,"STRAW","STREAM","STRIKE","STRING","SUB","SUIT","SUPERHERO","SWING","SWITCH","TABLE"
			,"TABLET","TAG","TAIL","TAP","TEACHER","TELESCOPE","TEMPLE","THEATER","THIEF","THUMB"
			,"TICK","TIE","TIME","TOKYO","TOOTH","TORCH","TOWER","TRACK","TRAIN","TRIANGLE","TRIP"
			,"TRUNK","TUBE","TURKEY","UNDERTAKER","UNICORN","VACUUM","VAN","VET","WAKE","WALL"
			,"WAR","WASHER","WASHINGTON","WATCH","WATER","WAVE","WEB","WELL","WHALE","WHIP","WIND"
			,"WITCH","WORM","YARD","area","book","business","case","child","company","country"
			,"day","eye","fact","family","government","group","hand","home","job","life","lot"
			,"man","money","month","mother","Mr","night","number","part","people","place"
			,"point","problem","program","question","right","room","school","state","story"
			,"student","study","system","thing","time","water","way","week","woman","word"
			,"work","world","year"};
	ArrayList<String> codeNames = new ArrayList<String>();

	public ArrayList<String> createArrayList(String[] x) {
		for(int i=0;i<x.length;i++	) {
			codeNames.add(x[i]);
		}
		return codeNames;
	}

	public boolean noNull(String[] selected) {
		for(int i=0;i<board.list.length;i++) {
			if(board.list[i]==null||board.list[i].equals(null))
				return false;
		}
		return true;
	}

	public boolean noDoubles(String[] input) {
		for(int i=0;i<board.list.length;i++) {
			String check = board.list[i];
			for(int q=0;q<board.list.length;q++) {
				if(board.list[q].equals(check)&&q!=i)
					return false;
			}
		}
		return true;
	}

	@Test
	public void testBoard() throws Exception {

		assertTrue(board.board[0].length==5&&board.board.length==5); //Testing Board Size (5x5)
		assertEquals(board.readCSVFile(filename),createArrayList(file)); // Tests of Code Names file was read correctly
		assertTrue(board.list.length==25); //Tests to make sure list has selected 25 names
		board.createList();// Must create list before test for noNull
		assertTrue(noNull(board.list));// Testing to make sure there are no nulls in list
		assertTrue(noDoubles(board.list));// Assures there are no repeated codenames
		
	}

	@Test
	public void testWin() {
		
	}
}
