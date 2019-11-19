
public class Loader
{
    public static void main(String[] args)
    {
        float[] temperatureAray = new float[30];
        int healthyCount = 0;

        for (int i = 0; i < temperatureAray.length; i++) {
            temperatureAray[i] = (float) (32.f + 10.f * Math.random());
        }

        float totalTemperature = 0.0f;
        String strHealthy;
        for (float patient : temperatureAray) {
            totalTemperature += patient;

            if ((patient >= 36.2f)&&(patient <= 36.9f)) {
                healthyCount++;
                strHealthy = " - Здоров";
            } else {
                strHealthy = "";
            }

            System.out.printf("%3.1f%s\n", patient, strHealthy);
        }

        float averageTemperature = totalTemperature / temperatureAray.length;
        System.out.printf("Средняя температура - %3.1f, здоровых - %d", averageTemperature, healthyCount);
    }
}
