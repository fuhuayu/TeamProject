package OverallGame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Game1.CrabCatcherGameTest;
import Game2.Game2Test;
import Game2.JumpingBarTest;
import Game2.MovingObjectTest;
import Game3.Game3Tests;
import Game3.MusselTests;
import Game3.PlantTests;
import Game3.RunoffTests;

@RunWith(Suite.class)
@SuiteClasses({ CrabCatcherGameTest.class, Game2Test.class, MovingObjectTest.class,JumpingBarTest.class, Game3Tests.class, MusselTests.class, PlantTests.class, RunoffTests.class, OverallGameTest.class })
public class AllTests {

}
