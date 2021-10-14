package seedu.kolinux.routes;

import seedu.kolinux.Main;
import seedu.kolinux.exceptions.KolinuxException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Route {

    public void readNodesFromFile(ArrayList<String> vertices, String filePath) throws IOException, KolinuxException {
        try {
            InputStream inputStream = Main.class.getResourceAsStream(filePath);
            if (inputStream == null) {
                throw new KolinuxException("File not found");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                vertices.add(line);
            }
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public void setRoute(ArrayList<String> vertices, Graph graph) throws KolinuxException {
        if (vertices == null) {
            throw new KolinuxException("Route doesn't exist");
        }
        for (String v : vertices) {
            String[] vertex = v.split(" ");
            graph.addEdge(Integer.parseInt(vertex[0]), Integer.parseInt((vertex[1])));
        }
    }

    public int getStopNumberAOne(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "kr bus terminal":
            return 0;
        case "lt13":
            return 1;
        case "as 5":
            return 2;
        case "com 2":
            return 3;
        case "biz 2":
            return 4;
        case "opp tcoms":
            return 5;
        case "pgp":
            return 6;
        case "kr mrt":
            return 7;
        case "lt27":
            return 8;
        case "uhall":
            return 9;
        case "opp uhc":
            return 10;
        case "yih":
            return 11;
        case "clb":
            return 12;
        default:
            return -1;
        }
    }

    public int getStopNumberDOne(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "opp hssml":
            return 0;
        case "opp nuss":
            return 1;
        case "com 2":
            return 2;
        case "ventus":
            return 3;
        case "it":
            return 4;
        case "opp yih":
            return 5;
        case "museum":
            return 6;
        case "utown":
            return 7;
        case "yih":
            return 8;
        case "clb":
            return 9;
        case "lt13":
            return 10;
        case "as 5":
            return 11;
        case "biz 2":
            return 12;
        default:
            return -1;
        }
    }

    public int getStopNumberDTwo(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "pgp":
            return 0;
        case "kr mrt":
            return 1;
        case "lt27":
            return 2;
        case "uhall":
            return 3;
        case "opp uhc":
            return 4;
        case "museum":
            return 5;
        case "utown":
            return 6;
        case "uhc":
            return 7;
        case "opp uhall":
            return 8;
        case "s 17":
            return 9;
        case "opp kr mrt":
            return 10;
        case "pgpr":
            return 11;
        default:
            return -1;
        }
    }

    public int getStopNumberE(String command) {
        assert command != null;
        switch (command.trim().toLowerCase()) {
        case "kent vale":
            return 0;
        case "ea":
            return 1;
        case "sd3":
            return 2;
        case "it":
            return 3;
        case "opp yih":
            return 4;
        case "utown":
            return 5;
        case "raffles hall":
            return 6;
        default:
            return -1;
        }
    }
}
