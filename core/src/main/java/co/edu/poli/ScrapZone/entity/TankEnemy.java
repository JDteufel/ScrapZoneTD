package co.edu.poli.ScrapZone.entity;

public class TankEnemy extends Enemy {
    private static final float TANK_HEALTH = 100f;
    private static final float TANK_SPEED = 30f;
    private static final int TANK_DAMAGE = 20;
    private static final float TANK_WIDTH = 32f;
    private static final float TANK_HEIGHT = 32f;

    public TankEnemy() {
        super(TANK_HEALTH, TANK_SPEED, TANK_DAMAGE, TANK_WIDTH, TANK_HEIGHT, EnemyType.TANK);
    }

    @Override
    protected void updateSpecific(float delta) {

    }
}
