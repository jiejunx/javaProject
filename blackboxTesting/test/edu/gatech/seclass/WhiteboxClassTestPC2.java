package edu.gatech.seclass;

import org.junit.Before;
import org.junit.Test;


public class WhiteboxClassTestPC2 {

    private WhiteboxClass whiteboxClass;

    @Before
    public void setUp(){whiteboxClass = new WhiteboxClass();}

    @Test
    public void whiteboxTestPC2(){
        whiteboxClass.whiteboxMethod2(3,2);
        whiteboxClass.whiteboxMethod2(2,2);
    }

}
