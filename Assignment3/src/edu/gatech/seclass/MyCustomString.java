package edu.gatech.seclass;

import java.io.CharArrayWriter;


public class MyCustomString implements MyCustomStringInterface{

    private String string;

    /**
     * Returns the current string.
     * If the string is null, or has not been set to a value, it should return null.
     *
     * @return Current string
     */
    public String getString() {return string;}


    public void setString(String string){
        this.string = string;
        }



    /**
     * Returns the number of duplicated characters.
     * This will count the number of characters that are immediately followed by another
     * identical character, even if the pairs overlap.
     *
     * If the current string is null or empty, the method should return 0.
     *
     * Examples:
     * - countDoubles would return 4 for string "Billy has 111 apples".
     *
     * @return Number of doubled characters in the current string
     */
    public int countDuplicates(){


        int count=0;

        if(string == null){
            count = 0;
        }
        else if (string.equals("")) {
            count = 0;
        }


        else {

        for(int i=0; i<string.length()-1;i++)
        {
            char c = string.charAt(i);

            if(string.charAt(i) == string.charAt(i+1))
            {
                count = count + 1;
            }

        }   }

        return count;

    }

    /**
     * Returns a string equivalent to the original string with n or -n added to every digit in the string.
     * If 'positive' is true, n is added to every digit.
     * If 'positive' is false, n is subtracted.
     * The digit will wrap from 9 to 0 or 0 to 9 whenever it passes those values.
     *
     * Examples:
     * - For n=2 and positive=true, "hello 90, bye 2" would be converted to "hello 12, bye 4".
     * - For n=4 and positive=true, "1234567890" would be converted to "5678901234".
     * - For n=2 and positive=false, "hello 90, bye 2" would be converted to "hello 78, bye 0".
     * - For n=4 and positive=false, "1234567890" would be converted to "7890123456".
     *
     * @param n amount to increment or decrement every digit
     * @param positive Boolean that indicates n should be treated as positive or negative
     * @return String with the original string's digits incremented or decremented by n
     * @throws NullPointerException     If the current string is null
     * @throws IllegalArgumentException If n > 9 or n <=0 (and the current string is not null)
     */
    public String addDigits(int n, boolean positive) {

        String string = this.string;
        char arr[] = string.toCharArray();
        char[] newarr = new char[string.length()];
        int REDIX=10;

        if (string == null)
        { throw new NullPointerException("The current string is null");}

        else if ((n > 9) || (n <= 0))
        { throw new IllegalArgumentException("N could not be more than 9 or smaller than 0"); }
        else {
            for (int i = 0; i < string.length(); i++) {
                if (Character.isDigit(arr[i])) {
                    if (positive == true) {
                        int num = Character.getNumericValue(arr[i]);
                        int addn = num + n;
                        int number = addn % 10;
                        newarr[i] = Character.forDigit(number,REDIX);
                               // (Integer.toString(number)).charAt(0);
                    }

                    if (positive == false) {
                        int num = Character.getNumericValue(arr[i]) - n;
                        if (num < 0){ num = 10 + num; }
                        int number = num % 10;
                        newarr[i] = Character.forDigit(number,REDIX);
                    }

                }
                else {
                    newarr[i] = arr[i];
                }
            }
            }

        String newstr = new String(newarr);


        return newstr;

    }

    /**
     * Reverses the position of all letters (i.e. a-z and A-Z) in the current string, between startPosition and endPosition (both inclusive).
     * The first letter will become the last, and the last will become the first.  All other characters remain unchanged.
     * *Note: The first character in the string is considered to be in Position 1.
     *
     * Examples:
     * - String "Rat" would be converted to "taR"
     * - String "3 Cats, 2 Dogs." would be converted to "3 sgoD, 2 staC."
     *
     * @param startPosition Position of the first character to consider
     * @param endPosition   Position of the last character to consider
     * @throws NullPointerException        If the current string is null
     * @throws MyIndexOutOfBoundsException If endPosition is > the length of the string or startPosition <= 0
     *                                     (and the current string is not null)
     * @throws IllegalArgumentException    If startPosition > endPosition
     *                                     (and the current string is not null, and neither position is out of bounds)
     */
    public void flipLetttersInSubstring(int startPosition, int endPosition){

        char newarr[] = new char[string.length()];
        StringBuilder newstring= new StringBuilder();


        if (string == null)
        {
            throw new NullPointerException("The current string is null");
        }

        else if ((endPosition > string.length()) || (startPosition <= 0))
        {
            throw new MyIndexOutOfBoundsException();
        }

        else if (startPosition > endPosition){
            throw new IllegalArgumentException();
        }

        else{
            int count = 0;
            char arr[] = string.toCharArray();

            for (int i = startPosition-1; i < endPosition; i++) {

                if (Character.isLetter(arr[i])) {
                    //letterArray.append((char)i);
                    count = count +1;
                }
            }

            int[] letterArray = new int[count];

            int num = 0;
            for (int i = 0; i < string.length(); i++) {

                if ((i >= startPosition - 1) && (i < endPosition)) {
                    if
                    (Character.isLetter(arr[i])) {
                       // System.out.print(num+"  array  " + i + "  ");
                        letterArray[num] = i;
                        num = num + 1;
                    }
                    else{newarr[i] = arr[i];}
                }

                else {
                    newarr[i] = arr[i];
                }
            }


            if (count > 0){

                for (int j = 0; j < count; j++){

                    newarr[letterArray[count-j-1]]=arr[letterArray[j]];

                }
            }

            }


        this.string = new String(newarr);

    }
}



