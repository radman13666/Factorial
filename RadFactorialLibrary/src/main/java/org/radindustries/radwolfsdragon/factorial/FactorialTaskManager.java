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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

class FactorialTaskManager {
  
  private ExecutorService executor;
  
  FactorialTaskManager() {}

  BigInteger[] calculateFactorialPartialProducts(Limits[] setOfLimits) {

    BigInteger[] products = new BigInteger[setOfLimits.length];
    List<Future<BigInteger>> results;
    
    try {

      executor = Executors.newFixedThreadPool(setOfLimits.length);
      results = getResultsFromAsyncTasks(setOfLimits);

      for (int k = 0; k < setOfLimits.length; k++) {
        Future<BigInteger> future = results.get(k);
        BigInteger result = future.get();
        products[k] = result;
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      executor.shutdownNow();
    }

    return products;
  }

  private List<Future<BigInteger>> getResultsFromAsyncTasks(
    Limits[] limits
  ) throws InterruptedException, RejectedExecutionException {
    ArrayList<FactorialAsyncTask> tasks = new ArrayList<>();

    for (int i = 0; i < limits.length; i++) {
      FactorialPartialProduct product
        = new FactorialPartialProduct(limits[i]);

      tasks.add(new FactorialAsyncTask(product));
    }

    return executor.invokeAll(tasks);
  }
}
