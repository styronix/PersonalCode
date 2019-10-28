import java.util.Scanner;
class DecimalToBinaryRecursion{
    public static void main(String args []){ // main function
        Scanner sc = new Scanner(System.in); 
        System.out.println("Enter the number in decimal");
        int decimal = sc.nextInt();
        int binary = convertToBinary(decimal);
        System.out.println(binary);
    }

    static int convertToBinary(int n){ // recursive function
        if(n < 2){ // base case
            return n;
        }
        return convertToBinary(n/2)*10 + (n%2);
    }
}