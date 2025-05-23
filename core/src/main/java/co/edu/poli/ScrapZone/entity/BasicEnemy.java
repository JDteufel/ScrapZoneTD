package co.edu.poli.ScrapZone.entity;

public class BasicEnemy extends Enemy {
    private static final float BASIC_HEALTH = 70f;
    private static final float BASIC_SPEED = 50f;
    private static final int BASIC_DAMAGE = 30;
    private static final float BASIC_WIDTH = 32f;
    private static final float BASIC_HEIGHT = 32f;

    public BasicEnemy() {
        super(BASIC_HEALTH, BASIC_SPEED, BASIC_DAMAGE, BASIC_WIDTH, BASIC_HEIGHT, EnemyType.BASIC);
    }

    @Override
    protected void updateSpecific(float delta) {

    }
}
