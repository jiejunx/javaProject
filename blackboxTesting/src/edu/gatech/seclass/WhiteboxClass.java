package edu.gatech.seclass;

public class WhiteboxClass {

    public void whiteboxMethod1(){
        //it is impossible to create a method that contains a
        // division by zero fault that it can
        // create a test suite that achieves 100% branch coverage and does not reveal the fault,
        // and (2) every test suite that achieves 100% statement coverage
        // reveals the fault
        // because branch coverage is stronger and includes statement coverage.
        // If a test suite could achieve 100% branch coverage, it will
        // achieve 100% statement coverage as well
        // Thus, it is impossible that every test suite that achieves 100% statement
        // coverage reveals the fault.
}


    public void whiteboxMethod2(int a, int b){

        if(a/b == 1){
            System.out.print(a+b);}
        else {
            System.out.print(2);}

    }



    public void whiteboxMethod3(int a){

        int x = 0;
        int y = 2;

        if(a>3) {
            x += 1 ;
        }
        System.out.print(y/x);}







    public void whiteboxMethod4(){
        // it is impossible to create a method meeting both requirements
        // The first requirement is(1): it is possible to create a test suite
        // with less than 100% statement coverage that does find the fault.
        // and second requirement(2) every test suite that achieves 100% statement coverage
        // does not reveal the fault
        // the first requirement is included in the second requirement.
        // since (2) says "every" test suite that achieves 100% statement
        // coverage, it should include tests in （1）
        // Thus, it is impossible to create a method meeting both requirements.



        }

    public boolean whiteboxMethod5 (boolean a, boolean b) {
        int x = 2;
        int y = 4;
        if(a)
            x = x*2;
        else
            b = !b;
        if(b)
            y -= x;
        else
            x -= y;
        return ((x/y)>= 1);
    }


// ================
//
    // Fill in column “output” with T, F, or E:
    //
// | a | b |output|
// ================
// | T | T | E |
// | T | F |  F    |
// | F | T |  F    |
// | F | F |  T    |
// ================
//
// Fill in the blanks in the following sentences with
// “NEVER”, “SOMETIMES” or “ALWAYS”:
//
// Test suites with 100% statement coverage _SOMETIMES____ reveal the fault in this method.
// Test suites with 100% branch coverage _SOMETIMES____ reveal the fault in this method.
// Test suites with 100% path coverage ___ALWAYS_________ reveal the fault in this method.
// ================

    }






