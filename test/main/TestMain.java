
package main;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TestMain {
	
	@Test
    public void testMainInstantiation() {
        Main main = new Main();
        assertNotNull(main);
    }

}
