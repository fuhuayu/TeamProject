package OverallGame;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Game1.CrabCatcherGameTest;
import Game2Test.TestGame2;
import Game2Test.TestMovingObject;
import Game3.Game3Tests;
import Game3.MusselTests;
import Game3.PlantTests;
import Game3.RunoffTests;

@RunWith(Suite.class)
@SuiteClasses({ CrabCatcherGameTest.class, TestGame2.class, TestMovingObject.class, Game3Tests.class, MusselTests.class, PlantTests.class, RunoffTests.class, OverallGameTest.class })
public class AllTests {

}
