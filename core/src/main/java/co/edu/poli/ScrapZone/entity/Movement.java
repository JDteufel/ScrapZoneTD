package co.edu.poli.ScrapZone.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Movement {
    private float x, y;
    private float speed;
    private Array<Vector2> path;
    private int currentPathIndex = 0;
    private boolean reachedEnd = false;
    private float rotation = 0f;

    public Movement(float speed) {
        this.speed = speed;
        this.currentPathIndex = 0;
        this.reachedEnd = false;
    }

    public void setPath(Array<Vector2> path) {
        this.path = path;
        this.currentPathIndex = 0;
        this.reachedEnd = false;

        if (path != null && path.size > 0) {
            Vector2 start = path.get(0);
            this.x = start.x;
            this.y = start.y;
        }
    }

    public boolean update(float delta) {
        if (path == null || path.size == 0 || reachedEnd) {
            return reachedEnd;
        }

        if (currentPathIndex < path.size) {
            Vector2 target = path.get(currentPathIndex);
            float dx = target.x - x;
            float dy = target.y - y;
            float distance = (float) Math.sqrt(dx * dx + dy * dy);

            if (distance < 5f) {
                currentPathIndex++;
                if (currentPathIndex >= path.size) {
                    reachedEnd = true;
                    return true;
                }
            } else {
                dx /= distance;
                dy /= distance;
                rotation = (float) Math.atan2(dy, dx);

                x += dx * speed * delta;
                y += dy * speed * delta;
            }
        }

        return false;
    }

    // Getters y setters
    public float getX() { return x; }
    public float getY() { return y; }
    public float getSpeed() { return speed; }
    public float getRotation() { return rotation; }
    public boolean hasReachedEnd() { return reachedEnd; }

    public void setPosition(float x, float y) { this.x = x; this.y = y; }
    public void setSpeed(float speed) { this.speed = speed; }
}
