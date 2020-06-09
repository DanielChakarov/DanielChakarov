import java.util.Random;

public class Machine {
    protected  Random randomNumberGenerator = new Random();
    protected  int machineTemperature;
    protected  int machinePower;
    protected  String machineColor;
    protected  String [] colorOfMachine = new String []{"White","Green","Red","Yellow","Pink","Purple","Orange"};

public Machine(){
    machinePower       = randomNumberGenerator.nextInt(1000);
    machineTemperature = randomNumberGenerator.nextInt(100);
    machineColor       = colorOfMachine[randomNumberGenerator.nextInt(7)];
 }

 public int getMachinePower(){
    return  machinePower;
 }
public int getMachineTemperature(){
    return machineTemperature;
}
public String getMachineColor(){
    return machineColor;
}

    public void setMachinePower (int power) {
    machinePower = power;
}
    public void setMachineHeight(int temperature) {
    machineTemperature = temperature;
}
    public void setMachineColor (String color) {
    machineColor = color;
}


}
