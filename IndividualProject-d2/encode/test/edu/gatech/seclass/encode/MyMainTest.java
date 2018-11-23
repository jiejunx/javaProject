package edu.gatech.seclass.encode;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyMainTest {

    /*
    Place all  of your tests in this class, optionally using MainTest.java as an example.
    */
    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    /*
     *  TEST UTILITIES
     */

    // Create File Utility
    private File createTmpFile() throws Exception {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }

    // Write File Utility
    private File createInputFile(String input) throws Exception {
        File file =  createTmpFile();

        OutputStreamWriter fileWriter =
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);

        fileWriter.write(input);

        fileWriter.close();
        return file;
    }


    //Read File Utility
    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /*
     * TEST FILE CONTENT
     */
    private static final String FILE1 = "abcyzde";
    private static final String FILE2 = "Today is my 29th birthday,\n" +
            "I am going to have a birthday cake!";
    private static final String FILE3 = " abc2018!";
    private static final String FILE4 = "abCD2018!";
    private static final String FILE5 = " abcxyz!";
    private static final String FILE6 = "";
    private static final String FILE7 = "a";
    private static final String FILE8 = "ABCXYZ";
    private static final String FILE9 = "AB###%%@@CXYZ";



    // test cases

    /*
     *   TEST CASES
     */

    // Purpose: Test Case1, check -r and -l method can not use together
    // Frame #: Test Case 1
    @Test
    public void mymainTest1() throws Exception {
        File inputFile1 = createInputFile(FILE1);
        String args[]  = {"-r", "5", "-l", "3", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>", errStream.toString().trim());
    }

    // Purpose: Test Case 2, check if -n, -l, -c can work together
    // Frame #: Test Case 2
    @Test
    public void mymainTest2() throws Exception {
        File inputFile16 = createInputFile(FILE1);
        String args[]  = {"-n","3", "-l","2","c","abc", inputFile16.getPath()};
        Main.main(args);
        String expected16 = "06020307080405";
        String actual16 = getFileContent(inputFile16.getPath());
        assertEquals("The files differ!", expected16, actual16);
    }

    // Purpose: Test Case 3, check if -n, -l, -c can work together
    // Frame #: Test Case 3
    @Test
    public void mymainTest3() throws Exception {
        File inputFile17 = createInputFile(FILE1);
        String args[]  = {inputFile17.getPath()};
        Main.main(args);
        String expected17 = "14151612131718";
        String actual17 = getFileContent(inputFile17.getPath());
        assertEquals("The files differ!", expected17, actual17);
    }

    // Purpose: Test Case4, check error if there is no input integer for -n, -r or -l method
    // Frame #: Test Case 4
    @Test
    public void mymainTest4() throws Exception {
        File inputFile13 = createInputFile(FILE1);
        String args[]  = {"-n","3", "-l", inputFile13.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>", errStream.toString().trim());
    }



    // Purpose: Test Case 5, check if -n without a number can work
    // Frame #: Test Case 5
    @Test
    public void mymainTest5() throws Exception {
        File inputFile18 = createInputFile(FILE1);
        String args[]  = {inputFile18.getPath()};
        Main.main(args);
        String expected18 = "14151612131718";
        String actual18 = getFileContent(inputFile18.getPath());
        assertEquals("The files differ!", expected18, actual18);
    }

    // Purpose: Test Case6, check error if input integer is 0 for -l or -r method
    // Frame #: Test Case 6
    @Test
    public void mymainTest6() throws Exception {
        File inputFile14 = createInputFile(FILE1);
        String args[]  = {"-n","3", "-l","0", inputFile14.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>", errStream.toString().trim());
    }


    // Purpose: Test Case 7, check if -n 0 works
    // Frame #: Test Case 7
    @Test
    public void mymainTest7() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-n","0",inputFile.getPath()};
        Main.main(args);
        String expected = "01020325260405";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Test Case 8, check error if [int] > 25 for -n method
    // Frame #: Test Case 8
    @Test
    public void mymainTest8() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-n","28",inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>", errStream.toString().trim());
    }


    // Purpose: Test Case 9, check error if input integer is smaller than 0
    // Frame #: Test Case 9
    @Test
    public void mymainTest9() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-n","28",inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>", errStream.toString().trim());
    }

    // Purpose: Test Case 10, input String content for -c is empty
    // Frame #: Test Case 10
    @Test
    public void mymainTest10() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-c",inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>", errStream.toString().trim());
    }
    // Purpose: Test Case11, check if there is no alphabetical letters for input string content for -c method
    // Frame #: Test Case 11
    @Test
    public void mymainTest11() throws Exception {
        File inputFile15 = createInputFile(FILE1);
        String args[]  = {"-c", "123!459", inputFile15.getPath()};
        Main.main(args);
        String expected15 = "abcyzde";
        String actual15 = getFileContent(inputFile15.getPath());
        assertEquals("The files differ!", expected15, actual15);
    }



    // Purpose: Test if input string content with Uppercase can pass the -c operation
    // Frame #: Test Case 12
    @Test
    public void mymainTest12() throws Exception {
        File inputFile2 = createInputFile(FILE2);

        String args[] = {"-c", "AC", inputFile2.getPath()};
        Main.main(args);
        String expected2= "TodAy is my 29th birthdAy,\n" +
                "I Am going to hAve A birthdAy CAke!";

        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected2, actual2);
    }

    // Purpose: Test Case 13, input String content for -c with lowercase
    // Frame #: Test Case 13
    @Test
    public void mymainTest13() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-c","aDY", inputFile.getPath()};
        Main.main(args);
        String expected = "AbcYzDe";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }
    // Purpose: Test Case 14, input String content for -c with spaces
    // Frame #: Test Case 14
    @Test
    public void mymainTest14() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-c","B D y", inputFile.getPath()};
        Main.main(args);
        String expected = "aBcYzDe";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Test Case 15, input String content for -c with spaces
    // Frame #: Test Case 15
    @Test
    public void mymainTest15() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-c","B D y"};
        Main.main(args);
        assertEquals("File Not Found", errStream.toString().trim());
    }

    // Purpose: Test Case 16, inputFile empty content,return empty content
    // Frame #: Test Case 16
    @Test
    public void mymainTest16() throws Exception {
        File inputFile = createInputFile(FILE6);
        String args[]  = {"-r","1","-c","B D y", inputFile.getPath()};
        Main.main(args);
        String expected = "";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }

    // Purpose: Test Case 17, input file content with length = 1
    // Frame #: Test Case 17
    @Test
    public void mymainTest17() throws Exception {
        File inputFile = createInputFile(FILE7);
        String args[]  = {"-r","1","-c","A D y",inputFile.getPath()};
        Main.main(args);
        String expected = "A";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }


    // Purpose: Test Case 20, input file content are all uppercase characters
    // Frame #: Test Case 20
    @Test
    public void mymainTest18() throws Exception {
        File inputFile = createInputFile(FILE8);
        String args[]  = {"-r","1","-c","A D y",inputFile.getPath()};
        Main.main(args);
        String expected = "ZaBCXy";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }


    // Purpose: Test Case 21, input file with special characters
    // Frame #: Test Case 21
    @Test
    public void mymainTest19() throws Exception {
        File inputFile = createInputFile(FILE9);
        String args[]  = {"-r","1","-c","A D y",inputFile.getPath()};
        Main.main(args);
        String expected = "ZaB###%%@@CXy";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
    }


    // Purpose: Test Case 22, input method with -n, interger >0 , file content lowercase character
    // Frame #: Test Case 22
    @Test
    public void mymainTest20() throws Exception {
        File inputFile = createInputFile(FILE1);
        String args[]  = {"-n","5","-c",inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>", errStream.toString().trim());

    }



    // Purpose: Test if -n can work for file with mixed Uppercase and lowercase alphanumeric characters
    // Frame #: Test Case 24
    @Test
    public void mymainTest21() throws Exception {
        File inputFile3 = createInputFile(FILE4);
        String args[] = {"-n", "3", inputFile3.getPath()};
        Main.main(args);
        String expected3 = "040532332018!";

        String actual3 = getFileContent(inputFile3.getPath());

        assertEquals("The files differ!", expected3, actual3);
    }

    // Purpose: Test if -r can work for file with mixed Uppercase and lowercase alphanumeric characters
    // Frame #: Test Case 26
    @Test
    public void mymainTest22() throws Exception {
        File inputFile4 = createInputFile(FILE4);

        String args[] = {"-r", "5", inputFile4.getPath()};
        Main.main(args);

        String expected4 = "2018!abCD";

        String actual4 = getFileContent(inputFile4.getPath());

        assertEquals("The files differ!", expected4, actual4);
    }

    // Purpose: Test if -l can work for file with lowercase characters
    // Frame #: Test Case 28
    @Test
    public void mymainTest23() throws Exception {
        File inputFile5 = createInputFile(FILE1);

        String args[] = {"-l", "2", inputFile5.getPath()};
        Main.main(args);

        String expected5 = "cyzdeab";

        String actual5 = getFileContent(inputFile5.getPath());

        assertEquals("The files differ!", expected5, actual5);
    }


    // Purpose: Test if -n, -r, -c can work for file with lowercase characters, integer >0, String with alphabetical letters
    // Frame #: Test Case 34
    @Test
    public void mainTest24() throws Exception {
        File inputFile6 = createInputFile(FILE1);

        String args[] = {"-n", "2", "-r", "2", "-c", "abc", inputFile6.getPath()};
        Main.main(args);

        String expected5 = "06072930310102";

        String actual5 = getFileContent(inputFile6.getPath());

        assertEquals("The files differ!", expected5, actual5);
    }


    // Purpose: Test if -n, -r can work for file with mixed Uppercase and lowercase alphanumeric characters
    // Frame #: Test Case 38
    @Test
    public void mainTest25() throws Exception {
        File inputFile7 = createInputFile(FILE4);

        String args[] = {"-n", "2", "-r", "2", inputFile7.getPath()};
        Main.main(args);
        String expected7 = "8!03043132201";
        String actual7 = getFileContent(inputFile7.getPath());
        assertEquals("The files differ!", expected7, actual7);
    }

    // Purpose: Test if -n, -r can work for file with spaces
    // Frame #: Test Case 39
    @Test
    public void mainTest26() throws Exception {
        File inputFile8 = createInputFile(FILE3);

        String args[] = {"-n", "5", "-r", "5", inputFile8.getPath()};
        Main.main(args);

        String expected8 = "2018! 060708";

        String actual8 = getFileContent(inputFile8.getPath());

        assertEquals("The files differ!", expected8, actual8);
    }

    // Purpose: Test if -n, -l can work for file with lowercase characters
    // Frame #: Test Case 40
    @Test
    public void mainTest27() throws Exception {
        File inputFile9 = createInputFile(FILE1);

        String args[] = {"-n", "3", "-l", "5", inputFile9.getPath()};
        Main.main(args);

        String expected9 = "07080405060203"; // error before

        String actual9 = getFileContent(inputFile9.getPath());

        assertEquals("The files differ!", expected9, actual9);
    }

    // Purpose: Test if -n, -c can work for file with lowercase characters
    // Frame #: Test Case 43
    @Test
    public void mainTest28() throws Exception {
        File inputFile10 = createInputFile(FILE1);

        String args[] = {"-n", "3", "-c", "cde", inputFile10.getPath()};
        Main.main(args);

        String expected10 = "04053202033334";//error before

        String actual10 = getFileContent(inputFile10.getPath());

        assertEquals("The files differ!", expected10, actual10);
    }

    // Purpose: Test if -n, -c can work for file with mixed uppercase and lowercase characters
    // Frame #: Test Case 44
    @Test
    public void mainTest29() throws Exception {
        File inputFile11 = createInputFile(FILE4);
        String args[] = {"-n", "4", "-c", "abe", inputFile11.getPath()};
        Main.main(args);
        String expected11 = "313233342018!";
        String actual11 = getFileContent(inputFile11.getPath());
        assertEquals("The files differ!", expected11, actual11);
    }

    // Purpose: Test if -n, -c can work for file with spaces????
    // Frame #: Test Case 45
    @Test
    public void mainTest30() throws Exception {
        File inputFile11 = createInputFile(FILE3);

        String args[] = {"-n", "4", "-c", "abe", inputFile11.getPath()};
        Main.main(args);

        String expected12 = " 3132072018!";

        String actual12 = getFileContent(inputFile11.getPath());

        assertEquals("The files differ!", expected12, actual12);
    }




}
