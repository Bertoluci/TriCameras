package cz.seucit.axis.enums;

import cz.seucit.axis.environment.UrlsConstants;

public enum Move {
    Move,
    Home,
    Up,
    Down,
    Left,
    Right,
    Upleft,
    Upright,
    Downleft,
    Downright;

    @Override
    public String toString() {
        if ("Move".equals(this.name())) {
            return "Select move";
        }
        return this.name();
    }

    public String getUrl() {
        return UrlsConstants.BASIS_MOVE + this.name().toLowerCase();
    }
}
