# FACTORIAL

This is a small Java command-line application that calculates the factorial
of a given positive integer. The radix of the integer must be 
specified and the factorial calculated will be in the specified radix as well.

For example, to calculate the factorial of ```0xc0ffee``` 
(which is represented in radix 16), execute ```$ ./FactorialApp c0ffee 16```.
The factorial result will also be in radix 16.

The application uses a divide-conquer-combine approach to solve the problem.
The factorials are calculated as a product of smaller partial products, each
calculated in a separate thread and then combined to form the solution.

### NOTE
This application is meant to work on large numbers. 
Any positive integer less than 10 is ignored.
