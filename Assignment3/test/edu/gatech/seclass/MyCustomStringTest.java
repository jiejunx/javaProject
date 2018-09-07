package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Junit test class created for use in Georgia Tech CS6300.
 *
 * This is an test class for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * You should implement your tests in this class.  Do not change the test names.
 */

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    //Test Purpose: This is the first instructor example test.
    @Test
    public void testCountDuplicates1() {
        mycustomstring.setString("People are sleeping... Zzz.");
        assertEquals(4, mycustomstring.countDuplicates());
    }
    //Test Purpose: test if the string is null.
    @Test
    public void testCountDuplicates2() {
        mycustomstring.setString(null);
        assertEquals(0, mycustomstring.countDuplicates());
    }

    //Test Purpose: test when the string is empty.
    @Test
    public void testCountDuplicates3() {
        mycustomstring.setString("");
        assertEquals(0, mycustomstring.countDuplicates());
        
    }

    //Test Purpose: test Count Duplicate, with no duplicate.
    @Test
    public void testCountDuplicates4() {
        mycustomstring.setString("This is a dog");
        assertEquals(0, mycustomstring.countDuplicates());
    }

    //Test Purpose: test Count Duplicate with duplicated punctuation
    @Test
    public void testCountDuplicates5() {

        mycustomstring.setString("//**!!new,...year");
        assertEquals(5, mycustomstring.countDuplicates());
    }

    //Test Purpose: test Count Duplicate with duplicated punctuation and annotations.
    @Test
    public void testCountDuplicates6() {
        mycustomstring.setString("happiness is important!!! Be happy!###//");
        assertEquals(8, mycustomstring.countDuplicates());
    }

    //Test Purpose: This is the second instructor example test.
    @Test
    public void testAddDigits1() {
        mycustomstring.setString("1234!!! H3y, l3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("5678!!! H7y, l7t'9 put 94me d505ts in this 9tr5n0!55!5", mycustomstring.addDigits(4, true));
    }

    //Test Purpose: This the third instructor example test.
    @Test
    public void testAddDigits2() {
        mycustomstring.setString("1234!!! H3y, l3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("7890!!! H9y, l9t'1 put 16me d727ts in this 1tr7n2!77!7", mycustomstring.addDigits(4, false));
    }

    //Test Purpose: test AddDigit when n is large and positive is false.
    @Test
    public void testAddDigits3() {

        mycustomstring.setString("1234!!! H3y, l3t'5 put 50me d161ts in this 5tr1n6!11!1");
        assertEquals("2345!!! H4y, l4t'6 put 61me d272ts in this 6tr2n7!22!2", mycustomstring.addDigits(9, false));
    }

    //Test Purpose: test exception for the method(NullPointerException)
    @Test(expected = NullPointerException.class)
    public void testAddDigits4() { mycustomstring.addDigits(2, false); }

    //Test Purpose: test exception for the method(IllegalArgument Exception if n>9 or n<=0, current string != Null)
    @Test(expected = IllegalArgumentException.class)
    public void testAddDigits5() {
        mycustomstring.setString("123newstring, this is //newstring#@");
        mycustomstring.addDigits(0, false);
    }

    //Test Purpose: test exception for the method(IllegalArgument Exception if n>9 or n<=0, current string != Null)
    @Test(expected = IllegalArgumentException.class)
    public void testAddDigits6() {
        mycustomstring.setString("new year plan yea!!!!");
        mycustomstring.addDigits(10, false);
    }

    // test Adddigit when there is no digit,but with a lot of annotations.
    @Test
    public void testAddDigits7() {

        mycustomstring.setString("abcd#$no&&!!!!");
        assertEquals("abcd#$no&&!!!!", mycustomstring.addDigits(4, false));
    }

    // test when there is no digit
    @Test
    public void testAddDigits8() {

        mycustomstring.setString("new year plan yea!!!!");
        assertEquals("new year plan yea!!!!", mycustomstring.addDigits(4, false));
    }

    // test additions for a sequence
    @Test
    public void testAddDigits9() {

        mycustomstring.setString("12345678!!!!,new98765");
        assertEquals("56789012!!!!,new32109", mycustomstring.addDigits(4, true));
    }

    //Test Purpose: test exception for the method(IllegalArgument Exception  n<=0, current string not Null)
    @Test(expected = IllegalArgumentException.class)
    public void testAddDigits10() {

        mycustomstring.setString("123newstring, this is //newstring#@");
        mycustomstring.addDigits(-10, false);    }

    //Test Purpose:test subtraction for a sequence of numbers
    @Test
    public void testAddDigits11() {

        mycustomstring.setString("12345678!!!!,new98765");
        assertEquals("23456789!!!!,new09876", mycustomstring.addDigits(9, false));
    }

    //Test Purpose:test Nullpointerexception if n is not well set.
    @Test(expected = NullPointerException.class)
    public void testAddDigits12() { mycustomstring.addDigits(-2, true);    }


    //Test Purpose: This is the fourth instructor example test.
    @Test
    public void testFlipLetttersInSubstring1() {
        mycustomstring.setString("H3y, l3t'5 put 50me D161ts in this 5tr1n6!11!!");
        mycustomstring.flipLetttersInSubstring(18, 30);
        assertEquals("H3y, l3t'5 put 50ni s161tD em this 5tr1n6!11!!", mycustomstring.getString());
    }

    //Test Purpose: This is an instructor example test to demonstrate testing for an exception.
    @Test(expected = NullPointerException.class)
    public void testFlipLetttersInSubstring2() {
        mycustomstring.flipLetttersInSubstring(200, 100);
    }

    //Test Purpuse: test if there is no letters in substring
    @Test
    public void testFlipLetttersInSubstring3() {
        mycustomstring.setString("this is 135886898435 her number");
        mycustomstring.flipLetttersInSubstring(9, 20);
        assertEquals("this is 135886898435 her number", mycustomstring.getString());
    }

    //Test Purpose: test for the MyIndexOutOfBoundsException.
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testFlipLetttersInSubstring4() {
        mycustomstring.setString("this is 135886898435 her number");
        mycustomstring.flipLetttersInSubstring(0, 50);    }

    //Test Purpose: test for the MyIndexOutOfBoundsException.
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testFlipLetttersInSubstring5() {
        mycustomstring.setString("this is her number");
        mycustomstring.flipLetttersInSubstring(0, 10);    }

    //Test Purpose: test for the IllegalArgumentException.
    @Test(expected = IllegalArgumentException.class)
    public void testFlipLetttersInSubstring6() {
        mycustomstring.setString("this is her number");
        mycustomstring.flipLetttersInSubstring(10, 1);    }

    //Test Purpose: test for the MyIndexOutOfBoundsException when the illegalargument exits.
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testFlipLetttersInSubstring7() {

        mycustomstring.setString("this is her number");
        mycustomstring.flipLetttersInSubstring(300, 200);    }

    //Test Purpose:  test flip letters when there is special annotations between letters.
    @Test
    public void testFlipLetttersInSubstring8() {

        mycustomstring.setString("this is my email: 123happynewyear@163.com &&//***");
        mycustomstring.flipLetttersInSubstring(12, 30);
        assertEquals("this is my yweny: 123ppahliameear@163.com &&//***", mycustomstring.getString());
    }


    //Test Purpose: test flip letters  when there is special annotations.
    @Test
    public void testFlipLetttersInSubstring9() {

        mycustomstring.setString("this is &&//***");
        mycustomstring.flipLetttersInSubstring(5, 12);
        assertEquals("this si &&//***", mycustomstring.getString());
    }

    //Test Purpose: test flip letters with special annotations.
    @Test
    public void testFlipLetttersInSubstring10() {


        mycustomstring.setString("the sis &&//***");
        mycustomstring.flipLetttersInSubstring(1, 12);
        assertEquals("sis eht &&//***", mycustomstring.getString());  }

}
