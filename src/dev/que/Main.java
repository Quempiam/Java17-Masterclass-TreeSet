package dev.que;

public class Main {

    public static void main(String[] args) {
        Theatre theatreABC = new Theatre("ABC", 20, 60);
        theatreABC.printSeatMap();

        Theatre theatreAtoL = new Theatre("A to L", 15, 183);
        theatreAtoL.printSeatMap();

        Theatre emptyTheatre = new Theatre("Empty", -5, -2);
        emptyTheatre.printSeatMap();

        operationInfo(theatreABC.reserveSeat("C005", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("C005", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("C005", false));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("C-05", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("C--5", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("CA05", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("b020", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("C025", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("d005", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("C05", true));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("a1", false));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("a005", false));
        theatreABC.printSeatMap();

        operationInfo(theatreABC.reserveSeat("%005", true));
        theatreABC.printSeatMap();
    }

    private static void operationInfo(boolean result){
        System.out.println("Operation " + (result ? "successful." : "unsuccessful."));
    }
}
