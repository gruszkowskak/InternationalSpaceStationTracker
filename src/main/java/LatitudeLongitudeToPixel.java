import java.io.IOException;

public class LatitudeLongitudeToPixel {
    float latitude;
    float longitude;

    public LatitudeLongitudeToPixel(ISSPosition issPosition) throws IOException, InterruptedException {
        this.latitude = issPosition.getLatitude();
        this.longitude = issPosition.getLongitude();
    }

    public double convertlongitude(int mapWidth) {
        double x = (longitude+180)*(mapWidth/360);
        return x;
    }
    public double convertlatitude(int mapHeight,int mapWidth) {
        double latRad = latitude*Math.PI/180;
        double mercN = Math.log(Math.tan((Math.PI/4)+(latRad/2)));
        double y     = (mapHeight/2)-(mapWidth*mercN/(2*Math.PI));
        //double y = (90-latitude)*(mapHeight/180);
        return y;
    }
}