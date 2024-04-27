package ch.ksrminecraft.signs.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.HangingSign;
import org.bukkit.block.Sign;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public class CreateSignCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        // Überprüfen, ob der Befehlssender ein Spieler ist
        if (!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        // Überprüfen der Anzahl der Argumente und Senden einer Nachricht an den Spieler, wenn die Anzahl der Argumente nicht passt
        if (!(strings.length == 2)) {
            player.sendMessage(Component.text("Du musst einen Zeilennummer und ein Wort angeben!"));
            player.sendMessage(Component.text("Zum Beispiel: /sign 2 Beispieltext"));
            return true;
        }

        // Berechnung der Position vor dem Spieler auf gleicher Höhe
        // Position des Spielers ---------> Position des Schildes
        Location playerLocation = player.getLocation();
        Vector direction = playerLocation.getDirection().normalize();
        Location signLocation = playerLocation.clone().add(direction.getX(), 0, direction.getZ()).getBlock().getLocation();

        // Setzen des Schildes an der berechneten Position
        signLocation.getBlock().setType(Material.ACACIA_WALL_SIGN);

        // Abrufen der Blockdaten und Anpassen der Ausrichtung des Schildes
        BlockData blockData = signLocation.getBlock().getBlockData();
        if (blockData instanceof WallSign) {
            WallSign wallSign = (WallSign) blockData;
            wallSign.setFacing(getBlockFace(playerLocation.getYaw()));
            signLocation.getBlock().setBlockData(wallSign);
        }

        // Text auf dem Schild setzen
        Sign sign = (Sign) signLocation.getBlock().getState();
        int line = Integer.parseInt(strings[0]) - 1;
        String word = strings[1];

        if (line > 4) {
            player.sendMessage(Component.text("Du musst eine Zeile zwischen 1 und 4 angeben"));
            return true;
        }

        sign.setLine(line, word);

        // Aktualisieren des Schildzustandes, um die Änderungen anzuzeigen
        sign.update();

        return true;
    }

    // Hilfsmethode zur Bestimmung der Blickrichtung des Spielers basierend auf Yaw-Werten
    private BlockFace getBlockFace(float yaw) {
        if (yaw < 0) {
            yaw += 360;
        }
        if ((yaw >= 315) || (yaw < 45)) {
            return BlockFace.NORTH;
        } else if ((yaw < 135)) {
            return BlockFace.EAST;
        } else if ((yaw < 225)) {
            return BlockFace.SOUTH;
        } else {
            return BlockFace.WEST;
        }

    }
}
