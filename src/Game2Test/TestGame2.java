package Game2Test;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import Game2.RipRapGame;

public class TestGame2 {

	@Test
	public void test() throws InterruptedException {
		RipRapGame r=new RipRapGame(0, 90);
		TimeUnit.SECONDS.sleep(1);
		r.updateTime();
		assertEquals(89,r.getTime());
	}

}
