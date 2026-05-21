/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package project;

import java.util.Scanner;

import project.core.Database;
import project.core.FileManager;
import project.core.Menu;
import project.core.Report;
import project.core.Validation;
/**
 *
 * @author reemashkar
 * @author akramseifeddine
 */
public class MyProject {

    public static void main(String[] args) {
        Report report = new Report();
        Scanner sc = new Scanner(System.in);
        FileManager fm = new FileManager("/Users/akramseifeddine/Documents/collage/oop/myProject/src/main/java/project/core/db.txt","/Users/akramseifeddine/Documents/collage/oop/myProject/src/main/java/project/core/report.txt");
        Database db = new Database();

        int choice;
        
        while (true) { 
            Menu.PrintMainMenu();
            
            choice = Validation.GetIntInput(sc);
            
            Menu.HandleMainMenu(report, sc, choice, db);
            if (choice == 11) {
                return;
            }
        }
    }
}
