
import java.io.*;
import java.lang.Runtime;
import java.util.Scanner;

public class CPU {

    public static void main(String args[]) {
        int PC = 0; // has the memory address that we wish to get
        int SP = 1000; //
        int IR = 0; // holds instruction that was found in memory address
        int AC = 0; // used for math?
        int X = 0;
        int Y = 0;

        int timer = Integer.parseInt(args[1]);
        int instructionsRan = 0;

        try {
            int x;
            Runtime rt = Runtime.getRuntime();

            Process proc = rt.exec("java Memory " + args[0]); // args [0] should be the name of the input file
            //Process proc = rt.exec("cat hello.java");

            InputStream is = proc.getInputStream();
            OutputStream os = proc.getOutputStream();

            PrintWriter pw = new PrintWriter(os);
            Scanner sc = new Scanner(is);//is);
            boolean signalRecieved = false;
            while (!signalRecieved) {   //  Wait for memory to read user input file

                try {
                    String line = sc.nextLine();
                    if (line != null) {
                        if (line.startsWith("D")) {
                            signalRecieved = true;
                        }
                        System.out.println(line);
                    }
                } catch (Exception e) {
                }
            }

            do {
                //Fetch
                if (instructionsRan >= timer && PC < 1000) {
                    //process interupt TIMER INTERRUPT
//                    interrupt = true;
//                    System.out.println("Instructions ran since last timer interrupt:" + instructionsRan);
                    instructionsRan = 0;
                    pw.printf("w " + 1999 + " " + SP + "\n");
                    pw.flush();
//                    PC++;
                    pw.printf("w " + 1998 + " " + PC + "\n");
                    pw.flush();
//                    System.out.println("TIMER INTERRUPT: PUSHING PC:" + PC + " SP:" + SP);

                    SP = 1998;
                    PC = 1000;
                    continue;

                }
//                System.out.println("New PC is: " + PC);
                pw.printf(PC + "\n");
                pw.flush();
//                while(sc.hasNext())
//                {
//                    
//                    
//                }
                IR = Integer.parseInt(sc.next());
                instructionsRan++;

//                System.out.println("IR:" + IR);
                //Execute
                switch (IR) {
                    case 1:
                        //Load
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();

                        AC = Integer.parseInt(sc.next());

                        break;
                    case 2:
                        //Load value at address into AC
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();

                        int address = Integer.parseInt(sc.next());
                        if (PC < 1000 && address >= 1000) {
                            System.out.println("Error: User program attempted to access system memory");
                            IR = 50;
                        } else {
                            pw.printf(address + "\n");
                            pw.flush();
                            AC = Integer.parseInt(sc.next());
                        }
                        break;

                    case 3:
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();

                        address = Integer.parseInt(sc.next());
                        if (PC < 1000 && address >= 1000) {
                            System.out.println("Error: User program attempted to access system memory");
                            IR = 50;
                        } else {
                            pw.printf(address + "\n");
                            pw.flush();

                            address = Integer.parseInt(sc.next());
                            if (PC < 1000 && address >= 1000) {
                                System.out.println("Error: User program attempted to access system memory");
                                IR = 50;
                            } else {
                                AC = address;
                            }
                        }

                        break;
                    case 4:
                        //Load ID + x addr
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();

                        address = Integer.parseInt(sc.next());
                        if (PC < 1000 && address >= 1000) {
                            System.out.println("Error: User program attempted to access system memory");
                            IR = 50;
                        } else {
                            pw.printf((X + address) + "\n");
                            pw.flush();

                            AC = Integer.parseInt(sc.next());
                        }
                        break;
                    case 5:
                        //Load ID + y addr
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();
                        address = Integer.parseInt(sc.next());
                        if (PC < 1000 && address >= 1000) {
                            System.out.println("Error: User program attempted to access system memory");
                            IR = 50;
                        } else {
                            pw.printf(Y + address + "\n");
                            pw.flush();

                            AC = Integer.parseInt(sc.next());
                        }
                        break;
                    case 6:
                        // Adds SP and X and reads from that address into AC
                        if ((SP + X) >= 1000 && PC < 1000) {
                            System.out.println("Error: User program attempted to access system memory");
                            IR = 50;
                        } else {
                            pw.printf((SP + X) + "\n");
                            pw.flush();
                            AC = Integer.parseInt(sc.next());
                        }
                        break;
                    case 7:
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();
                        address = Integer.parseInt(sc.next());
                        if (PC < 1000 && address >= 1000) {
                            System.out.println("Error: User program attempted to access system memory");
                            IR = 50;
                        } else {
                            pw.printf("w " + address + " " + AC + "\n");
                            pw.flush();
//                            System.out.println("Running total now is " + AC);

                        }
                        break;
                    case 8:
                        //get randome num
                        AC = (int) (Math.random() * (100 - 1)) + 1;
                        break;
                    case 9:
                        //put port
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();
                        IR = Integer.parseInt(sc.next());
                        if (IR == 1) {
                            //print int
                            System.out.print(AC);
                        } else if (IR == 2) {
                            //print CHar
                            System.out.print((char) AC);
                        }
                        break;
                    case 10:
                        //Add X
                        AC += X;
                        break;
                    case 11:
                        //Add Y
                        AC += Y;
//                        System.out.println("Adds Y to AC now is " + AC);
                        break;
                    case 12:
                        AC -= X;
                        break;
                    case 13:
                        AC -= Y;
                        break;
                    case 14:
                        //Copy to x
                        X = AC;
//                        System.out.println("X now is " + X);
                        break;
                    case 15:
                        //Copy from x
                        AC = X;
//                        System.out.println("AC now is " + AC);
                        break;
                    case 16:
                        Y = AC;
//                        System.out.println("Y now is " + Y);
                        //Copy To Y
                        break;
                    case 17:
                        //Set AC equal to value in Y
                        AC = Y;
                        break;
                    case 18:
                        SP = AC;
                        break;
                    case 19:
                        AC = SP;
                        break;
                    case 20:
                        // jump
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();
                        address = Integer.parseInt(sc.next());
                        if (PC < 1000 && address >= 1000) {
                            System.out.println("Error: User program attempted to access system memory");
                            IR = 50;
                        } else {
                            PC = address;
                        }

                        continue;
                    case 21:
                        //jump to address if value is equal to zero
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();
                        if (AC == 0) {
                            address = Integer.parseInt(sc.next());
                            if (address >= 1000 && PC < 1000) {
                                System.out.println("Error: User program attempted to access system memory");
                                IR = 50;
                                break;
                            } else {
                                PC = address;
                            }
                            continue;
                        } else {
                            sc.next();
                            break;
                        }
                    case 22:
                        //jump to address if value is NOT equal to zero
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();
//                        AC = Integer.parseInt(sc.nextLine());
//                        System.out.println("AC:" + AC);
                        if (AC != 0) {
                            address = Integer.parseInt(sc.next());
                            if (address >= 1000 && PC < 1000) {
                                System.out.println("Error: User program attempted to access system memory");
                                IR = 50;
                                break;
                            } else {
                                PC = address;
                            }
//                            System.out.println("Jumping to " +PC);
                            continue;
                        } else {
                            sc.next();
                            break;
                        }
                    case 23:
                        // return address on stack then jump to addr
//                        System.out.println("Instruction 23");
                        PC++;
                        pw.printf(PC + "\n");
                        pw.flush();

                        PC++;
                        SP--;
//                        System.out.println("Sending write signal");
//                        System.out.println("Writing " + PC + " at address " + SP);

                        pw.printf("w " + SP + " " + PC + "\n");
                        pw.flush();

//                        System.out.println("SP is now " + SP);
                        PC = sc.nextInt();
//                        System.out.println("Jumping to " + PC);

                        continue;
                    case 24:
                        //Jump to ret addr from stack
                        pw.printf(SP + "\n");
                        pw.flush();
                        PC = Integer.parseInt(sc.next());
                        SP++;

                        continue;
                    case 25:
                        //increment x
                        X++;
                        break;
                    case 26:
                        //Dec x
                        X--;
//                        System.out.println("X has been decremented to:" +X);
                        break;
                    case 27:
                        //Push AC on to stack
//                        System.out.println("Pushing AC: " + AC + " Onto stack");
//                        System.out.println("Sending write signal");
                        SP--;
                        pw.printf("w " + SP + " " + AC + "\n");
                        pw.flush();

//                        System.out.println("SP is now " + SP);
                        break;
                    case 28:
                        //Pop from stack to AC
//                        System.out.println("Popping from SP:" + SP);
                        pw.printf(SP + "\n");
                        pw.flush();
                        AC = Integer.parseInt(sc.next());
//                        System.out.println("Popped:" + AC);
                        SP++;
                        break;
                    case 29:
                        //SYSCALL
                        //SP Switch to System stack
//                        SP = 2000;

//                        SP--;
//                        interrupt = true;
//                        System.out.println("Entering Application syscall");
                        pw.printf("w " + 1999 + " " + SP + "\n");
                        pw.flush();
                        pw.printf("w " + 1998 + " " + ++PC + "\n");
                        pw.flush();

                        SP = 1998;
                        PC = 1500;
                        continue;
                    case 30:
                        //RETURN FROM SYSCALL
//                        interrupt = false;
//                        instructionsRan = 0;

                        pw.printf(SP + "\n");
                        pw.flush();
                        SP++;

                        pw.printf(SP + "\n");
                        pw.flush();
                        SP++;

                        PC = Integer.parseInt(sc.next());
//                        PC++;
                        SP = Integer.parseInt(sc.next());
//                        PC++;
//                        System.out.println("Leaving syscall, PC:" + PC + " and SP:" + SP);
                        continue;
                    case 50:
                        break;
                    default:
                        IR = 50;
                }
                PC++;
            } while (IR != 50);
            pw.close();
            proc.waitFor();
            int exitVal = proc.exitValue();

            System.out.println("Process exited: " + exitVal);
//            }
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }
}
