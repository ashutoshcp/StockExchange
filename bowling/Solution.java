package bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.

sq1:
1 0 0 white

sq2
String s2 = "1 0 0 divided\n" +
"2 1 0 white\n" +
"3 1 1 black\n" +
"4 1 2 white\n" +
"5 1 3 black\n";

sq3:
String s3 = "1  0  0 divided\n" +
"2  1  0 white\n" +
"3  1  2 white\n" +
"4  1  1 divided\n" +
"5  1  3 divided\n" +
"6  4  0 white\n" +
"7  4  1 black\n" +
"8  4  2 white\n" +
"9  4  3 black\n" +
"10 5  0 white\n" +
"11 5  1 divided\n" +
"12 5  2 black\n" +
"13 5  3 white\n" +
"14 11 0 white\n" +
"15 11 1 white\n" +
"16 11 2 white\n" +
"17 11 3 black\n";






Given two strings:
Pattern: a%b?c. i
a%
bbbdd
Test String: ah7bc3 j

dp[0][0]=true
dp[1][0]=true
dp[1][1] = true
if p[i] = '%'
  dp[i][j]= dp[i][j-1] || dp[i-1][j]
if p[i]='?'
  dp[i][j]=dp[i-1][j-1]
if p[i] = '.'
  dp[i][j]=dp[i-1][j-1]
else
 if p[i]=s[j]
  dp[i][j]=true
 else
  dp[i][j]=false

res=dp[p.len-1][s.len-1]

Here:
% Means 0 or more characters.
? Means 0 or 1 character.
. Means exactly 1 character.

Output: Write a function to tell true or false validating the “Test String” is a complete match of “Pattern”.



 */

class Square {
    int          id;
    boolean      isDivided = false;
    List<Square> squares   = new ArrayList<Square>();
    boolean      isBlack   = false;
    int          size;
}

class Solution {
    static HashMap<Integer, Square> map = new HashMap<>();

    public static void parseInput(String input) {
        String[] cmds = input.split("\n");
        for (String cmd : cmds) {
            String[] parts = cmd.split("\\s+");

            boolean isDivided = (parts[3]).equalsIgnoreCase("divided");
            boolean isBlack = (parts[3]).equalsIgnoreCase("black");
            createSquare(Integer.valueOf(parts[0]),
                    Integer.valueOf(parts[1]),
                    Integer.valueOf(parts[2]),
                    isDivided,
                    isBlack);
        }

    }

    public static void createSquare(int id, int par, int childNo, boolean isDivided, boolean isBlack) {
        Square square = new Square();
        square.id = id;
        square.isDivided = isDivided;
        square.isBlack = isBlack;
        if (isDivided) {
            square.squares = initializeChilds();
        }
        if (par > 0) {
            map.get(par).squares.add(childNo, square);
        }
        else {
            map.put(id, square);
        }
    }

    public static List<Square> initializeChilds() {
        List<Square> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Square square = new Square();
            list.add(square);
        }
        return list;
    }

    public static void main(String[] args) {
        String
                input =
                "1 0 0 divided"
                        + "\n"
                        + "2 1 0 white"
                        + "\n"
                        + "3 1 1 black"
                        + "\n"
                        + "4 1 2 white"
                        + "\n"
                        + "5 1 3 black";
        parseInput(input);
        Square square1 = map.get(1);
        map.clear();
        input =
                "1  0  0 divided\n"
                        + "2  1  0 white\n"
                        + "3  1  2 white\n"
                        + "4  1  1 divided\n"
                        + "5  1  3 divided\n"
                        + "6  4  0 white\n"
                        + "7  4  1 black\n"
                        + "8  4  2 white\n"
                        + "9  4  3 black\n"
                        + "10 5  0 white\n"
                        + "11 5  1 divided\n"
                        + "12 5  2 black\n"
                        + "13 5  3 white\n"
                        + "14 11 0 white\n"
                        + "15 11 1 white\n"
                        + "16 11 2 white\n"
                        + "17 11 3 black\n";
        parseInput(input);
        Square square2 = map.get(1);
        Square square = add(square1, square2);
        printSquare(square);

    }

    public static void printSquare(Square square) {
        if (square.isDivided) {
            System.out.println("squareId: " + square.id + " isDivided");
            for (Square sq : square.squares) {
                printSquare(sq);
            }
        }
        else {
            System.out.println("squareId: " + square.id + ", color:" + (square.isBlack ? "black" : "white"));
        }
    }

    public static Square add(Square square1, Square square2) {
        if (!square1.isDivided) {
            System.out.println("-1");

            if (square1.isBlack) {
                return square1;
            }
            return square2;
        }
        if (!square2.isDivided) {
            System.out.println("-2");

            if (square2.isBlack) {
                return square2;
            }
            return square1;
        }

        if (square1.isDivided && square2.isDivided) {
            Square resSquare = new Square();
            resSquare.squares = initializeChilds();
            resSquare.isDivided = true;
            for (int i = 0; i < 4; i++) {
                System.out.println(i);
                Square childSquare = add(square1.squares.get(i), square2.squares.get(i));
                resSquare.squares.add(childSquare);
            }
            return resSquare;
        }
        return null;
    }
}
