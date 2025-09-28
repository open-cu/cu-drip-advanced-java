package ru.centraluniversity.pool;

class Event {

    public Event(Integer userId, Integer x, Integer y) {
        this.userId = userId;
        this.x = x;
        this.y = y;
    }

    private Integer userId;
    private Integer x;
    private Integer y;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void reset() {
        this.userId = null;
        this.x = null;
        this.y = null;
    }

    @Override
    public String toString() {
        return "Event{" +
            "userId=" + userId +
            ", x=" + x +
            ", y=" + y +
            '}';
    }
}
