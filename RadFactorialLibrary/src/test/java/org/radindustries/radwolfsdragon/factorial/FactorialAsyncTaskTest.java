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

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.math.BigInteger;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

public class FactorialAsyncTaskTest {
  
  public FactorialAsyncTaskTest() {}

  @Test
  public void calculatingPartialProductAsynchronously() 
    throws InterruptedException, ExecutionException {
    Limits testLimits = new Limits();
    testLimits.lowerLimit = BigInteger.valueOf(20);
    testLimits.upperLimit = BigInteger.valueOf(25);
    
    FactorialPartialProduct product = new FactorialPartialProduct(testLimits);

    FactorialAsyncTask testTask = new FactorialAsyncTask(product);

    ExecutorService executor = Executors.newFixedThreadPool(1);
    BigInteger result = executor.submit(testTask).get();
    executor.shutdownNow();

    BigInteger expResult = BigInteger.valueOf(127512000);

    assertTrue(result + " is not equal to " + expResult,
      result.compareTo(expResult) == 0);
  }
}
