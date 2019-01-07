package pl.bobowski.myOrganiserApp.model.dto;


public class WeatherDto {

    private TempDto main;
    private CloudDto clouds;

    public TempDto getTempDto() {
        return main;
    }

    public CloudDto getClouds() {
        return clouds;
    }


    public static class CloudDto {
        private int all;

        public String getCloudsState(){
            if(all <= 10){
                return"noClouds";
            }else if (all <=60){
                return"someClouds";
            }else{
                return"clouds";
            }
        }

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }

    public static class TempDto {

        private double temp;

        public double getTemp() {
            return Math.round(temp - 273.15);
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

}
