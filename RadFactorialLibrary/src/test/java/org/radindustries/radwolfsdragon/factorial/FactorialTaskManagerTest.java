/*
 * Copyright (C) 2018 William Kibirango <williamkaos.kibirango76@gmail.com> 
 *
 * This file is part of Factorial.
 * 
 * Factorial is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Factorial is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Factorial.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.radindustries.radwolfsdragon.factorial;

import java.math.BigInteger;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class FactorialTaskManagerTest {
  
  public FactorialTaskManagerTest() {}

  @Test
  public void testManagerExecution() {
    Limits firstLimits = new Limits();
    firstLimits.lowerLimit = BigInteger.valueOf(20);
    firstLimits.upperLimit = BigInteger.valueOf(25);

    Limits secondLimits = new Limits();
    secondLimits.lowerLimit = BigInteger.valueOf(26);
    secondLimits.upperLimit = BigInteger.valueOf(30);

    Limits thirdLimits = new Limits();
    thirdLimits.lowerLimit = BigInteger.valueOf(15);
    thirdLimits.upperLimit = BigInteger.valueOf(19);

    Limits[] setOfLimits = new Limits[] {
      firstLimits, secondLimits, thirdLimits
    };

    FactorialTaskManager taskManager = new FactorialTaskManager();
    BigInteger[] result
      = taskManager.calculateFactorialPartialProducts(setOfLimits);

    BigInteger[] expResult = new BigInteger[] {
      BigInteger.valueOf(127512000),
      BigInteger.valueOf(17100720),
      BigInteger.valueOf(1395360)
    };

    for (int i = 0; i < setOfLimits.length; i++) {
      assertTrue(result[i] + " is not equal to " + expResult[i],
        result[i].compareTo(expResult[i]) == 0);
    }
  }

  /*private Limits[] generateRandomLimits(int numOfLimitsToGenerate) {
    Limits[] limits = new Limits[numOfLimitsToGenerate];

    for (int i = 0; i < numOfLimitsToGenerate; i++) {
      long generatedLowerLimit = 1 + ((long) Math.random() * 100);
      long generatedUpperLimit = lowerLimit + 10;

      limits[i] = new Limit();
      limits[i].lowerLimit = generatedLowerLimit;
      limits[i].upperLimit = generatedUpperLimit;
    }

    return limits;
  }*/
}
