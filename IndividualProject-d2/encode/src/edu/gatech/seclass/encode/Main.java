package edu.gatech.seclass.encode;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {




    public static void main(String[] args ) {

        // loop args array and find if it is in valid form
        // catch error if it is null, length = 0, or without a file
        String actualContent = null;
        String opt;
        String outputString;
        int increaseNum;
        Charset charset = StandardCharsets.UTF_8;
        String inputFile = null;
        int check = 0;

        if (args == null || args.length == 0 || args[args.length-1].charAt(0) == '-') {
            usage();
            return;
        }

        else {
            String inputname = args[args.length-1];
            try {
                inputFile = new String(Files.readAllBytes(Paths.get(inputname)), charset);
            }
            catch(IOException e){
                fileNotFound();
                return;
        }
    }
        String argsopt[]={"-r","-l","-n","-d", "-c"};
        int checkErr = 0;
        if (args.length>1){
            if(!Arrays.asList(argsopt).contains(args[0])){
                usage();
                return;
            }
            for(int i=0; i<args.length-1;i++){

                if(isNumeber(args[i])){
                    if(i==0){ // is it possible true??
                        usage();
                        return;
                    }
                    if(i!=args.length-1 && Arrays.asList(argsopt).contains(args[i-1])==false){
                        usage();
                        return;
                    }
                    int numToCheck = Integer.parseInt(args[i]);
                    if(numToCheck < 0){
                        usage();
                        return;
                    }

                }
                if(args[i] == "-d"){
                    if (!isNumeber(args[i+1])){
                        usage();
                        return;
                    }}
                if(args[i]=="-r"|| args[i] =="-l"){
                    checkErr += 1;
                    if (!isNumeber(args[i+1])){
                        usage();
                        return;
                    }

                    int rotateInt = Integer.parseInt(args[i+1]);
                    if (isNumeber(args[i+1])&& (rotateInt>inputFile.length()||rotateInt < 1) ){
                        usage();
                        return;
                    }

                }
                if(checkErr==2){
                    usage();
                    return;
                }

                if(args[i].equals("-c")){//-c has to follow with some text
                    if(i+1>=args.length-1){
                        usage();
                        return;
                    }
                }

                if(args[i].equals("-n")){//-c has to follow with some text
                    if(i+1<args.length-1 && isNumeber(args[i+1])){
                        int nInput = Integer.parseInt(args[i+1]);
                        if(nInput>25 || nInput<0){
                        usage();
                        return;
                        }
                    }
                }
            }
        }

        FileWriter fw = null;
        PrintWriter printer = null;

        try {
            fw = new FileWriter(args[args.length -1 ]);

        }catch(IOException e){
            e.printStackTrace();
        }
        printer = new PrintWriter(fw);
        printer.write(encodeStr(inputFile,args));
        printer.flush();
        printer.close();

/*
        System.out.println("start a new line!!!!!!!");

        String input = "a bcde";
        int num = 3;
        Main.nToNum(input, 3);
        System.out.println(Main.nToNum(input, 3));
        System.out.println(Main.rightToString(input, 3));
        System.out.println(Main.leftToString(input, 1));
        System.out.println(Main.convertLetter(input, "abe ew"));
*/
    }


    private static void usage() {
        System.err.println("Usage: encode [-n [int]] [-r int | -l int] [-c string] [-d int] <filename>");
    }

    private static void fileNotFound() {
        System.err.println("File Not Found");
    }



    private static boolean isNumeber(String input) {
        int check = 0;
        for (int j = 0; j < input.length(); j++) {
            if (!Character.isDigit(input.charAt(j))) {
                check = 1;
                break;
            }
        }
        if (check == 0) {
            return true;
        } else {
            return false;
        }
    }



    private static String nToNum(String input, int num) {
        //get char for input and transform to array
        // find digit in the array and encode digit
        // Char array to String
        ArrayList<String> encodeArr = new ArrayList<String>();
        for (int i = 0; i < input.length(); i++) {
            int position = 0;
            Character newchar = input.charAt(i);
            if (Character.isLetter(newchar)) {
                //encode this char
                if (newchar >= 'A' && newchar <= 'Z') {
                    position = (int) newchar - 'A' + 1 + 26;
                    position = position + num;
                    if (position > 52) {
                        position = position - 26;
                    }
                }
                if (newchar >= 'a' && newchar <= 'z') {
                    position = (int) newchar - 'a' + 1;
                    position = position + num;
                    if (position > 26) {
                        position = position - 26;
                    }
                }
                String positionStr = Integer.toString(position);
                if(positionStr.length()==1){
                    positionStr = "0" + positionStr;
                }
                encodeArr.add(positionStr);
            } else {
                encodeArr.add(Character.toString(newchar));
            }
        }
        StringBuilder builder = new StringBuilder();
        for (String s : encodeArr) {
            builder.append(s);
        }
        String str = builder.toString();
        return str;
    }


    private static String rightToString(String input, int num) {
        num = num%(input.length());

        String newString = input;

        if( num == input.length()) ;
        {
            newString = input;
        }
        if (num < input.length() && num > 0) {
            String subStr1 = input.substring((input.length() - num), input.length());

            String subStr2 = input.substring(0, input.length() - num);
            newString = subStr1 + subStr2;
        }
        return newString;
    }

    private static String leftToFile(String input, int num){
        ArrayList<String> lineList = new ArrayList<>();
        ArrayList<Integer> delimList = new ArrayList<>();
        String outputString;
        int count = 0;
        for(int i=0; i<input.length();i++){
            count += 1;
            if(input.charAt(i)=='\n'|| input.charAt(i)=='\r'){
                delimList.add(i);
            }
        }
        String lines[] = input.split("\\r|\\n");

        for(int i=0; i<lines.length;i++){
            lines[i] = leftToString(lines[i],num);
            if(i<delimList.size()){
                lines[i] += input.charAt(delimList.get(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s:lines){
            sb.append(s);
        }
        return sb.toString();
    }


    private static String rightToFile(String input, int num){
        ArrayList<String> lineList = new ArrayList<>();
        ArrayList<Integer> delimList = new ArrayList<>();
        String outputString;
        int count = 0;
        for(int i=0; i<input.length();i++){
            count += 1;
            if(input.charAt(i)=='\n'|| input.charAt(i)=='\r'){
                delimList.add(i);
            }
        }
        String lines[] = input.split("\\r|\\n");
        for(int i=0; i<lines.length;i++){
            lines[i] = rightToString(lines[i],num);
            if(i<delimList.size()){
                lines[i] += input.charAt(delimList.get(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String s:lines){
            sb.append(s);
        }
        return sb.toString();
    }


    private static String leftToString(String input, int num) {
        String newString = input;
        num = num%(input.length());


        if (num == input.length()) ;
        {
            newString = input;
        }
        if (num < input.length() && num > 0) {

            String subStr1 = input.substring(0, num);
            String subStr2 = input.substring(num, input.length());
            newString = subStr2 + subStr1;
        }
        return newString;
    }




    private static String deleteChar(String input, int num) {
        String newString = input;
        char[] charArr = newString.toCharArray();
        String outPutString;
        ArrayList <Character> charOutPut = new ArrayList<Character>();
        if (charArr.length< num+1){
            outPutString = input;
        }

        else{
            for (int k = 0; k < num; k++){
                charOutPut.add(charArr[k]);
            }
            for (int j = num; j < charArr.length; j++){
                int count = 1;
                for(int i = 0; i < j; i++){
                    if (charArr[i]== charArr[j]){
                        count += 1;
                    }
                    }
                if(count <= num){
                    charOutPut.add(charArr[j]);
                }
            }
            StringBuilder builder = new StringBuilder(charOutPut.size());
            for(Character ch: charOutPut)
            {
                builder.append(ch);
            }
            outPutString = builder.toString();

    }
        return outPutString;
    }

    private static boolean equalsCharIgonreCase(char input1, char input2) {

        // char to String
        String inputStr1 = Character.toString(input1);
        String inputStr2 = Character.toString(input2);
        if (inputStr1.equalsIgnoreCase(inputStr2)) {
            return true;
        } else {
            return false;
        }
    }

    private static char convertChar(char input) {
        char outputChar;
        if (Character.isUpperCase(input)) {
            outputChar = Character.toLowerCase(input);
        } else {
            outputChar = Character.toUpperCase(input);
        }
        return outputChar;
    }


    // need change
    private static String convertLetter(String text, String inputStr) {
        // input string must not be empty and not null no space
        ArrayList<Character> convertCase = new ArrayList<Character>(text.length());
        char output;
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char outputChar = textChar;
            for (int j = 0; j < inputStr.length(); j++) {
                char convertLetter = inputStr.charAt(j);
                if (equalsCharIgonreCase(convertLetter, textChar)) {
                    outputChar = convertChar(textChar);
                    break;
                }
            }
            convertCase.add(outputChar);
        }
        StringBuilder builder = new StringBuilder(convertCase.size());
        for (Character ch : convertCase) {
            builder.append(ch);
        }
        return builder.toString();
    }




    private static String encodeStr(String input, String args[]) {
        String outputString = input;
        String inputStr = "";

        for (int i = 0; i < args.length - 1; i++) {
            if(args[i] == "-d"){
                String numStr = args[i + 1];
                int number = Integer.parseInt(numStr);
                outputString = deleteChar(outputString,number);
            }
        }
            for (int i = 0; i < args.length - 1; i++) {
            if (args[i].charAt(0) == '-') {
                // check r l c
                if (args[i].charAt(1) == 'r') {
                        String numStr = args[i + 1];
                        int number = Integer.parseInt(numStr);
                        outputString = rightToFile(outputString, number);
                }
                if (args[i].charAt(1) == 'l') {
                        String numStr = args[i + 1];
                        int number = Integer.parseInt(numStr);
                        outputString = leftToFile(outputString, number);
                }

                if (args[i].charAt(1) == 'c'){
                        inputStr = args[i + 1];

            }

        }}

        if(Arrays.asList(args).contains("-c")){
            outputString = convertLetter(outputString,inputStr);
        }

        if(args.length == 1){
            outputString = nToNum(outputString, 13);
        }

        int increase = 13;
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].charAt(0) == '-' && args[i].charAt(1) == 'n') {
                if (args[i + 1].charAt(0) == '-' || (i + 1) == args.length) {
                    increase = 0;
                    outputString = nToNum(outputString, increase);//
                }

                else {
                    increase = Integer.parseInt(args[i + 1]);
                    if (increase > 25) {
                        usage();
                    } else {
                        outputString = nToNum(outputString, increase);
                        }
                    }
                }
            }
        return outputString;

        }


}