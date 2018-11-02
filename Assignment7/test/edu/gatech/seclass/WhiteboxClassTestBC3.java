package edu.gatech.seclass;

import org.junit.Before;
import org.junit.Test;


public class WhiteboxClassTestBC3 {

    private WhiteboxClass whiteboxClass;

    @Before
    public void setUp(){whiteboxClass = new WhiteboxClass();}

    @Test
    public void whiteboxTestBC3(){
        whiteboxClass.whiteboxMethod3(1);
        whiteboxClass.whiteboxMethod3(5);
    }

}
