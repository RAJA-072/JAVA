import java.util.Scanner;

class Book {
    Scanner in = new Scanner(System.in);
    int n = 3;
    String[] title = new String[n];
    String[] author_name = new String[n];
    String[] category = new String[n];
    int[] isbn_no = new int[n];
    int[] cost_per_quant = new int[n];
    int[] quantity = new int[n];
    int t = 0;
  
  void getinput() {
        for (int i = 0; i < n; i++) {
            System.out.println(" ");
            System.out.print("enter the title: ");
            title[i] = in.nextLine();
            System.out.print("enter the author name: ");
            author_name[i] = in.nextLine();
            System.out.print("enter the category: ");
            category[i] = in.nextLine();
            System.out.print("enter the ID no: ");
            isbn_no[i] = in.nextInt();
            System.out.print("enter the quantity: ");
            quantity[i] = in.nextInt();
            System.out.print("enter the cost per quantity: ");
            cost_per_quant[i] = in.nextInt();
            in.nextLine();}}
    void printoutput() {
        int i;
        int c = totalCostCal();
        System.out.println("*****************Details of the Books present at the Store********************");
        for (i = 0; i < n; i++) {
            System.out.println("TITLE: " + title[i]);
            System.out.println("Author: " + author_name[i]);
            System.out.println("ID no: " + isbn_no[i]);
            System.out.println("Category: " + category[i]);
            System.out.println("Quantity: " + quantity[i]);
            System.out.println("");}
        System.out.println("Total cost: " + c);}
  
  int totalCostCal() {
        int i;
        for (i = 0; i < n; i++) {
            t = t + (quantity[i] * cost_per_quant[i]);}
        return t; }}

class Main {
    public static void main(String args[]) {
        Book ob = new Book();
        ob.getinput();
        ob.printoutput(); }}
