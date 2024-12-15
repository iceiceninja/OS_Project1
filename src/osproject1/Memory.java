
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package osproject1;
/**
 *
 * @author iceiceninja
 */
public class Memory {

    static int[] memory = new int[2000];
    static PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        //  TODO: READ IN FILE. IF LINE STARTS WITH . OR A DIGIT THEN READ UNTIL WHITE SPACE.
        //      THEN MOVE TO NEXT LINE. OTHERWISE SKIP LINE AND START READING NEXT
        try {
            try (Scanner scanner = new Scanner(new File(args[0]))) {
                int counter = 0;
                while (scanner.hasNextLine()) {
                    var line = scanner.nextLine();
                    if ((line.length() > 0) && (line.charAt(0) == '.' || Character.isDigit(line.charAt(0)))) {                       
//                        pw.write("memAddr[" + counter + "]: " + line + "\n");
                        String command = line.replaceAll("\\s+", " ").split(" ")[0];
//                        pw.write(command + "\n");
//                        command

                        if (line.charAt(0) == '.') {

                            counter = Integer.parseInt(line.replace(".", ""));
                        } else {
//                        pw.write(testline);
                            write(counter, Integer.parseInt(command)); //Will eventually have to handle non-Ints
                            counter++;
                        }
                    }
                }
//                while (scanner.hasNextInt()) {
//
////                    System.out.println(scanner.nextLine());
////                  memory[counter] = scanner.nextInt();
//                    write(counter, scanner.nextInt());
//                    counter++;
//                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        // Signal readiness to the parent process
        Scanner sc = new Scanner(System.in);    //After this line memory can start recieving commands from the parent
//         pw.write("memory at 0 is " + memory[0] + "\n");
//        pw.flush();
        pw.write("Done \n");    //This signals the parent process that the child is done reading the file in
        pw.flush();
        int memoryAddress = 0;

        while (sc.hasNext()) {
            //  Reading from memory
            var cpuRequest = sc.nextLine();
            if (cpuRequest.split(" ")[0].equals("w")) {//"w".equals(cpuRequest)
//                int stackPointer = Integer.parseInt(sc.nextLine());
//                int data = Integer.parseInt(sc.nextLine());
//                write(stackPointer,data);
                write(Integer.parseInt(cpuRequest.split(" ")[1]),Integer.parseInt(cpuRequest.split(" ")[2]));
            } else {
                memoryAddress = Integer.parseInt(cpuRequest);//sc.nextInt();
                read(memoryAddress);
            }
        }

    }

    static public void read(int address) {
        System.out.println(memory[address]);
    }

    static public void write(int address, int data) {
//         pw.write(address + " " + data + " \n");
//            pw.flush();
        memory[address] = data;
    }

}
