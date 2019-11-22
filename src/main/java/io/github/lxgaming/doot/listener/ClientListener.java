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

package io.github.lxgaming.doot.listener;

import io.github.lxgaming.doot.Doot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientListener {
    
    private static final SoundEvent DOOT_DOOT_SOUND_EVENT = new SoundEvent(new ResourceLocation(Doot.ID, "doot_doot"));
    private boolean played;
    
    @SubscribeEvent
    public void onGuiScreen(GuiScreenEvent.InitGuiEvent.Post event) {
        if (played) {
            return;
        }
        
        if (event.getGui() instanceof MainMenuScreen) {
            Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(DOOT_DOOT_SOUND_EVENT, 1.0F));
            played = true;
        }
    }
}