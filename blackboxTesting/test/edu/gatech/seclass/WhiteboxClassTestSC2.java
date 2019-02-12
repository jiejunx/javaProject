package edu.gatech.seclass;

import org.junit.Before;
import org.junit.Test;


public class WhiteboxClassTestSC2 {

    private WhiteboxClass whiteboxClass;

    @Before
    public void setUp(){whiteboxClass = new WhiteboxClass();}

    @Test
    public void whiteboxTestSC2(){
        whiteboxClass.whiteboxMethod2(1,0);
    }

}
