package fr.maliimaloo.bosscustomisation.attack;

import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;

public abstract class Attack implements Listener {

    // Temps entre chaque attaque (en secondes)
    protected int attackCooldown;
    protected int attackDamage;
    protected int attackRadius;

    public Attack(int attackCooldown, int attackDamage, int attackRadius) {
        this.attackCooldown = attackCooldown;
        this.attackDamage = attackDamage;
        this.attackRadius = attackRadius;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }
    
    public int getAttackDamage() {
    	return attackDamage;
    }
    
    public int getAttackRadius() {
    	return attackRadius;
    }

    // Méthode à implémenter pour lancer l'attaque
    public abstract void startAttack(Entity boss);
    
    //Méthode à implémenter pour arreter l'attaque
    public abstract void stopAttack();
}
