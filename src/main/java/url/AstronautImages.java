package url;

import java.util.ArrayList;

public class AstronautImages {
    private static class AstronautImage{
        String contentUrl;
        String name;
        String encodingFormat;
        Integer width;
        Integer height;

        public String getContentUrl() {
            return contentUrl;
        }

        public String getName() {
            return name;
        }

        public String getEncodingFormat() {
            return encodingFormat;
        }

        public Integer getWidth() {
            return width;
        }

        public Integer getHeight() {
            return height;
        }
    }
    private String readLink;
    private ArrayList<AstronautImage> value;

    public String getContentUrl() {
        return value.get(0).contentUrl;
    }

    public String getName() {
        return value.get(0).name;
    }

    public String getEncodingFormat() {
        return value.get(0).encodingFormat;
    }

    public Integer getWidth() {
        return value.get(0).width;
    }

    public Integer getHeight() {
        return value.get(0).height;
    }



}
