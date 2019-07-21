package cz.seucit.axis.enums;

import cz.seucit.axis.environment.UrlsConstants;

public enum Zoom {
    Select(-1),
    Zoom0(0),
    Zoom25(25),
    Zoom50(50),
    Zoom75(75),
    Zoom100(100);

    private Integer percent;

    Zoom(int percent) {
        this.percent = percent;
    }

    public Integer getPercent() {
        if (percent == -1) return 0;
        Double d = 9999.0 * (percent / 100.0);
        Integer i = d.intValue();
        System.out.println("zoom: " + i);
        return i;
    }

    @Override
    public String toString() {
        if ("Select".equals(this.name())) {
            return "Select";
        }
        return String.valueOf(percent);
    }

    public String getUrl() {
        return UrlsConstants.BASIS_ZOOM + getPercent();
    }

}
