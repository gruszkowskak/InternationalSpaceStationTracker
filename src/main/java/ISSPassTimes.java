

import java.util.ArrayList;

public class ISSPassTimes {
    public static  class Request{
        private float altitude;
        private int datetime;
        private float latitude;
        private float longitude;
        private int passes;

        public float getAltitude() {
            return altitude;
        }

        public int getDatetime() {
            return datetime;
        }

        public float getLatitude() {
            return latitude;
        }

        public float getLongitude() {
            return longitude;
        }

        public int getPasses() {
            return passes;
        }

        @Override
        public String toString() {
            return "Request{" +
                    "altitude=" + altitude +
                    ", datetime=" + datetime +
                    ", latitude=" + latitude +
                    ", longitud=" + longitude +
                    ", passes=" + passes +
                    '}';
        }
    }
    public static class Response{
            private int duration;
            private int risetime;

        public int getDuration() {
            return duration;
        }

        public int getRisetime() {
            return risetime;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "duration=" + duration +
                    ", risetime=" + risetime +
                    '}';
        }
    }


    private String message;
    private Request request;
    private ArrayList<Response> response;

    public String getMessage() {
        return message;
    }

    public Request getRequest() {
        return request;
    }

    public ArrayList<Response> getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "ISSPassTimes{" +
                "message='" + message + '\'' +
                ", request=" + request +
                ", response=" + response +
                '}';
    }
}
