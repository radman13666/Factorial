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
package org.radindustries.factorial.app;

import org.radindustries.radwolfsdragon.factorial.Injector;
import org.radindustries.factorial.core.FactorialCalculator;

public class Factorial {
  public static void main(String[] args) {
    try {
      FactorialCalculator calc = Injector.newFactorialCalculator();
      String result = calc.calculateFactorial(args[0], args[1]);
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
