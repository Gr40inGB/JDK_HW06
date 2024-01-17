package org.gr40in;

import org.junit.Test;

public class MainTest {
    @Test
    public void test(){
        assert Main.changeChoice(0, 1) == 2;
        assert Main.changeChoice(1, 0) == 2;
        assert Main.changeChoice(2, 1) == 0;
        assert Main.changeChoice(1, 2) == 0;
        assert Main.changeChoice(0, 2) == 1;
        assert Main.changeChoice(2, 0) == 1;
    }
}
