package edu.gatech.seclass;

import org.junit.Before;
import org.junit.Test;


public class WhiteboxClassTestSC3 {

    private WhiteboxClass whiteboxClass;

    @Before
    public void setUp(){whiteboxClass = new WhiteboxClass();}

    @Test
    public void whiteboxTestSC3(){
        whiteboxClass.whiteboxMethod3(4);
    }

}
