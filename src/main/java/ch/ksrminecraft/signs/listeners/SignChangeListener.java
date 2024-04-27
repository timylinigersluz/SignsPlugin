package ch.ksrminecraft.signs.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent event){

        // Dem Spieler eine Nachricht schicken
        event.getPlayer().sendMessage(Component.text("Du hast das Schild verändert!"));

        // Den Text des Schildes auslesen
        String[] lines = event.getLines();

        // Aus einer Liste von Wörtern ein String machen
        String text = "";
        for (String line : lines) {
            text += line;
            text += " ";
        }
        // Den Schildertext an den Spieler schicken
        event.getPlayer().sendMessage(Component.text(text));
    }
}
