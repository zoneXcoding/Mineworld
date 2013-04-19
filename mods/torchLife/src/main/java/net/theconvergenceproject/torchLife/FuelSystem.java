package net.theconvergenceproject.torchLife;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.componentSystem.UpdateSubscriberSystem;
import org.terasology.entitySystem.*;
import org.terasology.game.CoreRegistry;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockComponent;
import org.terasology.world.block.management.BlockManager;

@RegisterComponentSystem(authorativeOnly = true)
public class FuelSystem implements EventHandlerSystem, UpdateSubscriberSystem {
    int tick = 0;
    int classLastTick = 0;
    int id = 15;
    private static final Logger logger = LoggerFactory.getLogger(FuelSystem.class);
    private EntityManager entityManager;

    @In
    private WorldProvider worldProvider;

    public void initialise() {

        entityManager = CoreRegistry.get(EntityManager.class);
        logger.info("Starting up TorchLife 1.0!");
    }

    @Override
    public void shutdown() {
    }

    public void update(float delta) {
        tick += delta * 1000;
        //TODO Make it so every 30 mins they get rid of some light and fuel.
        if(tick - classLastTick < 1000){
            return;
        }
        classLastTick = tick;
        for (EntityRef entity : entityManager.iteratorEntities(FuelComponent.class)) {
            FuelComponent fuelComp = entity.getComponent(FuelComponent.class);
            BlockComponent block = new BlockComponent();
            if (entity.hasComponent(BlockComponent.class)) {
                block = entity.getComponent(BlockComponent.class);
            }
            //entity.saveComponent(hunger);
            //So now basically every second i could take coal out of the torch?
            fuelComp.quantity -= 1;
            //id--;
            System.out.println(id);
            Block currentBlock = worldProvider.getBlock(block.getPosition());
            worldProvider.setBlock(block.getPosition().x, block.getPosition().y, block.getPosition().z, BlockManager.getInstance().getBlock("torchLife:torch14"), currentBlock);

            if(fuelComp.quantity<=0){
            	
                ///if (!entity.hasComponent(ItemComponent.class)) {
            	//Block currentBlock = worldProvider.getBlock(block.getPosition());
            	//worldProvider.setBlock(block.getPosition(), BlockManager.getInstance().getAir(), currentBlock);
                //}
                ///entity.destroy();
                if(entity.hasComponent(BlockComponent.class)){
                    worldProvider.setBlock(block.getPosition(), BlockManager.getInstance().getAir(), currentBlock);
                }
                entity.destroy();
            }
        }
    }

}
