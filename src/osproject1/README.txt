My files are CPU.java and Memory.java
CPU.java- the cpu class where all of the logic is held. This is the class that runs the memory file
	and does most of the work.

Memory.java- This file is simply for storage of the user's program as well as the user and system stacks.
	it is interacted with by the CPU, but has very little logic of its own.

HOW TO COMPILE AND RUN PROJECT:
Compile the programs with...
javac CPU.java Memory.java

Run them with...
java CPU (input file name).txt (timer value*)

*Note: make the timer value over 15-20ish to avoid any errors.

You do not need to do CPU.class I don't believe, but try it if the above commands don't work


Summary.pdf- My summary over the project that is one page long.

sample5.txt- Finds the factorial of 10. The number can be modified by changing the number
	at the beginning of the sample (line number 3)
