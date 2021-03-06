package me.joeleoli.practice.game.party.argument;

import me.joeleoli.practice.command.CommandException;
import me.joeleoli.practice.command.PluginCommandArgument;
import me.joeleoli.practice.game.party.Party;
import me.joeleoli.practice.manager.ManagerHandler;
import me.joeleoli.practice.player.PracticeProfile;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartyInfoArgument extends PluginCommandArgument {

    private List<String> aliases = Collections.emptyList();

    public List<String> getAliases() {
        return this.aliases;
    }

    public boolean requiresPlayer() {
        return false;
    }

    public boolean requiresPermission() {
        return false;
    }

    public String getPermission() {
        return "";
    }

    public void onCommand(CommandSender sender, String[] args) throws CommandException {
        Player player = (Player) sender;
        PracticeProfile profile = ManagerHandler.getPlayerManager().getPlayerProfile(player);
        Party party = profile.getParty();

        if (party == null) {
            throw new CommandException(Collections.singletonList("You do not have a party."));
        }

        player.sendMessage(ChatColor.LIGHT_PURPLE + "Party of " + party.getLeader().getName());

        if (party.getPlayers().isEmpty()) {
            player.sendMessage(ChatColor.RED + "You have nobody in your party.");
        }
        else {
            String msg = ChatColor.GOLD + "Players: " + ChatColor.YELLOW + "";

            for (Player p : party.getPlayers()) {
                msg += p.getName() + ", ";
            }

            player.sendMessage(msg.substring(0, msg.length() - 2));
        }
    }

    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

}