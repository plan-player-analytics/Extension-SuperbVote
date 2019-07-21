/*
    Copyright(c) 2019 Risto Lahtela (Rsl1122)

    The MIT License(MIT)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files(the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions :
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
*/
package com.djrapitops.extension;

import com.djrapitops.plan.extension.CallEvents;
import com.djrapitops.plan.extension.DataExtension;
import com.djrapitops.plan.extension.annotation.NumberProvider;
import com.djrapitops.plan.extension.annotation.PluginInfo;
import com.djrapitops.plan.extension.icon.Color;
import com.djrapitops.plan.extension.icon.Family;
import io.minimum.minecraft.superbvote.SuperbVote;
import io.minimum.minecraft.superbvote.storage.VoteStorage;
import io.minimum.minecraft.superbvote.util.PlayerVotes;

import java.util.UUID;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

/**
 * Template for new DataExtension.
 *
 * @author Rsl1122
 */
@PluginInfo(name = "SuperbVote", iconName = "check", iconFamily = Family.SOLID, color = Color.TEAL)
public class SuperbVoteExtension implements DataExtension {

    private VoteStorage store;

    SuperbVoteExtension(boolean forTesting) {
    }

    public SuperbVoteExtension() {
        SuperbVote plugin = getPlugin(SuperbVote.class);
        if (plugin == null) throw new IllegalStateException();
        store = plugin.getVoteStorage();
        if (store == null) throw new IllegalStateException();
    }

    @Override
    public CallEvents[] callExtensionMethodsOn() {
        return new CallEvents[]{
                CallEvents.PLAYER_LEAVE
        };
    }

    @NumberProvider(
            text = "Votes",
            description = "How many times player has voted while SuperbVote was installed",
            iconName = "check",
            iconColor = Color.TEAL,
            showInPlayerTable = true
    )
    public long votes(UUID playerUUID) {
        PlayerVotes votes = store.getVotes(playerUUID);
        return votes != null ? votes.getVotes() : 0;
    }
}