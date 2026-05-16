/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package project;

import java.util.Scanner;

import project.core.Database;
import project.core.FileManager;
import project.core.Report;
import project.core.Utils;

/**
 *
 * @author akramseifeddine
 */
public class MyProject {

    public static void main(String[] args) {
        Report report = new Report();
        Scanner sc = new Scanner(System.in);
        FileManager fm = new FileManager("/Users/akram/Documents/collage/oop/oop1/src/db.txt","/Users/akram/Documents/collage/oop/oop1/src/report.txt");
        Database db = new Database();

        int choice;
        
        while (true) { 
            Utils.PrintMainMenu();
            choice = sc.nextInt();
            sc.nextLine();

            
            Utils.HandleMainMenu(report, sc, choice, db);
            if (choice == 11) {
                return;
            }
        }
    }
}
