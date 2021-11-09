package ua.com.alevel.levels.third.life;

public class PresetFigures {

    public static boolean addBlock(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+1<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+1<conwaysGameOfLife.getUniverse()[0].length)){
            conwaysGameOfLife.addPoint(initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addBeehive(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+2<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+3<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 3 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 2 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addLoaf(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+3<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+2<conwaysGameOfLife.getUniverse()[0].length)){
            conwaysGameOfLife.addPoint(initialVertPos,1+initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos,2+initialHorPos);
            conwaysGameOfLife.addPoint(1+initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1+initialVertPos,3+initialHorPos);
            conwaysGameOfLife.addPoint(2+initialVertPos,1+initialHorPos);
            conwaysGameOfLife.addPoint(2+initialVertPos,3+initialHorPos);
            conwaysGameOfLife.addPoint(3+initialVertPos,2+initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addBoat(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+2<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+2<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 1 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addTub(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+2<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+2<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 1 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addBlinker(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+2<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 2 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addToad(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+1<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+3<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 3 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 2 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addBeacon(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+3<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+3<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 3 + initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, 3 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addPentaDeacathlon(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+9<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+2<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(4 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(5 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(6 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(7 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(7 + initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(8 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(9 + initialVertPos, 1 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addGlider(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+2<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+2<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 2 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addLWSS(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+3<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+4<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 3 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 4 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 4 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 4 + initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, 3 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addMWSS(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+4<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+5<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 3 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 4 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 5 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 5 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 5 + initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, 4 + initialHorPos);
            conwaysGameOfLife.addPoint(4 + initialVertPos, 2 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }

    public static boolean addHWSS(ConwaysGameOfLife conwaysGameOfLife, int initialVertPos, int initialHorPos) {
        if ((initialVertPos >= 0 && initialVertPos+4<conwaysGameOfLife.getUniverse().length
                && initialHorPos>=0 && initialHorPos+6<conwaysGameOfLife.getUniverse()[0].length)) {
            conwaysGameOfLife.addPoint(initialVertPos, 1 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 3 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 4 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 5 + initialHorPos);
            conwaysGameOfLife.addPoint(initialVertPos, 6 + initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(1 + initialVertPos, 6 + initialHorPos);
            conwaysGameOfLife.addPoint(2 + initialVertPos, 6 + initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, initialHorPos);
            conwaysGameOfLife.addPoint(3 + initialVertPos, 5 + initialHorPos);
            conwaysGameOfLife.addPoint(4 + initialVertPos, 2 + initialHorPos);
            conwaysGameOfLife.addPoint(4 + initialVertPos, 3 + initialHorPos);
            return true;
        } else {
            return false;
        }
    }
}
