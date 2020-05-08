package source;

import source.CLI;
import source.Entrance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EntrancePage {
    public static void enterPage()throws IOException
    {
        Scanner s=new Scanner(System.in);
        RunGame running =new RunGame();
        String f;
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        if (Entrance.loginPermission()) {
            Log.makelog();
            running.runGame();

            do {
//System.out.println("entered succefully");
                // Reading data using readLine
                String name = reader.readLine();
                CLI.cli(name);

                // Printing the read line

            }while(true);

        }

    }
}
