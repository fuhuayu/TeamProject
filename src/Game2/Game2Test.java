package Game2;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class Game2Test {

	@Test
	public void test() throws InterruptedException {
		RipRapGame r=new RipRapGame(0, 90);
		TimeUnit.SECONDS.sleep(1);
		r.updateTime();
		assertEquals(89,r.getTime());
	}

}
