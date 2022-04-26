package com.kalachev.task4;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DivisionMakerTest {
	
	@Test
	void divide_ShouldReturnCorrectResult_whenDividentIsZero() {
	    int dividend = 0;
        int divisor = 5;
        String expected = String
                .format("_0|5%n" +
                        "  |-%n" +
                		" -|0%n" +
                        " 0");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}	
	
		
	@Test
	void divide_shouldReturnIlligalArgumentException_whenDividerIsZero() {
		 int dividend = 5;
	     int divisor = 0;	     
	     assertThrows(IllegalArgumentException.class, () -> DivisionMaker.divide(dividend, divisor));
	}
	
	@Test
	void divide_shouldReturnReminder_whenDividendIsSmallerThenDivider() {
	    int dividend = 4;
        int divisor = 5;
        String expected = String
                .format("_4|5%n" +
                        "  |-%n" +
                		" -|0%n" +
                        " 4");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}

	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreTheSameWithSameLengthsAndAreOneDigits() {
	    int dividend = 5;
        int divisor = 5;
        String expected = String
                .format("_5|5%n" +
                        " 5|-%n" +
                		" -|1%n" +
                        " 0");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}	
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreTheSameWithSameLengthsAndAreTwoDigits() {
		int dividend = 15;
        int divisor = 15;
        String expected = String
                .format("_15|15%n" +
                        " 15|--%n" +
                		" --|1%n" +
                        "  0");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreTheSameWithSameLengthsAndAreMultipleDigits() {
		int dividend = 20000;
        int divisor = 20000;
        String expected = String
                .format("_20000|20000%n" +
                        " 20000|-----%n" +
                		" -----|1%n" +
                        "     0");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreDifferentWithSameLengthsAndAreOneDigits() {
	    int dividend = 8;
        int divisor = 2;
        String expected = String
                .format("_8|2%n" +
                        " 8|-%n" +
                		" -|4%n" +
                        " 0");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}	
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreDifferentWithSameLengthsAndAreTwoDigits() {
		int dividend = 99;
        int divisor = 33;
        String expected = String
                .format("_99|33%n" +
                        " 99|--%n" +
                		" --|3%n" +
                        "  0");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreDifferentWithSameLengthsAndAreMultipleDigits() {
		int dividend = 50000;
        int divisor = 10000;
        String expected = String
                .format("_50000|10000%n" +
                        " 50000|-----%n" +
                		" -----|5%n" +
                        "     0");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
		
		
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreOneDifferentDigitWithReminder() {
	    int dividend = 5;
        int divisor = 3;
        String expected = String
                .format("_5|3%n" +
                        " 3|-%n" +
                		" -|1%n" +
                        " 2");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreTwoDifferentDigitWithReminder() {
	    int dividend = 15;
        int divisor = 13;
        String expected = String
                .format("_15|13%n" +
                        " 13|--%n" +
                		" --|1%n" +
                        "  2");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsAreMultipleDifferentDigitWithReminder() {
	    int dividend = 15000;
        int divisor = 2149;
        String expected = String
                .format("_15000|2149%n" +
                        " 12894|----%n" +
                		" -----|6%n" +
                        "  2106");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
	@Test
	void divide_ShouldReturnCorrectResult_whenInputsHaveTwoDivisionCycles() {
	    int dividend = 120;
        int divisor = 7;
        String expected = String
                .format("_120|7%n" +
                        "  7 |--%n" +
                		" -- |17%n" +
                        " _50%n" +
                		"  49%n" +
                        "  --%n" +
                		"   1");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
	@Test
	public void divide_shouldReturnStringSolution_whenDividendAndDivisorAreAcceptable() {
        int dividend = 118889911;
        int divisor = 99;

        String expected = String
                .format("_118889911|99%n" +
                        "  99      |-------%n" +
                        " ---      |1200908%n" +
                        " _198%n" +
                        "  198%n" +
                        "  ---%n" +
                        "    _899%n" +
                        "     891%n" +
                        "     ---%n" +
                        "      _811%n" +
                        "       792%n" +
                        "       ---%n" +
                        "        19");
        String actual = DivisionMaker.divide(dividend, divisor);
        assertEquals(expected, actual);
	}
	
   @Test
   public void divide_shouldReturnStringSolution_whenDivisionIsTwoCycleAndNoReminder() {
       int dividend = 145;
       int divisor = 5;

       String expected = String
               .format("_145|5%n" +
                       " 10 |--%n" +
               		   " -- |29%n" +
                       " _45%n" +
               		   "  45%n" +
                       "  --%n" +
               		   "   0");
       String actual = DivisionMaker.divide(dividend, divisor);
       assertEquals(expected, actual);
	}
   
   @Test
   public void divide_shouldReturnStringSolution_whenInputsAreBigAndLotsOfCycles() {
       int dividend = 6984112;
       int divisor = 998;

       String expected = String
               .format("_6984112|998%n" +
                       " 5988   |----%n" +
               		   " ----   |6998%n" +
                       " _9961%n" +
               		   "  8982%n" +
                       "  ----%n" +
               		   "  _9791%n" +
                       "   8982%n"+ 
                       "   ----%n" +
                       "   _8092%n" +
                       "    7984%n" +
                       "    ----%n" +
                       "     108");
       String actual = DivisionMaker.divide(dividend, divisor);
       assertEquals(expected, actual);
	}
   
   @Test
   public void divide_shouldReturnStringSolution_whenDivisorHasMultipleZeros() {
       int dividend = 30001200;
       int divisor = 3;

       String expected = String
               .format("_30001200|3%n" +
                       " 3       |--------%n" +
               		   " -       |10000400%n" +
                       "    _12%n" +
               		   "     12%n" +
                       "     --%n" +
               		   "      0");
       String actual = DivisionMaker.divide(dividend, divisor);
       assertEquals(expected, actual);
	}
   
   @Test
   public void divide_shouldReturnStringSolution_whenDivisorHasMultipleZeros2() {
       int dividend = 70000007;
       int divisor = 7;

       String expected = String
               .format("_70000007|7%n" +
                       " 7       |--------%n" +
               		   " -       |10000001%n" +
                       "       _7%n" +
               		   "        7%n" +
                       "        -%n" +
               		   "        0");
       String actual = DivisionMaker.divide(dividend, divisor);
       assertEquals(expected, actual);
	}
   
   @Test
   public void divide_shouldReturnStringSolution_whenDivisorHasMultipleZerosAndDivisorIsBig() {
       int dividend = 20394990;
       int divisor = 198;

       String expected = String
               .format("_20394990|198%n" +
                       " 198     |------%n" +
               		   " ---     |103005%n" +
                       "  _594%n" +
               		   "   594%n" +
                       "   ---%n" +
               		   "     _990%n" +
                       "      990%n" +
               		   "      ---%n" +
                       "        0");
       String actual = DivisionMaker.divide(dividend, divisor);
       assertEquals(expected, actual);
	}
   
   @Test
	void divide_shouldReturnReminder_whenDividentIsNegative() {
	    int dividend = -8;
       int divisor = 2;
       String expected = String
               .format("_8|-2%n" +
                       " 8|--%n" +
               		   " -|-4%n" +
                       " 0");
       String actual = DivisionMaker.divide(dividend, divisor);
       assertEquals(expected, actual);
	}
   @Test
   void divide_shouldReturnReminder_whenDivisorIsNegative() {
	    int dividend = 8;
      int divisor = -2;
      String expected = String
              .format("_8|-2%n" +
                      " 8|--%n" +
              		  " -|-4%n" +
                      " 0");
      String actual = DivisionMaker.divide(dividend, divisor);
      assertEquals(expected, actual);
	}
   
   @Test
   void divide_shouldReturnReminder_whenDividentAndDivisorAreNegative() {
	    int dividend = -8;
      int divisor = -2;
      String expected = String
              .format("_8|2%n" +
                      " 8|--%n" +
              		  " -|4%n" +
                      " 0");
      String actual = DivisionMaker.divide(dividend, divisor);
      assertEquals(expected, actual);
	}
   
   @Test
	void divide_shouldDealWithNegativeDivident_whenDividendIsSmallerThenDivider() {
	    int dividend = -4;
       int divisor = 5;
       String expected = String
               .format("_4|-5%n" +
                       "  |--%n" +
               		   " -|0%n" +
                       " 4");
       String actual = DivisionMaker.divide(dividend, divisor);
       assertEquals(expected, actual);
	}
   
   @Test
	void divide_shouldDealWithNegativeDivisor_whenDividendIsSmallerThenDivider() {
	    int dividend = 4;
      int divisor = -5;
      String expected = String
              .format("_4|-5%n" +
                      "  |--%n" +
              		   " -|0%n" +
                      " 4");
      String actual = DivisionMaker.divide(dividend, divisor);
      assertEquals(expected, actual);
	}
   
   @Test
	void divide_shouldDealWithNegativeDivisorAndDivident_whenDividendIsSmallerThenDivider() {
	    int dividend = -4;
     int divisor = -5;
     String expected = String
             .format("_4|5%n" +
                     "  |-%n" +
             		 " -|0%n" +
                     " 4");
     String actual = DivisionMaker.divide(dividend, divisor);
     assertEquals(expected, actual);
	}
}
