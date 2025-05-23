package co.edu.poli.ScrapZone.entity;

public class Health {
    private float health;
    private float maxHealth;

    public Health(float maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(float damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public void heal(float amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public float getHealth() { return health; }
    public float getMaxHealth() { return maxHealth; }
    public float getHealthPercentage() { return health / maxHealth; }

    public void setHealth(float health) {
        this.health = Math.min(health, maxHealth);
        if (this.health < 0) this.health = 0;
    }
}
