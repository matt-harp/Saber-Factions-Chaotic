package com.massivecraft.factions.cmd.claim;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FactionsPlugin;
import com.massivecraft.factions.cmd.CommandContext;
import com.massivecraft.factions.cmd.CommandRequirements;
import com.massivecraft.factions.cmd.FCommand;
import com.massivecraft.factions.cmd.audit.FLogType;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.util.CC;
import com.massivecraft.factions.zcore.fperms.PermissableAction;
import com.massivecraft.factions.zcore.util.TL;

public class CmdClaimAt extends FCommand {

    /**
     * @author FactionsUUID Team
     */

    public CmdClaimAt() {
        super();
        this.aliases.add("claimat");

        this.requiredArgs.add("world");
        this.requiredArgs.add("x");
        this.requiredArgs.add("z");

        this.requirements = new CommandRequirements.Builder(Permission.CLAIMAT)
                .playerOnly()
                .memberOnly()
                .withAction(PermissableAction.TERRITORY)
                .build();
    }

    @Override
    public void perform(CommandContext context) {
        int x = context.argAsInt(1);
        int z = context.argAsInt(2);
        FLocation location = new FLocation(context.argAsString(0), x, z);
        context.fPlayer.attemptClaim(context.faction, location, true);
        FactionsPlugin.instance.logFactionEvent(context.fPlayer.getFaction(), FLogType.CHUNK_CLAIMS, context.fPlayer.getName(), CC.GreenB + "CLAIMED", "1", (location).formatXAndZ(","));
    }


    @Override
    public TL getUsageTranslation() {
        return null;
    }
}
