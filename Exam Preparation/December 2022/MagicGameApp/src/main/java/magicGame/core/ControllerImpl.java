package magicGame.core;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.MagicImpl;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static magicGame.common.ExceptionMessages.*;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGIC;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN;

public class ControllerImpl implements Controller{
    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private RegionImpl region;

    public ControllerImpl() {
        this.magics = new MagicRepositoryImpl();
        this.magicians = new MagicianRepositoryImpl();
        this.region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        if (type.equals("RedMagic")) {
            magic = new RedMagic(name, bulletsCount);
        } else if (type.equals("BlackMagic")) {
            magic = new BlackMagic(name, bulletsCount);
        } else {
            throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }

        this.magics.addMagic(magic);

        return String.format(SUCCESSFULLY_ADDED_MAGIC, magic.getName());
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magic = this.magics.findByName(magicName);
        if (magic == null) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }
        Magician magician;
        if (type.equals("Wizard")) {
            magician = new Wizard(username, health, protection, magic);
        } else if (type.equals("BlackWidow")) {
            magician = new BlackWidow(username, health, protection, magic);
        } else {
            throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }

        this.magicians.addMagician(magician);

        return String.format(SUCCESSFULLY_ADDED_MAGICIAN, magician.getUsername());
    }

    @Override
    public String startGame() {
        List<Magician> aliveMagicians = this.magicians.getData().stream().filter(Magician::isAlive).collect(Collectors.toList());

        return this.region.start(aliveMagicians);
    }

    @Override
    public String report() {
        return this.magicians.getData().stream()
                .sorted(Comparator.comparing(Magician::getHealth)
                        .thenComparing(Magician::getUsername)
                        .thenComparing(m -> m.getClass().getSimpleName()))
                .map(Magician::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
