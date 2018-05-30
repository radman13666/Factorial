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
import org.radindustries.factorial.core.InvalidInputException;
import org.radindustries.factorial.core.InputTooSmallException;

public class RadFactorialCalculatorTest {
  
  public RadFactorialCalculatorTest() {}

  @Test
  public void calculateSmallFactorialInBase10() 
    throws InvalidInputException, InputTooSmallException {
    RadFactorialCalculator calc = new RadFactorialCalculator();
    String result = calc.calculateFactorial("20");
    String expResult = "2432902008176640000";

    assertTrue(result + " is not equal to " + expResult,
      result.equals(expResult)
    );
  }

  @Test
  public void calculateSmallFactorialInBase16() 
    throws InvalidInputException, InputTooSmallException {
    RadFactorialCalculator calc = new RadFactorialCalculator();
    String result = calc.calculateFactorial("20", "16");
    String expResult = "32ad5a155c6748ac18b9a580000000";

    assertTrue(result + " is not equal to " + expResult,
      result.equals(expResult)
    );
  }
}
