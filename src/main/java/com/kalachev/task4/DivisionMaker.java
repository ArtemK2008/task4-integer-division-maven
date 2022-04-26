package com.kalachev.task4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DivisionMaker {
	static final String NEWLINE = System.lineSeparator();

	public String divide(int dividend, int divisor) throws IllegalArgumentException {
		DivisionsContainer divisionsContainer = new DivisionsContainer(dividend, divisor);
		handleNegatives(divisionsContainer);
		zeroCheckCase(divisor);
		if (dividendSmallerThenDivisorCheck(divisionsContainer)) {
			return handleSmallDividendCase(divisionsContainer);
		}
		String outputCalculationsPart = handleAllDivisionCalculation(divisionsContainer);
		return mergeCalculationsWithGraphics(outputCalculationsPart, divisionsContainer);
	}

	private String mergeCalculationsWithGraphics(String multiLinesString, DivisionsContainer divisionsContainer) {

		StringBuilder resultBuilder = new StringBuilder();
		try (Scanner scanner = new Scanner(multiLinesString)) {
			int modifiedLineNumber = 0;
			while (scanner.hasNextLine()) {
				String oneLine = scanner.nextLine();
				if (modifiedLineNumber < 3) {
					oneLine = handleFirstThreeLinesEnum(modifiedLineNumber, divisionsContainer);
					modifiedLineNumber += 1;
				}
				resultBuilder.append(oneLine + NEWLINE);
			}
			if (modifiedLineNumber < 3) {
				for (int i = modifiedLineNumber; i < 3; i++) {
					String oneLine = handleFirstThreeLinesEnum(i, divisionsContainer);
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

	private List<String> createPartialCalculationDivisors(DivisionsContainer divisionsContainer) {
		List<String> divisorList = new ArrayList<>();
		String[] divResultStringArray = convertToStringArray(divisionsContainer.getDividend() / divisionsContainer.getDivisor());
		ArrayList<String> divResultStringListWithZerosRemoved = retrieveWithZerosRemover(divResultStringArray);
		for (String s : divResultStringListWithZerosRemoved) {
			String curentDivisorString = String.valueOf(Integer.parseInt(s) * divisionsContainer.getDivisor());
			divisorList.add(curentDivisorString);
		}
		return divisorList;
	}

	private String[] convertToStringArray(int number) {
		return String.valueOf(number).split("");
	}

	private ArrayList<String> retrieveWithZerosRemover(String[] array) {
		ArrayList<String> divResultStringArrayWithZerosRemoved = new ArrayList<>();
		for (String s : array) {
			if (!s.equals("0")) {
				divResultStringArrayWithZerosRemoved.add(s);
			}
		}
		return divResultStringArrayWithZerosRemoved;
	}

	private List<String> createPartialCalculationDividents(DivisionsContainer divisionsContainer) {
		List<String> divisorList = createPartialCalculationDivisors(divisionsContainer);
		List<String> dividentList = new ArrayList<>();
		int lengthOfFirstDivisor = divisorList.get(0).length();
		String dividentString = String.valueOf(divisionsContainer.getDividend());
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

			while ((Integer.parseInt(sb.toString()) / divisionsContainer.getDivisor()) == 0) {
				sb.append(dividentLeft.charAt(0));
				dividentLeft = dividentLeft.substring(1);
			}
			dividentList.add(sb.toString());
		}
		dividentList.add(String.valueOf(divisionsContainer.getDividend() % divisionsContainer.getDivisor()));
		return dividentList;
	}

	private String handleFirstLineOfExpression(DivisionsContainer divisionsContainer) {
		int currentDivisor = divisionsContainer.getDivisor();
		StringBuilder consoleOutputString = new StringBuilder();
		consoleOutputString.append("_" + Integer.toString(divisionsContainer.getDividend()));
		consoleOutputString.append("|");
		for (int i = 0; i < divisionsContainer.getNegativeIntHandler(); i++) {
			currentDivisor *= -1;
		}
		consoleOutputString.append(Integer.toString(currentDivisor));
		return consoleOutputString.toString();
	}

	private String handleSecondLineOfExpression(DivisionsContainer divisionsContainer) {
		StringBuilder consoleOutputString = new StringBuilder();
		int calculateLengthBeforeSlash = String.valueOf(divisionsContainer.getDividend()).length() + 1;
		String firstDivisor = createPartialCalculationDivisors(divisionsContainer).get(0);
		String firstDivident = createPartialCalculationDividents(divisionsContainer).get(0);
		int requiredDashsBeforeDivisor = firstDivident.length() - firstDivisor.length() + 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, requiredDashsBeforeDivisor), " ")));
		consoleOutputString.append(firstDivisor);
		int requiredDashsAfterDivisor = calculateLengthBeforeSlash - firstDivisor.length() - requiredDashsBeforeDivisor;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, requiredDashsAfterDivisor), " ")));
		consoleOutputString.append("|");
		consoleOutputString
				.append(String.join("", Collections.nCopies(Math.max(0, calculateRinghtLineTwoDashAmmout(divisionsContainer)), "-")));
		return consoleOutputString.toString();
	}

	private int calculateRinghtLineTwoDashAmmout(DivisionsContainer divisionsContainer) {
		int possibleMaxAsDivisorLength = Integer.toString(divisionsContainer.getDivisor()).length();
		int possibleMaxDivResultLength = Integer.toString(divisionsContainer.getDividend() / divisionsContainer.getDivisor()).length();
		int maxAmmout = Math.max(possibleMaxAsDivisorLength, possibleMaxDivResultLength);
		if (divisionsContainer.getNegativeIntHandler() > 0) {
			maxAmmout += 1;
		}
		return maxAmmout;
	}

	private String handleThirdLineOfExpression(DivisionsContainer divisionsContainer) {
		StringBuilder consoleOutputString = new StringBuilder();
		consoleOutputString.append(" ");
		String firstDivident = createPartialCalculationDividents(divisionsContainer).get(0);
		consoleOutputString.append(String.join("", Collections.nCopies(firstDivident.length(), "-")));
		int calculateLengthBeforeSlash = String.valueOf(divisionsContainer.getDividend()).length() + 1;
		int calculateRequiredDashSize = calculateLengthBeforeSlash - firstDivident.length() - 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, calculateRequiredDashSize), " ")));
		consoleOutputString.append("|");
		int divResult = divisionsContainer.getDividend() / divisionsContainer.getDivisor();
		for (int i = 0; i < divisionsContainer.getNegativeIntHandler(); i++) {
			divResult *= -1;
		}
		consoleOutputString.append(Integer.toString(divResult));
		return consoleOutputString.toString();
	}

	private String handleAllDivisionCalculation(DivisionsContainer divisionsContainer) {
		return printDivisionProcess(divisionsContainer);
	}

	private String printDivisionProcess(DivisionsContainer divisionsContainer) {
		StringBuilder consoleOutputString = new StringBuilder();
		int dashAmount = 0;
		int lastDash = 1;
		List<String> dividentList = createPartialCalculationDividents(divisionsContainer);
		List<String> divisorList = createPartialCalculationDivisors(divisionsContainer);
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

	private void zeroCheckCase(int divisor) {
		if (divisor == 0) {
			throw new IllegalArgumentException();
		}
	}

	private boolean dividendSmallerThenDivisorCheck(DivisionsContainer divisionsContainer) {
		return divisionsContainer.getDividend() < divisionsContainer.getDivisor();
	}

	private String handleSmallDividendCase(DivisionsContainer divisionsContainer) {
		StringBuilder consoleOutputString = new StringBuilder();
		consoleOutputString.append(handleFirstLineOfExpression(divisionsContainer) + NEWLINE);

		int dashsRequiredBeforeSlash = String.valueOf(divisionsContainer.getDividend()).length() + 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, dashsRequiredBeforeSlash), " ")));
		consoleOutputString.append("|");
		int lineTwoDashsRequired = calculateRinghtLineTwoDashAmmout(divisionsContainer);
		if (divisionsContainer.getNegativeIntHandler() == 2) {
			lineTwoDashsRequired -= 1;
		}
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, lineTwoDashsRequired), "-")));
		consoleOutputString.append(NEWLINE);

		int numberOfVerticalSymbolsForDivResult = String.valueOf(divisionsContainer.getDividend()).length();
		consoleOutputString.append(" ");
		consoleOutputString.append(String.join("", Collections.nCopies(numberOfVerticalSymbolsForDivResult, "-")));
		int lineThreeDashMaxPossibleSize = String.valueOf(divisionsContainer.getDividend()).length() + 1;
		int lineThreeRequiredDashSize = lineThreeDashMaxPossibleSize - numberOfVerticalSymbolsForDivResult - 1;
		consoleOutputString.append(String.join("", Collections.nCopies(Math.max(0, lineThreeRequiredDashSize), " ")));
		consoleOutputString.append("|" + Integer.toString(divisionsContainer.getDividend() / divisionsContainer.getDivisor()) + NEWLINE);

		consoleOutputString.append(" " + Integer.toString(divisionsContainer.getDividend() % divisionsContainer.getDivisor()));
		return consoleOutputString.toString();
	}

	private void handleNegatives(DivisionsContainer divisionsContainer) {
		if (divisionsContainer.getDividend() < 0) {
			divisionsContainer.setDividend(Math.abs(divisionsContainer.getDividend()));
			divisionsContainer.setNegativeIntHandler(divisionsContainer.getNegativeIntHandler() + 1); 
		}

		if (divisionsContainer.getDivisor() < 0) {
			divisionsContainer.setDivisor(Math.abs(divisionsContainer.getDivisor())); 
          divisionsContainer.setNegativeIntHandler(divisionsContainer.getNegativeIntHandler() +1);
		}
	}

	private String handleFirstThreeLinesEnum(int line, DivisionsContainer divisionsContainer) {
		String returnString = "";
		if (line == 0) {
			returnString = handleFirstLineOfExpression(divisionsContainer);
		}
		if (line == 1) {
			returnString = handleSecondLineOfExpression(divisionsContainer);
		}
		if (line == 2) {
			returnString = handleThirdLineOfExpression(divisionsContainer);
		}
		return returnString;
	}

}