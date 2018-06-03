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
import org.radindustries.factorial.core.FactorialCalculator;
import org.radindustries.factorial.core.InvalidInputException;
import org.radindustries.factorial.core.InputTooSmallException;


class RadFactorialCalculator implements FactorialCalculator {
  
  RadFactorialCalculator() {}

  @Override
  public String calculateFactorial(String number)
    throws InvalidInputException, InputTooSmallException {
    return calculateFactorial(number, "10");
  }

  @Override
  public String calculateFactorial(String number, String radix)
    throws InvalidInputException, InputTooSmallException {

    int r = Integer.parseInt(radix);
    BigInteger input = new BigInteger(number, r);
    if (input.compareTo(BigInteger.ONE) == -1) // input < 1
      throw new InvalidInputException();

    Limits[] limitsForPartialProducts
      = formPartialProductsSetAsLimits(input);
    
    BigInteger[] partialProducts
      = evaluatePartialProducts(limitsForPartialProducts);

    BigInteger factorial
      = evaluateFinalProduct(partialProducts);

    return factorial.toString(r);
  }

  private Limits[] formPartialProductsSetAsLimits(BigInteger number) 
    throws InputTooSmallException {
    BigInteger numOfPartialProducts
      = chooseNumOfPartialProductsToEvaluate(number);

    return chooseLimits(number, numOfPartialProducts);
  }
  
  private BigInteger chooseNumOfPartialProductsToEvaluate(BigInteger number) 
    throws InputTooSmallException {
    
    if (number.toString().length() < 2)
      throw new InputTooSmallException();

    BigInteger lowerBound = BigInteger.TEN;
    BigInteger upperBound = BigInteger.TEN;
    BigInteger q = BigInteger.valueOf(2);
    
    try {
      int n = 2;
      while(true) {
        if( // 10^(n-1) <= number < 10^n
          number.compareTo(lowerBound.pow(n - 1)) > -1 &&
          number.compareTo(upperBound.pow(n)) == -1
        ) {
          q = q.pow(n);
          break;
        } else {
          n = n + 1;
        }
      }
    } catch (ArithmeticException e) {
      e.printStackTrace();
    }
    
    return q;
  }

  private Limits[] chooseLimits(BigInteger number, BigInteger q) {
    
    BigInteger b = BigInteger.ONE;
    BigInteger e = BigInteger.ONE;
    try {
      b = number.divide(q);
      e = number.mod(q);
    } catch (ArithmeticException ex) {
      ex.printStackTrace();
    }

    Limits[] chosenLimits
       = e.compareTo(BigInteger.ZERO) == 1 ? // e > 0
       new Limits[q.intValue() + 1] :
       new Limits[q.intValue()]; 

    for (int m = 0; m < q.intValue(); m++) {
      BigInteger lowerLimit // = (b * m) + 1
        = b.multiply(BigInteger.valueOf(m)).add(BigInteger.ONE); 
      BigInteger upperLimit // = b * (m + 1) 
        = b.multiply(BigInteger.valueOf(m + 1));

      chosenLimits[m] = new Limits();
      chosenLimits[m].lowerLimit = lowerLimit;
      chosenLimits[m].upperLimit = upperLimit;
    }
    
    if (e.compareTo(BigInteger.ZERO) == 1) { // if e > 0
      chosenLimits[q.intValue()] = new Limits();
      chosenLimits[q.intValue()].lowerLimit // = (q * b) + 1
        = q.multiply(b).add(BigInteger.ONE);
      chosenLimits[q.intValue()].upperLimit = number;
    }

    return chosenLimits;
  }

  private BigInteger[] evaluatePartialProducts(Limits[] limits) {
    FactorialTaskManager taskManager = new FactorialTaskManager();
    return taskManager.calculateFactorialPartialProducts(limits);
  }

  private BigInteger evaluateFinalProduct(BigInteger[] partialProducts) {
    BigInteger finalProduct = BigInteger.ONE;
    for(BigInteger product : partialProducts) {
      finalProduct = finalProduct.multiply(product);
    }
    return finalProduct;
  }

}
