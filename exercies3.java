import java.util.Scanner;

public class exercies3 {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        int floorNumber= scanner.nextInt();
        String neighbor="Default value";
        switch(floorNumber){
            case 8:
                neighbor="Мими";
                break;
            case 7:
                neighbor="Пепи";
                break;
            case 6:
                neighbor="Ваня";
                break;
            case 5:
                neighbor="Валя";
                break;
            case 4:
                neighbor="Габи";
                break;
            case 3:
                neighbor="Роси";
                break;
            case 2:
                neighbor="Коци";
                break;
            case 1:
                neighbor="Ник";
                break;
            case 0:
                neighbor="Ауч";
                break;
            default:
                neighbor="Invalid floor";
                break;
        }
        System.out.println(neighbor);
    }
}
