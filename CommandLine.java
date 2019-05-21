import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;

public class CommandLine {
    public static Scanner input = new Scanner(System.in);
    public Scanner inputNot = input;
    public static boolean exit = false;
    public static String currentDirectory;
    public String currentDirectoryNot = currentDirectory;
    public void checkReply(String reply) {
        FileCommands files = new FileCommands();
        Math mathCommand = new Math();
        RockPaperScissors rpcGame = new RockPaperScissors();
        switch(reply) {
            case "help":
                System.out.println("Please check the readme file for context of what you can do.");
                break;
            case "exit":
                exit = true;
                break;
            case "cd":
                System.out.print("Please enter the file system here: ");
                try {
                    String newFileDir = input.nextLine();
                    files.setCurrentDirectory(newFileDir);
                    currentDirectory = System.getProperty("user.dir");
                } catch (Exception exception) {
                    System.out.println("I'm sorry, but there was some error. Please try again.");
                }
                break;
            case "make":
                System.out.print("Please enter your file contents here, then hit [Enter] when finished: ");
                String fileContent = input.nextLine();
                System.out.print("Write the file name:");
                String fileName = input.nextLine();
                try {
                    files.makeFile(fileContent, fileName);
                } catch (Exception exception) {}
                break;
            case "move":
                System.out.print("Please enter the directory you'd like to move the file to: ");
                String secondDirectory = input.nextLine();
                System.out.print("Please enter the filename: ");
                String fileName2 = input.nextLine();
                try {
                    files.moveFile(fileName2, secondDirectory);
                } catch (Exception exception) {
                    System.out.println("Error: Could not move file");
                }
                break;
            case "math":
                mathCommand.run();
                break;
            case "games":
                System.out.println("Please select a game through 1 - 1: ");
                System.out.println("1 = Rock Paper Scissors");
                int gameNumber = input.nextInt();
                switch (gameNumber) {
                    case 1:
                        rpcGame.RockPaperScissorsLoop();
                        break;
                }
                break;
            case "":
                break;
            default:
                System.out.println("Error: No such command");
        }
        
    }
    public static void main(String[] args) {
        currentDirectory = System.getProperty("user.dir");
        CommandLine main = new CommandLine();
        while (true) {
            System.out.print(currentDirectory + " $" );
            currentDirectory = System.getProperty("user.dir");
            String humanreply = input.nextLine();
            main.checkReply(humanreply);
            if (exit == true) {
                break;
            }
            
        }
        input.close();
    }
}
class FileCommands {
    public boolean setCurrentDirectory(String directory_name)
    {
        boolean result = false;  // Boolean indicating whether directory was set
        File directory;       // Desired current working directory

        directory = new File(directory_name).getAbsoluteFile();
        if (directory.exists() || directory.mkdirs())
        {
            result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
        }

        return result;
    }

    public PrintWriter openOutputFile(String file_name)
    {
        PrintWriter output = null;  // File to open for writing

        try
        {
            output = new PrintWriter(new File(file_name).getAbsoluteFile());
        }
        catch (Exception exception) {}

        return output;
    }
    public void makeFile(String filecontent, String filename) throws IOException {
        System.out.println("Creating file " + filename);
        CommandLine central = new CommandLine();
        String currentDirectory = central.currentDirectoryNot;
        FileOutputStream fos = new FileOutputStream(currentDirectory + "/" + filename);
        fos.write(filecontent.getBytes());
        fos.flush();
        fos.close();
        System.out.println("Created file " + filename + " with no errors.");
    }
    
    public void moveFile(String filename, String otherDirectory) throws IOException{
        CommandLine central = new CommandLine();
        String currentDirectory = central.currentDirectoryNot;
        Path temp = Files.move 
        (Paths.get(currentDirectory + "/" + filename),  
        Paths.get(otherDirectory + "/" + filename)); 
  
        if(temp != null) 
        { 
            System.out.println("File renamed and moved successfully"); 
        } 
        else
        { 
            System.out.println("Failed to move the file"); 
        } 
    } 
    
    public static void main(String[] args) {

    }
}
class Math {
    int Final = 0;
    
    public int doMath(int number1, String opreator, int number2) {
        switch (opreator) {
            case "+":
                Final = number1 + number2;
                break;
            case "-":
                Final = number1 - number2;
                break;
            case "*":
                Final = number1 * number2;
                break;
            case "/":
                Final = number1 / number2;
                break;
            case "%":
            	Final = number1 % number2;
            default:
                System.out.println("Error: had to do with the possible opreator");
        }
        return Final;
    }
    public void run(){
        CommandLine central = new CommandLine();
        Scanner input = central.inputNot;
        Math math = new Math();
        System.out.print("Enter your first number: ");
        int FirstNumber = input.nextInt();
        System.out.print("Enter an opreator: ");
        String opreator = input.next();
        System.out.print("Enter your second number: ");
        int SecondNumber = input.nextInt();
        math.doMath(FirstNumber, opreator, SecondNumber);
        System.out.println("Answer: " + math.Final);
    }
    public static void main(String[] args) {
         
    }
}
class RockPaperScissors {
    public String ComputerAnswer;
    public String getRandomElement(ArrayList<String> List ) {
        Random random = new Random();
        return List.get(random.nextInt(List.size()));
    }
    public RockPaperScissors() {
        ArrayList<String> answerlist = new ArrayList<String>();
        answerlist.add("rock");
        answerlist.add("paper");
        answerlist.add("scissors");
        ComputerAnswer = getRandomElement(answerlist);

    }
    public void checkAnswer(String answer) {
        answer = answer.toLowerCase();
            if (ComputerAnswer.equals(answer)) {
                System.out.println("It's a tie!");
            }
            else if (answer.equals("rock")) {
                if (ComputerAnswer.equals("scissors")) {
                    System.out.println("You win!");
                }
                else {
                    System.out.println("Computer wins!");
                }
            }
            else if (answer.equals("paper")) {
                if (ComputerAnswer.equals("rock")) {
                    System.out.println("You win!");
                }
                else {
                    System.out.println("Computer wins!");
                }
            } 
            else if (answer.equals("scissors")) {
                if (ComputerAnswer.equals("paper")) {
                    System.out.println("You win!");
                }
                else {
                    System.out.println("Computer wins!");
                }
            }
            else if (answer.equals("exit")){
                System.out.println("Thanks for playing!");
            } 
            else {
                System.out.println("I think there was an error somewhere..."); 
            }
    }
    public void RockPaperScissorsLoop(){
        CommandLine central = new CommandLine();
        Scanner scan = central.inputNot;
        boolean quit = true;
        char ComputerAnswerFirstLetter =  Character.toUpperCase(ComputerAnswer.charAt(0));
        String PrintableComputerAnswer = ComputerAnswerFirstLetter + ComputerAnswer.substring(1);
        while (quit != false) {
            System.out.println("Please choose between Rock, Paper, or Scissors");
            String HumanAnswer = scan.nextLine();
            if (!HumanAnswer.toLowerCase().equals("exit")) {
                System.out.println(PrintableComputerAnswer);
            }
            checkAnswer(HumanAnswer);
            ArrayList<String> answerlist = new ArrayList<String>();
            answerlist.add("rock");
            answerlist.add("paper");
            answerlist.add("scissors");
            ComputerAnswer = getRandomElement(answerlist);
            ComputerAnswerFirstLetter =  Character.toUpperCase(ComputerAnswer.charAt(0));
            PrintableComputerAnswer = ComputerAnswerFirstLetter + ComputerAnswer.substring(1);
            if (HumanAnswer.toLowerCase().equals("exit")) {
                quit = false;
            }
        }
    }
}