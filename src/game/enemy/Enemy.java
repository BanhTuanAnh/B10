package game.enemy;

import base.GameObject;
import base.Vector2D;
import game.player.BulletPlayer;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;

import java.awt.*;

public class Enemy extends GameObject implements PhysicBody {

    public Vector2D velocity;

    public BoxCollider boxCollider;

    public EnemyShoot enemyShoot;

    private RunHitObject runHitObject;

    public Enemy() {
        this.renderer = new ImageRenderer("resources-rocket-master/resources/images/circle.png", 20, 20);
        this.velocity = new Vector2D();
        this.enemyShoot = new EnemyAttack();
        this.boxCollider=new BoxCollider(20,20);
        this.runHitObject = new RunHitObject(
                BulletPlayer.class
        );
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        runHitObject.run(this);
    }
    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        ((EnemyAttack) this.enemyShoot)
                .bulletEnemies
                .forEach(bulletEnemy -> bulletEnemy.render(graphics));

    }
    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
