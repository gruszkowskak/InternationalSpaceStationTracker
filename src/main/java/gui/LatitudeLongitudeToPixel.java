package gui;

import url.ISSPosition;

import java.io.IOException;

public class LatitudeLongitudeToPixel {
    float latitude;
    float longitude;

    public LatitudeLongitudeToPixel(ISSPosition issPosition) throws IOException, InterruptedException {
        this.latitude = issPosition.getLatitude();
        this.longitude = issPosition.getLongitude();
    }

    public double convertlongitude(int mapWidth) {
        double doubleMapWidth = mapWidth;
        double x = (longitude+180)*(doubleMapWidth/360);
        return x;
    }
    public double convertlatitude(int mapHeight,int mapWidth) {
        int zone= (int) Math.floor(longitude/6+31);
        double northing = (Math.atan(Math.tan(latitude*Math.PI/180)/Math.cos((longitude*Math.PI/180-(6*zone -183)*Math.PI/180)))-latitude*Math.PI/180)*0.9996*6399593.625/Math.sqrt(1+0.006739496742*Math.pow(Math.cos(latitude*Math.PI/180),2))*(1+0.006739496742/2*Math.pow(0.5*Math.log((1+Math.cos(latitude*Math.PI/180)*Math.sin((longitude*Math.PI/180-(6*zone -183)*Math.PI/180)))/(1-Math.cos(latitude*Math.PI/180)*Math.sin((longitude*Math.PI/180-(6*zone -183)*Math.PI/180)))),2)*Math.pow(Math.cos(latitude*Math.PI/180),2))+0.9996*6399593.625*(latitude*Math.PI/180-0.005054622556*(latitude*Math.PI/180+Math.sin(2*latitude*Math.PI/180)/2)+4.258201531e-05*(3*(latitude*Math.PI/180+Math.sin(2*latitude*Math.PI/180)/2)+Math.sin(2*latitude*Math.PI/180)*Math.pow(Math.cos(latitude*Math.PI/180),2))/4-1.674057895e-07*(5*(3*(latitude*Math.PI/180+Math.sin(2*latitude*Math.PI/180)/2)+Math.sin(2*latitude*Math.PI/180)*Math.pow(Math.cos(latitude*Math.PI/180),2))/4+Math.sin(2*latitude*Math.PI/180)*Math.pow(Math.cos(latitude*Math.PI/180),2)*Math.pow(Math.cos(latitude*Math.PI/180),2))/3);
        northing = northing + 10000000;
        northing=Math.round(northing*100)*0.01;
        double y=northing/20000000 * mapHeight;
        return mapHeight - y;
    }
}
