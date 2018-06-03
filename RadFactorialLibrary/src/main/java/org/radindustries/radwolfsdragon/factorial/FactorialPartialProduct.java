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

class FactorialPartialProduct {
  
  private final Limits limits;

  FactorialPartialProduct(Limits limits) {
    this.limits = limits;
  }

  BigInteger get() {
    BigInteger result = BigInteger.ONE;

    for (
      BigInteger factor = limits.lowerLimit;
      factor.compareTo(limits.upperLimit) < 1; // factor <= upperLimit
      factor = factor.add(BigInteger.ONE) // factor++
    )
      result = result.multiply(factor);

    return result;
  }
}
