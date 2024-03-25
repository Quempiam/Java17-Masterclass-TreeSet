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
                seatSet.add(new Seat(row, j++));
            }
            row++;
        } // if numberOfRows == 0, seatSet will be empty
        System.out.println("-".repeat(20));
        System.out.printf("%s theatre has %d seats located in %d rows.", name, seatSet.size(), numberOfRows);
    }

    public void printSeatMap() {
        Iterator<Seat> seatIterator = seatSet.iterator();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.printf("%s ", seatIterator.next());
            }
        }
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
