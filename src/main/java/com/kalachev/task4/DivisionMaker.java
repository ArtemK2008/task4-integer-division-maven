package com.kalachev.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DivisionMaker {
	private int dividend;
	private int divisor;
	private int negativeIntHandler;
	static final String NEWLINE = System.lineSeparator();

	public DivisionMaker(int divident, int divisor) {
		this.dividend = divident;
		this.divisor = divisor;
		negativeIntHandler = 0;
	}

	public static String divide(int dividend, int divisor) throws IllegalArgumentException {
		DivisionMaker divisionMaker = new DivisionMaker(dividend, divisor);
		handleNegatives(divisionMaker);
		zeroCheckCase(divisor);
		if (dividendSmallerThenDivisorCheck(divisionMaker)) {
			return handleSmallDividendCase(divisionMaker);
		}
		String outputDevisionPart = handleAllDivisionCalculation(divisionMaker);
		String appendGraphicPartToDivisions = changeFirstThreeLinesOfOutput(outputDevisionPart, divisionMaker);
		return appendGraphicPartToDivisions;
	}

	private static String changeFirstThreeLinesOfOutput(String multiLinesString, DivisionMaker divisionMaker) {

		StringBuilder resultBuilder = new StringBuilder();
		try (Scanner scanner = new Scanner(multiLinesString)) {
			int modifiedLineNumber = 0;
			while (scanner.hasNextLine()) {
				String oneLine = scanner.nextLine();
				if (modifiedLineNumber < 3) {
					oneLine = handleEnumPurpuses(modifiedLineNumber, divisionMaker);
					modifiedLineNumber += 1;
				}
				resultBuilder.append(oneLine + NEWLINE);
			}
			if (modifiedLineNumber < 3) {
				for (int i = modifiedLineNumber; i < 3; i++) {
					String oneLine = handleEnumPurpuses(i, divisionMaker);
					resultBuilder.append(oneLine + NEWLINE);
				}
			}
		}
	   // delete this line if u need \n in the end
		int last = resultBuilder.lastIndexOf(NEWLINE);
		if (last >= 0) { 
			resultBuilder.delete(last, resultBuilder.length());
		}
		return resultBuilder.toString();
	}

	private static List<String> getDivisorList(DivisionMaker divisionMaker) {
		List<String> divisorList = new ArrayList<>();
		String[] divResultStringArray = divisionMaker
				.convertIntToStringArray(divisionMaker.dividend / divisionMaker.divisor);
		ArrayList<String> divResultStringListWithZerosRemoved = getListWithZerosRemover(divResultStringArray);
		for (String s : divResultStringListWithZerosRemoved) {
			String curentDivisorString = String.valueOf(Integer.parseInt(s) * divisionMaker.divisor);
			divisorList.add(curentDivisorString);
		}
		return divisorList;
	}

	private String[] convertIntToStringArray(int number) {
		return String.valueOf(number).split("");
	}

	private static ArrayList<String> getListWithZerosRemover(String[] array) {
		ArrayList<String> divResultStringArrayWithZerosRemoved = new ArrayList<>();
		for (String s : array) {
			if (!s.equals("0")) {
				divResultStringArrayWithZerosRemoved.add(s);
			}
		}
		return divResultStringArrayWithZerosRemoved;
	}

	private static List<String> getDividentList(DivisionMaker divisionMaker) {
		List<String> divisorList = getDivisorList(divisionMaker);
		List<String> dividentList = new ArrayList<>();
		int lengthOfFirstDivisor = divisorList.get(0).length();
		String dividentString = String.valueOf(divisionMaker.dividend);
		String firstDivident = dividentString.substring(0, lengthOfFirstDivisor);
		if (Integer.parseInt(firstDivident) < Integer.parseInt(divisorList.get(0))) {
			firstDivident = dividentString.substring(0, lengthOfFirstDivisor + 1);
		}
		dividentList.add(firstDivident);
		String dividentLeft = dividentString.substring(firstDivident.length());

		for (int i = 0; i < divisorList.size() - 1; i++) {
			StringBuilder sb = new StringBuilder();
			int currentReminder = Integer.parseInt(dividentList.get(i)) % Integer.parseInt(divisorList.get(i));
			sb.append(String.valueOf(currentReminder));

			while ((Integer.parseInt(sb.toString()) / divisionMaker.divisor) == 0) {
				sb.append(dividentLeft.charAt(0));
				dividentLeft = dividentLeft.substring(1);
			}
			dividentList.add(sb.toString());
		}
		dividentList.add(String.valueOf(divisionMaker.dividend % divisionMaker.divisor));
		return dividentList;
	}

	private static String handleFirstLineOfExpression(DivisionMaker divisionMaker) {
		int currentDivisor = divisionMaker.divisor;
		StringBuilder consoleOutputString = new StringBuilder();
		consoleOutputString.append("_" + Integer.toString(divisionMaker.dividend));
		consoleOutputString.append("|");
		for (int i = 0; i < divisionMaker.negativeIntHandler; i++) {
			currentDivisor *= -1;
		}
		consoleOutputString.append(Integer.toString(currentDivisor));
		return consoleOutputString.toString();
	}

	private static String handleSecondLineOfExpression(DivisionMaker divisionMaker) {
		StringBuilder consoleOutputString = new StringBuilder();
		int calculateLengthBeforeSlash = String.valueOf(divisionMaker.dividend).length() + 1;
		String firstDivisor = getDivisorList(divisionMaker).get(0);
		String firstDivident = getDividentList(divisionMaker).get(0);
		int requiredDashsBeforeDivisor = firstDivident.length() - firstDivisor.length() + 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, requiredDashsBeforeDivisor), " ")));
		consoleOutputString.append(firstDivisor);
		int requiredDashsAfterDivisor = calculateLengthBeforeSlash - firstDivisor.length() - requiredDashsBeforeDivisor;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, requiredDashsAfterDivisor), " ")));
		consoleOutputString.append("|");
		consoleOutputString.append(
				String.join("", Collections.nCopies(Math.max(0, getRinghtLineTwoDashAmmout(divisionMaker)), "-")));
		return consoleOutputString.toString();
	}

	private static int getRinghtLineTwoDashAmmout(DivisionMaker divisionMaker) {
		int possibleMaxAsDivisorLength = Integer.toString(divisionMaker.divisor).length();
		int possibleMaxDivResultLength = Integer.toString(divisionMaker.dividend / divisionMaker.divisor).length();
		int maxAmmout = Math.max(possibleMaxAsDivisorLength, possibleMaxDivResultLength);
		if (divisionMaker.negativeIntHandler > 0) {
			maxAmmout += 1;
		}
		return maxAmmout;
	}

	private static String handleThirdLineOfExpression(DivisionMaker divisionMaker) {
		StringBuilder consoleOutputString = new StringBuilder();
		consoleOutputString.append(" ");
		String firstDivident = getDividentList(divisionMaker).get(0);
		consoleOutputString.append(String.join("", Collections.nCopies(firstDivident.length(), "-")));
		int calculateLengthBeforeSlash = String.valueOf(divisionMaker.dividend).length() + 1;
		int calculateRequiredDashSize = calculateLengthBeforeSlash - firstDivident.length() - 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, calculateRequiredDashSize), " ")));
		consoleOutputString.append("|");
		int divResult = divisionMaker.dividend / divisionMaker.divisor;
		for (int i = 0; i < divisionMaker.negativeIntHandler; i++) {
			divResult *= -1;
		}
		consoleOutputString.append(Integer.toString(divResult));
		return consoleOutputString.toString();
	}

	private static String handleAllDivisionCalculation(DivisionMaker divisionMaker) {
		return printDivisionProcess(divisionMaker);
	}

	private static String printDivisionProcess(DivisionMaker divisionMaker) {
		StringBuilder consoleOutputString = new StringBuilder();
		int dashAmount = 0;
		int lastDash = 1;
		List<String> dividentList = getDividentList(divisionMaker);
		List<String> divisorList = getDivisorList(divisionMaker);
		for (int i = 0; i < dividentList.size(); i++) {
			int remove = 0;
			String currentDivident = dividentList.get(i);
			if (currentDivident.charAt(0) == '0') {
				while ((currentDivident.length() > 1) && (currentDivident.charAt(0) == '0')) {
					currentDivident = currentDivident.substring(1);
					remove += 1;
				}
			}
			if (i == (dividentList.size() - 1)) {
				consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, lastDash), " ")));
				consoleOutputString.append(dividentList.get(dividentList.size() - 1));
			} else {
				int lengthOfDivident = currentDivident.length();
				int lengthOfDivisor = divisorList.get(i).length();
				int reminder = (Integer.parseInt(currentDivident)) % (Integer.parseInt(divisorList.get(i)));
				int lengthOfReminder = String.valueOf(reminder).length();
				if (reminder == 0) {
					lengthOfReminder = 0;
				}
				int lengthDiif = lengthOfDivident - lengthOfDivisor;
				int lengthDividentMinusReminder = lengthOfDivident - lengthOfReminder;

				if (remove > 0) {
					remove -= 1;
				}
				dashAmount += remove;
				consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, dashAmount), " ")));
				consoleOutputString.append("_").append(currentDivident).append(NEWLINE);

				consoleOutputString
						.append(String.join("", Collections.nCopies(Math.max(0, dashAmount + lengthDiif + 1), " ")));
				consoleOutputString.append(divisorList.get(i) + NEWLINE);

				consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, dashAmount + 1), " ")));
				consoleOutputString.append(String.join("", Collections.nCopies(currentDivident.length(), "-")));
				consoleOutputString.append(NEWLINE);

				lastDash = dashAmount + 1 + (lengthOfDivident - String.valueOf(reminder).length());
				dashAmount += lengthDividentMinusReminder;
			}
		}
		return consoleOutputString.toString();
	}

	private static void zeroCheckCase(int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException();
		}
	}

	private static boolean dividendSmallerThenDivisorCheck(DivisionMaker divisionMaker) {
		return divisionMaker.dividend < divisionMaker.divisor;
	}

	private static String handleSmallDividendCase(DivisionMaker divisionMaker) {
		StringBuilder consoleOutputString = new StringBuilder();
		consoleOutputString.append(handleFirstLineOfExpression(divisionMaker) + NEWLINE);

		int dashsRequiredBeforeSlash = String.valueOf(divisionMaker.dividend).length() + 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, dashsRequiredBeforeSlash), " ")));
		consoleOutputString.append("|");
		int lineTwoDashsRequired = getRinghtLineTwoDashAmmout(divisionMaker);
		if (divisionMaker.negativeIntHandler == 2) {
			lineTwoDashsRequired -= 1;
		}
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, lineTwoDashsRequired), "-")));
		consoleOutputString.append(NEWLINE);

		int numberOfVerticalSymbolsForDivResult = String.valueOf(divisionMaker.dividend).length();
		consoleOutputString.append(" ");
		consoleOutputString.append(String.join("", Collections.nCopies(numberOfVerticalSymbolsForDivResult, "-")));
		int lineThreeDashMaxPossibleSize = String.valueOf(divisionMaker.dividend).length() + 1;
		int lineThreeRequiredDashSize = lineThreeDashMaxPossibleSize - numberOfVerticalSymbolsForDivResult - 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, lineThreeRequiredDashSize), " ")));
		consoleOutputString.append("|" + Integer.toString(divisionMaker.dividend / divisionMaker.divisor) + NEWLINE);

		consoleOutputString.append(" " + Integer.toString(divisionMaker.dividend % divisionMaker.divisor));
		return consoleOutputString.toString();
	}

	private static void handleNegatives(DivisionMaker divisionMaker) {
		if (divisionMaker.dividend < 0) {
			divisionMaker.dividend = Math.abs(divisionMaker.dividend);
			divisionMaker.negativeIntHandler = divisionMaker.negativeIntHandler + 1;
		}

		if (divisionMaker.divisor < 0) {
			divisionMaker.divisor = Math.abs(divisionMaker.divisor);
			divisionMaker.negativeIntHandler = divisionMaker.negativeIntHandler + 1;
		}
	}

	private static String handleEnumPurpuses(int line, DivisionMaker divisionMaker) {
		String returnString = "";
		if (line == 0) {
			returnString = handleFirstLineOfExpression(divisionMaker);
		}
		if (line == 1) {
			returnString = handleSecondLineOfExpression(divisionMaker);
		}
		if (line == 2) {
			returnString = handleThirdLineOfExpression(divisionMaker);
		}
		return returnString;
	}

}