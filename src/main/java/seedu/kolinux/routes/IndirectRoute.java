package seedu.kolinux.routes;

import seedu.kolinux.exceptions.KolinuxException;

import java.io.IOException;
import java.util.ArrayList;

public class IndirectRoute extends Route {

    public IndirectRoute(String[] splitInput) throws KolinuxException, IOException {
        super(splitInput);
        getBusStopNumber();
    }

    /**
     * Checks if any 2 vertices are connected by an indirect path which
     * requires a change of bus.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    public boolean checkIndirectRoutes(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        if (vertexCodeA1[0] >= 0 && checkIndirectAOne(busOne, busTwo, midLoc)) {
            return true;
        }
        if (vertexCodeA2[0] >= 0 && checkIndirectATwo(busOne, busTwo, midLoc)) {
            return true;
        }
        if (vertexCodeD1[0] >= 0 && graph[2].isConnected(vertexCodeD1[0], location.getStopNumberDOne(STOP_UTOWN))
                && checkIndirectDOne(busOne, busTwo, midLoc)) { //extra condition because D1 is not a loop service
            return true;
        }
        if (vertexCodeD2[0] >= 0 && graph[3].isConnected(vertexCodeD2[0], location.getStopNumberDTwo(STOP_UTOWN))
                && checkIndirectDTwo(busOne, busTwo, midLoc)) { //extra condition because D2 is not a loop service
            return true;
        }
        if (vertexCodeE[0] >= 0 && checkIndirectE(busOne, busTwo, midLoc)) {
            return true;
        }
        if (vertexCodeK[0] >= 0 && graph[5].isConnected(vertexCodeK[0], location.getStopNumberK(STOP_KENT_VALE))
                && checkIndirectK(busOne, busTwo, midLoc)) { //extra condition because K is not a loop service
            return true;
        }
        if (vertexCodeD2[0] == STOP_NUMBER_UTOWN_D2 && checkIndirectDTwo(busOne, busTwo, midLoc)) {
            return true;
        }
        if (vertexCodeK[0] == STOP_NUMBER_KENT_VALE_K && checkIndirectK(busOne, busTwo, midLoc)) {
            return true;
        }
        return false;
    }

    /**
     * Checks for indirect route in bus route A1.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectAOne(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        if (graph[3].isConnected(location.getStopNumberDTwo(STOP_PGP), vertexCodeD2[1])) {
            busTwo.add(BUS_D2);
            flag = true;
        }
        if (graph[5].isConnected(location.getStopNumberK(STOP_PGP), vertexCodeK[1])) {
            busTwo.add(BUS_K);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_A1);
            midLoc.add(STOP_PGP);
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route A2.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectATwo(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        if (graph[2].isConnected(location.getStopNumberDOne(STOP_IT), vertexCodeD1[1])) {
            busTwo.add(BUS_D1);
            flag = true;
        }
        if (graph[4].isConnected(location.getStopNumberE(STOP_IT), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_A2);
            midLoc.add(STOP_IT);
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route D1.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectDOne(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        if (graph[3].isConnected(location.getStopNumberDTwo(STOP_UTOWN), vertexCodeD2[1])) {
            busTwo.add(BUS_D2);
            flag = true;
        }
        if (graph[4].isConnected(location.getStopNumberE(STOP_UTOWN), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_D1);
            midLoc.add(STOP_UTOWN);
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route D2.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectDTwo(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        if (vertexCodeD2[0] == STOP_NUMBER_UTOWN_D2) {
            if (graph[1].isConnected(location.getStopNumberATwo(STOP_PGPR), vertexCodeA2[1])) {
                busOne.add(BUS_D2);
                busTwo.add(BUS_A2);
                midLoc.add(STOP_PGPR);
                return true;
            }
        }
        if (graph[2].isConnected(location.getStopNumberDOne(STOP_UTOWN), vertexCodeD1[1])) {
            busTwo.add(BUS_D1);
            flag = true;
        }
        if (graph[4].isConnected(location.getStopNumberE(STOP_UTOWN), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_D2);
            midLoc.add(STOP_UTOWN);
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route E.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectE(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        if (vertexCodeE[0] == STOP_NUMBER_RAFFLES_HALL_E) {
            if (graph[5].isConnected(location.getStopNumberK(STOP_KENT_VALE), vertexCodeK[1])) {
                busTwo.add(BUS_K);
                flag = true;
            }
            if (flag) {
                busOne.add(BUS_E);
                midLoc.add(STOP_KENT_VALE);
            }
        } else {
            if (graph[2].isConnected(location.getStopNumberDOne(STOP_UTOWN), vertexCodeD1[1])) {
                busTwo.add(BUS_D1);
                flag = true;
            }
            if (graph[3].isConnected(location.getStopNumberDTwo(STOP_UTOWN), vertexCodeD2[1])) {
                busTwo.add(BUS_D2);
                flag = true;
            }
            if (flag) {
                busOne.add(BUS_E);
                midLoc.add(STOP_UTOWN);
            }
        }
        return flag;
    }

    /**
     * Checks for indirect route in bus route K.
     *
     * @param busOne bus number which connects to the intermediate bus stop
     * @param busTwo bus number which connects from the intermediate bus stop
     *               to the final location
     * @param midLoc is the intermediate bus stop
     * @return true if connected, false otherwise
     */
    private boolean checkIndirectK(ArrayList<String> busOne, ArrayList<String> busTwo, ArrayList<String> midLoc) {
        boolean flag = false;
        if (vertexCodeK[0] == STOP_NUMBER_KENT_VALE_K) {
            if (graph[1].isConnected(location.getStopNumberATwo(STOP_PGPR), vertexCodeA2[1])) {
                busOne.add(BUS_K);
                busTwo.add(BUS_A2);
                midLoc.add(STOP_PGPR);
                return true;
            }
        }
        if (graph[4].isConnected(location.getStopNumberE(STOP_KENT_VALE), vertexCodeE[1])) {
            busTwo.add(BUS_E);
            midLoc.add(STOP_KENT_VALE);
            flag = true;
        }
        if (graph[1].isConnected(location.getStopNumberATwo(STOP_PGPR), vertexCodeA2[1])) {
            busTwo.add(BUS_A2);
            midLoc.add(STOP_PGPR);
            flag = true;
        }
        if (flag) {
            busOne.add(BUS_K);
        }
        return flag;
    }
}