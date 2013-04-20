package org.terasology.world.generator.core;

import org.terasology.config.Config;
import org.terasology.config.WorldGenerationConfig;
import org.terasology.game.CoreRegistry;
import org.terasology.utilities.FastRandom;
import org.terasology.world.WorldBiomeProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.management.BlockManager;
import org.terasology.world.chunks.Chunk;
import org.terasology.world.generator.ChunkGenerator;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Fred
 * Date: 4/19/13
 * Time: 7:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class OreGenerator implements ChunkGenerator {

    String worldSeed;
    private WorldBiomeProvider biomeProvider;

    private Block stoneBlock;
    private WorldGenerationConfig config = CoreRegistry.get(Config.class).getWorldGeneration();

    public OreGenerator() {
        stoneBlock = BlockManager.getInstance().getBlock("engine:Stone");

        System.out.println("Starting oreGenerator!");
    }

    @Override
    public void generateChunk(Chunk c) {
        // TODO: Better seeding mechanism
        FastRandom random = new FastRandom(worldSeed.hashCode() ^ (c.getPos().x + 39L * (c.getPos().y + 39L * c.getPos().z)));
        for (int y = 0; y < Chunk.SIZE_Y; y++) {
            for (int x = 0; x < Chunk.SIZE_X; x++) {
                for (int z = 0; z < Chunk.SIZE_Z; z++) {
                    generateOres(c, x, y, z, random);
                }
            }
        }
    }

    private void generateOres(Chunk c, int x, int y, int z, FastRandom random) {
        Block targetBlock = c.getBlock(x, y, z);
        if ((targetBlock.equals(stoneBlock))) {

            double grassRand = (random.randomDouble() + 1.0) / 2.0;
            double grassProb = 1.0;

            WorldBiomeProvider.Biome biome = biomeProvider.getBiomeAt(c.getBlockWorldPosX(x), c.getBlockWorldPosZ(z));

            switch (biome) {
                case PLAINS:
                    grassProb = 1.0 - config.getPlainsGrassDensity();
                    break;
                case MOUNTAINS:
                    grassProb = 1.0 - config.getMountainGrassDensity();
                    break;
                case FOREST:
                    grassProb = 1.0 - config.getForestGrassDensity();
                    break;
                case SNOW:
                    grassProb = 1.0 - config.getSnowGrassDensity();
                    break;
                case DESERT:
                    grassProb = 1.0 - config.getDesertGrassDensity();
                    break;
            }

            if (grassRand > grassProb) {
                /*
                 * Generate tall grass.
                 */
                double rand = random.standNormalDistrDouble();

                /*if (rand > -1.0 && rand < 1.0) {
                    c.setBlock(x, y + 1, z, BlockManager.getInstance().getBlock("engine:CoalOre"));
                } else if (rand > -0.9 && rand < 0.9) {
                    c.setBlock(x, y + 1, z, BlockManager.getInstance().getBlock("engine:CopperOre"));
                } else if (rand > -0.8 && rand < 0.8) {
                    c.setBlock(x, y + 1, z, BlockManager.getInstance().getBlock("engine:DiamondOre"));
                } else if (rand > -0.7 && rand < 0.7) {
                    c.setBlock(x, y + 1, z, BlockManager.getInstance().getBlock("engine:GoldOre"));
                }            */

                double flowerRand = random.standNormalDistrDouble();

                /*
                 * Generate flowers.
                 */
                if (flowerRand >= -0.3 && flowerRand < -0.1) {
                    c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:CoalOre"));
                    /*} else if (flowerRand >= -0.9 && flowerRand < -0.8) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:GoldOre"));
                    } else if (flowerRand >= -0.8 && flowerRand < -0.7) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:Gravel"));
                    } else if (flowerRand >= -0.7 && flowerRand < -0.6) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:IronOre"));
                    } else if (flowerRand >= -0.6 && flowerRand < -0.5) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:GoldOre"));
                    } else if (flowerRand >= -0.5 && flowerRand < -0.4) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:IronOre"));
                    } else if (flowerRand >= -0.4 && flowerRand < -0.3) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:IronOre"));
                    } else if (flowerRand >= -0.3 && flowerRand < -0.2) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:IronOre"));
                    } else if (flowerRand >= -0.2 && flowerRand < -0.1) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:IronOre"));
                    } else if (flowerRand >= -0.1 && flowerRand < 0.0) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:GoldOre"));
                    } else if (flowerRand >= 0.0 && flowerRand < 0.1) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:GoldOre"));
                    } else if (flowerRand >= 0.1 && flowerRand < 0.2) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:CoalOre"));
                    } else if (flowerRand >= 0.2 && flowerRand < 0.3) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:CopperOre"));
                    } else if (flowerRand >= 0.3 && flowerRand < 0.4) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:CopperOre"));
                    } else if (flowerRand >= 0.4 && flowerRand < 0.5) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:GoldOre"));
                    } else if (flowerRand >= 0.5 && flowerRand < 0.6) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:DiamondOre"));
                    } else if (flowerRand >= 0.6 && flowerRand < 0.7) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:DiamondOre"));
                    } else if (flowerRand >= 0.7 && flowerRand < 0.8) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:DiamondOre"));
                    } else if (flowerRand >= 0.8 && flowerRand < 0.9) {
                        c.setBlock(x, y - 1, z, BlockManager.getInstance().getBlock("engine:DiamondOre"));   */
                }    //Flower generation
            }

        }
    }

    @Override
    public void setWorldSeed(String seed) {
        this.worldSeed = seed;
    }

    @Override
    public void setWorldBiomeProvider(WorldBiomeProvider biomeProvider) {
        this.biomeProvider = biomeProvider;
    }

    @Override
    public Map<String, String> getInitParameters() {
        return null;
    }

    @Override
    public void setInitParameters(final Map<String, String> initParameters) {
    }
}
