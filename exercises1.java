import java.util.Scanner;

public class exercises1 {
    public static void main(String[] args){
     Scanner scanner=new Scanner(System.in);
     double sideA=scanner.nextDouble();
     double sideB=scanner.nextDouble();
     double Square=sideA*sideB;
     double Tour=(2*sideA)+(2*sideB);
     System.out.println(Square+"кв. см");
     System.out.println(Tour+"см");

    }
}
