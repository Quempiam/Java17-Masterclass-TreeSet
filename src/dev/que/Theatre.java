package dev.que;

import java.util.*;

public class Theatre {
    private String name;
    private int seatsPerRow;
    private int numberOfRows;
    private NavigableSet<Seat> seatSet;

    public Theatre(String name, int seatsPerRow, int numberOfSeats) {
        this.name = name;

        if (seatsPerRow > 0) {
            this.seatsPerRow = seatsPerRow;
        }
        else {
            this.seatsPerRow = 1;
            System.out.println("Number of seats per row must not be less than 1.\n" +
                    "It has been set to 1.");
        }

        if (numberOfSeats < 1) {
            numberOfSeats = 1;
            System.out.println("Number of seats in theatre must not be less than 1.\n" +
                    "It has been set to 1.");
        }
        seatSet = new TreeSet<>(Seat.seatComparator);
        numberOfRows = numberOfSeats / this.seatsPerRow;
        if (numberOfSeats % this.seatsPerRow != 0){
            System.out.println("Number of seats should be multiple of number of seats per row.\n" +
                    "Excess seats has been ignored");
        }
        if (numberOfRows > 26) {
            numberOfRows = 26;
            System.out.println("Number of rows has been reduced to 26");
        }
        char row = 'A';
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 1; j <= this.seatsPerRow; j++) {
                seatSet.add(new Seat(row, j));
            }
            row++;
        } // if numberOfRows == 0, seatSet will be empty
        System.out.println("-".repeat(20));
        System.out.printf("%s theatre has %d seats located in %d rows.\n", name, seatSet.size(), numberOfRows);
    }

    public void printSeatMap() {
        System.out.println("-".repeat(20));
        System.out.println("Seats in Theatre " + name + " :");

        Iterator<Seat> seatIterator = seatSet.iterator();
        for (int i = 0; i < numberOfRows; i++) {
            System.out.printf("%c  ", (char) 65 + i);
            for (int j = 0; j < seatsPerRow; j++) {
                Seat seat = seatIterator.next();
                System.out.printf("%s%c ", seat.getSeatID(),
                        seat.isReserved() ? '☑' : '☐');
            }
            System.out.print("\n");
        }
        System.out.println("-".repeat(20));
    }

    public boolean reserveSeat(String seatID, boolean reserve) {
        if (seatID == null) {
            System.out.println("SeatID cannot be null.");
            return false;
        }
        if (seatID.length() != 4){
            System.out.println("Incorrect seatID format.");
            return false;
        }
        char row = seatID.toUpperCase().charAt(0);
        if (row < 'A' || row > 'Z') {
            System.out.println("Incorrect row character");
            return false;
        }
        try {
            int number = Integer.parseInt(seatID.substring(1));
            Seat requestedSeat = new Seat(row, number);
            Seat seatToReserve = seatSet.floor(requestedSeat);
            if (requestedSeat.equals(seatToReserve)) {
                if (seatToReserve.isReserved() == reserve) {
                    System.out.println("Seat " + seatToReserve.getSeatID() + " is already " +
                            (reserve ? "reserved." : "not reserved."));
                    return false;
                }
                else {
                    seatToReserve.setReserved(reserve);
                    System.out.println("Reservation of seat " + seatToReserve.getSeatID() +
                            " has been " + (seatToReserve.isReserved() ? "set." : "canceled"));
                    return true;
                }
            }
            else {
                System.out.println("Requested seat (" + requestedSeat.getSeatID() + ") has not been found.");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Incorrect number format.");
        }
        return false;
    }

    private class Seat{
        private final char row;
        private final int number;
        private final String seatID;
        private boolean reserved;
        public static Comparator<Seat> seatComparator = Comparator.comparing(Seat::getRow)
                .thenComparing(Seat::getNumber);

        public Seat(char row, int number) {
            this.row = Character.toUpperCase(row);
            this.number = number;
            this.seatID = String.format("%c%03d", this.row, number);
            this.reserved = false;
        }

        public char getRow() {
            return row;
        }

        public int getNumber() {
            return number;
        }

        public String getSeatID() {
            return seatID;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void setReserved(boolean reserved) {
            this.reserved = reserved;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Seat seat = (Seat) o;
            return row == seat.row && number == seat.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, number);
        }
    }
}
