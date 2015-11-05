package OverallGame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Game2.CrabCatcherGameTest;
import Game1.Game1Test;
import Game1.JumpingBarTest;
import Game1.MovingObjectTest;
import Game3.Game3Tests;
import Game3.MusselTests;
import Game3.PlantTests;
import Game3.RunoffTests;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * Test all tests at once
 */
@RunWith(Suite.class)
@SuiteClasses({ CrabCatcherGameTest.class, Game1Test.class, MovingObjectTest.class,JumpingBarTest.class, Game3Tests.class, MusselTests.class, PlantTests.class, RunoffTests.class, OverallGameTest.class })
public class AllTests {

}
