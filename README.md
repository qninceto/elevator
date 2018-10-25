# elevator

## Simple single threaded app, representing the work of an elevetor.

### INPUT:
First line represent an elevator`s starting floor
Next line is a number M that is the number of people
Next M lines represent every person - space separated parameters (starting floor, desired floor, weight)

if the elevator is empty it checks the closest floor request from the outside and strts moving in that direction untill it gets to the treshold floor or gets empty again. The application is terminated when all of the requests are executed.
