package cz.seucit.axis.environment;

import java.net.URI;

public class UrlsConstants {

    public final static URI BASE_URI = URI.create("http://192.168.1.182");
    public final static URI BASE_VIDEO_URI = URI.create("http://192.168.1.182/mjpg/1/video.mjpg");
    public final static URI BASE_IMAGE_URI = URI.create("http://192.168.1.182/jpg/1/image.jpg");

    // home, up, down, left, right, upleft, upright, downleft, downright
    public final static String BASIS_MOVE = "http://192.168.1.182/axis-cgi/com/ptz.cgi?move=";
    // 0, 90, 180, 270
    public final static String BASIS_ZOOM = "http://192.168.1.182/axis-cgi/com/ptz.cgi?zoom=";

}
