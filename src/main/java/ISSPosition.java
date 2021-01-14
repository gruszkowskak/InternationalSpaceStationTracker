

import java.io.Serializable;

/** The Issposition store and shere data from single request from page "http://api.open-notify.org/iss-now.json" */
public class ISSPosition implements Serializable {

    public static class LongitudeLatitude implements  Serializable{
        private float latitude;
        private float longitude;


        public float getLongitude() {
            return longitude;
        }

        public float getLatitude() {
            return latitude;
        }

        // for check loading json to class
        @Override
        public String toString() {
            return "LongitudeLatitude{" +
                    ", latitude=" + latitude +
                    "longitude=" + longitude +

                    '}';
        }
    }
    private int timestamp;
    private LongitudeLatitude iss_position;
    private String message;

    public int getTimestamp() {
        return timestamp;
    }

    public LongitudeLatitude getIss_position() {
        return iss_position;
    }

    public String getMessage() {
        return message;
    }
    public float getLongitude() {
        return iss_position.getLongitude();
    }

    public float getLatitude() {
        return iss_position.getLatitude();
    }

    // for check loading json to class
    @Override
    public String toString() {
        return "ISSposition{" +
                "timestamp=" + timestamp +
                ", iss_position=" + iss_position +
                ", message='" + message + '\'' +
                '}';
    }
}
