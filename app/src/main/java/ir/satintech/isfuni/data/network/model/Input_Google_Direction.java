package ir.satintech.isfuni.data.network.model;



public class Input_Google_Direction {

    double sourcelat;
    double sourcelog;
    double destlat;
    double destlog;

    String mode;
    String language;
    String units;
    String key;
    String sensor;

    public Input_Google_Direction(double sourcelat, double sourcelog, double destlat, double destlog, String mode, String language, String units, String key, String sensor) {
        this.sourcelat = sourcelat;
        this.sourcelog = sourcelog;
        this.destlat = destlat;
        this.destlog = destlog;
        this.mode = mode;
        this.language = language;
        this.units = units;
        this.key = key;
        this.sensor = sensor;
    }


    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public double getSourcelat() {
        return sourcelat;
    }

    public void setSourcelat(double sourcelat) {
        this.sourcelat = sourcelat;
    }

    public double getSourcelog() {
        return sourcelog;
    }

    public void setSourcelog(double sourcelog) {
        this.sourcelog = sourcelog;
    }

    public double getDestlat() {
        return destlat;
    }

    public void setDestlat(double destlat) {
        this.destlat = destlat;
    }

    public double getDestlog() {
        return destlog;
    }

    public void setDestlog(double destlog) {
        this.destlog = destlog;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
