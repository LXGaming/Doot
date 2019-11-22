/*
 * Copyright 2019 Alex Thomson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lxgaming.doot;

import io.github.lxgaming.doot.listener.ClientListener;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = Doot.ID)
public class Doot {
    
    public static final String ID = "doot";
    public static final String NAME = "Doot";
    public static final String VERSION = "${version}";
    
    private static Doot instance;
    private final Logger logger = LogManager.getLogger(Doot.NAME);
    
    public Doot() {
        instance = this;
        
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            ClientListener clientListener = new ClientListener();
            FMLJavaModLoadingContext.get().getModEventBus().register(clientListener);
            MinecraftForge.EVENT_BUS.register(clientListener);
        });
        
        StartupMessageManager.addModMessage(String.format("%s v%s Initialized", Doot.NAME, Doot.VERSION));
        getLogger().info("{} v{} Initialized", Doot.NAME, Doot.VERSION);
    }
    
    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {
        StartupMessageManager.addModMessage(String.format("%s v%s Setup", Doot.NAME, Doot.VERSION));
        getLogger().info("{} v{} Setup", Doot.NAME, Doot.VERSION);
    }
    
    public static Doot getInstance() {
        return instance;
    }
    
    public Logger getLogger() {
        return logger;
    }
}