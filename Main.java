import java.io.File;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {

        //re
        char[][] maze;
        Scanner file = new Scanner(new File("src/maze.txt"));
        int rows = 0;
        int cols = 0;

        // this loop should count the height and width of the maze
        while (file.hasNext()) {
            String line = file.next();
            rows++;
            cols = line.length();
        }

        maze = new char[rows + 2][cols + 2];

        // reset the scanner so we can actually read  the characters into the array
        file = new Scanner(new File("src/maze.txt"));

        // this loop should read all the characters into the array
        for (int r = 1; r < rows + 1; r++) {
            maze[r][0] = 'O';
            maze[r][cols + 1] = 'O';
            String thisRow = file.next();
            for (int c = 1; c < cols + 1; c++) {
                maze[0][c] = 'O';
                maze[rows + 1][c] = 'O';
                maze[r][c] = thisRow.charAt(c - 1);
            }
        }

        //print the maze
        for (int r = 0; r < rows + 2; r++) {
            System.out.println("");
            for (int c = 0; c < cols + 2; c++) {
                System.out.print(maze[r][c]);
            }
        }

        //Find Start @
        //Push Position on Stack
        //Are you done? (You are on the finish $ spot) - if yes then the stack contains your solution
        //If you can Go Up without backtracking, go to #2
        //If you can Go Down without backtracking, go to #2
        //If you can Go Left without backtracking, go to #2
        //If you can Go Right without backtracking, go to #2
        //Pop Position off Stack, go to #2 (if stack is empty then maze is impossible)

        Stack stack = new Stack();

        //find start
        for (int r = 0; r < rows; r++) {
            System.out.println("");
            for (int c = 0; c < cols; c++) {
                if (maze[r][c] == '@') {
                    stack.push(r, c);
                }
            }
        }


        System.out.println("start found at: " + (stack.peek()[0] - 1) + ", " + (stack.peek()[1] - 1));

        //main loop
        //for (int i = 0; i < 90; i++) {
        while (maze[stack.peek()[0]][stack.peek()[1]] != '$') {
            if (stack.peek()[0] != 0 && maze[stack.peek()[0] - 1][stack.peek()[1]] == '.') {
                //go up
                stack.push(stack.peek()[0] - 1, stack.peek()[1]);
                maze[stack.peek()[0]][stack.peek()[1]] = '#';
            } else if (stack.peek()[0] != maze[0].length - 1 && maze[stack.peek()[0] + 1][stack.peek()[1]] == '.') {
                //go down
                stack.push(stack.peek()[0] + 1, stack.peek()[1]);
                maze[stack.peek()[0]][stack.peek()[1]] = '#';
            } else if (stack.peek()[1] != maze.length - 1 && maze[stack.peek()[0]][stack.peek()[1] + 1] == '.') {
                //go right
                stack.push(stack.peek()[0], stack.peek()[1] + 1);
                maze[stack.peek()[0]][stack.peek()[1]] = '#';
            } else if (stack.peek()[1] != 0 && maze[stack.peek()[0]][stack.peek()[1] - 1] == '.') {
                //go left
                stack.push(stack.peek()[0], stack.peek()[1] - 1);
                maze[stack.peek()[0]][stack.peek()[1]] = '#';
            } else if (maze[stack.peek()[0]][stack.peek()[1]] == '$') {
                System.out.println("found the finish");
                break;
            } else {
                if (maze[stack.peek()[0] - 1][stack.peek()[1]] == '$') {
                    stack.push(stack.peek()[0] - 1, stack.peek()[1]);
                    System.out.println("found end at " + (stack.peek()[0] - 1) + " " + (stack.peek()[1] - 1));
                    break;
                } else if (maze[stack.peek()[0] + 1][stack.peek()[1]] == '$') {
                    stack.push(stack.peek()[0] + 1, stack.peek()[1] - 1);
                    System.out.println("found end at " + (stack.peek()[0] - 1) + " " + (stack.peek()[1] - 1));
                    break;
                } else if (maze[stack.peek()[0]][stack.peek()[1] - 1] == '$') {
                    stack.push(stack.peek()[0], stack.peek()[1] - 1);
                    System.out.println("found end at " + (stack.peek()[0] - 1) + " " + (stack.peek()[1] - 1));
                    break;
                } else if (maze[stack.peek()[0]][stack.peek()[1] + 1] == '$') {
                    stack.push(stack.peek()[0], stack.peek()[1] + 1);
                    System.out.println("found end at " + (stack.peek()[0] - 1) + " " + (stack.peek()[1] - 1));
                    break;
                }
                //if you cant go up down left or right
                stack.pop();

            }//else if(maze[stack.peek()[0]][stack.peek()[1]-1] ) {shiloh was here!}


        }
    }
}

