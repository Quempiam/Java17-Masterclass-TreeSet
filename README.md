# Java17 Masterclass - TreeSet
## The task is to manage theatre seats. To fill the requirements I need to do the following:
- create *Theatre* class with set of *Seats*
- *Seat* should be a nested class
- *Seat* constructor should take row character and seat number integer (ex. 'B', 15)
- *Seat* should have a *String* type field, representing seat number in format "B015"
    and boolean type field indicating reservation
>
- *Theatre* class should have three fields: name, seats in row number and set of seats
- Constructor should take name, number of seats in row and total number of seats in theatre
  (for simplicity assume that there is equal number of seats in each row 
  and there is maximum 26 rows, so they can be labeled from A to Z)
- Create *printSeatMap()* method which does what it says and prints seat numbers 
    in separate lines for each row, showing which seats are reserved
- Create *reserveSeat()* method for reserving a single seat
