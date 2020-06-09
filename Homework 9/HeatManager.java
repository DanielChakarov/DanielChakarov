/**
 * Клас реализираш извеждането на екрана
 */
public class HeatManager {

    public HeatManager(Machine heatMachine, WrapperEngine wrapperMachine){
        System.out.printf("Максималната мощност на тази Heat машина е:%d",heatMachine.getMachinePower());
        System.out.println();
        System.out.printf("Максималната мощност на тази Wrapper машина е:%d ",wrapperMachine.getMachinePower());
    }
}
