public class Main {
    public static void main(String[] args) {

        HeatEngine[] heater     = new HeatEngine[7];
        WrapperEngine[] wrapper = new WrapperEngine[7];

        for (int j = 0; j < heater.length; j++) {
            heater[j] = new HeatEngine();
            wrapper[j] = new WrapperEngine();
            new HeatManager(heater[j],wrapper[j]);
        }

    }

}
