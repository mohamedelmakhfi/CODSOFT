import java.util.Scanner;

class GradeCalculator {
    public static void calculateGrade() {
        // Input: Take marks obtained (out of 100) in each subject
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        double totalMarks = 0;

        // Calculate Total Marks: Sum up the marks obtained in all subjects
        for (int i = 1; i <= numSubjects; i++) {
            double subjectMarks;

            // Demander à l'utilisateur de saisir la note jusqu'à ce qu'elle soit valide (entre 0 et 100)
            do {
                System.out.print("Enter marks obtained in subject " + i + " (out of 100): ");
                subjectMarks = scanner.nextDouble();

                if (subjectMarks < 0 || subjectMarks > 100) {
                    System.out.println("Please enter a valid mark between 0 and 100.");
                }
            } while (subjectMarks < 0 || subjectMarks > 100);

            totalMarks += subjectMarks;
        }


        // Calculate Average Percentage: Divide the total marks by the total number of subjects
        double averagePercentage = totalMarks / numSubjects;

        // Grade Calculation: Assign grades based on the average percentage achieved
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display Results: Show the total marks, average percentage, and the corresponding grade to the user
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        // Close the scanner
        scanner.close();
    }
}