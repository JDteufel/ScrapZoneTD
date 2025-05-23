package co.edu.poli.ScrapZone.entity;

public class FastEnemy extends Enemy {

    private static final float FAST_HEALTH = 50f;
    private static final float FAST_SPEED = 100f;
    private static final int FAST_DAMAGE = 10;
    private static final float FAST_WIDTH = 32f;
    private static final float FAST_HEIGHT = 32f;

    public FastEnemy() {
        super(FAST_HEALTH, FAST_SPEED, FAST_DAMAGE, FAST_WIDTH, FAST_HEIGHT, EnemyType.FAST);
    }

    @Override
    protected void updateSpecific(float delta) {

    }
}
